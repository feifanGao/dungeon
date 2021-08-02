package test;
import javafx.scene.input.KeyCode;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.DungeonController;
import unsw.dungeon.domain.Dungeon;
import unsw.dungeon.domain.Player;
import unsw.dungeon.domain.Wall;
import unsw.dungeon.goal.GoalSubBuilder;
import unsw.dungeon.util.DungeonUtil;

import java.util.ArrayList;

public class WallTest {

    private Dungeon dungeon;

    @Before
    public void init() {
        dungeon = new Dungeon(3,3);
    }

    @Test
    public void testTreasure() {
        Wall wall = new Wall(1,1);
        Assert.assertEquals(wall.getX(), 1);
        Assert.assertEquals(wall.getY(), 1);
    }


    @Test
    public void testMoveUpToWall() {

        Wall wall = new Wall(1,1);
        Player player = new Player(dungeon,1,2);
        dungeon.addEntity(wall);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"boulders\"\n" +
                "  }"), dungeon);
        dungeonController.eventHandle(KeyCode.getKeyCode("Up"), player);

        // player from (1,2) to (1,1)
        // behind the player is wall , so the player is still here
        Assert.assertEquals(DungeonUtil.getNode(1,2, dungeon), "Player");
        System.out.println("-----------------------WIN ----------------------------");
    }

}
