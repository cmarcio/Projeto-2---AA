package com.company;

import java.util.ArrayList;

public class TimeSerie {
    private ArrayList<Double> values;
    private int classNumber;
    private int closest_serie;
    private double distance;

    public TimeSerie(int classNumber) {
        this.classNumber = classNumber;
        values = new ArrayList<>();
        distance = Integer.MAX_VALUE;
    }

    public void addValue(double value) {
        values.add(value);
    }

    public ArrayList<Double> getValues() {
        return values;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public int getSizeSerie() { return values.size(); }

    public int getClosest_serie() { return closest_serie; }

    public void classify(int serie, double distance){
        if (distance < this.distance)
            closest_serie = serie;
    }
}
