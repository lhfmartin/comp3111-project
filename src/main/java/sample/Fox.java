package sample;

import javafx.scene.image.Image;

public class Fox extends Monster{

//    Fox(){
//        super("Fox", new Image("fox.png"));
//    }
	
    Fox(int HP, int speed){
    	super("Fox", new Image("fox.png"), 0, 0, HP, speed);
    }



}
