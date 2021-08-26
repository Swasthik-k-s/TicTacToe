package com.tictactoe;

public class CreateBoard {
	
	private char board[] = new char[10];
	
	public CreateBoard() {
		for(int i=0;i<10;i++) {
			board[i] = ' ';
		}
	}
}
