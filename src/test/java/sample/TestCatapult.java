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

public class TestCatapult extends ApplicationTest {

	AnchorPane paneArena;
	private Scene s;
	ArrayList<Monster> m = new ArrayList<Monster>();
	ArrayList<Monster> m10 = new ArrayList<Monster>();
	Catapult t;
	Catapult t2;
	Catapult t3;


	public void start(Stage primaryStage) throws Exception{
		paneArena = new AnchorPane();
		t = new Catapult(3,5);
		t2 = new Catapult();
		t3 = new Catapult();
		Monster m1 = new Fox(10, 2);
		Monster m2 = new Fox(10, 2);
		Monster m3 = new Fox(10, 2);
		Monster m4 = new Fox(10, 2);
		Monster m5 = new Fox(10, 2);
		Monster m6 = new Fox(10, 2);		
		Monster m7 = new Fox(10, 2);


		m1.setX(6);
		m1.setY(4);
		m2.setX(6);
		m2.setY(4);
		m3.setX(6);
		m3.setY(4);
		m4.setX(6);
		m4.setY(3);
		m5.setX(6);
		m5.setY(3);
		m6.setX(3);
		m6.setY(5);
		m7.setX(100);
		m7.setY(100);

		m.add(m1);
		m.add(m2);
		m.add(m3);
		m.add(m4);
		m.add(m5);
		m.add(m6);
		m.add(m7);


	}
	@Test
	public void test() {
		Assert.assertTrue(t.attack(m,paneArena).size() > 0);
		Assert.assertEquals(t.attack(m,paneArena).size(), 0);
		Assert.assertNotNull(t.getRestore_time());
		Assert.assertNotNull(t.getRest_time());
		Assert.assertNotNull(t.getCicle_b4());
		Assert.assertNotNull(t.circle_surrounding());
		t.upgrade();
		Assert.assertEquals(t.getRest_time(), 4);
		Assert.assertNotNull(t.getInfo());
		t.isgameover(paneArena);
		Assert.assertEquals(paneArena.getChildren().contains(t.getCicle_b4()), false);
		t.isgameover(paneArena);
		Assert.assertEquals(paneArena.getChildren().contains(t.getCicle_b4()), false);
		Assert.assertEquals(t3.attack(m10,paneArena).size(), 0);

	}

}