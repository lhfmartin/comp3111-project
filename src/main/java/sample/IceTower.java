package sample;

import java.util.ArrayList;
import java.util.*;
import java.util.Map.Entry;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
/***
 * This is the Ice Tower Class.
 * It will have the shooting range of [0,65] 
 * It will make monster move slower for a period of time 
 * When a monster is attacked by Ice Tower. It will receive 1 HP deduction.
 * @author tszmoonhung
 *
 */
public class IceTower extends Tower {
	
	
	// HashMap<Monster, HashMap<RemainingFrame, OriginalSpeed>>
	private HashMap<Monster, ArrayList<Integer>> slowdown = new HashMap<Monster, ArrayList<Integer>>();
	private Line line;

	
	private int slowdown_period = 3;
	private int slowdown_speed = 1;
	
	/***
	 * This is the getter function.It will return the shooting line created by the Ice Tower
	 * @return shooting line created by the Ice Tower
	 */
	public Line getLine() {
		return line;
	}
	
	/***
	 * This is the setter function.It will set the shooting line of the Ice Tower.
	 * @param l shooting line of the tower
	 */
	public void setLine(Line l) {
		line = l;
	}
	
	/***
	 * This is the getter function.It will return the period that a monster will slow down.
	 * @return the period that a monster will slow down
	 */
	public int getSlowdown_period() {
		return slowdown_period ;
	}
	
	/***
	 * This is the getter function.It will return the speed that will be reduced by the Ice Tower 
	 * when a monster is attacked by the tower.
	 * @return the speed that will be reduced by the Ice Tower
	 */
	public int getSlowdown_speed() {
		return slowdown_speed;
	}
	
	
	/***
	 * This is the getter function.It will return the remaining slow down period of each of the monsters of the attacked monsters by the tower.
	 * @return HashMap of attacked monster as key and the respective remaining slow down period as value
	 */
	public HashMap<Monster, ArrayList<Integer>> getSlowdown(){
		return slowdown;
	}
	
	/**
	 * The default constructor of Ice Tower
	 */
    IceTower(){
        super("Ice Tower", new Image("iceTower.png"));
    }
    
    /***
     * This is the conversion constructor of Ice Tower which can set the coordinate of the Ice Tower
     * @param x x coordinate
     * @param y y coordinate
     */
    IceTower(int x, int y){
        super("Ice Tower", new Image("iceTower.png"), 0, 65, 15, 30, 1, x, y);
    }

    /**
     * This is the upgrade function of Ice Tower. It increases the duration of the monster being slowed.
     */
    @Override
    public void upgrade() {
    	slowdown_period += 1;
    }
    
