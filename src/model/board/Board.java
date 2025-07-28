package model.board;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controller.Move;
import model.CheckScanner;
import model.pieces.Bishop;
import model.pieces.Coordinate;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Piece;
import model.pieces.Queen;
import model.pieces.Rook;
import view.board.BoardView;
import view.pieces.PieceView;

/**
 * Ez a legleglegfőbb osztályunk, ami mindent is csinál. Lényegében a játék logikai részéért felel, tehát olyan
 * funkciókért, mint a lépések, a bábuk megfelelő nyilvántartása stb.
 * 
 * @author Y89Q16
 */
public class Board implements Serializable {
    
    public static final int COLS = 8;
    public static final int ROWS = 8;
    public static final int TILE_SIZE = 85;

    private List<Piece> pieceList;
    private Piece selectedPiece;
    private transient BoardView boardView;
    private int enPassantTile = -1;

    private CheckScanner checkScanner;

    private boolean isWhiteToMove = true;
    private boolean isGameOver = false;

    /**
     * Ctor, legenerálja a bábukat, valamint beállít egy sakkvizsgálót erre a táblára.
     */
    public Board() {
        this.pieceList = new ArrayList<>();
        this.generatePieces();
        this.setCheckScanner(new CheckScanner(this));
    }

    /*
     * A létrehozott tárolóhoz egyesével bábukat rendel hozzá.
     */
    public void generatePieces() {
        this.getPieceList().add(new Knight(new Coordinate(1, 0), false, this));
        this.getPieceList().add(new Knight(new Coordinate(6, 0), false, this));
        this.getPieceList().add(new Knight(new Coordinate(1, 7), true, this));
        this.getPieceList().add(new Knight(new Coordinate(6, 7), true, this));

        this.getPieceList().add(new King(new Coordinate(4, 0), false, this));
        this.getPieceList().add(new King(new Coordinate(4, 7), true, this));
        this.getPieceList().add(new Queen(new Coordinate(3, 0), false, this));
        this.getPieceList().add(new Queen(new Coordinate(3, 7), true, this));

        this.getPieceList().add(new Rook(new Coordinate(0, 0), false, this));
        this.getPieceList().add(new Rook(new Coordinate(7, 0), false, this));
        this.getPieceList().add(new Rook(new Coordinate(0, 7), true, this));
        this.getPieceList().add(new Rook(new Coordinate(7, 7), true, this));

        this.getPieceList().add(new Bishop(new Coordinate(2, 0), false, this));
        this.getPieceList().add(new Bishop(new Coordinate(5, 0), false, this));
        this.getPieceList().add(new Bishop(new Coordinate(2, 7), true, this));
        this.getPieceList().add(new Bishop(new Coordinate(5, 7), true, this));

        for(int c = 0; c < Board.COLS; c++) {
            this.getPieceList().add(new Pawn(new Coordinate(c, 1), false, this));
            this.getPieceList().add(new Pawn(new Coordinate(c, 6), true, this));
        }
    }

    /**
     * Visszaadja a bábuk listáját.
     * @return List<Piece>
     */
    public List<Piece> getPieceList() {
        return pieceList;
    }
    
    /**
     * Beállítja a bábuk listáját.
     * @param pieceList - List<Piece>
     */
    public void setPieceList(List<Piece> pieceList) {
        this.pieceList = pieceList;
    }
    
    /**
     * A mozgás által kiválasztott bábut adja vissza.
     * @return Piece
     */
    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    /**
     * A mozgáshoz kiválasztott bábut állítja.
     * @param selectedPiece - Piece
     */
    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    /**
     * A táblához tartozó nézetet adja vissza.
     * @return BoardView
     */
    public BoardView getBoardView() {
        return boardView;
    }

