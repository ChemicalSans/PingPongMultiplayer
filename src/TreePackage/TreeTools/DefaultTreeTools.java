package TreePackage.TreeTools;

import com.sun.javafx.geom.Vec2d;

public class DefaultTreeTools {
    public static void main(String[] args) {
        System.out.println(dir2angle(angle2dir(20)));

        System.out.println(lineIntersection(0,0,100,100,100,0,0,100));
    }

    public static Vec2d normalize(Vec2d in) {
        double hyper = Math.sqrt(Math.pow(in.x,2)+Math.pow(in.y,2));
        return new Vec2d(in.x/hyper,in.y/hyper);
    }

    public static double pythagoras(double x, double y) {
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }

    public static double dir2angle(Vec2d in) {
        return Math.toDegrees(Math.atan2(in.y,in.x));
    }

    public static Vec2d angle2dir(double r) {
        return new Vec2d(Math.cos(Math.toRadians(r)), Math.sin(Math.toRadians(r)));
    }

    public static Vec2d lineIntersection(double x1, double y1 , double x2, double y2, double x3 , double y3, double x4, double y4){
        double den = (x1 -x2) * (y3 -y4) - (y1 - y2) * (x3 - x4);

        if(den == 0) return null;

        double t = ((x1 - x3) * (y3 -y4) - (y1 - y3) * (x3 - x4))/den;
        double u = -((x1 - x2) * (y1 -y3) - (y1 - y2) * (x1 - x3))/den;

        if(t > 0 && t < 1 && u > 0) {
            //temp.x = x1 + t * (x2 - x1);
            //temp.y = y1 + t * (y2 - y1);

            double x = x3 + (u*x4 - u*x3);
            double y = y3 + (u*y4 - u*y3);

            return new Vec2d(x , y);
        }

        return null;
    }

}
