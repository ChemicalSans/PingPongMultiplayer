package Game;

import java.awt.*;
import java.io.Serializable;
import java.util.Vector;

public class Player implements Serializable {
    public static Vector<Player> players = new Vector<Player>();
    public static int idCounter = 0;
    public int id;
    public Point pos;

    public Player(int x, int y) {
        this.pos = new Point(x,y);
        this.id = idCounter++;

        players.add(this);
    }

    public void delete() {
        players.remove(this);
    }

    @Override
    public String toString() {
        return "Player: " + pos.x + " " + pos.y;
    }



    public static Player getPlayer(int id) {
        for(Player player : players) {
            if(id == player.id) return player;
        }

        return null;
    }
}
