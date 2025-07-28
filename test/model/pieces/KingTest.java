package model.pieces;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.board.Board;

class KingTest {
	
	Board board;
	
	@BeforeEach
	public void setUp() {
		board = new Board();
	}

	@Test
	public void testCanCastle() {
		board.getPieceList().remove(board.getPiece(new Coordinate(4, 7)));
		board.getPieceList().remove(board.getPiece(new Coordinate(5, 7)));
		board.getPieceList().remove(board.getPiece(new Coordinate(6, 7)));
		
		King king = new King(new Coordinate(4, 7), true, board);
		board.getPieceList().add(king);
		
		
		Assert.assertTrue(king.canCastle(6, 7));
	}

}
