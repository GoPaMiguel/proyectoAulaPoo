/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.publico;

import controller.ProfileController;
import controller.ResidueController;
import java.awt.event.KeyEvent;
import model.CORE.Residue;
import model.CORE.User;
import model.DTO.userDTO.UserPointsDTO;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Breiner
 */
public class Recycle extends javax.swing.JFrame {

    /**
     * Creates new form RedeemPoints
     */
    public Recycle() {
        initComponents();
        ResidueController.ShowResidueController(tbResidues);
        YourPoints();
        txtYourPoints.setEnabled(false);
        txtRecyclePoints.setEnabled(false);
        txtTotalPoints.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbResidues = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtYourPoints = new javax.swing.JTextField();
        txtWeight = new javax.swing.JTextField();
        txtTotalPoints = new javax.swing.JTextField();
        txtRecyclePoints = new javax.swing.JTextField();
        btnCalculate = new javax.swing.JButton();
        btnRecycle = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(10, 49, 48));
        jPanel2.setPreferredSize(new java.awt.Dimension(467, 117));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 80, 110));

        btnBack.setBackground(new java.awt.Color(85, 140, 54));
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("BACK");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel2.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(733, 13, 67, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(128, 204, 40));
        jLabel7.setText("ECO");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 13, 100, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(128, 204, 40));
        jLabel6.setText("FRIENDLY");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 43, 150, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("CODE");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 73, 50, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Recycle");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 140, 80));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 110));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 22)); // NOI18N
        jLabel1.setText("Look for the material you want to recycle");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 153, -1, -1));
        jPanel1.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 185, 260, -1));

        btnSearch.setPreferredSize(new java.awt.Dimension(33, 5));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, -1, 30));

        tbResidues.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbResidues.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbResiduesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbResidues);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 217, 300, 242));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 22)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Your Points:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 94, -1));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 22)); // NOI18N
        jLabel8.setText("Recycle Points:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 22)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Weight:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 87, -1));

        jLabel10.setFont(new java.awt.Font("Tw Cen MT Condensed", 0, 22)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Total Points:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 87, -1));

        txtYourPoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYourPointsActionPerformed(evt);
            }
        });
        jPanel1.add(txtYourPoints, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, 88, -1));

        txtWeight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtWeightKeyTyped(evt);
            }
        });
        jPanel1.add(txtWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 88, -1));
        jPanel1.add(txtTotalPoints, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 88, -1));
        jPanel1.add(txtRecyclePoints, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, 88, -1));

        btnCalculate.setText("CALCULATE");
        btnCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateActionPerformed(evt);
            }
        });
        jPanel1.add(btnCalculate, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 330, 144, 45));

        btnRecycle.setText("RECYCLE");
        btnRecycle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecycleActionPerformed(evt);
            }
        });
        jPanel1.add(btnRecycle, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 390, 144, 45));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 250, 241));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtYourPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYourPointsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtYourPointsActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        this.dispose();
        UsersView user = new UsersView();
        user.setLocationRelativeTo(null);
        user.setVisible(true);

    }//GEN-LAST:event_btnBackActionPerformed

    private void btnCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateActionPerformed
        // TODO add your handling code here:
        if (!validador()) return;
        int total = Calculate();
        txtTotalPoints.setText(total + "");
    }//GEN-LAST:event_btnCalculateActionPerformed

    private void YourPoints() {
        User u = ProfileController.GetProfileController();
        txtYourPoints.setText(String.valueOf(u.getPoints()));
    }

    private boolean validador() {
        if (txtYourPoints.getText().equals("") || txtRecyclePoints.getText().equals("") || txtWeight.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor valido");
            return false;
        }
        return true;
    }

    private int Calculate() {

            int yourPoints = Integer.parseInt(txtYourPoints.getText());
            int recyclePoints = Integer.parseInt(txtRecyclePoints.getText());
            double weight = Double.parseDouble(txtWeight.getText());
            return (int) Math.round(yourPoints + (recyclePoints * weight));

    }

    private void tbResiduesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbResiduesMouseClicked
        // TODO add your handling code here:
        Residue r = ResidueController.SelectResidueController(tbResidues);
        txtRecyclePoints.setText(String.valueOf(r.getPoints()));
    }//GEN-LAST:event_tbResiduesMouseClicked

    private void btnRecycleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecycleActionPerformed
        // TODO add your handling code here:
        if (!validador()) return;
        String id = ProfileController.getCedula();
        int total = Calculate();
        ProfileController.InsertPointsController(new UserPointsDTO(id, total));
        JOptionPane.showMessageDialog(null, "Recycle sucessfully");
        this.dispose();
        UsersView view = new UsersView();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }//GEN-LAST:event_btnRecycleActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        addFilter(tbResidues, txtSearch, 2);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtWeightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtWeightKeyTyped
        char c = evt.getKeyChar();

        // Permitir números, punto decimal y tecla de borrar
        if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE) {
            evt.consume(); // Ignorar el evento si no cumple las condiciones
        }

        // Evitar múltiples puntos decimales
        if (c == '.' && txtWeight.getText().contains(".")) {
            evt.consume(); // Ignorar si ya hay un punto
        }
    }//GEN-LAST:event_txtWeightKeyTyped

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
            java.util.logging.Logger.getLogger(Recycle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recycle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recycle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recycle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Recycle().setVisible(true);
            }
        });
    }
    
        public void addFilter(JTable table, JTextField textField, int columnIndex) {
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

        textField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filtrar();
            }

            private void filtrar() {
                String searchText = textField.getText();
                if (searchText.trim().isEmpty()) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, columnIndex)); 
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCalculate;
    private javax.swing.JButton btnRecycle;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbResidues;
    private javax.swing.JTextField txtRecyclePoints;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTotalPoints;
    private javax.swing.JTextField txtWeight;
    private javax.swing.JTextField txtYourPoints;
    // End of variables declaration//GEN-END:variables
}
