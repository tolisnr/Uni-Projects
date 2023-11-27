public class Dictionary {

    private static final String[] WORDS = {
        "UNIVERSITY", "COMPUTER", "LAPTOP", "HEADPHONES", "FUZZY",
        "DOG", "KEYHOLE", "TELEPHONE", "PRINTER", "BUILDING",
        "ELEPHANT", "GUITAR", "BANANA", "WATERMELON", "PIZZA",
        "SUNFLOWER", "OCEAN", "MOUNTAIN", "BUTTERFLY", "DRAGONFLY",
        "CROCODILE", "KANGAROO", "PARROT", "TIGER", "ZEBRA",
        "EAGLE", "PENGUIN", "RHINOCEROS", "SCORPION", "SNAKE" };
                
    public static String getWord(int index) {
        if (index > 0 && index < WORDS.length) return WORDS[index];
        return "Illegal index";
    }

    public static int getWordCount() {
        return WORDS.length;
    }
}