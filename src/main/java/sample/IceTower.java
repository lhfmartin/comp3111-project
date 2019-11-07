package sample;

import javafx.scene.image.Image;

public class IceTower extends Tower {
    IceTower(){
        super("Ice Tower", new Image("iceTower.png"));
    }

    IceTower(int x, int y){
        super("Ice Tower", new Image("iceTower.png"), 0, 5, 5, 2.5, 0.5, x, y);
    }

    @Override
    public boolean upgrade() {
        return false;
    }
    
    @Override
    public void attack() {
    	
    }
}
