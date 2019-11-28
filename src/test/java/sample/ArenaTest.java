package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.ButtonMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.WaitForAsyncUtils;

import java.util.List;

public class ArenaTest extends ApplicationTest {
    FxRobot robot = new FxRobot();

    private Scene s;
    private Arena a;
    private AnchorPane paneArena;


    @Override
	public void start(Stage primaryStage) throws Exception{

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Tower Defence");
        s = new Scene(root, 600, 480);
        primaryStage.setScene(s);
        primaryStage.show();
        Arena appController = (Arena)loader.getController();
        appController.createArena();
        a = appController;
        paneArena = a.getPane();
	}

    @Test
    public void testCreateArena(){
        a.createArena();
    }

    @Test
    public void testDragBasicTowerLabel(){
        List<Tower> towers = a.getTower();
        int towersLength = towers.size();
        robot.drag("#labelBasicTower").dropTo(paneArena.getChildren().get(1));
        Assert.assertEquals(towersLength + 1, a.getTower().size());
        Assert.assertEquals("Basic Tower", a.getTower().get(a.getTower().size() - 1).getName());
    }

    @Test
    public void testBuild2TowersInSameGrid(){
        List<Tower> towers = a.getTower();
        int towersLength = towers.size();
        robot.drag("#labelBasicTower").dropTo(paneArena.getChildren().get(1));
        robot.drag("#labelLaserTower").dropTo(paneArena.getChildren().get(1));
        Assert.assertEquals(towersLength + 1, a.getTower().size());
        Assert.assertEquals("Basic Tower", a.getTower().get(a.getTower().size() - 1).getName());
    }

    @Test
    public void testDestroyTowerButton(){
        List<Tower> towers = a.getTower();
        int towersLength = towers.size();
        robot.drag("#labelBasicTower").dropTo(paneArena.getChildren().get(1));
        robot.clickOn(paneArena.getChildren().get(1));
        robot.moveBy(-10, 50).clickOn();
        Assert.assertEquals(towersLength, a.getTower().size());
    }

    @Test
    public void testUpgradeTowerButton(){
        Tower bTower = new BasicTower(0, 0);
        bTower.upgrade();
        robot.drag("#labelBasicTower").dropTo(paneArena.getChildren().get(1));
        robot.clickOn(paneArena.getChildren().get(1));
        robot.moveBy(10, 50).clickOn();
//        Assert.assertEquals(bTower.getAttack(), a.getTower().get(a.getTower().size() - 1).getAttack(), 0.00000001);
    }
}
