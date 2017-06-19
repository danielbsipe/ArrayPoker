package array_poker;

import java.util.Random;

public class Main {

	private static int[] diceArray = new int[5];

	private static void welcome() {

	}

	private static int randomGen() {
		Random rand = new Random();
		int num = rand.nextInt(6) + 1;
		return num;
	}

	private static int[] createHand() {
		for (int i = 0; i < diceArray.length; i++) {
			diceArray[i] = randomGen();
		}
		return diceArray;
	}

	private static void printHand() {
		for (int n : diceArray) {
			System.out.print(n + "  ");
		}
		System.out.print("\n");
	}

	private static void identifyHand(int[] diceArray) {
		// make a logic algorithm to id the hand
		int Taken1 = 0;
		int Taken2 = 0;
		int Taken3 = 0;
		int Taken4 = 0;
		int straightCount = 1;
		for (int i = 0; i < diceArray.length; i++) {
			if (i == 0) {
				int count = 1;
				for (int j = (i + 1); j < diceArray.length; j++) {
					if (diceArray[i] == diceArray[j]) {
						count++;
						if (count > 1) {
							Taken1 = diceArray[i];
						} else {
							straightCount++;
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

			if (i == 1) {
				int count = 1;
				for (int j = (i + 1); j < diceArray.length; j++) {
					if (diceArray[i] == diceArray[j]) {
						count++;
						if (count > 1) {
							Taken2 = diceArray[i];
						} else {
							straightCount++;
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

			if (i == 2) {
				int count = 1;
				for (int j = (i + 1); j < diceArray.length; j++) {
					if (diceArray[i] == diceArray[j]) {
						count++;
						if (count > 1) {
							Taken3 = diceArray[i];
						} else {
							straightCount++;
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

			if (i == 3) {
				int count = 1;
				for (int j = (i + 1); j < diceArray.length; j++) {
					if (diceArray[i] == diceArray[j]) {
						count++;
						if (count > 1) {
							Taken4 = diceArray[i];
						} else {
							straightCount++;
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
		
		if (straightCount == 5) {
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
				} else if (diceArray[i] == 2) {
					two++;
				} else if (diceArray[i] == 3) {
					three++;
				} else if (diceArray[i] == 4) {
					four++;
				} else if (diceArray[i] == 5) {
					five++;
				} else if (diceArray[i] == 6) {
					six++;
				}
			}
			
			if((one == 1 && two == 1 && three == 1 && four == 1 && five == 1) ||
					(two == 1 && three == 1 && four == 1 && five == 1 && six == 1)){
				System.out.println("You have a straight, congrats!");
			}else{
				System.out.println("Sorry, bad hand!");
			}
		}

	}

	public static void main(String[] args) {
		welcome();
		createHand();
		printHand();
		identifyHand(diceArray);
	}
}
