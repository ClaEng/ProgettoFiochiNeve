
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Buttoni personalizzati.
 * 
 * @author Claudio Engeler
 * @version 11 ott 2019
 */
public class CButton extends Button implements MouseListener, CButtonListener{
    
    private int x;
    
    private int y;
    
    private int width;
    
    private int height;
    
    /**
     * Colore sfondo dei bottoni.
     */
    private Color bColorButton = new Color(202, 84, 139);
    
    /**
     * Colore testo dei bottoni.
     */
    private Color tColorButton = new Color(95, 195, 163);
    
    private List<CButtonListener> listeners;
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(this.bColorButton);
        g.fillRect(this.x, this.y, this.width, this.height);
    }
    
    @Override
    public boolean contains(Point p) {
        return (p.x > this.x && p.x < this.y + this.width) && (p.y > this.y && p.y < this.y + this.height);
    }
    
    public void addListener(CButtonListener listener) {
        this.listeners.add(listener);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(this.contains(e.getPoint())) {
            for (CButtonListener listener : listeners) {
                listener.click();
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
    public void click() {
    }

    public CButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.listeners = new ArrayList<>();
    }
}
