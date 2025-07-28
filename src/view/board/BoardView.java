package view.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import controller.Input;
import controller.Move;
import model.board.Board;
import model.pieces.Piece;
import view.pieces.PieceView;


/**
 * A táblához tartozó nézetet megvalósító osztály.
 * @author Y89Q16
 */
public class BoardView extends JPanel {

    private Board board;
    private Input input;

    Map<Piece, PieceView> textures = new HashMap<>();


    /**
     * Ctor, amiben beállítjuk a táblanézet paramétereit a felvett tábla alapján. Valamint Hasheljük a táblához tartozó bábukat
     * az azokra létrehozott nézetekkel.
     * @param board - Board
     */
    public BoardView(Board board) {
        this.board = board;
        this.setPreferredSize(new Dimension(Board.COLS * Board.TILE_SIZE, Board.ROWS * Board.TILE_SIZE));

        this.input = new Input(board);
        this.addMouseListener(input);
        this.addMouseMotionListener(input);

        for(Piece piece: this.getBoard().getPieceList()) {
            textures.put(piece, new PieceView(piece));
        }
        
    }

    /**
     * A kirajzolásért felelős metódus. A páros koordikra fehér, a páratlanokra fekete mezőket vesz fel,
     * a legális mezőket zölddel kiemeli és megjeleníti a bábuk textúráját.
     * @param g - Graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //paint board
        for(int r = 0; r < Board.ROWS; r++) {
            for(int c = 0; c < Board.COLS; c++) {
                g2d.setColor((c+r) % 2 == 0 ? new Color(222, 204, 166) : new Color(148, 91, 20));
                g2d.fillRect(c * Board.TILE_SIZE, r * Board.TILE_SIZE, Board.TILE_SIZE, Board.TILE_SIZE);
            }
        }

        //paint highlights
        if(this.getBoard().getSelectedPiece() != null) {
            for(int r = 0; r < Board.ROWS; r++) {
                for(int c = 0; c < Board.COLS; c++) {
                    if(this.getBoard().isValidMove(new Move(this.getBoard(), this.getBoard().getSelectedPiece(), c, r))) {
                        g2d.setColor(new Color(68, 180, 57, 190));
                        g2d.fillRect(c * Board.TILE_SIZE, r * Board.TILE_SIZE, Board.TILE_SIZE, Board.TILE_SIZE);
                    }
                }
            }
        }

        //paint pieces
        for(Piece piece: this.getBoard().getPieceList()) {
            //piece.paint(g2d);
            textures.get(piece).paint(g2d);

        }


    }

    /**
     * Visszaadja a táblát.
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Beállítja a táblát.
     * @param board - Board
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Lekéri az inputfigyelőt.
     * @return Input
     */
    public Input getInput() {
        return input;
    }

    /**
     * Beállítja az inputfigyelőt.
     * @param input - Input
     */
    public void setInput(Input input) {
        this.input = input;
    }

    /**
     * Lekéri a textúrákat.
     * @return Map<Piece, PieceView>
     */
    public Map<Piece, PieceView> getTextures() {
        return textures;
    }

    /**
     * Megadja a textúrákat tartalmazó HashMapet
     * @param textures - Map<Piece, PieceView>
     */
    public void setTextures(Map<Piece, PieceView> textures) {
        this.textures = textures;
    }
}
