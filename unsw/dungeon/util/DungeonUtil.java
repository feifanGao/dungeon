package unsw.dungeon.util;

import unsw.dungeon.domain.Dungeon;
import unsw.dungeon.domain.Entity;

import java.util.ArrayList;
import java.util.List;

public final class DungeonUtil {

    public static String getNode(int row, int column, Dungeon dungeon) {
        List<String> result = new ArrayList<>();
        List<Entity> entities = dungeon.getEntities();
        for (Entity node : entities) {
            if (node.getX() == row && node.getY() == column) {
                result.add(node.getClass().getSimpleName());
            }
        }

        if (result.size() == 1) {
            return result.get(0);
        }
        else if (result.size() == 0) {
            return "";
        }
        else {
            for (String r : result) {
                if (r.equals("Boulder") )
                    return r;
            }
            for (String r : result) {
                if (r.equals("Switch"))
                    return r;
            }
            for (String r : result) {
                if (result.contains("Player") && result.contains("Exit"))
                    return "Player";
            }
            for (String r : result) {
                if (!r.equals("Player"))
                    return r;
            }
            return "";
        }
    }

    public static Entity getEntity(int row, int column, Dungeon dungeon) {
        List<Entity> result = new ArrayList<>();
        List<Entity> entities = dungeon.getEntities();
        for (Entity node : entities) {
            if (node.getX() == row && node.getY() == column) {
                result.add(node);
            }
        }

        if (result.size() == 1) {
            return result.get(0);
        }
        else if (result.size() == 0) {
            return null;
        }
        else {
            for (Entity r : result) {
                if (r.getClass().getSimpleName().equals("Boulder"))
                    return r;
            }
            for (Entity r : result) {
                if (r.getClass().getSimpleName().equals("Switch"))
                    return r;
            }
            for (Entity r : result) {
                if (!r.getClass().getSimpleName().equals("Player"))
                    return r;
            }
            return null;
        }
    }

    public static void removeEntity(int x, int y, Dungeon dungeon) {
        String node = getNode(x, y,dungeon);
        List<Entity> result = new ArrayList<>();
        for (Entity e : dungeon.getEntities()) {
            if (!e.getClass().getSimpleName().equals(node)) {
                result.add(e);
            }
        }
        dungeon.setEntities(result);
    }
}
