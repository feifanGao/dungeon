package unsw.dungeon.domain;

import unsw.dungeon.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;

    private boolean alive = true;

    private Sword sword;

    private Key key;

    private Bomb bomb;

    private List<Treasure> treasures = new ArrayList<>();

    private Invincibility invincibility;

    private List<Observer> observers = new ArrayList<>();

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public void moveUp() {
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // notify all Observer
    public void notifyInvincibilityObserver() {
        if(observers != null && observers.size()>0){
            for (Observer observer : observers) {
                if (observer.getClass().getSimpleName().equals("InvincibilityObserver")) {
                    observer.update();
                }
            }
        }
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Sword getSword() {
        return sword;
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }

    public List<Treasure> getTreasures() {
        return treasures;
    }

    public void setTreasures(List<Treasure> treasures) {
        this.treasures = treasures;
    }

    public Invincibility getInvincibility() {
        return invincibility;
    }

    public void setInvincibility(Invincibility invincibility) {
        // notify invincibility timer start
        notifyInvincibilityObserver();
        this.invincibility = invincibility;
    }
}
