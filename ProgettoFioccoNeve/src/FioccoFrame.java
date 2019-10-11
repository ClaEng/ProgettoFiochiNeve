
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Frame di Fiocco di neve
 *
 * @author Claudio Engeler
 * @version 20/09/2019
 */
public class FioccoFrame extends JFrame implements MouseListener, MouseMotionListener, CButtonListener {
    
    /**
     * Sono tutti i punti creati.
     */
    private ArrayList<Point> punti = new ArrayList<>();
    
    private ArrayList<CButton> butttons = new ArrayList<>();

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
    
    
    /**
     * Colore sfondo dei bottoni.
     */
    private Color bColorButton = new Color(202, 84, 139);
    
    /**
     * Colore testo dei bottoni.
     */
    private Color tColorButton = new Color(95, 195, 163);
    
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
    
    @Override
    public void click() {
        
    }

    public FioccoFrame() {
        super("Fiocchi di Neve Generator");
        this.setSize(700, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        for (int i = 0; i < 4; i++) {
            CButton button = new CButton
            ((int)(this.getWidth() / 10) + (int)(this.getWidth() * (0.2 * i + 1)),
            (int)(this.getHeight() - 100),
            (int)(this.getWidth() * 0.15), (int)(this.getWidth() * 0.05));
            this.butttons.add(button);
            this.addMouseListener(button);
            button.addListener(this);
            System.out.println(this.butttons);
        }
        
        /*Button taglia = new Button("Taglia");
        taglia.setBounds((int)(this.getWidth() / 10), 
                (int)(this.getHeight() - 100), (int)(this.getWidth() * 0.15), (int)(this.getWidth() * 0.05));
        taglia.setBackground(this.bColorButton);
        taglia.setForeground(this.tColorButton);
        taglia.setFocusable(false);
        taglia.addMouseListener(new MouseAdapter() {           
            @Override
            public void mouseEntered(MouseEvent e) {
                taglia.setBackground(tColorButton);
                taglia.setForeground(bColorButton);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taglia.setBackground(bColorButton);
                taglia.setForeground(tColorButton);
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
        
        Button reset = new Button("Reset");
        reset.setBounds((int)(this.getWidth() / 10) + (int)(this.getWidth() * 0.2), 
                (int)(this.getHeight() - 100), (int)(this.getWidth() * 0.15), (int)(this.getWidth() * 0.05));
        reset.setFocusable(false);
        reset.setBackground(this.bColorButton);
        reset.setForeground(this.tColorButton);
        this.add(reset);
        reset.addActionListener((e) -> {
            this.printParteTagliata = false;
            this.punti.removeAll(punti);
            repaint();
        });
        reset.addMouseListener(new MouseAdapter() {           
            @Override
            public void mouseEntered(MouseEvent e) {
                reset.setBackground(tColorButton);
                reset.setForeground(bColorButton);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                reset.setBackground(bColorButton);
                reset.setForeground(tColorButton);
            }
        });
        
        Button savePoints = new Button("Salva Punti");
        savePoints.setBounds(250, 400, 75, 25);
        savePoints.setFocusable(false);
        savePoints.setBackground(bColorButton);
        savePoints.setForeground(tColorButton);
        this.add(savePoints);
        savePoints.addActionListener((e) -> {
            savePoints();
        });
        savePoints.addMouseListener(new MouseAdapter() {           
            @Override
            public void mouseEntered(MouseEvent e) {
                savePoints.setBackground(tColorButton);
                savePoints.setForeground(bColorButton);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                savePoints.setBackground(bColorButton);
                savePoints.setForeground(tColorButton);
            }
        });
        
        
        Button loadPoints = new Button("Carica Punti");
        loadPoints.setBounds(350, 400, 75, 25);
        loadPoints.setFocusable(false);
        loadPoints.setBackground(bColorButton);
        loadPoints.setForeground(tColorButton);
        this.add(loadPoints);
        loadPoints.addActionListener((e) -> {
            loadPoints();
        });
        loadPoints.addMouseListener(new MouseAdapter() {           
            @Override
            public void mouseEntered(MouseEvent e) {
                loadPoints.setBackground(tColorButton);
                loadPoints.setForeground(bColorButton);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loadPoints.setBackground(bColorButton);
                loadPoints.setForeground(tColorButton);
            }
        });*/
        
    }

    /**
     * Metodo Paint.
     * 
     * @param g Componente grafico.
     */
    @Override
    public void paint(Graphics g) {
    super.paint(g);

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        for (CButton button : this.butttons) {
            button.paint(g);
        }
        
        //Stampa Triangolo
        g.setColor(Color.WHITE);
        Polygon2 triangle = new Polygon2(getTrianglePoints());
        triangle.paint(g);

        if (this.printParteTagliata) {
            g.setColor(Color.BLUE);
            g.fillPolygon(parteTagliata);
        }

        if (!this.printParteTagliata) {
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
    
    /**
     * Salva i punti su un file txt (nella cartella progetto NetBeans).
     */
    public void savePoints() {
        if(!this.punti.isEmpty()) {
            try {
                PrintWriter file = new PrintWriter("Punti.txt", "UTF-8");
                for (Point point : punti) {
                    file.println(point.x + "-" + point.y);
                }
                file.close();
            }
            catch(IOException ioe) {
                System.err.println("Error");
            }
        }else{
            System.out.println("Nessun punto salvato");
        }
    }
    
    /**
     * Carica e stampa i punti presenti nel file Punti.txt.
     */
    public void loadPoints() {
        try {
            Path p = Paths.get("Punti.txt");
            List<String> lines = Files.readAllLines(p);
            String[][] valPunti = new String[lines.size() * 2][2];
            for (int i = 0; i < lines.size(); i++) {
                valPunti[i] = lines.get(i).split("-");
            }
            
            this.punti.removeAll(punti);
            
            for (int i = 0; i < valPunti.length; i++) {
                try {
                    this.punti.add(new Point(Integer.parseInt(valPunti[i][0]), Integer.parseInt(valPunti[i][1])));
                } catch (NumberFormatException e) {
                }
            }
            
            this.printParteTagliata = false;
            
            repaint();
        }
        catch(IOException ioe) {
            System.err.println("File non trovato");
        }
    }
    
    
    public static void main(String[] args) {
        FioccoFrame jf = new FioccoFrame();
        
        jf.setVisible(true);
    }
}
