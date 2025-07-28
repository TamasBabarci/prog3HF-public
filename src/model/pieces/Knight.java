package model.pieces;


import model.board.Board;

/**
 * Lovag osztály.
 * 
 * @author Y89Q16
 */
public class Knight extends Piece { 

	/**
	 * Ctor
	 * @param coords - Coordinate
	 * @param isWhite - boolean
	 * @param board - Board
	 */
    public Knight(Coordinate coords, boolean isWhite, Board board) {
        super("Knight", coords, isWhite, board);
    }

    /**
     * A paci lépéseit meghatározó függvény.
     * @param col - int
     * @param row - int
     * @return boolean
     */
    @Override
    public boolean isValidMovement(int col, int row) {
        return Math.abs(col - this.getCoords().getCol()) * Math.abs(row - this.getCoords().getRow()) == 2;
    }

    /**
     * Uaz, mint a királynál. Itt is csak placeholder.
     * @param col - int
     * @param row - int
     * @return boolean
     */
    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        return false;
    }
}
