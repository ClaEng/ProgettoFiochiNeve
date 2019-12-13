
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
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
     * Trasla un poligono
     * 
     * @param pixelX quantità di pixel X.
     * @param pixelY quantità di pixel Y.
     * @return La shape traslata.
     */
    public Shape translatePolygon(int pixelX, int pixelY) {
        AffineTransform at = new AffineTransform();
        at.translate(pixelX, pixelY);
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
        int differenza;
        for (Point punto : this.getPoints()) {
            differenza = max - punto.x;
            p.addPoint(new Point(max + differenza, punto.y));
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
        Rectangle r = this.getBounds();
        return r.x + r.width;
    }
    
    public int getMaxY() {
        Rectangle r = this.getBounds();
        return r.y + r.height;
    }
    
    public int getHalfX() {
        Rectangle r = this.getBounds();
        return r.x + (r.width / 2);
    }

    /**
     * Ridimensiona un poligono.
     *
     * @return il poligono ridimensionato.
     */
    public Shape resize() {
        AffineTransform at = new AffineTransform();
        at.scale(0.5, 0.5);
        Shape p = at.createTransformedShape(this);
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
