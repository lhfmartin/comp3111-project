package sample;

import javafx.scene.image.Image;
/**
 * This is the Penguin Class
 * It can replenish some HP (but not more than its initial value) each time it moves. 
 * @author hoyammong
 *
 */
public class Penguin extends Monster{

//	Penguin(){
//        super("Penguin", new Image("penguin.png"));
//    }
	/**
	 * This is the conversion constructor. HP and speed can be set in this constructor
	 * @param HP HP of the Penguin
	 * @param speed speed of the Penguin
	 */
	Penguin(int HP, int speed){
    	super("Penguin", new Image("penguin.png"), 0, 0, HP, speed);
    }
	/**
	 * This is the function for replenish HP
	 * This will increase the HP by 2 point each time it is called until the HP is maximized
	 */
	public void regenHP() {
		if(this.getHP()+2 < this.getMaxHP()) {
			this.setHP(getHP()+2);
		}
		else {
			this.setHP(getMaxHP());
		}
	}
}