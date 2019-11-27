package sample;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class TestIceTower extends ApplicationTest{
	IceTower I1;
	IceTower I2;
	AnchorPane paneArena;
	private Scene s;
	ArrayList<Monster> m = new ArrayList<Monster>();
	ArrayList<Monster> m10 = new ArrayList<Monster>();


	@Override
	public void start(Stage primaryStage) throws Exception{
		paneArena = new AnchorPane();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Tower Defence");
        s = new Scene(root, 600, 480);
        primaryStage.setScene(s);
        Monster m1 = new Fox(10, 2);
		Monster m2 = new Fox(10, 2);
		Monster m2_1 = new Fox(10, 2);
		Monster m3 = new Fox(10, 2);
		Monster m4 = new Fox(10, 2);
		m1.setX(0);
		m1.setY(0);
		m2.setX(2);
		m2.setY(1);
		m3.setX(100);
		m3.setY(100);
		m4.setX(-10);
		m4.setY(-10);
		m.add(m1);
		m.add(m2);
		m.add(m2_1);
		m.add(m3);
		m.add(m4);
    	Monster[][] s = new Monster[12][12];
    	s[0][0] = m1;
    	s[1][2] = m2;
    	s[10][10] = m3;
        Arena appController = (Arena)loader.getController();
        appController.setSequence(s);
        appController.createArena();

        
	}

	@Test
	public void testConstructor() {
		I1 = new IceTower();
		I2 = new IceTower(1,1);
		Assert.assertNotNull(I1);
		Assert.assertNotNull(I2);
		Assert.assertNull(I1.getLine());
		Assert.assertNotNull(I1.getSlowdown_period());
		Assert.assertNotNull(I1.getSlowdown_speed());
		Assert.assertNotNull(I1.getSlowdown());
		I1.upgrade();
		Assert.assertEquals(I1.getSlowdown_period(), 4);
		Assert.assertNotNull(I1.getInfo());
		I1.isgameover(paneArena);
		Line l = new Line();
		I1.setLine(l);
		paneArena.getChildren().addAll(l);
		I1.isgameover(paneArena);
	}
	
	@Test 
	public void testAttack() {
		I1 = new IceTower(1,1);
		Assert.assertTrue(I1.attack(m, paneArena).size() > 0);
		Assert.assertTrue(I1.attack(m10, paneArena).size() == 0);
		Assert.assertTrue(I1.attack(m, paneArena).size() > 0);
		Assert.assertTrue(I1.attack(m10, paneArena).size() == 0);
		Assert.assertTrue(I1.attack(m10, paneArena).size() == 0);
		Assert.assertTrue(I1.attack(m10, paneArena).size() == 0);
		Assert.assertTrue(I1.attack(m10, paneArena).size() == 0);
		Assert.assertTrue(I1.attack(m10, paneArena).size() == 0);
		Assert.assertTrue(I1.attack(m10, paneArena).size() == 0);
	}

}
