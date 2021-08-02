package unsw.dungeon.goal;

import org.json.JSONArray;
import org.json.JSONObject;
import unsw.dungeon.domain.Dungeon;

public class GoalSubBuilder {

    private Dungeon dungeon;

    private JSONObject initJson;

    public GoalSubBuilder(JSONObject initJson, Dungeon dungeon){
        this.initJson = initJson;
        this.dungeon = dungeon;
    }

    private boolean buildGoal(JSONObject jsonObject) {
        String goal = jsonObject.getString("goal");

        if (goal.equals("enemies")) {
            return new EnemySubGoal(dungeon).subGoal();
        }
        if (goal.equals("boulders")) {
            return new BoulderSubGoal(dungeon).subGoal();
        }
        if (goal.equals("exit")) {
            return new ExitSubGoal(dungeon).subGoal();
        }
        if (goal.equals("treasure")) {
            return new TreasureSubGoal(dungeon).subGoal();
        }
        if (goal.equals("AND")) {
            JSONArray subGoals = initJson.getJSONArray("subgoals");
            boolean tmp = true;
            for (int i = 0; i < subGoals.length(); i++) {
                tmp = tmp && buildGoal(subGoals.getJSONObject(i));
            }
            return tmp;
        }
        if (goal.equals("OR")) {
            JSONArray subGoals = initJson.getJSONArray("subgoals");
            boolean tmp = false;
            boolean exit = true;
            for (int i = 0; i < subGoals.length(); i++) {
                // exit goal must be achieved in the end
                if (subGoals.getJSONObject(i).getString("goal").equals("exit")) {
                    exit = buildGoal(subGoals.getJSONObject(i));
                } else {
                    tmp = tmp || buildGoal(subGoals.getJSONObject(i));
                }

            }
            return tmp && exit;
        }
        return false;
    }

    public boolean checkGoal(Dungeon dungeon){
        this.dungeon = dungeon;
        return buildGoal(initJson);
    }





}
