package WithPanels;

import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author Claudio Engeler
 * @version 25 ott 2019
 */
public class GeneraPanel extends JPanel{

    public GeneraPanel() {
        Button genera = new Button("Genera");
        genera.setFocusable(false);
        genera.setBackground(ButtonsPanel.bColorButton);
        genera.setForeground(ButtonsPanel.tColorButton);
        this.add(genera);
        genera.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                genera.setBackground(ButtonsPanel.tColorButton);
                genera.setForeground(ButtonsPanel.bColorButton);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                genera.setBackground(ButtonsPanel.bColorButton);
                genera.setForeground(ButtonsPanel.tColorButton);
                genera.setLabel("Genera");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Genera");
            }
        });
    }
    
/**public void generaFiocco() {
        Area51 triangleArea = new Area51(this.triangle);
        Area51 parteTagliataArea = new Area51(this.parteTagliata);
        triangleArea.subtract(parteTagliataArea);
        this.puntiConTagli = triangleArea.getPoints();
        int lowestY = parteTagliataArea.findLowestY();
        System.out.println(triangleArea.findLowestY());
        this.printTagli = true;
        repaint();
        
    }*/
}
