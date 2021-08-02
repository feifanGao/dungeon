package test;
import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.input.KeyCode;
import unsw.dungeon.DungeonController;
import unsw.dungeon.domain.Dungeon;
import unsw.dungeon.domain.Enemy;
import unsw.dungeon.domain.Player;
import unsw.dungeon.goal.GoalSubBuilder;

public class EnemyTest {

    private Dungeon dungeon;

    @Before
    public void init() {
        dungeon = new Dungeon(9,10);
    }

    @Test
    public void testEnemyMove () {
        Enemy enemy = new Enemy(8,9);
        dungeon.setPlayer(new Player(dungeon,0, 0));
        dungeon.addEntity(enemy);
        enemy.setDungeon(dungeon);
        while (enemy.getX() > 0 && enemy.getY() >= 0) {
            enemy.moveAutomatic();
        }
        Assert.assertEquals(enemy.getX(),0);
        Assert.assertEquals(enemy.getY(),0);
        System.out.println("-----------------------WIN ----------------------------");
    }
    
    @Test
    public void testPlayerDieMeetingEnemy () {
        //Acceptance Criteria: The player would die upon he is in the squares
    	//immediately to the left, right, above or below to the enemy without a sword.

        Enemy enemy = new Enemy(1,1);
        Player player = new Player(dungeon,1,2);
        dungeon.addEntity(enemy);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        DungeonController dungeonController = new DungeonController(dungeon, new ArrayList<>());
        DungeonController.goalBuilder = new GoalSubBuilder(new JSONObject("{\n" +
                "    \"goal\": \"enemies\"\n" +
                "  }"), dungeon);
        dungeonController.eventHandle(KeyCode.getKeyCode("Up"), player);

        // isAlive true   player alive
        // isAlive false  player die
        Assert.assertFalse(player.isAlive());
        System.out.println("-----------------------WIN ----------------------------");
    }
}
