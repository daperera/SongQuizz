package game;
import deezerAdapter.Track;

public class Main {

	private static SongQuizz quizz;
	private static User user;
	private static Stats stats;

	public static void main(String[] args) {
		// game
		game();
	}
 	

	/* ******************** *
	 * ****** SCRIPT ****** *
	 * ******************** */

	private static void game() {
		// declare global variable
		quizz = new SongQuizz();
		stats = new Stats();
		boolean done = false;

		// Introduction message
		intro();

		// program main loop
		while(!done) {
			done = play();
		}
		
		// exit program
		leave();
	}
	
	
	/* *************************** *
	 * ****** STATE MACHINE ****** *
	 * *************************** */

	private static void intro() {
		println("Song quizz program");
		println("type q! to display stats");
		println("type n! to advance to next song");
	}




	private static boolean play() {
		// variables
		Track track;
		String guess;
		boolean correctGuess;
		boolean done = false;
		
		// play
		track = quizz.playNext(); // play next song
		guess = input("guess"); // ask user for a guess
		while(!done) {
			correctGuess = checkTrue(guess, track); // check guess correctness
			if(guess.equals("q!")) { // if user ask to leave
				return true;
			}
			else if(guess.equals("n!")) {
				done = true;
			}
			else if(correctGuess) { // if correct guess
				println("correct"); // correct guess message
				stats.correctGuess(user, track); // register correct guess
				done = true; // go to next track
			}
			else { // if incorrect guess
				println("incorrect"); // incorrect guess  message
				stats.wrongGuess(user, track); // register incorrect guess
			}
		}
		
		// print correct answer
		println("song's information");
		track.describe();
		
		return false;
	}


/*
	private static void register() {
		println("Please enter a username (leave blank "
				+ "to play anonymously).");
		String username = input("username");
		user = new User(username);
	}

	private static int menu() {
		String choice;
		int userChoice = -1;

		// menu selection loop
		while(userChoice < 0) {

			// print choices 
			blank();
			println("Please enter a choice");
			println("1 : new game");
			println("2 : stats");
			println("0 : leave");

			// get user input
			choice = input("choice");

			// check input valididy
			try {
				userChoice = Integer.parseInt(choice);
				if(userChoice < 0 || userChoice > 2)
					throw new NumberFormatException();
			} catch(NumberFormatException e) {
				println("Please enter a valid choice");
			}

		}

		return userChoice;
	}

	private static void stats() {
		blank(2);
		stats.printStats();
		blank(2);
		stats.printStats(user);
	}
*/
	
	private static void leave() {
		println("exiting");
	}

	/* ************************** *
	 * **** UTILITY FUNCTIONS *** *
	 * ************************** */

	private static boolean checkTrue(String guess, Track track) {
		boolean correctGuess = false;
		correctGuess |= track.getAlbum().getTitle().contains(guess);
		correctGuess |= track.getArtist().getName().contains(guess);
		correctGuess |= track.getTitle().contains(guess);
		return correctGuess;
	}	
	
	public static void blank() {
		System.out.println("");
	}

	public static void blank(int n) {
		for(int k=0; k<n; k++)
			System.out.println("");
	}

	private static String input(String inputName) {
		print(inputName + " : ");
		return input();
	}

	private static String input() {
		return System.console().readLine();
	}

	private static void print(String s) {
		System.out.print(s);
	}

	private static void println(String s) {
		System.out.println(s);
	}

}
