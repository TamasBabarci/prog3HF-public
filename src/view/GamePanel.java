package view;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JPanel;

import model.GameEngine;

/**
 * A gameEngine funkcióknak gombokat megfeleltető osztály.
 * @author Y89Q16
 */
public class GamePanel extends JPanel{

    JButton newGame, saveGame, loadGame;

    GameEngine gameEngine;

    /**
     * Ctor, minden gombra beállítjuk, amit be kell.
     * @param gameEngine - GameEngine
     */
    public GamePanel(GameEngine gameEngine) {

        this.gameEngine = gameEngine;

        setLayout(new GridLayout(3, 1));

        newGame = new JButton("New Game");
        newGame.setBounds(50, 50, 100, 20);
        newGame.addActionListener(new NewGameButtonListener());
        add(newGame);

        saveGame = new JButton("Save Game");
        saveGame.addActionListener(new SaveGameButtonListener());
        add(saveGame);

        loadGame = new JButton("Load Game");
        loadGame.addActionListener(new LoadGameButtonListener());
        add(loadGame);
    }

    /**
     * A GamePanel New Game gombjának figyelője
     * @author Y89Q16
     */
    class NewGameButtonListener implements ActionListener {
    	/**
    	 * Meghívja a gépezet új játékot csináló metódusát
    	 * @param e - ActionEvent
    	 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			System.out.println("New");
			gameEngine.newGame();
			
		}
		
	}

    /**
     * A GamePanel Save Game gombjának figyelője
     * @author Y89Q16
     */
    class SaveGameButtonListener implements ActionListener {
    	/**
    	 * Létrehoz egy Exportert a gépezetre
    	 * @param e - ActionEvent
    	 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Save");
			Exporter exporter = new Exporter(gameEngine);
			
		}
		
	}

    /**
     * A GamePanel Load Game gombjának figyelője
     * @author Y89Q16
     */
    class LoadGameButtonListener implements ActionListener {
    	/**
    	 * Létrehoz egy Importert a gépezetre
    	 * @param e - ActionEvent
    	 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			System.out.println("Load");
			Importer importer = new Importer(gameEngine);
			
		}
		
	}
}
