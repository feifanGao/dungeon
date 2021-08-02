package unsw.dungeon.state;

import unsw.dungeon.domain.Door;

public class OpenState implements DoorState {

    private Door door;

    public void setDoor(Door door) {
        this.door = door;
    }

    @Override
    public void open() {
        System.out.println("door is already open");
    }

    @Override
    public void close() {
        System.out.println("door is closed");
    }
}
