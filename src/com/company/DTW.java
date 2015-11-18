package com.company;

public class DTW {

    private double cost;

    public double DTWDistance(TimeSerie s, TimeSerie t) {
        double[][] dtw = new double[s.getSizeSerie()][t.getSizeSerie()];

        for (int i = 1; i < s.getSizeSerie(); i++)
            dtw[i][0] = Float.MAX_VALUE;
        for (int i = 1; i < t.getSizeSerie(); i++)
            dtw[0][i] = Float.MAX_VALUE;
        dtw[0][0] = 0;

        for (int i = 1; i < s.getSizeSerie(); i++) {
            for (int j = 1; j < t.getSizeSerie(); j++) {
                cost = Math.abs(s.getValues().get(i) - t.getValues().get(j));
                dtw[i][j] = cost + Math.min(dtw[i-1][j], Math.min(dtw[i][j-1], dtw[i-1][j-1]));
            }
        }
        return dtw[s.getSizeSerie()-1][t.getSizeSerie()-1];
    }
}
