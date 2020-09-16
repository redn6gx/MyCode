package fxApp;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import hw2.Hangman;

public class App extends Application {

	private final ImageView selectedImage;
	private Text output;
	private TextField input;
	private Hangman h;
	private int gifNo;
	
	private int gameState;
	
	private static final int START_STATE = 0;
	private static final int PLAY_STATE = 1;
	private static final int END_STATE = 2;

	public App() {
		selectedImage = new ImageView();
		input = new TextField();
		output = new Text();
		gameState = START_STATE;
		gifNo = 1;
	}

	@Override
	public void start(Stage primaryStage) {
		showGif();
		String str = "Welcome to Hangman\nEnter a word.";
		output.setText(str);

		// handler when user types input and presses ENTER
		input.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent ae) {
				String s = input.getText();
				s = s.trim().toUpperCase();
				input.setText(""); // erase input text
				
				if (gameState == START_STATE) {
					// start new game.  user has entered the word.
					
					h = new Hangman(s, 6);
					gameState = PLAY_STATE;
					String str = String.format(
							"You have %d incorrect guesses remaining.\n" + "The word so far is \n %s \nEnter your guess.",
							h.getIncorrectGuessesRemaining(), h.getDisplay());
					output.setText(str);

				} else if (gameState == END_STATE) {
					// game is over.
					output.setText("The game is over.");

					
				} else {
					// play game. user has entered guess or wants a hint.
					playGame(s);
				}
			}
		});

		VBox vbox = new VBox(30);
		vbox.setPadding(new Insets(25, 25, 25, 25));
		vbox.getChildren().addAll(selectedImage, output, input);
		Scene scene = new Scene(vbox, 300, 450); // window size: w x h

		primaryStage.setTitle("Hangman");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void showGif() {
		FileInputStream inputstream = null;
		try {
			inputstream = new FileInputStream("h" + gifNo + ".gif");
		} catch (Exception e) {
			System.out.println("error. file not found " + gifNo);
			return;
		}
		Image image = new Image(inputstream);
		selectedImage.setImage(image);
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	public void playGame(String s) {
		String str = "";
		int result = 0;
//		str = ("You have " + h.getIncorrectGuessesRemaining() + 
//			   " incorrect guesses remaining.\nThe word so is now\n"
//			   + h.getDisplay());
		if (s.contentEquals("HINT")) {
			// user requested hint
			
			// TO DO complete code to give user a hint
			// assign to variable str a  message 
			str = ("OK. The hint is " + h.hint() + "\nBut since you used hint\nyou can have 5 incorrect guesses remaining.\nThe word so far is\n" + h.getDisplay());
		} else if (s.length() != 1 || (s.charAt(0) < 'A' || s.charAt(0) > 'Z') )  {
			// invalid input.  more than 1 letter, or input is not a letter A-Z
			
			// TO DO complete code to assign message to variable str
			//  when user had entered invalid input
			str = "Incorrect input. Must be single letter A-Z\nYou have 5 incorrect guesses remaining.\nThe word is now\n" + h.getDisplay();
		} else {
			// user entered a guess
			
			// TODO  use Hangman guess method to determine if guess is 
			// correct or incorrect.
			result = h.guess(s.charAt(0));
			if(result == -1) {	//invalid guess
				str = "You already guessed the letter " 
				+ s.charAt(0) + "\nYou have " + h.getIncorrectGuessesRemaining() 
				+ " incorrect guesses remaining\nThe word is\n" + h.getDisplay();
			}
			else if (result == -2){ //bad guess
				str = "Sorry" + s.charAt(0) + " is not in the word.\nYou have " 
				+ h.getIncorrectGuessesRemaining() + " guesses remaining.\nThe word is now\n" 
				+ h.getDisplay();
			}
			else {	//good guess
				str = "That's right. " + s.charAt(0) + 
					  " is in the word.\nYou have " + h.getIncorrectGuessesRemaining() 
					  + " incorrect guesses remaining.\nThe word is now\n" + h.getDisplay();
			}
		}
		// update GIF, and output message,  Check if game is won or lost.
		
		// TODO remove comments in the following code 
		
		
		int nextGifNo = 7 - h.getIncorrectGuessesRemaining();
		if (nextGifNo != gifNo) {
			gifNo = nextGifNo;
			showGif();
		}
		if (h.won()) {
			gameState = END_STATE;
			str = "Congratulations! " + h.getDisplay();

		} else if (h.getIncorrectGuessesRemaining() <= 0) {
			gameState = END_STATE;
			str ="You failed. The word was " + h.getFullDisplay();
		}		
		
		output.setText(str);

	}

}
