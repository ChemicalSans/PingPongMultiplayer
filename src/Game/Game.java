package Game;

import java.util.Vector;

public class Game {
    public Vector<Player> players = new Vector<Player>();

    public Game() {


        new Player(this,0,0);
        new Player(this,50,50);

    }


    public Player getPlayer(int id) {
        for(Player player : players) {
            if(id == player.id) return player;
        }

        return null;
    }
}
