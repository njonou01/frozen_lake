package edu.upec.episen.ing2.logique;

import java.util.Scanner;

import edu.upec.episen.ing2.logique.service.FrozenLake;
import edu.upec.episen.ing2.logique.service.strategy.ManhattanEnhancedStrategy;
import edu.upec.episen.ing2.logique.service.strategy.ManhattanStrategy;
import edu.upec.episen.ing2.logique.service.strategy.RandomEnhancedStrategy;
import edu.upec.episen.ing2.logique.service.strategy.RandomStategy;
import edu.upec.episen.ing2.logique.service.strategy.Strategy;
import edu.upec.episen.utils.Utils;

public class App {
    private static void simulateStrategy(Strategy strategy, String strategyName, int numberOfGames) throws Exception {
        Integer winCount = 0;
        for (int i = 0; i < numberOfGames; i++) {
            FrozenLake lake = new FrozenLake(false, strategy);
            if (lake.playFullGame()) {
                ++winCount;
            }
        }
        double winRate = (double) winCount / numberOfGames * 100;
        System.out.println("==========================");
        System.out.println(" Statistiques - " + strategyName);
        System.out.println("==========================");
        System.out.println("Nombre total de parties : " + numberOfGames);
        System.out.println("Nombre de victoires : " + winCount);
        System.out.println("Taux de victoires : " + String.format("%.2f", winRate) + "%");
        System.out.println("==========================");
        System.out.println();
    }

    private static void playOneGame(Strategy strategy, String strategyName) throws Exception {
        System.out.println("==========================");
        System.out.println(" Jouer une partie - " + strategyName);
        System.out.println("==========================");

        FrozenLake lake = new FrozenLake(true, strategy);
        boolean win = lake.playFullGame();

        System.out.println("Résultat : " + (win ? "Victoire!" : "Défaite!"));
        System.out.println("==========================");
        System.out.println();
    }

    private static void simulateAllStrategies(int numberOfGames) throws Exception {
        simulateStrategy(new RandomStategy(), "Stratégie Aléatoire", numberOfGames);
        simulateStrategy(new RandomEnhancedStrategy(), "Stratégie Aléatoire Améliorée", numberOfGames);
        simulateStrategy(new ManhattanStrategy(), "Stratégie Manhattan", numberOfGames);
        simulateStrategy(new ManhattanEnhancedStrategy(), "Stratégie Manhattan Améliorée", numberOfGames);
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 3) {
            Utils.clearScreen();
            System.out.println("==========================");
            System.out.println(" Menu FrozenLake");
            System.out.println("==========================");
            System.out.println("1. Jouer une partie");
            System.out.println("2. Simuler plusieurs parties");
            System.out.println("3. Quitter");
            System.out.println("==========================");
            System.out.print("Votre choix: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                Utils.clearScreen();

                switch (choice) {
                    case 1:
                        System.out.println("\nChoisir une stratégie:");
                        System.out.println("1. Stratégie Aléatoire");
                        System.out.println("2. Stratégie Aléatoire Améliorée");
                        System.out.println("3. Stratégie Manhattan");
                        System.out.println("4. Stratégie Manhattan Améliorée");
                        System.out.print("Votre choix: ");

                        int strategyChoice = Integer.parseInt(scanner.nextLine());
                        Utils.clearScreen();
                        Strategy selectedStrategy;
                        String strategyName;

                        switch (strategyChoice) {
                            case 1:
                                selectedStrategy = new RandomStategy();
                                strategyName = "Stratégie Aléatoire";
                                break;
                            case 2:
                                selectedStrategy = new RandomEnhancedStrategy();
                                strategyName = "Stratégie Aléatoire Améliorée";
                                break;
                            case 3:
                                selectedStrategy = new ManhattanStrategy();
                                strategyName = "Stratégie Manhattan";
                                break;
                            case 4:
                                selectedStrategy = new ManhattanEnhancedStrategy();
                                strategyName = "Stratégie Manhattan Améliorée";
                                break;
                            default:
                                System.out.println("Choix invalide. Stratégie Aléatoire sélectionnée par défaut.");
                                selectedStrategy = new RandomStategy();
                                strategyName = "Stratégie Aléatoire";
                        }

                        playOneGame(selectedStrategy, strategyName);
                        break;

                    case 2:
                        System.out.println("Entrez le nombre de parties à simuler :");
                        int numberOfGames = 0;
                        try {
                            numberOfGames = Integer.parseInt(scanner.nextLine());
                            if (numberOfGames <= 0) {
                                System.out.println(
                                        "Le nombre de parties doit être positif. Utilisation de la valeur par défaut: 100");
                                numberOfGames = 100;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Entrée invalide. Utilisation de la valeur par défaut: 100");
                            numberOfGames = 100;
                        }
                        Utils.clearScreen();
                        simulateAllStrategies(numberOfGames);
                        break;

                    case 3:
                        System.out.println("Au revoir!");
                        break;

                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            }

            if (choice != 3) {
                System.out.println("Appuyez sur Entrée pour continuer...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}