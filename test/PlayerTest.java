package test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.domain.Dungeon;
import unsw.dungeon.domain.Player;
import unsw.dungeon.util.DungeonUtil;

public class PlayerTest {

    private Dungeon dungeon;

    private Player player;

    @Before
    public void init() {

        dungeon = new Dungeon(5,5);
        player = new Player(dungeon,2,2);
        dungeon.addEntity(player);
    }

    @Test
    public void testBasicMoveUp() {
        player.moveUp();
        Assert.assertTrue(DungeonUtil.getNode(2,1,dungeon).equals(Player.class.getSimpleName()));
        System.out.println("-----------------------WIN ----------------------------");
        
        player.moveDown();
        Assert.assertTrue(DungeonUtil.getNode(2,2,dungeon).equals(Player.class.getSimpleName()));
        System.out.println("-----------------------WIN ----------------------------");
        
        player.moveLeft();
        Assert.assertTrue(DungeonUtil.getNode(1,2,dungeon).equals(Player.class.getSimpleName()));
        System.out.println("-----------------------WIN ----------------------------");
        
        player.moveRight();
        Assert.assertTrue(DungeonUtil.getNode(2,2,dungeon).equals(Player.class.getSimpleName()));
        System.out.println("-----------------------WIN ----------------------------");
        
    }

}
