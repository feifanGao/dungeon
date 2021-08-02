package test;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.DungeonController;
import unsw.dungeon.domain.Dungeon;
import unsw.dungeon.domain.Player;
import unsw.dungeon.goal.GoalSubBuilder;

import java.util.ArrayList;

public class GoalTest {

    private Dungeon dungeon;

    @Before
    public void init() {
        // no enemy treasure switch exit
        // so all the goal should be achieved
        dungeon = new Dungeon(3,3);
        Player player = new Player(dungeon,1,2);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
    }

    @Test
    public void testExitSubGoal () {


        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"exit\"\n" +
                "  }"), dungeon);
        Assert.assertTrue(DungeonController.goalBuilder.checkGoal(dungeon));
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testEnemySubGoal () {
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"enemies\"\n" +
                "  }"), dungeon);
        Assert.assertTrue(DungeonController.goalBuilder.checkGoal(dungeon));
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testTreasureExitGoal () {
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"treasure\"\n" +
                "  }"), dungeon);
        Assert.assertTrue(DungeonController.goalBuilder.checkGoal(dungeon));
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testBoulderSubGoal () {
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"boulders\"\n" +
                "  }"), dungeon);
        Assert.assertTrue(DungeonController.goalBuilder.checkGoal(dungeon));
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testAdvancedGoal () {
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"AND\",\n" +
                "    \"subgoals\": [\n" +
                "      {\n" +
                "        \"goal\": \"enemies\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"goal\": \"treasure\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }"), dungeon);
        Assert.assertTrue(DungeonController.goalBuilder.checkGoal(dungeon));
        System.out.println("-----------------------WIN ----------------------------");
    }

}