    /**
     * A táblához tartozó nézetet állítja.
     * @param boardView
     */
    public void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }

    /**
     * Az En Passant feltételeinek megfelelő mezőt adja vissza.
     * @return int
     */
    public int getEnPassantTile() {
        return enPassantTile;
    }

    /**
     * Az En Passant mezőjét állítja.
     * @param enPassantTile - int
     */
    public void setEnPassantTile(int enPassantTile) {
        this.enPassantTile = enPassantTile;
    }

    /**
     * A sakkvizsgálót visszaadja.
     * @return CheckScanner
     */
    public CheckScanner getCheckScanner() {
        return checkScanner;
    }

    /**
     * A sakkvizsgálót állatja.
     * @param checkScanner
     */
    public void setCheckScanner(CheckScanner checkScanner) {
        this.checkScanner = checkScanner;
    }

    /**
     * Visszaadja egy bináris értékben, hogy éppen melyik csapat van soron.
     * @return boolean
     */
    public boolean isWhiteToMove() {
        return isWhiteToMove;
    }

    /**
     * Beállítja, hogy ki jön éppen.
     * @param isWhiteToMove - boolean
     */
    public void setWhiteToMove(boolean isWhiteToMove) {
        this.isWhiteToMove = isWhiteToMove;
    }

    /**
     * Megmondja, hogy végetérte a játék.
     * @return boolean
     */
    public boolean isGameOver() {
        return isGameOver;
    }

    /**
     * Ez igazából úgyszintén csak jelképes létezésű metódus.
     * @param isGameOver boolean
     */
    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    
    /**
     * Visszaadja az adott koordinátán álló bábut-
     * @param coords - Coordinate
     * @return Piece
     */
    public Piece getPiece(Coordinate coords) {
        for(Piece piece: this.getPieceList()){
            if(piece.getCoords().equals(coords))
                return piece;
        }
        return null;
    }

    /**
     * Ellenőrzi, hogy a kiválasztott bábu a célmezőnek szánt helyre léphete.
     * @param move - Move
     * @return boolean
     */
    public boolean isValidMove(Move move) {

        if(this.isGameOver()) {
            return false;
        }

        if(move.getSelectedPiece().isWhite() != this.isWhiteToMove()) {
            return false;
        }

        if(this.sameTeam(move.getSelectedPiece(), move.getCapture())) {
            return false;
        }

        if(!(move.getSelectedPiece().isValidMovement(move.getNewCol(), move.getNewRow()))){
            return false;
        }

        if(move.getSelectedPiece().moveCollidesWithPiece(move.getNewCol(), move.getNewRow())){
            return false;
        }

        if(checkScanner.isKingChecked(move)) {
            return false;
        }

        return true;
    }

    /**
     * Megadja, hogy két bábu egy csapatban vane.
     * @param p1 - Piece
     * @param p2 - Piece
     * @return boolean
     */
    public boolean sameTeam(Piece p1, Piece p2) {
        if(p1 == null || p2 == null) {
            return false;
        }
        return p1.isWhite() == p2.isWhite();
    }

    /**
     * Ellenőrzi, hogy végetérte a játék, és az alapján jelzéseket ad felugró üzenetek formájában.
     */
    private void updateGameState() {
        Piece king = findKing(this.isWhiteToMove());
        if(this.getCheckScanner().isGameOver(king)) {
            if(this.getCheckScanner().isKingChecked(new Move(this, king, king.getCoords().getCol(), king.getCoords().getRow()))) {
                System.out.println(this.isWhiteToMove() ? "Fekete nyert!" : "Fehér nyert!");
                String message = this.isWhiteToMove() ? "Fekete nyert!" : "Fehér nyert!";
                JOptionPane.showMessageDialog(boardView, message, "Game Over", JOptionPane.PLAIN_MESSAGE);
            } else {
                System.out.println("Stalemate");
                String message = "Döntetlen!";
                JOptionPane.showMessageDialog(boardView, message, "Game Over", JOptionPane.PLAIN_MESSAGE);
                this.setGameOver(true);
            }
            
        } 
    }


    /**
     * Eldönti, hogy milyen lépést kell végrehajtani, valamint biztosítja, hogy felváltva következzenek a játékosok.
     *
     * @param move - Move
     */
    public void makeMove(Move move) {

        if(move.getSelectedPiece().getName().equals("Pawn")) {
            this.movePawn(move);
        } else if(move.getSelectedPiece().getName().equals("King")) {
            moveKing(move);
        }
        this.makeStep(move);
        this.setWhiteToMove(!isWhiteToMove);

        updateGameState();

    }

    /**
     * Külön eljárás a királynak a sáncolás miatt. Ugye megnézzük, hogy 2 mezőnyit lépe, és hogy melyik irányba,
     * és az alapján mozgatjuk a releváns bástyát.
     * @param move - Move
     */
    private void moveKing(Move move) {
        if(Math.abs(move.getSelectedPiece().getCoords().getCol() - move.getNewCol()) == 2) {
            Piece rook;

            if(move.getSelectedPiece().getCoords().getCol() < move.getNewCol()) {
                rook = this.getPiece(new Coordinate(7, move.getSelectedPiece().getCoords().getRow()));
                rook.getCoords().setCol(5);
            } else {
                rook = this.getPiece(new Coordinate(0, move.getSelectedPiece().getCoords().getRow()));
                rook.getCoords().setCol(3);
            }

            rook.getCoords().setxPos(rook.getCoords().getCol() * Board.TILE_SIZE);
        }
    }

    /**
     * A paraszt különleges lépéseit figyelembe vevő metódus, ahol is először megnézzük, hogy milyen színnel mozgunk.
     * Majd megvizsgáljuk, hogy En Passant mezőre lépünke, amikor is átállítjuk a leütni kívánt bábu mezőjét, a mozgásban
     * az En Passant számára megfelelő bábura.
     * Továbbá vizsgálja, hogy nem mi követtünke el olyan lépést, ami miatt En Passant lépést hajhatnak rajtunk végre.
     * Valamint csekkolja, hogy a megfelelő szín szerint előléptetésben részesüle a gyalogunk vagy sem.
     * @param move - Move
     */
    private void movePawn(Move move) {
        //en passant
        int colorIndex = move.getSelectedPiece().isWhite() ? 1 : -1;

        if(this.getTileNum(move.getNewCol(), move.getNewRow()) == this.getEnPassantTile()) {
            move.setCapture(this.getPiece(new Coordinate(move.getNewCol(), move.getNewRow() + colorIndex)));
        }

        if(Math.abs(move.getSelectedPiece().getCoords().getRow() - move.getNewRow()) == 2) {
            this.setEnPassantTile(getTileNum(move.getNewCol(), move.getNewRow() + colorIndex));
        } else {
            this.setEnPassantTile(-1);
        }

        //promotions
        colorIndex = move.getSelectedPiece().isWhite() ? 0 : 7;

        if(move.getNewRow() == colorIndex) {
            this.promotePawn(move);
        }

        this.makeStep(move);

    }

    /**
     * Ha beérünk egy gyaloggal, akkor annak a helyén létrehozunk egy Vezért, majd töröljük a gyalogot, ütés esetén még a leütött bábut is.
     * Majd hozzáadjuk az új bábut a táblához és frissítjük a nézeteket.
     * @param move - Move
     */
    private void promotePawn(Move move) {
        this.getPieceList().add(new Queen(new Coordinate(move.getNewCol(), move.getNewRow()), move.getSelectedPiece().isWhite(), this));
        this.capture(move.getSelectedPiece());
        this.capture(move.getCapture());
        Piece piece = this.getPiece(new Coordinate(move.getNewCol(), move.getNewRow()));
        System.out.println(piece.getName());
        this.getBoardView().getTextures().put(piece, new PieceView(piece));
        
    }

    /**
     * A paraméterben kapott lépésobjektum alapján végrehajtja a lépést a kiinduló mezőről, a célmezőre
     * a kiválasztott bábuval, és egyben leüti a célbábut, ha van olyan.
     * @param move - Move
     */
    private void makeStep(Move move) {
        move.getSelectedPiece().getCoords().setCol(move.getNewCol());
        move.getSelectedPiece().getCoords().setRow(move.getNewRow());

        move.getSelectedPiece().getCoords().setxPos(move.getNewCol() * Board.TILE_SIZE);
        move.getSelectedPiece().getCoords().setyPos(move.getNewRow() * Board.TILE_SIZE);
        
        move.getSelectedPiece().setFirstMove(false);

        this.capture(move.getCapture());
    }

    /**
     * A konkrét ütés metódusa, amikor is a paraméterben kapott bábut töröljük a nyilvántartásból.
     * @param piece - Piece
     */
    public void capture(Piece piece) {
        this.getPieceList().remove(piece);
    }

    /**
     * Egy koordináta alapján visszaad egy mezőértéket, ami azt a mezőt egyedien azonosítja.
     * @param col int 
     * @param row int
     * @return int
     */
    public int getTileNum(int col, int row) {
        return row * Board.ROWS + col;
    }

    /**
     * Visszaadja a színnek megfelelő királyt.
     * @param isWhite - boolean
     * @return Piece
     */
    public Piece findKing(boolean isWhite) {
        for(Piece piece: pieceList) {
            if(isWhite == piece.isWhite() && piece.getName().equals("King")) {
                return piece;
            }
        }
        return null;
    }




}
