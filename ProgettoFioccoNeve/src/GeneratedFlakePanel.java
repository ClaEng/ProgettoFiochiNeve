
import java.awt.*;

/**
 *
 *
 @author Claudio Engeler
 * @version 10 nov 2019
 */
public class GeneratedFlakePanel extends javax.swing.JPanel{

    /**
     * Riferimento al TrianglePanel.
     */
    private TrianglePanel tp;

    public GeneratedFlakePanel(TrianglePanel tp) {
        this.tp = tp;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.tp.getParteTagliata().paint(g);
    }
}
