package com.javarush.task.task29.task2909.car;

import java.util.Date;

public abstract class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;
    static public final int MAX_TRUCK_SPEED = 80;
    static public final int MAX_SEDAN_SPEED = 120;
    static public final int MAX_CABRIOLET_SPEED = 90;


    public static Car create(int type,int numberOfPassengers){
        Car car = null;

        if (type== 0){
            car = new Truck(numberOfPassengers);
        }
        if (type== 1){
            car = new Sedan(numberOfPassengers);
        }
        if (type== 2){
            car = new Cabriolet(numberOfPassengers);
        }
        return car;
    }

    private boolean canPassengersBeTransferred(){
        if (isDriverAvailable()&& fuel>0){
            return true;
        } else {
            return false;
        }
    }

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    public boolean isSummer(Date date , Date summerStart, Date summerEnd){
        if (date.before(summerStart) || date.after(summerEnd)){
            return false;
        } else {
            return true;
        }
    }

    public double getWinterConsumption(int length){

        return length * winterFuelConsumption + winterWarmingUp;
    }

    public double getSummerConsumption(int length){
        return length * summerFuelConsumption;
    }

    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    public void fill(double numberOfLiters) throws Exception {
        if (numberOfLiters < 0)
            throw new Exception();
        fuel += numberOfLiters;
    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        double consumption;
        if (isSummer(date,SummerStart,SummerEnd)){
            consumption = getSummerConsumption(length);
        } else {
            consumption = getWinterConsumption(length);
        }
        return consumption;
    }

    public int getNumberOfPassengersCanBeTransferred() {
        if (!canPassengersBeTransferred())
            return 0;

        return numberOfPassengers;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        if (numberOfPassengers > 0) {
            fastenPassengersBelts();

        }  fastenDriverBelt();

    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

    abstract public int getMaxSpeed();
}