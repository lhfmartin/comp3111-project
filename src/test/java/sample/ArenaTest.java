package sample;

import javafx.scene.control.Label;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;

public class ArenaTest extends ApplicationTest {
    FxRobot fxRobot = new FxRobot();

    @Test
    public void testCreateArena(){
        Arena a = new Arena();
        Label grids
    }

    // For other class??
    @Test
    public void testGetName() {
        Tower icetower = new IceTower();
        Assert.assertEquals(icetower.getName(), "Ice Tower");
    }

    @Test
    public void testGetAttack(){
        Tower lasertower = new LaserTower(10, 20);
        Assert.assertEquals(lasertower.getAttack(), 3, 0.01);
    }

    @Test
    public void testGetMaxRange(){
        Tower lasertower = new LaserTower(10, 20);
        Assert.assertEquals(lasertower.getMaxRange(), 65, 0.01);
    }

    @Test
    public void testGetMinRange(){
        Tower lasertower = new LaserTower(10, 20);
        Assert.assertEquals(lasertower.getMinRange(), 0, 0.01);
    }

    @Test
    public void testGetBuildCost(){
        Tower lasertower = new LaserTower(10, 20);
        Assert.assertEquals(lasertower.getBuildCost(), 25, 0.01);
    }

    @Test
    public void testGetUpgradeCost(){
        Tower basicTower = new BasicTower(100, 20);
        Assert.assertEquals(basicTower.getUpgradeCost(), 10, 0.01);
    }

    @Test
    public void testGetX(){
        Tower basicTower = new BasicTower(100, 20);
        Assert.assertEquals(basicTower.getX(), 100);
    }

    @Test
    public void testGetY(){
        Tower basicTower = new BasicTower(100, 20);
        Assert.assertEquals(basicTower.getY(), 20);
    }

    @Test
    public void testCreateIceTower(){
        Arena a = new Arena();
        Assert.assertEquals(a.createTower("Ice Tower", 10, 60).getInfo(), new IceTower(10, 60).getInfo());
    }

    @Test
    public void testCreateBasicTower(){
        Arena a = new Arena();
        Assert.assertEquals(a.createTower("Basic Tower", 10, 60).getInfo(), new BasicTower(10, 60).getInfo());
    }

    @Test
    public void testCreateLaserTower(){
        Arena a = new Arena();
        Assert.assertEquals(a.createTower("Laser Tower", 10, 60).getInfo(), new LaserTower(10, 60).getInfo());
    }

    @Test
    public void testCreateNotATower(){
        Arena a = new Arena();
        Assert.assertEquals(a.createTower("Not A Tower", 10, 60), null);
    }

    @Test
    public void testCreateNotATower2(){
        Arena a = new Arena();
        Assert.assertEquals(a.createTower("Not A Tower"), null);
    }

    @Test
    public void testCreateCatapult(){
        Arena a = new Arena();
        Assert.assertEquals(a.createTower("Catapult", 10, 60).getInfo(), new Catapult(10, 60).getInfo());
        Assert.assertEquals(a.createTower("Catapult", 10, 60).getX(), new Catapult(10, 60).getX());
        Assert.assertEquals(a.createTower("Catapult", 10, 60).getY(), new Catapult(10, 60).getY());
    }

    @Test
    public void testCreateCatapult2(){
        Arena a = new Arena();
        Assert.assertEquals(a.createTower("Catapult").getInfo(), new Catapult().getInfo());
    }

    @Test
    public void testCreateIceTower2(){
        Arena a = new Arena();
        Assert.assertEquals(a.createTower("Ice Tower").getInfo(), new IceTower().getInfo());
    }

    @Test
    public void testCreateBasicTower2(){
        Arena a = new Arena();
        Assert.assertEquals(a.createTower("Basic Tower").getInfo(), new BasicTower().getInfo());
    }

    @Test
    public void testCreateLaserTower2(){
        Arena a = new Arena();
        Assert.assertEquals(a.createTower("Laser Tower").getInfo(), new LaserTower().getInfo());
    }
}
