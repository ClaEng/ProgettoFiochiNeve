
import java.awt.Point;
import java.awt.geom.*;
import java.util.ArrayList;

/**
 * Versione migliore di Area.
 * 
 * @author Claudio Engeler
 * @version 18 ott 2019
 */
public class Area51 extends Area{

    /**
     * Ritorna una lista di punti dell'area.
     * 
     * @return Lista di punti dell'area.
     */
    public ArrayList<Point> getPoints() {
        ArrayList<Point> p = new ArrayList<>();
        PathIterator iterator = this.getPathIterator(null);
        float[] floats = new float[100];
        while (!iterator.isDone()) { 
            int type = iterator.currentSegment(floats);
            int x = (int) floats[0];
            int y = (int) floats[1];
            if (type != PathIterator.SEG_CLOSE) {
                p.add(new Point(x, y));
            }
            iterator.next();
        }
        return p;
    }
    
        
    
    /**
     * Data una lista di punti ne ritorna la Y più piccola.
     * Ovvero la più in alto nel frame.
     * 
     * @return La y.
     */
    public int findLowestY() {
        ArrayList<Point> p = this.getPoints();
        int lowestY = p.get(0).y;
        int i = 0;
        for (Point point : p) {
            if (lowestY > point.y && point.y != 140) {
                lowestY = point.y;
            }
            i++;
        }
        return lowestY;
    }
    
    /**
     * Verifica se tutti i punti sono all'interno dell'area.
     * 
     * @param p I punti da verificare.
     * @return True se si altrimenti false.
     */
    public boolean allIn(ArrayList<Point> p) {
        for (Point point : p) {
            if(!this.contains(point)) {
                return false;
            }
        }
        return true;
    }
    
    public ArrayList<Integer> getIndexs() {
        ArrayList<Point> p = this.getPoints();
        ArrayList<Integer> in = new ArrayList<>();
        int i = 0;
        for (Point point : p) {
            if (point.y == this.findLowestY()) {
                in.add(i);
            }
            i++;
        }
        return in;
    }
    
    public Area51(Polygon2 p) {
        super(p);
    }    
}
