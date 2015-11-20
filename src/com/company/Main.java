package com.company;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int hits = 0;
        int total = 0;
        long startTime;
        long elapsedTime = 0;

        // Verifica qual o teste que o usuário quer fazer
        Main.printUserOptions();
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();

        // Se for o teste unidimencional
        if (option == 1){
            ReadFile testFile = new ReadFile("input/teste.txt");
            ReadFile trainingFile = new ReadFile("input/treino.txt");

            // Coloca as series do arquivo se testes em uma lista
            ArrayList<TimeSerie> trainingSeries = trainingFile.getAllSeries();

            // Marca o inicio dos testes
            startTime = System.currentTimeMillis();

            // Pra cada serie do arquivo de teste, encontra a serie mais similar na lista de treinos
            while(testFile.hasNextLine()) {
                TimeSerie serieT = testFile.nextSerie();
                total++;

                for (int i = 0; i < trainingSeries.size(); i++)
                    // Calcula a dtw e faz a classificacao
                    serieT.dtw(trainingSeries.get(i));

                // Verifica se a classificacao foi correta e contabiliza os acertos
                if (serieT.isClassificationRight())
                    hits++;
            }
            // Marca o tempo decorrido
            elapsedTime = System.currentTimeMillis() - startTime;
        }
        // Se for o teste unidimensional com banda de restrição
        else if (option == 2) {
            ReadFile testFile = new ReadFile("input/teste.txt");
            ReadFile trainingFile = new ReadFile("input/treino.txt");

            // Coloca as series do arquivo se testes em uma lista
            ArrayList<TimeSerie> trainingSeries = trainingFile.getAllSeries();

            // Pergunta ao usuario qual o tamanho da banda de restrição
            System.out.println("Qual o tamanho da banda de restrição?");
            System.out.println("Digite valores de 0 até 100. (%)");
            double interval = input.nextInt() * 0.01;

            // Marca o inicio dos testes
            startTime = System.currentTimeMillis();

            // Pra cada serie do arquivo de teste, encontra a serie mais similar na lista de treinos
            while(testFile.hasNextLine()) {
                TimeSerie serieT = testFile.nextSerie();
                total++;

                for (int i = 0; i < trainingSeries.size(); i++)
                    // Calcula a dtw e faz a classificacao
                    serieT.dtwOptimized(trainingSeries.get(i), interval);

                // Verifica se a classificacao foi correta e contabiliza os acertos
                if (serieT.isClassificationRight())
                    hits++;
            }
            // Marca o tempo decorrido
            elapsedTime = System.currentTimeMillis() - startTime;
        }
        // Se for o teste multidimensional
        else {

        }

        // imprime o resultado
        System.out.print("Total de testes:" + total + "\nAcertos:" + hits + "\nTaxa de acertos:" + (hits*1.0/total*1.0) + "\nTempo decorrido:" + elapsedTime +"ms");
    }

    private static void printUserOptions(){
        System.out.println("-----> MENU <-----");
        System.out.println();
        System.out.println("1 - DTW");
        System.out.println("2 - DTW com Banda de restrição");
        System.out.println("3 - DTW multidimensional");
    }
}
