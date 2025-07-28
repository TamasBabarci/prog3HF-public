package controller;



import model.board.Board;
import model.pieces.Coordinate;
import model.pieces.Piece;

/**
 * Ez az osztály reprezentál egy mozgást. Tárolja a mozgás kiinduló és célkoordinátáit, valamint
 * a mozgásra kiválasztott bábut, és a célmezőn lévő bábut, ha az létezik.
 * 
 * @author Y89Q16
 */
public class Move {

    private int oldCol;
    private int oldRow;
    private int newCol;
    private int newRow;

    private Piece selectedPiece;
    private Piece capture;

    /**
     * A konstruktorban megadjuk a táblát, a mozgásra kiválasztott bábut, és a célmező koordinátáit
     * amelyekkel inicializáljuk az adattagokat.
     * 
     * @param board - Board
     * @param selectedPiece - Piece
     * @param newCol - int
     * @param newRow - int
     */
    public Move(Board board, Piece selectedPiece, int newCol, int newRow) {
        this.oldCol = selectedPiece.getCoords().getCol();
        this.oldRow = selectedPiece.getCoords().getRow();
        this.newCol = newCol;
        this.newRow = newRow;
        this.selectedPiece = selectedPiece;
        this.capture = board.getPiece(new Coordinate(newCol, newRow));
    }

    /**
     * Visszaadja a kiinduló oszlopot.
     * @return int
     */
    public int getOldCol() {
        return oldCol;
    }
    
    /**
     * Megadja a kiinduló oszlopot.
     * @param oldCol - int
     */
    public void setOldCol(int oldCol) {
        this.oldCol = oldCol;
    }

    /**
     * Visszaadja a kiinduló sort.
     * @return int
     */
    public int getOldRow() {
        return oldRow;
    }

    /**
     * Megadja a kiinduló sort.
     * @param oldRow - int
     */
    public void setOldRow(int oldRow) {
        this.oldRow = oldRow;
    }

    /**
     * Visszaadja a céloszlopot.
     * @return int
     */
    public int getNewCol() {
        return newCol;
    }

    /**
     * Megadja a céloszlopot.
     * @param newCol - int
     */
    public void setNewCol(int newCol) {
        this.newCol = newCol;
    }

    /**
     * Visszaadja a célsort.
     * @return int
     */
    public int getNewRow() {
        return newRow;
    }

    /**
     * Megadja a célsort.
     * @param newRow - int
     */
    public void setNewRow(int newRow) {
        this.newRow = newRow;
    }

    /**
     * Visszaadja a kiválasztott bábut.
     * @return Piece
     */
    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    /**
     * Megadja a kiválasztott bábut.
     * @param selectedPiece - Piece
     */
    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    /**
     * Visszaadja a célmezőn álló bábut.
     * @return Piece
     */
    public Piece getCapture() {
        return capture;
    }
    
    /**
     * Megadja a célmezőn álló bábut.
     * @param capture - Piece
     */
    public void setCapture(Piece capture) {
        this.capture = capture;
    }
}
