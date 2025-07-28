package model.pieces;

import model.board.Board;

/**
 * A futó osztálya.
 * @author Y89Q16
 */
public class Bishop extends Piece {
    
	/**
	 * Ctor, ahol állítjuk a futó koordinátáját, csapatát, tábláját és a super classban
	 * megadjuk a futó példány nevét. 
	 * @param coords - Coordinate
	 * @param isWhite - boolean
	 * @param board - Board
	 */
    public Bishop(Coordinate coords, boolean isWhite, Board board) {
        super("Bishop", coords, isWhite, board);
     
    }

    /**
     * Megadja, hogy a futó milyen kényszerek mellett léphet. 
     * 
     * @param col - int
     * @param row - int
     * @return boolean
     */
    @Override
    public boolean isValidMovement(int col, int row) {
        return Math.abs(this.getCoords().getCol() - col) == Math.abs(this.getCoords().getRow() - row);
    }

    /**
     * Minden átlóban csekkoljuk, hogy vane akadályozó tényező egy lépés során. Ha van, akkor azt nem ugorhatjuk át.
     * @param col - int
     * @param row - int
     * @return boolean
     */
    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
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

        return false;
    }
}
