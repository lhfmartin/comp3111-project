package sample;

import java.util.ArrayList;
import java.util.*;
import java.util.Map.Entry;

import javafx.scene.shape.*;
import javafx.geometry.Point2D;


import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
/***
 * This is the Laser Tower Class. It consumes some resources to attack a monster.
 * It will shoot laser directly of monster in range of [0,65]
 * The laser will shoot from the tower to the target monster to the edge of the Arena
 * All monsters on the line or within 3px away from the laser will receive damage.
 * It consumes some resources to attack a monster. 
 * @author tszmoonhung
 *
 */
public class LaserTower extends Tower {
	private Line line;
	private Line line2;
//	private Line line3;
	private double required_resources = 10;
	/***
	 * This is the setter function of shooting line from tower to monster created by the Laser Tower when performing attack
	 * @param l shooting line from tower to monster
	 */
	public void setLine1(Line l) {
		line = l;
	}
	/***
	 * This is the setter function of shooting line from monster to edge created by the Laser Tower when performing attack
	 * @param l2 shooting line from monster to edge
	 */
	public void setLine2(Line l2) {
		line2 = l2;
	}
	/**
	 * This is the getter function of shooting line from tower to monster created by the Laser Tower
	 * @return shooting line from tower to monster
	 */
	public Line getLine() {
		return line;
	}
	
	/**
	 * This is the getter function of shooting line from monster to edge created by the Laser Tower
	 * @return shooting line from monster to edge
	 */
	public Line getLine2() {
		return line2;
	}
	
	/**
	 * This is the getter function of the required resource to shoot laser each time
	 * @return required resource to shoot laser each time
	 */
	public double getRequired_resources() {
		return required_resources;
	}
	
	/**
	 * This is the default constructor of Laser Tower
	 */
    LaserTower(){
        super("Laser Tower", new Image("laserTower.png"));
    }

    /**
     * This is the conversion constructor of Laser Tower. You can set the coordinate of the tower in this constructor.
     * @param x x coordinate
     * @param y y coordinate
     */
    LaserTower(int x, int y){
        super("Laser Tower", new Image("laserTower.png"), 0, 65, 25, 50, 3, x, y);
    }
    
	/**
	 * This is the upgrade function of Laser Tower
	 * It will increase the Attack power by 1
	 */
    @Override
    public void upgrade() {
    	setAttack(getAttack()+1);    
    }
    
    /**
     * This return the cost of shooting laser each time
     * @return cost of shooting laser each time
     */
    public double getCost() {
    	return required_resources;
    }
    
