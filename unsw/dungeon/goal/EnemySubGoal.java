package unsw.dungeon.goal;


import unsw.dungeon.domain.Dungeon;
import unsw.dungeon.util.DungeonUtil;

public class EnemySubGoal implements SubGoal{

    private Dungeon dungeon;

    public EnemySubGoal(Dungeon dungeon){
        this.dungeon = dungeon;
    }

    @Override
    public boolean subGoal() {
        for (int i = 0; i < dungeon.getWidth(); i++) {
            for (int j = 0; j < dungeon.getHeight(); j++) {
                if (DungeonUtil.getNode(i, j, dungeon).equals("Enemy")) {
                    return false;
                }
            }
        }
        return true;
    }
}
