package com.tictactoe;

import java.util.*;

public class CreateBoard {

	//All the required variable declared
	private char board[] = new char[10];
	private char playerLetter;
	private char computerLetter;
	char winnerLetter;
	boolean playerFirst;
	int tempPos = 0;
	boolean completed = false;
	int filled = 0;
	Random random = new Random();
	Scanner scanner = new Scanner(System.in);

	//A empty board is created
	public CreateBoard() {
		board[0] = '1';
		for(int i=1;i<10;i++) {
			board[i] = ' ';
		}
	}

	//Allowing user to select a letter of his wish
	public void userInput() {
		//Ask User to Select Letter
		System.out.println("Select Your Letter\n1) X\t2) O");
		int choice = scanner.nextInt();

		switch(choice) {
		case 1:
			playerLetter = 'X';
			computerLetter = 'O';
			break;
		case 2:
			playerLetter = 'O';
			computerLetter = 'X';
			break;
		default:
			playerLetter = 'X';
			computerLetter = 'O';
			System.out.println("Invalid Choice. Your Letter will be considered as X");
			break;
		}
		System.out.println("Player = " + playerLetter + "\nComputer = " + computerLetter);
	}

	//Choosing who will start the game using a toss
	public void toss() {
		//Ask User to Select Toss
		System.out.println("Select Your Choice\n1) Heads\t2) Tails");
		int choice = scanner.nextInt();
		int tossWin = random.nextInt(2) + 1;
		if(tossWin == choice) {
			System.out.println("You Won The Toss");
			playerFirst = true;
		} else {
			System.out.println("Computer Won The Toss");
			playerFirst = false;
		}
	}

	//Printing the board
	public void showBoard() {
		System.out.println(" " + board[1] + " | " + board[2] + " | " + board[3]);
		System.out.println(" ----------");
		System.out.println(" " + board[4] + " | " + board[5] + " | " + board[6]);
		System.out.println(" ----------");
		System.out.println(" " + board[7] + " | " + board[8] + " | " + board[9]);

	}

	//The game loop allowing player and computer to play till the end
	public void makeMove() {
		while(filled<=8 && !completed) {
			if(playerFirst) {
				playerMove();
				computerMove();
			} else {
				computerMove();
				playerMove();
			}
		}
		printResult();
	}

	//Performs the Player move
	public void playerMove() {
		while(true) {
			if(filled<9 && !completed) {
				showBoard();
				System.out.println("Players Turn. Enter the position to Insert " + playerLetter);
				int pos = scanner.nextInt();
				if(pos<=0 || pos>9) {
					System.out.println("Invalid Position");
				} else if(board[pos] != ' ') {
					System.out.println("Letter Already Exist is Position " + pos);
				} else {
					board[pos] = playerLetter;
					filled += 1;
					checkWinner(playerLetter);
					break;
				}
			} else {
				break;
			}
		}
	}

	//Performs the Computer move
	public void computerMove() {
		if(filled<9 && !completed) {
			tempPos = 0;
			int countComputer = 0;
			int countPlayer = 0;
			int emptyPos = 0;
			boolean done = false;

			//Checking for Row Completion
			for(int i=1;i<=9;i=i+3) {
				countComputer = 0;
				countPlayer = 0;
				emptyPos = 0;
				for(int j=i;j<i+3;j++) {
					if(board[j] == computerLetter) {
						countComputer++;
					} else if(board[j] == playerLetter) {
						countPlayer++;
					} else {
						emptyPos = j;
					}
				}
				done = smartComputer(countComputer,countPlayer,emptyPos);
				if(done == true) {
					return;
				}
			}

			//Checking for Column Completion
			for(int i=1;i<=3;i++) {
				countComputer = 0;
				countPlayer = 0;
				emptyPos = 0;
				for(int j=i;j<=9;j=j+3) {
					if(board[j] == computerLetter) {
						countComputer++;
					} else if(board[j] == playerLetter) {
						countPlayer++;
					} else {
						emptyPos = j;
					}
					done = smartComputer(countComputer,countPlayer,emptyPos);
					if(done == true) {
						return;
					}
				}
			}

			//Checking Diagonal Completion
			countComputer = 0;
			countPlayer = 0;
			emptyPos = 0;
			for(int i=1;i<=9;i=i+4) {
				if(board[i] == computerLetter) {
					countComputer++;
				} else if(board[i] == playerLetter) {
					countPlayer++;
				} else {
					emptyPos = i;
				}
			}
			done = smartComputer(countComputer,countPlayer,emptyPos);
			if(done == true) {
				return;
			}

			countComputer = 0;
			countPlayer = 0;
			emptyPos = 0;
			for(int i=3;i<=7;i=i+2) {
				if(board[i] == computerLetter) {
					countComputer++;
				} else if(board[i] == playerLetter) {
					countPlayer++;
				} else {
					emptyPos = i;
				}
			}
			done = smartComputer(countComputer,countPlayer,emptyPos);
			if(done == true) {
				return;
			}

			//Blocking Player from Winning
			if(tempPos != 0) {
				board[tempPos] = computerLetter;
				filled += 1;
				checkWinner(computerLetter);
				return;
			}

			//Default Moves
			//Corner Moves
			int corner[] = {1,3,7,9};
			for(int i: corner) {
				if(board[i] == ' ') {
					board[i] = computerLetter;
					filled += 1;
					checkWinner(computerLetter);
					return;
				}
			}

			for(int i=1;i<=9;i++) {
				if(board[i] == ' ') {
					board[i] = computerLetter;
					filled += 1;
					checkWinner(computerLetter);
					break;
				}
			}
		}
	}

	// Finding the best position to make the next move
	public boolean smartComputer(int countComputer,int countPlayer,int emptyPos) {
		if(countComputer == 2 && board[emptyPos] == ' ') {
			board[emptyPos] = computerLetter;
			filled += 1;
			checkWinner(computerLetter);
			return true;
		} else if(countPlayer == 2 && board[emptyPos] == ' ') {
			System.out.println(tempPos);
			tempPos = emptyPos;
			return false;
		} else {
			return false;
		}
	}

	//All the required possibilities to check for Win
	public void checkWinner(char letter) {
		String boardLetters = null;
		String winLetters = "" + letter + letter + letter;
		for(int i=1;i<=8;i++) {

			switch(i) {
			case 1:
				boardLetters = "" + board[1] + board[2] + board[3];
				break;
			case 2:
				boardLetters = "" + board[4] + board[5] + board[6];
				break;
			case 3:
				boardLetters = "" + board[7] + board[8] + board[9];
				break;
			case 4:
				boardLetters = "" + board[1] + board[4] + board[7];
				break;
			case 5:
				boardLetters = "" + board[2] + board[5] + board[8];
				break;
			case 6:
				boardLetters = "" + board[3] + board[6] + board[9];
				break;
			case 7:
				boardLetters = "" + board[1] + board[5] + board[9];
				break;
			case 8:
				boardLetters = "" + board[3] + board[5] + board[7];
				break;
			}
			if(boardLetters.equals(winLetters)) {
				completed = true;
				winnerLetter = letter;
				break;
			}

		}
	}

	//Specific message displayed while ending the Game
	public void printResult() {
		showBoard();
		if(completed == true) {
			if(winnerLetter == playerLetter) {
				System.out.println("You have Won the Game");
			} else {
				System.out.println("Computer has Won the Game");
			}
		} else {
			System.out.println("Game Tied");
		}
	}
}
