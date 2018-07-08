/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import acm.util.ErrorException;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		add(new GLine(getWidth()/2-BEAM_LENGTH, TOP_OFFSET, getWidth()/2-BEAM_LENGTH, TOP_OFFSET+SCAFFOLD_HEIGHT));
		add(new GLine(getWidth()/2-BEAM_LENGTH, TOP_OFFSET, getWidth()/2, TOP_OFFSET));
		add(new GLine(getWidth()/2, TOP_OFFSET, getWidth()/2, TOP_OFFSET + ROPE_LENGTH));
		add(maskedWord, getWidth()/2-BEAM_LENGTH, TOP_OFFSET*2+SCAFFOLD_HEIGHT);
		add(incorrectGuesses, getWidth()/2-BEAM_LENGTH, TOP_OFFSET*3+SCAFFOLD_HEIGHT);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		maskedWord.setLabel(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter, int nGuessesRemaining) {
		String tempIncGuess = incorrectGuesses.getLabel();
		incorrectGuesses.setLabel(tempIncGuess + letter);
		
		switch (nGuessesRemaining) {
		case 7: add(new GOval(getWidth()/2-HEAD_RADIUS, TOP_OFFSET + ROPE_LENGTH, HEAD_RADIUS*2, HEAD_RADIUS*2)); break;
		case 6: add(new GLine(getWidth()/2, TOP_OFFSET + ROPE_LENGTH + HEAD_RADIUS*2, getWidth()/2, TOP_OFFSET + ROPE_LENGTH + HEAD_RADIUS*2 + BODY_LENGTH)); break;
		case 5: add(new GLine(getWidth()/2, TOP_OFFSET + ROPE_LENGTH + HEAD_RADIUS*2 + ARM_OFFSET_FROM_HEAD, getWidth()/2 - UPPER_ARM_LENGTH, TOP_OFFSET + ROPE_LENGTH + HEAD_RADIUS*2 + ARM_OFFSET_FROM_HEAD));
				add(new GLine(getWidth()/2 - UPPER_ARM_LENGTH, TOP_OFFSET + ROPE_LENGTH + HEAD_RADIUS*2 + ARM_OFFSET_FROM_HEAD, getWidth()/2 - UPPER_ARM_LENGTH, TOP_OFFSET + ROPE_LENGTH + HEAD_RADIUS*2 + ARM_OFFSET_FROM_HEAD - LOWER_ARM_LENGTH)); break;
		case 4: add(new GLine(0,0,10,0)); break;
		case 3: add(new GLine(0,0,20,0)); break;
		case 2: add(new GLine(0,0,30,0)); break;
		case 1: add(new GLine(0,0,40,0)); break;
		default: throw new ErrorException("getWord: too many guesses");
	}
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int TOP_OFFSET = 28;
	private GLabel maskedWord = new GLabel("");
	private GLabel incorrectGuesses = new GLabel("");

}
