package com.javarush.task.task35.task3513;


import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

public class Model {

    private static final int  FIELD_WIDTH = 4;
    private Tile[][] gameTiles;

    int score;
    int maxTile;

    public Stack<Tile[][]> previousStates = new Stack<>();
    public Stack<Integer> previousScores = new Stack<>();

    boolean isSaveNeeded = true;

    public Model() {
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    private void saveState(Tile[][] tiles) {
        previousStates.push(deepTileCopy(tiles));
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if(!previousStates.isEmpty())
            gameTiles = previousStates.pop();
        if(!previousScores.isEmpty())
            score = previousScores.pop();
    }

    private Tile[][] deepTileCopy(Tile[][] tiles) {
        Tile[][] newTile = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                newTile[i][j] = new Tile(tiles[i][j].value);
            }
        }
        return newTile;
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue<>(4,Collections.reverseOrder());
        priorityQueue.offer(getMoveEfficiency(this::left));
        priorityQueue.offer(getMoveEfficiency(this::right));
        priorityQueue.offer(getMoveEfficiency(this::up));
        priorityQueue.offer(getMoveEfficiency(this::down));
        priorityQueue.peek().getMove().move();

    }

    private  boolean hasBoardChanged() {
        int sum1 = 0;
        int sum2 = 0;
        if(!previousStates.isEmpty()) {
            Tile[][] prevGameTiles = previousStates.peek();
            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    sum1 += gameTiles[i][j].value;
                    sum2 += prevGameTiles[i][j].value;
                }
            }
        }
        return sum1 != sum2;
    }

    public MoveEfficiency getMoveEfficiency(Move move){
        MoveEfficiency moveEfficiency;
        move.move();
        if(hasBoardChanged()){
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        }else{
            moveEfficiency = new MoveEfficiency(-1, 0, move);
        }
        rollback();
        return moveEfficiency;
    }

    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch(n) {
            case 0 :
                left();
                break;
            case 1 :
                right();
                break;
            case 2 :
                up();
                break;
            case 3 :
                down();
                break;
        }
    }

    public boolean canMove() {
        if(!getEmptyTiles().isEmpty())
            return true;
        for(int i = 0; i < gameTiles.length; i++) {
            for(int j = 1; j < gameTiles.length; j++) {
                if(gameTiles[i][j].value == gameTiles[i][j-1].value)
                    return true;
            }
        }
        for(int j = 0; j < gameTiles.length; j++) {
            for(int i = 1; i < gameTiles.length; i++) {
                if(gameTiles[i][j].value == gameTiles[i-1][j].value)
                    return true;
            }
        }
        return false;
    }

    private void addTile() {
        ArrayList<Tile> emptyTiles = getEmptyTiles();
        if (!emptyTiles.isEmpty()) {
            Tile tile = emptyTiles.get((int)(Math.random() * emptyTiles.size()));
            tile.value = Math.random() < 0.9 ? 2 : 4;
        }

    }

    private void turnRight(Tile[][] tiles) {
        Tile[][] newTile = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                newTile[j][tiles.length - (i + 1)] = tiles[i][j];
            }
        }
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tiles[i][j] = newTile[i][j];
            }
        }
    }

    private ArrayList<Tile> getEmptyTiles() {
        ArrayList<Tile> list = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value == 0) {
                    list.add(gameTiles[i][j]);
                }
            }
        }
        return list;
    }

    void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }

        addTile();
        addTile();

        score = 0;
        maxTile = 0;
    }

    protected void left() {
        if (isSaveNeeded) saveState(gameTiles);

        boolean needToAdd = false;
        for (Tile[] row : gameTiles) {
            if (compressTiles(row) | mergeTiles(row)) {
                needToAdd = true;
            }
        }
        if (needToAdd) addTile();
        isSaveNeeded = true;
    }

    protected void right() {
        saveState(gameTiles);
        turnRight(gameTiles);
        turnRight(gameTiles);
        left();
        turnRight(gameTiles);
        turnRight(gameTiles);
    }

    protected void up() {
        saveState(gameTiles);
        turnRight(gameTiles);
        turnRight(gameTiles);
        turnRight(gameTiles);
        left();
        turnRight(gameTiles);
    }

    protected void down() {
        saveState(gameTiles);
        turnRight(gameTiles);
        left();
        turnRight(gameTiles);
        turnRight(gameTiles);
        turnRight(gameTiles);
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isChanged = false;
        ArrayList<Tile> list = new ArrayList<>();
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].value != 0) {
                list.add(tiles[i]);
            }
        }
        for (int i = list.size(); i < tiles.length; i++) {
            list.add(new Tile());
        }
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].value != list.get(i).value) {
                isChanged = true;
            }
            tiles[i] = list.get(i);
        }
        return isChanged;
    }


    private boolean mergeTiles(Tile[] tiles) {
        boolean isChanged = false;
        for (int j = 0; j < tiles.length-1; j++) {
            if(tiles[j].value==tiles[j+1].value && tiles[j].value!=0)
            {
                isChanged = true;
                tiles[j].value*=2;
                if(tiles[j].value > maxTile)
                {
                    maxTile=tiles[j].value;
                }
                score+=tiles[j].value;
                tiles[j+1].value=0;
                compressTiles(tiles);
            }
        }
        return isChanged;
    }
}
