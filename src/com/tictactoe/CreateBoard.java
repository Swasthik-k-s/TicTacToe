package com.tictactoe;

import java.util.*;

public class CreateBoard {
	
	//All the required variable declared
	private char board[] = new char[10];
	private char playerLetter;
	private char computerLetter;
	char winnerLetter;
	boolean playerFirst;
	boolean completed = false;
	int filled = 0;
	int[] emptyField = {1,2,3,4,5,6,7,8,9};
	Random random = new Random();
	Scanner scanner = new Scanner(System.in);

	//A empty board is created
	public CreateBoard() {
		for(int i=0;i<10;i++) {
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
					for(int j=0;j<=9-filled;j++) {
						if(emptyField[j] == pos) {
							emptyField[j] = emptyField[9-filled];
						}
					} 
					checkBoard(playerLetter);
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
			int compArrayPos = random.nextInt(9-filled);
			int compPos = emptyField[compArrayPos];
			board[compPos] = computerLetter;
			filled += 1;
			emptyField[compArrayPos] = emptyField[9-filled];
			checkBoard(computerLetter);
		}
	}

	//All the required possibilities to check for Win
	public void checkBoard(char letter) {
		int count = 0;
		
		//Checking for Row Completion
		for(int i=1;i<=9;i=i+3) {
			count = 0;
			for(int j=0;j<3;j++) {
				if(board[i+j] == letter) {
					count++;
				}
			}
			checkResult(count, letter);
		}

		//Checking for Column Completion
		for(int i=1;i<=3;i++) {
			count = 0;
			for(int j=i;j<=9;j=j+3) {
				if(board[j] == letter) {
					count++;
				}
				checkResult(count, letter);
			}
		}

		//Checking Diagonal Completion
		count = 0;
		for(int i=1;i<=9;i=i+4) {
			if(board[i] == letter) {
				count++;
			}
		}
		checkResult(count, letter);
		count = 0;
		for(int i=3;i<=7;i=i+2) {
			if(board[i] == letter) {
				count++;
			}
		}
		checkResult(count, letter);
	}
	
	//Called for each winning possibility 
	public void checkResult(int count, char letter) {
		if(count == 3) {
			completed = true;
			winnerLetter = letter;
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
