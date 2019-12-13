
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

/**
 * Versione migliore di Area.
 *
 * @author Claudio Engeler
 * @version 18 ott 2019
 */
public class Area51 extends Area {

    /**
     * Ritorna una lista di punti dell'area.
     *
     * @return Lista di punti dell'area.
     */
    public ArrayList<Point> getPoints() {
        ArrayList<Point> p = new ArrayList<>();
        ArrayList<Point> points0 = new ArrayList<>();
        PathIterator iterator = this.getPathIterator(null);
        float[] floats = new float[6];
        Point chiusura = null;
        while (!iterator.isDone()) {
            int type = iterator.currentSegment(floats);
            int x = (int) floats[0];
            int y = (int) floats[1];
            p.add(new Point(x, y));
            if (type == 0) {
                chiusura = new Point(x, y);
                points0.add(chiusura);
            }
            if (type == 4) {
                p.add(chiusura);
            }
            iterator.next();
        }
        for (Point punto : points0) {
            p.add(punto);
        }
        return p;
    }

    /**
     * Metodo paint
     *
     * @param g Componente grafico.
     */
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.fill(this);
    }

    public Area51(Polygon2 p) {
        super(p);
    }
}
