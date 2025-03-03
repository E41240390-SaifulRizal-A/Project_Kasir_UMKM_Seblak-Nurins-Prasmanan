/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kasir_umkm;

import popuplaporan.popupposisi;

/**
 *
 * @author Saiful
 */
public class Transaksi_kasir extends javax.swing.JFrame {

    /**
     * Creates new form menu_laporan
     */
    public Transaksi_kasir() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTransaksi_pembelian = new javax.swing.JButton();
        jTransaksi_penjualan = new javax.swing.JButton();
        jdashboard3 = new javax.swing.JButton();
        jproduk = new javax.swing.JButton();
        jtransaksi = new javax.swing.JButton();
        jlaporan = new javax.swing.JButton();
        jkeluar = new javax.swing.JButton();
        juser = new javax.swing.JButton();
        jpengguna = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/seblak.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTransaksi_pembelian.setBackground(new java.awt.Color(0, 51, 102));
        jTransaksi_pembelian.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTransaksi_pembelian.setForeground(new java.awt.Color(255, 255, 255));
        jTransaksi_pembelian.setText("PEMBELIAN");
        jTransaksi_pembelian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTransaksi_pembelianActionPerformed(evt);
            }
        });
        getContentPane().add(jTransaksi_pembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 470, 220, 60));

        jTransaksi_penjualan.setBackground(new java.awt.Color(0, 51, 102));
        jTransaksi_penjualan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTransaksi_penjualan.setForeground(new java.awt.Color(255, 255, 255));
        jTransaksi_penjualan.setText("PENJUALAN");
        jTransaksi_penjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTransaksi_penjualanActionPerformed(evt);
            }
        });
        getContentPane().add(jTransaksi_penjualan, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 470, 200, 60));

        jdashboard3.setBackground(new java.awt.Color(204, 204, 204));
        jdashboard3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jdashboard3.setText("DASHBOARD");
        jdashboard3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdashboard3ActionPerformed(evt);
            }
        });
        getContentPane().add(jdashboard3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, 170, 60));

        jproduk.setBackground(new java.awt.Color(204, 204, 204));
        jproduk.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jproduk.setText("PRODUK");
        jproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jprodukActionPerformed(evt);
            }
        });
        getContentPane().add(jproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 660, 200, 60));

        jtransaksi.setBackground(new java.awt.Color(204, 204, 204));
        jtransaksi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jtransaksi.setText("TRANSAKSI");
        jtransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(jtransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 660, 180, 60));

        jlaporan.setBackground(new java.awt.Color(204, 204, 204));
        jlaporan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlaporan.setText("LAPORAN");
        jlaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlaporanActionPerformed(evt);
            }
        });
        getContentPane().add(jlaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 660, 190, 60));

        jkeluar.setBackground(new java.awt.Color(255, 0, 0));
        jkeluar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jkeluar.setText("KELUAR");
        jkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkeluarActionPerformed(evt);
            }
        });
        getContentPane().add(jkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 150, 60));

        juser.setBackground(new java.awt.Color(204, 204, 204));
        juser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        juser.setText("USER");
        juser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                juserActionPerformed(evt);
            }
        });
        getContentPane().add(juser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 660, 170, 60));

        jpengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/popup/profile.png"))); // NOI18N
        jpengguna.setText("    ");
        jpengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpenggunaMouseClicked(evt);
            }
        });
        getContentPane().add(jpengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/Halaman Transaksi Awal.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1281, 800));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTransaksi_penjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTransaksi_penjualanActionPerformed
        new Transaksi_penjualan_kasir().setVisible(true); // membuka halaman User
        setVisible(false);
    }//GEN-LAST:event_jTransaksi_penjualanActionPerformed

    private void jTransaksi_pembelianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTransaksi_pembelianActionPerformed
        new Transaksi_pembelian_kasir().setVisible(true); // membuka halaman User
        setVisible(false);
    }//GEN-LAST:event_jTransaksi_pembelianActionPerformed

    private void jdashboard3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdashboard3ActionPerformed
        new Dashboard_Kasir().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jdashboard3ActionPerformed

    private void jprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jprodukActionPerformed
        new Data_produkkasir().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jprodukActionPerformed

    private void jtransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtransaksiActionPerformed
        new Transaksi_kasir().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jtransaksiActionPerformed

    private void jlaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlaporanActionPerformed
        popupposisi posisi = new popupposisi();
        posisi.setAlwaysOnTop(true);  // Agar popup berada di depan
        posisi.setVisible(true);
    }//GEN-LAST:event_jlaporanActionPerformed

    private void juserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_juserActionPerformed
        popupposisi posisi = new popupposisi();
        posisi.setAlwaysOnTop(true);  // Agar popup berada di depan
        posisi.setVisible(true);
    }//GEN-LAST:event_juserActionPerformed

    private void jkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkeluarActionPerformed
        new Masuk().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jkeluarActionPerformed

    private void jpenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpenggunaMouseClicked
        new Form_user_kasir().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jpenggunaMouseClicked

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
            java.util.logging.Logger.getLogger(Transaksi_kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi_kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi_kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi_kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi_kasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jTransaksi_pembelian;
    private javax.swing.JButton jTransaksi_penjualan;
    private javax.swing.JButton jdashboard3;
    private javax.swing.JButton jkeluar;
    private javax.swing.JButton jlaporan;
    private javax.swing.JLabel jpengguna;
    private javax.swing.JButton jproduk;
    private javax.swing.JButton jtransaksi;
    private javax.swing.JButton juser;
    // End of variables declaration//GEN-END:variables
}
