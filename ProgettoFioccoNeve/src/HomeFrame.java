
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


/**
 * Home per Fiocchi di neve generator.
 *
 * @author Claudio Engeler
 * @version 04/10/2019
 */
public class HomeFrame extends JFrame {

    public HomeFrame() throws IOException {
        super("Fiocchi di Neve Generator");
        
        LayoutManager gl = new GridLayout(4, 1);
        this.setLayout(gl);
        
        JLabel avvia = new JLabel("Fiocchi di neve generator");
        avvia.setForeground(Color.BLACK);
        avvia.setFont(new Font("Verdana", 1, 30));
        this.add(avvia);
        
        JLabel author = new JLabel("by Claudio Engeler");
        author.setFont(new Font("Verdana", 1, 15));
        this.add(author);
        
        this.setBackground(Color.BLUE);
        
        this.setSize(500, 500);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) throws IOException {
        HomeFrame hf = new HomeFrame();
        
        JPanel jp = new JPanel();
        BufferedImage img = ImageIO.read(new File("C:\\Users\\Claudio\\Desktop\\ProgettoFioccoNeve\\src\\img\\FioccoDiNeve.png"));
        ImageIcon icon = new ImageIcon(img);
        JLabel jl = new JLabel(icon);
        jp.add(jl);
        
        hf.add(jp);
        
        JButton avvia = new JButton("Avvia");
        avvia.setBounds(100, 100, 50, 50);
        avvia.setBackground(Color.WHITE);
        avvia.setForeground(Color.CYAN);
        avvia.setFocusPainted(false);
        avvia.setFont(new Font("Arial Black", 1, 75));
        avvia.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FioccoFrame.main(args);
                hf.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                avvia.setBackground(Color.MAGENTA);
                avvia.setForeground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                avvia.setBackground(Color.WHITE);
                avvia.setForeground(Color.CYAN);
            }
        });
        hf.add(avvia);
        
        hf.setVisible(true);
    }
}
