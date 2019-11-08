package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class IceTower extends Tower {
	
	
	// HashMap<Monster, HashMap<RemainingFrame, OriginalSpeed>>
	HashMap<Monster, ArrayList<Integer>> slowdown = new HashMap<Monster, ArrayList<Integer>>();
	private Line line;

	
	int slowdown_period = 3;
	int slowdown_speed = 1;
	
    IceTower(){
        super("Ice Tower", new Image("iceTower.png"));
    }

    IceTower(int x, int y){
        super("Ice Tower", new Image("iceTower.png"), 0, 65, 15, 30, 0, x, y);
    }

    @Override
    public void upgrade() {
    	slowdown_period += 1;
    }
    
    @Override
    public boolean attack(ArrayList<Monster> monsters, AnchorPane paneArena) {
    
    	if (line != null) paneArena.getChildren().remove(line);

    	if (!slowdown.keySet().isEmpty()) {
			System.out.println(slowdown);

    		for (Entry<Monster, ArrayList<Integer>> entry : slowdown.entrySet()) {
    		    ArrayList<Integer> value = entry.getValue();
    		    value.set(0, Math.max(0, value.get(0)-1));
    		}
    		
    		ArrayList<Monster> penalty_end = new ArrayList<Monster>();
    		for (Entry<Monster, ArrayList<Integer>> entry : slowdown.entrySet()) {
    		    Monster key = entry.getKey();
    		    System.out.println(key.getName() + " speed is " + key.getSpeed());
    		    ArrayList<Integer> value = entry.getValue();
    		    if (value.get(0).equals(0)) {
    		    	key.setSpeed(value.get(1));
    		    	penalty_end.add(key);
    		    	System.out.println("Release " + key.getName() + " restore speed: " + key.getSpeed());
    		    } 
    		}
    		for (int i = 0 ; i < penalty_end.size() ; i++) {
    			slowdown.remove(penalty_end.get(i));
    			System.out.println(slowdown);
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
    		if (distance.get(i) >= 0) {
    			if (distance.get(i) <= 65){
					index.add(i);
					System.out.println(monsters.get(i).getName() + " within the range [0,65]");
    			}
    		}

    	}
    	
    	HashMap<Integer, Double> ending = new HashMap<Integer, Double>();
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
    	
    	if (min == null) return false;

    	int min_index = min.getKey(); 
    	
    	Monster prey = monsters.get(min_index);
    	System.out.println(prey.getName() + " is attacked" + " at x: " + prey.getX() + " y:" + prey.getY() + " by Ice tower");
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
	    	prey.setSpeed(Math.max(0, prey.getSpeed()-slowdown_speed));
    	}
    	else {
	    	target.add(slowdown_period);
	    	target.add(prey.getSpeed());
	    	prey.setSpeed(Math.max(0, prey.getSpeed()-slowdown_speed));
	    	slowdown.put(prey, target);
    	}
    	
    	return true;
    }
    
    @Override
    public String getInfo(){
        return "Name: " + getName() + "\n"
                + "Min Range: " + getMinRange() + "\n"
                + "Max Range: " + getMaxRange() + "\n"
                + "Attack: " + getAttack() + "\n"
                + "Cost: " + getBuildCost() + "\n"
                + "Freeze Time: " + slowdown_period;
    }
}
