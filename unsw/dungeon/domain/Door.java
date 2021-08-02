package unsw.dungeon.domain;

import unsw.dungeon.state.CloseState;
import unsw.dungeon.state.OpenState;
import unsw.dungeon.state.DoorState;

public class Door extends Entity {

    /**
     * state Pattern； for the state of door
     */
    private final static DoorState OPEN_DOOR_STATE = new OpenState();

    private final static DoorState CLOSE_DOOR_STATE = new CloseState();

    private DoorState currentDoorState = CLOSE_DOOR_STATE;

    public void open() {
        this.currentDoorState.open();
        this.currentDoorState = OPEN_DOOR_STATE;
    }

    public void close() {
        this.currentDoorState.close();
        this.currentDoorState = CLOSE_DOOR_STATE;
    }

    private int id;

    /**
     * Create an entity positioned in square (x,y)
     *
     * @param x
     * @param y
     */
    public Door(int x, int y) {
        super(x, y);
    }

    public Door(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DoorState getCurrentDoorState() {
        return currentDoorState;
    }

    public void setCurrentDoorState(DoorState currentDoorState) {
        this.currentDoorState = currentDoorState;
    }
}
