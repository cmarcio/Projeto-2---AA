package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;


public class TimeSerie {
    ArrayList<Double> values;
    int classNumber;
    TimeSerie closestSerie;
    double distance;

    public TimeSerie(int classNumber) {
        this.classNumber = classNumber;
        values = new ArrayList<>();
        distance = Double.MAX_VALUE;
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
            dtw[0][i] = Double.MAX_VALUE;
        dtw[0][0] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                double cost = Math.abs(this.values.get(i) - serie.values.get(j));
                dtw[i][j] = cost + Math.min(dtw[i - 1][j], Math.min(dtw[i][j - 1], dtw[i - 1][j - 1]));
            }
        }

        // Verifica se a série a série testada é a mais próxima
        classify(dtw[n-1][m-1], serie);

        return dtw[n-1][m-1];
    }

    public double dtwOptimized(TimeSerie serie, double band){
        int n = this.values.size();
        int m = serie.values.size();

        int w = (int) ((Math.abs(n-m)+1) + Math.max(m, n)*band); // adapt window size (*)

        double[][] dtw = new double[n][m];
        //int [][] matrix = new int[n][m];

        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++) {
                //matrix[i][j] = 0;
                dtw[i][j] = Double.MAX_VALUE;
            }
        dtw[0][0] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = Math.max(1, i-w); j < Math.min(m, i+w); j++) {
                //matrix[i][j] = 1;
                double cost = Math.abs(this.values.get(i) - serie.values.get(j));
                dtw[i][j] = cost + Math.min(dtw[i - 1][j], Math.min(dtw[i][j - 1], dtw[i - 1][j - 1]));
            }
        }

        /*for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if (matrix[i][j] == 0)
                    System.out.print(" * ");
                else
                    System.out.print("   ");

            }
            System.out.print("\n");
        }
        System.out.println("--------------------------------------------------------------");*/

        // Verifica se a série a série testada é a mais próxima
        classify(dtw[n-1][m-1], serie);

        return dtw[n-1][m-1];
    }

    private void classify(double distance, TimeSerie serieT) {
        if (distance < this.distance) {
            this.closestSerie = serieT;
            this.distance = distance;
        }
    }

    public boolean isClassificationRight(){
        if (classNumber == closestSerie.getClassNumber())
            return true;
        else return false;
    }
}
