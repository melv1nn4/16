package com.tictac;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean codeIsRunning = true;

        Conf.loadConfig();

        while (codeIsRunning) {
            System.out.println("\n-- ГОЛОВНЕ МЕНЮ --");
            System.out.println("1 - Почати гру");
            System.out.println("2 - Налаштування");
            System.out.println("3 - Переглянути статистику");
            System.out.println("4 - Вихід");
            System.out.print("Ваш вибір: ");
            char inputSwitch = scanner.nextLine().charAt(0);

            if (inputSwitch == '1') {
                playGame(scanner);
            } else if (inputSwitch == '2') {
                settingsMenu(scanner);
            } else if (inputSwitch == '3') {
                Statistics.showStatistics();
            } else if (inputSwitch == '4') {
                codeIsRunning = false;
            } else {
                System.out.println("Невірний вибір, спробуйте ще.");
            }
        }

        System.out.println("Гру завершено.");
        scanner.close();
    }

    static void playGame(Scanner scanner) {
        char currentPlayer = 'X';
        boolean gameEnd = false;
        Constants.f.initializeBoard();


        while (!gameEnd) {
            Constants.f.printBoard();

            System.out.print("Гравець " + (currentPlayer == 'X' ? Constants.cnf.getPlayerX() : Constants.cnf.getPlayerO()) + ", введіть рядок і колонку (від 1 до " + Constants.cnf.getBoardSize() + "): ");
            String input = scanner.nextLine();
            if (input.length() == 1 && input.charAt(0) == '2') return;

            int row = -1, col = -1;
            boolean valid = false;

            while (!valid) {
                Scanner inputScanner = new Scanner(input);
                if (inputScanner.hasNextInt()) {
                    row = inputScanner.nextInt() - 1;
                    if (inputScanner.hasNextInt()) {
                        col = inputScanner.nextInt() - 1;
                        if (row >= 0 && row < Constants.cnf.getBoardSize() && col >= 0 && col < Constants.cnf.getBoardSize()) {
                            row *= 2;
                            col *= 2;
                            if (Constants.f.getBoard()[row][col] == ' ') {
                                valid = true;
                            } else {
                                System.out.println("Клітинка зайнята. Введіть інші координати:");
                            }
                        } else {
                            System.out.println("Невірні координати. Спробуйте знову:");
                        }
                    } else {
                        System.out.println("Потрібно ввести два числа:");
                    }
                } else {
                    System.out.println("Потрібно ввести два числа:");
                }
                if (!valid) input = scanner.nextLine();
            }

            Constants.f.getBoard()[row][col] = currentPlayer;

            if (Constants.f.checkWin(currentPlayer)) {
                Constants.f.printBoard();
                System.out.println("Гравець " + (currentPlayer == 'X' ? Constants.cnf.getPlayerX() : Constants.cnf.getPlayerO()) + " переміг!");
                Statistics.saveStatistics(currentPlayer);
                gameEnd = true;
            } else if (Constants.f.isBoardFull()) {
                Constants.f.printBoard();
                System.out.println("Нічия!");
                Statistics.saveStatistics('D');
                gameEnd = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }


    static void settingsMenu(Scanner scanner) {
        System.out.print("Ім'я гравця X: ");
        Constants.cnf.setPlayerX(scanner.nextLine());
        System.out.print("Ім'я гравця O: ");
        Constants.cnf.setPlayerO(scanner.nextLine());
        System.out.println("Розмір поля: 3(1), 5(2), 7(3), 9(4): ");
        char input = scanner.nextLine().charAt(0);
        if (input == '1') Constants.cnf.setBoardSize(3);
        else if (input == '2') Constants.cnf.setBoardSize(5);
        else if (input == '3') Constants.cnf.setBoardSize(7);
        else if (input == '4') Constants.cnf.setBoardSize(9);

        Conf.saveConfig();
    }

}