package gameRunner;
/*
 * wordManager()
 * Description - This class handles the process of retrieving a 
 * 		word from the list and passing it back to the main class to play the game.
 */

//imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class WordManager {
	
	private ArrayList<String> wordList = new ArrayList<>();
	
	public WordManager()
	{
		loadWords();
	}
	
	
	public void loadWords()
	{
		try {
		      File myObj = new File("words.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        wordList.add(myReader.nextLine());
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public String chooseWord()
	{
		int listLength = wordList.size();
		String chosenWord;
		int randNum = (int)(Math.random() * listLength);
		chosenWord = wordList.get(randNum);
		return chosenWord;
	}
}
