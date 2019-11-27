//package sample;
//
//import java.util.ArrayList;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.testfx.framework.junit.ApplicationTest;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import javafx.scene.shape.Line;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//
//public class TestCatapult extends ApplicationTest {
//
//	AnchorPane paneArena;
//	private Scene s;
//	ArrayList<Monster> m = new ArrayList<Monster>();
//	ArrayList<Monster> m10 = new ArrayList<Monster>();
//	Catapult t = new Catapult(3,5);
//	Catapult t2 = new Catapult();
//	Catapult t3 = new Catapult();
//
//
//	public void start(Stage primaryStage) throws Exception{
//		paneArena = new AnchorPane();
//
//
//		Monster m1 = new Fox(10, 2);
//		Monster m2 = new Fox(10, 2);
//		Monster m3 = new Fox(10, 2);
//		Monster m4 = new Fox(10, 2);
//		Monster m5 = new Fox(10, 2);
//		Monster m6 = new Fox(10, 2);
//		Monster m7 = new Fox(10, 2);
//
//
//		m1.setX(6);
//		m1.setY(4);
//		m2.setX(6);
//		m2.setY(4);
//		m3.setX(6);
//		m3.setY(4);
//		m4.setX(6);
//		m4.setY(3);
//		m5.setX(6);
//		m5.setY(3);
//		m6.setX(3);
//		m6.setY(5);
//		m7.setX(100);
//		m7.setY(100);
//
//		m.add(m1);
//		m.add(m2);
//		m.add(m3);
//		m.add(m4);
//		m.add(m5);
//		m.add(m6);
//		m.add(m7);
//
//
//	}
//	@Test
//	public void test() {
//		t.attack(m,paneArena);
//		t.attack(m,paneArena);
//		t.attack(m,paneArena);
//		t.attack(m,paneArena);
//		t.attack(m,paneArena);
//		t.attack(m,paneArena);
//		t.attack(m,paneArena);
//		t.attack(m,paneArena);
//		t.attack(m,paneArena);
//		t.attack(m,paneArena);
//		Assert.assertNotNull(t.getRestore_time());
//		Assert.assertNotNull(t.getRest_time());
//		Assert.assertNotNull(t.getCicle_b4());
//		Assert.assertNotNull(t.circle_surrounding());
//		t.upgrade();
//		Assert.assertNotNull(t.getInfo());
//		t.isgameover(paneArena);
//		t.isgameover(paneArena);
//		t3.attack(m10,paneArena);
//
//	}
//
//}
