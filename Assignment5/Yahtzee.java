/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.IODialog;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import java.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		scorecard = new int[nPlayers][17];
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		//Cycle through turns
		for (int i = 0; i < 13; i++) {
			for (int j = 1; j <= nPlayers; j++){
				display.printMessage(playerNames[j-1] + "'s turn! Click \"Roll Dice\" button to roll the dice");
				display.waitForPlayerToClickRoll(j);
				diValues = rollDice(true);
				display.displayDice(diValues);
				for (int k = 0; k < 2; k++){
					display.printMessage(playerNames[j-1] + ", select the dice you wish to re-roll and click \"Roll again\"");
					display.waitForPlayerToSelectDice();
					diValues = rollDice(false);
					display.displayDice(diValues);
				}
				display.printMessage("Your turn's over. Select a category please :)");
				selectedCategory = display.waitForPlayerToSelectCategory();
				int score = getScore(selectedCategory);
				display.updateScorecard(selectedCategory, j, score);
				scorecard[j-1][selectedCategory] = score;
			}
		}
		int winner = calculateTotals();
		display.printMessage("Contrats, " + playerNames[winner] + "! You won!!");
	}
	
	private int[] rollDice(boolean isFirstTurn) {
		for (int i = 0; i < 5; i++) {
			if (isFirstTurn == true || display.isDieSelected(i) == true) {
				diValues[i] = (rgen.nextInt(1,6));
			}
		}
		return diValues;
	}

	private int getScore(int category) {
		boolean p = YahtzeeMagicStub.checkCategory(diValues, category);
		if (p == true) {
			int score = 0;
			switch (category) {
				case 1:
					for (int i = 0; i < 5; i++) {
						if (diValues[i] == 1) {
							score += diValues[i];
						}
					}
					break;
				case 2:
					for (int i = 0; i < 5; i++) {
						if (diValues[i] == 2) {
							score += diValues[i];
						}
					}
					break;
				case 3:
					for (int i = 0; i < 5; i++) {
						if (diValues[i] == 3) {
							score += diValues[i];
						}
					}
					break;
				case 4:
					for (int i = 0; i < 5; i++) {
						if (diValues[i] == 4) {
							score += diValues[i];
						}
					}
					break;
				case 5:
					for (int i = 0; i < 5; i++) {
						if (diValues[i] == 5) {
							score += diValues[i];
						}
					}
					break;
				case 6:
					for (int i = 0; i < 5; i++) {
						if (diValues[i] == 6) {
							score += diValues[i];
						}
					}
					break;
				case 9:
					for (int i = 0; i < 5; i++) {
						score += diValues[i];
					}
					break;
				case 10:
					for (int i = 0; i < 5; i++) {
						score += diValues[i];
					}
					break;
				case 11:
					score = 25;
					break;
				case 12:
					score = 30;
					break;
				case 13:
					score = 40;
					break;
				case 14:
					score = 50;
					break;
				case 15:
					for (int i = 0; i < 5; i++) {
						score += diValues[i];
					}
					break;
			}
				
			return score;
		}
		else return 0;
	}

	private boolean checkCategory(int[] diValues, int category) {
		return true;
	}

	private int calculateTotals() {
		int[] totalScore = new int[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			for (int j = 0; j <6; j++) {
				totalScore[i-1] += scorecard[i-1][j];
				if (totalScore[i-1] >= 63) {
					display.updateScorecard(8, i, 35);
					totalScore[i-1] += 35;
				}
				else {
					display.updateScorecard(8, i, 35);
				}
				display.updateScorecard(7, i, totalScore[i-1]);
			}
			int lowerScore = 0;
			for (int k = 9; k <= 15; k++) {
				totalScore[i-1] += scorecard[i-1][k];
				lowerScore += scorecard[i-1][k];
			}
			display.updateScorecard(16, i, lowerScore);
			display.updateScorecard(17, i, totalScore[i-1]);
		}
		int maxTotal = 1;
		for (int m = 0; m < nPlayers; m++) {
			if (totalScore[m] > maxTotal) {
				maxTotal = m;
			}
		}
		return maxTotal;
	}
		
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] diValues = new int[5];
	private int[][] scorecard = null;
	private int selectedCategory = 0;
}
