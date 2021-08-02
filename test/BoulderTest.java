package test;
import javafx.scene.input.KeyCode;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.DungeonController;
import unsw.dungeon.domain.Boulder;
import unsw.dungeon.domain.Dungeon;
import unsw.dungeon.domain.Player;
import unsw.dungeon.domain.Switch;
import unsw.dungeon.goal.GoalSubBuilder;
import unsw.dungeon.util.DungeonUtil;

import java.util.ArrayList;

public class BoulderTest {
    private Dungeon dungeon;

    @Before
    public void init() {
        dungeon = new Dungeon(4,4);
    }

    @Test
    public void testBoulderMov() {
        Boulder boulder = new Boulder(1,1);
        Player player = new Player(dungeon,1,2);
        dungeon.addEntity(boulder);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"boulders\"\n" +
                "  }"), dungeon);
        dungeonController.eventHandle(KeyCode.getKeyCode("Up"), player);

        // player from (1,2) to (1,1)
        // boulder from (1,1) to (1,0)
        //Acceptance Criteria: The floor switch is triggered when a boulder is pushed onto it (Switch on or off? it does not work)
        Assert.assertEquals(DungeonUtil.getNode(1,1, dungeon), "Player");
        Assert.assertEquals(DungeonUtil.getNode(1,0, dungeon), "Boulder");
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testBoulderMovWithTwo() {
        Boulder boulder1 = new Boulder(1,1);
        Boulder boulder2 = new Boulder(1,2);
        Player player = new Player(dungeon,1,3);
        dungeon.addEntity(boulder1);
        dungeon.addEntity(boulder2);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"boulders\"\n" +
                "  }"), dungeon);
        dungeonController.eventHandle(KeyCode.getKeyCode("Up"), player);

        // player from (1,2) to (1,1)
        // boulder from (1,1) to (1,0)
        // the player cannot push two boulders ,so he is still here
        Assert.assertEquals(DungeonUtil.getNode(1,3, dungeon), "Player");
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testBoulderGoal() {
        Switch aSwitch = new Switch(1,0);
        Boulder boulder = new Boulder(1,1);
        Player player = new Player(dungeon,1,2);
        dungeon.addEntity(aSwitch);
        dungeon.addEntity(boulder);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"boulders\"\n" +
                "  }"), dungeon);
        dungeonController.eventHandle(KeyCode.getKeyCode("Up"), player);

        // player from (1,2) to (1,1)
        // boulder from (1,1) to (1,0)
        // the player cannot push two boulders ,so he is still here
        //Acceptance Criteria: The player would complete the goal “Having a boulder on all floor switches”
        // after pushing a boulder into each square containing a floor switch.
        Assert.assertTrue(DungeonController.goalBuilder.checkGoal(dungeon));
        System.out.println("-----------------------WIN ----------------------------");
    }

}
