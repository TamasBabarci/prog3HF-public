package view;


import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;


import model.GameEngine;

/**
 * A gépezethez tartozó nézet.
 * @author Y89Q16
 */
public class GameEngineView extends JFrame {

    GameEngine gameEngine;


    /**
     * Ctor, egy ablakot hoz létre mindennel, ami csak kell.
     * @param gameEngine - GameEngine
     */
    public GameEngineView(GameEngine gameEngine) {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Sakk");
        this.setResizable(false);
        this.gameEngine = gameEngine;

        
    }
}
