package unsw.dungeon.domain;

import unsw.dungeon.util.DungeonTimer;
import unsw.dungeon.util.DungeonUtil;

public class Bomb extends Entity {
    /**
     * Create an entity positioned in square (x,y)
     *
     * @param x
     * @param y
     */
    // true ---- lit bomb
    // false ----- unlit bomb
    private boolean status;

    private int lastTime = 5;

    private Dungeon dungeon;

    public void setDungeon(Dungeon dungeon) {
        this.dungeon =  dungeon;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    public void fire(){
        DungeonTimer.timer(lastTime);
        status = true;

        bombSingOrient(getX() - 1, getY());
        bombSingOrient(getX() + 1, getY());
        bombSingOrient(getX() , getY() - 1);
        bombSingOrient(getX() , getY() + 1);
    }

    public Bomb(int x, int y) {
        super(x, y);
    }

    private void bombSingOrient(int x ,int y) {
        if (DungeonUtil.getNode(x, y, dungeon).equals(Boulder.class.getSimpleName()) ||
                DungeonUtil.getNode(x, y, dungeon).equals(Enemy.class.getSimpleName())) {
            dungeon.getEntities().remove(DungeonUtil.getEntity(x, y, dungeon));
        }
        if (DungeonUtil.getNode(x, y, dungeon).equals(Player.class.getSimpleName())) {
            dungeon.getPlayer().setAlive(false);
        }
    }
}
