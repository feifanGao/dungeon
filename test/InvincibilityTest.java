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

public class InvincibilityTest {

    private Dungeon dungeon;

    @Before
    public void init() {
        dungeon = new Dungeon(3,3);
    }

    @Test
    public void testInvincibilityLastTime () {
        //Acceptance Criteria: Invincibility potion only last for 10 seconds and it would disappeared afterwards.
        Invincibility invincibility = new Invincibility(1,1);
        Assert.assertEquals(10, invincibility.getLastTime());
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testInvincibility () {
        //Acceptance Criteria:
        Enemy enemy = new Enemy(1,1);
        Invincibility invincibility = new Invincibility(0,0);
        Player player = new Player(dungeon,1,2);
        player.setInvincibility(invincibility);
        dungeon.addEntity(enemy);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"enemies\"\n" +
                "  }"), dungeon);
        dungeonController.eventHandle(KeyCode.getKeyCode("Up"), player);

        Assert.assertEquals(DungeonUtil.getNode(1,1,dungeon), "Player");
        System.out.println("-----------------------WIN ----------------------------");
    }
}
