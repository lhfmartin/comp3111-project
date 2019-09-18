package sample;

import javafx.scene.image.Image;

public class Tower {
    private String name;
    private Image image;
    private double minRange;
    private double maxRange;
    private double cost;
    private int x;
    private int y;
    private double attack;

    public Tower(String name, Image image, double minRange, double maxRange, double cost, double attack) {
        this.name = name;
        this.image = image;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.cost = cost;
        this.attack = attack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public String getInfo(){
        return "Name: " + this.name + "\n"
                + "Min Range: " + this.minRange + "\n"
                + "Max Range: " + this.maxRange + "\n"
                + "Attack: " + this.attack + "\n"
                + "Cost: " + this.cost;
    }
}
