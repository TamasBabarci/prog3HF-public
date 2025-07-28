package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.board.Board;
import model.pieces.Coordinate;
import model.pieces.Piece;


/** 
 * Ez az osztály felel a sakktáblán végrehajtható egérműveletek érzékeléséért,
 * és a megfelelő adatok beolvasásáért.
 * 
 * @author Y89Q16
 */
public class Input extends MouseAdapter{

    Board board;

    /**
     * Egy paraméteres ctor. A bekérd táblára állítja rá az Input figyelőt.
     * 
     * @param board - Board 
     */
    public Input(Board board) {
        this.board = board;
    }

    /**
     * Ez a metódus az egérgomb lenyomására bekére az egér koordinátáit, és az alapján lekér
     * egy bábut, ami a kiválasztott bábu lesz.
     * 
     * @param e - MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / Board.TILE_SIZE;
        int row = e.getY() / Board.TILE_SIZE;

        Piece pieceXY = board.getPiece(new Coordinate(col, row));

        if(pieceXY != null) {
            board.setSelectedPiece(pieceXY);
        }
    }

    /**
     * Ez a metódus az egérgomb elengedésének bekéri a koordinátáját, ami alapján létrehoz egy új mozgást
     * arra a célkoordinátára a táblán a kiválasztott bábuval. Majd ellenőrzi, hogy a valóban van kiválasztva
     * bábu, és, hogy a célkoordináta egy legális lépés. Ha az akkor a bábura meghívja a mozgás metódust, ha nem,
     * akkor a bábut a kiinduló mezőre visszateszi.
     * 
     * @param e - MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        int newCol = e.getX() / Board.TILE_SIZE;
        int newRow = e.getY() / Board.TILE_SIZE;

        if(board.getSelectedPiece() != null) {
            Move move = new Move(board, board.getSelectedPiece(), newCol, newRow);

            if(board.isValidMove(move)) {
                board.makeMove(move);

            } else {
                board.getSelectedPiece().getCoords().setxPos(board.getSelectedPiece().getCoords().getCol() * Board.TILE_SIZE);
                board.getSelectedPiece().getCoords().setyPos(board.getSelectedPiece().getCoords().getRow() * Board.TILE_SIZE);

            }
        }

        board.setSelectedPiece(null);
        board.getBoardView().repaint();
        

    }

    /**
     * Ez a metódus a kiválasztott bábu létezése esetén, tehát ha nem üres mezőre kattintottunk,
     * az egér nyomvatartására és húzására, minden új pozícióban koordinátákat kér le. Ezekhez az
     * egérkoordinátákhoz igazítja a kiválasztott bábut és annak az ikonját, tehát az ikon követni
     * fogja az egeret, amíg azt el nem eresztik.
     * 
     * @param e - MouseEvent
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(board.getSelectedPiece() != null) {
            board.getSelectedPiece().getCoords().setxPos(e.getX() - Board.TILE_SIZE / 2);
            board.getSelectedPiece().getCoords().setyPos(e.getY() - Board.TILE_SIZE / 2);

            board.getBoardView().repaint();
        }
    }


}
