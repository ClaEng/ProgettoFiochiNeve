
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 * Versione migliore di poligono.
 *
 * @author Claudio Engeler
 * @version 11 ott 2019
 */
public class Polygon2 extends Polygon {

    /**
     * Ritorna una lista con tutti i punti.
     *
     * @return Lista con tutti i punti.
     */
    public ArrayList<Point> getPoints() {

        ArrayList<Point> punti = new ArrayList<>();
        for (int i = 0; i < this.getNPoints(); i++) {
            punti.add(this.getPoint(i));
        }

        return punti;
    }

    /**
     * Ritorna un punto scelto dell'index.
     *
     * @param index indice del punto.
     * @return Il punto scelto.
     */
    public Point getPoint(int index) {
        try {

            int[] xs = this.xpoints;
            int[] ys = this.ypoints;

            return new Point(xs[index], ys[index]);
        } catch (ArrayIndexOutOfBoundsException i) {
            System.err.println(i.getMessage());
            return null;
        }
    }

    /**
     * Ritorna il numero di punti.
     *
     * @return Il numero di punti.
     */
    public int getNPoints() {
        return this.npoints;
    }

    /**
     * Rotea un poligono
     *
     * @param degrees i gradi di rotazione. (IN GRADI).
     * @param center il centro di rotazione.
     * @return Una Shape roteata.
     */
    public Shape rotate(double degrees, Point center) {
        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(degrees), center.x, center.y);
        Shape p = at.createTransformedShape(this);
        return p;
    }

    /**
     * Specchia un poligono sull'asse X.
     *
     * @return il pologono originale unito a quello scpecchiato.
     */
    public Polygon2 mirror() {
        Polygon2 p = this;
        int max = this.getXMax(p.getPoints());
        for (Point punto : this.getPoints()) {
            p.addPoint(new Point(max - punto.x, punto.y));
        }
        return p;
    }
    
    /**
     * Data una lista di punti ne trova quello con la X maggiore.
     * 
     * @param p La lista.
     * @return La X più grande.
     */
    private int getXMax(ArrayList<Point> p) {
        int max = p.get(0).x;
        for (Point point : p) {
            if (point.x > max) {
                max = point.x;
            }
        }
        return max;
    }

    /**
     * Ridimensiona un poligono.
     *
     * @return il poligono ridimensionato.
     */
    public Polygon2 resize() {
        Polygon2 p = new Polygon2();
        Point puntoAttuale;
        int differenzaX , differenzaY;
        for (int i = 0; i < this.getNPoints() - 1; i++) {
            puntoAttuale = this.getPoint(i);
            differenzaX = this.getPoint(i + 1).x - puntoAttuale.x;
            differenzaY = this.getPoint(i + 1).y - puntoAttuale.y;
            differenzaX /= 2;
            differenzaY /= 2;
            p.addPoint(puntoAttuale.x + differenzaX, puntoAttuale.y + differenzaY);
        }
        return p;
    }

    /**
     * Metodo Paint per Polygon2.
     *
     * @param g Componente grafico.
     */
    public void paintComponent(Graphics g) {
        g.fillPolygon(this);
    }

    /**
     * Aggiunge un punto al poligono.
     *
     * @param p Il punto da aggiungere.
     */
    public void addPoint(Point p) {
        this.addPoint(p.x, p.y);
    }

    public Polygon2(ArrayList<Point> points) {
        super();
        for (Point point : points) {
            this.addPoint(point.x, point.y);
        }
    }

    public Polygon2() {
        super();
    }
}
