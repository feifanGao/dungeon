package unsw.dungeon;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import unsw.dungeon.domain.*;
import unsw.dungeon.goal.GoalSubBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader() {
    }

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("src/dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        JSONObject goal = json.getJSONObject("goal-condition");
        // init goalBuilder
        DungeonController.goalBuilder = new GoalSubBuilder(goal, dungeon);
        return dungeon;
    }

    public void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "boulder":
            Boulder boulder =new Boulder(x,y);
            onLoad(boulder);
            entity= boulder;
            break;
        case"switch":
            Switch aSwitch =new Switch(x,y);
            onLoad(aSwitch);
            entity= aSwitch;
            break;
        case"enemy":
            Enemy enemy=new Enemy(x,y);
            onLoad(enemy);
            entity=enemy;
            break;
        case"sword":
            Sword sword=new Sword(x,y);
            onLoad(sword);
            entity=sword;
            break;
        case"invincibility":
            Invincibility invincibility=new Invincibility(x,y);
            onLoad(invincibility);
            entity=invincibility;
            break;
        case"bomb":
            Bomb bomb=new Bomb(x,y);
            onLoad(bomb);
            entity=bomb;
            break;
        case"treasure":
            Treasure treasure=new Treasure(x,y);
            onLoad(treasure);
            entity=treasure;
            break;
        case"key":
            Key key=new Key(x,y);
            onLoad(key);
            entity=key;
            break;
        case"closeDoor":
            Door door=new Door(x,y);
            onLoad(door);
            entity=door;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;
            break;

        }


        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Player player);
    public abstract void onLoad(Wall wall);
    public abstract void onLoad(Boulder boulder);
    public abstract void onLoad(Switch aSwitch);
    public abstract void onLoad(Bomb bomb);
    public abstract void onLoad(Treasure treasure);
    public abstract void onLoad(Invincibility invincibility);
    public abstract void onLoad(Sword sword);
    public abstract void onLoad(Key key);
    public abstract void onLoad(Door door);
    public abstract void onLoad(Enemy enemy);
    public abstract void onLoad(Exit exit);


}
