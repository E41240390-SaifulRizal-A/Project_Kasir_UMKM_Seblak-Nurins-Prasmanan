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
import java.util.Calendar;

/**
 *
 * @author Saiful
 */
public class laporan_penjualan extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    database db = new database();

    /**
     * Creates new form laporan_penjualan
     */
    public laporan_penjualan() {
        initComponents();
        db.koneksi();
        aturTabel();
        tampilkanDataBerdasarkanPeriodeBebas();
        tampilkanDataBerdasarkanBulan();
        setLayoutForm();
    }

    private void setLayoutForm() {
        // Pusatkan teks dalam sel data
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Pusatkan horizontal
        cellRenderer.setVerticalAlignment(SwingConstants.CENTER);   // Pusatkan vertikal

        // Terapkan renderer ke setiap kolom
        for (int i = 0; i < tabelview.getColumnModel().getColumnCount(); i++) {
            TableColumn column = tabelview.getColumnModel().getColumn(i);
            column.setCellRenderer(cellRenderer);
        }

        // Pusatkan teks dalam header tabel
        JTableHeader header = tabelview.getTableHeader();
        header.setFont(new Font("Tahome", Font.BOLD, 15));
        tabelview.setFont(new Font("Tahome", Font.PLAIN, 16));
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Pusatkan horizontal
        headerRenderer.setVerticalAlignment(SwingConstants.CENTER);   // Pusatkan vertikal
        header.setDefaultRenderer(headerRenderer);
    }

    private String getEndDateOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    private String getStartDateOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    private void tampilkanPenjualanTerbaru() {
        try {
            model.setRowCount(0); // Reset tabel

            String query = "SELECT "
                    + "    penjualan.id_penjualan, "
                    + "    penjualan.tanggal_jual, "
                    + "    detail_penjualan.id_produk, "
                    + "    produk.nama_produk, "
                    + "    detail_penjualan.varian_level, "
                    + "    detail_penjualan.jumlah_pcs, "
                    + "    detail_penjualan.subtotal, "
                    + "    penjualan.grand_total "
                    + "FROM "
                    + "    penjualan "
                    + "JOIN "
                    + "    detail_penjualan "
                    + "    ON penjualan.id_penjualan = detail_penjualan.id_penjualan "
                    + "JOIN "
                    + "    produk "
                    + "    ON detail_penjualan.id_produk = produk.id_produk "
                    + "ORDER BY penjualan.tanggal_jual DESC "
                    + "LIMIT 10"; // Ambil 10 data terbaru

            ResultSet rs = db.ambildata(query);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_penjualan"),
                    rs.getString("tanggal_jual"),
                    rs.getString("id_produk"),
                    rs.getString("nama_produk"),
                    rs.getString("varian_level"),
                    rs.getString("jumlah_pcs"),
                    rs.getString("subtotal"),
                    rs.getString("grand_total")
                });
            }

            tabelview.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void aturTabel() {
        model.addColumn("Id Penjualan");
        model.addColumn("Tanggal Jual");
        model.addColumn("Id  Produk");
        model.addColumn("Nama Produk");
        model.addColumn("Varian Level");
        model.addColumn("Jumlah Pcs");
        model.addColumn("Sub Total");
        model.addColumn("Grand Total");
    }

    private void hitungTotal(String startDate, String endDate) {
        try {
            ResultSet rs = db.ambildata(
                    "SELECT "
                    + "    SUM(detail_penjualan.jumlah_pcs) AS total_jumlah_pcs, "
                    + "    SUM(penjualan.grand_total) AS total_grand_total "
                    + "FROM "
                    + "    penjualan "
                    + "JOIN "
                    + "    detail_penjualan ON penjualan.id_penjualan = detail_penjualan.id_penjualan "
                    + "WHERE "
                    + "    penjualan.tanggal_jual BETWEEN '" + startDate + "' AND '" + endDate + "'"
            );

            if (rs.next()) {
                jumlahproduk.setText(rs.getString("total_jumlah_pcs"));
                jumlahharga.setText(rs.getString("total_grand_total"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tampilkanDataBerdasarkanPeriodeBebas() {
        try {
            model.setRowCount(0); // Reset tabel

            // Ambil tanggal mulai dan tanggal akhir dari JDateChooser
            java.util.Date startDate = time1.getDate(); // Misalnya dateChooserStart untuk tanggal mulai
            java.util.Date endDate = time2.getDate(); // Misalnya dateChooserEnd untuk tanggal akhir

            if (startDate != null && endDate != null) {
                // Konversi ke format tanggal SQL (yyyy-MM-dd)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String start = sdf.format(startDate);
                String end = sdf.format(endDate);

                String query = "SELECT "
                        + "    penjualan.id_penjualan, "
                        + "    penjualan.tanggal_jual, "
                        + "    detail_penjualan.id_produk, "
                        + "    produk.nama_produk, "
                        + "    detail_penjualan.varian_level, "
                        + "    detail_penjualan.jumlah_pcs, "
                        + "    detail_penjualan.subtotal, "
                        + "    penjualan.grand_total "
                        + "FROM "
                        + "    penjualan "
                        + "JOIN "
                        + "    detail_penjualan "
                        + "    ON penjualan.id_penjualan = detail_penjualan.id_penjualan "
                        + "JOIN "
                        + "    produk "
                        + "    ON detail_penjualan.id_produk = produk.id_produk "
                        + "WHERE "
                        + "    penjualan.tanggal_jual BETWEEN '" + start + "' AND '" + end + "' "
                        + "ORDER BY penjualan.id_penjualan ASC;";

                ResultSet rs = db.ambildata(query);

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("id_penjualan"),
                        rs.getString("tanggal_jual"),
                        rs.getString("id_produk"),
                        rs.getString("nama_produk"),
                        rs.getString("varian_level"),
                        rs.getString("jumlah_pcs"),
                        rs.getString("subtotal"),
                        rs.getString("grand_total")
                    });
                }
                tabelview.setModel(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tampilkanDataBerdasarkanBulan() {
        try {

            model.setRowCount(0); // Reset tabel

            // Ambil bulan yang dipilih dari JMonthChooser (bulan mulai dari 0)
            int selectedMonth = jMonthChooser1.getMonth();  // jMonthChooser1 memberi bulan yang dipilih
            int selectedYear = jYearChooser1.getYear(); // Ambil tahun dari JYearChooser

            // Tentukan tanggal awal dan akhir bulan
            String startDate = selectedYear + "-" + (selectedMonth + 1) + "-01";
            String endDate = getEndDateOfMonth(selectedYear, selectedMonth);

            String query = "SELECT "
                    + "    penjualan.id_penjualan, "
                    + "    penjualan.tanggal_jual, "
                    + "    detail_penjualan.id_produk, "
                    + "    produk.nama_produk, "
                    + "    detail_penjualan.varian_level, "
                    + "    detail_penjualan.jumlah_pcs, "
                    + "    detail_penjualan.subtotal, "
                    + "    penjualan.grand_total "
                    + "FROM "
                    + "    penjualan "
                    + "JOIN "
                    + "    detail_penjualan "
                    + "    ON penjualan.id_penjualan = detail_penjualan.id_penjualan "
                    + "JOIN "
                    + "    produk "
                    + "    ON detail_penjualan.id_produk = produk.id_produk "
                    + "WHERE "
                    + "    penjualan.tanggal_jual BETWEEN '" + startDate + "' AND '" + endDate + "' "
                    + "ORDER BY penjualan.id_penjualan ASC;";

            ResultSet rs = db.ambildata(query);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_penjualan"),
                    rs.getString("tanggal_jual"),
                    rs.getString("id_produk"),
                    rs.getString("nama_produk"),
                    rs.getString("varian_level"),
                    rs.getString("jumlah_pcs"),
                    rs.getString("subtotal"),
                    rs.getString("grand_total")
                });
            }
            tabelview.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void resetForm() {
        // Reset periode bebas (tanggal)
        time1.setDate(null);  // Reset tanggal mulai
        time2.setDate(null);  // Reset tanggal akhir

        // Reset periode bulan (bulan dan tahun)
        jMonthChooser1.setMonth(-1); // Reset bulan (tidak ada bulan yang dipilih)
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        view_cari = new javax.swing.JButton();
        Export = new javax.swing.JButton();
        jkeluar = new javax.swing.JButton();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jumlahharga = new javax.swing.JTextField();
        jumlahproduk = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelview = new javax.swing.JTable();
        time2 = new com.toedter.calendar.JDateChooser();
        time1 = new com.toedter.calendar.JDateChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jpengguna = new javax.swing.JLabel();
        jdashboard = new javax.swing.JButton();
        jproduk = new javax.swing.JButton();
        jtransaksi1 = new javax.swing.JButton();
        jlaporan = new javax.swing.JButton();
        juser = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/seblak.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        view_cari.setBackground(new java.awt.Color(153, 153, 153));
        view_cari.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        view_cari.setText("VIEW");
        view_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_cariActionPerformed(evt);
            }
        });
        getContentPane().add(view_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 160, 120, 50));

        Export.setBackground(new java.awt.Color(153, 153, 153));
        Export.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Export.setText("EXPORT");
        Export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportActionPerformed(evt);
            }
        });
        getContentPane().add(Export, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 160, 120, 50));

        jkeluar.setBackground(new java.awt.Color(255, 0, 0));
        jkeluar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jkeluar.setText("KELUAR");
        jkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkeluarActionPerformed(evt);
            }
        });
        getContentPane().add(jkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 10, 140, 50));
        getContentPane().add(jMonthChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 100, -1, 50));

        jumlahharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahhargaActionPerformed(evt);
            }
        });
        getContentPane().add(jumlahharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 250, 190, 50));

        jumlahproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahprodukActionPerformed(evt);
            }
        });
        getContentPane().add(jumlahproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, 190, 50));

        tabelview.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id Penjualan", "Tanggal Jual", "Id produk", "Nama Produk", "Varian Level", "Jumlah Pcs", "Sub Total", "Grand Total"
            }
        ));
        tabelview.setRowHeight(30);
        jScrollPane1.setViewportView(tabelview);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 1020, 280));
        getContentPane().add(time2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, 190, 50));
        getContentPane().add(time1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 190, 50));
        getContentPane().add(jYearChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 100, 120, 50));

        jpengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/popup/profile.png"))); // NOI18N
        jpengguna.setText("    ");
        jpengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpenggunaMouseClicked(evt);
            }
        });
        getContentPane().add(jpengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, -1));

        jdashboard.setBackground(new java.awt.Color(204, 204, 204));
        jdashboard.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jdashboard.setText("DASHBOARD");
        jdashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdashboardActionPerformed(evt);
            }
        });
        getContentPane().add(jdashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 660, 180, 60));

        jproduk.setBackground(new java.awt.Color(204, 204, 204));
        jproduk.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jproduk.setText("PRODUK");
        jproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jprodukActionPerformed(evt);
            }
        });
        getContentPane().add(jproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 660, 180, 60));

        jtransaksi1.setBackground(new java.awt.Color(204, 204, 204));
        jtransaksi1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jtransaksi1.setText("TRANSAKSI");
        jtransaksi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtransaksi1ActionPerformed(evt);
            }
        });
        getContentPane().add(jtransaksi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 660, 170, 60));

        jlaporan.setBackground(new java.awt.Color(204, 204, 204));
        jlaporan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlaporan.setText("LAPORAN");
        jlaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlaporanActionPerformed(evt);
            }
        });
        getContentPane().add(jlaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 660, 170, 60));

        juser.setBackground(new java.awt.Color(204, 204, 204));
        juser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        juser.setText("USER");
        juser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                juserActionPerformed(evt);
            }
        });
        getContentPane().add(juser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 660, 170, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/laporan penjualan.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1274, 800));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void view_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_cariActionPerformed
        try {
            // Mengecek apakah bulan dan tahun sudah dipilih
            if (jMonthChooser1.getMonth() == -1 || jYearChooser1.getYear() == 0) {

                return;
            }

            // Mengecek apakah periode bebas atau periode bulanan dipilih
            if (time1.getDate() != null && time2.getDate() != null) {
                // Jika kedua tanggal (periode bebas) dipilih
                tampilkanDataBerdasarkanPeriodeBebas();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String startDate = sdf.format(time1.getDate());
                String endDate = sdf.format(time2.getDate());
                hitungTotal(startDate, endDate);
                resetForm();
            } else if (jMonthChooser1.getMonth() != -1) {
                // Jika bulan dipilih (periode bulanan)
                tampilkanDataBerdasarkanBulan();
                int year = jYearChooser1.getYear();
                int month = jMonthChooser1.getMonth();
                String startDate = getStartDateOfMonth(year, month);
                String endDate = getEndDateOfMonth(year, month);
                hitungTotal(startDate, endDate);
                resetForm();
            } else {
                // Jika tidak ada pilihan yang valid

            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage());
        }

    }//GEN-LAST:event_view_cariActionPerformed

    private void jkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkeluarActionPerformed
        new Masuk().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jkeluarActionPerformed

    private void jpenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpenggunaMouseClicked
        new Form_user().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jpenggunaMouseClicked

    private void jdashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdashboardActionPerformed
        new Dashboard_Owner().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jdashboardActionPerformed

    private void jprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jprodukActionPerformed

    }//GEN-LAST:event_jprodukActionPerformed

    private void jtransaksi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtransaksi1ActionPerformed
        new Transaksi().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jtransaksi1ActionPerformed

    private void jlaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlaporanActionPerformed
        new menu_laporan().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jlaporanActionPerformed

    private void juserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_juserActionPerformed
        new Data_user().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_juserActionPerformed

    private void jumlahhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahhargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahhargaActionPerformed

    private void jumlahprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahprodukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahprodukActionPerformed

    private void ExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportActionPerformed
        MessageFormat header = new MessageFormat("DATA LAPORAN PENJUALAN");
        MessageFormat footer = new MessageFormat("");

        try {
            PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
            set.add(OrientationRequested.PORTRAIT);
            tabelview.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, set, true);
            JOptionPane.showMessageDialog(null, "\n" + "Printed Succefully");

        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(null, "\n" + "Failed" + "\n" + e);

        }
    }//GEN-LAST:event_ExportActionPerformed

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
            java.util.logging.Logger.getLogger(laporan_penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laporan_penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laporan_penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laporan_penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new laporan_penjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Export;
    private javax.swing.JLabel jLabel1;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JButton jdashboard;
    private javax.swing.JButton jkeluar;
    private javax.swing.JButton jlaporan;
    private javax.swing.JLabel jpengguna;
    private javax.swing.JButton jproduk;
    private javax.swing.JButton jtransaksi1;
    private javax.swing.JTextField jumlahharga;
    private javax.swing.JTextField jumlahproduk;
    private javax.swing.JButton juser;
    private javax.swing.JTable tabelview;
    private com.toedter.calendar.JDateChooser time1;
    private com.toedter.calendar.JDateChooser time2;
    private javax.swing.JButton view_cari;
    // End of variables declaration//GEN-END:variables

}
