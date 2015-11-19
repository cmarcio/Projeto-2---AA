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

        int size = training.size();
        int i = 0;
        int acertos = 0;
        ArrayList<TimeSerie> test = new ArrayList<>();
        // Processa cada linha do arquivo de teste e contabiliza um acerto ou erro
        while(testFile.hasNextLine()) {
            test.add(testFile.nextSerie());
            int j = 0;
            while (j < size) {
                double newDistance = distancesCalculator.DTWDistance(test.get(i), training.get(j));
                test.get(i).classify(j, newDistance);
                j++;
            }
            if (test.get(i).getClassNumber() == training.get(test.get(i).getClosest_serie()).getClassNumber())
                acertos++;
            i++;
        }
    }
}
