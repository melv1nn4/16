package com.tictac;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.tictac.Constants;

public class Conf {
     public static void loadConfig() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("config.txt"));
            Constants.cnf.setPlayerX(reader.readLine());
            Constants.cnf.setPlayerO(reader.readLine());
            Constants.cnf.setBoardSize(Integer.parseInt(reader.readLine()));
            reader.close();
        } catch (Exception e) {
        }
    }
    public static void saveConfig() {
        try {
            FileWriter writer = new FileWriter("config.txt");
            writer.write(Constants.cnf.getPlayerX() + "\n");
            writer.write(Constants.cnf.getPlayerO() + "\n");
            writer.write(Constants.cnf.getBoardSize() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Помилка при збереженні конфігурації.");
        }
    }
}
