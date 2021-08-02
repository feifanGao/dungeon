package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import unsw.dungeon.domain.*;
import unsw.dungeon.goal.GoalSubBuilder;
import unsw.dungeon.observer.InvincibilityObserver;
import unsw.dungeon.util.DungeonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;
    //private ImageView ground = new ImageView(new Image("images/dirt_0_new.png"));

    private Player player;

    private Dungeon dungeon;

    public static GoalSubBuilder goalBuilder;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        // register InvincibilityObserver
        InvincibilityObserver observer = new InvincibilityObserver(player);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("images/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        KeyCode code = event.getCode();
        eventHandle(code, player);
        GameMessage();
    }

    public void eventHandle(KeyCode code, Player player) {
        int x = player.getX();
        int y = player.getY();

        if (code == KeyCode.getKeyCode("Up")) {
            int nextStep = y - 1;
            String temp = DungeonUtil.getNode(x, nextStep, dungeon);
            if (DungeonUtil.getNode(x , nextStep, dungeon).equals(Boulder.class.getSimpleName())) {
                if (DungeonUtil.getNode(x, nextStep - 1, dungeon).equals(Switch.class.getSimpleName())
                        || DungeonUtil.getNode(x, nextStep - 1, dungeon).equals("")) {
                    DungeonUtil.getEntity(x ,nextStep,dungeon).y().set(nextStep -1);
                    player.moveUp();
                    dungeon.setPlayer(player);
                }
                if (goalBuilder.checkGoal(dungeon)) {
                    dungeon.setSuccess(true);
                }
                return;
            }
            // open the door
            if (DungeonUtil.getNode(x, nextStep, dungeon).equals(Door.class.getSimpleName())) {
                if (Objects.nonNull(player.getKey())) {
                    Door door = (Door) DungeonUtil.getEntity(x, nextStep, dungeon);
                    if (door.getId() == player.getKey().getId()) {
                        player.moveUp();
                        // the key can only once.
                        player.setKey(null);
                        door.open();
                        dungeon.setPlayer(player);
                        return;
                    }
                    else return;
                }
            }
            if ((!temp.equals(Wall.class.getSimpleName()))) {
                player.moveUp();
                meetThingsOrEnemy(player);
            }
            if (goalBuilder.checkGoal(dungeon)) {
                dungeon.setSuccess(true);
            }
        }
        if (code == KeyCode.getKeyCode("Down")) {
            int nextStep = y + 1;
            String temp = DungeonUtil.getNode(x, nextStep, dungeon);
            if (DungeonUtil.getNode(x , nextStep, dungeon).equals(Boulder.class.getSimpleName())) {
                if (DungeonUtil.getNode(x, nextStep +1, dungeon).equals(Switch.class.getSimpleName())
                        || DungeonUtil.getNode(x, nextStep +1, dungeon).equals("")) {
                    DungeonUtil.getEntity(x ,nextStep,dungeon).y().set(nextStep +1);
                    player.moveDown();
                    dungeon.setPlayer(player);

                }
                if (goalBuilder.checkGoal(dungeon)) {
                    dungeon.setSuccess(true);
                }
                return;
            }
            // open the door
            if (DungeonUtil.getNode(x, nextStep, dungeon).equals(Door.class.getSimpleName())) {
                if (Objects.nonNull(player.getKey())) {
                    Door door = (Door) DungeonUtil.getEntity(x, nextStep, dungeon);
                    if (door.getId() == player.getKey().getId()) {
                        player.moveDown();
                        player.setKey(null);
                        door.open();
                        dungeon.setPlayer(player);
                        return;
                    }
                    else return;
                }
            }
            if ((!temp.equals(Wall.class.getSimpleName()))) {
                player.moveDown();
                meetThingsOrEnemy(player);
            }
            if (goalBuilder.checkGoal(dungeon)) {
                dungeon.setSuccess(true);
            }
        }
        if (code == KeyCode.getKeyCode("Left")) {
            int nextStep = x - 1;
            String temp = DungeonUtil.getNode(nextStep, y, dungeon);
            //check if the boulder
            if (DungeonUtil.getNode(nextStep, y, dungeon).equals(Boulder.class.getSimpleName())) {
                // check can move the boulder
                if (DungeonUtil.getNode(nextStep -1, y, dungeon).equals(Switch.class.getSimpleName())
                        || DungeonUtil.getNode(nextStep -1, y, dungeon).equals("")) {
                    DungeonUtil.getEntity(nextStep,y ,dungeon).x().set(nextStep -1);
                    player.moveLeft();
                    dungeon.setPlayer(player);
                }
                // check boulder goal
                if (goalBuilder.checkGoal(dungeon)) {
                    dungeon.setSuccess(true);
                }
                return;
            }
            // open the door
            if (DungeonUtil.getNode(nextStep, y, dungeon).equals(Door.class.getSimpleName())) {
                if (Objects.nonNull(player.getKey())) {
                    Door door = (Door) DungeonUtil.getEntity(nextStep, y, dungeon);
                    if (door.getId() == player.getKey().getId()) {
                        player.moveLeft();
                        player.setKey(null);
                        door.open();
                        dungeon.setPlayer(player);
                        return;
                    }
                    else return;
                }
            }
            // move next
            if ((!temp.equals(Wall.class.getSimpleName()))) {
                player.moveLeft();
                meetThingsOrEnemy(player);

            }
            //check goal
            if (goalBuilder.checkGoal(dungeon)) {
                dungeon.setSuccess(true);
            }
        }
        if (code == KeyCode.getKeyCode("Right")) {
            int nextStep = x +1;
            String temp = DungeonUtil.getNode(nextStep, y, dungeon);
            if (DungeonUtil.getNode(nextStep, y, dungeon).equals(Boulder.class.getSimpleName())) {
                if (DungeonUtil.getNode(nextStep +1, y, dungeon).equals(Switch.class.getSimpleName())
                        || DungeonUtil.getNode(nextStep +1, y, dungeon).equals("")) {
                    DungeonUtil.getEntity(nextStep,y,dungeon).x().set(nextStep +1);
                    player.moveRight();
                    dungeon.setPlayer(player);
                }
                if (goalBuilder.checkGoal(dungeon)) {
                    dungeon.setSuccess(true);
                }
                return;
            }
            if (DungeonUtil.getNode(nextStep, y, dungeon).equals(Door.class.getSimpleName())) {
                if (Objects.nonNull(player.getKey())) {
                    Door door = (Door) DungeonUtil.getEntity(nextStep, y, dungeon);
                    if (door.getId() == player.getKey().getId()) {
                        player.moveRight();
                        player.setKey(null);
                        door.open();
                        dungeon.setPlayer(player);
                        return;
                    }
                    else return;
                }
            }
            if ((!temp.equals(Wall.class.getSimpleName()))) {
                player.moveRight();
                meetThingsOrEnemy(player);
            }
            if (goalBuilder.checkGoal(dungeon)) {
                dungeon.setSuccess(true);
            }
        }
    }

    private void meetThingsOrEnemy(Player player) {
        // Player can carry only one sword at a time
        if (Objects.isNull(player.getSword()) && DungeonUtil.getNode(player.getX(), player.getY(), dungeon).equals(Sword.class.getSimpleName())) {
            // add to play
            player.setSword((Sword) DungeonUtil.getEntity(player.getX(), player.getY(), dungeon));
            // remove from dungeon
            DungeonUtil.removeEntity(player.getX(), player.getY(), dungeon);
            dungeon.setPlayer(player);
            //reload();
        }
        // Player can carry only one Invincibility at a time

        if (Objects.isNull(player.getInvincibility()) && DungeonUtil.getNode(player.getX(), player.getY(), dungeon).equals(Invincibility.class.getSimpleName())) {
            player.setInvincibility((Invincibility) DungeonUtil.getEntity(player.getX(), player.getY(), dungeon));
            DungeonUtil.removeEntity(player.getX(), player.getY(), dungeon);
            dungeon.setPlayer(player);
            //reload();
        }
        // Player can carry only one key at a time
        if (Objects.isNull(player.getKey()) && DungeonUtil.getNode(player.getX(), player.getY(), dungeon).equals(Key.class.getSimpleName())) {
            player.setKey((Key) DungeonUtil.getEntity(player.getX(), player.getX(), dungeon));
            DungeonUtil.removeEntity(player.getX(), player.getY(), dungeon);
            dungeon.setPlayer(player);
            //reload();
        }
        // Player can carry only one bomb at a time
        if (Objects.isNull(player.getKey()) && DungeonUtil.getNode(player.getX(), player.getY(), dungeon).equals(Bomb.class.getSimpleName())) {
            player.setBomb((Bomb) DungeonUtil.getEntity(player.getX(), player.getY(), dungeon));
            DungeonUtil.removeEntity(player.getX(), player.getY(), dungeon);
            dungeon.setPlayer(player);
            //reload();
        }
        if (DungeonUtil.getNode(player.getX(), player.getY(), dungeon).equals(Treasure.class.getSimpleName())) {
            player.getTreasures().add((Treasure) DungeonUtil.getEntity(player.getX(), player.getY(), dungeon));
            DungeonUtil.removeEntity(player.getX(), player.getY(), dungeon);
            dungeon.setPlayer(player);
            //reload();
        }
        //meet enemy
        if (DungeonUtil.getNode(player.getX(), player.getY(), dungeon).equals(Enemy.class.getSimpleName())) {
        	 //if the player collided with enemy when he is in invincible state.
            if (Objects.nonNull(player.getInvincibility()) && player.getInvincibility().getLastTime() > 0) {
                DungeonUtil.removeEntity(player.getX(), player.getY(), dungeon);
                return;
            }
        	if (Objects.nonNull(player.getSword()) && player.getSword().getHit() > 0) {
                player.getSword().setHit(player.getSword().getHit() -1);
                if (player.getSword().getHit() == 0) {
                    //the sword would disappeared after five hits
                    player.setSword(null);
                }
                DungeonUtil.removeEntity(player.getX(), player.getY(), dungeon);
                return;
            }
            //The enemies would be immediately destroyed
           player.setAlive(false);
        }

    }

//    public void reload()  {
//        initialEntities=new ArrayList<>();
//        EntityToImageView entityToImageView=new EntityToImageView(dungeon);
//        entityToImageView.transform();
//        initialEntities=entityToImageView.getImageViewList();
//        initialize();
//
//    }

    private void GameMessage(){
        if (dungeon.isSuccess()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.titleProperty().set("Game Over");
            alert.headerTextProperty().set("you win!!!");
            alert.showAndWait();
            System.exit(0);
        }
        if (!player.isAlive()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.titleProperty().set("Game Over");
            alert.headerTextProperty().set("you die!!!");
            alert.showAndWait();
            System.exit(0);
        }
    }



}



