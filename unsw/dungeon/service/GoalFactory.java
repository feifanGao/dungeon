package unsw.dungeon.service;

import org.json.JSONArray;
import org.json.JSONObject;
import unsw.dungeon.domain.Dungeon;
import unsw.dungeon.util.DungeonUtil;

public class GoalFactory {

    private Dungeon dungeon;

    private boolean finalGoal;

    private JSONObject initJson;

    public GoalFactory(JSONObject initJson, Dungeon dungeon){
        this.initJson = initJson;
        this.dungeon = dungeon;
    }

    private void buildGoal(){
        String goal = initJson.getString("goal");
        boolean localGoal = true;

        if (goal.equals("enemy")) {
            finalGoal =  subGoal(dungeon, "Enemy");
        }
        if (goal.equals("boulder")) {
            finalGoal =  subGoal(dungeon, "Boulder");
        }
        if (goal.equals("exit")) {
            finalGoal =  subGoal(dungeon, "Exit");
        }
        if (goal.equals("treasure")) {
            finalGoal =  subGoal(dungeon, "Treasure");
        }
        if (goal.equals("AND")) {
            JSONArray subGoals = initJson.getJSONArray("subgoals");
            finalGoal = true;
            for (int i = 0; i < subGoals.length(); i++) {
                finalGoal = finalGoal && subGoal(dungeon, subGoals.getJSONObject(i).getString("goal"));
            }
        }
        if (goal.equals("OR")) {
            JSONArray subGoals = initJson.getJSONArray("subgoals");
            finalGoal = false;
            for (int i = 0; i < subGoals.length(); i++) {
                finalGoal = finalGoal || subGoal(dungeon, subGoals.getJSONObject(i).getString("goal"));
            }
        }


    }

    public boolean checkGoal(Dungeon dungeon){
        this.dungeon = dungeon;
        buildGoal();
        return finalGoal;

    }

    private boolean subGoal(Dungeon dungeon, String goalName){
        for (int i = 0; i < dungeon.getWidth(); i++) {
            for (int j = 0; j < dungeon.getHeight(); j++) {
                if (DungeonUtil.getNodeByRowColumnIndex(i, j, dungeon).equals(goalName)) {
                    return false;
                }
            }
        }
        return true;
    }



}
