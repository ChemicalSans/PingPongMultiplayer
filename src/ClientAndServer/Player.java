package ClientAndServer;

import com.sun.javafx.geom.Vec2d;

import java.awt.*;
import java.io.Serializable;

public class Player implements Serializable {
    public int x;
    public int y;
    public Color color;
    public int id;
    public static int idCount;

    public Player() {
        this.x = 0;
        this.y = 0;
        this.id = idCount++;
        this.color = Color.WHITE;
    }

    public int getId() {
        return id;
    }
}
