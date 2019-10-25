
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe Frame di Fiocco di neve
 *
 * @author Claudio Engeler
 * @version 20/09/2019
 */
public class FioccoFrame extends JFrame {

    /**
     * Sono tutti i punti creati.
     */
    private ArrayList<Point> punti = new ArrayList<>();

    /**
     * Lista con tutti i bottoni.
     */
    private ArrayList<Button> buttons;

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
    private ArrayList<Point> puntiConTagli = new ArrayList<Point>();

    public FioccoFrame() {
        super("Fiocchi di Neve Generator");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        this.buttons = new ArrayList<>();

        this.buttons.add(new Button("Taglia"));
        this.buttons.get(0).setBackground(this.bColorButton);
        this.buttons.get(0).setForeground(this.tColorButton);
        this.buttons.get(0).setFocusable(false);
        this.buttons.get(0).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttons.get(0).setBackground(tColorButton);
                buttons.get(0).setForeground(bColorButton);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttons.get(0).setBackground(bColorButton);
                buttons.get(0).setForeground(tColorButton);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (punti.size() >= 3) {
                    parteTagliata = new Polygon2();
                    for (Point item : punti) {
                        parteTagliata.addPoint(item.x, item.y);
                    }
                    printParteTagliata = true;
                    repaint();
                }
            }
        });
        this.add(this.buttons.get(0));

        this.buttons.add(new Button("Reset"));
        this.buttons.get(1).setFocusable(false);
        this.buttons.get(1).setBackground(this.bColorButton);
        this.buttons.get(1).setForeground(this.tColorButton);
        this.add(this.buttons.get(1));
        this.buttons.get(1).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttons.get(1).setBackground(tColorButton);
                buttons.get(1).setForeground(bColorButton);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttons.get(1).setBackground(bColorButton);
                buttons.get(1).setForeground(tColorButton);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                printParteTagliata = false;
                punti.removeAll(punti);
                triangle = new Polygon2(getTrianglePoints());
                printTagli = false;
                repaint();
            }
        });

        this.buttons.add(new Button("Salva Punti"));
        this.buttons.get(2).setFocusable(false);
        this.buttons.get(2).setBackground(bColorButton);
        this.buttons.get(2).setForeground(tColorButton);
        this.add(this.buttons.get(2));
        this.buttons.get(2).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttons.get(2).setBackground(tColorButton);
                buttons.get(2).setForeground(bColorButton);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttons.get(2).setBackground(bColorButton);
                buttons.get(2).setForeground(tColorButton);
                buttons.get(2).setLabel("Salva Punti");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                savePoints();
            }
        });

        this.buttons.add(new Button("Carica Punti"));
        this.buttons.get(3).setFocusable(false);
        this.buttons.get(3).setBackground(bColorButton);
        this.buttons.get(3).setForeground(tColorButton);
        this.add(this.buttons.get(3));
        this.buttons.get(3).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttons.get(3).setBackground(tColorButton);
                buttons.get(3).setForeground(bColorButton);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttons.get(3).setBackground(bColorButton);
                buttons.get(3).setForeground(tColorButton);
                buttons.get(3).setLabel("Carica Punti");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                loadPoints();
            }
        });

        this.buttons.add(new Button("Genera"));
        this.buttons.get(4).setFocusable(false);
        this.buttons.get(4).setBackground(bColorButton);
        this.buttons.get(4).setForeground(tColorButton);
        this.add(this.buttons.get(4));
        this.buttons.get(4).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttons.get(4).setBackground(tColorButton);
                buttons.get(4).setForeground(bColorButton);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttons.get(4).setBackground(bColorButton);
                buttons.get(4).setForeground(tColorButton);
                buttons.get(4).setLabel("Genera");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                generaFiocco();
            }
        });
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

        for (int i = 0; i < 4; i++) {
            this.buttons.get(i).setBounds(
                    (int) (this.getWidth() / 10) + (int) (this.getWidth() * (0.2 * i)),
                    (int) (this.getHeight() * 0.8),
                    (int) (this.getWidth() * 0.15),
                    (int) (this.getWidth() * 0.05));
        }

        this.buttons.get(4).setBounds(
                (this.getWidth() / 5) - 7,
                20,
                (this.getWidth() / 5) * 3,
                ((this.getHeight() / 5) / 2));

        Font bFont = new Font("Verdana", 1, (this.getWidth() / 40));
        for (Button item : this.buttons) {
            item.setFont(bFont);
        }

        //Stampa Triangolo
        g.setColor(Color.WHITE);
        this.triangle = new Polygon2(getTrianglePoints());
        this.triangle.paint(g);

        if (this.printTagli) {
            Polygon2 aa = new Polygon2(this.puntiConTagli);
            g.setColor(new Color(0, 255, 200, 100));
            aa.paint(g);
            g.setColor(Color.MAGENTA);
            for (int i = 0; i < aa.getNPoints() - 1; i++) {
                g.drawLine(aa.getPoint(i).x, aa.getPoint(i).y, aa.getPoint(i + 1).x, aa.getPoint(i + 1).y);
            }
            int dim = aa.getNPoints();
            g.drawLine(aa.getPoint(dim - 1).x, aa.getPoint(dim - 1).y, aa.getPoint(0).x, aa.getPoint(0).y);
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

    /**
     * Salva i punti su un file txt (nella cartella progetto NetBeans).
     */
    public void savePoints() {
        if (!this.punti.isEmpty()) {
            try {
                PrintWriter file;
                JFileChooser jfc = new JFileChooser("./");
                jfc.setDialogTitle("Save Points");
                int returnValue = jfc.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String path = jfc.getSelectedFile().toString();
                    path += ".points";
                    file = new PrintWriter(path, "UTF-8");
                    for (Point point : punti) {
                        file.println(point.x + "-" + point.y);
                    }
                    file.close();
                    this.buttons.get(2).setLabel("Done");
                }
            } catch (IOException ioe) {
                this.buttons.get(2).setBackground(Color.RED);
                this.buttons.get(2).setForeground(Color.BLACK);
                this.buttons.get(2).setLabel("ERRORE");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {

                }
            }
        } else {
            System.out.println("Nessun punto salvato");
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
                String extension = getExtensionByStringHandling(path);
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
                }else{
                    throw new IOException();
                }
            }
        } catch (IOException ioe) {
            this.buttons.get(3).setBackground(Color.RED);
            this.buttons.get(3).setForeground(Color.BLACK);
            this.buttons.get(3).setLabel("ERRORE");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {

            }
        }
    }

    public void generaFiocco() {
        Area51 triangleArea = new Area51(this.triangle);
        Area51 parteTagliataArea = new Area51(this.parteTagliata);
        triangleArea.subtract(parteTagliataArea);
        this.puntiConTagli = triangleArea.getPoints();
        int lowestY = parteTagliataArea.findLowestY();
        System.out.println(triangleArea.findLowestY());
        this.printTagli = true;
        repaint();

    }

    /**
     * Data la path di un file ne ritorna l'estensione.
     * 
     * @param filename La path del file.
     * @return L'estensione del file.
     */
    public String getExtensionByStringHandling(String filename) {
        if (filename.contains(".")) {
            return filename.substring(filename.lastIndexOf(".") + 1);
        }else{
            return "";
        }
    }

    public static void main(String[] args) {
        FioccoFrame jf = new FioccoFrame();

        jf.setVisible(true);
    }
}
