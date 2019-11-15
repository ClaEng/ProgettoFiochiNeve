
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 * Versione migliore di poligono.
 * 
 * @author Claudio Engeler
 * @version 11 ott 2019
 */
public class Polygon2 extends Polygon{
    
    /**
     * Rapporto tra radianti e gradi.
     */
    public static final double RADIANT_TO_DEGREES = 180 / Math.PI;
    
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
        
        try{
            
            int[] xs = this.xpoints; 
            int[] ys = this.ypoints;
        
            return new Point(xs[index], ys[index]);
        }
        catch(ArrayIndexOutOfBoundsException i) {
                        
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
     * @param radius il raggio dell'asse di rotazione.
     * @param degrees i gradi di rotazione. (IN GRADI).
     */
    public void rotate(int radius, double degrees) {
        int asseX = radius * 2;
        int asseY = radius * 2;
        double seno = Math.sin(degrees / RADIANT_TO_DEGREES);
    }
    
    /**
     * Metodo Paint per Polygon2.
     * 
     * @param g Componente grafico.
     */
    public void paint(Graphics g) {
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
