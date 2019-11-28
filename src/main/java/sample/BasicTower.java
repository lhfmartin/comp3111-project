package sample;

import java.util.*;
import java.util.Map.Entry;


import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

import java.lang.Math;
/***
 * This is Basic Tower. It has a shooting range of [0,65] pixels
 * @author tszmoonhung
 *
 */
public class BasicTower extends Tower {

	private Line line;
	
	/***
	 * This is the setter function of shooting line
	 * @param l shooting line of basic tower
	 */
	public void setline(Line l) {
		line = l;
	}
	
	/***
	 * This is the getter function of shooting line
	 * @return line
	 */
	public Line getLine() {
		return line;
	}
	
	/**
	 * This is the default constructor of Basic Tower
	 */
	
    BasicTower(){
        super("Basic Tower", new Image("basicTower.png"));
    }
    
    /**
     * This is the conversion constructor of Basic Tower which accept a string 
     * @param name name of tower
     */
    BasicTower(String name){
    	super(name);
    }
    
    /**
     * This is the conversion constructor of Basic Tower which can set the coordinate of the Basic Tower
     * @param x x coordinate 
     * @param y y coordinate
     */
    BasicTower(int x, int y){
        super("Basic Tower", new Image("basicTower.png"), 0, 65, 10, 10, 1, x, y);
    }
    
    /**
     * This is the upgrade function of Basic Tower. After each update the attack power will increase by one
     */
    @Override
    public void upgrade() {
    	setAttack(getAttack()+1);
    }
    
    /**
     * This is the attack function of Basic Tower. It will attack once each frame. 
     * It will choose the monsters that are closer to the ending within the shooting range [0,65]
     * When there is more than one of the candidates that meet the above requirement 
     * It will choose the monster who arrive first in the grid.
     * @param monsters ArrayList of existing Monster 
     * @param paneArena (game interface)
     * @return ArrayList of attacked Monster(s) by the Basic Tower
     */
    @Override
    public ArrayList<Monster> attack(ArrayList<Monster> monsters, AnchorPane paneArena) {
    	ArrayList<Monster> attacked = new ArrayList<Monster>();
    	
    	if (line != null) paneArena.getChildren().remove(line);

    	
    	ArrayList<Double> distance = new ArrayList<Double>();
    	ArrayList<Integer> tower_pixel = getpixel(getX(), getY());
//    	System.out.println("x: " + tower_pixel.get(0));
//    	System.out.println("y: " + tower_pixel.get(1));
    	for (int i = 0 ; i < monsters.size(); i ++) {
    		ArrayList<Integer> monster_pixel = getpixel(monsters.get(i).getX(), monsters.get(i).getY());
//    		System.out.println("monster x: " + monster_pixel.get(0) +  "monster y: " + monster_pixel.get(1));
    		double a = Math.pow((monster_pixel.get(0)-tower_pixel.get(0)),2);
    		double b = Math.pow((monster_pixel.get(1)-tower_pixel.get(1)),2);
    		double temp_distance = Math.sqrt(a+b);
//    		System.out.println(monsters.get(i).getName()  + " is tower distance " + temp_distance);
    		distance.add(temp_distance);
    	}
    	
    	ArrayList<Integer> index = new ArrayList<Integer>();
    	for (int i = 0 ; i < distance.size(); i++) {
//    		if (distance.get(i) >= 0) {
    			if (distance.get(i) <= 65){
//    				System.out.println(Arena.sequence[monsters.get(i).getY()][monsters.get(i).getX()]);
    				if (Arena.sequence[monsters.get(i).getY()][monsters.get(i).getX()] == monsters.get(i))
    					index.add(i);
//					System.out.println(monsters.get(i).getName() + " within the range [0,65]");
    			}
    	//	}

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
		attacked.add(prey);
    	prey.setHP(((int)Math.max((Math.round(prey.getHP() - this.getAttack())), 0)));
//    	System.out.println(prey.getName() + " is attacked" + " at x: " + prey.getX() + " y:" + prey.getY());
    	Line new_line = new Line();
		ArrayList<Integer> monster_pixel = getpixel(monsters.get(min_index).getX(), monsters.get(min_index).getY());
    	new_line.setStartX(monster_pixel.get(0));
		new_line.setStartY(monster_pixel.get(1));
		new_line.setEndX(tower_pixel.get(0));
		new_line.setEndY(tower_pixel.get(1));
		new_line.setStyle("-fx-stroke: black;");
		line = new_line;
    	paneArena.getChildren().addAll(line);
    	return attacked;
    	  	    	
    }
    /***
     * This is the function is used when game over. It removes the GUI element created by the tower
     * @param paneArena (game interface)
     */
    @Override
    public void isgameover(AnchorPane paneArena) {
    	if (line != null) paneArena.getChildren().remove(line);
    }
}
