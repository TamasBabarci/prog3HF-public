package model;

import java.io.Serializable;

import controller.Move;
import model.board.Board;
import model.pieces.Coordinate;
import model.pieces.Piece;

/**
 * Ez az osztály figyeli azt, hogy az éppen lépésre használt bábu színével egyező színű
 * király sakkba kerül vagy sem, illetve, hogy eleve abban van vagy sem.
 * 
 * @author Y89Q16
 */
public class CheckScanner implements Serializable{
    /**
	 * Ser num
	 */
	private static final long serialVersionUID = 1L;
	private Board board;

	/**
	 * Ctor, ami a sakkvizsgálót ráállítja a paraméterben beadott táblára.
	 * 
	 * @param board - Board
	 */
    public CheckScanner(Board board) {
        this.board = board;
    }

    /**
     * Az osztály fő metódusa, ami a mozgásban kiválasztott bábu színével egyező királyt megtalálja
     * annak adatait elmenti, illetve figyeli, hogyha a kiválasztott bábu a király, majd megnézi, hogy
     * a mozgás célmezőjére lépés esetén a király sakk alatt lesz vagy sem.
     * 
     * @param move - Move
     * @return boolean
     */
    public boolean isKingChecked(Move move) {
        Piece king = this.getBoard().findKing(move.getSelectedPiece().isWhite()); //a kiválasztott bábuhoz találjuk meg uazt a színű királyt
        assert king != null;

        int kingCol = king.getCoords().getCol();
        int kingRow = king.getCoords().getRow();

        //megnézzük, hogy a kiválasztott bábu király volt-e
        if(this.getBoard().getSelectedPiece() != null && this.getBoard().getSelectedPiece().getName().equals("King")) {
            kingCol = move.getNewCol();
            kingRow = move.getNewRow();
        }

        //Ha választott bábu a királyunk, akkor ugye megnézzük, hogy ahová le akarjuk tenni, ott sakkban lenne-e
        //Ha nem a királyunk, akkor megnézzük, hogy a célmezőre lépéssel nem tesszük-e ki sakknak a királyunkat

        return hitByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 0, 1)  //up
            || hitByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 1, 0)  //right
            || hitByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 0, -1)        //down
            || hitByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, -1, 0)        //left

            || hitByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, -1, -1)      //up left
            || hitByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, +1, -1)      //up right
            || hitByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, +1, +1)      //down right
            || hitByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, -1, +1)      //down left

            || hitByKnight(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow)
            || hitByPawn(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow)
            || hitByKing(king, kingCol, kingRow)
        ;
    }

    /**
     * Ez a metódus azt vizsgálja, hogy az adott vmiDir irányban vane ellenséges bástya, aki fenyegeti
     * a mozgásra kiválasztott bábu királyát a célmezőre lépés esetén.
     * 
     * @param col - int céloszlop
     * @param row - int célsor
     * @param king - Piece a saját királyunk
     * @param kingCol - int a király oszlopa
     * @param kingRow - int a király sora
     * @param colDir - int az oszlop iránymutatója
     * @param rowDir - int a sor iránymutatója (a numerikus és a megjelenítési értelmezés eltér)
     * @return boolean
     */
    public boolean hitByRook(int col, int row, Piece king, int kingCol, int kingRow, int colDir, int rowDir) {
        for(int i = 1; i < 8; i++) {
            if(kingCol + (i * colDir) == col && kingRow + (i * rowDir) == row) {
                break;
            }

            Piece piece = this.getBoard().getPiece(new Coordinate(kingCol + (i * colDir), kingRow + (i * rowDir)));

            if(piece != null && piece != this.getBoard().getSelectedPiece()) {
                if(!(this.getBoard().sameTeam(piece, king)) && ((piece.getName().equals("Rook")) || piece.getName().equals("Queen"))) {
                    return true;
                }
                break;
            }
        }

        return false;
    }

    /**
     * Ez a metódus azt vizsgálja, hogy az adott vmiDir irányban vane ellenséges futó, aki fenyegeti
     * a mozgásra kiválasztott bábu királyát a célmezőre lépés esetén.
     * 
     * @param col - int
     * @param row - int
     * @param king - Piece
     * @param kingCol - int
     * @param kingRow - int
     * @param colDir - int
     * @param rowDir - int
     * @return boolean
     */
    public boolean hitByBishop(int col, int row, Piece king, int kingCol, int kingRow, int colDir, int rowDir) {
        for(int i = 1; i < 8; i++) {
            if(kingCol - (i * colDir) == col && kingRow - (i * rowDir) == row) {
                break;
            }

            Piece piece = this.getBoard().getPiece(new Coordinate(kingCol - (i * colDir), kingRow - (i * rowDir)));

            if(piece != null && piece != this.getBoard().getSelectedPiece()) {
                if(!(this.getBoard().sameTeam(piece, king)) && ((piece.getName().equals("Bishop")) || piece.getName().equals("Queen"))) {
                    return true;
                }
                break;
            }
        }

        return false;
    }

    /**
     * Ez a metódus azt vizsgálja, hogy lépés esetén a királyunk mezőjétől L alakban lévő mezőkön vane ló, aki
     * minket fenyeget. Ennek a metódusnak kb. csak akkor van értelme, ha királlyal lépünk.
     * 
     * @param col - int 
     * @param row - int
     * @param king - Piece
     * @param kingCol - int
     * @param kingRow - int
     * @return boolean
     */
    private boolean hitByKnight(int col, int row, Piece king, int kingCol, int kingRow) {
        return checkKnight(this.getBoard().getPiece(new Coordinate(kingCol - 1, kingRow - 2)), king, col, row)
            || checkKnight(this.getBoard().getPiece(new Coordinate(kingCol + 1, kingRow - 2)), king, col, row)
            || checkKnight(this.getBoard().getPiece(new Coordinate(kingCol + 2, kingRow - 1)), king, col, row)
            || checkKnight(this.getBoard().getPiece(new Coordinate(kingCol + 2, kingRow + 1)), king, col, row)
            || checkKnight(this.getBoard().getPiece(new Coordinate(kingCol + 1, kingRow + 2)), king, col, row)
            || checkKnight(this.getBoard().getPiece(new Coordinate(kingCol - 1, kingRow + 2)), king, col, row)
            || checkKnight(this.getBoard().getPiece(new Coordinate(kingCol - 2, kingRow + 1)), king, col, row)
            || checkKnight(this.getBoard().getPiece(new Coordinate(kingCol - 2, kingRow - 1)), king, col, row)
            ;
    }

    /**
     * Ez a metódus azt vizsgálja, hogy a van-e ló, aki az adott célkoordinátán minket fenyegetne.
     * 
     * @param p - Piece
     * @param k - Piece
     * @param col - int
     * @param row - int
     * @return boolean
     */
    private boolean checkKnight(Piece p, Piece k, int col, int row) {
        return p != null && !this.getBoard().sameTeam(p, k) && p.getName().equals("Knight") 
            && !(p.getCoords().getCol() == col && p.getCoords().getRow() == row);
    }

    /**
     * Ez a metódus azt vizsgálja, hogy a célmezőre lépést fenyegetie az ellenséges király.
     * Ennek is csak a királlyal való lépéskor van értelme. Szépen körbevizsgáljuk a célmezőt.
     * 
     * @param king - Piece
     * @param kingCol - int
     * @param kingRow - int
     * @return boolean
     */
    private boolean hitByKing(Piece king, int kingCol, int kingRow) {
        return chechKing(this.getBoard().getPiece(new Coordinate(kingCol - 1, kingRow - 1)), king)
            || chechKing(this.getBoard().getPiece(new Coordinate(kingCol + 1, kingRow - 1)), king)
            || chechKing(this.getBoard().getPiece(new Coordinate(kingCol, kingRow - 1)), king)
            || chechKing(this.getBoard().getPiece(new Coordinate(kingCol - 1, kingRow)), king)
            || chechKing(this.getBoard().getPiece(new Coordinate(kingCol + 1, kingRow)), king)
            || chechKing(this.getBoard().getPiece(new Coordinate(kingCol - 1, kingRow + 1)), king)
            || chechKing(this.getBoard().getPiece(new Coordinate(kingCol + 1, kingRow + 1)), king)
            || chechKing(this.getBoard().getPiece(new Coordinate(kingCol, kingRow + 1)), king)
        ;
    }

    /**
     * Ez a metódus azt vizsgálja, hogy egy konkrét célmezőről lekért bábu ellenséges királye.
     * 
     * @param p - Piece
     * @param k - Piece
     * @return
     */
    private boolean chechKing(Piece p, Piece k) {
        return p != null && !(this.getBoard().sameTeam(p, k)) && p.getName().equals("King");
    }  

    /**
     * Visszaadja a táblát.
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Megadja a sakkvizsgálóhoz tartozó táblát.
     * @param board - Board
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Ez a metódus a mi királyunk irányától ellentétesen vizsgálja, hogy a király koordinátáit fenyegetie
     * gyalog.
     * 
     * @param col - int
     * @param row - int
     * @param king - Piece
     * @param kingCol - int
     * @param kingRow - int
     * @return boolean
     */
    private boolean hitByPawn(int col, int row, Piece king, int kingCol, int kingRow) {
        int colorIndex = king.isWhite() ? -1 : 1;
        return checkPawn(this.getBoard().getPiece(new Coordinate(kingCol + 1, kingRow + colorIndex)), king, col, row)
            || checkPawn(this.getBoard().getPiece(new Coordinate(kingCol - 1, kingRow + colorIndex)), king, col, row)
        ;
    }

    /**
     * A konkrét vizsgálatot végző metódus.
     * 
     * @param p - Piece
     * @param k - Piece
     * @param col - int
     * @param row - int
     * @return boolean
     */
    private boolean checkPawn(Piece p, Piece k, int col, int row) {
        return p != null && !(this.getBoard().sameTeam(p, k)) && p.getName().equals("Pawn")
            && !(p.getCoords().getCol() == col && p.getCoords().getRow() == row);
    }  

    /**
     * Ez a metódus azt vizsgálja, hogy vane még legális lépése a mi királyunk csapatának.
     * Ha nincs, akkor a játéknak vége.
     *   
     * @param king - Piece
     * @return boolean
     */
    public boolean isGameOver(Piece king) {
        for(Piece piece: this.getBoard().getPieceList()) {
            if(this.getBoard().sameTeam(piece, king)) {
                this.getBoard().setSelectedPiece(piece == king ? king: null);

                for(int r = 0; r < Board.ROWS; r++) {
                    for(int c = 0; c < Board.COLS; c++) {
                        Move move = new Move(this.getBoard(), piece, c, r);
                        if(this.getBoard().isValidMove(move)) { //találtunk valid move-ot, tehát megy tovább
                            return false;
                        }
                    } 
                }
            }
        }
        return true;
    }
    
}
