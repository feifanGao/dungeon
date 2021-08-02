package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import unsw.dungeon.domain.*;
import unsw.dungeon.observer.InvincibilityObserver;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {


    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image exitImage;
    private Image enemyImage;
    private Image swordImage;
    private Image boudlerImage;
    private Image floorSwitchImage;
    private Image treasureImage;
    private Image bombImage;
    private Image invincibilityImage;
    private Image doorImage;
    private Image keyImage;


    public DungeonControllerLoader() {
        entities = new ArrayList<>();
        playerImage = new Image("images/human_new.png");
        wallImage = new Image("images/brick_brown_0.png");
        exitImage=new Image("images/exit.png");
        enemyImage=new Image("images/deep_elf_master_archer.png");
        swordImage=new Image("images/greatsword_1_new.png");
        boudlerImage=new Image("images/boulder.png");
        floorSwitchImage=new Image("/images/pressure_plate.png");
        treasureImage=new Image("images/gold_pile.png");
        bombImage=new Image("images/bomb_unlit.png");
        invincibilityImage=new Image("images/brilliant_blue_new.png");
        doorImage=new Image("images/closed_door.png");
        keyImage=new Image("images/key.png");
    }

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("images/human_new.png");
        wallImage = new Image("images/brick_brown_0.png");
        exitImage=new Image("images/exit.png");
        enemyImage=new Image("images/deep_elf_master_archer.png");
        swordImage=new Image("images/greatsword_1_new.png");
        boudlerImage=new Image("images/boulder.png");
        floorSwitchImage=new Image("/images/pressure_plate.png");
        treasureImage=new Image("images/gold_pile.png");
        bombImage=new Image("images/bomb_unlit.png");
        invincibilityImage=new Image("images/brilliant_blue_new.png");
        doorImage=new Image("images/closed_door.png");
        keyImage=new Image("images/key.png");





    }


    @Override
    public void onLoad(Player player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }

    @Override
    public void onLoad(Boulder boulder) {
        ImageView view=new ImageView(boudlerImage);
        addEntity(boulder,view);
    }

    @Override
    public void onLoad(Switch aSwitch) {
        ImageView view =new ImageView(floorSwitchImage);
        addEntity(aSwitch,view);
    }

    @Override
    public void onLoad(Bomb bomb) {
        ImageView view=new ImageView(bombImage);
        addEntity(bomb,view);

    }

    @Override
    public void onLoad(Treasure treasure) {
        ImageView view= new ImageView(treasureImage);
        addEntity(treasure,view);

    }

    @Override
    public void onLoad(Invincibility invincibility) {
        ImageView view=new ImageView(invincibilityImage);
        addEntity(invincibility,view);
    }

    @Override
    public void onLoad(Sword sword) {
        ImageView view=new ImageView(swordImage);
        addEntity(sword,view);

    }

    @Override
    public void onLoad(Key key) {
        ImageView view= new ImageView(keyImage);
        addEntity(key,view);
    }

    @Override
    public void onLoad(Door door) {
        ImageView view=new ImageView(doorImage);
        addEntity(door,view);

    }

    @Override
    public void onLoad(Enemy enemy) {

        ImageView view=new ImageView(enemyImage);
        addEntity(enemy,view);
    }

    @Override
    public void onLoad(Exit exit) {

        ImageView view=new ImageView(exitImage);
        addEntity(exit,view);
    }


    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    public List<ImageView> getEntities() {
        return entities;
    }

    public void setEntities(List<ImageView> entities) {
        this.entities = entities;
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }


    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }



}
