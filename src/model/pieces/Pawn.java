package model.pieces;


import model.board.Board;

/**
 * A gyalogot megvalósító osztály.
 * 
 * @author Y89Q16
 */
public class Pawn extends Piece {

	/**
	 * Ctor, uaz, mint az összes többi.
	 * @param coords - Coordinate
	 * @param isWhite - boolean
	 * @param board - Board
	 */
    public Pawn(Coordinate coords, boolean isWhite, Board board) {
        super("Pawn", coords, isWhite, board);
        
    }

    /**
     * A paraszt lépéseit megszabó metódus.
     * @param col - int
     * @param row - int
     * @return boolean
     */
    @Override
    public boolean isValidMovement(int col, int row) {

        int colorIndex = this.isWhite() ? 1 : -1;

        //push pawn 1
        if(this.getCoords().getCol() == col && this.getCoords().getRow() - colorIndex == row
            && this.getBoard().getPiece(new Coordinate(col, row)) == null) {
            return true;
        }

        //push pawn 2
        if(this.isFirstMove() && this.getCoords().getCol() == col && this.getCoords().getRow() - colorIndex * 2 == row
            && this.getBoard().getPiece(new Coordinate(col, row)) == null && this.getBoard().getPiece(new Coordinate(col, row + colorIndex)) == null) {
            return true;
        }

        //capture left
        if(col == this.getCoords().getCol() - 1 && row == this.getCoords().getRow() - colorIndex 
            && this.getBoard().getPiece(new Coordinate(col, row)) != null) {
            return true;
        }

        //capture right
        if(col == this.getCoords().getCol() + 1 && row == this.getCoords().getRow() - colorIndex 
            && this.getBoard().getPiece(new Coordinate(col, row)) != null) {
            return true;
        }

        //en passant left
        if(this.getBoard().getTileNum(col, row) == this.getBoard().getEnPassantTile() && col == this.getCoords().getCol() - 1
            && row == this.getCoords().getRow() - colorIndex && this.getBoard().getPiece(new Coordinate(col, row + colorIndex)) != null) {
            return true;
        }

        //en passant right
        if(this.getBoard().getTileNum(col, row) == this.getBoard().getEnPassantTile() && col == this.getCoords().getCol() + 1
            && row == this.getCoords().getRow() - colorIndex && this.getBoard().getPiece(new Coordinate(col, row + colorIndex)) != null) {
            return true;
        }

        
        return false;
    }

    /**
     * Ez is csak helytartó szerepet lát el.
     * @param col - int
     * @param row - int
     * @return boolean
     */
    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        return false;
    }
}
