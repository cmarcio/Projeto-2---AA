package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ReadFile trainingFile = new ReadFile("input/treino.txt");
        ReadFile testFile = new ReadFile("input/teste.txt");

        // Guarda as séries temporais do arquivo de treino em uma lista
        ArrayList<TimeSerie> training = new ArrayList<>();
        while(trainingFile.hasNextLine()) {
            training.add(trainingFile.nextSerie());
        }

        // Processa cada linha do arquivo de teste e contabiliza um acerto ou erro
        while(testFile.hasNextLine()) {

        }



    }
}
