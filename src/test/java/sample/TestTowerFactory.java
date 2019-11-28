package sample;

import org.junit.Assert;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;


public class TestTowerFactory extends ApplicationTest{

	@Test
	public void testDefault() {
		TowerFactory factory = new TowerFactory();
		Tower ice_tower = factory.createTower("Ice Tower");
		Assert.assertTrue(ice_tower instanceof IceTower);
		Tower catapult = factory.createTower("Catapult");
		Assert.assertTrue(catapult instanceof Catapult);
		Tower nothing = factory.createTower("");
		Assert.assertNull(nothing);

	}
	
	@Test
	public void testDefaultConversion() {
		TowerFactory factory = new TowerFactory();
		Tower ice_tower = factory.createTower("Ice Tower",1,1);
		Assert.assertTrue(ice_tower instanceof IceTower);
		Tower catapult = factory.createTower("Catapult",1,1);
		Assert.assertTrue(catapult instanceof Catapult);
		Tower laser_tower = factory.createTower("Laser Tower",1,1);
		Assert.assertTrue(laser_tower instanceof LaserTower);
		Tower nothing = factory.createTower("",1,1);
		Assert.assertNull(nothing);

	}

}
