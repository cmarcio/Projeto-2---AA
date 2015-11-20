package com.company;

import java.sql.Time;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ReadFile trainingFile = new ReadFile("input/treino.txt");
        ReadFile testFile = new ReadFile("input/teste.txt");
        int hits = 0;
        int total = 0;

        // Guarda as series temporais do arquivo de treino em uma lista
        ArrayList<TimeSerie> training = new ArrayList<>();
        while(trainingFile.hasNextLine()) {
            training.add(trainingFile.nextSerie());
        }
        training.trimToSize();

        // Marca o inicio dos testes
        long startTime = System.currentTimeMillis();

        // Processa cada linha do arquivo de teste e contabiliza um acerto ou erro
        while(testFile.hasNextLine()) {
            TimeSerie serieT = testFile.nextSerie();
            total++;
            for (int i = 0; i < training.size(); i++) {
                // Calcula a dtw e faz a classificacao
                serieT.dtwOptimized(training.get(i), 1);
            }
            // Verifica se a classificacao foi correta e contabiliza os acertos
            if (serieT.isClassificationRight())
                hits++;
        }

        long elapsedTime = System.currentTimeMillis() - startTime;

        // imprime o resultado
        System.out.print("Total de testes:" + total + "\nAcertos:" + hits + "\nTaxa de acertos:" + (hits*1.0/total*1.0) + "\nTempo decorrido:" + elapsedTime +"ms");
    }
}
