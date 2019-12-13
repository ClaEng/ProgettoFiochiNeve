
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.geom.PathIterator;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

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

    /**
     * Contiene i poligoni che formano il fiocco.
     */
    private ArrayList<Shape> shapes = new ArrayList<>();

    /**
     * Determina se stampare o no il triangolo.
     */
    private boolean printTriangle = true;

    /**
     * Determina se stampare o no i punti.
     */
    private boolean printPoints = true;

    /**
     * Determina se stampare o no il fiocco.
     */
    private boolean printFlake = false;

    /**
     * Copia di parteTagliata.
     */
    private Polygon2 parteTagliataCopy;

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
    public Polygon2 getParteTagliata() {
        return this.parteTagliata;
    }

    /**
     * Metodo Paint.
     *
     * @param g Componente grafico.
     */
    @Override
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();
        g.setColor(this.bColorButton);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //Stampa Triangolo
        g.setColor(Color.WHITE);
        this.triangle = new Polygon2(getTrianglePoints());
        if (this.printTriangle) {
            this.triangle.paintComponent(g);
        }

        if (this.printParteTagliata) {
            g.setColor(Color.WHITE);
            g.fillPolygon(this.parteTagliata);
        }

        this.paintFlake(g2d);

        if (this.printPoints) {
            g.setColor(Color.red);
            punti.forEach((item) -> {
                g.fillOval(item.x - R, item.y - R, R * 2, R * 2);
            });

            g.setColor(Color.BLACK);
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
     * Stampa il fiocco di neve generato.
     *
     * @param g2d Componente grafico.
     */
    public void paintFlake(Graphics2D g2d) {
        if (this.printFlake) {
            for (int i = 0; i < 6; i++) {
                g2d.setColor(Color.WHITE);
                g2d.fill(this.shapes.get(i));
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
        int width = this.getWidth();
        int height = this.getHeight();
        int[] xs = {
            this.getWidth() / 7 * 2,
            this.getWidth() / 7 * 5,
            this.getWidth() / 7 * 5
        };
        int[] ys = {
            this.getHeight() / 7,
            this.getHeight() / 7,
            this.getHeight() / 7 * 6
        };

        for (int i = 0; i < 3; i++) {
            p.add(new Point(xs[i], ys[i]));
        }

        return p;
    }

    /**
     * Gestisce il salvataggio dei punti.
     */
    public void savePoints() {
        JFileChooser jfc = new JFileChooser("./");
        jfc.setDialogTitle("Save Points");
        FileFilter fPoints = new FileNameExtensionFilter("Punti", "points");
        jfc.addChoosableFileFilter(fPoints);
        jfc.setAcceptAllFileFilterUsed(false);
        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (!this.punti.isEmpty()) {
                try {
                    String path = jfc.getSelectedFile().toString() + ".points";
                    PrintWriter file = new PrintWriter(path, "UTF-8");
                    for (Point point : punti) {
                        file.println(point.x + "-" + point.y);
                    }
                    file.close();
                } catch (IOException ioe) {

                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Nessun punto salvato",
                        "ERRORE",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Gestisce il salvataggio delle immagini.
     */
    public void savePng() {
        JFileChooser jfc = new JFileChooser("./");
        jfc.setDialogTitle("Save PNG");
        FileFilter fPng = new FileNameExtensionFilter("PNG", "png");
        jfc.addChoosableFileFilter(fPng);
        jfc.setAcceptAllFileFilterUsed(false);
        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String path = jfc.getSelectedFile().toString() + ".png";
            BufferedImage img = new BufferedImage(
                    this.getWidth(),
                    this.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            this.paint(img.getGraphics());
            try {
                ImageIO.write(
                        img,
                        "png",
                        new File(path));
            } catch (IOException e) {
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
            FileFilter fPoints = new FileNameExtensionFilter("Punti", "points");
            jfc.addChoosableFileFilter(fPoints);
            jfc.setAcceptAllFileFilterUsed(false);
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                String path = file.getPath();
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
            }
        } catch (IOException ioe) {

        }
    }

    /**
     * Crea un poligono con i punti creati cliccando.
     */
    public void taglia() {
        if (this.areTherePoints()) {
            this.parteTagliata = new Polygon2();
            this.parteTagliataCopy = new Polygon2();
            for (Point item : this.punti) {
                this.parteTagliata.addPoint(item.x, item.y);
                this.parteTagliataCopy.addPoint(item.x, item.y);
            }
            Area51 triangleArea = new Area51(this.triangle);
            Area51 parteTagliataArea = new Area51(this.parteTagliata);
            triangleArea.subtract(parteTagliataArea);
            ArrayList<Point> a = triangleArea.getPoints();
            this.parteTagliata = new Polygon2(a);
            this.printParteTagliata = true;
            this.printPoints = false;
            this.printTriangle = false;
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
        this.printTriangle = true;
        this.printPoints = true;
        this.puntiConTagli.removeAll(this.puntiConTagli);
        if (this.parteTagliata != null) {
            this.parteTagliata.reset();
        }
        if (this.parteTagliataCopy != null) {
            this.parteTagliataCopy.reset();
        }
        this.shapes.removeAll(this.shapes);
        this.printFlake = false;
        repaint();
    }

    /**
     * Genera il fiocco di neve.
     */
    public void generaFiocco() {
        Area51 triangleArea = new Area51(this.triangle);
        Area51 parteTagliataArea = new Area51(this.parteTagliataCopy);
        triangleArea.subtract(parteTagliataArea);
        this.puntiConTagli = triangleArea.getPoints();
        Polygon2 p = new Polygon2(this.puntiConTagli);
        p = p.mirror();
        Polygon2 pPronto = shapeToPolygon2(p.resize());
        Polygon2 q = shapeToPolygon2(pPronto.translatePolygon(75, 25));
        Point center = getRotationPoint(q);
        center.x = q.getHalfX();
        center.y = q.getMaxY();
        for (int i = 0; i < 6; i++) {
            this.shapes.add(q.rotate(60 * i, center));
        }
        this.printTagli = true;
        this.printTriangle = false;
        this.printParteTagliata = false;
        this.printPoints = false;
        this.printFlake = true;
        repaint();
    }

    /**
     * Verifica che ci siamo almeno tre punti nella lista.
     *
     * @return true se ci sono alrimenti false.
     */
    public boolean areTherePoints() {
        return this.punti.size() >= 3;
    }

    /**
     * Calcola il punto di rotazione del fiocco.
     *
     * @return il punto di rotazione del fiocco.
     */
    public Point getRotationPoint(Polygon2 q) {
        Polygon2 triangleCopy = shapeToPolygon2(q.resize());
        return triangleCopy.getPoint(2);
    }

    /**
     * Getter per printFlake.
     *
     * @return treu se stampare il fiocco altrimenti false.
     */
    public boolean getPrintFlake() {
        return this.printFlake;
    }

    /**
     * Ritorna una lista di punti dell'area.
     *
     * @param shape La shape.
     * @return Lista di punti dell'area.
     */
    public Polygon2 shapeToPolygon2(Shape shape) {
        ArrayList<Point> p = new ArrayList<>();
        ArrayList<Point> points0 = new ArrayList<>();
        PathIterator iterator = shape.getPathIterator(null);
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
        return new Polygon2(p);
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
