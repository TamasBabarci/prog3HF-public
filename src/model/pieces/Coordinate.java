package model.pieces;

import java.io.Serializable;

import model.board.Board;

/**
 * A koordináták és pozíciók tárolására, lekérésére használt osztály. Azért létezik, hogy ezen
 * adatok egy helyen legyenek tárolva.
 * 
 * @author Y89Q16
 */
public class Coordinate implements Serializable { 
    private int col;
    private int row;
    private int xPos; 
    private int yPos;

    /**
     * Ctor, ami kap egy oszlopot és sort, amiből kiszámolja a pozíció adatokat.
     * @param col - int
     * @param row - int
     */
    public Coordinate(int col, int row) {
        this.col = col;
        this.row = row;
        this.xPos = col * Board.TILE_SIZE;
        this.yPos = row * Board.TILE_SIZE;
    }

    /**
     * Visszaadja az oszlopot.
     * @return int
     */
    public int getCol() {
        return col;
    }

    /**
     * Megadja az oszlopot.
     * @param col - int
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Visszaadja a sort.
     * @return int
     */
    public int getRow() {
        return row;
    }

    /**
     * Megadja a sort.
     * @param row - int
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Visszaadja az x pozíciót.
     * @return int
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Megadja az x pozíciót.
     * @param xPos - int
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Visszaadja az y pozíciót.
     * @return int
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * Megadja az y pozíciót.
     * @param yPos - int
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * Tartalmi egyenlőséget vizsgál két Coordinate objektum között. 
     * @param obj - Object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { 
            return true; // Ha ugyanaz az objektum, akkor nyilván egyenlők
        }
        if (obj == null || getClass() != obj.getClass()) { 
            return false; // Null érték vagy másik osztály esetén nem egyenlő
        }
        Coordinate other = (Coordinate) obj; // Típus-konverzió, mivel tudjuk, hogy azonos osztály
        return this.col == other.col && this.row == other.row; // Az oszlop és sor értékek összehasonlítása
    }
    
    /**
     * Hashkód, mert csak.
     * @return int
     */
    @Override
    public int hashCode() {
        return 31 * col + row; 
    }
}
