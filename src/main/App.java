package main;

import model.GameEngine;


/**
 * Ez az osztály a program kiinduló pontja, ez tartalmazza a main metódust,
 * ami létrehozza magát a játék gépezetet.
 * 
 * @author Y89Q16
 */
public class App {
	/**
	 * Ez a main metódus, létrehoz egy új GameEnginet.
	 * 
	 * @param args - String[]
	 */
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        
    }   
}
