package model.pieces;



import model.board.Board;

/**
 * Már unom leírni, de a bástya osztálya.
 * @author Y89Q16
 */
public class Rook extends Piece {
    
	/**
	 * Ctor
	 * @param coords - Coordinate
	 * @param isWhite - boolean
	 * @param board - Board
	 */
    public Rook(Coordinate coords, boolean isWhite, Board board) {
        super("Rook", coords, isWhite, board);

    }

    /**
    * Megszabja a Bástya mozdulatait.
    * @param col - int
    * @param row - int
    * @return boolean
    */
    @Override
    public boolean isValidMovement(int col, int row) {
        return this.getCoords().getCol() == col || this.getCoords().getRow() == row;
    }

    /**
     * Akadályok kivizsgálása.
     * @param col - int
     * @param row - int
     * @return boolean
     */
    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        //checkleft
        if(this.getCoords().getCol() > col) {
            for(int c = this.getCoords().getCol() - 1; c > col; c--) {
                if(this.getBoard().getPiece(new Coordinate(c, this.getCoords().getRow())) != null) {
                    return true;
                }
            }
        }

        //checkright
        if(this.getCoords().getCol() < col) {
            for(int c = this.getCoords().getCol() + 1; c < col; c++) {
                if(this.getBoard().getPiece(new Coordinate(c, this.getCoords().getRow())) != null) {
                    return true;
                }
            }
        }

        //checkup
        if(this.getCoords().getRow() > row) {
            for(int r = this.getCoords().getRow() - 1; r > row; r--) {
                if(this.getBoard().getPiece(new Coordinate(this.getCoords().getCol(), r)) != null) {
                    return true;
                }
            }
        }

        //checkdown
        if(this.getCoords().getRow() < row) {
            for(int r = this.getCoords().getRow() + 1; r < row; r++) {
                if(this.getBoard().getPiece(new Coordinate(this.getCoords().getCol(), r)) != null) {
                    return true;
                }
            }
        }


        return false;
    }
}
