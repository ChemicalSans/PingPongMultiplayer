package Game;

import java.awt.*;
import java.io.Serializable;
import java.util.Vector;

public class Player implements Serializable {
    public static int idCounter = 0;
    public int id;
    public Point pos;
    public Game game;

    public Player(Game game, int x, int y) {
        this.pos = new Point(x,y);
        this.id = idCounter++;
        this.game = game;

        game.players.add(this);
    }

    public void delete() {
        game.players.remove(this);
    }

    @Override
    public String toString() {
        return "Player: " + pos.x + " " + pos.y;
    }




}
