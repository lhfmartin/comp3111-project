package sample;

import javafx.scene.image.Image;

public class Unicorn extends Monster{

    Unicorn(){
        super("Unicorn", new Image("unicorn.png"));
    }
	
    Unicorn(int HP, int speed){
    	super("Unicorn", new Image("unicorn.png"), 0, 0, HP, speed);
    }



}
