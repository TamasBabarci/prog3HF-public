package model.pieces;



import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.Move;
import model.board.Board;
import view.board.BoardView;

class PawnTest {

	Board board;
	
	@BeforeEach
	public void setUp() {
		board = new Board();
	}
	
	@Test
	public void testEnPassant() {
		Piece blackPawn = new Pawn(new Coordinate(4, 4), false, board);
		board.getPieceList().add(blackPawn);
		
		Move move1 = new Move(board, board.getPiece(new Coordinate(5, 6)), 5, 4);
		board.makeMove(move1);
		
		Move move2 = new Move(board, blackPawn, 5, 5);
		board.makeMove(move2);
		
		Assert.assertTrue(board.getPiece(new Coordinate(5, 4)) == null);
		Assert.assertSame(board.getPiece(new Coordinate(5, 5)), blackPawn);
		
	}
	
	@Test
	public void testPawnPromotion() {
		board.getPieceList().remove(board.getPiece(new Coordinate(7, 6)));
		board.getPieceList().remove(board.getPiece(new Coordinate(7, 7)));
		Piece blackPawn = new Pawn(new Coordinate(7, 6), false, board);
		board.getPieceList().add(blackPawn);
		
		BoardView bw = new BoardView(board);
		board.setBoardView(bw);
		
		Move move1 = new Move(board, blackPawn, 7, 7);
		board.makeMove(move1);
		
		Assert.assertEquals(board.getPiece(new Coordinate(7, 7)).getName(), "Queen");
		Assert.assertNotSame(board.getPiece(new Coordinate(7, 7)), blackPawn);
		
		
	}

}
