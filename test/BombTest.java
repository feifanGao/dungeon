package test;
import javafx.scene.input.KeyCode;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.DungeonController;
import unsw.dungeon.domain.*;
import unsw.dungeon.goal.GoalSubBuilder;
import unsw.dungeon.util.DungeonUtil;

import java.util.ArrayList;

public class BombTest {

    private Dungeon dungeon;

    @Before
    public void init() {
        dungeon = new Dungeon(3,3);
    }


    @Test
    public void testBomb () {
        //Acceptance Criteria:
        //The fuse burns down for 5 seconds before the bomb explodes
        Bomb bomb = new Bomb(0,0);
        Assert.assertEquals(5, bomb.getLastTime());
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testBombPickUp () {
        //Acceptance Criteria:
        //the player would carry a bomb if he or she moves into the squares containing it
        //Pick it up test
        Bomb bomb = new Bomb(1,1);
        Player player = new Player(dungeon,1,2);
        dungeon.addEntity(bomb);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"enemies\"\n" +
                "  }"), dungeon);
        dungeonController.eventHandle(KeyCode.getKeyCode("Up"), player);

        // player from (1,2) to (1,1)
        // behind the player is bomb , so the player move up and pick up the bomb
        Assert.assertNotNull(player.getBomb());
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testFire () {
        Bomb bomb = new Bomb(1,1);
        Player player = new Player(dungeon,1,0);
        bomb.setDungeon(dungeon);
        dungeon.addEntity(bomb);
        dungeon.addEntity(new Enemy(0 ,1));
        dungeon.addEntity(new Boulder(1,2));
        dungeon.addEntity(new Enemy(2,1));
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"enemies\"\n" +
                "  }"), dungeon);
        bomb.fire();
        Assert.assertTrue(bomb.isStatus());
        //Acceptance Criteria: If the player is in one of these squares they die.
        Assert.assertNull(DungeonUtil.getEntity(0, 1, dungeon));
        Assert.assertNull(DungeonUtil.getEntity(1, 2, dungeon));

        ///Acceptance Criteria: If the player is in one of these squares they die.
        Assert.assertFalse(dungeon.getPlayer().isAlive());

        //Acceptance Criteria: The player would complete the goal “Destroying all enemies” after destroying all enemies.
        Assert.assertTrue(DungeonController.goalBuilder.checkGoal(dungeon));
        Assert.assertFalse(dungeon.getPlayer().isAlive());
        System.out.println("-----------------------WIN ----------------------------");
    }


}
