package unsw.dungeon.domain;

public class Sword extends Entity {
    /**
     * Create an entity positioned in square (x,y)
     *
     * @param x
     * @param y
     */
    private int hit = 5;

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public Sword(int x, int y) {
        super(x, y);
    }
}
