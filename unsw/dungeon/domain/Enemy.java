package unsw.dungeon.domain;

import unsw.dungeon.util.DungeonUtil;

import java.util.Objects;

public class Enemy extends Entity {

    /**
     * Create an entity positioned in square (x,y)
     *
     * @param x
     * @param y
     */
    public Enemy(int x, int y) {
        super(x, y);
    }

    private Dungeon dungeon;

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public void moveAutomatic() {
        if (Objects.nonNull(dungeon.getPlayer().getInvincibility())
                && dungeon.getPlayer().getInvincibility().getLastTime() > 0) { // far away from player
            // distance of x label is smaller than y, move x
            if (Math.abs(dungeon.getPlayer().getX() - this.getX()) < Math.abs(dungeon.getPlayer().getY() - this.getY())) {
                if (dungeon.getPlayer().getX() - this.getX() > 0) {
                    //not the wall
                    x().set(this.getX() - 1);
                } else {
                    x().set(this.getX() + 1);
                }
            }
            else {
                if (dungeon.getPlayer().getY() - this.getY() > 0) {
                    y().set(this.getY() - 1);
                } else {
                    y().set(this.getY() + 1);
                }
            }
        } else {
            // distance of x label is bigger than y, move x
            if (Math.abs(dungeon.getPlayer().getX() - this.getX()) > Math.abs(dungeon.getPlayer().getY() - this.getY())) {
                if (dungeon.getPlayer().getX() - this.getX() > 0) {
                    x().set(this.getX() + 1);
                } else {
                    x().set(this.getX() - 1);
                }
            } else {
                if (dungeon.getPlayer().getY() - this.getY() > 0) {
                    y().set(this.getY() + 1);
                } else {
                    y().set(this.getY() - 1);
                }
            }
        }

    };
}
