package sample;

import javafx.scene.image.Image;

/***
 * This is the base class of all the Monster.
 * @author hoyammong
 *
 */
public abstract class Monster {
    private String name;
    private Image image;
    private int x;
    private int y;
    private int maxHP;
    private int HP;
    private int speed;
    
//    public Monster(String name, Image image) {
//        this.name = name;
//        this.image = image;
//    }
/***
 * This is the conversion constructor which accept name, image, x coordinate, y coordinate, maximum HP and speed
 * @param name name of the monster
 * @param image image of the monster
 * @param x x coordinate
 * @param y y coordinate
 * @param maxHP maximum HP
 * @param speed speed of the monster
 */
    public Monster(String name, Image image, int x, int y, int maxHP, int speed) {
    	this.name = name;
    	this.image = image;
    	this.x = x;
    	this.y = y;
  		this.maxHP = maxHP;
  		this.HP = maxHP;
  		this.speed = speed;
    }
    
    /***
     * This returns the Name of the monster
     * @return name of the monster
     */
    public String getName() {
        return name;
    }
    /***
     * This returns the image of the monster
     * @return image of the monster
     */
    public Image getImage() {
        return image;
    }
    
    /***
     * This gets the x coordinate of the monster
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /***
     * This gets the y coordinate of the monster
     * @return y coordinate
     */
    public int getY() {
        return y;
    }
    
    /***
     * This gets the maximum HP of the monster
     * @return maximum HP
     */
    public int getMaxHP() {
        return maxHP;
    }
    
    /***
     * This gets the current HP of the monster
     * @return HP
     */
    public int getHP() {
        return HP;
    }
    
    /***
     * This gets the speed of the monster
     * @return speed
     */
    public int getSpeed() {
        return speed;
    }
    
    /***
     * This sets the x coordinate of the monster
     * @param x x coordinate 
     */
    public void setX(int x) {
    	this.x = x;
    }

    /***
     * This sets the y coordinate of the monster
     * @param y y coordinate 
     */
    public void setY(int y) {
    	this.y = y;
    }
    
    /***
     * This sets the HP of the monster
     * @param HP HP
     */
    public void setHP(int HP) {
    	this.HP = HP;
    }
    
    /**
     * This sets the speed of the monster
     * @param speed the speed of the monster
     */
    public void setSpeed(int speed) {
    	this.speed = speed;
    }
    
    /**
     * This check whether the monster is alive
     * @return true if HP is larger than 0, otherwise false
     */
    public boolean IsAlive() {
    	if(HP<=0)
    		return false;
    	else return true;
    }
    
    /***
     * 
     * This return a string that describe the basic information of monster
     * @return a string that describe the basic information of monster
     */
    public String getInfo(){
        return name+ "\n" +"HP: " + HP + "/" +maxHP + "\n";
    }
}
