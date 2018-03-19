package com.jetbrains;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Board board = new Board(10,10);
        System.out.println(Arrays.deepToString(board.fetchContent));
        board = board.fetchesCoordenate(1,5);
        System.out.println(Arrays.deepToString(board.fetchContent));
        System.out.println(board);
    }
}
