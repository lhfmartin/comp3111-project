package sample;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

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
    public static final int GRID_WIDTH = 40;
    public static final int GRID_HEIGHT = 40;

    public Tower(String name, Image image) {
        this.name = name;
        this.image = image;
    }

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

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public double getMinRange() {
        return minRange;
    }

    public void setMinRange(double minRange) {
        this.minRange = minRange;
    }

    public double getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(double maxRange) {
        this.maxRange = maxRange;
    }

    public double getBuildCost() {
        return buildCost;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public double getUpgradeCost() {
        return upgradeCost;
    }

    public String getInfo(){
        return "Name: " + name + "\n"
                + "Min Range: " + minRange + "\n"
                + "Max Range: " + maxRange + "\n"
                + "Attack: " + attack + "\n"
                + "Cost: " + buildCost;
    }
    
    public ArrayList<Integer> getpixel(int x, int y){
    	ArrayList<Integer> pixel = new ArrayList<Integer>();
    	pixel.add(x * GRID_WIDTH + GRID_WIDTH/2);
    	pixel.add(y * GRID_HEIGHT + GRID_HEIGHT/2);
    	return pixel;
    }

    public abstract void upgrade();
    
    public abstract void isgameover(AnchorPane paneArena);
    
    public abstract ArrayList<Monster> attack(ArrayList<Monster> monsters, AnchorPane paneArena);
}