    /***
     * This is the attack function of Ice Tower.
     * Only one monster will attack each time.
     * It will choose the monsters that are closer to the ending within the shooting range [0,65]
     * When there is more than one of the candidates that meet the above requirement 
     * It will choose the monster who arrive first in the grid.
     * Then, it will slow down monster and add it to the HashMap that store the remaining penalty time
     * It is possible that the same monster attacked more that once by the tower 
     * It will increase the penalty time and further decreases it speed until it reach 0 speed
     * When, the penalty time ends, the monster(s) will restore to the speed that they have before being attacked by the Ice Tower
     * @param monsters ArrayList of existing Monster 
     * @param paneArena (game interface)
     * @return ArrayList of attacked Monster(s) by the Ice Tower
     */
    @Override
    public ArrayList<Monster> attack(ArrayList<Monster> monsters, AnchorPane paneArena) {
    	ArrayList<Monster> attacked = new ArrayList<Monster>();
    	
    	if (line != null) paneArena.getChildren().remove(line);

    	if (!slowdown.keySet().isEmpty()) {
//			System.out.println(slowdown);

    		for (Entry<Monster, ArrayList<Integer>> entry : slowdown.entrySet()) {
    		    ArrayList<Integer> value = entry.getValue();
    		    value.set(0, Math.max(0, value.get(0)-1));
    		}
    		
    		ArrayList<Monster> penalty_end = new ArrayList<Monster>();
    		for (Entry<Monster, ArrayList<Integer>> entry : slowdown.entrySet()) {
    		    Monster key = entry.getKey();
//    		    System.out.println(key.getName() + " speed is " + key.getSpeed());
    		    ArrayList<Integer> value = entry.getValue();
    		    if (value.get(0).equals(0)) {
    		    	key.setSpeed(value.get(1));
    		    	penalty_end.add(key);
//    		    	System.out.println("Release " + key.getName() + " restore speed: " + key.getSpeed());
    		    } 
    		}
    		for (int i = 0 ; i < penalty_end.size() ; i++) {
    			slowdown.remove(penalty_end.get(i));
//    			System.out.println(slowdown);
    		}
    		
    	}
    	
    	ArrayList<Double> distance = new ArrayList<Double>();
    	ArrayList<Integer> tower_pixel = getpixel(getX(), getY());
    	for (int i = 0 ; i < monsters.size(); i ++) {
    		ArrayList<Integer> monster_pixel = getpixel(monsters.get(i).getX(), monsters.get(i).getY());
    		double a = Math.pow((monster_pixel.get(0)-tower_pixel.get(0)),2);
    		double b = Math.pow((monster_pixel.get(1)-tower_pixel.get(1)),2);
    		double temp_distance = Math.sqrt(a+b);
    		distance.add(temp_distance);
    	}
    	    	
    	ArrayList<Integer> index = new ArrayList<Integer>();
    	for (int i = 0 ; i < distance.size(); i++) {
//    		if (distance.get(i) >= 0) {
    			if (distance.get(i) <= 65){
    				if (Arena.sequence[monsters.get(i).getY()][monsters.get(i).getX()] == monsters.get(i))
    					index.add(i);
//					System.out.println(monsters.get(i).getName() + " within the range [0,65]");
    			}
//    		}

    	}
    	
    	LinkedHashMap<Integer, Double> ending = new LinkedHashMap<Integer, Double>();
    	for (int i = 0 ; i < index.size(); i ++) {
    		ArrayList<Integer> monster_pixel = getpixel(monsters.get(index.get(i)).getX(), monsters.get(index.get(i)).getY());
    		double a = Math.pow((monster_pixel.get(0)-440),2);
    		double b = Math.pow((monster_pixel.get(1)-0),2);
    		double temp_distance = Math.sqrt(a+b);
    		ending.put(index.get(i), temp_distance);		
    	}
    	
    	Entry<Integer, Double> min = null;
    	for (Entry<Integer, Double> entry : ending.entrySet()) {
    	    if (min == null || min.getValue() > entry.getValue()) {
    	        min = entry;
    	    }
    	}
    	
    	if (min == null) return attacked;

    	int min_index = min.getKey(); 
    	
    	Monster prey = monsters.get(min_index);
//    	System.out.println(prey.getName() + " is attacked" + " at x: " + prey.getX() + " y:" + prey.getY() + " by Ice tower");
    	ArrayList<Integer> target = new ArrayList<Integer>();
    	Line new_line = new Line();
		ArrayList<Integer> monster_pixel = getpixel(prey.getX(), prey.getY());
    	new_line.setStartX(monster_pixel.get(0));
		new_line.setStartY(monster_pixel.get(1));
		new_line.setEndX(tower_pixel.get(0));
		new_line.setEndY(tower_pixel.get(1));
		new_line.setStyle("-fx-stroke: blue;");
		line = new_line;
		
    	paneArena.getChildren().addAll(line);

    	if (slowdown.containsKey(prey)) {
    		ArrayList<Integer> remain = slowdown.get(prey);
    		remain.set(0, remain.get(0)+slowdown_period);
	    	prey.setSpeed(Math.max(0, prey.getSpeed()-slowdown_speed));
	    	prey.setHP((int)Math.max(prey.getHP()- getAttack(),0));
	    	attacked.add(prey);
    	}
    	else {
	    	target.add(slowdown_period);
	    	target.add(prey.getSpeed());
	    	prey.setSpeed(Math.max(0, prey.getSpeed()-slowdown_speed));
	    	slowdown.put(prey, target);
	    	attacked.add(prey);
    	}
    	
    	System.out.println(slowdown);
    	return attacked;
    }
    
    /***
     * This return a string that describe the information of Ice Tower
     * @return a string that describe the information of Ice Tower
     */
    @Override
    public String getInfo(){
        return "Name: " + getName() + "\n"
                + "Min Range: " + getMinRange() + "\n"
                + "Max Range: " + getMaxRange() + "\n"
                + "Attack: " + getAttack() + "\n"
                + "Cost: " + getBuildCost() + "\n"
                + "Freeze Time: " + slowdown_period;
    }
    
    /**
     * This is the function is used when game over.It removes the GUI element created by the tower
     * @param paneArena (game interface)
     */
    @Override
    public void isgameover(AnchorPane paneArena) {
    	if (line != null) paneArena.getChildren().remove(line);
    }
    
}
