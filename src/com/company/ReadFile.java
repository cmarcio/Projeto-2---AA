package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
        TimeSerie t = new TimeSerie(Integer.valueOf(lines[0]));
        for (int i = 1; i < lines.length; i++) {
            t.addValue(Double.valueOf(lines[i]));
        }
        return t;
    }

    public ArrayList<TimeSerie> getAllSeries() {
        ArrayList<TimeSerie> list = new ArrayList<>();
        while(this.hasNextLine()) {
            list.add(this.nextSerie());
        }
        list.trimToSize();

        return list;
    }

    public boolean hasNextLine(){
        return fileReader.hasNextLine();
    }
}
