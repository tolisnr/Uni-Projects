import java.util.Scanner;

public class Menu {
    private static Scanner in = new Scanner(System.in);

    public static String mainMenu() {
        System.out.println("Welcome to the game of Hangman!\n");

        System.out.println("MAIN MENU");
        System.out.println("  - Start a new Game (N)");
        System.out.println("  - Statistics (S)");
        System.out.println("  - Exit (E)");
        System.out.print("Please enter your choice: ");
    
        String choice = in.nextLine();

        return choice;
    }
}