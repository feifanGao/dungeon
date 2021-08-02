package unsw.dungeon.domain;

public class Boudler extends Entity {
    /**
     * Create an entity positioned in square (x,y)
     *
     * @param x
     * @param y
     */
    public Boudler(int x, int y) {
        super(x, y);
    }


//    private Dungeon dungeon;
//    /**
//     * Create an entity positioned in square (x,y)
//     *
//     * @param x
//     * @param y
//     */
//    public Boudler(Dungeon dungeon,int x, int y) {
//
//        super(x, y);
//        this.dungeon=dungeon;
//    }
//
//    public void moveUp(){
//        if(getX()>0)
//            y().set(getY()-1);
//    }
//    public void moveDown(){
//        if(getY()< dungeon.getHeight()-1)
//            y().set(getY() +1);
//    }
//
//    public void moveLeft(){
//        if(getX()>0)
//            x().set(getX()-1);
//    }
//
//    public void moveRight(){
//        if(getX()<dungeon.getWidth()-1)
//            x().set(getX()+1);
//    }
}
