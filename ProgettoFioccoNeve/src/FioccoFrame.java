
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Classe Frame di Fiocco di neve
 *
 * @author Claudio Engeler
 * @version 20/09/2019
 */
public class FioccoFrame extends JFrame implements MouseListener, MouseMotionListener {

    /**
     * Sono tutti i punti creati.
     */
    private ArrayList<Point> punti = new ArrayList<>();

    /**
     * Rappresenta il raggio dei punti.
     */
    public static final int R = 5;

    /**
     * È la figura che si crea usando le linee di taglio.
     */
    private Polygon parteTagliata;

    /**
     * Determina se stampare o meno la parte tagliata.
     */
    private boolean printParteTagliata = false;
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            punti.add(e.getPoint());
            repaint();
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            double distanza;
            for (Point item : punti) {
                distanza = e.getPoint().distance(item);
                if (distanza <= R) {
                    this.punti.remove(item);
                    repaint();
                    break;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        double distanza;
        int threshold = 5;
        for (int i = 0; i < this.punti.size(); i++) {
            distanza = e.getPoint().distance(this.punti.get(i));
            if (distanza <= R + threshold) {
                this.punti.set(i, e.getPoint());
                repaint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public FioccoFrame() {
        super("Fiocchi di Neve Generator");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        Button taglia = new Button("Taglia");
        taglia.setBounds(50, 400, 75, 25);
        taglia.setBackground(Color.ORANGE);
        taglia.setForeground(Color.GREEN);
        taglia.setFocusable(false);
        taglia.addMouseListener(new MouseAdapter() {           
            @Override
            public void mouseEntered(MouseEvent e) {
                taglia.setBackground(Color.cyan);
                taglia.setForeground(Color.YELLOW);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taglia.setBackground(Color.ORANGE);
                taglia.setForeground(Color.GREEN);
            }
        });
        this.add(taglia);
        taglia.addActionListener((e) -> {
           this.parteTagliata = new Polygon();
           for (Point item : punti) {
                parteTagliata.addPoint(item.x, item.y);
            }
            this.printParteTagliata = true;
            repaint();
        });
        
        Button exit = new Button("Esci");
        exit.setBounds(50, 50, 50, 50);
        exit.setFocusable(false);
        this.add(exit);
        exit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        //Stampa Triangolo
        g.setColor(Color.WHITE);
        Polygon2 triangle = new Polygon2(getTrianglePoints());
        triangle.paint(g);

        if (this.printParteTagliata) {
            g.setColor(Color.BLUE);
            g.fillPolygon(parteTagliata);
        }

        g.setColor(Color.red);
        punti.forEach((item) -> {
            g.fillOval(item.x - R, item.y - R, R * 2, R * 2);
        });

        for (int i = 0; i < this.punti.size() - 1; i++) {
            g.drawLine(this.punti.get(i).x, this.punti.get(i).y, this.punti.get(i + 1).x, this.punti.get(i + 1).y);
        }

        if (this.punti.size() >= 3) {
            int dim = this.punti.size();
            g.drawLine(this.punti.get(dim - 1).x, this.punti.get(dim - 1).y, this.punti.get(0).x, this.punti.get(0).y);
        }
    }
    
    /**
     * Crea e ritorna una lista con i punti del triangolo.
     * 
     * @return Una lista con i punti del triangolo.
     */
    public ArrayList<Point> getTrianglePoints() {
        ArrayList<Point> p = new ArrayList<>();
        int[] xs = {this.getWidth() / 5, (this.getWidth() / 5) * 4, (this.getWidth() / 5) * 4};
        int[] ys = {this.getHeight() / 5 + 40, this.getHeight() / 5 + 40, (this.getHeight() / 5) * 4};
        
        for (int i = 0; i < 3; i++) {
            p.add(new Point(xs[i], ys[i]));
        }
        
        return p;
    }

    public static void main(String[] args) {
        FioccoFrame jf = new FioccoFrame();
        jf.setBackground(Color.CYAN);
        
        jf.setVisible(true);
    }
}
