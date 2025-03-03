package kasir_umkm;

import java.awt.Font;
import java.text.SimpleDateFormat; // Untuk format tanggal
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.Calendar;
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

public class laporan_pembelian extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    database db = new database();

    public laporan_pembelian() {
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

    private void aturTabel() {
        model.addColumn("Id Pembelian");
        model.addColumn("Tanggal Beli");
        model.addColumn("Id Produk");
        model.addColumn("Nama Produk");
        model.addColumn("Harga Beli");
        model.addColumn("Jumlah Pcs");
        model.addColumn("Total Harga");
    }

    private void hitungTotal(String startDate, String endDate) {
        try {
            // Query untuk menghitung total jumlah pcs dan total harga
            ResultSet rs = db.ambildata(
                    "SELECT "
                    + "    SUM(detail_pembelian.jumlah_beli) AS total_jumlah_pcs, "
                    + "    SUM(pembelian.total_harga) AS total_grand_total "
                    + "FROM "
                    + "    pembelian "
                    + "JOIN "
                    + "    detail_pembelian ON pembelian.id_pembelian = detail_pembelian.id_pembelian "
                    + "WHERE "
                    + "    pembelian.tanggal_beli BETWEEN '" + startDate + "' AND '" + endDate + "'"
            );

            if (rs.next()) {
                // Menampilkan hasil query ke komponen teks
                jumlah_beli.setText(rs.getString("total_jumlah_pcs"));
                total_harga.setText(rs.getString("total_grand_total"));
            } else {
                // Jika tidak ada data
                total_harga.setText("0");
                total_harga.setText("0");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Cetak kesalahan di konsol untuk debugging
        }
    }

    private void tampilkanDataBerdasarkanPeriodeBebas() {
        try {
            model.setRowCount(0); // Reset tabel

            // Ambil tanggal mulai dan tanggal akhir dari JDateChooser
            java.util.Date startDate = time1.getDate(); // JDateChooser untuk tanggal mulai
            java.util.Date endDate = time2.getDate();   // JDateChooser untuk tanggal akhir

            if (startDate != null && endDate != null) {
                // Konversi ke format tanggal SQL (yyyy-MM-dd)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String start = sdf.format(startDate);
                String end = sdf.format(endDate);

                String query = "SELECT "
                        + "pembelian.id_pembelian, "
                        + "pembelian.tanggal_beli, "
                        + "detail_pembelian.id_produk, "
                        + "produk.nama_produk, "
                        + "detail_pembelian.harga_beli, "
                        + "detail_pembelian.jumlah_beli, "
                        + "pembelian.total_harga "
                        + "FROM pembelian "
                        + "JOIN detail_pembelian ON pembelian.id_pembelian = detail_pembelian.id_pembelian "
                        + "JOIN produk ON detail_pembelian.id_produk = produk.id_produk "
                        + "WHERE pembelian.tanggal_beli BETWEEN '" + start + "' AND '" + end + "' "
                        + "ORDER BY pembelian.id_pembelian ASC";

                ResultSet rs = db.ambildata(query);

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("id_pembelian"),
                        rs.getString("tanggal_beli"),
                        rs.getString("id_produk"),
                        rs.getString("nama_produk"),
                        rs.getString("harga_beli"),
                        rs.getString("jumlah_beli"),
                        rs.getString("total_harga")
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

            // Ambil bulan dan tahun dari JMonthChooser dan JYearChooser
            int selectedMonth = jMonthChooser1.getMonth(); // JMonthChooser untuk bulan
            int selectedYear = jYearChooser1.getYear();   // JYearChooser untuk tahun

            if (selectedMonth >= 0 && selectedYear > 0) {
                // Tentukan tanggal awal dan akhir bulan
                String startDate = selectedYear + "-" + (selectedMonth + 1) + "-01";
                String endDate = getEndDateOfMonth(selectedYear, selectedMonth);

                String query = "SELECT "
                        + "pembelian.id_pembelian, "
                        + "pembelian.tanggal_beli, "
                        + "detail_pembelian.id_produk, "
                        + "produk.nama_produk, "
                        + "detail_pembelian.harga_beli, "
                        + "detail_pembelian.jumlah_beli, "
                        + "pembelian.total_harga "
                        + "FROM pembelian "
                        + "JOIN detail_pembelian ON pembelian.id_pembelian = detail_pembelian.id_pembelian "
                        + "JOIN produk ON detail_pembelian.id_produk = produk.id_produk "
                        + "WHERE pembelian.tanggal_beli BETWEEN '" + startDate + "' AND '" + endDate + "' "
                        + "ORDER BY pembelian.id_pembelian ASC";

                ResultSet rs = db.ambildata(query);

                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getString("id_pembelian"),
                        rs.getString("tanggal_beli"),
                        rs.getString("id_produk"),
                        rs.getString("nama_produk"),
                        rs.getString("harga_beli"),
                        rs.getString("jumlah_beli"),
                        rs.getString("total_harga")
                    });
                }
                tabelview.setModel(model);
            }
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
        // Reset tahun (tidak ada tahun yang dipilih)
    }

    private String getStartDateOfMonth(int year, int month) {
        // Membuat instance kalender dan mengatur tahun, bulan, serta hari pertama bulan
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        // Format tanggal ke dalam bentuk "yyyy-MM-dd"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    private String getEndDateOfMonth(int year, int month) {
        // Membuat instance kalender dan mengatur tahun, bulan, serta hari terakhir bulan
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        // Format tanggal ke dalam bentuk "yyyy-MM-dd"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jkeluar = new javax.swing.JButton();
        export = new javax.swing.JButton();
        view = new javax.swing.JButton();
        total_harga = new javax.swing.JTextField();
        jumlah_beli = new javax.swing.JTextField();
        time2 = new com.toedter.calendar.JDateChooser();
        time1 = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelview = new javax.swing.JTable();
        jdashboard = new javax.swing.JButton();
        jproduk = new javax.swing.JButton();
        jtransaksi = new javax.swing.JButton();
        jlaporan = new javax.swing.JButton();
        juser = new javax.swing.JButton();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jpengguna = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/seblak.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jkeluar.setBackground(new java.awt.Color(255, 0, 0));
        jkeluar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jkeluar.setText("KELUAR");
        jkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkeluarActionPerformed(evt);
            }
        });
        getContentPane().add(jkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 10, 140, 50));

        export.setBackground(new java.awt.Color(153, 153, 153));
        export.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        export.setText("EXPORT");
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });
        getContentPane().add(export, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 160, 120, 50));

        view.setBackground(new java.awt.Color(153, 153, 153));
        view.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        view.setText("VIEW");
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });
        getContentPane().add(view, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 160, 120, 50));

        total_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_hargaActionPerformed(evt);
            }
        });
        getContentPane().add(total_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 250, 190, 50));

        jumlah_beli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlah_beliActionPerformed(evt);
            }
        });
        getContentPane().add(jumlah_beli, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, 190, 50));
        getContentPane().add(time2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 160, 200, 50));
        getContentPane().add(time1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 190, 50));

        tabelview.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id Pembelian", "Tanggal Beli", "Id Produk", "Nama Produk", "Harga Beli", "Jumlah Pcs", "Total Harga"
            }
        ));
        tabelview.setRowHeight(30);
        jScrollPane1.setViewportView(tabelview);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 1020, 280));

        jdashboard.setBackground(new java.awt.Color(204, 204, 204));
        jdashboard.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jdashboard.setText("DASHBOARD");
        jdashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdashboardActionPerformed(evt);
            }
        });
        getContentPane().add(jdashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 660, 180, 70));

        jproduk.setBackground(new java.awt.Color(204, 204, 204));
        jproduk.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jproduk.setText("PRODUK");
        jproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jprodukActionPerformed(evt);
            }
        });
        getContentPane().add(jproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 660, 180, 70));

        jtransaksi.setBackground(new java.awt.Color(204, 204, 204));
        jtransaksi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jtransaksi.setText("TRANSAKSI");
        jtransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(jtransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 660, 190, 70));

        jlaporan.setBackground(new java.awt.Color(204, 204, 204));
        jlaporan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlaporan.setText("LAPORAN");
        jlaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlaporanActionPerformed(evt);
            }
        });
        getContentPane().add(jlaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 660, 190, 70));

        juser.setBackground(new java.awt.Color(204, 204, 204));
        juser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        juser.setText("USER");
        juser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                juserActionPerformed(evt);
            }
        });
        getContentPane().add(juser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 660, 170, 70));
        getContentPane().add(jYearChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 100, 120, 50));
        getContentPane().add(jMonthChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 100, 120, 50));

        jpengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/popup/profile.png"))); // NOI18N
        jpengguna.setText("    ");
        jpengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpenggunaMouseClicked(evt);
            }
        });
        getContentPane().add(jpengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/laporan pembelian.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1282, 800));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkeluarActionPerformed
        new Masuk().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jkeluarActionPerformed

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

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
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
    }//GEN-LAST:event_viewActionPerformed

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed
        MessageFormat header = new MessageFormat("DATA LAPORAN PEMBELIAN");
        MessageFormat footer = new MessageFormat("");

        try {
            PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
            set.add(OrientationRequested.PORTRAIT);
            tabelview.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, set, true);
            JOptionPane.showMessageDialog(null, "\n" + "Printed Succefully");

        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(null, "\n" + "Failed" + "\n" + e);

        }
    }//GEN-LAST:event_exportActionPerformed

    private void jpenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpenggunaMouseClicked
        new Form_user().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jpenggunaMouseClicked

    private void total_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_hargaActionPerformed

    private void jumlah_beliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlah_beliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlah_beliActionPerformed

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
            java.util.logging.Logger.getLogger(laporan_pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(laporan_pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(laporan_pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(laporan_pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new laporan_pembelian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton export;
    private javax.swing.JLabel jLabel2;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JButton jdashboard;
    private javax.swing.JButton jkeluar;
    private javax.swing.JButton jlaporan;
    private javax.swing.JLabel jpengguna;
    private javax.swing.JButton jproduk;
    private javax.swing.JButton jtransaksi;
    private javax.swing.JTextField jumlah_beli;
    private javax.swing.JButton juser;
    private javax.swing.JTable tabelview;
    private com.toedter.calendar.JDateChooser time1;
    private com.toedter.calendar.JDateChooser time2;
    private javax.swing.JTextField total_harga;
    private javax.swing.JButton view;
    // End of variables declaration//GEN-END:variables
}
