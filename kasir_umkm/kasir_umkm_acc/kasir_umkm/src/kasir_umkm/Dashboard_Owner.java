package kasir_umkm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class Dashboard_Owner extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    // Instansiasi kelas Database
    private Database db = new Database();

    // Constructor
    public Dashboard_Owner() {
        initComponents();
        db.koneksi();
        refreshData();
        setLayoutForm();
    }

    private void refreshData() {
        pendapatanHariIni();
        penjualanHariIni();
        rataRataTransaksi();
        loadBarangTerlaris();
        loadStokMenipis();
    }

    private void pendapatanHariIni() {
        double pendapatan = db.pendapatanHariIni();
        pendapatanHariini.setText(String.format("Rp %.2f", pendapatan));
    }

    private void penjualanHariIni() {
        int jumlahPenjualan = db.penjualanHariIni(); // Menggunakan metode yang mengembalikan jumlah baris
        penjualanHariIni.setText(String.valueOf(jumlahPenjualan)); // Mengatur teks dengan nilai jumlah penjualan
    }

    private void rataRataTransaksi() {
        double rataRata = db.rataRataTransaksi();
        rataRataTransaksi.setText(String.format("Rp %.2f", rataRata));
    }

    private void loadBarangTerlaris() {
        db.loadBarangTerlaris(jBarangTerlaris);
    }

    private void loadStokMenipis() {
        db.loadStokMenipis(jStokMenipis);
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
        jBarangTerlaris.setFont(new Font("Tahome", Font.PLAIN, 15));
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

    // Kelas Database
    public class Database {

        private Connection conn;

        // Membuka koneksi ke database
        public void koneksi() {
            try {
                String url = "jdbc:mysql://localhost:3306/db_kasir_umkm1";
                String user = "root";
                String pass = "";
                conn = DriverManager.getConnection(url, user, pass);
                System.out.println("Koneksi berhasil!");
            } catch (SQLException e) {
                System.out.println("Koneksi gagal: " + e.getMessage());
            }
        }

        // Mengambil data dari database
        private ResultSet ambildata(String query) {
            try {
                Statement stmt = conn.createStatement();
                return stmt.executeQuery(query);
            } catch (SQLException e) {
                System.out.println("Error mengambil data: " + e.getMessage());
                return null;
            }
        }

        // Menutup koneksi
        public void close() {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("Koneksi ditutup.");
                }
            } catch (SQLException e) {
                System.out.println("Error menutup koneksi: " + e.getMessage());
            }
        }

        // Implementasi method untuk mengambil data dari database
        public double pendapatanHariIni() {
            String query = "SELECT SUM(grand_total) AS totalPendapatan FROM penjualan WHERE tanggal_jual = CURDATE()";
            ResultSet rs = ambildata(query);
            try {
                if (rs != null && rs.next()) {
                    return rs.getDouble("totalPendapatan");
                }
            } catch (SQLException e) {
                System.out.println("Error mengambil data pendapatan: " + e.getMessage());
            }
            return 0.0;
        }

        public int penjualanHariIni() {
            // Query untuk menghitung jumlah penjualan hari ini
            String query = "SELECT COUNT(p.id_penjualan) AS jumlahPenjualan "
                    + "FROM penjualan p "
                    + "WHERE DATE(p.tanggal_jual) = CURDATE()";

            ResultSet rs = ambildata(query); // Pastikan metode ambildata() menangani koneksi dan statement
            try {
                if (rs != null && rs.next()) {
                    return rs.getInt("jumlahPenjualan"); // Ambil jumlah penjualan
                }
            } catch (SQLException e) {
                System.out.println("Error mengambil data penjualan: " + e.getMessage());
            } finally {
                try {
                    if (rs != null) {
                        rs.close(); // Tutup ResultSet
                    }
                } catch (SQLException e) {
                    System.out.println("Error menutup ResultSet: " + e.getMessage());
                }
            }
            return 0; // Jika tidak ada data atau terjadi error
        }

        public double rataRataTransaksi() {
            String query = "SELECT AVG(grand_total) AS rataRata FROM penjualan WHERE tanggal_jual = CURDATE()";
            ResultSet rs = ambildata(query);
            try {
                if (rs != null && rs.next()) {
                    return rs.getDouble("rataRata");
                }
            } catch (SQLException e) {
                System.out.println("Error mengambil data rata-rata transaksi: " + e.getMessage());
            }
            return 0.0;
        }

        public void loadBarangTerlaris(javax.swing.JTable table) {
            try {
                String query = "SELECT p.nama_produk, p.harga_jual, COUNT(detail_penjualan.id_penjualan) AS terjual "
                        + "FROM produk p "
                        + "JOIN detail_penjualan ON p.id_produk = detail_penjualan.id_produk "
                        + "GROUP BY p.id_produk "
                        + "ORDER BY terjual DESC "
                        + "LIMIT 10";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);

                while (rs.next()) {
                    String produk = rs.getString("nama_produk");
                    int harga = rs.getInt("harga_jual");
                    int terjual = rs.getInt("terjual");
                    model.addRow(new Object[]{produk, harga, terjual});
                }

                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Error mengambil data barang terlaris: " + e.getMessage());
            }
        }

        public void loadStokMenipis(javax.swing.JTable table) {
            try {
                String query = "SELECT nama_produk, stok, 12 AS minimal FROM produk WHERE stok <= 12";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);

                while (rs.next()) {
                    String produk = rs.getString("nama_produk");
                    int stok = rs.getInt("stok");
                    int minimal = rs.getInt("minimal");
                    model.addRow(new Object[]{produk, stok, minimal});
                }

                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Error mengambil data stok menipis: " + e.getMessage());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jStokMenipis = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jBarangTerlaris = new javax.swing.JTable();
        juser = new javax.swing.JButton();
        jtransaksi = new javax.swing.JButton();
        jlaporan = new javax.swing.JButton();
        jdashboard3 = new javax.swing.JButton();
        jproduk = new javax.swing.JButton();
        jkeluar = new javax.swing.JButton();
        penjualanHariIni = new javax.swing.JTextField();
        rataRataTransaksi = new javax.swing.JTextField();
        pendapatanHariini = new javax.swing.JTextField();
        dashboar_Owner1 = new com.raven.main.Dashboar_Owner();
        jpengguna = new javax.swing.JLabel();
        Username1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/seblak.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jStokMenipis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null},
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
        jStokMenipis.setEnabled(false);
        jStokMenipis.setRowHeight(30);
        jScrollPane2.setViewportView(jStokMenipis);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 300, 350, 230));

        jBarangTerlaris.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null},
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
        jBarangTerlaris.setEnabled(false);
        jBarangTerlaris.setRowHeight(30);
        jScrollPane9.setViewportView(jBarangTerlaris);

        getContentPane().add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, 350, 230));

        juser.setBackground(new java.awt.Color(204, 204, 204));
        juser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        juser.setText("USER");
        juser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                juserActionPerformed(evt);
            }
        });
        getContentPane().add(juser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 660, 170, 60));

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

        jkeluar.setBackground(new java.awt.Color(255, 0, 0));
        jkeluar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jkeluar.setText("KELUAR");
        jkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkeluarActionPerformed(evt);
            }
        });
        getContentPane().add(jkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 150, 60));

        penjualanHariIni.setBackground(new java.awt.Color(153, 153, 153));
        penjualanHariIni.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        penjualanHariIni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        penjualanHariIni.setEnabled(false);
        penjualanHariIni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penjualanHariIniActionPerformed(evt);
            }
        });
        getContentPane().add(penjualanHariIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, 180, 50));

        rataRataTransaksi.setBackground(new java.awt.Color(153, 153, 153));
        rataRataTransaksi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rataRataTransaksi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rataRataTransaksi.setEnabled(false);
        getContentPane().add(rataRataTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 180, 190, 50));

        pendapatanHariini.setBackground(new java.awt.Color(153, 153, 153));
        pendapatanHariini.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pendapatanHariini.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pendapatanHariini.setEnabled(false);
        pendapatanHariini.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendapatanHariiniActionPerformed(evt);
            }
        });
        getContentPane().add(pendapatanHariini, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 190, 50));
        getContentPane().add(dashboar_Owner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jpengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/popup/profile.png"))); // NOI18N
        jpengguna.setText("    ");
        jpengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpenggunaMouseClicked(evt);
            }
        });
        getContentPane().add(jpengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, -1));

        Username1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Username1.setForeground(new java.awt.Color(255, 255, 255));
        Username1.setText("Username");
        getContentPane().add(Username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        Posisi1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Posisi1.setForeground(new java.awt.Color(255, 255, 255));
        Posisi1.setText("Posisi");
        getContentPane().add(Posisi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/Dashboard OwnerAdmin.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1277, 784));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void juserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_juserActionPerformed
        new Data_user().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_juserActionPerformed

    private void jtransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtransaksiActionPerformed
        new Transaksi().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jtransaksiActionPerformed

    private void jlaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlaporanActionPerformed
        new menu_laporan().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jlaporanActionPerformed

    private void jdashboard3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdashboard3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jdashboard3ActionPerformed

    private void jprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jprodukActionPerformed
        new Data_produk().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jprodukActionPerformed

    private void penjualanHariIniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penjualanHariIniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_penjualanHariIniActionPerformed

    private void pendapatanHariiniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendapatanHariiniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pendapatanHariiniActionPerformed

    private void jkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkeluarActionPerformed
        new Masuk().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jkeluarActionPerformed

    private void jpenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpenggunaMouseClicked
        new Form_user().setVisible(true);
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
            java.util.logging.Logger.getLogger(Dashboard_Owner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Owner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Owner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Owner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard_Owner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public final javax.swing.JLabel Posisi1 = new javax.swing.JLabel();
    public javax.swing.JLabel Username1;
    private com.raven.main.Dashboar_Owner dashboar_Owner1;
    private javax.swing.JTable jBarangTerlaris;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jStokMenipis;
    private javax.swing.JButton jdashboard3;
    private javax.swing.JButton jkeluar;
    private javax.swing.JButton jlaporan;
    private javax.swing.JLabel jpengguna;
    private javax.swing.JButton jproduk;
    private javax.swing.JButton jtransaksi;
    private javax.swing.JButton juser;
    private javax.swing.JTextField pendapatanHariini;
    private javax.swing.JTextField penjualanHariIni;
    private javax.swing.JTextField rataRataTransaksi;
    // End of variables declaration//GEN-END:variables
}
