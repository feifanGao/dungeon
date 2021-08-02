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
import unsw.dungeon.goal.GoalSubBuilder;

import java.util.ArrayList;

public class ExitTest {

    private Dungeon dungeon;

    @Before
    public void init() {
        dungeon = new Dungeon(3,3);
    }

    @Test
    public void testExit() {
        Exit exit = new Exit(1,1);
        Assert.assertEquals(exit.getX(), 1);
        Assert.assertEquals(exit.getY(), 1);
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testExitGoal() {
        Exit exit = new Exit(0,0);
        Player player = new Player(dungeon,0,1);
        dungeon.addEntity(exit);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"exit\"\n" +
                "  }"), dungeon);
        dungeonController.eventHandle(KeyCode.getKeyCode("Up"), player);

        //Acceptance Criteria: The player would complete the goal “Getting to an exit” after moving into the exit.
        Assert.assertTrue(DungeonController.goalBuilder.checkGoal(dungeon));
        System.out.println("-----------------------WIN ----------------------------");

    }
}
