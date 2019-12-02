package sample;

/**
 * This is the factory class for generating towers of different types
 * @author lhfmartin
 */
public class TowerFactory {
    /**
     * Create a new tower of the specified type for the user to drag and drop to the arena.
     * @param label Name of the tower to be generated
     * @return A tower object of the specified type
     */
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

    /**
     * Create a new tower of the specified type that will be placed on (x, y) in the arena
     * @param label Name of the tower to be generated
     * @param x x-coordinate of the tower
     * @param y y-coordinate of the tower
     * @return A tower object of the specified type
     */
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

    /**
     * The default constructor of the TowerFactory class
     */
    public TowerFactory(){

    }
}
