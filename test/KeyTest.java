package test;
import javafx.scene.input.KeyCode;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.DungeonController;
import unsw.dungeon.domain.Door;
import unsw.dungeon.domain.Dungeon;
import unsw.dungeon.domain.Key;
import unsw.dungeon.domain.Player;
import unsw.dungeon.goal.GoalSubBuilder;
import unsw.dungeon.state.CloseState;
import unsw.dungeon.state.OpenState;

import java.util.ArrayList;

public class KeyTest {

    private Dungeon dungeon;

    @Before
    public void init() {
        dungeon = new Dungeon(3,3);
    }

    @Test
    public void testKeyOpenDoor () {
        Door d = new Door(1,1,1);
        Key key = new Key(0,0,1);
        Player player = new Player(dungeon,1,2);
        player.setKey(key);
        dungeon.addEntity(d);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"boulders\"\n" +
                "  }"), dungeon);
        dungeonController.eventHandle(KeyCode.getKeyCode("Up"), player);

        // the player carry on key which id number is 1, from (1,2) to (1,1)
        // the id number of door is also 1, so the door should be open

        //Acceptance Criteria: the player moves through the door if he holds the correct key
        Assert.assertEquals(dungeon.getPlayer().getX(), 1);
        Assert.assertEquals(dungeon.getPlayer().getY(), 1);
        //Acceptance Criteria: the door remains open after opened
        Assert.assertTrue(d.getCurrentDoorState() instanceof OpenState);
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testKeyCannotOpenDoor () {
        Door d = new Door(1,1,2);
        Key key = new Key(0,0,1);
        Player player = new Player(dungeon,1,2);
        player.setKey(key);
        dungeon.addEntity(d);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"boulders\"\n" +
                "  }"), dungeon);
        dungeonController.eventHandle(KeyCode.getKeyCode("Up"), player);

        // Acceptance Criteria: the player remains at the square in front of the door
        // if he try to move through the door without the correct key(can not door open without key
        Assert.assertEquals(dungeon.getPlayer().getX(), 1);
        Assert.assertEquals(dungeon.getPlayer().getY(), 2);

        //the door remains closed without the correct key
        Assert.assertTrue(d.getCurrentDoorState() instanceof CloseState);
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testOnlyOneKeyBeCarry () {
        Key key1 = new Key(0,0,2);
        Key key = new Key(0,0,1);
        Player player = new Player(dungeon,1,2);
        player.setKey(key);
        player.setKey(key1);
        dungeon.addEntity(key1);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"boulders\"\n" +
                "  }"), dungeon);
        dungeonController.eventHandle(KeyCode.getKeyCode("Up"), player);

        //Acceptance Criteria: Player can carry only one key at a time
        Assert.assertEquals(player.getKey().getId(),2);
        System.out.println("-----------------------WIN ----------------------------");
    }
}
