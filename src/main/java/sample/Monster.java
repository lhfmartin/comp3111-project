package sample;

import javafx.scene.image.Image;

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

    public Monster(String name, Image image, int x, int y, int maxHP, int speed) {
    	this.name = name;
    	this.image = image;
    	this.x = x;
    	this.y = y;
  		this.maxHP = maxHP;
  		this.HP = maxHP;
  		this.speed = speed;
    }
    
    
    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getMaxHP() {
        return maxHP;
    }
    
    public int getHP() {
        return HP;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void setX(int x) {
    	this.x = x;
    }

    public void setY(int y) {
    	this.y = y;
    }
    
    public void setHP(int HP) {
    	this.HP = HP;
    }
    
    public void setSpeed(int speed) {
    	this.speed = speed;
    }
    
    public boolean IsAlive() {
    	if(HP<=0)
    		return false;
    	else return true;
    }
    
    public String getInfo(){
        return name+ "\n" +"HP: " + HP + "/" +maxHP + "\n";
    }
}
