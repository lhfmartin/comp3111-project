package sample;

import java.util.ArrayList;
import java.util.*;
import java.util.Map.Entry;

import javafx.scene.shape.*;


import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Catapult extends Tower {
	
	private int restore_time = 0;
	private int rest_time = 5;
	private Circle circle_b4;
	private Circle circle_surrounding;
	
    Catapult(){
        super("Catapult", new Image("catapult.png"));
    }

    Catapult(int x, int y){
        super("Catapult", new Image("catapult.png"), 50, 150, 20, 40, 2, x, y);
    }

    @Override
    public void upgrade() {
    	rest_time = Math.max(0, rest_time-1);
    }
    
    @Override
    public boolean attack(ArrayList<Monster> monsters, AnchorPane paneArena) {
		if (this.circle_b4 != null && this.circle_surrounding!= null) {
			paneArena.getChildren().remove(circle_b4);
			paneArena.getChildren().remove(circle_surrounding);
		}

    	if (restore_time == 0) {
	    	ArrayList<Double> distance = new ArrayList<Double>();
	    	ArrayList<Integer> tower_pixel = getpixel(getX(), getY());
	    	for (int i = 0 ; i < monsters.size(); i ++) {
	    		ArrayList<Integer> monster_pixel = getpixel(monsters.get(i).getX(), monsters.get(i).getY());
	    		double a = Math.pow((monster_pixel.get(0)-tower_pixel.get(0)),2);
	    		double b = Math.pow((monster_pixel.get(1)-tower_pixel.get(1)),2);
	    		double temp_distance = Math.sqrt(a+b);
	    		distance.add(temp_distance);
	    	}
	    	
	    	
	    	Monster place_of_rock = null; 
	    	
	    	ArrayList<Integer> index = new ArrayList<Integer>();
	    	for (int i = 0 ; i < distance.size(); i++) {
	    		if (distance.get(i) > 50) {
	    			if (distance.get(i) < 150){
						index.add(i);
						System.out.println(monsters.get(i).getName() + " within the range [50,150]");
	    			}
	    		}

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
	    	
	    	if (min == null) return false;

	    	int min_index = min.getKey(); 

	    			
			place_of_rock = monsters.get(min_index);
			ArrayList<Integer> pixel_of_rock = getpixel(place_of_rock.getX(), place_of_rock.getY());
			Circle circle = new Circle();
			circle.setCenterX(pixel_of_rock.get(0));
			circle.setCenterY(pixel_of_rock.get(1));
			circle.setRadius(25);
		    circle.setStyle("-fx-stroke: red;");
		    circle.setFill(Color.CHOCOLATE);
		    circle_b4 = circle;
		    
			Circle circle2 = new Circle();
			circle2.setCenterX(pixel_of_rock.get(0));
			circle2.setCenterY(pixel_of_rock.get(1));
			circle2.setRadius(50);
		    circle2.setStyle("-fx-stroke: red;");
		    circle2.setFill(Color.TRANSPARENT);
		    circle_surrounding = circle2;
			paneArena.getChildren().addAll(circle);
			paneArena.getChildren().addAll(circle2);
			
			for (int j = 0 ; j < monsters.size() ; j++) {
    			ArrayList<Integer> monster_pixel = getpixel(monsters.get(j).getX(), monsters.get(j).getY());
    			if (circle_surrounding.contains(monster_pixel.get(0), monster_pixel.get(1))) {
    				monsters.get(j).setHP((int)Math.round(Math.max(monsters.get(j).getHP() - getAttack(), 0)));
    			}
			}

	    	System.out.println();	    	
	    	restore_time = rest_time;
	    	return true;
    	}
    	else {
    		restore_time--;
    		return false;
    	}
    	
    }
    
    @Override
    public String getInfo(){
        return "Name: " + getName() + "\n"
                + "Min Range: " + getMinRange() + "\n"
                + "Max Range: " + getMaxRange() + "\n"
                + "Attack: " + getAttack() + "\n"
                + "Cost: " + getBuildCost()	+ "\n"
                + "Remaining Restore Time: " + restore_time + "\n"
                + "Cold down frame time: "	+ rest_time;
                
        
    }
}
