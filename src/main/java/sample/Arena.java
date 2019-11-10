package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    @FXML
    private Button buttonNextFrame;

    @FXML
    private Button buttonSimulate;

    @FXML
    private Button buttonPlay;

    @FXML
    private AnchorPane paneArena;

    @FXML
    private Label labelBasicTower;

    @FXML
    private Label labelIceTower;

    @FXML
    private Label labelCatapult;

    @FXML
    private Label labelLaserTower;

    @FXML
    private Label labelMoney;
    
    private int frame=0;
    
    private static final int ARENA_WIDTH = 480;
    private static final int ARENA_HEIGHT = 480;
    public static final int GRID_WIDTH = 40;
    public static final int GRID_HEIGHT = 40;
    private static final int MAX_H_NUM_GRID = 12;
    private static final int MAX_V_NUM_GRID = 12;

    private Label grids[][] = new Label[MAX_V_NUM_GRID][MAX_H_NUM_GRID]; //the grids on arena
    private int x = -1, y = 0; //where is my monster
    private double money = 10000000;
    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private ArrayList<Monster> monsters = new ArrayList<Monster>();
    static Monster sequence[][];


    /**
     * A dummy function to show how button click works
     */
    @FXML
    private void play() {
        System.out.println("Play button clicked");
    }

    /**
     * A function that create the Arena
     */
    @FXML
    public void createArena() {
        if (grids[0][0] != null)
            return; //created already
        for (int i = 0; i < MAX_V_NUM_GRID; i++)
            for (int j = 0; j < MAX_H_NUM_GRID; j++) {
                Label newLabel = new Label();
                newLabel.setLayoutX(j * GRID_WIDTH);
                newLabel.setLayoutY(i * GRID_HEIGHT);
                newLabel.setMinWidth(GRID_WIDTH);
                newLabel.setMaxWidth(GRID_WIDTH);
                newLabel.setMinHeight(GRID_HEIGHT);
                newLabel.setMaxHeight(GRID_HEIGHT);
                newLabel.setStyle("-fx-border-color: black;");
                grids[i][j] = newLabel;

                if (j % 2 == 0 || i == ((j + 1) / 2 % 2) * (MAX_V_NUM_GRID - 1)){
                    newLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                    getMonsterInfo(j, i);
                } else{
                    newLabel.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                    setDragAndDrop(j, i);
                }

                if(i == 0 && j == 0){
                    newLabel.setStyle(newLabel.getStyle() + "-fx-background-image: url('start.png'); -fx-background-size: " + GRID_WIDTH * 0.7 + " " + GRID_HEIGHT * 0.7 + "; -fx-background-repeat: no-repeat; -fx-background-position: center center; -fx-background-color: white");
                } else if(i == 0 && j == MAX_H_NUM_GRID - 1){
                    newLabel.setStyle(newLabel.getStyle() + "-fx-background-image: url('end.png'); -fx-background-size: " + GRID_WIDTH * 0.7 + " " + GRID_HEIGHT * 0.7 + "; -fx-background-repeat: no-repeat; -fx-background-position: center center; -fx-background-color: white");
                }

                paneArena.getChildren().addAll(newLabel);
            }

        updateMoneyLabel();
    }

    @FXML
    private void nextFrame() {
//        if (x == -1) {
//            grids[0][0].setText("M");
//            x = 0;
//            return;
//        }
//        if (y == MAX_V_NUM_GRID - 1)
//            return;
//        grids[y++][x].setText("");
//        grids[y][x].setText("M");
    	for(int i=0;i<monsters.size();i++) {
        	Monster a = monsters.get(i);
        	if(!a.IsAlive()&&!Gameover()) {
        		grids[a.getY()][a.getX()].setGraphic(null);
        		monsters.remove(a);
        		money += 10;
				updateMoneyLabel();
        	}
        }

    	if(!Gameover()){
    		moveMonster();
	    	for (int i = 0 ; i < towers.size() ; i ++) { 
	    		
	    		if (towers.get(i) instanceof LaserTower) {
	    			LaserTower lt = (LaserTower)towers.get(i);
	    			if (lt.attack(monsters, paneArena) && money >= lt.getCost()) {
	    				money -= lt.getCost();
	    				updateMoneyLabel();
	    			}
	    		}else {
	    			towers.get(i).attack(monsters, paneArena);
	    		}
	    	}
    	}
    	if(!Gameover()){
	    	if(frame%2==0) {
	    		for(int i =0; i<frame/10+1;i++)
		    			createMonster();
	    	}
//	        if(monsters.size()>1) {
//	        	Monster t = monsters.get(0);
//	        	System.out.println(t.getX()+ " " + t.getY());
//	        }

	    	for(int i=0;i<monsters.size();i++) {
	        	Monster a = monsters.get(i);
	        	if(a.getX()<12&&a.getY()<12) {
		        	if(a.IsAlive()) {
		        		createMonsterIcon(a);
						grids[a.getY()][a.getX()].setText(String.valueOf(i));
		        	}
		        	else {
		        		createCollisionIcon(a);
		        	}
	        	}
	        }
	    	
    	}
        frame ++;
    }

    /**
     * A function that demo how drag and drop works
     */
    private void setDragAndDrop(int x, int y) {
        Label target = grids[y][x];
//        target.setText("Drop\nHere");
        Label source1 = labelBasicTower;
        Label source2 = labelIceTower;
        Label source3 = labelCatapult;
        Label source4 = labelLaserTower;
        
        
        source1.setOnDragDetected(new DragEventHandler(source1));
        source2.setOnDragDetected(new DragEventHandler(source2));
        source3.setOnDragDetected(new DragEventHandler(source3));
        source4.setOnDragDetected(new DragEventHandler(source4));
        

        target.setOnDragDropped((event) -> {
            System.out.println("xx");
            Dragboard db = event.getDragboard();
            boolean success = false;
            System.out.println(db.getString());
            if (db.hasString() && target.getText().isEmpty()) {
                Tower tower = createTower(db.getString(), x, y);
                if(tower.getBuildCost() > money){
                    new Alert(Alert.AlertType.WARNING, "Not enough money to build " + tower.getName().toLowerCase() + "!", ButtonType.OK).show();
                } else {
                    money -= tower.getBuildCost();
                    ImageView imageView = new ImageView(tower.getImage());
                    imageView.setFitWidth(GRID_WIDTH * 0.9);
                    imageView.setFitHeight(GRID_HEIGHT * 0.9);
                    ((Label)event.getGestureTarget()).setGraphic(imageView);
                    towers.add(tower);
                    target.setText(tower.getName());
                    updateMoneyLabel();

                    target.setOnMouseEntered((event1) -> {
                        Text towerInfo = new Text(tower.getInfo());
                        VBox vbox = new VBox(towerInfo);
                        vbox.setId("towerInformation");
                        vbox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                        vbox.setPadding(new Insets(4));
                        paneArena.getChildren().add(vbox);
                        paneArena.layout();
                        vbox.setLayoutX((x + 1) * GRID_WIDTH + vbox.getWidth() >= ARENA_WIDTH ? x * GRID_WIDTH - vbox.getWidth() : (x + 1) * GRID_WIDTH);
                        vbox.setLayoutY(y * GRID_HEIGHT + vbox.getHeight() >= ARENA_HEIGHT ? (y + 1) * GRID_WIDTH - vbox.getHeight() : y * GRID_HEIGHT);
                    });

                    target.setOnMouseExited((event1) -> {
                        paneArena.getChildren().remove(paneArena.lookup("#towerInformation"));
                    });

                    target.setOnMouseClicked((event1) -> {
                        if(paneArena.lookup("#destroyUpgradeHbox" + x + "" + y) != null){
                            paneArena.lookup("#destroyUpgradeHbox" + x + "" + y).setVisible(!paneArena.lookup("#destroyUpgradeHbox" + x + "" + y).isVisible());
                            return;
                        }
                        Button destroyButton = new Button("Destroy");
                        Button upgradeButton = new Button("Upgrade");
                        HBox hbox = new HBox(destroyButton, upgradeButton);
                        hbox.setId("destroyUpgradeHbox" + x + "" + y);
                        hbox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                        hbox.setPadding(new Insets(4));
                        paneArena.getChildren().add(hbox);
                        paneArena.applyCss();
                        paneArena.layout();

                        hbox.setLayoutX(x * GRID_WIDTH + GRID_WIDTH / 2 + hbox.getWidth() / 2 >= ARENA_WIDTH ? (x + 1) * GRID_WIDTH - hbox.getWidth() : x * GRID_WIDTH + GRID_WIDTH / 2 - hbox.getWidth() / 2 < 0 ? 0 : x * GRID_WIDTH + GRID_WIDTH / 2 - hbox.getWidth() / 2);
                        hbox.setLayoutY(y * GRID_HEIGHT - hbox.getHeight() < 0 ? (y + 1) * GRID_HEIGHT : y * GRID_HEIGHT - hbox.getHeight());

                        destroyButton.setOnMouseClicked((event2) -> {
                            target.setText("");
                            ((Label)event.getGestureTarget()).setGraphic(null);
                            if(towers.remove(tower)){
                                System.out.println("Tower removed successfully");
                            }
                            paneArena.getChildren().remove(paneArena.lookup("#destroyUpgradeHbox" + x + "" + y));
                            target.setOnMouseEntered(null);
                            target.setOnMouseExited(null);
                            target.setOnMouseClicked(null);
                        });

                        upgradeButton.setOnMouseClicked((event2) -> {
                            if(tower.getUpgradeCost() > money){
                                System.out.println("not enough resource to upgrade " + tower.getName());
                            } else {
                                tower.upgrade();
                                money -= tower.getUpgradeCost();
                                updateMoneyLabel();
                                System.out.println(tower.getName() + " is being upgraded");
                            }
                            hbox.setVisible(false);
                        });
                    });
                }
            }
            success = true;
            event.setDropCompleted(success);
            event.consume();
        });

        //well, you can also write anonymous class or even lambda
        //Anonymous class
        target.setOnDragOver((event) -> {
            /* data is dragged over the target */
//                System.out.println("onDragOver");

            /* accept it only if it is  not dragged from the same node
             * and if it has a string data */
            if (event.getGestureSource() != target &&
                    event.getDragboard().hasString()) {
                /* allow for both copying and moving, whatever user chooses */
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }

            event.consume();
        });

        target.setOnDragEntered((event) -> {
            /* the drag-and-drop gesture entered the target */
            System.out.println("onDragEntered");
            /* show to the user that it is an actual gesture target */
            if (event.getGestureSource() != target &&
                    event.getDragboard().hasString()) {
                target.setStyle("-fx-border-color: blue;");
            }

            event.consume();
        });
        //lambda
        target.setOnDragExited((event) -> {
            /* mouse moved away, remove the graphical cues */
            target.setStyle("-fx-border-color: black;");
            System.out.println("Exit");
            event.consume();
        });
    }
    
    private void getMonsterInfo(int x, int y) {
    	Label target = grids[y][x];
		//System.out.println("Enter1");
    	target.setOnMouseEntered(event->{
    		if(!target.getText().isEmpty()) {
    			Monster a  = monsters.get(Integer.parseInt(target.getText()));
    			Text monsterInfo = new Text(a.getInfo());
                VBox vbox = new VBox(monsterInfo);
                vbox.setId("monsterInformation");
                vbox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                vbox.setPadding(new Insets(4));
                paneArena.getChildren().add(vbox);
                paneArena.layout();
                vbox.setLayoutX((x + 1) * GRID_WIDTH + vbox.getWidth() >= ARENA_WIDTH ? x * GRID_WIDTH - vbox.getWidth() : (x + 1) * GRID_WIDTH);
                vbox.setLayoutY(y * GRID_HEIGHT + vbox.getHeight() >= ARENA_HEIGHT ? (y + 1) * GRID_WIDTH - vbox.getHeight() : y * GRID_HEIGHT);    		}
    		});
        target.setOnMouseExited((event1) -> {
            paneArena.getChildren().remove(paneArena.lookup("#monsterInformation"));
        });
    }

    private void updateMoneyLabel(){
        labelMoney.setText("Money: " + money);
    }

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
    
    
    public void createMonster() {
    	
    	Monster monster =null;
    	switch((int)(Math.random()*3)) {
    	case 0:	
    		monster = new Fox(8+frame/10, 2);
    		break;
    	case 1:	
    		monster = new Penguin(10+frame/10, 1);
    		break;
    	case 2:	
    		monster = new Unicorn(12+frame/10, 1);
    		break;
       	default:
    		return;
    	}
    	monsters.add(monster);
    }
    
    public void moveMonster(){
    	sequence = new Monster[MAX_V_NUM_GRID][MAX_H_NUM_GRID];
    	for(int i=0;i<monsters.size();i++) {
        	Monster a = monsters.get(i);
        	grids[a.getY()][a.getX()].setGraphic(null);
    		grids[a.getY()][a.getX()].setText("");
        	if (a instanceof Penguin) {
        		((Penguin) a).regenHP();
        	}
        	if(a.IsAlive()) {
        		int speed = a.getSpeed();
        		while(speed>0) {
        			speed -= 1;
        			int temp;
        			if(a.getX()%2==1) {
        				temp = a.getX()+1;
        				if(temp<12) {
        					a.setX(temp);	
        				}
        				else {
        					a.setX(11);	
        				}
        			}
        			else if((a.getX())%4==0) {
        				if(a.getY()== MAX_V_NUM_GRID-1) {
            				temp = a.getX()+1;
            				if(temp<12) {
            					a.setX(temp);	
            				}
            				else {
            					a.setX(11);	
            				}
        				}
        				else {
            				temp = a.getY()+1;
            				if(temp<12) {
            					a.setY(temp);	
            				}
            				else {
            					a.setX(11);	
            				}
        				}
        			}
        			else {
        				if(a.getY()==0) {
            				temp = a.getX()+1;
            				if(temp<12) {
            					a.setX(temp);	
            				}
            				else {
            					a.setX(11);	
            				}
        				}
        				else {
            				temp = a.getY()-1;
            				if(temp<12) {
            					a.setY(temp);	
            				}
            				else {
            					a.setX(11);	
            				}
        				}
        			}
        		}
            	if (sequence[a.getY()][a.getX()] == null) {
            		sequence[a.getY()][a.getX()] = a;
//            		System.out.println(a.getName() + " Change");
            	}
        	}
        	else {
        		
        	}
    	}
    }
    
    public void createMonsterIcon(Monster a ) {
        ImageView imageView = new ImageView(a.getImage());
        imageView.setFitWidth(GRID_WIDTH * 0.9);
        imageView.setFitHeight(GRID_HEIGHT * 0.9);
        grids[a.getY()][a.getX()].setGraphic(imageView);
    }
    
    public void createCollisionIcon(Monster a ) {
        ImageView imageView = new ImageView("collision.png");
        imageView.setFitWidth(GRID_WIDTH * 0.9);
        imageView.setFitHeight(GRID_HEIGHT * 0.9);
        grids[a.getY()][a.getX()].setGraphic(imageView);
    }
    
    public boolean Gameover() {
    	for(int i=0;i<monsters.size();i++) {
        	Monster a = monsters.get(i);
        	if(a.IsAlive()&& a.getX()>= 11 && a.getY()>=0) {
        		Text GameoverInfo = new Text("Gamover");
        		VBox vbox = new VBox(GameoverInfo);
        		vbox.setId("GameoverInformation");
        		vbox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        		vbox.setPadding(new Insets(7));
        		paneArena.getChildren().add(vbox);
        		paneArena.layout();
        		vbox.setLayoutX(200);
        		vbox.setLayoutY(200);
        		System.out.println("Gameover");
        		return true;
        		//System.exit(0);
        	}
    	}
    	return false;
    }

    class DragEventHandler implements EventHandler<MouseEvent> {
        private Label source;
        public DragEventHandler(Label e) {
            source = e;
        }
        @Override
        public void handle (MouseEvent event) {
            Dragboard db = source.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();
            content.putString(source.getText());
            content.putImage(createTower(source.getText()).getImage());
            db.setContent(content);

            event.consume();
        }
    }
}


