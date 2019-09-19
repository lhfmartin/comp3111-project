package sample;

import javafx.scene.image.Image;

public class Catapult extends Tower {
    Catapult(){
        super("Catapult", new Image("catapult.png"));
    }

    Catapult(int x, int y){
        super("Catapult", new Image("catapult.png"), 0, 15, 15, 7.5, 1, x, y);
    }

    @Override
    public boolean upgrade() {
        return false;
    }
}
