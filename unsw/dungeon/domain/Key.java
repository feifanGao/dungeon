package unsw.dungeon.domain;

public class Key extends Entity {
    /**
     * Create an entity positioned in square (x,y)
     *
     * @param x
     * @param y
     */
    private int id;

    public Key(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }

    public Key(int x, int y) {
        super(x, y);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
