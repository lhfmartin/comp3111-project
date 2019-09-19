package sample;

import javafx.scene.image.Image;

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

    public abstract boolean upgrade();
}
