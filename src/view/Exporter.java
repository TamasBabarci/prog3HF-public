package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.GameEngine;


/**
 * A fájlbaírás nézetéért felelős osztály
 * @author Y89Q16
 */
public class Exporter extends JFrame {

    private JTextField saveTextField = new JTextField(20);
    private JButton button = new JButton("Mentés");
    private JLabel label = new JLabel("Fájl név:");
    GameEngine gameEngine;

    /**
     * Ctor, létrehoz egy új ablakot minden földi jóval a textfieldtől kezdve a buttonig stb.
     * @param gameEngine - GameEngine
     */
    public Exporter(GameEngine gameEngine) {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Játék mentése");
        this.setResizable(false);  
        setSize(400, 110);

        this.gameEngine = gameEngine;

        button.addActionListener(new OKButtonListener());
        JPanel p1 = new JPanel();
        p1.setLayout(new BorderLayout());

        p1.add(button, BorderLayout.EAST);
        p1.add(saveTextField, BorderLayout.CENTER);
        p1.add(label, BorderLayout.WEST);
        this.add(p1);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Az Exporter gombjának figyelője
     * @author Y89Q16
     */
    class OKButtonListener implements ActionListener {
    	/**
    	 * Gombnyomásra a tárolt gameEngine megfelelő metódusával elmenti a játékállást.
    	 * @param e - ActionEvent
    	 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
            try {
                gameEngine.saveGame(saveTextField.getText());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
			
		}
		
	}



}
