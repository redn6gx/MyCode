package hw2;


/**
 * Title: File Name
 * Abstract: Overall purpose (or functionality) of the program.
 * Author: Your name
 * Date: The date you wrote the program
 */
public class Hangman {
    
    public static final int INVALID_GUESS = -1;
    public static final int BAD_GUESS = -2;
    public static final int GOOD_GUESS = 1;

    String word;
    int incorrect;   // incorrect guesses remaining
    char[] display;  // letters to show user
    String guesses;  // history of all correct and incorrect guesses

    public Hangman(String w, int incorrect) {
        this.word = w.trim().toUpperCase();
        this.incorrect = incorrect;
        display = new char[this.word.length()];
        // word should only contain characters A-Z and spaces
        // no digits of special characters allowed
        // space is displayed as # 
        // unguessed characters are displayed as _ underscore
        for (int i = 0; i < display.length; i++) {
            if (this.word.charAt(i) == ' ') {
                display[i] = '#';
            } else {
                display[i] = '_';
            }
        }
        this.guesses = "";
    }

    /*
     * return String will all characters in word showing, 
     *    space replaced with #, and blank separated.
    */
    public String getFullDisplay() {
        String d = word.substring(0,1);
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == ' ') {
                d = d+ " #";
            } else {
                d = d + " " + word.charAt(i);
            }
        }
        return d;
    }

    /*
     * return String containing correctedly guessed letters
     *   _ (underscore) for unguessed letters
     *  # for blanks
     *  blank separated
     */
    public String getDisplay() {
        String d = Character.toString(display[0]);
        for (int i = 1; i < display.length; i++) {
            d = d + " " + display[i];
        }
        return d;
    }

    /*
     * is the game won?  
     */
    public boolean won() {
        for (char c : display) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }
    
  
    /*
     *   INVALID_GUESS.  user already guessed that letter
     *   BAD_GUESS
     *   GOOD_GUESS
     */
    public int guess(char g) {
        g = Character.toUpperCase(g);
        if (guesses.indexOf(g) >= 0) {
            //  already guessed that letter
            if (word.indexOf(g) >= 0) {
                // letter is in word
                // so no penalty
                return INVALID_GUESS;
            } else {
                // letter not in word. 
                incorrect = incorrect - 1;
                return INVALID_GUESS;
            }
        }
        guesses = guesses + g;
        if (word.indexOf(g) < 0) {
            // not in word
            incorrect = incorrect - 1;
            return BAD_GUESS;
        }
        // good guess
        for (int i = 0; i < display.length; i++) {
            if (word.charAt(i) == g) {
                display[i] = g;
            }
        }
        return GOOD_GUESS;
    }

    /* 
     * return number of incorrect guesses remaining
    */
    public int getIncorrectGuessesRemaining() {
        return incorrect;
    }

    /* 
     * return hint.  first unguessed letter in word.
     *   decrement number of incorrect guesses
     *   call method guess( ) to process the hint.
    */
    public char hint() {
        for (int i = 0; i < display.length; i++) {
            if (display[i] == '_') {
                char g = word.charAt(i);
                incorrect = incorrect - 1;
                guess(g);
                return g;
            }
        }
        return '#';  // should never occur.
    }

}
