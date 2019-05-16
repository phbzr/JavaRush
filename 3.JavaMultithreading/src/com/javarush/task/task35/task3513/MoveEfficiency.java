package com.javarush.task.task35.task3513;


public class MoveEfficiency implements Comparable {

    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {

        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) return 1;
        MoveEfficiency efficiency = (MoveEfficiency)o;
        if (numberOfEmptyTiles != efficiency.numberOfEmptyTiles) {
            return Integer.compare(numberOfEmptyTiles, efficiency.numberOfEmptyTiles);
        } else {
            return Integer.compare(score, efficiency.score);
        }
    }

    public int compareTo(MoveEfficiency another) {
        if (numberOfEmptyTiles != another.numberOfEmptyTiles) {
            return Integer.compare(numberOfEmptyTiles, another.numberOfEmptyTiles);
        } else {
            return Integer.compare(score, another.score);
        }
    }
}