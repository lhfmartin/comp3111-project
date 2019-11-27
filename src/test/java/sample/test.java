/**
 * 
 * You might want to uncomment the following code to learn testFX. Sorry, no tutorial session on this.
 * 
 */
package sample;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
 

public class test extends ApplicationTest {

	private Scene s;
	private Arena appController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Tower Defence");
        s = new Scene(root, 600, 480);
        primaryStage.setScene(s);
        primaryStage.show();
        appController = (Arena)loader.getController();
        appController.createArena();
	}
	
	
	@Test
	public void testMonster() throws InterruptedException {
		interact(()->{
			appController.createArena();
			Monster temp = appController.createMonster();
			temp.setSpeed(3);		
			appController.moveMonster();
			appController.createMonsterIcon(temp);
			temp.getInfo();
			temp.setHP(0);
			Assert.assertEquals(temp.getHP(), 0);
			Assert.assertEquals(temp.IsAlive(), false);
			appController.createCollisionIcon(temp);
	    	while(!appController.Gameover()) {
	    		Monster temp2 = appController.createMonster();
	    		appController.createMonsterIcon(temp2);
	    		temp2.setHP(2);
    			appController.moveMonster();
	    	}
		});
	}
}