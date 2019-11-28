package sample;

import javafx.scene.image.Image;
/**
 * This is the Unicorn Class
 * It has more HP than other monsters. 
 * @author hoyammong
 *
 */
public class Unicorn extends Monster{

//    Unicorn(){
//        super("Unicorn", new Image("unicorn.png"));
//    }
	
	/**
	 * This is the conversion constructor. HP and speed can be set in this constructor
	 * @param HP HP of the Unicorn
	 * @param speed speed of the Unicorn
	 */
    Unicorn(int HP, int speed){
    	super("Unicorn", new Image("unicorn.png"), 0, 0, HP, speed);
    }



}
