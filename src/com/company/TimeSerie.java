package com.company;

import java.util.ArrayList;

public class TimeSerie {
    ArrayList<Double> values;
    int classNumber;

    public TimeSerie(int classNumber) {
        this.classNumber = classNumber;
        values = new ArrayList<>();
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
}
