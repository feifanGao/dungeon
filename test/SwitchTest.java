package test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.domain.Dungeon;
import unsw.dungeon.domain.Switch;

public class SwitchTest {
    private Dungeon dungeon;

    @Before
    public void init() {
        dungeon = new Dungeon(3,3);
    }

    @Test
    public void testSwitch() {
        Switch exit = new Switch(1,1);
        Assert.assertEquals(exit.getX(), 1);
        Assert.assertEquals(exit.getY(), 1);
        System.out.println("-----------------------WIN ----------------------------");
    }
}