    private ArrayList<Double> equationofline (ArrayList<Integer> pta, ArrayList<Integer> ptb){
    	ArrayList<Double> line = new ArrayList<Double>();
    	double x1 = pta.get(0);
    	double y1 = pta.get(1);
    	double x2 = ptb.get(0);
    	double y2 = ptb.get(1);
    	double slope = (y1-y2)/(x1-x2);
    	double c = y1 - slope*x1;
    	line.add(slope);
    	line.add(c);
    	return line;
    }
    
        
    private Line nearestborder (Double slope, Double c, ArrayList<Integer> monster, ArrayList<Integer> tower){
    	ArrayList<Point2D> border = new ArrayList<Point2D>();
    	Line line = new Line();
    	double max_x = 480;
    	double max_y = 480;
    	double min_x = 0;
    	double min_y = 0;

    	border.add(new Point2D(max_x, slope*max_x+c));
    	border.add(new Point2D(min_x, slope*min_x+c));
    	border.add(new Point2D(((max_y-c)/slope), max_y));
    	border.add(new Point2D(((min_y-c)/slope), min_y));
    	
    	ArrayList<Point2D> removed_negative = new ArrayList<Point2D>();
//    	System.out.println("Before remove: " + border);
    	for (int i = 0 ; i < border.size() ; i++) {
    		if (border.get(i).getX() >= 0 && border.get(i).getY() >= 0 && border.get(i).getX() <= 480 && border.get(i).getY() <= 480)
    			removed_negative.add(border.get(i));
    	}
    	
    	border = removed_negative;
    	if (border.isEmpty()) {
    		border.add(new Point2D(monster.get(0), max_y));
    		border.add(new Point2D(monster.get(0), min_y));
    	}
    	
//    	System.out.println(border);
    	for (int i = 0 ; i < border.size() ; i ++) {
    		Line temp = new Line();
    		temp.setStartX(monster.get(0));
    		temp.setStartY(monster.get(1));
    		temp.setEndX(border.get(i).getX());
    		temp.setEndY(border.get(i).getY());
    		temp.setStyle("-fx-stroke: red;");
    		if (!temp.contains(tower.get(0), tower.get(1))){
    			line = temp;
    			break;
    		}

    	}
    	return line;
    }
    /***
     * This is the attack function of Laser Tower
     * It will first choose a target monster
     * It will choose the monsters that are closer to the ending within the shooting range [0,65]
     * When there is more than one of the candidates that meet the above requirement 
     * It will choose the monster who arrive first in the grid.
     * Then, the laser will shoot from the tower to the target monster to the edge of the Arena
     * All monsters on the line or within 3px away from the laser will receive damage.
     * @param monsters ArrayList of existing Monster 
     * @param paneArena (game interface)
     * @return ArrayList of attacked Monster(s) by the Laser Tower
     */
    @Override
    public ArrayList<Monster> attack(ArrayList<Monster> monsters, AnchorPane paneArena){
    	ArrayList<Monster> attacked = new ArrayList<Monster>();
    	
    	if (line != null && line2 != null) {
    		paneArena.getChildren().remove(line);
    		paneArena.getChildren().remove(line2);
//    		paneArena.getChildren().remove(line3);

    	}
    	
    	ArrayList<Double> distance = new ArrayList<Double>();
    	ArrayList<Integer> tower_pixel = getpixel(getX(), getY());
//    	System.out.println("x: " + tower_pixel.get(0));
//    	System.out.println("y: " + tower_pixel.get(1));
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
    			if (distance.get(i) <= 65)
    					index.add(i);
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
    	
    	ArrayList<Integer> monster = getpixel(monsters.get(min_index).getX(), monsters.get(min_index).getY());
    	ArrayList<Integer> tower = getpixel(getX(), getY());
    	ArrayList<Double> lineequation = equationofline(monster, tower);
    	
    	double  slope = lineequation.get(0);
    	double c = lineequation.get(1);
    	Line border = nearestborder(slope, c, monster, tower);
		Line new_line = new Line();
		
		new_line.setStartX(monster.get(0));
		new_line.setStartY(monster.get(1));
		new_line.setEndX(tower.get(0));
		new_line.setEndY(tower.get(1));
		new_line.setStyle("-fx-stroke: red;");
		Line fullline = new Line();
		fullline.setStartX(border.endXProperty().doubleValue());
		fullline.setStartY(border.endYProperty().doubleValue());
		fullline.setEndX(tower.get(0));
		fullline.setEndY(tower.get(1));
		fullline.setStyle("-fx-stroke: blue;");
		fullline.setStrokeWidth(7);

    	this.line = new_line;
    	this.line2 = border;
//    	this.line3 = fullline;
    	
    	paneArena.getChildren().addAll(line);
    	paneArena.getChildren().addAll(line2);
//    	paneArena.getChildren().addAll(line3);

    	
		ArrayList<Monster> hurt_mon = new ArrayList<Monster>();
		Point2D t = new Point2D(tower.get(0),tower.get(1) );
		Point2D edge = new Point2D(border.endXProperty().doubleValue(),border.endYProperty().doubleValue());

		for (int i = 0 ; i < monsters.size() ; i++) {
			ArrayList<Integer> p = getpixel(monsters.get(i).getX(), monsters.get(i).getY());
			Point2D cur = new Point2D(p.get(0),p.get(1));
			if (fullline.contains(p.get(0), p.get(1))) {
//				System.out.println(monsters.get(i).getName() + " is attacked by laser");
//				System.out.println("x: " + monsters.get(i).getX() + " y:" + monsters.get(i).getY());
				if (!hurt_mon.contains(monsters.get(i))) 
					hurt_mon.add(monsters.get(i));
			}
//			if (!hurt_mon.contains(monsters.get(i)) && t.distance(cur) <= 3) 
//				hurt_mon.add(monsters.get(i)); 
//			
//			if (!hurt_mon.contains(monsters.get(i)) && edge.distance(cur) <= 3) 
//				hurt_mon.add(monsters.get(i)); 
			
		}

		for (int i = 0 ; i < hurt_mon.size() ; i ++) {
			hurt_mon.get(i).setHP((int)Math.round(Math.max(0, hurt_mon.get(i).getHP()-getAttack())));
		}
		return hurt_mon;
    	
    }
    /**
     * This return a string that describe the information of Laser Tower
     * @return a string that describe the information of Laser Tower
     */
    @Override
    public String getInfo(){
        return "Name: " + getName() + "\n"
                + "Min Range: " + getMinRange() + "\n"
                + "Max Range: " + getMaxRange() + "\n"
                + "Attack: " + getAttack() + "\n"
                + "Cost: " + getBuildCost() + "\n"
                + "Money required for laser shoot: " + required_resources;
    }
    
    /***
     * This is the function is used when game over. It removes the GUI element created by the tower
     * @param paneArena (game interface)
     */
    @Override
    public void isgameover(AnchorPane paneArena) {
    	if (line != null && line2 != null) {
    		paneArena.getChildren().remove(line);
    		paneArena.getChildren().remove(line2);
    	}
    }
}
