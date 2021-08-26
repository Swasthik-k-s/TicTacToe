package com.tictactoe;

public class TicTacToeGame {

	public static void main(String[] args) {
		System.out.println("Welcome to Tic-Toe-Game");
		CreateBoard game = new CreateBoard();
		
		game.userInput();
		game.toss();
		game.makeMove();
	}
}
