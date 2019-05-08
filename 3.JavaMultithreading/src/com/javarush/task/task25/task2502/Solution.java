package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;


        public Car() throws Exception {
            List<Wheel>list = new ArrayList<>();
            String a = Wheel.BACK_LEFT.toString();
            String b = Wheel.BACK_RIGHT.toString();
            String c = Wheel.FRONT_LEFT.toString();
            String d = Wheel.FRONT_RIGHT.toString();
            if (loadWheelNamesFromDB().length!= 4){
                throw new Exception();
            }

            for (String wl : loadWheelNamesFromDB()) {
                if (wl!=a &&wl!=b&&wl!=c &&wl!=d){
                    System.out.println(wl);
                    throw new Exception();
                }
            }
            for (int i = 0; i < loadWheelNamesFromDB().length; i++){
                list.add(Wheel.valueOf(loadWheelNamesFromDB()[i]));
            }
            wheels = list;

        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) throws Exception {
        Car car = new Car();
    }
}
