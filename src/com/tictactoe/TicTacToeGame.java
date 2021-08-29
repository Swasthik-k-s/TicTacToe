package com.tictactoe;

import java.util.*;
public class TicTacToeGame {


	public static void main(String[] args) {
		
		System.out.println("Welcome to Tic-Toe-Game");
		CreateBoard game;
		
		//Creating new game until user wishes to play
		while(true) {
			game = new CreateBoard();
			game.userInput();
			game.toss();
			game.makeMove();
			Scanner sc = new Scanner(System.in);
			System.out.println("Play Another Game??\n1) YES\t2) NO");
			int newGame = sc.nextInt();
			if(newGame != 1) {
				System.out.println("Thank you!!! Exiting Game");
				sc.close();
				break;
			} 
		}
	}
}
