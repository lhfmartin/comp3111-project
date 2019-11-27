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

public class TestLaserTower extends ApplicationTest{
	
	private Scene s;
	ArrayList<Monster> m = new ArrayList<Monster>();
	ArrayList<Monster> m10 = new ArrayList<Monster>();
	LaserTower lt;
	LaserTower lt2;
	AnchorPane paneArena;

	@Override
	public void start(Stage primaryStage) throws Exception{
		lt = new LaserTower();
		lt2 = new LaserTower(1,1);
		paneArena = new AnchorPane();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Tower Defence");
        s = new Scene(root, 600, 480);
        primaryStage.setScene(s);
        primaryStage.show();
		Monster m1 = new Fox(10, 2);
		Monster m2 = new Fox(10, 2);
		Monster m2_1 = new Fox(10, 2);
		Monster m3 = new Fox(10, 2);
		Monster m4 = new Fox(10, 2);
		Monster m5 = new Fox(10, 2);
		Monster m6 = new Fox(10, 2);
		Monster m7 = new Fox(10, 2);
		m1.setX(0);
		m1.setY(0);
		m2.setX(2);
		m2.setY(1);
		m5.setX(2);
		m5.setY(1);
		m6.setX(2);
		m6.setY(1);
		m7.setX(1);
		m7.setY(0);
		m3.setX(100);
		m3.setY(100);
		m4.setX(-10);
		m4.setY(-10);
		m.add(m1);
		m.add(m2);
		m.add(m2_1);
		m.add(m3);
		m.add(m4);
		m.add(m7);

        Arena appController = (Arena)loader.getController();

        appController.createArena();
	}
	
	
	@Test
	public void constructortest() {
		Assert.assertNotNull(lt);
		Assert.assertNotNull(lt2);
		Assert.assertNotNull(lt2.getCost());
		Assert.assertNotNull(lt2.getRequired_resources());
		Assert.assertNull(lt2.getLine2());
		Assert.assertNull(lt2.getLine());
		Assert.assertNotNull(lt2.getInfo());
		lt2.upgrade();	
		lt2.upgrade() ;		
		lt2.upgrade() ;		
		lt2.upgrade() ;		
		Assert.assertEquals((int)lt2.getAttack(),7);


	}
	
	@Test
	public void testattack() {
		lt2.attack(m10, paneArena);
		lt2.attack(m, paneArena);
		lt2.attack(m, paneArena);
		lt2.attack(m, paneArena);
		lt2.attack(m, paneArena);
		lt2.attack(m, paneArena);
		lt2.attack(m, paneArena);
		lt2.attack(m, paneArena);
		lt2.attack(m, paneArena);
		lt2.isgameover(paneArena);
		lt2.setLine1(null);
		lt2.setLine2(null);
		lt2.isgameover(paneArena);
		lt = new LaserTower(3,2);
		ArrayList<Monster> mm = new ArrayList<Monster>();
		Monster m1 = new Fox(10, 2);
		m1.setX(3);
		m1.setY(1);
		mm.add(m1);
		lt.attack(mm,paneArena);

	}

}
