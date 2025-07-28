package model.pieces;



import model.board.Board;

/**
 * Vezér osztály.
 * 
 * @author Y89Q16
 */
public class Queen extends Piece {
    
	/**
	 * Ctor, uaz, mint a többi.
	 * @param coords - Coordinate
	 * @param isWhite - boolean
	 * @param board - Board
	 */
    public Queen(Coordinate coords, boolean isWhite, Board board) {
        super("Queen", coords, isWhite, board);
        
    }
    
    /**
     * Megszabja a Vezér mozdulatait.
     * @param col - int
     * @param row - int
     * @return boolean
     */
    @Override
    public boolean isValidMovement(int col, int row) {
        return this.getCoords().getCol() == col || this.getCoords().getRow() == row
            || Math.abs(this.getCoords().getCol() - col) == Math.abs(this.getCoords().getRow() - row);
    }

    /**
     * Akadályok kivizsgálása.
     * @param col - int
     * @param row - int
     * @return boolean
     */
    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        if(this.getCoords().getCol() == col || this.getCoords().getRow() == row) {

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
        } else {
            //up left
            if(this.getCoords().getCol() > col && this.getCoords().getRow() > row) {
                for(int i = 1; i < Math.abs(this.getCoords().getCol() - col); i++) {
                    if(this.getBoard().getPiece(new Coordinate(this.getCoords().getCol() - i, this.getCoords().getRow() - i)) != null) {
                        return true;
                    }
                }
            }

            //up right
            if(this.getCoords().getCol() < col && this.getCoords().getRow() > row) {
                for(int i = 1; i < Math.abs(this.getCoords().getCol() - col); i++) {
                    if(this.getBoard().getPiece(new Coordinate(this.getCoords().getCol() + i, this.getCoords().getRow() - i)) != null) {
                        return true;
                    }
                }
            }

            //down left
            if(this.getCoords().getCol() > col && this.getCoords().getRow() < row) {
                for(int i = 1; i < Math.abs(this.getCoords().getCol() - col); i++) {
                    if(this.getBoard().getPiece(new Coordinate(this.getCoords().getCol() - i, this.getCoords().getRow() + i)) != null) {
                        return true;
                    }
                }
            }

            //down right
            if(this.getCoords().getCol() < col && this.getCoords().getRow() < row) {
                for(int i = 1; i < Math.abs(this.getCoords().getCol() - col); i++) {
                    if(this.getBoard().getPiece(new Coordinate(this.getCoords().getCol() + i, this.getCoords().getRow() + i)) != null) {
                        return true;
                    }
                }
            }

        }
        
        return false;

    }

    
}
