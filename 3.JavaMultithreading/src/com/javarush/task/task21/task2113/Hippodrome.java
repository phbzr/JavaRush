package com.javarush.task.task21.task2113;


import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    private List<Horse>horses;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }
    public Horse getWinner(){
        Horse winner = null;
        double tmp = 0;
        for (int i = 0; i< horses.size(); i++){
            horses.get(i).getDistance();
            if (horses.get(i).getDistance()> tmp){
                tmp = horses.get(i).getDistance();
                winner = horses.get(i);
            }
        }

        return winner;

    }
    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName()+ "!");
    }
    public void run(){
        for (int i = 0; i<100; i++){
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void move(){
        for (int i = 0; i<horses.size(); i++){
            horses.get(i).move();
        }
    }
    public void print(){
        for (int i = 0; i<horses.size(); i++){
            horses.get(i).print();
        }
        for (int i = 0; i < 10; i++){
            System.out.println();
        }
    }

    static Hippodrome game;

    public static void main(String[] args) {
        Horse horse1 = new Horse("Fialka",3,0);
        Horse horse2 = new Horse("Rose", 3, 0);
        Horse horse3 = new Horse("Romashka", 3, 0);
        List<Horse> list = new ArrayList<>();
        list.add(horse1);
        list.add(horse2);
        list.add(horse3);
        Hippodrome hippodrome = new Hippodrome(list);
        game = hippodrome;
        game.run();
        game.getWinner();
        game.printWinner();
    }
}
