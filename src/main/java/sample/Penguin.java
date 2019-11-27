package sample;

import javafx.scene.image.Image;

public class Penguin extends Monster{

//	Penguin(){
//        super("Penguin", new Image("penguin.png"));
//    }
	
	Penguin(int HP, int speed){
    	super("Penguin", new Image("penguin.png"), 0, 0, HP, speed);
    }
	public void regenHP() {
		if(this.getHP()+2 < this.getMaxHP()) {
			this.setHP(getHP()+2);
		}
		else {
			this.setHP(getMaxHP());
		}
	}
}