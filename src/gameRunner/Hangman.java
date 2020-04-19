/**
 * @author angelotodaro
 * Written on 4/18/2020
 */
package gameRunner;

import java.util.Scanner;


public class Hangman {

	public static void main(String[] args) 
	{
		//Variable Declarations
		WordManager manager = new WordManager();
		Scanner s = new Scanner(System.in);
		boolean currentlyPlaying = true;
		boolean inRounds = true;
		String wordToUse = "";
		String currentBoard = "";
		String currentGuessesTotal = "";
		String currentGuessesWrong = "";
		String calculatingTemp = "";
		char guess;
		char playAgainChoice;
		int wrongGuessCount = 0;
		
		
		//Begin Logic
		System.out.println("Welcome to Hangman!");
		while(currentlyPlaying)
		{
			//Get new word and set up blank board
			wordToUse = manager.chooseWord();
			currentBoard = wordToUse.replaceAll("[a-z]", "_");
			
			
			//Begin Rounds
			while(inRounds)
			{
				
				//Show current status and retrieve next guess
				System.out.println("Here is the current game board:");
				if(wrongGuessCount == 0)
				{
					System.out.println();
				}
				else
				{
					printHangImage(wrongGuessCount);
				}
				printSpacedString(currentBoard);
				System.out.println("Here is the current letters you have guessed:");
				printSpacedString(currentGuessesWrong);
				guess = getGuess(s,currentGuessesTotal);
				System.out.println("*************************************************************");
				//Check input character against current board and selected word to build new board in temporary variable			
				for(int i=0; i<wordToUse.length(); i++)
				{
					if(wordToUse.charAt(i) == guess)
						calculatingTemp += guess;
					else if (currentBoard.charAt(i) != '_')
						calculatingTemp += currentBoard.charAt(i);
					else
						calculatingTemp += '_';
				}
				
				//Validate the new board based on the temporary variable and end game if max wrong guesses is hit.
				if(currentBoard.equals(calculatingTemp))
				{
					wrongGuessCount++;
					currentGuessesTotal += guess;
					currentGuessesWrong += guess;
					System.out.println("Sorry that character is not in the word.");
					
					if(wrongGuessCount == 7)
					{
						printHangImage(wrongGuessCount);
						System.out.println("Sorry, you're all out of guesses. \nThe word was " + wordToUse + ".");
						inRounds = false;
					}
				}
				else
				{
					System.out.println("The character " + guess + " was in the word.");
					currentGuessesTotal += guess;
					currentBoard = calculatingTemp;
				}
				
				//Check if word is completed
				if(currentBoard.equals(wordToUse))
				{
					System.out.println("Congrats! You have successfuly guessed the word " + wordToUse + ".");
					inRounds = false;
				}
				
				//Clear out temporary variables
				calculatingTemp="";
			}
			
			//Post Game
			System.out.println("Would you like to play again? (y\\n)");
			playAgainChoice = s.next().charAt(0);
			while(playAgainChoice != 'y' && playAgainChoice != 'n')
			{
				System.out.println("Sorry that is an invalid choice.");
				System.out.println("Would you like to play again? (y\\n)");
				playAgainChoice = s.next().charAt(0);
			}
			if(playAgainChoice == 'y')
			{
				//Clear out necessary variables to run again
				inRounds = true;
				wrongGuessCount = 0;
				wordToUse = "";
				currentBoard = "";
				calculatingTemp = "";
				currentGuessesTotal = "";
				currentGuessesWrong = "";
			}
			else if(playAgainChoice == 'n')
			{
				currentlyPlaying = false;
				System.out.println("Goodbye.");
				s.close();
			}
				
				
		}
		

	}
	
	
	/**
	 * Retrieves the guess and validates that it is a alpha character that has not been guessed prior
	 * @param s The scanner to use in order to retrieve input.
	 * @param currentGuessesTotal A string containing the current guesses.
	 * @return Returns a char variable that contains a valid guess.
	 */
	public static char getGuess(Scanner s, String currentGuessesTotal)
	{
		char guess;
		while(true)
		{
			System.out.println("Please enter your guess:");
			guess = s.next().charAt(0);
			if(!Character.isLetter(guess))
			{
				System.out.println("This is not a valid guess.");
				continue;
			}
			if(currentGuessesTotal.indexOf(guess) != -1)
			{
				System.out.println("You've already guessed that character.");
				continue;
			}
			break;
		}
		return guess;
	}
	
	/**
	 * Prints out a string with spaces between each letter for better legibility in the console
	 * @param wordToPrint word that will be printed on the screen
	 */
	public static void printSpacedString(String wordToPrint)
	{
		for(int i=0; i<wordToPrint.length(); i++)
		{
			System.out.print(wordToPrint.charAt(i) + " ");
		}
		System.out.println();
	}
	

	/**
	 * Prints out an ASCII hangman based on the current number of incorrect guesses. 
	 * Copied from https://gist.githubusercontent.com/SedaKunda/79e1d9ddc798aec3a366919f0c14a078/raw/85308d40724ac8188beee6dcd329496fe083b876/hangman.java
	 * @param count
	 */
	public static void printHangImage(int count) {
		if (count == 1) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("___|___");
			System.out.println();
		}
		if (count == 2) {
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|___");
		}
		if (count == 3) {
			System.out.println("   ____________");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   | ");
			System.out.println("___|___");
		}
		if (count == 4) {
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("   |");
			System.out.println("___|___");
		}
		if (count == 5) {
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |           |");
			System.out.println("   |           |");
			System.out.println("   |");
			System.out.println("___|___");
		}
		if (count == 6) {
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |           |");
			System.out.println("   |           |");
			System.out.println("   |          / \\ ");
			System.out.println("___|___      /   \\");
		}
		if (count == 7) {
			System.out.println("   ____________");
			System.out.println("   |          _|_");
			System.out.println("   |         /   \\");
			System.out.println("   |        |     |");
			System.out.println("   |         \\_ _/");
			System.out.println("   |          _|_");
			System.out.println("   |         / | \\");
			System.out.println("   |          / \\ ");
			System.out.println("___|___      /   \\");
		}
	}

}
