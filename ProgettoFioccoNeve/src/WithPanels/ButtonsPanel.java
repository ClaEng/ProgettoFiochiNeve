package WithPanels;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author Claudio Engeler
 * @version 25 ott 2019
 */
public class ButtonsPanel extends JPanel{

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
    public static final Color bColorButton = new Color(0, 102, 255);

    /**
     * Colore testo dei bottoni.
     */
    public static final Color tColorButton = new Color(0, 204, 0);

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
    
    public ButtonsPanel() {
        super();
        this.setSize(500, 500);
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
                    file = new PrintWriter(jfc.getSelectedFile(), "UTF-8");
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
                    }catch (NumberFormatException e) {
                        
                    }
                }

                this.printParteTagliata = false;

                repaint();
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 4; i++) {
            this.buttons.get(i).setBounds(
                    (int) (this.getWidth() / 10) + (int) (this.getWidth() * (0.2 * i)),
                    (int) (this.getHeight() * 0.8),
                    (int) (this.getWidth() * 0.15),
                    (int) (this.getWidth() * 0.05));
        }

        Font bFont = new Font("Verdana", 1, (this.getWidth() / 40));
        for (Button item : this.buttons) {
            item.setFont(bFont);
        }
    }
    
    
}
