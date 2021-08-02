package unsw.dungeon;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import unsw.dungeon.domain.*;


import java.util.ArrayList;
import java.util.List;

public class EntityToImageView extends DungeonControllerLoader {
    private Dungeon dungeon;
    private List<ImageView> imageViewList;
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



    public EntityToImageView(Dungeon dungeon) {
        this.dungeon = dungeon;
        imageViewList=new ArrayList<>();
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

    public void transform(){
        List<Entity> entities=dungeon.getEntities();
        for(int i=0;i<entities.size();i++){
            String type=entities.get(i).getClass().getSimpleName();
            int x=entities.get(i).getX();
            int y=entities.get(i).getY();
            switch (type){
                case "Player":
                    Player player = new Player(dungeon, x, y);
                    dungeon.setPlayer(player);
                    onLoad(player);
                    break;
                case "Wall":
                    Wall wall = new Wall(x, y);
                    onLoad(wall);
                    break;
                // TODO Handle other possible entities
                case "Boulder":
                    Boulder boulder =new Boulder(x,y);
                    onLoad(boulder);
                    break;
                case"Switch":
                    Switch aSwitch =new Switch(x,y);
                    onLoad(aSwitch);
                    break;
                case"Enemy":
                    Enemy enemy=new Enemy(x,y);
                    onLoad(enemy);
                    break;
                case"Sword":
                    Sword sword=new Sword(x,y);
                    onLoad(sword);
                    break;
                case"Invincibility":
                    Invincibility invincibility=new Invincibility(x,y);
                    onLoad(invincibility);
                    break;
                case"Bomb":
                    Bomb bomb=new Bomb(x,y);
                    onLoad(bomb);
                    break;
                case"Treasure":
                    Treasure treasure=new Treasure(x,y);
                    onLoad(treasure);
                    break;
                case"Key":
                    Key key=new Key(x,y);
                    onLoad(key);
                    break;
                case"Door":
                    Door door=new Door(x,y);
                    onLoad(door);
                    break;
            }
        }
        imageViewList=super.getEntities();
    }

    public List<ImageView> getImageViewList() {
        return imageViewList;
    }
}
