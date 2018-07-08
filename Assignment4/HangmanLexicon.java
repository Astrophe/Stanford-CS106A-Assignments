/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.*;
import acm.util.ErrorException;

public class HangmanLexicon {
	
	public HangmanLexicon() {
		try {
		BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
		while (true) {
			String line = rd.readLine();
			//add to array list
			if (line==null) break;
			lexicon.add(line);
		}
		rd.close();
		} catch (FileNotFoundException ex) {
			System.out.println("file not found: HangmanLexicon.txt");
		}
		catch (IOException ex) {
			System.out.println("IOException");
		}
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lexicon.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return lexicon.get(index).toString();
		}
	private ArrayList lexicon = new ArrayList<String>();
}
