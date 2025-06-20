/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import static Interfaces.Board.tipoBusqSelect;
import static Interfaces.Load.tableroJuego;
import javax.swing.JButton;

/**
 *
 * @author juanp
 */
public class SearchType extends javax.swing.JFrame {

    
    
    public SearchType( ) {
        initComponents();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        tipoBusquedaG.add(rbDFS);
        tipoBusquedaG.add(rbBFS);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoBusquedaG = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rbDFS = new javax.swing.JRadioButton();
        rbBFS = new javax.swing.JRadioButton();
        selectTipoBusq = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Seleccione el tipo de busqueda que desea realizar");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel2.setText("Tipo de Busqueda");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        rbDFS.setText("DFS");
        rbDFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDFSActionPerformed(evt);
            }
        });
        jPanel1.add(rbDFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        rbBFS.setText("BFS");
        rbBFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbBFSActionPerformed(evt);
            }
        });
        jPanel1.add(rbBFS, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        selectTipoBusq.setText("Seleccionar");
        selectTipoBusq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectTipoBusqActionPerformed(evt);
            }
        });
        jPanel1.add(selectTipoBusq, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 120, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 270));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbBFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbBFSActionPerformed
        tipoBusqSelect = "BFS";
    }//GEN-LAST:event_rbBFSActionPerformed

    private void rbDFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDFSActionPerformed
        tipoBusqSelect = "DFS";
    }//GEN-LAST:event_rbDFSActionPerformed

    private void selectTipoBusqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectTipoBusqActionPerformed
        Board board = new Board();
        this.dispose();
    }//GEN-LAST:event_selectTipoBusqActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchType().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbBFS;
    private javax.swing.JRadioButton rbDFS;
    private javax.swing.JButton selectTipoBusq;
    private javax.swing.ButtonGroup tipoBusquedaG;
    // End of variables declaration//GEN-END:variables
}
