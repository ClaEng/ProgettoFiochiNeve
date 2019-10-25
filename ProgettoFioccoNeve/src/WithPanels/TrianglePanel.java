package WithPanels;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Claudio Engeler
 * @version 25 ott 2019
 */
public class TrianglePanel extends JPanel{

    public TrianglePanel() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Polygon2 triangle = new Polygon2(getTrianglePoints());
        triangle.paint(g);
    }
    
    /**
     * Crea e ritorna una lista con i punti del triangolo.
     *
     * @return Una lista con i punti del triangolo.
     */
    public ArrayList<Point> getTrianglePoints() {
        ArrayList<Point> p = new ArrayList<>();
        int[] xs = {
            this.getWidth() / 5,
            this.getWidth() / 5 * 4,
            this.getWidth() / 5 * 4};
        int[] ys = {
            this.getHeight() / 5 + 40,
            this.getHeight() / 5 + 40,
            this.getHeight() / 5 * 4};

        for (int i = 0; i < 3; i++) {
            p.add(new Point(xs[i], ys[i]));
        }

        return p;
    }
}
