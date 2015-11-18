package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ReadFile trainingFile = new ReadFile("input/treino.txt");
        ReadFile testFile = new ReadFile("input/teste.txt");
        DTW distancesCalculator = new DTW();

        // Guarda as series temporais do arquivo de treino em uma lista
        ArrayList<TimeSerie> training = new ArrayList<>();
        while(trainingFile.hasNextLine()) {
            training.add(trainingFile.nextSerie());
        }

        int i = 0;
        ArrayList<TimeSerie> test = new ArrayList<>();
        // Processa cada linha do arquivo de teste e contabiliza um acerto ou erro
        while(testFile.hasNextLine()) {
            test.add(testFile.nextSerie());
            double newDistance = distancesCalculator.DTWDistance(test.get(i), training.get(i));
            test.get(i).classify(i, newDistance);
            i++;
        }
    }
}
