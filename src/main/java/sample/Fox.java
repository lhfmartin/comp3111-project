package sample;

import javafx.scene.image.Image;
/**
 * This is the Fox Class
 * It moves the fastest
 * @author hoyammong
 *
 */
public class Fox extends Monster{

//    Fox(){
//        super("Fox", new Image("fox.png"));
//    }
	/**
	 * This is the conversion constructor. HP and speed can be set in this constructor
	 * @param HP HP of the Fox
	 * @param speed speed of the Fox
	 */
    Fox(int HP, int speed){
    	super("Fox", new Image("fox.png"), 0, 0, HP, speed);
    }



}
