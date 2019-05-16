package com.javarush.task.task35.task3513;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {

    private Model model;
    private View view;

    private static final int WINNING_TILE = 2048;

    public View getView() {
        return view;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            resetGame();
        }
        if (!model.canMove()) {
            view.isGameLost = true;
        }
        if (!view.isGameLost && !view.isGameWon) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                model.left();
            }
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                model.right();
            }
            else if (e.getKeyCode() == KeyEvent.VK_UP) {
                model.up();
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                model.down();
            }
            else if (e.getKeyCode() == KeyEvent.VK_Z) {
                model.rollback();
            }
            else if (e.getKeyCode() == KeyEvent.VK_R) {
                model.randomMove();
            }
            else if (e.getKeyCode() == KeyEvent.VK_A) {
                model.autoMove();
            }
        }
        if (model.maxTile == WINNING_TILE) {
            view.isGameWon = true;
        }
        view.repaint();
    }

    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    public void resetGame() {
        model.score = 0;
        model.maxTile = 2;
        view.isGameLost = false;
        view.isGameWon = false;
        model.resetGameTiles();
    }

    public int getScore() {
        return model.score;
    }
}