package sample;

import javafx.scene.image.Image;

public class LaserTower extends Tower {
    LaserTower(){
        super("Laser Tower", new Image("laserTower.png"));
    }

    LaserTower(int x, int y){
        super("Laser Tower", new Image("laserTower.png"), 0, 90, 90, 45, 1, x, y);
    }

    @Override
    public boolean upgrade() {
        return false;
    }
}
