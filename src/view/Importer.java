package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameEngine;

/**
 * A fájlból olvasás nézetéért felelős osztály.
 * @author Y89Q16
 */
public class Importer extends JFrame {

    JComboBox combo;
    private JButton button = new JButton("Betöltés");
    private JLabel label = new JLabel("Fájl név:");
    GameEngine gameEngine;

    /**
     * Ctor, minden is beállít.
     * @param gameEngine - GameEngine
     */
    public Importer(GameEngine gameEngine) {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Játék betöltése");
        this.setResizable(false);  
        setSize(400, 110);

        this.gameEngine = gameEngine;

        String[] fileNames = getFileNames("./saves/");
        this.combo = new JComboBox(fileNames);

        button.addActionListener(new OKButtonListener());
        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());

        p1.add(button, BorderLayout.EAST);
        p1.add(combo, BorderLayout.CENTER);
        p1.add(label, BorderLayout.WEST);
        this.add(p1);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Egy megadott elérési út alapján kigyűjti az abban a mappában lévő fájlneveket.
     * @param directoryPath - String
     * @return String[]
     */
    public static String[] getFileNames(String directoryPath) {
        // Az adott mappa
        File directory = new File(directoryPath);

        // Ellenőrzés, hogy a mappa létezik-e és tényleg mappa-e
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("A megadott útvonal nem létezik vagy nem mappa: " + directoryPath);
            return new String[0]; // Üres tömb, ha nem található mappa
        }

        // A fájlok listázása
        return directory.list((dir, name) -> new File(dir, name).isFile());
    }

    /**
     * Az Importer gombjának figyelője.
     * @author Y89Q16
     */
    class OKButtonListener implements ActionListener {
    	/**
    	 * Gombnyomásra a tárolt gameEngine megfelelő metódusával beolvassa a játékállást.
    	 * @param e - ActionEvent
    	 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
            
            try {
                gameEngine.loadGame("./saves/"+combo.getSelectedItem().toString());
            } catch (ClassNotFoundException | IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
			
		}
		
	}



}
