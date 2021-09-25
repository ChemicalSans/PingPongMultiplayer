package Game;

import java.awt.*;
import java.io.Serializable;

public class Player implements Serializable {
    public Point pos;

    public Player(int x, int y) {
        this.pos = new Point(x,y);
    }

    @Override
    public String toString() {
        return "Player: " + pos.x + " " + pos.y;
    }
}
