package array_poker;

import java.util.Random;
import java.util.Scanner;

public class Main {

	private static int[] diceArray = new int[5];
	private static boolean fullHouse = false;
	private static boolean pairsAndThrees = false;
	private static boolean straight = false;

	private static void welcome() {

	}

	// Pushes a random number between 1 and 6 to simulate a dice roll
	private static int randomGen() {
		Random rand = new Random();
		int num = rand.nextInt(6) + 1;
		return num;
	}

	// Fills a dice array with 5 dice
	private static int[] createHand() {
		for (int i = 0; i < diceArray.length; i++) {
			diceArray[i] = randomGen();
			// diceArray[i] = i + 1;
		}
		return diceArray;
	}

	// Displays the dice array
	private static void printHand() {
		for (int n : diceArray) {
			System.out.print(n + "  ");
		}
		System.out.print("\n");
	}

	private static void checkPairsAndThrees(int[] diceArray) {
		// make a logic algorithm to id the hand
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
					System.out.println("You have five of a kind, you lucky dog!");
				} else if (count == 4) {
					System.out.println("You have four of a kind for " + diceArray[i] + "'s!");
				} else if (count == 3) {
					System.out.println("You have three of a kind for " + diceArray[i] + "'s!");
				} else if (count == 2) {
					System.out.println("You have a pair of " + diceArray[i] + "'s!");
				}

			}

			else if (i == 1) {
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
						System.out.println("You have four of a kind for " + diceArray[i] + "'s!");
					} else if (count == 3) {
						System.out.println("You have three of a kind for " + diceArray[i] + "'s!");
					} else if (count == 2) {
						System.out.println("You have a pair of " + diceArray[i] + "'s!");
					}
				}
			}

			else if (i == 2) {
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
						System.out.println("You have three of a kind for " + diceArray[i] + "'s!");
					} else if (count == 2) {
						System.out.println("You have a pair of " + diceArray[i] + "'s!");
					}
				}
			}

			else if (i == 3) {
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
						System.out.println("You have a pair of " + diceArray[i] + "'s!");
					}
				}
			}
		}
		if(Taken1 != Taken2 && Taken1 != Taken3 && Taken1 != Taken4 
				&& Taken2 != Taken3 && Taken2 != Taken4 && Taken3 != Taken4){
			pairsAndThrees = false;
		}else{
			pairsAndThrees = true;
		}
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
			System.out.println("You have a straight, congrats! \n");
		}else{
			straight = false;
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
		//int lastIndexThree = 0;
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
					if ((diceArray[i] == diceArray[j]) 
							&& (diceArray[i] != lastIndexZero)) {
						iCountOne++;
					}
				}
			}
			if (i == 2) {
				lastIndexTwo = diceArray[i];
				for (int j = (i + 1); j < diceArray.length; j++) {
					if ((diceArray[i] == diceArray[j])
							&&((diceArray[i] != lastIndexZero)
									&&(diceArray[i]!=lastIndexOne))) {
						iCountTwo++;
					}
				}
			}
			if (i == 3) {
				for (int j = (i + 1); j < diceArray.length; j++) {
					if ((diceArray[i] == diceArray[j])
							&&(diceArray[i] != lastIndexZero)
									&&(diceArray[i]!=lastIndexOne)
										&&(diceArray[i]!=lastIndexTwo)) {
						iCountThree++;
					}
				}
			}
		}
		if ((iCountZero == 2 && iCountOne == 3) || (iCountZero == 3 && iCountOne == 2)
				|| (iCountZero == 2 && iCountTwo == 3) || (iCountZero == 3 && iCountTwo == 2)
				|| (iCountZero == 3 && iCountThree == 2)) {
			System.out.println("You have a full house!");
		} else {
			fullHouse = false;
		}
	}
	
	private static void identifyHand(){
		checkFullHouse(diceArray);
		if(!fullHouse){
			checkPairsAndThrees(diceArray);
		}else if(!pairsAndThrees){
			checkStraight(diceArray);
		}else if(!straight){
			System.out.println("I should write something encouraging.. but sometimes the truth hurts..");
		}
	}

	public static void main(String[] args) {
		welcome();

		char input;
		do {

			createHand();
			printHand();
			identifyHand();

			System.out.println("\nTo roll again, enter 'y'!");
			Scanner s = new Scanner(System.in);
			input = s.next().charAt(0);

		} while (input == 'y');
	}
}
