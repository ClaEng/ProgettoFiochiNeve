
import javax.swing.*;
/**
 *
 * @author Claudio Engeler
 * @version 18 ott 2019
 */
public class test extends JFrame {

    public test() {
        super("DDD");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        test aaa = new test();
        int a = 0;
        
        aaa.setVisible(true);
    }
}
