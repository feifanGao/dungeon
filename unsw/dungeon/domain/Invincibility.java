package unsw.dungeon.domain;

import unsw.dungeon.util.DungeonTimer;

public class Invincibility extends Entity  {
    /**
     * Create an entity positioned in square (x,y)
     *
     * @param x
     * @param y
     */
    private int lastTime = 10;

    public void startTimer() {
        DungeonTimer.timer(lastTime);
    }

    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    public Invincibility(int x, int y) {
        super(x, y);
    }
}
