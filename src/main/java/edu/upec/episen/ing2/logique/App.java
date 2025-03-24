package edu.upec.episen.ing2.logique;

import edu.upec.episen.ing2.logique.service.FrozenLake;
import edu.upec.episen.ing2.logique.service.strategy.ManhattanStrategy;

public class App {
    private final static Integer TOTAL_GAMES = 2000;

    private static void simulateManyParty() throws Exception {
        Integer winCount = 0;

        for (int i = 0; i < TOTAL_GAMES; i++) {
            FrozenLake lake = new FrozenLake(false, new ManhattanStrategy());
            if (lake.playFullGame())
                ++winCount;
        }

        System.out.println("==========================");
        System.out.println(" Statistiques des Parties ");
        System.out.println("==========================");
        System.out.println("Nombre total de parties : " + TOTAL_GAMES);
        System.out.println("Nombre de victoires : " + winCount);
        System.out.println("==========================");

    }

    private static void test() throws Exception {
        FrozenLake lake = new FrozenLake(true, new ManhattanStrategy());
        lake.move();
        lake.move();
        lake.move();
        lake.move();
        lake.move();
        lake.move();
        lake.move();
        lake.move();

    }

    public static void main(String[] args) throws Exception {
        test();
    }
}
