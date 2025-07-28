package model.board;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.Move;
import model.pieces.Coordinate;
import model.pieces.King;
import model.pieces.Rook;
import model.pieces.Piece;


class BoardTest {

	Board board;
	
	@BeforeEach
	public void setUp() {
		board = new Board();
	}
	
	
	@Test
	public void testSameTeam() {
		Piece p1 = new Rook(new Coordinate(7, 7), true, board);
		Piece p2 = new King(new Coordinate(4, 7), true, board);
		
		Assert.assertTrue(board.sameTeam(p1, p2));
	}
	
	
	@Test
	public void testGetPiece() {
		Piece p1 = new Rook(new Coordinate(7, 7), true, board);
		board.getPieceList().remove(board.getPiece(p1.getCoords()));
		board.getPieceList().add(p1);
		
		Piece p2 = board.getPiece(p1.getCoords());
		
		Assert.assertSame(p1, p2);
	}
	
	@Test
	public void testIsValidMove() {
		Move move = new Move(board, board.getPiece(new Coordinate(7, 7)), 7, 6);
		
		boolean wasMoveValid = board.isValidMove(move);
		
		Assert.assertFalse(wasMoveValid);
	}
	
	@Test
	public void testMakeMove() {
		Move move = new Move(board, board.getPiece(new Coordinate(4, 6)), 4, 4);
		
		board.makeMove(move);
		
		Assert.assertFalse(board.isWhiteToMove());
		Assert.assertFalse(board.getPiece(new Coordinate(4, 4)).isFirstMove());
		Assert.assertTrue(board.getPiece(new Coordinate(4, 6)) == null);
	}
	
	
	@Test
	public void testFindKing() {
		Piece king = board.findKing(true);
		
		Assert.assertSame(king, board.getPiece(new Coordinate(4, 7)));
		
	}
}
