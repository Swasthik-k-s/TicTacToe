package com.tictactoe;

import java.util.*;

public class CreateBoard {
	
	private char board[] = new char[10];
	private char player;
	private char computer;
	
	public CreateBoard() {
		for(int i=0;i<10;i++) {
			board[i] = ' ';
		}
	}
	
	public void userInput() {
		Scanner scanner = new Scanner(System.in);
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
}
