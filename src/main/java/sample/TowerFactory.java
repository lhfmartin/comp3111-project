package sample;

public class TowerFactory {
    public Tower createTower(String label){
        switch (label){
            case "Basic Tower":
                return new BasicTower();
            case "Ice Tower":
                return new IceTower();
            case "Catapult":
                return new Catapult();
            case "Laser Tower":
                return new LaserTower();
            default:
                return null;
        }
    }

    public Tower createTower(String label, int x, int y){
        switch (label){
            case "Basic Tower":
                return new BasicTower(x, y);
            case "Ice Tower":
                return new IceTower(x, y);
            case "Catapult":
                return new Catapult(x, y);
            case "Laser Tower":
                return new LaserTower(x, y);
            default:
                return null;
        }
    }
}
