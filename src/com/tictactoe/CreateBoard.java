package com.tictactoe;

import java.util.*;

public class CreateBoard {

	private char board[] = new char[10];
	private char player;
	private char computer;
	Scanner scanner = new Scanner(System.in);

	public CreateBoard() {
		for(int i=0;i<10;i++) {
			board[i] = ' ';
		}
	}

	public void userInput() {
		System.out.println("Select Your Letter\n1) X\t2) O");
		int choice = scanner.nextInt();

		switch(choice) {
		case 1:
			player = 'X';
			computer = 'O';
			break;
		case 2:
			player = 'O';
			computer = 'X';
			break;
		default:
			player = 'X';
			computer = 'O';
			System.out.println("Invalid Choice. Your Letter will be considered as X");
			break;
		}

		System.out.println("Player = " + player + "\nComputer = " + computer);
	}

	public void showBoard() {
		for (int i = 1; i < board.length; i = i + 3) {
			for (int j = 0; j < 3; j++) {
				if (j == 1) {
					System.out.print(" | " + board[i + j] + " | ");
				} else if (j == 0) {
					System.out.print(" " + board[i + j]);
				} else {
					System.out.print(board[i + j]);
				}
			}
			if (i != 7) {
				System.out.println("\n ----------");
			} else {
				System.out.println("\n");
			}
		}
	}

	public void makeMove() {
		System.out.println("Players Turn. Enter the position to Insert " + player);
		int pos = scanner.nextInt();
		if(pos<=0 || pos>9) {
			System.out.println("Invalid Position");
		} else if(board[pos] != ' ') {
			System.out.println("Letter Already Exist is Position " + pos);
		} else {
			board[pos] = player;
			showBoard();
		}
	}
}
