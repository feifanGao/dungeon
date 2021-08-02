package unsw.dungeon.observer;

import unsw.dungeon.domain.Invincibility;
import unsw.dungeon.domain.Player;

public class InvincibilityObserver implements Observer
{
    protected Player player;

    private Invincibility invincibility;

    public InvincibilityObserver(Player player) {
        this.player = player;
        this.player.addObserver(this);
    }

    public Invincibility getInvincibility() {
        return invincibility;
    }

    public void setInvincibility(Invincibility invincibility) {
        this.invincibility = invincibility;
    }

    @Override
    public void update() {
        System.out.println("get the invincibility potion, start timer");
        invincibility.startTimer();
    }
}
