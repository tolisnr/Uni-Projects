import java.util.Scanner;

public class Game {
    
    private int guesses, correct, wrong;
    private int index;
    private String guessedWord, word;

    public Game(int index) {
        this.index = index;
        this.guessedWord = "";
        this.guesses = 8; // The player has only 8 guesses.
        this.correct = this.wrong = 0;
    }

    private String getMaskedWord() {
        // TODO implement here
        String maskedWord = "";
        word = Dictionary.getWord(index);

       for(int i = 0; i < word.length(); i++) {
            maskedWord += "-";
        }
        // We mask the word with dashes.

        return maskedWord;
    }

    private String getUnmaskedWord(String guessedLetters) {
        // TODO implement here
        char[] guessedWordArray = guessedWord.toCharArray();
        // We convert the masked word to char array so that we can change the letters.

        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == guessedLetters.charAt(0)) { /*  We check if the guessed letter is 
                                                                       in the word and in which position
                                                                       with the help of counter "i". */
                guessedWordArray[i] = guessedLetters.charAt(0);
            }
        }

        // We convert the char array to string.
        return new String(guessedWordArray);
    }


    private boolean isFound(String guessedLetters) {
        // TODO implement here
        String guessedUpLetters = guessedLetters.toUpperCase(); // We convert the guessed letters to upper case.

        if(word.contains(guessedUpLetters)) {
            guessedWord = getUnmaskedWord(guessedUpLetters); // We unmask the word with the guessed letters.
            return true;
        }
        
        return false;
    }

    public int PlayGame() {
        // TODO implement here
        Scanner in = new Scanner(System.in);
        boolean found = false;
        String guess = "";
        int result = -1;

        guessedWord = getMaskedWord();    // This method takes a random word from the dictionary and masks it.

        while(guesses > 0 && !found) {         // We check if the player has guesses left and if the word is not found.     
            System.out.println("\nThe random word is now: " + guessedWord);
            System.out.println("You have " + guesses + " guesses left.");
            System.out.print("Your guess: ");
            guess = in.nextLine();

            // Check if the guess is a letter
            if (!guess.matches("[a-zA-Z]")) {
                System.out.println("Invalid input. Please enter a letter.");
                continue; // We skip the rest of the loop and start from the beginning.
            }

            if(isFound(guess)) {
                System.out.println("The guess is CORRECT!");
                correct++;
            } 
            else {
                System.out.println("There are no " + guess.toUpperCase() + "'s in the word.");
                guesses--;
                wrong++;
            }

            if(guesses == 0) {
                System.out.println("\nYou lost! The word was: " + word + "\n");
                result = 0;
            } 
            else if(word.equals(guessedWord)) {
                found = true;
                result = 1;
                System.out.println("\nCongratulations! You guessed the word: " + word + "\n");
            }
        }
        
        System.out.println("You made " + correct + " correct guesses and " + wrong + " wrong guesses.\n");

        return result;
    }
}
