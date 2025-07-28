package model;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.board.Board;
import view.Exporter;
import view.GameEngineView;
import view.GamePanel;
import view.board.BoardView;

/**
 * Ez az osztály felel a játék/alkalmazás főbb funkcióiért.
 * 
 * @author Y89Q16
 */
public class GameEngine {

    public Board board;
    public BoardView boardView;
    public GamePanel gamePanel;
    public GameEngineView gameEngineView;

    /**
     * Mindent is beállító ctor. Beállítjuk a gépezethez tartozó nézeteket, és a táblát.
     */
    public GameEngine() {
        gamePanel = new GamePanel(this);
        gameEngineView = new GameEngineView(this);
        board = new Board();
        boardView = new BoardView(board);
        board.setBoardView(boardView);
        gameEngineView.add(gamePanel, BorderLayout.WEST);
        gameEngineView.add(boardView, BorderLayout.EAST);
        gameEngineView.pack();
    
        gameEngineView.setVisible(true);
    }

    /**
     * Ezzel a metódussal új táblát hozunk létre a régi helyére, a régi nézetét kitöröljük,
     * és újat adunk hozzá, majd ráfrissítünk a megjelenítésre.
     */
    public void newGame() {
        gameEngineView.remove(boardView);
        board = new Board();
        boardView = new BoardView(board);
        board.setBoardView(boardView);

        gameEngineView.add(boardView, BorderLayout.EAST);
        gameEngineView.revalidate();
        gameEngineView.repaint();
        
    }

    /**
     * Ez a metódus a paraméterben kapott néven létrehoz egy elérési utat, amire elmenti/kiírja
     * a gépezethez tartozó aktuális tábla adatait.
     * 
     * @param fileName - String
     * @throws IOException
     */
    public void saveGame(String fileName) throws IOException {

        String directoryPath = "./saves/";
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("A megadott útvonal nem létezik vagy nem mappa: " + directoryPath);
        }
        
        FileOutputStream f = new FileOutputStream(directoryPath+fileName+".txt");
		ObjectOutputStream out = new ObjectOutputStream(f);
		out.writeObject(this.board);
		out.close();
    }

    /**
     * Ez a metódus a paraméterben kapott elérési úton lévő fájlból beolvassa az abban tárolt táblát
     * és azt hozzárendeli ehhez a gépezethez, frissítve minden nézetet és miegymást.
     * 
     * @param fileName - String
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public void loadGame(String fileName) throws ClassNotFoundException, IOException {

        FileInputStream f = new FileInputStream(fileName);
		ObjectInputStream in = new ObjectInputStream(f);
		board = (Board)in.readObject();
		in.close();

        gameEngineView.remove(boardView);
        boardView = new BoardView(board);
        board.setBoardView(boardView);

        gameEngineView.add(boardView, BorderLayout.EAST);
        gameEngineView.revalidate();
        gameEngineView.repaint();
    }

}
