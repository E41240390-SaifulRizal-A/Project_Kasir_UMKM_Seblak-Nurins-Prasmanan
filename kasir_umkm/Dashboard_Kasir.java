package kasir_umkm;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import popuplaporan.popupposisi;

/**
 *
 * @author Saiful
 */
public class Dashboard_Kasir extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    database db = new database();

    private javax.swing.JButton jButtonOwnerMenu;

    public Dashboard_Kasir() {
        initComponents();
        loadBarangTerlaris();
        loadStokMenipis(); // Panggil method untuk memuat data barang terlaris
        setLayoutForm();
    }

    private void setLayoutForm() {
        // Pusatkan teks dalam sel data
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Pusatkan horizontal
        cellRenderer.setVerticalAlignment(SwingConstants.CENTER);   // Pusatkan vertikal

        DefaultTableCellRenderer cellRenderer1 = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Pusatkan horizontal
        cellRenderer.setVerticalAlignment(SwingConstants.CENTER);   // Pusatkan vertikal

        // Terapkan renderer ke setiap kolom
        for (int i = 0; i < jBarangTerlaris.getColumnModel().getColumnCount(); i++) {
            TableColumn column = jBarangTerlaris.getColumnModel().getColumn(i);
            column.setCellRenderer(cellRenderer);
        }
        for (int i = 0; i < jStokMenipis.getColumnModel().getColumnCount(); i++) {
            TableColumn column = jStokMenipis.getColumnModel().getColumn(i);
            column.setCellRenderer(cellRenderer);
        }

        // Pusatkan teks dalam header tabel
        JTableHeader header = jBarangTerlaris.getTableHeader();
        jBarangTerlaris.setFont(new Font("Tahome", Font.PLAIN, 18));
        header.setFont(new Font("Tahome", Font.BOLD, 18));
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Pusatkan horizontal
        headerRenderer.setVerticalAlignment(SwingConstants.CENTER);   // Pusatkan vertikal
        header.setDefaultRenderer(headerRenderer);

        JTableHeader header1 = jStokMenipis.getTableHeader();
        header1.setFont(new Font("Tahome", Font.BOLD, 18));
        jStokMenipis.setFont(new Font("Tahome", Font.PLAIN, 15));
        DefaultTableCellRenderer headerRenderer1 = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer1.setHorizontalAlignment(SwingConstants.CENTER); // Pusatkan horizontal
        headerRenderer1.setVerticalAlignment(SwingConstants.CENTER);   // Pusatkan vertikal
        header1.setDefaultRenderer(headerRenderer);

        new Timer(5000, e -> {
            loadBarangTerlaris(); // Memperbarui data barang terlaris
            loadStokMenipis(); // Memperbarui data stok menipis
        }).start();

    }

    private void loadBarangTerlaris() {
        try {
            // Koneksi ke database
            String url = "jdbc:mysql://localhost:3306/db_kasir_umkm1"; // Ganti dengan nama database
            String username = "root"; // Ganti dengan username MySQL
            String password = ""; // Ganti dengan password MySQL
            Connection conn = DriverManager.getConnection(url, username, password);

            String query = "SELECT p.nama_produk, p.harga_jual, COUNT(detail_penjualan.id_penjualan) AS terjual "
                    + "FROM produk p "
                    + "JOIN detail_penjualan ON p.id_produk = detail_penjualan.id_produk "
                    + "GROUP BY p.id_produk "
                    + "ORDER BY terjual DESC "
                    + "LIMIT 10";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // Model tabel untuk jBarangTerlaris
            DefaultTableModel model = (DefaultTableModel) jBarangTerlaris.getModel();
            model.setRowCount(0); // Hapus data lama

            // Tambahkan data ke tabel
            while (rs.next()) {
                String Produk = rs.getString("nama_produk");
                int Harga = rs.getInt("harga_jual");
                int Terjual = rs.getInt("terjual");
                model.addRow(new Object[]{Produk, Harga, Terjual});
            }

            // Tambahkan listener untuk mendeteksi perubahan model tabel
            model.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    if (e.getType() == TableModelEvent.UPDATE) {
                        // Tabel telah diperbarui
                        JOptionPane.showMessageDialog(Dashboard_Kasir.this, "Data barang terlaris telah diperbarui.");
                    }
                }
            });
            // Tutup koneksi
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data barang terlaris: " + e.getMessage());
        }
    }

    /**
     * Creates new form Dashboard_Kasir
     */
    // Method untuk memuat data stok menipis
    // Method untuk memuat data stok menipis
    private void loadStokMenipis() {
        try {
            // Koneksi ke database
            String url = "jdbc:mysql://localhost:3306/db_kasir_umkm1";
            String username = "root";
            String password = "";
            Connection conn = DriverManager.getConnection(url, username, password);

            // Query untuk stok menipis
            String query = "SELECT nama_produk, stok, 12 AS minimal "
                    + "FROM produk "
                    + "WHERE stok <= 12";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Model tabel untuk jStokMenipis
            DefaultTableModel model = (DefaultTableModel) jStokMenipis.getModel();
            model.setRowCount(0); // Hapus data lama

            // Tambahkan data ke tabel
            while (rs.next()) {
                String produk = rs.getString("nama_produk");
                int stok = rs.getInt("stok");
                int minimal = rs.getInt("minimal");
                model.addRow(new Object[]{produk, stok, minimal});
            }

            // Tutup koneksi
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data stok menipis: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jBarangTerlaris = new javax.swing.JTable();
        jkeluar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jStokMenipis = new javax.swing.JTable();
        jdashboard3 = new javax.swing.JButton();
        jproduk = new javax.swing.JButton();
        jtransaksi = new javax.swing.JButton();
        jlaporan1 = new javax.swing.JButton();
        juser = new javax.swing.JButton();
        jpengguna = new javax.swing.JLabel();
        Username = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/seblak.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBarangTerlaris.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Produk", "Harga", "Terjual"
            }
        ));
        jBarangTerlaris.setRowHeight(30);
        jScrollPane1.setViewportView(jBarangTerlaris);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 410, 270));

        jkeluar.setBackground(new java.awt.Color(255, 0, 0));
        jkeluar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jkeluar.setText("KELUAR");
        jkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkeluarActionPerformed(evt);
            }
        });
        getContentPane().add(jkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 150, 60));

        jStokMenipis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Produk", "Tersisa", "Minimal"
            }
        ));
        jStokMenipis.setRowHeight(30);
        jScrollPane2.setViewportView(jStokMenipis);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 250, 410, 270));

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

        jlaporan1.setBackground(new java.awt.Color(204, 204, 204));
        jlaporan1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlaporan1.setText("LAPORAN");
        jlaporan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlaporan1ActionPerformed(evt);
            }
        });
        getContentPane().add(jlaporan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 660, 190, 60));

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

        Username.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Username.setForeground(new java.awt.Color(255, 255, 255));
        Username.setText("Username");
        getContentPane().add(Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        Posisi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Posisi.setForeground(new java.awt.Color(255, 255, 255));
        Posisi.setText("Posisi");
        getContentPane().add(Posisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/Dashboard Kasir.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1282, 800));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkeluarActionPerformed
        new Masuk().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jkeluarActionPerformed

    private void jdashboard3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdashboard3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jdashboard3ActionPerformed

    private void jprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jprodukActionPerformed
        new Data_produkkasir().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jprodukActionPerformed

    private void jtransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtransaksiActionPerformed
        new Transaksi_kasir().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jtransaksiActionPerformed

    private void jlaporan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlaporan1ActionPerformed
        popupposisi posisi = new popupposisi();
        posisi.setAlwaysOnTop(true);  // Agar popup berada di depan
        posisi.setVisible(true);
    }//GEN-LAST:event_jlaporan1ActionPerformed

    private void juserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_juserActionPerformed
        popupposisi posisi = new popupposisi();
        posisi.setAlwaysOnTop(true);  // Agar popup berada di depan
        posisi.setVisible(true);
    }//GEN-LAST:event_juserActionPerformed

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
            java.util.logging.Logger.getLogger(Dashboard_Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard_Kasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public final javax.swing.JLabel Posisi = new javax.swing.JLabel();
    public javax.swing.JLabel Username;
    private javax.swing.JTable jBarangTerlaris;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jStokMenipis;
    private javax.swing.JButton jdashboard3;
    private javax.swing.JButton jkeluar;
    public static javax.swing.JButton jlaporan1;
    private javax.swing.JLabel jpengguna;
    private javax.swing.JButton jproduk;
    private javax.swing.JButton jtransaksi;
    public static javax.swing.JButton juser;
    // End of variables declaration//GEN-END:variables
}
