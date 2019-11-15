
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Classe Frame di Fiocco di neve
 *
 * @author Claudio Engeler
 * @version 20/09/2019
 */
public class TrianglePanel extends JPanel {
    
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
    private Polygon2 parteTagliata;

    /**
     * Determina se stampare o meno la parte tagliata.
     */
    private boolean printParteTagliata = false;

    /**
     * Colore sfondo dei bottoni.
     */
    private Color bColorButton = new Color(0, 102, 255);

    /**
     * Colore testo dei bottoni.
     */
    private Color tColorButton = new Color(0, 204, 0);

    /**
     * Triangolo Base.
     */
    private Polygon2 triangle;

    /**
     * Determina se stampare o no la parte tagliata dopo la generazione.
     */
    private boolean printTagli = false;

    /**
     * Lista con i punti della parte tagliata dopo la generazione.
     */
    private ArrayList<Point> puntiConTagli = new ArrayList<>();
  
    public TrianglePanel() {
        this.setLayout(null);
        this.addMouseListener(new MouseAdapter() {
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
                            punti.remove(item);
                            repaint();
                            break;
                        }
                    }
                }
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                double distanza;
                int threshold = 5;
                for (int i = 0; i < punti.size(); i++) {
                    distanza = e.getPoint().distance(punti.get(i));
                    if (distanza <= R + threshold) {
                        punti.set(i, e.getPoint());
                        repaint();
                    }
                }
            }
        });
    }
    
    /**
     * Getter di ParteTagliata.
     * 
     * @return il Poligono parte tagliata.
     */
    public Polygon2 getParteTagliata () {
        return this.parteTagliata;
    }

    /**
     * Metodo Paint.
     *
     * @param g Componente grafico.
     */
    @Override
    public void paint(Graphics g) {

        g.setColor(this.bColorButton);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //Stampa Triangolo
        g.setColor(Color.WHITE);
        this.triangle = new Polygon2(getTrianglePoints());
        this.triangle.paintComponent(g);
        

        if (this.printTagli) {
            Polygon2 cuttedPolygon = new Polygon2(this.puntiConTagli);
            g.setColor(new Color(0, 255, 200, 100));
            cuttedPolygon.paintComponent(g);
            /*g.setColor(Color.MAGENTA);
            for (int i = 0; i < cuttedPolygon.getNPoints() - 1; i++) {
                g.drawLine(cuttedPolygon.getPoint(i).x,
                        cuttedPolygon.getPoint(i).y,
                        cuttedPolygon.getPoint(i + 1).x,
                        cuttedPolygon.getPoint(i + 1).y);
            }
            int dim = cuttedPolygon.getNPoints();
            g.drawLine(cuttedPolygon.getPoint(dim - 1).x,
                    cuttedPolygon.getPoint(dim - 1).y,
                    cuttedPolygon.getPoint(0).x,
                    cuttedPolygon.getPoint(0).y);
            */
        }

        if (this.printParteTagliata) {
            g.setColor(new Color(255, 255, 0, 100));
            g.fillPolygon(parteTagliata);
        }

        if (!this.printParteTagliata) {
            g.setColor(Color.red);
            punti.forEach((item) -> {
                g.fillOval(item.x - R, item.y - R, R * 2, R * 2);
            });

            for (int i = 0; i < this.punti.size() - 1; i++) {
                g.drawLine(
                        this.punti.get(i).x,
                        this.punti.get(i).y,
                        this.punti.get(i + 1).x,
                        this.punti.get(i + 1).y);
            }

            if (this.punti.size() >= 3) {
                int dim = this.punti.size();
                g.drawLine(
                        this.punti.get(dim - 1).x,
                        this.punti.get(dim - 1).y,
                        this.punti.get(0).x,
                        this.punti.get(0).y);
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
        int[] xs = {
            this.getWidth() / 7 * 2,
            this.getWidth() / 7 * 5,
            this.getWidth() / 7 * 5};
        int[] ys = {
            this.getHeight() / 7,
            this.getHeight() / 7 + (this.getHeight() / 20 * 2),
            this.getHeight() / 7 * 6};

        for (int i = 0; i < 3; i++) {
            p.add(new Point(xs[i], ys[i]));
        }

        return p;
    }

    /**
     * Salva i punti su un file txt (nella cartella progetto NetBeans).
     */
    public void save() {
        JFileChooser jfc = new JFileChooser("./");
        jfc.setDialogTitle("Save");
        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String extension = getExtension(jfc.getSelectedFile().toString());
            String path = jfc.getSelectedFile().toString();
            switch (extension) {
                case "points":
                    if (!this.punti.isEmpty()) {
                        try {
                            PrintWriter file = new PrintWriter(path, "UTF-8");
                            for (Point point : punti) {
                                file.println(point.x + "-" + point.y);
                            }
                            file.close();
                        } catch (IOException ioe) {

                        }
                    } else {
                        System.out.println("Nessun punto salvato");
                    }
                    break;
                case "png":
                    BufferedImage img = new BufferedImage(
                            this.getWidth(),
                            this.getHeight(),
                            BufferedImage.TYPE_INT_RGB);
                    this.paint(img.getGraphics());
                    try {
                        ImageIO.write(img, "png", new File(jfc.getSelectedFile().toString()));
                    } catch (IOException e) {
                    }
                    break;
                case "svg":

                    break;
            }
        }
    }

    /**
     * Carica e stampa i punti presenti nel file scelto.
     */
    public void loadPoints() {
        try {
            JFileChooser jfc = new JFileChooser("./");
            jfc.setDialogTitle("Load Points");
            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                String path = file.getPath();
                String extension = getExtension(path);
                if (extension.equals("points")) {
                    Path p = Paths.get(file.getAbsolutePath());
                    List<String> lines = Files.readAllLines(p);
                    String[][] valPunti = new String[lines.size() * 2][2];
                    for (int i = 0; i < lines.size(); i++) {
                        valPunti[i] = lines.get(i).split("-");
                    }

                    this.punti.removeAll(punti);

                    for (String[] points : valPunti) {
                        try {
                            this.punti.add(new Point(
                                    Integer.parseInt(points[0]),
                                    Integer.parseInt(points[1])
                            ));
                        } catch (NumberFormatException e) {

                        }
                    }

                    this.printParteTagliata = false;

                    repaint();
                } else {
                    throw new IOException();
                }
            }
        } catch (IOException ioe) {

        }
    }

    /**
     * Crea un poligono con i punti creati cliccando.
     */
    public void taglia() {
        if (this.punti.size() >= 3) {
            this.parteTagliata = new Polygon2();
            for (Point item : this.punti) {
                this.parteTagliata.addPoint(item.x, item.y);
            }
            this.printParteTagliata = true;
            repaint();
        }
    }

    /**
     * Resetta il frame al suo stato originale.
     */
    public void reset() {
        this.printParteTagliata = false;
        this.punti.removeAll(this.punti);
        this.triangle = new Polygon2(getTrianglePoints());
        this.printTagli = false;
        repaint();
    }

    /**
     * Genera il fiocco di neve.
     */
    public void generaFiocco() {
        Area51 triangleArea = new Area51(this.triangle);
        Area51 parteTagliataArea = new Area51(this.parteTagliata);
        triangleArea.subtract(parteTagliataArea);
        this.puntiConTagli = triangleArea.getPoints();
        this.printTagli = true;
        flipTriangleX();
        repaint();
    }
    
    /**
     * Calcola il raggio del cerchio di rotazione.
     * 
     * @return il raggio.
     */
    private int getRadius() {
        ArrayList<Point> points = getTrianglePoints();
        return (int)points.get(0).distance(points.get(2));
    }

    /**
     * Specchia il triangolo sull'asse X.
     */
    public void flipTriangleX() {
        ArrayList<Point> originalPoints = this.triangle.getPoints();
        ArrayList<Point> editedPoints;
        ArrayList<Polygon2> fiocco = new ArrayList<>();
        fiocco.add(this.triangle);
        fiocco.get(0).rotate(getRadius(), 90, fiocco.get(0).getPoint(2));
    }

    /**
     * Data la path di un file ne ritorna l'estensione.
     *
     * @param filename La path del file.
     * @return L'estensione del file.
     */
    public String getExtension(String filename) {
        if (filename.contains(".")) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        } else {
            return null;
        }
    }
}
