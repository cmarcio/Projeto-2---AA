package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Marcio on 17/11/2015.
 */
public class ReadFile {
    Scanner fileReader;

    public ReadFile(String fileName) {
        // Abre um arquivo pra leitura
        File file = new File(fileName);
        try {
            fileReader = new Scanner(file, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Erro ao abrir arquivo!");
            return;
        }
    }

    public TimeSerie nextSerie() {
        String line = fileReader.nextLine();
        String[] lines = line.split(" ");
        TimeSerie t = new TimeSerie(String.lines[0]);
        for (int i = 1; i < lines.length; i++) {

        }
        return null;
    }

}
