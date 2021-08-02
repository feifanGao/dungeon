package unsw.dungeon.state;

import unsw.dungeon.domain.Door;

public class CloseState implements DoorState {

    private Door door;

    public void setDoor(Door door) {
        this.door = door;
    }

    @Override
    public void open() {
        System.out.println("door is open");
    }

    @Override
    public void close() {
        System.out.println("door is already closed");
    }
}
