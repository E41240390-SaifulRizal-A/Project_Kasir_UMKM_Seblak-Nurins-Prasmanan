package kasir_umkm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import popupptrans_pembelian.popuppembelian12;
import popupptrans_pembelian.popuppembelian10;
import popupptrans_pembelian.popuppembelian11;
import popupptrans_pembelian.popuppembelian9;
import popupptrans_pembelian.popuppembelian8;
import popupptrans_pembelian.popuppembelian7;
import popupptrans_pembelian.popuppembelian6;
import popupptrans_pembelian.popuppembelian5;
import popupptrans_pembelian.popuppembelian3;
import popupptrans_pembelian.popuppembelian4;
import popupptrans_pembelian.popuppembelian2;
import popupptrans_pembelian.popuppembelian1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import popupptrans_pembelian.popuppembelian13;
import popupptrans_pembelian.popuppembelian14;
import popupptrans_pembelian.popuppembelian15;
import popupptrans_pembelian.popuppembelian16;
import popupptrans_pembelian.popuppembelian17;
import popupptrans_pembelian.popuppembelian18;

public class Transaksi_pembelian extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    long sumTotal = 0;
    database db = new database();

    public Transaksi_pembelian() {
        initComponents();
        db.koneksi();
        tglskrg();
        buatnomor();
        aturTabel();
        jusername.setText("" + Masuk.currentUsername);
        nama_produk.requestFocus();
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
        header.setFont(new Font("Tahome", Font.BOLD, 20));
        tabelview.setFont(new Font("Tahome", Font.PLAIN, 18));
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Pusatkan horizontal
        headerRenderer.setVerticalAlignment(SwingConstants.CENTER);   // Pusatkan vertikal
        header.setDefaultRenderer(headerRenderer);
    }

    public void tglskrg() {
        Date skrg = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        jtanggal.setText(format.format(skrg));
    }

    public void buatnomor() {
        try {
            ResultSet rs = db.ambildata("SELECT id_pembelian AS auto FROM pembelian ORDER BY id_pembelian DESC");
            if (rs.next()) {
                int no_u = Integer.parseInt(rs.getString("auto")) + 1;
                jno_trans.setText(Integer.toString(no_u));
            } else {
                jno_trans.setText(Integer.toString(1));
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void aturTabel() {
        model.addColumn("Nama Produk");
        model.addColumn("Id Produk");
        model.addColumn("Harga Beli");
        model.addColumn("Jumlah Pcs");
        tabelview.setModel(model);
    }

    private void tampilkandatapembelian() {
        try (Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_kasir_umkm1")) {
            String query = "SELECT * FROM produk";
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            model.setRowCount(0);

            while (rs.next()) {
                Object[] rowData = {
                    rs.getString("nama_produk"),
                    rs.getString("id_produk"),
                    rs.getString("harga_jual"),
                    rs.getString("harga_beli"),
                    rs.getString("stok")
                };
                model.addRow(rowData);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan data: " + ex.getMessage());
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

        jproduk = new javax.swing.JButton();
        jdashboard = new javax.swing.JButton();
        jtransaksi = new javax.swing.JButton();
        jlaporan = new javax.swing.JButton();
        juser = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jno_trans = new javax.swing.JTextField();
        jtanggal = new javax.swing.JTextField();
        id_produk = new javax.swing.JTextField();
        nama_produk = new javax.swing.JComboBox<>();
        hargapack = new javax.swing.JTextField();
        jumlahbelipcs = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelview = new javax.swing.JTable();
        total_harga = new javax.swing.JTextField();
        tambah = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        jkeluar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jusername = new javax.swing.JTextField();
        label = new javax.swing.JLabel();
        hargabelipcs = new javax.swing.JTextField();
        jpengguna = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/seblak.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jproduk.setBackground(new java.awt.Color(204, 204, 204));
        jproduk.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jproduk.setText("PRODUK");
        jproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jprodukActionPerformed(evt);
            }
        });
        getContentPane().add(jproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 680, 180, 60));

        jdashboard.setBackground(new java.awt.Color(204, 204, 204));
        jdashboard.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jdashboard.setText("DASHBOARD");
        jdashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdashboardActionPerformed(evt);
            }
        });
        getContentPane().add(jdashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 680, 180, 60));

        jtransaksi.setBackground(new java.awt.Color(204, 204, 204));
        jtransaksi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jtransaksi.setText("TRANSAKSI");
        jtransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(jtransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 680, 180, 60));

        jlaporan.setBackground(new java.awt.Color(204, 204, 204));
        jlaporan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlaporan.setText("LAPORAN");
        jlaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlaporanActionPerformed(evt);
            }
        });
        getContentPane().add(jlaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 680, 170, 60));

        juser.setBackground(new java.awt.Color(204, 204, 204));
        juser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        juser.setText("USER");
        juser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                juserActionPerformed(evt);
            }
        });
        getContentPane().add(juser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 680, 160, 60));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TOTAL HARGA");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 70, -1, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ID PRODUK");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, -1, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("TRANSAKSI PEMBELIAN");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, -1, 50));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("TANGGAL BELI");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("HARGA PACK");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, -1, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("JUMLAH PCS");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, -1, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("NAMA PRODUK");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID PEMBELIAN");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        jno_trans.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jno_trans.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jno_trans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jno_transActionPerformed(evt);
            }
        });
        getContentPane().add(jno_trans, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 140, 40));

        jtanggal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jtanggal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtanggalActionPerformed(evt);
            }
        });
        getContentPane().add(jtanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 140, 40));

        id_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_produkActionPerformed(evt);
            }
        });
        getContentPane().add(id_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 150, 40));

        nama_produk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nama_produk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Barang", "Sosis Panjang", "Keju pedas", "Soki kotak", "Odeng", "Udang gulung", "Ekor udang", "Fish roll", "Chikuwa", "Soki stick 1", "Jamur enoki", "Crab stick", "Kembang cumi", "Dumpling ayam", "Dumpling keju", "Baso ikan", "Scallop", "Sosis jumbo", "Otak otak", "Siomay ikan", "Scallop pedes", "Bintang", "Telur", "Mie", "Intermie", "Mie sedap selection", "Sayur", "Cuanki", "Cikur", "Siomay kering", "Bakso daging", "Ceker" }));
        nama_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_produkActionPerformed(evt);
            }
        });
        getContentPane().add(nama_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 150, 40));

        hargapack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargapackActionPerformed(evt);
            }
        });
        getContentPane().add(hargapack, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 140, 40));

        jumlahbelipcs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahbelipcsActionPerformed(evt);
            }
        });
        getContentPane().add(jumlahbelipcs, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 140, 40));

        tabelview.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nama Produk", "Id Produk", "Harga Beli", "Jumlah Pcs"
            }
        ));
        tabelview.setEnabled(false);
        tabelview.setRowHeight(30);
        tabelview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelviewMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelview);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 1010, 350));

        total_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_hargaActionPerformed(evt);
            }
        });
        getContentPane().add(total_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 100, 220, 50));

        tambah.setBackground(new java.awt.Color(153, 153, 153));
        tambah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tambah.setText("TAMBAH");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });
        getContentPane().add(tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, 120, 50));

        edit.setBackground(new java.awt.Color(153, 153, 153));
        edit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        edit.setText("EDIT");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        getContentPane().add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 230, 120, 50));

        hapus.setBackground(new java.awt.Color(153, 153, 153));
        hapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        hapus.setText("HAPUS");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        getContentPane().add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 230, 120, 50));

        simpan.setBackground(new java.awt.Color(153, 153, 153));
        simpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        simpan.setText("SIMPAN");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });
        getContentPane().add(simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 230, 120, 50));

        jkeluar.setBackground(new java.awt.Color(255, 0, 0));
        jkeluar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jkeluar.setText("KELUAR");
        jkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkeluarActionPerformed(evt);
            }
        });
        getContentPane().add(jkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 150, 60));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("USERNAME");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, -1, 20));

        jusername.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jusername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jusernameActionPerformed(evt);
            }
        });
        getContentPane().add(jusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 120, 40));

        label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        label.setForeground(new java.awt.Color(255, 255, 255));
        label.setText("HARGA PCS");
        getContentPane().add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, -1, 40));

        hargabelipcs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargabelipcsActionPerformed(evt);
            }
        });
        getContentPane().add(hargabelipcs, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 180, 140, 40));

        jpengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/popup/profile.png"))); // NOI18N
        jpengguna.setText("    ");
        jpengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpenggunaMouseClicked(evt);
            }
        });
        getContentPane().add(jpengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/Halaman Produk Kosong.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1282, 800));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jprodukActionPerformed
        new Data_produk().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jprodukActionPerformed

    private void jdashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdashboardActionPerformed
        new Dashboard_Owner().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jdashboardActionPerformed

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

    private void jno_transActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jno_transActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jno_transActionPerformed

    private void jtanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtanggalActionPerformed

    private void id_produkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_produkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_produkActionPerformed

    private void nama_produkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_produkActionPerformed
        // TODO add your handling code here:
        try {
            // Retrieve data for a product with the given product ID
            ResultSet rs = db.ambildata("SELECT * FROM produk WHERE nama_produk = '" + nama_produk.getSelectedItem() + "'");

            if (rs.next()) {
                // Populate fields with data from the result set
                id_produk.setText(rs.getString("id_produk"));
                jumlahbelipcs.requestFocus(); // Moves focus to the specified component
            } else {
                // Display a message if the product code is not found
                popuppembelian1 pwSalah = new popuppembelian1();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);

            }
        } catch (Exception e) {
            // Display the exception message for debugging purposes
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

    }//GEN-LAST:event_nama_produkActionPerformed

    private void hargapackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargapackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargapackActionPerformed
    public class Config {

        private static Connection mysqlconnect;

        public static Connection ConfigDB() throws SQLException {
            try {
                String url = "jdbc:mysql://localhost/db_kasir_umkm1";
                String user = "root";
                String password = "";
                mysqlconnect = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi ke database berhasil.");
            } catch (SQLException e) {
                System.err.println("Koneksi gagal: " + e.getMessage());
                e.printStackTrace(); // Tampilkan detail error
            }
            return mysqlconnect;
        }
    }
    private void jumlahbelipcsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahbelipcsActionPerformed
        try {
            // Ambil input dari TextField
            int hargaPack = Integer.parseInt(hargapack.getText().trim());
            int jumlahPcs = Integer.parseInt(jumlahbelipcs.getText().trim());

            // Validasi jumlah PCS
            if (jumlahPcs > 0) {
                // Hitung harga beli per pcs
                int hargaBeliPcs = hargaPack / jumlahPcs;
                hargabelipcs.setText(String.valueOf(hargaBeliPcs));
            } else {
                popuppembelian2 pwSalah = new popuppembelian2();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
            }

            // Pindahkan fokus ke elemen berikutnya
            tambah.requestFocus();

        } catch (NumberFormatException ex) {
            popuppembelian3 pwSalah = new popuppembelian3();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);
        }


    }//GEN-LAST:event_jumlahbelipcsActionPerformed

    private void tabelviewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelviewMouseClicked
        // TODO add your handling code here:
        int row = tabelview.getSelectedRow(); // Ambil baris yang diklik
        if (row != -1) {
            nama_produk.setSelectedItem(model.getValueAt(row, 0).toString());
            id_produk.setText(model.getValueAt(row, 1).toString());
            hargabelipcs.setText(model.getValueAt(row, 2).toString());
            jumlahbelipcs.setText(model.getValueAt(row, 3).toString());

        }
    }//GEN-LAST:event_tabelviewMouseClicked

    private void total_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_hargaActionPerformed
        // TODO add your handling code here

    }//GEN-LAST:event_total_hargaActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        try {
            // Ambil nilai dari input
            int qty = Integer.parseInt(jumlahbelipcs.getText());
            int harga = Integer.parseInt(hargabelipcs.getText());

            if (qty <= 0 || harga <= 0) {
                popuppembelian4 pwSalah = new popuppembelian4();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
                return;
            }

            // Tambahkan data ke tabel
            model.addRow(new Object[]{
                nama_produk.getSelectedItem(),
                id_produk.getText(),
                harga,
                qty
            });
            tabelview.setModel(model);

            // Hitung ulang total harga berdasarkan semua data di tabel
            int rowCount = tabelview.getRowCount();
            int totalHargaBaru = 0;
            for (int i = 0; i < rowCount; i++) {
                int hargaBaris = Integer.parseInt(tabelview.getValueAt(i, 2).toString());
                int jumlahBaris = Integer.parseInt(tabelview.getValueAt(i, 3).toString());
                totalHargaBaru += (hargaBaris * jumlahBaris);
            }

            // Perbarui total harga
            total_harga.setText(String.valueOf(totalHargaBaru));

            // Reset input untuk item baru
            nama_produk.setSelectedItem("");
            id_produk.setText("");
            hargapack.setText("");
            jumlahbelipcs.setText("");
            hargabelipcs.setText("");
            nama_produk.requestFocus();

        } catch (NumberFormatException e) {
            popuppembelian5 pwSalah = new popuppembelian5();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_tambahActionPerformed


    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed

        try {
            int selectedRow = tabelview.getSelectedRow();
            if (selectedRow == -1) {
                popuppembelian6 pwSalah = new popuppembelian6();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
                return;
            }

            // Ambil nilai baru dari form
            String newNamaProduk = nama_produk.getSelectedItem().toString();
            int newHargaPack = Integer.parseInt(hargapack.getText().trim());
            int newJumlahPcs = Integer.parseInt(jumlahbelipcs.getText().trim());

            // Validasi input
            if (newJumlahPcs <= 0 || newHargaPack <= 0) {
                popuppembelian7 pwSalah = new popuppembelian7();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
                return;
            }

            // Hitung ulang harga beli per PCS
            int newHargaBeliPcs = newHargaPack / newJumlahPcs;
            hargabelipcs.setText(String.valueOf(newHargaBeliPcs));

            // Perbarui data di tabel
            model.setValueAt(newNamaProduk, selectedRow, 0); // Kolom Nama Produk
            model.setValueAt(newHargaBeliPcs, selectedRow, 2); // Kolom Harga per PCS
            model.setValueAt(newJumlahPcs, selectedRow, 3); // Kolom Jumlah PCS

            // Hitung ulang total harga untuk semua item di tabel
            int row = tabelview.getRowCount();
            sumTotal = 0;
            for (int i = 0; i < row; i++) {
                int harga = Integer.parseInt(tabelview.getValueAt(i, 2).toString());
                int jumlah = Integer.parseInt(tabelview.getValueAt(i, 3).toString());
                sumTotal += harga * jumlah;
            }
            total_harga.setText(String.valueOf(sumTotal));

            popuppembelian8 pwSalah = new popuppembelian8();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);
        } catch (NumberFormatException e) {
            popuppembelian9 pwSalah = new popuppembelian9();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_editActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
//        try {
        //            int n = JOptionPane.showConfirmDialog(this,
        //                    "Anda yakin ingin menghapus produk ini?",
        //                    "Konfirmasi Hapus Produk",
        //                    JOptionPane.YES_NO_OPTION,
        //                    JOptionPane.QUESTION_MESSAGE,
        //                    null);
        //
        //            if (n == JOptionPane.YES_OPTION) {
        //                int selectedRow = tabelview.getSelectedRow();
        //                if (selectedRow == -1) {
//        popuppembelian10 pwSalah = new popuppembelian10();
//        pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
//        pwSalah.setVisible(true);
//                    return;
//                }
//
//                System.out.println("Baris yang dipilih: " + selectedRow);
//
//                // Hapus baris dari model
//                model.removeRow(selectedRow);
//                System.out.println("Baris dihapus. Jumlah baris tersisa: " + tabelview.getRowCount());
//
//                // Hitung ulang total harga
//                int row = tabelview.getRowCount();
//                int tot = 0;
//                for (int i = 0; i < row; i++) {
//                    try {
//                        int hrg = Integer.parseInt(tabelview.getValueAt(i, 2).toString());
//                        int jml = Integer.parseInt(tabelview.getValueAt(i, 3).toString());
//                        System.out.println("Baris " + i + ": Harga = " + hrg + ", Jumlah = " + jml);
//                        tot += (hrg * jml);
//                    } catch (NumberFormatException ex) {
//                        System.out.println("Data tidak valid di baris " + i);
//                        popuppembelian15 pwSalah = new popuppembelian15();
//                        pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
//                        pwSalah.setVisible(true);
//                        return;
//                    }
//                }
//
//                // Perbarui tampilan total harga
//                total_harga.setText(String.valueOf(tot));
//                System.out.println("Total harga baru: " + tot);
//
//                // Reset form input
//                nama_produk.setSelectedItem("");
//                id_produk.setText("");
//                hargabelipcs.setText("");
//                hargapack.setText("");
//                jumlahbelipcs.setText("");
//                nama_produk.requestFocus();
//            }
//        } catch (Exception e) {
//            popuppembelian10 pwSalah = new popuppembelian10();
//            pwSalah.setAlwaysOnTop(true);
//            pwSalah.setVisible(true);
//        }

    main.setVisible(true);
    }//GEN-LAST:event_hapusActionPerformed
    private MainFrame main = new MainFrame();
    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        int row = tabelview.getRowCount();

        if (row == 0) {
            popuppembelian11 pwSalah = new popuppembelian11();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);
            return;
        }

        try {
            // Validasi nomor transaksi dan tanggal
            String nomorTransaksi = jno_trans.getText();
            String tanggalBeli = jtanggal.getText();

            if (nomorTransaksi == null || nomorTransaksi.isEmpty()) {
                popuppembelian13 pwSalah = new popuppembelian13();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
                return;
            }

            if (tanggalBeli == null || tanggalBeli.isEmpty()) {
                popuppembelian14 pwSalah = new popuppembelian14();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
                return;
            }

            // Mulai transaksi
            db.aksi("START TRANSACTION");
            // Ambil id_user berdasarkan Masuk.currentUsername
            String idUser = "";
            String userQuery = "SELECT id_user FROM users WHERE username = '" + Masuk.currentUsername + "'";
            ResultSet rsUser = db.ambildata(userQuery);
            if (rsUser.next()) {
                idUser = rsUser.getString("id_user");
            } else {
                throw new Exception("User tidak ditemukan.");
            }
            // Simpan data pembelian (header)
//            String queryPembelian = "INSERT INTO pembelian (id_user, tanggal_beli, total_harga) VALUES ('"
//                    + idUser + "', '" + jtanggal.getText() + "', 0)";
//
//// Jalankan query insert penjualan
//            db.aksi(queryPembelian);

            String sqlTransaksi = "INSERT INTO pembelian (id_pembelian, id_user, tanggal_beli, total_harga)VALUES (?, ?, ?, ?)";
            try (PreparedStatement stSimpan = Config.ConfigDB().prepareStatement(sqlTransaksi)) {
                stSimpan.setInt(1, 0);
                stSimpan.setString(2, idUser);
                stSimpan.setString(3, jtanggal.getText());
                stSimpan.setInt(4, 0);
                stSimpan.executeUpdate();
            }
            sumTotal = 0;
// Ambil id_penjualan yang baru saja diinsert
            String getIdPembelianQuery = "SELECT LAST_INSERT_ID()"; // Mengambil ID terakhir yang diinsert
            ResultSet rsPembelian = db.ambildata(getIdPembelianQuery);
            int idPembelian = 0;
            try {
                if (rsPembelian != null && rsPembelian.next()) {
                    idPembelian = rsPembelian.getInt(1);  // Ambil id_penjualan yang baru
                }
            } catch (SQLException e) {
                System.out.println("Error mendapatkan id_penjualan: " + e.getMessage());
            }

// Setelah mendapatkan idPenjualan, Anda bisa melanjutkan untuk memasukkan data ke tabel detail_penjualan
            int totalHarga = 0; // Untuk menghitung total harga pembelian

            for (int i = 0; i < row; i++) {
                String idProduk = tabelview.getValueAt(i, 1) != null ? tabelview.getValueAt(i, 1).toString() : "";
                int hargaBeli = 0;
                int jumlahBeli = 0;

                // Validasi harga beli
                if (tabelview.getValueAt(i, 2) != null) {
                    try {
                        hargaBeli = Integer.parseInt(tabelview.getValueAt(i, 2).toString());
                    } catch (NumberFormatException e) {
                        popuppembelian16 pwSalah = new popuppembelian16();
                        pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                        pwSalah.setVisible(true);
                        continue; // Lewati baris ini jika tidak valid
                    }
                }

                // Validasi jumlah beli
                if (tabelview.getValueAt(i, 3) != null) {
                    try {
                        jumlahBeli = Integer.parseInt(tabelview.getValueAt(i, 3).toString());
                    } catch (NumberFormatException e) {
                        popuppembelian17 pwSalah = new popuppembelian17();
                        pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                        pwSalah.setVisible(true);
                        continue; // Lewati baris ini jika tidak valid
                    }
                }

                if (idProduk.isEmpty() || hargaBeli <= 0 || jumlahBeli <= 0) {
                    popuppembelian18 pwSalah = new popuppembelian18();
                    pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                    pwSalah.setVisible(true);
                    continue; // Lewati baris ini jika data tidak valid
                }

                // Hitung total harga untuk baris ini
                int totalHargaBaris = hargaBeli * jumlahBeli;
                totalHarga += totalHargaBaris;

                // Simpan data ke detail_pembelian
                String queryDetailPembelian = "INSERT INTO detail_pembelian (id_pembelian, id_produk, jumlah_beli, harga_beli) VALUES ('"
                        + nomorTransaksi + "', '"
                        + idProduk + "', "
                        + jumlahBeli + ", "
                        + hargaBeli + ")";
                db.aksi(queryDetailPembelian);
            }

            // Update total harga pada tabel pembelian setelah semua detail_pembelian dimasukkan
            String updateTotalPembelian = "UPDATE pembelian SET total_harga = " + totalHarga + " WHERE id_pembelian = '" + nomorTransaksi + "'";
            db.aksi(updateTotalPembelian);

            // Commit transaksi jika semua berhasil
            db.aksi("COMMIT");

            // Bersihkan form dan reset data
            DefaultTableModel model = (DefaultTableModel) tabelview.getModel();
            model.setRowCount(0);
            total_harga.setText("");
            hargapack.setText("");
            buatnomor();
            db.transferStokDanHarga();

            popuppembelian12 pwSalah = new popuppembelian12();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);

        } catch (Exception e) {
            // Rollback transaksi jika terjadi kesalahan
            db.aksi("ROLLBACK");
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_simpanActionPerformed

    private void hargabelipcsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargabelipcsActionPerformed
        try {
            // Ambil input dari TextField
            double hargaPack = Double.parseDouble(hargapack.getText().trim());
            int jumlahPcs = Integer.parseInt(jumlahbelipcs.getText().trim());

            // Validasi jumlah PCS
            if (jumlahPcs > 0) {
                // Hitung harga beli per pcs
                double hargaBeliPcs = (hargaPack / jumlahPcs);
                hargabelipcs.setText(String.format("%.2f", hargaBeliPcs));
            } else {
                popuppembelian2 pwSalah = new popuppembelian2();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
            }
        } catch (NumberFormatException ex) {
            popuppembelian3 pwSalah = new popuppembelian3();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);

        }
    }//GEN-LAST:event_hargabelipcsActionPerformed
    public class MainFrame extends JFrame {

        public MainFrame() {
            // Set frame properties
            setTitle("SIAPKAN SNIPER");
            setSize(401, 183); // Tinggi frame sesuai dengan kebutuhan
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Panel utama
            JPanel panel = new JPanel();
            panel.setBackground(new Color(20, 20, 50)); // Warna biru tua
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Layout vertikal untuk mengatur label dan tombol ke bawah

            // Label dengan teks
            JLabel label = new JLabel("Apakah anda yakin ingin menghapus produk ini?", SwingConstants.CENTER);
            label.setForeground(Color.WHITE);
            label.setAlignmentX(Component.CENTER_ALIGNMENT); // Pusatkan label secara horizontal
            panel.add(Box.createVerticalStrut(35)); // Tambahkan jarak atas
            panel.add(label);

            // Panel untuk tombol
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(new Color(20, 20, 50)); // Warna biru tua
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Atur tombol berdampingan dengan jarak

            // Tombol Yes
            JButton yesButton1 = new JButton("Yes");
            yesButton1.setFont(new Font("Arial", Font.BOLD, 14)); // Atur font untuk teks
            yesButton1.setPreferredSize(new Dimension(80, 30)); // Ukuran tombol yang proporsional
            yesButton1.setBackground(new Color(153, 153, 153)); // Warna tombol biru
            yesButton1.setForeground(Color.BLACK); // Warna teks putih
            buttonPanel.add(yesButton1);
            yesButton1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = tabelview.getSelectedRow();
                    if (selectedRow == -1) {
                        popuppembelian10 pwSalah = new popuppembelian10();
                        pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                        pwSalah.setVisible(true);
                        return;
                    }
                    System.out.println("Baris yang dipilih: " + selectedRow);

                    // Hapus baris dari model
                    model.removeRow(selectedRow);

                    System.out.println("Baris dihapus. Jumlah baris tersisa: " + tabelview.getRowCount());

                    // Hitung ulang total harga
                    int row = tabelview.getRowCount();
                    int tot = 0;
                    for (int i = 0;
                            i < row; i++) {

                        try {
                            int hrg = Integer.parseInt(tabelview.getValueAt(i, 2).toString());
                            int jml = Integer.parseInt(tabelview.getValueAt(i, 3).toString());
                            System.out.println("Baris " + i + ": Harga = " + hrg + ", Jumlah = " + jml);
                            tot += (hrg * jml);
                        } catch (NumberFormatException ex) {
                            System.out.println("Data tidak valid di baris " + i);
                            popuppembelian15 pwSalah = new popuppembelian15();
                            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                            pwSalah.setVisible(true);
                            return;
                        }
                    }

                    // Perbarui tampilan total harga
                    total_harga.setText(String.valueOf(tot));
                    System.out.println("Total harga baru: " + tot);

                    // Reset form input
                    nama_produk.setSelectedItem("");
                    id_produk.setText("");
                    hargabelipcs.setText("");
                    hargapack.setText("");
                    jumlahbelipcs.setText("");
                    nama_produk.requestFocus();
                }
            });

            // Tombol No
            JButton noButton = new JButton("No");

            noButton.setFont(new Font("Arial", Font.BOLD, 14)); // Atur font untuk teks
            noButton.setPreferredSize(new Dimension(80, 30)); // Ukuran tombol yang proporsional
            noButton.setBackground(new Color(153, 153, 153)); // Warna tombol merah
            noButton.setForeground(Color.BLACK); // Warna teks putih
            buttonPanel.add(noButton);
            noButton.addActionListener(
                    new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    main.setVisible(false);
                }

            });

            System.out.println("No di klik");

            // Tambahkan panel tombol ke panel utama
            panel.add(Box.createVerticalStrut(20)); // Tambahkan jarak antara label dan tombol
            panel.add(buttonPanel);

            // Tambahkan panel utama ke frame
            add(panel);
        }
    }
    private void jkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkeluarActionPerformed
        new Masuk().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jkeluarActionPerformed

    private void jusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jusernameActionPerformed
        // Inisialisasi komponen GUI terlebih dahulu
        initComponents();
        Transaksi_pembelian tb = new Transaksi_pembelian();
        tb.jusername.setText("" + Masuk.currentUsername);
        tb.setVisible(true);
        this.dispose(); // Menutup form login
        // Setel text jusername ke username yang login (terdapat di Masuk.currentUsername)
        jusername.setText(Masuk.currentUsername);
    }//GEN-LAST:event_jusernameActionPerformed

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
                java.util.logging.Logger.getLogger(Transaksi_pembelian.class
                        .getName()).log(java.util.logging.Level.SEVERE, null, ex);

            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(Transaksi_pembelian.class
                        .getName()).log(java.util.logging.Level.SEVERE, null, ex);

            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(Transaksi_pembelian.class
                        .getName()).log(java.util.logging.Level.SEVERE, null, ex);

            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(Transaksi_pembelian.class
                        .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            //</editor-fold>
            //</editor-fold>
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Transaksi_pembelian().setVisible(true);
                }
            });
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton edit;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField hargabelipcs;
    private javax.swing.JTextField hargapack;
    private javax.swing.JTextField id_produk;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jdashboard;
    private javax.swing.JButton jkeluar;
    private javax.swing.JButton jlaporan;
    private javax.swing.JTextField jno_trans;
    private javax.swing.JLabel jpengguna;
    private javax.swing.JButton jproduk;
    private javax.swing.JTextField jtanggal;
    private javax.swing.JButton jtransaksi;
    private javax.swing.JTextField jumlahbelipcs;
    private javax.swing.JButton juser;
    private javax.swing.JTextField jusername;
    private javax.swing.JLabel label;
    private javax.swing.JComboBox<String> nama_produk;
    private javax.swing.JButton simpan;
    private javax.swing.JTable tabelview;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField total_harga;
    // End of variables declaration//GEN-END:variables
}
