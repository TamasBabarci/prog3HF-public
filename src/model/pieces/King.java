package model.pieces;


import controller.Move;
import model.board.Board;

/**
 * A királyt megvalósító osztály.
 * 
 * @author Y89Q16
 */
public class King extends Piece {

	/**
	 * Ctor, ugyanaz, mint az öt másik.
	 * @param coords - Coordinate
	 * @param isWhite - boolean
	 * @param board - Board
	 */
    public King(Coordinate coords, boolean isWhite, Board board) {
        super("King", coords, isWhite, board);
    }

    /**
     * Ez a metódus megszabja, hogy hogyan léphet a király, milyen feltételek mellett.
     * @param col - int
     * @param row - int
     * @return boolean
     */
    @Override
    public boolean isValidMovement(int col, int row) {
        return Math.abs((col - this.getCoords().getCol()) * (row - this.getCoords().getRow())) == 1
            || Math.abs((col - this.getCoords().getCol())) + Math.abs((row - this.getCoords().getRow())) == 1
            || canCastle(col, row)
            ;
    }

    /**
     * Ez a metódus, csak simán hamissal tér vissza. Azért van, hogy a Piece abstract metódusa implementálva legyen.
     * Alapból a király úgyse ütközne senkivel.
     * @return boolean
     */
    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        return false;
    }

    /**
     * A sáncolást vizsgáló metódus.
     * @param col - int
     * @param row - int
     * @return boolean
     */
    public boolean canCastle(int col, int row) {
        if(this.getCoords().getRow() == row) {
            if(col == 6) {
                Piece rook = this.getBoard().getPiece(new Coordinate(7, row));
                if(rook != null && rook.isFirstMove() && this.isFirstMove()) {
                    return this.getBoard().getPiece(new Coordinate(5, row)) == null
                        && this.getBoard().getPiece(new Coordinate(6, row)) == null
                        && !this.getBoard().getCheckScanner().isKingChecked(new Move(this.getBoard(), this, 6, row))
                    ;
                }
            } else if(col == 2) {
                Piece rook = this.getBoard().getPiece(new Coordinate(0, row));
                if(rook != null && rook.isFirstMove() && this.isFirstMove()) {
                    return this.getBoard().getPiece(new Coordinate(3, row)) == null
                        && this.getBoard().getPiece(new Coordinate(2, row)) == null
                        && this.getBoard().getPiece(new Coordinate(1, row)) == null
                        && !this.getBoard().getCheckScanner().isKingChecked(new Move(this.getBoard(), this, 2, row))
                    ;
                }
            }
        }

        return false;
    }
}
