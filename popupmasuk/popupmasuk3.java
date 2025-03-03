/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package popupmasuk;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

import kasir_umkm.Masuk;

/**
 *
 * @author Tijani Robith
 */
public class popupmasuk3 extends javax.swing.JFrame {

    private Connection conn;

    // Koneksi database
    private void connectToDatabase() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_kasir_umkm1", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal terhubung ke database: " + e.getMessage());
        }
    }

    public popupmasuk3() {
        initComponents();
        setIcon();
        connectToDatabase();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jganti_password = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jganti_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jganti_passwordActionPerformed(evt);
            }
        });
        getContentPane().add(jganti_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 270, 40));

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("OKE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 110, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Password baru tidak valid. Harus minimal 8 karakter");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/popup/BG popup.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(417, 193));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String newPassword = jganti_password.getText().trim();

        // Validasi password
        if (newPassword.length() < 8) {
            jLabel1.setText("Password baru tidak valid. Harus minimal 8 karakter");
            return; // Hentikan jika password tidak valid
        }

        // Memperbarui password di database
        if (updatePassword(newPassword)) {
            this.dispose();
        }
    }

    private boolean updatePassword(String newPassword) {
        try {
            // Query SQL untuk mengupdate password untuk posisi 'Owner'
            String sql = "UPDATE users SET password = ? WHERE posisi = 'Owner'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newPassword); // Mengatur password baru

            int rowsUpdated = ps.executeUpdate(); // Eksekusi query
            return rowsUpdated > 0; // Return true jika update berhasil
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jganti_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jganti_passwordActionPerformed

    }//GEN-LAST:event_jganti_passwordActionPerformed

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
            java.util.logging.Logger.getLogger(popupmasuk3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(popupmasuk3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(popupmasuk3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(popupmasuk3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new popupmasuk3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jganti_password;
    // End of variables declaration//GEN-END:variables
        private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("seblak.png")));
    }
}
