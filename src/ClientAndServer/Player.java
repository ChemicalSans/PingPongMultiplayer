package ClientAndServer;

import com.sun.javafx.geom.Vec2d;

import java.awt.*;
import java.io.Serializable;

public class Player implements Serializable {
    public int x;
    public int y;
    public Color color;
    public int id;

    public Player(int id) {
        this.x = 0;
        this.y = 0;
        this.id = id;
        this.color = Color.WHITE;
    }

}
