import java.util.Scanner;

public class SafeInput {

    // Method to get a ranged integer from the user
    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int value = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            if (console.hasNextInt()) {
                value = console.nextInt();
                if (value >= low && value <= high) {
                    valid = true;
                } else {
                    System.out.println("Input out of range. Please enter a value between " + low + " and " + high + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                console.next(); // Clear invalid input
            }
        }
        return value;
    }

    // Method to get a Y/N confirmation from the user
    public static boolean getYNConfirm(Scanner console, String prompt) {
        String input;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            input = console.next();
            if (input.equalsIgnoreCase("Y")) {
                valid = true;
                return true;
            } else if (input.equalsIgnoreCase("N")) {
                valid = true;
                return false;
            } else {
                System.out.println("Please enter Y or N.");
            }
        }
        return false;
    }
}
