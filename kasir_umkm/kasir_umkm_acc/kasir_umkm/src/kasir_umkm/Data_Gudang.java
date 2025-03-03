package kasir_umkm;

import java.awt.Font;
import java.text.SimpleDateFormat; // Untuk format tanggal
import java.sql.ResultSet;
import java.text.MessageFormat;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import popuplaporan.popupgudang;

/**
 *
 * @author THINKPAD
 */
public class Data_Gudang extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    database db = new database();

    public Data_Gudang() {
        initComponents();
        db.koneksi();
        aturTabel();
        setLayoutForm();
    }

    private void setLayoutForm() {
        // Pusatkan teks dalam sel data
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Pusatkan horizontal
        cellRenderer.setVerticalAlignment(SwingConstants.CENTER);   // Pusatkan vertikal

        // Terapkan renderer ke setiap kolom
        for (int i = 0; i < jdatagudang.getColumnModel().getColumnCount(); i++) {
            TableColumn column = jdatagudang.getColumnModel().getColumn(i);
            column.setCellRenderer(cellRenderer);
        }

        // Pusatkan teks dalam header tabel
        JTableHeader header = jdatagudang.getTableHeader();
        header.setFont(new Font("Tahome", Font.BOLD, 16));
        jdatagudang.setFont(new Font("Tahome", Font.PLAIN, 18));
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Pusatkan horizontal
        headerRenderer.setVerticalAlignment(SwingConstants.CENTER);   // Pusatkan vertikal
        header.setDefaultRenderer(headerRenderer);
    }
    
    private void tampilkanLaporanGudang() {
        try {
            model.setRowCount(0); // Reset tabel

            String query = "SELECT "
                    + "    dp.id_detail, "
                    + "    dp.id_produk, "
                    + "    p.nama_produk, "
                    + "    dp.harga_beli, "
                    + "    dp.stok, "
                    + "    dp.tanggal_masuk, "
                    + "    dp.is_active "
                    + "FROM "
                    + "    detail_produk dp "
                    + "JOIN "
                    + "    produk p "
                    + "    ON dp.id_produk = p.id_produk "
                    + "ORDER BY dp.tanggal_masuk ASC"; // Urutkan berdasarkan tanggal masuk terlama

            ResultSet rs = db.ambildata(query);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_detail"),
                    rs.getString("id_produk"),
                    rs.getString("nama_produk"),
                    rs.getString("harga_beli"),
                    rs.getString("stok"),
                    rs.getString("tanggal_masuk"),
                    rs.getString("is_active")
                });
            }

            jdatagudang.setModel(model); // Menampilkan data ke tabel
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void aturTabel() {
        model.addColumn("Id Detail");
        model.addColumn("Id Produk");
        model.addColumn("Nama Produk");
        model.addColumn("Harga Beli");
        model.addColumn("Stok Cadangan");
        model.addColumn("Tanggal Masuk");
        model.addColumn("Is Active");

    }

    private void tampilkanLaporanGudangBerdasarkanPeriode() {
        try {
            model.setRowCount(0); // Reset tabel

            // Ambil tanggal mulai dan tanggal akhir dari JDateChooser
            java.util.Date startDate = time1.getDate(); // JDateChooser untuk tanggal mulai
            java.util.Date endDate = time2.getDate(); // JDateChooser untuk tanggal akhir

            if (startDate != null && endDate != null) {
                // Konversi ke format tanggal SQL (yyyy-MM-dd)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String start = sdf.format(startDate);
                String end = sdf.format(endDate);

                // Query untuk mengambil data laporan gudang berdasarkan periode
                String query = "SELECT "
                        + "    dp.id_detail, "
                        + "    dp.id_produk, "
                        + "    p.nama_produk, "
                        + "    dp.harga_beli, "
                        + "    dp.stok, "
                        + "    dp.tanggal_masuk, "
                        + "    dp.is_active "
                        + "FROM "
                        + "    detail_produk dp "
                        + "JOIN "
                        + "    produk p "
                        + "    ON dp.id_produk = p.id_produk "
                        + "WHERE "
                        + "    dp.tanggal_masuk BETWEEN '" + start + "' AND '" + end + "' "
                        + "ORDER BY dp.tanggal_masuk ASC;";

                ResultSet rs = db.ambildata(query);

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("id_detail"),
                        rs.getString("id_produk"),
                        rs.getString("nama_produk"),
                        rs.getString("harga_beli"),
                        rs.getString("stok"),
                        rs.getString("tanggal_masuk"),
                        rs.getString("is_active")
                    });
                }

                jdatagudang.setModel(model); // Menampilkan data ke tabel
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jdatagudang = new javax.swing.JTable();
        time2 = new com.toedter.calendar.JDateChooser();
        time1 = new com.toedter.calendar.JDateChooser();
        jdashboard = new javax.swing.JButton();
        jproduk = new javax.swing.JButton();
        jtransaksi = new javax.swing.JButton();
        jlaporan = new javax.swing.JButton();
        juser = new javax.swing.JButton();
        view_cari = new javax.swing.JButton();
        jkeluar1 = new javax.swing.JButton();
        Export = new javax.swing.JButton();
        jpengguna = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/seblak.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("-");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 30, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("GUDANG BARANG");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, -1, 50));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Periode :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 122, 110, 50));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jdatagudang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id Detail", "Id Produk", "Nama Produk", "Harga Beli", "Stok  Cadangan", "Tanggal Masuk", "Is Active"
            }
        ));
        jdatagudang.setRowHeight(30);
        jScrollPane1.setViewportView(jdatagudang);
        if (jdatagudang.getColumnModel().getColumnCount() > 0) {
            jdatagudang.getColumnModel().getColumn(0).setResizable(false);
            jdatagudang.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 17, 1100, 390));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 1120, 420));
        getContentPane().add(time2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 210, 50));
        getContentPane().add(time1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 210, 50));

        jdashboard.setBackground(new java.awt.Color(204, 204, 204));
        jdashboard.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jdashboard.setText("DASHBOARD");
        jdashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdashboardActionPerformed(evt);
            }
        });
        getContentPane().add(jdashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 680, 170, 60));

        jproduk.setBackground(new java.awt.Color(204, 204, 204));
        jproduk.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jproduk.setText("PRODUK");
        jproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jprodukActionPerformed(evt);
            }
        });
        getContentPane().add(jproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 680, 200, 60));

        jtransaksi.setBackground(new java.awt.Color(204, 204, 204));
        jtransaksi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jtransaksi.setText("TRANSAKSI");
        jtransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(jtransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 680, 180, 60));

        jlaporan.setBackground(new java.awt.Color(204, 204, 204));
        jlaporan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlaporan.setText("LAPORAN");
        jlaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlaporanActionPerformed(evt);
            }
        });
        getContentPane().add(jlaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 680, 190, 60));

        juser.setBackground(new java.awt.Color(204, 204, 204));
        juser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        juser.setText("USER");
        juser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                juserActionPerformed(evt);
            }
        });
        getContentPane().add(juser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 680, 170, 60));

        view_cari.setBackground(new java.awt.Color(153, 153, 153));
        view_cari.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        view_cari.setText("VIEW");
        view_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_cariActionPerformed(evt);
            }
        });
        getContentPane().add(view_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 120, 120, 50));

        jkeluar1.setBackground(new java.awt.Color(255, 0, 0));
        jkeluar1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jkeluar1.setText("KELUAR");
        jkeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkeluar1ActionPerformed(evt);
            }
        });
        getContentPane().add(jkeluar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 150, 60));

        Export.setBackground(new java.awt.Color(153, 153, 153));
        Export.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Export.setText("EXPORT");
        Export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportActionPerformed(evt);
            }
        });
        getContentPane().add(Export, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 120, 120, 50));

        jpengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/popup/profile.png"))); // NOI18N
        jpengguna.setText("    ");
        jpengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpenggunaMouseClicked(evt);
            }
        });
        getContentPane().add(jpengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/Halaman Produk Kosong.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1279, 794));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void view_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_cariActionPerformed
        // TODO add your handling code here:
        // Mengecek apakah periode bebas atau periode bulanan dipilih
        if (time1.getDate() != null && time2.getDate() != null) {
            // Jika kedua tanggal (periode bebas) dipilih
            tampilkanLaporanGudangBerdasarkanPeriode();
        } else {
            // Jika tidak ada pilihan yang valid
            popupgudang gudang = new popupgudang();
            gudang.setAlwaysOnTop(true);  // Agar popup berada di depan
            gudang.setVisible(true);
        }

    }//GEN-LAST:event_view_cariActionPerformed

    private void jdashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdashboardActionPerformed
        new Dashboard_Owner().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jdashboardActionPerformed

    private void jprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jprodukActionPerformed
        new Data_produk().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jprodukActionPerformed

    private void jtransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtransaksiActionPerformed
        new Transaksi().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jtransaksiActionPerformed

    private void jlaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlaporanActionPerformed
        new menu_laporan().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jlaporanActionPerformed

    private void juserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_juserActionPerformed
        new Data_user().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_juserActionPerformed

    private void jkeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkeluar1ActionPerformed
        new Masuk().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jkeluar1ActionPerformed

    private void jpenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpenggunaMouseClicked
        new Form_user().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jpenggunaMouseClicked

    private void ExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportActionPerformed
        MessageFormat header = new MessageFormat("DATA GUDANG");
        MessageFormat footer = new MessageFormat("");

        try {
            PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
            set.add(OrientationRequested.PORTRAIT);
            jdatagudang.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, set, true);
            JOptionPane.showMessageDialog(null, "\n" + "Printed Succefully");

        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(null, "\n" + "Failed" + "\n" + e);

        }
    }//GEN-LAST:event_ExportActionPerformed

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
            java.util.logging.Logger.getLogger(Data_Gudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Data_Gudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Data_Gudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Data_Gudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Data_Gudang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Export;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jdashboard;
    private javax.swing.JTable jdatagudang;
    private javax.swing.JButton jkeluar1;
    private javax.swing.JButton jlaporan;
    private javax.swing.JLabel jpengguna;
    private javax.swing.JButton jproduk;
    private javax.swing.JButton jtransaksi;
    private javax.swing.JButton juser;
    private com.toedter.calendar.JDateChooser time1;
    private com.toedter.calendar.JDateChooser time2;
    private javax.swing.JButton view_cari;
    // End of variables declaration//GEN-END:variables
}
