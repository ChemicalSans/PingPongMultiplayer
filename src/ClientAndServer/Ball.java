package ClientAndServer;

import com.sun.javafx.geom.Vec2d;

import java.util.Vector;

public class Ball {
    public Vec2d pos;                  //Ball position
    public Vec2d velocity;             //Geschwindigkeit
    public Vector<Collider> colliders; //Alle Collider
    boolean col;                       //collision

    public Ball(Vec2d pos, Vec2d velocity, Vector<Collider> colliders) {
        this.pos = pos;
        this.velocity = velocity;
        this.colliders = colliders;
    }


    public void move() {
        //Hier der code für Ball move



        //überprüft ob der ball mit einemobjekt kolidiert ist was vorher überprüft wurde
        if(col == true){
            velocity.x*=-1;
            velocity.y*=-1;
        }
    }

}
