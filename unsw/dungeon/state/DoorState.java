package unsw.dungeon.state;

import unsw.dungeon.domain.Door;

public interface DoorState {

    public abstract void open();

    public abstract void close();
}
