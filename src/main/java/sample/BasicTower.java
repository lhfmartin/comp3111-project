package sample;

import javafx.scene.image.Image;

public class BasicTower extends Tower {
    BasicTower(){
        super("Basic Tower", new Image("basicTower.png"));
    }

    BasicTower(int x, int y){
        super("Basic Tower", new Image("basicTower.png"), 0, 10, 10, 5, 1, x, y);
    }

    @Override
    public boolean upgrade() {
        return false;
    }
}
