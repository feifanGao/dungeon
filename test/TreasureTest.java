package test;
import javafx.scene.input.KeyCode;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.DungeonController;
import unsw.dungeon.domain.Dungeon;
import unsw.dungeon.domain.Exit;
import unsw.dungeon.domain.Player;
import unsw.dungeon.domain.Treasure;
import unsw.dungeon.goal.GoalSubBuilder;
import unsw.dungeon.util.DungeonUtil;

import java.util.ArrayList;

public class TreasureTest {

    private Dungeon dungeon;

    @Before
    public void init() {
        dungeon = new Dungeon(3,3);
    }

    @Test
    public void testTreasure() {
        Treasure treasure = new Treasure(1,1);
        Assert.assertEquals(treasure.getX(), 1);
        Assert.assertEquals(treasure.getY(), 1);
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testPickUpTreasure() {
        Treasure treasure = new Treasure(1,1);
        Player player = new Player(dungeon,1,2);
        dungeon.addEntity(treasure);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"boulders\"\n" +
                "  }"), dungeon);
        dungeonController.eventHandle(KeyCode.getKeyCode("Up"), player);

        // player from (1,2) to (1,1)
        // behind the player is treasure , so the player move up and pick up the treasure
        //Acceptance Criteria: The player would pick up the treasure after moving into the square containing it.
        Assert.assertEquals(DungeonUtil.getNode(1,1, dungeon), "Player");
        Assert.assertEquals(player.getTreasures().size(), 1);
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testTreasureGoal() {
        Treasure treasure = new Treasure(1,1);
        Player player = new Player(dungeon,1,2);
        dungeon.addEntity(treasure);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"treasure\"\n" +
                "  }"), dungeon);
        dungeonController.eventHandle(KeyCode.getKeyCode("Up"), player);

        //Acceptance Criteria: The player would complete the goal “Getting to an exit” after moving into the exit.
        Assert.assertTrue(DungeonController.goalBuilder.checkGoal(dungeon));
        System.out.println("-----------------------WIN ----------------------------");

    }

}
