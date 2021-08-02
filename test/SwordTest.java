package test;
import javafx.scene.input.KeyCode;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.DungeonController;
import unsw.dungeon.domain.Dungeon;
import unsw.dungeon.domain.Enemy;
import unsw.dungeon.domain.Player;
import unsw.dungeon.domain.Sword;
import unsw.dungeon.goal.GoalSubBuilder;
import unsw.dungeon.util.DungeonUtil;

import java.util.ArrayList;

public class SwordTest {

    private Dungeon dungeon;

    @Before
    public void init() {
        dungeon = new Dungeon(3,3);
    }

    @Test
    public void testSword () {
        //Acceptance Criteria:
        //the sword would be capable for five hits
        Sword sword = new Sword(0,0);
        Assert.assertEquals(5, sword.getHit());
        System.out.println("-----------------------WIN ----------------------------");
    }


    @Test
    public void testSwordDisappear () {
        //Acceptance Criteria:
        //the sword would be capable for five hits
        Sword sword = new Sword(0,0);
        // init the sword with 1 hit, the sword will disappear when kill enemy
        sword.setHit(1);
        Enemy enemy = new Enemy(1,1);
        Player player = new Player(dungeon,1,2);
        player.setSword(sword);
        dungeon.addEntity(enemy);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"enemies\"\n" +
                "  }"), dungeon);
        dungeonController.eventHandle(KeyCode.getKeyCode("Up"), player);

        // the player carry on sword which hits is 5, from (1,2) to (1,1)
        Assert.assertNull(player.getSword());
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testEnemyGoal () {
        //Acceptance Criteria:
        //The player would complete the goal “Destroying all enemies” after destroying all enemies.
        Sword sword = new Sword(0,0);
        Enemy enemy = new Enemy(1,1);
        Player player = new Player(dungeon,1,2);
        player.setSword(sword);
        dungeon.addEntity(enemy);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"enemies\"\n" +
                "  }"), dungeon);
        dungeonController.eventHandle(KeyCode.getKeyCode("Up"), player);

        // the player carry on sword which hits is 5, from (1,2) to (1,1)
        // the enemy will be killed and and the enemy goal will be achieved
        Assert.assertTrue(DungeonController.goalBuilder.checkGoal(dungeon));
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testSwordKillEnemy () {
        //Acceptance Criteria:
        //one enemy would be destroyed by one hit of the sword
        Sword sword = new Sword(0,0);
        Enemy enemy = new Enemy(1,1);
        Player player = new Player(dungeon,1,2);
        player.setSword(sword);
        dungeon.addEntity(enemy);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"enemies\"\n" +
                "  }"), dungeon);
        dungeonController.eventHandle(KeyCode.getKeyCode("Up"), player);

        // the player carry on sword which hits is 5, from (1,2) to (1,1)
        // the enemy will be killed and the hits of sword will be 4
        Assert.assertEquals(player.getSword().getHit(), 4);
        Assert.assertEquals(DungeonUtil.getNode(1,1,dungeon), "Player");
        System.out.println("-----------------------WIN ----------------------------");
    }
}
