package hw2;

/**
 * Title: File Name
 * Abstract: Overall purpose (or functionality) of the program.
 * Author: Your name
 * Date: The date you wrote the program
 */
import java.util.Scanner;

public class HgMain {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);

		System.out.println("--------- Welcome to Hangman ---------");
		System.out.println();
		System.out.print("Enter a word: ");
		String word = keyboard.nextLine();

		Hangman game = new Hangman(word, 4);

		while (true) {
			System.out.print("So far, the word is: ");
			System.out.println(game.getDisplay());
			System.out.printf("You have %d incorrect guesses left.\n", game.getIncorrectGuessesRemaining());

			// TODO prompt user to enter 1 or 2
			// see helper method below

			// if 1: prompt user to enter their guess
			// call Hangman guess method
			// if 2: call Hangman hint method

			String userSelection = getSelection(keyboard);
			String userGuess = "";
			int guessResult = 0;

			if (userSelection.contentEquals("1")) {
				userGuess = getGuess(keyboard);
				guessResult = game.guess(userGuess.charAt(0));
				
				if(guessResult == -1) {	//invalid guess
					System.out.println("Not valid input. You already guessed " + userGuess);
				}else if(guessResult == -2) {	//bad guess
					System.out.println("Sorry, " + userGuess + " isn't in the word.");
				}else {	//good guess
					System.out.println("That's right! " + userGuess + " is in the word.");
				}
			} else {
				System.out.println("OK! The hint is " + game.hint() + "\nBut since you used a hint, you can guess "
						+ game.getIncorrectGuessesRemaining() + " more times.");
			}
			
			System.out.println();
			if (game.won()) {
				System.out.print("Congratulations! The word was ");
				System.out.println(game.getDisplay());
				break;
			}
			if (game.getIncorrectGuessesRemaining() == 0) {
				System.out.print("You failed. The word was ");
				System.out.println(game.getFullDisplay());
				break;
			}
		}
	}

	// helper methods for get Selection: 1 or 2

	public static String getSelection(Scanner keyboard) {
		while (true) {
			System.out.print("Enter either 1 for guessing or 2 for hint: ");
			String input = keyboard.nextLine();
			input = input.trim().toUpperCase();
			if (input.equals("1") || input.equals("2")) {
				return input;
			} else {
				System.out.println("Incorrect input.");
			}
		}
	}

	// getGuess helper method. guess must be a single character A-Z

	public static String getGuess(Scanner keyboard) {
		while (true) {
			System.out.print("Enter your guess: ");
			String input = keyboard.nextLine().trim().toUpperCase();
			if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
				return input;
			}
			System.out.println("Incorrect input.");

		}
	}
}
