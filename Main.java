package array_poker;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {

	private int[] playerDiceArray = new int[5];
	private int[] opponentDiceArray = new int[5];
	private TextArea player;
	private TextArea opponent;
	private int playerScore = 0;
	private int opponentScore = 0;
	private boolean fullHouse = true;
	private boolean pairsAndThrees = true;
	private boolean straight = true;
	private boolean isPlayer = true;
	private Scanner scanner = new Scanner(System.in);
	public static int playerBalance;
	public static int oppBalance;

	public Main(TextArea p, TextArea o) {
		player = p;
		opponent = o;
	}

	private void welcome() {

		JFrame welcome = new JFrame();
		welcome.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		welcome.setAlwaysOnTop(true);
		welcome.setTitle("WELCOME TO DICE POKER");
		welcome.setSize(600, 400);
		welcome.setVisible(true);

		JPanel textPanel = new JPanel();
		TextArea welcomeText = new TextArea(17, 60);
		welcomeText.append("RULES:\n" + "Scoring Hierarchy of Valid Hands:\n\n" + "Five of a Kind:  	A A A A A \n"
				+ "Straight:        	A B C D E \n" + "Four of a Kind:  	A A A A B \n"
				+ "Full House:      	A A A B B \n" + "Three of a Kind: 	A A A B C \n"
				+ "Two Pairs:       	A A B B C \n" + "One Pair:        	A A B C D \n\n"
				+ "You will have the option to REROLL one die per round\n"
				+ "You will have the option to bid credits from from BANK\n"
				+ "and if you win you gain your opponents credits\n" + "if you lose, your opponent gains your credits\n"
				+ "GOOD LUCK!");
		welcomeText.setFont(new Font("Arial", Font.PLAIN, 16));
		welcomeText.setEditable(false);

		textPanel.add(welcomeText);
		welcome.add(textPanel);
	}

	// Pushes a random number between 1 and 6 to simulate a dice roll
	private int randomGen() {
		Random rand = new Random();
		int num = rand.nextInt(6) + 1;
		return num;
	}

	// Fills a dice array with 5 dice
	private int[] createHand(int[] diceArray) {
		for (int i = 0; i < diceArray.length; i++) {
			diceArray[i] = randomGen();
			// diceArray[i] = i + 1;
		}
		return diceArray;
	}

	// Displays the dice array
	private void printHand(int[] diceArray) {
		for (int n : diceArray) {
			if (isPlayer) {
				player.append(n + "  ");
			} else {
				opponent.append(n + "  ");
			}
		}
	}

	// identifies if the player has a full house (aabbb)
	private void checkFullHouse(int[] diceArray) {
		int iCountZero = 1;
		int iCountOne = 1;
		int iCountTwo = 1;
		int iCountThree = 1;
		int lastIndexZero = 0;
		int lastIndexOne = 0;
		int lastIndexTwo = 0;
		// int lastIndexThree = 0;
		for (int i = 0; i < diceArray.length; i++) {
			if (i == 0) {
				lastIndexZero = diceArray[i];
				for (int j = (i + 1); j < diceArray.length; j++) {
					if (diceArray[i] == diceArray[j]) {
						iCountZero++;
					}
				}
			}
			if (i == 1) {
				lastIndexOne = diceArray[i];
				for (int j = (i + 1); j < diceArray.length; j++) {
					if ((diceArray[i] == diceArray[j]) && (diceArray[i] != lastIndexZero)) {
						iCountOne++;
					}
				}
			}
			if (i == 2) {
				lastIndexTwo = diceArray[i];
				for (int j = (i + 1); j < diceArray.length; j++) {
					if ((diceArray[i] == diceArray[j])
							&& ((diceArray[i] != lastIndexZero) && (diceArray[i] != lastIndexOne))) {
						iCountTwo++;
					}
				}
			}
			if (i == 3) {
				for (int j = (i + 1); j < diceArray.length; j++) {
					if ((diceArray[i] == diceArray[j]) && (diceArray[i] != lastIndexZero)
							&& (diceArray[i] != lastIndexOne) && (diceArray[i] != lastIndexTwo)) {
						iCountThree++;
					}
				}
			}
		}
		if ((iCountZero == 2 && iCountOne == 3) || (iCountZero == 3 && iCountOne == 2)
				|| (iCountZero == 2 && iCountTwo == 3) || (iCountZero == 3 && iCountTwo == 2)
				|| (iCountZero == 3 && iCountThree == 2)) {
			fullHouse = true;
			pairsAndThrees = false;
			straight = false;
			if (isPlayer) {
				playerScore = 4;
				player.append("\nYou have a full house!");
			} else {
				opponentScore = 4;
				opponent.append("\nYou have a full house!");
			}
		} else {
			fullHouse = false;
		}
	}

	// Checks if the player has two or more of one number (aabcd or aaabc)
	// If a number occurs more than once in the dice array it is taken.
	// This method walks through the dice array and compares the index i
	// with the index j looking for matches. If there is a match, the number
	// of matches is counted in order to determine whether the player
	// has a pair, three of a kind, four of a kind etc.
	// pairCheck is true if the player has at least one pair in hand.
	// this is necessary to check for multiple pairs (aabbc, etc)
	private void checkPairsAndThrees(int[] diceArray) {
		boolean pairCheck = false;
		int takenOne = 0;
		int takenTwo = 0;
		int takenThree = 0;
		int takenFour = 0;
		for (int i = 0; i < diceArray.length; i++) {
			if (i == 0) {
				int count = 1;
				for (int j = (i + 1); j < diceArray.length; j++) {
					if (diceArray[i] == diceArray[j]) {
						count++;
						if (count > 1) {
							takenOne = diceArray[i];
						}
					}
				}
				if (count == 5) {
					if (isPlayer) {
						playerScore = 7;
						player.append("\nYou have five of a kind, you lucky dog!");
					} else {
						opponentScore = 7;
						opponent.append("\nYou have five of a kind, you lucky dog!");
					}
				}
				if (count == 4) {
					if (isPlayer) {
						playerScore = 5;
						player.append("\nYou have four of a kind for " + diceArray[i] + "'s!");
					} else {
						opponentScore = 5;
						opponent.append("\nYou have four of a kind for " + diceArray[i] + "'s!");
					}
				}
				if (count == 3) {
					if (isPlayer) {
						playerScore = 3;
						player.append("\nYou have three of a kind for " + diceArray[i] + "'s!");
					} else {
						opponentScore = 3;
						opponent.append("\nYou have three of a kind for " + diceArray[i] + "'s!");
					}
				}
				if (count == 2) {
					pairCheck = true;
					if (isPlayer) {
						playerScore = 1;
						player.append("\nYou have a pair of " + diceArray[i] + "'s!");
					} else {
						opponentScore = 1;
						opponent.append("\nYou have a pair of " + diceArray[i] + "'s!");
					}
				}

			}

			if (i == 1) {
				int count = 1;
				for (int j = (i + 1); j < diceArray.length; j++) {
					if (diceArray[i] == diceArray[j]) {
						count++;
						if (count > 1) {
							takenTwo = diceArray[i];
						}
					}
				}
				if (takenTwo != takenOne) {
					if (count == 4) {
						if (isPlayer) {
							playerScore = 5;
							player.append("\nYou have four of a kind for " + diceArray[i] + "'s!");
						} else {
							opponentScore = 5;
							opponent.append("\nYou have four of a kind for " + diceArray[i] + "'s!");
						}
					}
					if (count == 3) {
						if (isPlayer) {
							playerScore = 3;
							player.append("\nYou have three of a kind for " + diceArray[i] + "'s!");
						} else {
							opponentScore = 3;
							opponent.append("\nYou have three of a kind for " + diceArray[i] + "'s!");
						}
					}
					if (count == 2) {
						if (pairCheck) {
							if (isPlayer) {
								playerScore = 2;
								player.append("\nYou have a pair of " + diceArray[i] + "'s!");
							} else {
								opponentScore = 2;
								opponent.append("\nYou have a pair of " + diceArray[i] + "'s!");
							}

						} else {
							pairCheck = true;
							if (isPlayer) {
								playerScore = 1;
								player.append("\nYou have a pair of " + diceArray[i] + "'s!");
							} else {
								opponentScore = 1;
								opponent.append("\nYou have a pair of " + diceArray[i] + "'s!");
							}
						}
					}
				}
			}
			if (i == 2) {
				int count = 1;
				for (int j = (i + 1); j < diceArray.length; j++) {
					if (diceArray[i] == diceArray[j]) {
						count++;
						if (count > 1) {
							takenThree = diceArray[i];
						}
					}
				}
				if (takenThree != takenTwo && takenThree != takenOne) {
					if (count == 3) {
						if (isPlayer) {
							playerScore = 3;
							player.append("\nYou have three of a kind for " + diceArray[i] + "'s!");
						} else {
							opponentScore = 3;
							opponent.append("\nYou have three of a kind for " + diceArray[i] + "'s!");
						}
					}
					if (count == 2) {
						if (pairCheck) {
							if (isPlayer) {
								playerScore = 2;
								player.append("\nYou have a pair of " + diceArray[i] + "'s!");
							} else {
								opponentScore = 2;
								opponent.append("\nYou have a pair of " + diceArray[i] + "'s!");
							}

						} else {
							pairCheck = true;
							if (isPlayer) {
								playerScore = 1;
								player.append("\nYou have a pair of " + diceArray[i] + "'s!");
							} else {
								opponentScore = 1;
								opponent.append("\nYou have a pair of " + diceArray[i] + "'s!");
							}
						}
					}
				}
			}

			if (i == 3) {
				int count = 1;
				for (int j = (i + 1); j < diceArray.length; j++) {
					if (diceArray[i] == diceArray[j]) {
						count++;
						if (count > 1) {
							takenFour = diceArray[i];
						}
					}
				}
				if (takenFour != takenThree && takenFour != takenTwo && takenFour != takenOne) {
					if (count == 2) {
						if (pairCheck) {
							if (isPlayer) {
								playerScore = 2;
								player.append("\nYou have a pair of " + diceArray[i] + "'s!");
							} else {
								opponentScore = 2;
								opponent.append("\nYou have a pair of " + diceArray[i] + "'s!");
							}

						} else {
							pairCheck = true;
							if (isPlayer) {
								playerScore = 1;
								player.append("\nYou have a pair of " + diceArray[i] + "'s!");
							} else {
								opponentScore = 1;
								opponent.append("\nYou have a pair of " + diceArray[i] + "'s!");
							}
						}
					}
				}
			}
		}

		if (takenOne != 0 || takenTwo != 0 || takenThree != 0 || takenFour != 0) {
			pairsAndThrees = true;
			straight = false;
		} else {
			pairsAndThrees = false;
		}
		pairCheck = false;
	}

	// identify a possible straight (abcde)
	private void checkStraight(int[] diceArray) {
		int one = 0;
		int two = 0;
		int three = 0;
		int four = 0;
		int five = 0;
		int six = 0;
		for (int i = 0; i < diceArray.length; i++) {
			if (diceArray[i] == 1) {
				one++;
			}
			if (diceArray[i] == 2) {
				two++;
			}
			if (diceArray[i] == 3) {
				three++;
			}
			if (diceArray[i] == 4) {
				four++;
			}
			if (diceArray[i] == 5) {
				five++;
			}
			if (diceArray[i] == 6) {
				six++;
			}
		}

		if ((one == 1 && two == 1 && three == 1 && four == 1 && five == 1)
				|| (two == 1 && three == 1 && four == 1 && five == 1 && six == 1)) {
			straight = true;
			if (isPlayer) {
				playerScore = 6;
				player.append("\nYou have a straight, congrats! \n");
			} else {
				opponentScore = 6;
				opponent.append("\nYou have a straight, congrats! \n");
			}
		} else {
			straight = false;
		}
	}

	// Once a round is complete, the bools must return to default values
	private void resetBools() {
		fullHouse = true;
		pairsAndThrees = true;
		straight = true;
	}

	// This might not be necessary, but I included it to be safe
	private void resetScores() {
		playerScore = 0;
		opponentScore = 0;
	}

	// Check the players hand for any valid dice combinations
	private void identifyHand(int[] diceArray) {
		checkFullHouse(diceArray);
		if (!fullHouse) {
			checkPairsAndThrees(diceArray);
		}
		if (!pairsAndThrees) {
			checkStraight(diceArray);
		}
		if (!straight) {
		}
	}

	// When the player and opponent have the same hand, the highest
	// die must be found and compared in order to determine a winner
	private static int findHighDie(int[] diceArray) {
		int highDie = 0;
		int pairOne = 0;
		int pairTwo = 0;
		for (int i = 0; i < diceArray.length; i++) {
			if (i == 0) {
				int count = 0;
				for (int j = i + 1; j < diceArray.length; j++) {
					if (diceArray[i] == diceArray[j]) {
						count++;
					}
				}
				if (count > 0) {
					pairOne = diceArray[i];
				}
			}
			if (i == 1) {
				int count = 0;
				for (int j = i + 1; j < diceArray.length; j++) {
					if (diceArray[i] == diceArray[j]) {
						count++;
					}
				}
				if (count > 0 && pairOne != 0) {
					pairTwo = diceArray[i];
				}
				if (count > 0 && pairOne == 0) {
					pairOne = diceArray[i];
				}

			}
			if (i == 2) {
				int count = 0;
				for (int j = i + 1; j < diceArray.length; j++) {
					if (diceArray[i] == diceArray[j]) {
						count++;
					}
				}
				if (count > 0 && pairOne != 0) {
					pairTwo = diceArray[i];
				}
				if (count > 0 && pairOne == 0) {
					pairOne = diceArray[i];
				}
			}
			if (i == 3) {
				int count = 0;
				for (int j = i + 1; j < diceArray.length; j++) {
					if (diceArray[i] == diceArray[j]) {
						count++;
					}
				}
				if (count > 0 && pairOne != 0) {
					pairTwo = diceArray[i];
				}
				if (count > 0 && pairOne == 0) {
					pairOne = diceArray[i];
				}
			}
		}

		if (pairOne > pairTwo) {
			highDie = pairOne;
		}
		if (pairOne < pairTwo) {
			highDie = pairTwo;
		}

		return highDie;
	}

	// When the player and opponent have a tied score, the high
	// die must be compared to determine a winner, if the high die
	// are the same, then a tie is declared
	private void tieBreaker() {
		int playerHighDie = findHighDie(playerDiceArray);
		int oppHighDie = findHighDie(opponentDiceArray);

		if (playerHighDie > oppHighDie) {
			player.append("\nYou won the round!");
		} else if (playerHighDie < oppHighDie) {
			player.append("\nYou lost the round!");
		} else {
			player.append("\nYou tied!");
		}
	}

	private void reroll(int rerollDie, int[] diceArray, boolean setPlayer) {
		isPlayer = setPlayer;
		for (int i = 0; i < diceArray.length; i++) {
			if (diceArray[i] == rerollDie) {
				diceArray[i] = randomGen();
				break;
			}
		}
		printHand(diceArray);
		identifyHand(diceArray);
	}

	private void suggestPlayerReroll() {
		char input;
		int rerollDie = 0;
		player.append("\nPress 'r' if you want to RE-ROLL");
		input = scanner.next().charAt(0);
		if (input == 'r') {
			player.append("\nSelect the dice VALUE you want to RE-ROLL\n");
			rerollDie = scanner.nextInt();
			reroll(rerollDie, playerDiceArray, true);
		}

	}

	private void findOppReroll(int[] diceArray) {
		if (pairsAndThrees || !straight) {
			opponent.append("\n...Re-rolling...\n");
			int rerollDie = 0;
			int dieOne = diceArray[0];
			int dieTwo = diceArray[1];
			int dieThree = diceArray[2];
			int dieFour = diceArray[3];
			int dieFive = diceArray[4];
			
			if (dieOne != dieTwo && dieOne != dieThree && dieOne != dieFour && dieOne != dieFive) {
				rerollDie = dieOne;
			}
			if (dieTwo != dieOne && dieTwo != dieThree && dieTwo != dieFour && dieTwo != dieFive) {
				rerollDie = dieTwo;
			}
			if (dieThree != dieOne && dieThree != dieTwo && dieThree != dieFour && dieThree != dieFive) {
				rerollDie = dieThree;
			}
			if (dieFour != dieOne && dieFour != dieTwo && dieFour != dieThree && dieFour != dieFive) {
				rerollDie = dieFour;
			}
			if (dieFive != dieOne && dieFive != dieTwo && dieFive != dieThree && dieFive != dieFour) {
				rerollDie = dieFive;
			}
			
			reroll(rerollDie, opponentDiceArray, false);
		}
	}

	// Scores are compared in order to determine a winner of the round
	private void compareScores() {
		if (playerScore > opponentScore) {
			player.append("\nYou won the round!");
		} else if (playerScore < opponentScore) {
			player.append("\nYou lost the round!");
		} else {
			tieBreaker();
		}
	}

	// setPlayer determines whether the player or opponent is playing
	// then the game logic is executed
	private void playPoker(int[] diceArray, boolean setPlayer) {
		isPlayer = setPlayer;

		createHand(diceArray);
		printHand(diceArray);
		identifyHand(diceArray);

	}

	public void letsPlay() {

		char continueIn;
		do {
			playPoker(playerDiceArray, true);
			resetBools();
			playPoker(opponentDiceArray, false);
			suggestPlayerReroll();
			findOppReroll(opponentDiceArray);
			compareScores();
			resetBools();
			resetScores();

			continueIn = scanner.next().charAt(0);

			player.setText("Player\n--------------\n");
			opponent.setText("Opponent\n--------------\n");
		} while (continueIn == 'y');
		if (scanner != null) {
			scanner.close();
		}
	}

	public static void main(String[] args) {

		JFrame gameWindow = new JFrame();
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setAlwaysOnTop(true);
		gameWindow.setTitle("DICE POKER: Inspired by the Witcher 2 Mini-Game");
		gameWindow.setSize(800, 400);

		GameFrame gf = new GameFrame();
		Main main = new Main(gf.player, gf.opponent);
		BankAccount playerBank = new BankAccount(true, playerBalance);
		BankAccount oppBank = new BankAccount(false, oppBalance);

		Container pane = gameWindow.getContentPane();
		pane.add("Center", gf);
		gameWindow.setVisible(true);
		gf.setGame(main);

		main.welcome();
		main.letsPlay();
	}
}