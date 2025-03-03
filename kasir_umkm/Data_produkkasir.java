package kasir_umkm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import popuplaporan.popupposisi;
import popupproduk.popupproduk4;
import popupproduk.popupproduk5;
import popupproduk.popupproduk6;
import popupproduk.popupproduk7;
import popupproduk.popupproduk8;
import popupproduk.popuppwsalah16;
import popupproduk.popuppwsalah17;
import popupproduk.popupproduk;
import popupproduk.popupproduk1;
import popupproduk.popupproduk2;
import popupproduk.popupproduk9;
import popupuser.popupuser1;

/**
 *
 * @author Saiful
 */
public class Data_produkkasir extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    database db = new database();
    private static String loggedInUserRole;

    public Data_produkkasir() {
        initComponents();
        db.koneksi();
        viewdata();
        setLayoutForm();
    }

    private void setLayoutForm() {
        // Pusatkan teks dalam sel data
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Pusatkan horizontal
        cellRenderer.setVerticalAlignment(SwingConstants.CENTER);   // Pusatkan vertikal

        // Terapkan renderer ke setiap kolom
        for (int i = 0; i < jdataproduk.getColumnModel().getColumnCount(); i++) {
            TableColumn column = jdataproduk.getColumnModel().getColumn(i);
            column.setCellRenderer(cellRenderer);
        }

        // Pusatkan teks dalam header tabel
        JTableHeader header = jdataproduk.getTableHeader();
        header.setFont(new Font("Tahome", Font.BOLD, 20));
        jdataproduk.setFont(new Font("Tahome", Font.PLAIN, 18));
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Pusatkan horizontal
        headerRenderer.setVerticalAlignment(SwingConstants.CENTER);   // Pusatkan vertikal
        header.setDefaultRenderer(headerRenderer);
    }

    public void viewdata() {
        model.setRowCount(0); // Reset tabel untuk menghapus data lama

        // Tambah kolom tabel hanya jika belum ada
        if (model.getColumnCount() == 0) {
            model.addColumn("ID Produk");
            model.addColumn("Nama Produk");
            model.addColumn("Harga Jual");
            model.addColumn("Harga Beli");
            model.addColumn("Stok");
            model.addColumn("Tanggal Masuk");
        }

        try {
            // Ambil data produk dari database
            ResultSet rs = db.ambildata("SELECT * FROM produk");

            // Format untuk mata uang Rupiah
            NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

            // Loop untuk menambahkan data ke tabel
            while (rs.next()) {
                String idProduk = rs.getString("id_produk");
                String namaProduk = rs.getString("nama_produk");
                double hargaJual = rs.getDouble("harga_jual");
                double hargaBeli = rs.getDouble("harga_beli");
                int stok = rs.getInt("stok");
                String tanggalmasuk = rs.getString("waktu_pemasukan");

                // Format harga menjadi Rupiah
                String hargaJualRupiah = rupiahFormat.format(hargaJual);
                String hargaBeliRupiah = rupiahFormat.format(hargaBeli);

                // Tambahkan baris baru ke tabel
                model.addRow(new Object[]{idProduk, namaProduk, hargaJualRupiah, hargaBeliRupiah, stok, tanggalmasuk});
            }

            // Update model tabel
            jdataproduk.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Debug", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buatnomor() {
        try {
            // Pastikan ambildata mengembalikan ResultSet yang valid
            ResultSet rs = db.ambildata("Select id_produk as auto from produk ORDER by id_produk desc");

            if (rs.next()) {
                int no_u = Integer.parseInt(rs.getString("auto")) + 1;
                jid_produk.setText(Integer.toString(no_u));
            } else {
                int no_t = 1;
                jid_produk.setText(Integer.toString(no_t));
            }
            rs.close();
        } catch (Exception e) {
            // Menampilkan pesan error untuk debugging
            e.printStackTrace();
        }
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        buatnomor(); // Pastikan fungsi dipanggil saat form dibuka
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jsimpan = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jdataproduk = new javax.swing.JTable();
        jstok = new javax.swing.JTextField();
        jid_produk = new javax.swing.JTextField();
        jkembali = new javax.swing.JButton();
        jEdit1 = new javax.swing.JButton();
        jHapus = new javax.swing.JButton();
        jnamaproduk = new javax.swing.JTextField();
        Jcariproduk = new javax.swing.JTextField();
        jhargajual = new javax.swing.JTextField();
        jhargabeli = new javax.swing.JTextField();
        jdashboard = new javax.swing.JButton();
        jproduk = new javax.swing.JButton();
        jtransaksi = new javax.swing.JButton();
        jlaporan1 = new javax.swing.JButton();
        juser = new javax.swing.JButton();
        jcari1 = new javax.swing.JButton();
        jReset = new javax.swing.JButton();
        jkeluar = new javax.swing.JButton();
        Data_gudang = new javax.swing.JLabel();
        j1 = new javax.swing.JLabel();
        j2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        j = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jpengguna = new javax.swing.JLabel();
        Data_Gudang = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/seblak.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jsimpan.setBackground(new java.awt.Color(153, 153, 153));
        jsimpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jsimpan.setText("TAMBAH");
        jsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsimpanActionPerformed(evt);
            }
        });
        getContentPane().add(jsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, 100, 40));

        jdataproduk.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jdataproduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id produk", "nama produk", "harga jual", "harga beli", "stok", "tanggal masuk"
            }
        ));
        jdataproduk.setEnabled(false);
        jdataproduk.setRowHeight(30);
        jdataproduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jdataprodukMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jdataproduk);

        getContentPane().add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 1040, 330));

        jstok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jstokActionPerformed(evt);
            }
        });
        getContentPane().add(jstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, 170, 40));

        jid_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jid_produkActionPerformed(evt);
            }
        });
        getContentPane().add(jid_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 160, 40));

        jkembali.setBackground(new java.awt.Color(153, 153, 153));
        jkembali.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jkembali.setText("MENU");
        jkembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jkembaliMouseClicked(evt);
            }
        });
        jkembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkembaliActionPerformed(evt);
            }
        });
        getContentPane().add(jkembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 270, 100, 40));

        jEdit1.setBackground(new java.awt.Color(153, 153, 153));
        jEdit1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jEdit1.setText("EDIT");
        jEdit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jEdit1MouseClicked(evt);
            }
        });
        jEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEdit1ActionPerformed(evt);
            }
        });
        getContentPane().add(jEdit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 220, 100, 40));

        jHapus.setBackground(new java.awt.Color(153, 153, 153));
        jHapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jHapus.setText("HAPUS");
        jHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHapusActionPerformed(evt);
            }
        });
        getContentPane().add(jHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 220, 100, 40));

        jnamaproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jnamaprodukActionPerformed(evt);
            }
        });
        getContentPane().add(jnamaproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 160, 40));

        Jcariproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcariprodukActionPerformed(evt);
            }
        });
        getContentPane().add(Jcariproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 270, 220, 40));

        jhargajual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jhargajualActionPerformed(evt);
            }
        });
        getContentPane().add(jhargajual, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 160, 40));

        jhargabeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jhargabeliActionPerformed(evt);
            }
        });
        getContentPane().add(jhargabeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 170, 40));

        jdashboard.setBackground(new java.awt.Color(204, 204, 204));
        jdashboard.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jdashboard.setText("DASHBOARD");
        jdashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdashboardActionPerformed(evt);
            }
        });
        getContentPane().add(jdashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 680, 180, 60));

        jproduk.setBackground(new java.awt.Color(204, 204, 204));
        jproduk.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jproduk.setText("PRODUK");
        jproduk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jprodukActionPerformed(evt);
            }
        });
        getContentPane().add(jproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 680, 190, 60));

        jtransaksi.setBackground(new java.awt.Color(204, 204, 204));
        jtransaksi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jtransaksi.setText("TRANSAKSI");
        jtransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(jtransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 680, 180, 60));

        jlaporan1.setBackground(new java.awt.Color(204, 204, 204));
        jlaporan1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlaporan1.setText("LAPORAN");
        jlaporan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlaporan1ActionPerformed(evt);
            }
        });
        getContentPane().add(jlaporan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 680, 170, 60));

        juser.setBackground(new java.awt.Color(204, 204, 204));
        juser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        juser.setText("USER");
        juser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                juserActionPerformed(evt);
            }
        });
        getContentPane().add(juser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 680, 170, 60));

        jcari1.setBackground(new java.awt.Color(153, 153, 153));
        jcari1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jcari1.setText("CARI");
        jcari1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcari1MouseClicked(evt);
            }
        });
        jcari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcari1ActionPerformed(evt);
            }
        });
        getContentPane().add(jcari1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 100, 40));

        jReset.setBackground(new java.awt.Color(153, 153, 153));
        jReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jReset.setText("RESET");
        jReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResetActionPerformed(evt);
            }
        });
        getContentPane().add(jReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 220, 100, 40));

        jkeluar.setBackground(new java.awt.Color(255, 0, 0));
        jkeluar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jkeluar.setText("KELUAR");
        jkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkeluarActionPerformed(evt);
            }
        });
        getContentPane().add(jkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 150, 60));

        Data_gudang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/Data_gudang.png"))); // NOI18N
        getContentPane().add(Data_gudang, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 90, 60, 60));

        j1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        j1.setForeground(new java.awt.Color(255, 255, 255));
        j1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        j1.setText("STOK");
        getContentPane().add(j1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 100, 40));

        j2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        j2.setForeground(new java.awt.Color(255, 255, 255));
        j2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        j2.setText("HARGA BELI");
        getContentPane().add(j2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 100, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DATA PRODUK");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 260, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HARGA JUAL");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 110, 40));

        j.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        j.setForeground(new java.awt.Color(255, 255, 255));
        j.setText("CARI PRODUK");
        getContentPane().add(j, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 110, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("NAMA PRODUK");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 120, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ID PRODUK");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 110, 40));

        jpengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/popup/profile.png"))); // NOI18N
        jpengguna.setText("    ");
        jpengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpenggunaMouseClicked(evt);
            }
        });
        getContentPane().add(jpengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, -1));

        Data_Gudang.setBackground(new java.awt.Color(153, 153, 153));
        Data_Gudang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Data_Gudang.setText("DATA GUDANG");
        Data_Gudang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Data_GudangActionPerformed(evt);
            }
        });
        getContentPane().add(Data_Gudang, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 160, 140, 40));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/Halaman Produk Kosong.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1280, 800));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsimpanActionPerformed
        try {
            // Validasi input harga jual, harga beli, dan stok
            double hargaJual = Double.parseDouble(jhargajual.getText());
            double hargaBeli = Double.parseDouble(jhargabeli.getText());
            int stokBaru = Integer.parseInt(jstok.getText());

            if (hargaJual <= hargaBeli) {
                popupproduk pwSalah = new popupproduk();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
                return;
            }

            // Simpan data ke tabel produk
            String sqlProduk = "INSERT INTO produk (nama_produk, harga_jual, harga_beli, stok) VALUES ('"
                    + jnamaproduk.getText() + "', '"
                    + hargaJual + "', '"
                    + hargaBeli + "', '"
                    + stokBaru + "')";
            System.out.println("Executing query: " + sqlProduk); // Debugging log
            db.aksi(sqlProduk); // Eksekusi query

            // Tampilkan pesan sukses
            popupproduk1 pwSalah = new popupproduk1();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);

            // Reset input form setelah penyimpanan berhasil
            jid_produk.setText("");
            jnamaproduk.setText("");
            jhargajual.setText("");
            jhargabeli.setText("");
            jstok.setText("");

            // Refresh tampilan data
            viewdata();
        } catch (NumberFormatException e) {
            popupproduk2 pwSalah = new popupproduk2();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jsimpanActionPerformed

    private void jdataprodukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdataprodukMouseClicked
        // TODO add your handling code here:
        int row = jdataproduk.getSelectedRow(); // Ambil baris yang diklik
        if (row != -1) {
            jid_produk.setText(model.getValueAt(row, 0).toString());
            jnamaproduk.setText(model.getValueAt(row, 1).toString());
            jhargajual.setText(model.getValueAt(row, 2).toString());
            jhargabeli.setText(model.getValueAt(row, 3).toString());
            jstok.setText(model.getValueAt(row, 4).toString());

        }
    }//GEN-LAST:event_jdataprodukMouseClicked

    private void jstokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jstokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jstokActionPerformed

    private void jid_produkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jid_produkActionPerformed

    }//GEN-LAST:event_jid_produkActionPerformed

    private void jkembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jkembaliMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jkembaliMouseClicked

    private void jkembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkembaliActionPerformed
        viewdata();
        Jcariproduk.setText("");
    }//GEN-LAST:event_jkembaliActionPerformed

    private void jEdit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jEdit1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jEdit1MouseClicked

    private void jEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEdit1ActionPerformed
        try {
            if (jid_produk.getText().isEmpty() || jnamaproduk.getText().isEmpty() || jhargajual.getText().isEmpty() || jhargabeli.getText().isEmpty() || jstok.getText().isEmpty()) {
                popupproduk7 pwSalah = new popupproduk7();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
                return; // Hentikan proses jika ada data yang kosong
            }

            // Ambil nilai harga jual, harga beli, dan stok dari input
            double hargaJual = Double.parseDouble(jhargajual.getText());
            double hargaBeli = Double.parseDouble(jhargabeli.getText());
            int stokBaru = Integer.parseInt(jstok.getText());

            // Validasi: harga jual harus lebih besar dari harga beli
            if (hargaJual <= hargaBeli) {
                popupproduk pwSalah = new popupproduk();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
                return; // Hentikan proses penyimpanan jika validasi gagal
            }

            // Cek apakah produk dengan id_produk sudah ada
            ResultSet rs = db.ambildata("SELECT * FROM produk WHERE id_produk='" + jid_produk.getText() + "'");
            if (rs.next()) {
                // Jika data produk sudah ada, lakukan pembaruan
                db.aksi("UPDATE produk SET "
                        + "nama_produk='" + jnamaproduk.getText() + "', "
                        + "harga_jual='" + hargaJual + "', "
                        + "harga_beli='" + hargaBeli + "', "
                        + "stok='" + stokBaru + "' "
                        + "WHERE id_produk='" + jid_produk.getText() + "'");

                // Update detail produk
                db.aksi("UPDATE detail_produk SET "
                        + "harga_beli='" + hargaBeli + "', "
                        + "stok='" + stokBaru + "', "
                        + "tanggal_masuk=CURDATE() "
                        + "WHERE id_produk='" + jid_produk.getText() + "'");

                popupproduk8 pwSalah = new popupproduk8();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);

                // Reset input form
                jid_produk.setText("");
                jnamaproduk.setText("");
                jhargajual.setText("");
                jhargabeli.setText("");
                jstok.setText("");

                // Refresh tampilan data
                viewdata();
            } else {
                // Jika produk belum terdaftar, beri notifikasi
                popuppwsalah16 pwSalah = new popuppwsalah16();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
            }
        } catch (NumberFormatException e) {
            popupproduk2 pwSalah = new popupproduk2();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jEdit1ActionPerformed
    private MainFrame4 main4 = new MainFrame4();
    private void jHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHapusActionPerformed
// Ambil baris yang dipilih
//    int row = jdataproduk.getSelectedRow();
//    if (row != -1) {
//        String id_produk = model.getValueAt(row, 0).toString(); // Ambil id_produk dari tabel
//
//        // Menampilkan konfirmasi penghapusan
//        int confirm = JOptionPane.showConfirmDialog(null,
//                "Apakah Anda yakin ingin menghapus produk dengan ID: " + id_produk + "?",
//                "Konfirmasi Hapus",
//                JOptionPane.YES_NO_OPTION);
//
//        if (confirm == JOptionPane.YES_OPTION) {
//            try {
//                // Cek apakah produk memiliki data di detail_penjualan atau detail_pembelian
//                ResultSet rsPenjualan = db.ambildata("SELECT COUNT(*) AS jumlah FROM detail_penjualan WHERE id_produk='" + id_produk + "'");
//                ResultSet rsPembelian = db.ambildata("SELECT COUNT(*) AS jumlah FROM detail_pembelian WHERE id_produk='" + id_produk + "'");
//
//                boolean adaTransaksi = false;
//
//                if (rsPenjualan.next() && rsPenjualan.getInt("jumlah") > 0) {
//                    adaTransaksi = true;
//                }
//
//                if (rsPembelian.next() && rsPembelian.getInt("jumlah") > 0) {
//                    adaTransaksi = true;
//                }
//
//                if (adaTransaksi) {
//                    // Jika produk memiliki data transaksi, tampilkan pesan error
//                    JOptionPane.showMessageDialog(null, "Produk ini tidak dapat dihapus karena sudah memiliki data transaksi penjualan atau pembelian.");
//                } else {
//                    // Jika tidak ada data transaksi, lanjutkan dengan penghapusan
//                    db.aksi("DELETE FROM detail_produk WHERE id_produk='" + id_produk + "'");
//                    db.aksi("DELETE FROM produk WHERE id_produk='" + id_produk + "'");
//
//                    // Tampilkan pesan konfirmasi
//                    JOptionPane.showMessageDialog(null, "Produk berhasil dihapus.");
//
//                    // Refresh tampilan tabel dan reset input form
//                    viewdata();
//                    jid_produk.setText("");
//                    jnamaproduk.setText("");
//                    jhargajual.setText("");
//                    jhargabeli.setText("");
//                    jstok.setText("");
//                }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Error dalam menghapus produk: " + e.getMessage());
//            }
//        }
//    } else {
//        Jika tidak ada baris yang dipilih
//        JOptionPane.showMessageDialog(null, "Pilih produk yang ingin dihapus terlebih dahulu.");
//    }
        main4.setVisible(true);
    }//GEN-LAST:event_jHapusActionPerformed

    private void jnamaprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jnamaprodukActionPerformed
        // TODO add your handling code here:
        try {
            ResultSet rs = db.ambildata("SELECT * FROM produk WHERE nama_produk='" + jnamaproduk.getText() + "'");
            if (rs.next()) {
                jid_produk.setText(rs.getString("id_produk"));
                jhargajual.setText(rs.getString("harga_jual"));
                jhargabeli.setText(rs.getString("harga_beli"));
                jstok.setText(rs.getString("stok"));

                jsimpan.setEnabled(false);

            } else {
                popupuser1 pwSalah = new popupuser1();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
                jstok.requestFocus();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jnamaprodukActionPerformed
    public class MainFrame4 extends JFrame {

        public MainFrame4() {
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
            JLabel label = new JLabel("Anda yakin ingin menghapus produk ini?", SwingConstants.CENTER);
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
                    main4.setVisible(false);
                    int row = jdataproduk.getSelectedRow();
                    if (row != -1) {
                        try {
                            String id_produk = model.getValueAt(row, 0).toString(); // Ambil id_produk dari tabel

                            ResultSet rsPenjualan = db.ambildata("SELECT COUNT(*) AS jumlah FROM detail_penjualan WHERE id_produk='" + id_produk + "'");
                            ResultSet rsPembelian = db.ambildata("SELECT COUNT(*) AS jumlah FROM detail_pembelian WHERE id_produk='" + id_produk + "'");

                            boolean adaTransaksi = false;

                            if (rsPenjualan.next() && rsPenjualan.getInt("jumlah") > 0) {
                                adaTransaksi = true;
                            }

                            if (rsPembelian.next() && rsPembelian.getInt("jumlah") > 0) {
                                adaTransaksi = true;
                            }

                            if (adaTransaksi) {
                                // Jika produk memiliki data transaksi, tampilkan pesan error
                                popupproduk9 pwSalah = new popupproduk9();
                                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                                pwSalah.setVisible(true);
                            } else {
                                // Jika tidak ada data transaksi, lanjutkan dengan penghapusan
                                db.aksi("DELETE FROM detail_produk WHERE id_produk='" + id_produk + "'");
                                db.aksi("DELETE FROM produk WHERE id_produk='" + id_produk + "'");

                                // Tampilkan pesan konfirmasi
                                popupproduk4 pwSalah = new popupproduk4();
                                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                                pwSalah.setVisible(true);

                                // Refresh tampilan tabel dan reset input form
                                viewdata();
                                jid_produk.setText("");
                                jnamaproduk.setText("");
                                jhargajual.setText("");
                                jhargabeli.setText("");
                                jstok.setText("");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Data_produkkasir.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            // Tombol No
            JButton noButton = new JButton("No");
            noButton.setFont(new Font("Arial", Font.BOLD, 14)); // Atur font untuk teks
            noButton.setPreferredSize(new Dimension(80, 30)); // Ukuran tombol yang proporsional
            noButton.setBackground(new Color(153, 153, 153)); // Warna tombol merah
            noButton.setForeground(Color.BLACK); // Warna teks putih
            buttonPanel.add(noButton);
            noButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    main4.setVisible(false);
                }

            });
            // Tambahkan panel tombol ke panel utama

            panel.add(Box.createVerticalStrut(20)); // Tambahkan jarak antara label dan tombol
            panel.add(buttonPanel);

            // Tambahkan panel utama ke frame
            add(panel);
        }
    }
    private void JcariprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcariprodukActionPerformed
        // TODO add your handling code here:\
    }//GEN-LAST:event_JcariprodukActionPerformed

    private void jhargajualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jhargajualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jhargajualActionPerformed

    private void jhargabeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jhargabeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jhargabeliActionPerformed

    private void jdashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdashboardActionPerformed
        new Dashboard_Kasir().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jdashboardActionPerformed

    private void jprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jprodukActionPerformed

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

    private void jcari1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcari1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jcari1MouseClicked

    private void jcari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcari1ActionPerformed
        // Kode untuk pencarian produk
        String keyword = Jcariproduk.getText(); // Mengambil teks dari input pencarian
        model.setRowCount(0); // Menghapus semua baris pada tabel sebelum menambahkan data

        if (keyword.isEmpty()) {
            // Jika input pencarian kosong, tampilkan semua data
            viewdata();
            popuppwsalah17 pwSalah = new popuppwsalah17();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);
            return;
        }

        try {
            // Query untuk mencari data berdasarkan keyword
            ResultSet rs = db.ambildata("SELECT * FROM produk WHERE nama_produk LIKE '%" + keyword + "%'");
            boolean dataFound = false;

            // Formatter untuk mata uang Rupiah
            NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

            while (rs.next()) {
                // Format harga_jual dan harga_beli ke format Rupiah
                String hargaJual = rupiahFormat.format(rs.getDouble("harga_jual"));
                String hargaBeli = rupiahFormat.format(rs.getDouble("harga_beli"));

                // Tambahkan data ke tabel
                model.addRow(new Object[]{
                    rs.getString("id_produk"),
                    rs.getString("nama_produk"),
                    hargaJual,
                    hargaBeli,
                    rs.getString("stok")
                });
                dataFound = true;
            }

            // Jika data ditemukan, tampilkan di tabel
            if (dataFound) {
                jdataproduk.setModel(model);
            } else {
                // Jika data tidak ditemukan, tampilkan semua data dan pesan
                viewdata();
                popuppwsalah16 pwSalah = new popuppwsalah16();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error dalam pencarian produk: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jcari1ActionPerformed

    private void jResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResetActionPerformed
        // TODO add your handling code here:
        // Reset semua field input
        jid_produk.setText("");
        jnamaproduk.setText("");
        jhargajual.setText("");
        jhargabeli.setText("");
        jstok.setText("");
    }//GEN-LAST:event_jResetActionPerformed

    private void jkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkeluarActionPerformed
        new Masuk().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jkeluarActionPerformed

    private void jpenggunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpenggunaMouseClicked
        new Form_user_kasir().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jpenggunaMouseClicked

    private void Data_GudangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Data_GudangActionPerformed
        new Data_Gudang_kasir().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_Data_GudangActionPerformed

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
            java.util.logging.Logger.getLogger(Data_produkkasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Data_produkkasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Data_produkkasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Data_produkkasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new Data_produkkasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Data_Gudang;
    private javax.swing.JLabel Data_gudang;
    private javax.swing.JTextField Jcariproduk;
    private javax.swing.JLabel j;
    private javax.swing.JLabel j1;
    private javax.swing.JLabel j2;
    private javax.swing.JButton jEdit1;
    private javax.swing.JButton jHapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton jReset;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JButton jcari1;
    private javax.swing.JButton jdashboard;
    private javax.swing.JTable jdataproduk;
    private javax.swing.JTextField jhargabeli;
    private javax.swing.JTextField jhargajual;
    private javax.swing.JTextField jid_produk;
    private javax.swing.JButton jkeluar;
    private javax.swing.JButton jkembali;
    private javax.swing.JButton jlaporan1;
    private javax.swing.JTextField jnamaproduk;
    private javax.swing.JLabel jpengguna;
    private javax.swing.JButton jproduk;
    private javax.swing.JButton jsimpan;
    private javax.swing.JTextField jstok;
    private javax.swing.JButton jtransaksi;
    private javax.swing.JButton juser;
    // End of variables declaration//GEN-END:variables

    private void testData(JTable jdatauser) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
