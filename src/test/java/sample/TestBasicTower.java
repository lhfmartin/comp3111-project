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




public class TestBasicTower extends ApplicationTest {

	BasicTower basictower;
	BasicTower basictower2;
	BasicTower basictower3;
	BasicTower basictower_attack;
	AnchorPane paneArena;
	private Scene s;
	ArrayList<Monster> m = new ArrayList<Monster>();
	ArrayList<Monster> m10 = new ArrayList<Monster>();


	@Override
	public void start(Stage primaryStage) throws Exception{
		basictower = new BasicTower(1,1);
		basictower2 = new BasicTower();
		basictower3 = new BasicTower("Basic Tower");
		paneArena = new AnchorPane();
		basictower_attack = new BasicTower(1,1);


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
		Assert.assertNotNull(basictower);
		Assert.assertNotNull(basictower.getInfo());
		Assert.assertNotNull(basictower.getpixel(1,1));
		Assert.assertNotNull(basictower.getImage());
		Assert.assertNotNull(basictower.getMinRange());
		Assert.assertNotNull(basictower.getMaxRange());
		Assert.assertNotNull(basictower.getBuildCost());
		Assert.assertNotNull(basictower.getX());
		Assert.assertNotNull(basictower.getY());
		Assert.assertNotNull(basictower.getAttack());
		Assert.assertNotNull(basictower.getInfo());
		Assert.assertNotNull(basictower.getpixel(1,1));
		Assert.assertNotNull(basictower.getName());
		Assert.assertNotNull(basictower.getUpgradeCost());
		basictower.setMinRange(1);
		basictower.setMaxRange(10);
		Assert.assertEquals((int)basictower.getMinRange(),1);
		Assert.assertEquals((int)basictower.getMaxRange(),10);



	}

	@Test
	public void testConstructor2() {
		Assert.assertNotNull(basictower2);
	}

	@Test
	public void testConstructor3() {
		Assert.assertNotNull(basictower3);
	}

	@Test
	public void testUpgrade() {
		int before = (int)basictower.getAttack();
		basictower.upgrade();
		Assert.assertEquals((int)basictower.getAttack(),before+1);
	}

	@Test
	public void testGetLine() {
		Assert.assertNull(basictower.getLine());
	}

	@Test
	public void testisgameover() {
		Line l = new Line();
		basictower.setline(l);
		paneArena.getChildren().add(l);
		basictower.isgameover(paneArena);
		Assert.assertEquals(paneArena.getChildren().contains(l), false);
	}

	@Test
	public void testisgameover2() {
		Line orginal = basictower.getLine();
		orginal = null;
		int before = paneArena.getChildren().size();
		basictower.isgameover(paneArena);
		Assert.assertEquals(paneArena.getChildren().size(), before);
	}

	@Test
	public void testAttack() {
		ArrayList<Monster> attack = basictower_attack.attack(m, paneArena);
		Assert.assertEquals(attack.get(0), m.get(1));
		ArrayList<Monster> attack2 = basictower_attack.attack(m10, paneArena);
		Assert.assertEquals(attack2.size(),0);


	}

}
