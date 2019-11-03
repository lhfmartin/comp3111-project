package sample;

import javafx.scene.image.Image;

public class Penguin extends Monster{

	Penguin(){
        super("Penguin", new Image("penguin.png"));
    }
	
	Penguin(int HP, int speed){
    	super("Penguin", new Image("penguin.png"), 0, 0, HP, speed);
    }



}