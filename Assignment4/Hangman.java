/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

    public void run() {
    	canvas.reset();
    	//Get word
    	String word = hLex.getWord(rgen.nextInt(0, hLex.getWordCount()-1));
    	int wordLength = word.length();
    	String maskedWord = "";
    	//Build out masked word with slashes
    	for (int i = 0; i < wordLength; i++) {
    		maskedWord = maskedWord + "-";
    	}
    	canvas.displayWord(maskedWord);
    	//Introduction
    	println("Welcome to Hangman!");
    	boolean gameOver = false;
    	//Play game
    	while (nGuessesRemaining > 0 && gameOver == false) {
    		println("The word looks like this: " + maskedWord);
    		println("You have " + nGuessesRemaining + " chances to figure out the word");
    		String userGuess = readLine("Your guess: ");
    		//Convert string to char
    		char userGuessChar = userGuess.charAt(0);
    		//Convert to Upper Case
    		userGuessChar = Character.toUpperCase(userGuessChar);
    		int j = -1;
    		//if correct guess
    		if (word.indexOf(userGuessChar, j) != -1) {
    			while (word.indexOf(userGuessChar, j+1) != -1) {
    				j = word.indexOf(userGuessChar, j+1);
    				maskedWord = maskedWord.substring(0,j) + userGuessChar + maskedWord.substring(j+1, maskedWord.length());
    			}
    			println("That guess is correct!!");
    			canvas.displayWord(maskedWord);
    		}
    		//if incorrect guess, but game not over
    		else {
    			--nGuessesRemaining;
    			canvas.noteIncorrectGuess(userGuessChar, nGuessesRemaining);
    			println("There are no " + userGuessChar + "'s in the word");
    			if (nGuessesRemaining == 0) {
    				println("You're completely hung.");
    				println("The word was: " + word);
    				println("You lose!!!");
    				gameOver = true;
    			}
    		}
    		if (maskedWord.indexOf("-") == -1) {
    			println("You guessed the word: " + word);
    			println("You win!!!");
    			gameOver = true;
    		}
    		else if (gameOver = false) {
    			println("The word now looks like this: " + maskedWord);
    			println("You have " + nGuessesRemaining + " guesses left");
    			userGuess = readLine("Your guess: ");
    			//Convert string to char
    			userGuessChar = userGuess.charAt(0);
    			//Convert to Upper Case
    			userGuessChar = Character.toUpperCase(userGuessChar);
    			j = -1;
    		}
    	}
	}
    
    public void init() {
    	canvas = new HangmanCanvas();
    	add(canvas);
    }
    
    private HangmanLexicon hLex = new HangmanLexicon();
    private RandomGenerator rgen = new RandomGenerator();
    private int nGuessesRemaining = 8;
    private HangmanCanvas canvas;
}
