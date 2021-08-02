package test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.domain.*;
import unsw.dungeon.util.DungeonUtil;

public class DungeonTest {

    private Dungeon dungeon;

    @Before
    public void init() {
        dungeon = new Dungeon(3,3);
        Wall wall = new Wall(1,1);
        Sword sword = new Sword(2,2);
        Enemy enemy = new Enemy(0,1);
        Invincibility invincibility = new Invincibility(0,0);
        Key key = new Key(1,0);
        Player player = new Player(dungeon,1,2);
        dungeon.addEntity(wall);
        dungeon.addEntity(sword);
        dungeon.addEntity(enemy);
        dungeon.addEntity(invincibility);
        dungeon.addEntity(key);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
    }

    @Test
    public void testDungeonNodeGet () {
        Assert.assertEquals(DungeonUtil.getNode(1,1,dungeon), "Wall");
        Assert.assertEquals(DungeonUtil.getNode(2,2,dungeon), "Sword");
        Assert.assertEquals(DungeonUtil.getNode(0,1,dungeon), "Enemy");
        Assert.assertEquals(DungeonUtil.getNode(0,0,dungeon), "Invincibility");
        Assert.assertEquals(DungeonUtil.getNode(1,0,dungeon), "Key");
        Assert.assertEquals(DungeonUtil.getNode(1,2,dungeon), "Player");
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testDungeonEntityGet () {
        Assert.assertTrue(DungeonUtil.getEntity(1,1,dungeon) instanceof Wall);
        Assert.assertTrue(DungeonUtil.getEntity(2,2,dungeon) instanceof Sword);
        Assert.assertTrue(DungeonUtil.getEntity(0,1,dungeon) instanceof Enemy);
        Assert.assertTrue(DungeonUtil.getEntity(0,0,dungeon) instanceof Invincibility);
        Assert.assertTrue(DungeonUtil.getEntity(1,0,dungeon) instanceof Key);
        Assert.assertTrue(DungeonUtil.getEntity(1,2,dungeon) instanceof Player);
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testGetPlayerAndEnemyInOnePosition () {
        dungeon.addEntity(new Enemy(1,2));
        Assert.assertEquals("Enemy", DungeonUtil.getNode(1, 2, dungeon));
        System.out.println("-----------------------WIN ----------------------------");
    }


}
