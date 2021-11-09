//Utils class for all kinds of handy methods

import java.util.List;
import java.util.Scanner;

public class Utils {

    public static String takeInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static int takeInteger(int lowerBound, int upperBound) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        try {
            int number = Integer.parseInt(input);
            while (!(number >= lowerBound && number <= upperBound)) {
                number = takeInteger(lowerBound, upperBound);
            }
            return number;
        }
        catch (Exception e) {
            System.out.println("Invalid input! Try again. ");
            return takeInteger(lowerBound, upperBound);
        }
    }

    public static boolean takeYes() {
        String continueStr = takeInput().trim();
        try {
            if (continueStr.equalsIgnoreCase("yes")) {
                return true;
            }
            else if (continueStr.equalsIgnoreCase("no")) {
                return false;
            }
            else {
                throw new RuntimeException();
            }
        }
        catch (RuntimeException e) {
            System.out.print("Invalid input! Try again: ");
            return takeYes();
        }
    }

    public static void heroUseItem(Hero hero, List<Monster> monsters) {
        hero.showInventory();
        System.out.println("What item do you want to use? Enter its name: ");
        String input = Utils.takeInput();
        while (!hero.hasItem(input)) {
            input = Utils.takeInput();
        }
        while (!hero.useItem(input, monsters)) {
            hero.showInventory();
            System.out.println("What item do you want to use? Enter its name: ");
            input = Utils.takeInput();
            while (!hero.hasItem(input)) {
                input = Utils.takeInput();
            }
        }
    }
}
