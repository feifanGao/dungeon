package unsw.dungeon.goal;

import unsw.dungeon.domain.Dungeon;
import unsw.dungeon.util.DungeonUtil;

public class ExitSubGoal implements SubGoal{

    private Dungeon dungeon;

    public ExitSubGoal(Dungeon dungeon){
        this.dungeon = dungeon;
    }

    @Override
    public boolean subGoal() {
        for (int i = 0; i < dungeon.getWidth(); i++) {
            for (int j = 0; j < dungeon.getHeight(); j++) {
                if (DungeonUtil.getNode(i, j, dungeon).equals("Exit")) {
                    return false;
                }
            }
        }
        return true;
    }
}
