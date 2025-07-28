package model.pieces;


import java.io.Serializable;

// import javax.imageio.ImageIO;

import model.board.Board;

/**
 * Absztrakt osztály, amelyből a többi bábu leszármazik. Megadja az alap sémát, amire egy bábunak
 * illeszkednie kell.
 * 
 * @author Y89Q16
 */
public abstract class Piece implements Serializable{

    private String name;
    private Coordinate coords;
    private boolean isWhite;
    private Board board;
    
    private boolean isFirstMove = true;

    /**
     * Ctor, amely beállítja a bábu nevét, koordinátáit, csapatát és a tábláját. Protected, mert
     * azt akarjuk, hogy csak a leszármazottak érjék el.
     * @param name - String
     * @param coords - Coordinate
     * @param isWhite - boolean
     * @param board - Board
     */
    protected Piece(String name, Coordinate coords, boolean isWhite, Board board) {
        this.name = name;
        this.coords = coords;
        this.isWhite = isWhite;
        this.board = board;
    }

    /**
     * Visszaadja a nevet.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Megadja a nevet.
     * @param name - String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Visszaadja a koordit.
     * @return Coordinate
     */
    public Coordinate getCoords() {
        return coords;
    }

    /**
     * Megadja a koordit.
     * @param coords - Coordinate
     */
    public void setCoords(Coordinate coords) {
        this.coords = coords;
    }

    /**
     * Visszaadja, hogy fehére tehát a csapatát.
     * @return boolean
     */
    public boolean isWhite() {
        return isWhite;
    }

    /**
     * Visszaadja a táblát.
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Visszaadja, hogy először lépett_e
     * @return boolean
     */
    public boolean isFirstMove() {
        return isFirstMove;
    }
    
    /**
     * Megadja, hogy először lépette.
     * @param isFirstMove - boolean
     */
    public void setFirstMove(boolean isFirstMove) {
        this.isFirstMove = isFirstMove;
    }


    /**
     * Absztrakt metódus a bábu lépésmódjának meghatározására.
     * @param col - int
     * @param row - int
     * @return boolean
     */
    public abstract boolean isValidMovement(int col, int row);

    /**
     * Absztrakt metódus arra, hogy a bábu több mezőt érintő egyenes lépése esetén
     * vane akadály.
     * @param col - int
     * @param row - int
     * @return boolean
     */
    public abstract boolean moveCollidesWithPiece(int col, int row);

}
