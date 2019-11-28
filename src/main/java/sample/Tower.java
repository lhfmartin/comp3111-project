package sample;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
/***
 * This is the base class of all the tower.
 * @author tszmoonhung
 *
 */
public abstract class Tower {
    private String name;
    private Image image;
    private double minRange;
    private double maxRange;
    private double buildCost;
    private int x;
    private int y;
    private double attack;
    private double upgradeCost;
    /**
     * This stores the grid width of the game interface
     */
    public static final int GRID_WIDTH = 40;
    /**
     * This stores the grid height of the game interface
     */
    public static final int GRID_HEIGHT = 40;
    
    /**
     * This is the conversion constructor which accept the name of the tower and the image of the tower.
     * @param name name of the tower
     * @param image image of the tower
     */
    public Tower(String name, Image image) {
        this.name = name;
        this.image = image;
    }
    
    /***
     * This is the conversion constructor which accept the name of the tower
     * @param name name of the tower
     */
    public Tower(String name) {
        this.name = name;
    }
    
    /***
     * This is the conversion constructor which accept name, image, minRange, maxRange, buildCost, upgradeCost, attack, x coordinate and y coordinate. 
     * @param name name of the tower
     * @param image image of the tower
     * @param minRange minimum shooting range of the tower
     * @param maxRange maximum shooting range of the tower
     * @param buildCost building cost of the tower 
     * @param upgradeCost upgrading cost of the tower
     * @param attack attack power of the tower
     * @param x x coordinate
     * @param y y coordinate 
     */
    public Tower(String name, Image image, double minRange, double maxRange, double buildCost, double upgradeCost, double attack, int x, int y) {
        this.name = name;
        this.image = image;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.buildCost = buildCost;
        this.upgradeCost = upgradeCost;
        this.attack = attack;
        this.x = x;
        this.y = y;
    }
    
    /***
     * This returns the Name of the tower
     * @return name of the tower
     */
    public String getName() {
        return name;
    }
    
    /***
     * This returns the image of the tower
     * @return image of the tower
     */
    public Image getImage() {
        return image;
    }
    
    /***
     * This returns the minRange of the tower
     * @return minimum shooting range of the tower
     */
    public double getMinRange() {
        return minRange;
    }
    
    /***
     * This sets the minRange of the tower
     * @param minRange maximum shooting range of the tower
     */
    public void setMinRange(double minRange) {
        this.minRange = minRange;
    }
    
    /***
     * This gets the MaxRange of the tower
     * @return maximum shooting range of the tower
     */
    public double getMaxRange() {
        return maxRange;
    }
    
    /***
     * This sets the MaxRnage of the tower
     * @param maxRange maximum shooting range of the tower
     */
    public void setMaxRange(double maxRange) {
        this.maxRange = maxRange;
    }
    
    /***
     * This gets the BuildCost of the tower
     * @return building cost of the tower
     */
    public double getBuildCost() {
        return buildCost;
    }
    
    /***
     * This gets the x coordinate of the tower
     * @return x coordinate
     */
    public int getX() {
        return x;
    }
    
    /***
     * This gets the y of the tower
     * @return y coordinate
     */
    public int getY() {
        return y;
    }
    
    /***
     * This gets the attack power of the tower
     * @return attack power of the tower
     */
    public double getAttack() {
        return attack;
    }
    
    /***
     * This sets the attack power of the tower
     * @param attack attack power of the tower
     */
    public void setAttack(double attack) {
        this.attack = attack;
    }
    
    /***
     * This gets the upgrade cost of the tower
     * @return upgrade cost of the tower
     */
    public double getUpgradeCost() {
        return upgradeCost;
    }
    
    /***
     * 
     * This return a string that describe the basic information of Tower
     * @return a string that describe the basic information of Tower
     */
    public String getInfo(){
        return "Name: " + name + "\n"
                + "Min Range: " + minRange + "\n"
                + "Max Range: " + maxRange + "\n"
                + "Attack: " + attack + "\n"
                + "Cost: " + buildCost;
    }
    
    /**
     * It gets the tower coordinate in pixel
     * @param x x coordinate 
     * @param y y coordinate
     * @return ArrayList of x y coordinate in pixel
     */
    public ArrayList<Integer> getpixel(int x, int y){
    	ArrayList<Integer> pixel = new ArrayList<Integer>();
    	pixel.add(x * GRID_WIDTH + GRID_WIDTH/2);
    	pixel.add(y * GRID_HEIGHT + GRID_HEIGHT/2);
    	return pixel;
    }
    
    /**
     * Abstract upgrade function of tower
     * It will be override by the its subclass
     */
    public abstract void upgrade();
    
    /***
     * Abstract isgameover function of tower
     * It will remove all the GUI elements created by the tower
     * It will be override by the its subclass
     * @param paneArena game interface
     */
    public abstract void isgameover(AnchorPane paneArena);
   
    /***
     * Abstract attack function of tower
     * It will be override by the its subclass
     * @param monsters existing monsters in the game
     * @param paneArena game interface
     * @return ArrayList of attacked Monster(s) by the Tower
     */
    public abstract ArrayList<Monster> attack(ArrayList<Monster> monsters, AnchorPane paneArena);
}
