package array_poker;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame {

	private static int[] playerDiceArray = new int[5];
	private static int[] opponentDiceArray = new int[5];
	private static int playerScore = 0;
	private static int opponentScore = 0;
	private static boolean fullHouse = true;
	private static boolean pairsAndThrees = true;
	private static boolean straight = true;
	private static boolean isPlayer = true;
	static TextArea player = new TextArea(10, 50);
	static TextArea opponent = new TextArea(10, 50);

	private static void welcome() {

	}

	// Pushes a random number between 1 and 6 to simulate a dice roll
	private static int randomGen() {
		Random rand = new Random();
		int num = rand.nextInt(6) + 1;
		return num;
	}

	// Fills a dice array with 5 dice
	private static int[] createHand(int[] diceArray) {
		for (int i = 0; i < diceArray.length; i++) {
			diceArray[i] = randomGen();
			// diceArray[i] = i + 1;
		}
		return diceArray;
	}

	// Displays the dice array
	private static void printHand(int[] diceArray) {
		for (int n : diceArray) {
			if (isPlayer) {
				player.append(n + "  ");
			} else {
				opponent.append(n + "  ");
			}
		}
	}

	private static void checkFullHouse(int[] diceArray) {
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

	// If a number occurs more than once in the dice array it is taken.
	// This method walks through the dice array and compares the index i
	// with the index j looking for matches. If there is a match, the number
	// of matches is counted in order to determine whether the player
	// has a pair, three of a kind, four of a kind etc.
	private static void checkPairsAndThrees(int[] diceArray) {
		boolean pairCheck = false;
		int Taken1 = 0;
		int Taken2 = 0;
		int Taken3 = 0;
		int Taken4 = 0;
		for (int i = 0; i < diceArray.length; i++) {
			if (i == 0) {
				int count = 1;
				for (int j = (i + 1); j < diceArray.length; j++) {
					if (diceArray[i] == diceArray[j]) {
						count++;
						if (count > 1) {
							Taken1 = diceArray[i];
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
							Taken2 = diceArray[i];
						}
					}
				}
				if (Taken2 != Taken1) {
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
							Taken3 = diceArray[i];
						}
					}
				}
				if (Taken3 != Taken2 && Taken3 != Taken1) {
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
							Taken4 = diceArray[i];
						}
					}
				}
				if (Taken4 != Taken3 && Taken4 != Taken2 && Taken4 != Taken1) {
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

		if (Taken1 != 0 || Taken2 != 0 || Taken3 != 0 || Taken4 != 0) {
			pairsAndThrees = true;
		} else {
			pairsAndThrees = false;
		}
		pairCheck = false;
	}

	private static void checkStraight(int[] diceArray) {
		// identify a possible straight
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

	private static void resetBools() {
		fullHouse = true;
		pairsAndThrees = true;
		straight = true;
	}

	private static void resetScores() {
		playerScore = 0;
		opponentScore = 0;
	}

	private static void identifyHand(int[] diceArray) {
		checkFullHouse(diceArray);
		if (!fullHouse) {
			checkPairsAndThrees(diceArray);
		}
		if (!pairsAndThrees) {
			checkStraight(diceArray);
		}
		if (!straight) {
			if (isPlayer) {
				player.append("\nI should write something encouraging.. \nbut sometimes the truth hurts.." + "\n");
			} else {
				opponent.append("\nI should write something encouraging.. \nbut sometimes the truth hurts.." + "\n");
			}
		}
	}
	
	private static int findHighDie(int [] diceArray){
		int count = 0;
		int max = 0;
		int highDie = 0;
		for(int i = 0; i < diceArray.length; i ++){
			if(diceArray[i]>max){
				max = diceArray[i];
			}
		}
		for(int j = 0; j < diceArray.length; j++){
			if(diceArray[j] == max){
				count++;
			}
			if(count == 2){
				highDie = max;
				break;
			}
			max -= 1;
		}
		return highDie;
	}
	
	private static void tieBreaker(){
		int playerHighDie = findHighDie(playerDiceArray);
		int oppHighDie = findHighDie(opponentDiceArray);
		
		if(playerHighDie > oppHighDie){
			player.append("\nYou won the round!");
		}else if(playerHighDie < oppHighDie){
			player.append("\nYou lost the round!");
		}else{
			player.append("\nYou tied!");
		}
	}

	private static void compareScores() {
		if (playerScore > opponentScore) {
			player.append("\nYou won the round!");
		} else if (playerScore < opponentScore) {
			player.append("\nYou lost the round!");
		} else {
			tieBreaker();
		}
	}

	private static void playPoker(int[] diceArray, boolean setPlayer) {
		isPlayer = setPlayer;

		createHand(diceArray);
		printHand(diceArray);
		identifyHand(diceArray);
		resetBools();

	}

	public Main() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setTitle("DICE POKER: Inspired by the Witcher 2 Mini-Game");
		this.setSize(1200, 400);
		this.setLayout(new BorderLayout());
		this.setVisible(true);

		JPanel container = new JPanel();
		JPanel panelOne = new JPanel();
		JPanel panelTwo = new JPanel();
		Font myFont = new Font("Arial", Font.PLAIN, 18);

		container.setLayout(new GridLayout(1, 2));
		container.add(panelOne);
		container.add(panelTwo);
		panelOne.add(new JLabel("Player"), BorderLayout.EAST);
		panelTwo.add(new JLabel("Opponent"), BorderLayout.EAST);
		panelOne.add(player);
		panelTwo.add(opponent);

		player.setEditable(false);
		player.setFont(myFont);
		player.setSize(100, 100);
		opponent.setEditable(false);
		opponent.setFont(myFont);
		opponent.setSize(100, 100);

		this.add(container);

	}

	public static void main(String[] args) {
		new Main();

		welcome();

		char input;
		do {
			playPoker(playerDiceArray, true);
			playPoker(opponentDiceArray, false);
			compareScores();
			resetScores();

			System.out.println("\nTo roll again, enter 'y'!");
			Scanner s = new Scanner(System.in);
			input = s.next().charAt(0);
			// s.close();

			player.setText("");
			opponent.setText("");

		} while (input == 'y');

	}
}
