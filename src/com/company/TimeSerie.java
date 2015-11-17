package com.company;

import java.util.ArrayList;
import java.util.Comparator;


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

    public double dtw(TimeSerie serie){
        int n = this.values.size();
        int m = serie.values.size();

        double[][] dtw = new double[n][m];

        for (int i = 1; i < n; i++)
            dtw[i][0] = Double.MAX_VALUE;
        for (int i = 1; i < m; i++)
            dtw[m][1] = Double.MAX_VALUE;
        dtw[0][0] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                double cost = Math.abs(this.values.get(i) - serie.values.get(j));
                dtw[i][j] = cost + Math.min(dtw[i - 1][j], Math.min(dtw[i][j - 1], dtw[i - 1][j - 1]));
            }
        }
        return dtw[n][m];
    }
        /*int DTWDistance(s: array [1..n], t: array [1..m]) {
            DTW := array [0..n, 0..m]

            for i := 1 to n
                DTW[i, 0] := infinity
            for i := 1 to m
                DTW[0, i] := infinity
            DTW[0, 0] := 0

            for i := 1 to n
                for j := 1 to m
                    cost:= d(s[i], t[j])
                    DTW[i, j] := cost + minimum(DTW[i-1, j  ],    // insertion
                                            DTW[i  , j-1],    // deletion
                                            DTW[i-1, j-1])    // match

            return DTW[n, m]*/
}
