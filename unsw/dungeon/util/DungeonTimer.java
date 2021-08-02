package unsw.dungeon.util;

import java.util.Timer;
import java.util.TimerTask;

public final class DungeonTimer {

    public static void timer(int lastTime) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            //two seconds for redundant for start and end timer
            int tmpTime = lastTime -2;
            public void run() {
                if (tmpTime < 0) {
                    timer.cancel();
                }
                tmpTime--;
                int ss = tmpTime + 2;
//                System.out.println("last" + ss + "seconds");
            }
        }, 0, 1000);
    }

}
