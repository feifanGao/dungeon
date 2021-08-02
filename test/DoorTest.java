package test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import unsw.dungeon.domain.Door;
import unsw.dungeon.domain.Dungeon;
//import unsw.dungeon.state.DoorState;
import unsw.dungeon.state.OpenState;

public class DoorTest {

    private Dungeon dungeon;

    @Before
    public void init() {
        dungeon = new Dungeon(3,3);
    }

    @Test
    public void testDoorOpen () {
        Door door = new Door(1,1);
        door.open();
        Assert.assertTrue(door.getCurrentDoorState() instanceof OpenState);
        System.out.println("-----------------------WIN ----------------------------");
    }

    @Test
    public void testDoorPosition () {
        Door door = new Door(1,1);
        Assert.assertEquals(door.getX(), 1);
        Assert.assertEquals(door.getY(), 1);
        System.out.println("-----------------------WIN ----------------------------");
    }
}
