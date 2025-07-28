package model;



import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.board.Board;
import model.pieces.Bishop;
import model.pieces.Coordinate;
import model.pieces.Piece;
import model.pieces.Rook;

class CheckScannerTest {
	
	CheckScanner checkScanner;
	Board board = new Board();
	
	@BeforeEach
	public void setUp() {
		checkScanner = new CheckScanner(board);
	}
	
	@Test
	public void testHitByRook() {
		Piece king = board.findKing(true);
		Piece blackRook = new Rook(new Coordinate(5, 4), false, board);
		board.getPieceList().add(blackRook);
		board.getPieceList().remove(board.getPiece(new Coordinate(5, 6)));
		
		Assert.assertTrue(checkScanner.hitByRook(5, 6, king, 5, 6, 0, -1));
	}
	
	@Test
	public void testHitByBishop() {
		Piece king = board.findKing(true);
		Piece blackBishop = new Bishop(new Coordinate(7, 4), false, board);
		board.getPieceList().add(blackBishop);
		board.getPieceList().remove(board.getPiece(new Coordinate(5, 6)));
		
		Assert.assertTrue(checkScanner.hitByBishop(5, 6, king, 5, 6, -1, 1));
	}
	

}
