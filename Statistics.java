package com.tictac;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Statistics {
    static void saveStatistics(char winner) {
        try {
            FileWriter writer = new FileWriter(Constants.st.getPath(), true); // append

            if (winner == 'D') Constants.st.setResult("Нічия");
            else Constants.st.setResult((winner == 'X' ? Constants.cnf.getPlayerX() : Constants.cnf.getPlayerO() + " переміг"));

            writer.write(Constants.st.getResult() + ", розмір: " + Constants.cnf.getBoardSize() + "x" + Constants.cnf.getBoardSize() + ", дата: " + Constants.cnf.getTime() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Помилка при збереженні статистики.");
        }
    }

    static void showStatistics() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Constants.st.getPath()));
            String line;
            System.out.println("\n--- ІСТОРІЯ ІГОР ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Статистика відсутня.");
        }
    }
}
