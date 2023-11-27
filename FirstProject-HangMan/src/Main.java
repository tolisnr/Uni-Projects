import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        String choise = "";
        boolean exit = false;
        int games = 0, won = 0, lost = 0;

        do {
            choise = Menu.mainMenu();

            if(choise.equalsIgnoreCase("N")) {

                int RandomNum = random.nextInt(0, Dictionary.getWordCount() - 1);
                /* A random number is generated between 0 (inclusive) 
                   and the number of words in the dictionary (exclusive). */

                Game newGame = new Game(RandomNum); /* RandomNum is the random generated
                                                       index of the word in the dictionary. */

                // Initialize the game.
                int result = newGame.PlayGame(); // Returns 1 if the player won, 0 if lost.

                // Counting the games, won and lost.           
                games++;
                if(result == 1) won++;
                else lost++;
            }
            else if(choise.equalsIgnoreCase("S")) {
                Statistics.showStatistics(games, won, lost);
            }
            else if(choise.equalsIgnoreCase("E")) {
                exit = true;
            } 
            else {
                System.out.println("Invalid choise!\n");
            }
            
        } while(!exit);
    }
}
