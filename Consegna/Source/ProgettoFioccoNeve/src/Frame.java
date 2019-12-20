
import java.awt.*;

/**
 * SnowFlakeFrame.
 *
 * @author Claudio Engeler
 */
public class Frame extends javax.swing.JFrame {

    /**
     * Colore sfondo dei bottoni.
     */
    private Color bColorButton = new Color(0, 102, 255);

    /**
     * Colore testo dei bottoni.
     */
    private Color tColorButton = new Color(0, 204, 0);

    /**
     * Creates new form Frame
     */
    public Frame() {
        initComponents();
        this.setSize(500, 500);
        genera.setFocusable(false);
        salvaSVGButton.setFocusable(false);
        taglia.setFocusable(false);
        reset.setFocusable(false);
        salvaPng.setFocusable(false);
        genera.setEnabled(false);
        caricaPunti.setFocusable(false);
        salvaPoints.setFocusable(false);
        Dimension maxDimension = new Dimension(1080, 720);
        this.setMaximumSize(maxDimension);
        Dimension minDimension = new Dimension(480, 270);
        this.setMinimumSize(minDimension);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GeneraPanel = new javax.swing.JPanel();
        genera = new java.awt.Button();
        buttonsPanel = new javax.swing.JPanel();
        utilityPanel = new javax.swing.JPanel();
        reset = new java.awt.Button();
        taglia = new java.awt.Button();
        caricaPunti = new java.awt.Button();
        savePanel = new javax.swing.JPanel();
        salvaPng = new java.awt.Button();
        salvaSVGButton = new java.awt.Button();
        salvaPoints = new java.awt.Button();
        trianglePanel = new TrianglePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Snow Flake Generator");

        GeneraPanel.setLayout(new java.awt.GridLayout(1, 0));

        genera.setActionCommand("Genera");
        genera.setBackground(new java.awt.Color(0, 102, 255));
        genera.setForeground(new java.awt.Color(0, 204, 0));
        genera.setLabel("Genera");
        genera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                generaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                generaMouseExited(evt);
            }
        });
        genera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generaActionPerformed(evt);
            }
        });
        GeneraPanel.add(genera);
        genera.getAccessibleContext().setAccessibleParent(this);

        getContentPane().add(GeneraPanel, java.awt.BorderLayout.NORTH);

        buttonsPanel.setLayout(new java.awt.GridLayout(2, 0));

        utilityPanel.setLayout(new java.awt.GridLayout(1, 3));

        reset.setBackground(new java.awt.Color(0, 102, 255));
        reset.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        reset.setForeground(new java.awt.Color(0, 204, 0));
        reset.setLabel("Reset");
        reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                resetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                resetMouseExited(evt);
            }
        });
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        utilityPanel.add(reset);

        taglia.setBackground(new java.awt.Color(0, 102, 255));
        taglia.setForeground(new java.awt.Color(0, 204, 0));
        taglia.setLabel("Taglia");
        taglia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tagliaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tagliaMouseExited(evt);
            }
        });
        taglia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tagliaActionPerformed(evt);
            }
        });
        utilityPanel.add(taglia);

        caricaPunti.setBackground(new java.awt.Color(0, 102, 255));
        caricaPunti.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        caricaPunti.setForeground(new java.awt.Color(0, 204, 0));
        caricaPunti.setLabel("Carica Punti");
        caricaPunti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                caricaPuntiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                caricaPuntiMouseExited(evt);
            }
        });
        caricaPunti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caricaPuntiActionPerformed(evt);
            }
        });
        utilityPanel.add(caricaPunti);

        buttonsPanel.add(utilityPanel);

        savePanel.setLayout(new java.awt.GridLayout(1, 3));

        salvaPng.setBackground(new java.awt.Color(0, 102, 255));
        salvaPng.setForeground(new java.awt.Color(0, 204, 0));
        salvaPng.setLabel("Salva PNG");
        salvaPng.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                salvaPngMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                salvaPngMouseExited(evt);
            }
        });
        salvaPng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvaPngActionPerformed(evt);
            }
        });
        savePanel.add(salvaPng);

        salvaSVGButton.setBackground(new java.awt.Color(0, 102, 255));
        salvaSVGButton.setForeground(new java.awt.Color(0, 204, 0));
        salvaSVGButton.setLabel("Salva SVG");
        salvaSVGButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                salvaSVGButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                salvaSVGButtonMouseExited(evt);
            }
        });
        salvaSVGButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvaSVGButtonActionPerformed(evt);
            }
        });
        savePanel.add(salvaSVGButton);

        salvaPoints.setBackground(new java.awt.Color(0, 102, 255));
        salvaPoints.setForeground(new java.awt.Color(0, 204, 0));
        salvaPoints.setLabel("Salva Punti");
        salvaPoints.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                salvaPointsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                salvaPointsMouseExited(evt);
            }
        });
        salvaPoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvaPointsActionPerformed(evt);
            }
        });
        savePanel.add(salvaPoints);

        buttonsPanel.add(savePanel);

        getContentPane().add(buttonsPanel, java.awt.BorderLayout.PAGE_END);

        trianglePanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(trianglePanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generaActionPerformed
        trianglePanel.generaFiocco();
        genera.setEnabled(false);
    }//GEN-LAST:event_generaActionPerformed

    private void generaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generaMouseEntered
        genera.setBackground(tColorButton);
        genera.setForeground(bColorButton);
    }//GEN-LAST:event_generaMouseEntered

    private void generaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generaMouseExited
        genera.setBackground(bColorButton);
        genera.setForeground(tColorButton);
    }//GEN-LAST:event_generaMouseExited

    private void caricaPuntiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caricaPuntiActionPerformed
        trianglePanel.loadPoints();
    }//GEN-LAST:event_caricaPuntiActionPerformed

    private void caricaPuntiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_caricaPuntiMouseExited
        caricaPunti.setBackground(bColorButton);
        caricaPunti.setForeground(tColorButton);
    }//GEN-LAST:event_caricaPuntiMouseExited

    private void caricaPuntiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_caricaPuntiMouseEntered
        caricaPunti.setBackground(tColorButton);
        caricaPunti.setForeground(bColorButton);
    }//GEN-LAST:event_caricaPuntiMouseEntered

    private void salvaPngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvaPngActionPerformed
        trianglePanel.savePng();
    }//GEN-LAST:event_salvaPngActionPerformed

    private void salvaPngMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salvaPngMouseExited
        salvaPng.setBackground(bColorButton);
        salvaPng.setForeground(tColorButton);
    }//GEN-LAST:event_salvaPngMouseExited

    private void salvaPngMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salvaPngMouseEntered
        salvaPng.setBackground(tColorButton);
        salvaPng.setForeground(bColorButton);
    }//GEN-LAST:event_salvaPngMouseEntered

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        trianglePanel.reset();
        genera.setEnabled(false);
        taglia.setEnabled(true);
    }//GEN-LAST:event_resetActionPerformed

    private void resetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetMouseExited
        reset.setBackground(bColorButton);
        reset.setForeground(tColorButton);
    }//GEN-LAST:event_resetMouseExited

    private void resetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetMouseEntered
        reset.setBackground(tColorButton);
        reset.setForeground(bColorButton);
    }//GEN-LAST:event_resetMouseEntered

    private void tagliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tagliaActionPerformed
        trianglePanel.taglia();
        genera.setEnabled(true);
        taglia.setEnabled(!trianglePanel.areTherePoints());
    }//GEN-LAST:event_tagliaActionPerformed

    private void tagliaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tagliaMouseExited
        taglia.setBackground(bColorButton);
        taglia.setForeground(tColorButton);
    }//GEN-LAST:event_tagliaMouseExited

    private void tagliaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tagliaMouseEntered
        taglia.setBackground(tColorButton);
        taglia.setForeground(bColorButton);
    }//GEN-LAST:event_tagliaMouseEntered

    private void salvaPointsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salvaPointsMouseEntered
        salvaPoints.setBackground(tColorButton);
        salvaPoints.setForeground(bColorButton);
    }//GEN-LAST:event_salvaPointsMouseEntered

    private void salvaPointsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salvaPointsMouseExited
        salvaPoints.setBackground(bColorButton);
        salvaPoints.setForeground(tColorButton);
    }//GEN-LAST:event_salvaPointsMouseExited

    private void salvaPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvaPointsActionPerformed
        trianglePanel.savePoints();
    }//GEN-LAST:event_salvaPointsActionPerformed

    private void salvaSVGButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salvaSVGButtonMouseEntered
        salvaSVGButton.setBackground(tColorButton);
        salvaSVGButton.setForeground(bColorButton);
    }//GEN-LAST:event_salvaSVGButtonMouseEntered

    private void salvaSVGButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salvaSVGButtonMouseExited
        salvaSVGButton.setBackground(bColorButton);
        salvaSVGButton.setForeground(tColorButton);
    }//GEN-LAST:event_salvaSVGButtonMouseExited

    private void salvaSVGButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvaSVGButtonActionPerformed
        trianglePanel.saveSvg();
    }//GEN-LAST:event_salvaSVGButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            Frame f = new Frame();
            f.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GeneraPanel;
    private javax.swing.JPanel buttonsPanel;
    private java.awt.Button caricaPunti;
    public java.awt.Button genera;
    private java.awt.Button reset;
    public java.awt.Button salvaPng;
    public java.awt.Button salvaPoints;
    private java.awt.Button salvaSVGButton;
    private javax.swing.JPanel savePanel;
    public java.awt.Button taglia;
    private TrianglePanel trianglePanel;
    private javax.swing.JPanel utilityPanel;
    // End of variables declaration//GEN-END:variables
}
