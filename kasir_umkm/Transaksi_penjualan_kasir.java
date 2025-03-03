/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kasir_umkm;

/**
 *
 * @author Saiful
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.sql.PreparedStatement;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import popuplaporan.popupposisi;
import popupptrans_pembelian.popuppembelian6;
import popupptrans_pembelian.popuppembelian8;
import popupptrans_pembelian.popuppembelian11;
import popupptrans_pembelian.popuppembelian12;
import popuptrans_penjualan.popuppenjualan;
import popuptrans_penjualan.popuppenjualan1;
import popuptrans_penjualan.popuppenjualan2;
import popuptrans_penjualan.popuppenjualan3;
import popuptrans_penjualan.popuppenjualan4;
import popuptrans_penjualan.popuppenjualan6;
import popuptrans_penjualan.popuppenjualan9;
import popuptrans_penjualan.popuppenjualan13;
import popuptrans_penjualan.popuppenjualan14;
import popuptrans_penjualan.popuppenjualan15;
import popuptrans_penjualan.popuppenjualan16;
import popuptrans_penjualan.popuppenjualan17;
import popuptrans_penjualan.popuppenjualan18;

public class Transaksi_penjualan_kasir extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    long sumTotall = 0;

    database db = new database();

    /**
     * Creates new form Transaksi_penjualan
     */
    public Transaksi_penjualan_kasir() {
        initComponents();
        db.koneksi();
        tglskrg();
        buatnomor();
        aturTabel();
        jusername.setText("" + Masuk.currentUsername);
        nama_produk.requestFocus();
        setLayoutForm();
        jusername.setEditable(false);
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
        String tanggal = format.format(skrg);
        jtanggal.setText(format.format(skrg));
        jtanggal.setEditable(false); // Membuat jtanggal tidak bisa diedit
    }

    public void buatnomor() {
        try {
            ResultSet rs = db.ambildata("Select id_penjualan as auto from penjualan ORDER by id_penjualan desc");

            if (rs.next()) {
                int no_u = Integer.parseInt(rs.getString("auto")) + 1;
                jno_trans.setText(Integer.toString(no_u));
            } else {
                int no_t = 1;
                jno_trans.setText(Integer.toString(no_t));
            }
            jno_trans.setEditable(false); // Membuat jno_trans tidak bisa diedit
            rs.close();
        } catch (Exception e) {
        }
    }

    public void aturTabel() {
        model.addColumn("Nama Produk");
        model.addColumn("Id  Produk");
        model.addColumn("Harga  Jual");
        model.addColumn("Jumlah Pcs");
        tabelview.setModel(model);
    }

    private void tampilkanDataPenjualan() {
        try (Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_kasir_umkm1", "root", "")) {
            String query = "SELECT * FROM produk";
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            // Clear the table before adding new data
            model.setRowCount(0);

            while (rs.next()) {
                Object[] rowData = {
                    rs.getString("nama_produk"),
                    rs.getString("id_produk"),
                    rs.getString("harga_jual"),
                    rs.getString("jumlah_pcs"), // Assuming this column exists in your 'produk' table
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jkeluar = new javax.swing.JButton();
        jno_trans = new javax.swing.JTextField();
        jtanggal = new javax.swing.JTextField();
        id_produk = new javax.swing.JTextField();
        nama_produk = new javax.swing.JComboBox<>();
        stok = new javax.swing.JTextField();
        harga = new javax.swing.JTextField();
        jumlah_pcs = new javax.swing.JTextField();
        jumlah_harga = new javax.swing.JTextField();
        jenis_produk = new javax.swing.JComboBox<>();
        varian_level = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelview = new javax.swing.JTable();
        total_harga = new javax.swing.JTextField();
        dibayar = new javax.swing.JTextField();
        kembalian = new javax.swing.JTextField();
        tambah = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        Cetak_struk = new javax.swing.JButton();
        juser = new javax.swing.JButton();
        jlaporan = new javax.swing.JButton();
        jtransaksi = new javax.swing.JButton();
        jproduk = new javax.swing.JButton();
        jdashboard = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jusername = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jpengguna = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/seblak.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane1.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, -60, 230, 40));

        jkeluar.setBackground(new java.awt.Color(255, 0, 0));
        jkeluar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jkeluar.setText("KELUAR");
        jkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkeluarActionPerformed(evt);
            }
        });
        getContentPane().add(jkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 150, 60));

        jno_trans.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jno_trans.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jno_trans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jno_transActionPerformed(evt);
            }
        });
        getContentPane().add(jno_trans, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 150, 40));

        jtanggal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jtanggal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtanggalActionPerformed(evt);
            }
        });
        getContentPane().add(jtanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 150, 40));

        id_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_produkActionPerformed(evt);
            }
        });
        getContentPane().add(id_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 170, 40));

        nama_produk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nama_produk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Barang", "Sosis panjang", "Keju pedas", "Soki kotak", "Odeng", "Udang gulung", "Ekor udang", "Fish roll", "Chikuwa", "Soki stick1", "Jamur enoki", "Crab stick", "Kembang cumi", "Dumpling ayam", "Dumpling keju", "Baso ikan", "Scallop", "Sosis jumbo", "Otak-otak", "Siomay ikan", "Scallop pedes", "Bintang", "Telur", "Mie", "Intermie", "Mie sedap selection", "Cuanki", "Cikur", "Siomay kering", "Bakso daging", "Ceker" }));
        nama_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_produkActionPerformed(evt);
            }
        });
        getContentPane().add(nama_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 170, 40));

        stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stokActionPerformed(evt);
            }
        });
        getContentPane().add(stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, 160, 40));
        getContentPane().add(harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, 160, 40));

        jumlah_pcs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlah_pcsActionPerformed(evt);
            }
        });
        getContentPane().add(jumlah_pcs, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 160, 160, 40));

        jumlah_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlah_hargaActionPerformed(evt);
            }
        });
        getContentPane().add(jumlah_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 220, 160, 40));

        jenis_produk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jenis_produk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "nyemek", "kuah" }));
        jenis_produk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenis_produkActionPerformed(evt);
            }
        });
        getContentPane().add(jenis_produk, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 130, 40));

        varian_level.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        varian_level.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "level 1", "level 2", "level 3", "level 4", "level 5" }));
        varian_level.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                varian_levelActionPerformed(evt);
            }
        });
        varian_level.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                varian_levelKeyPressed(evt);
            }
        });
        getContentPane().add(varian_level, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 130, 40));

        tabelview.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nama Produk", "Id Produk", "Harga Jual", "Jumlah Pcs"
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

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 930, 270));

        total_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_hargaActionPerformed(evt);
            }
        });
        getContentPane().add(total_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 340, 130, 40));

        dibayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dibayarActionPerformed(evt);
            }
        });
        getContentPane().add(dibayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 340, 130, 40));
        getContentPane().add(kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 340, 130, 40));

        tambah.setBackground(new java.awt.Color(153, 153, 153));
        tambah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tambah.setText("TAMBAH");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });
        getContentPane().add(tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, 100, 40));

        edit.setBackground(new java.awt.Color(153, 153, 153));
        edit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        edit.setText("EDIT");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        getContentPane().add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 280, 100, 40));

        hapus.setBackground(new java.awt.Color(153, 153, 153));
        hapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        hapus.setText("HAPUS");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        getContentPane().add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 280, 100, 40));

        simpan.setBackground(new java.awt.Color(153, 153, 153));
        simpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        simpan.setText("SIMPAN");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });
        getContentPane().add(simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 470, 100, 40));

        Cetak_struk.setBackground(new java.awt.Color(0, 51, 102));
        Cetak_struk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Cetak_struk.setForeground(new java.awt.Color(255, 255, 255));
        Cetak_struk.setText("CETAK STRUK");
        Cetak_struk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cetak_strukActionPerformed(evt);
            }
        });
        getContentPane().add(Cetak_struk, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 410, 140, 50));

        juser.setBackground(new java.awt.Color(204, 204, 204));
        juser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        juser.setText("USER");
        juser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                juserActionPerformed(evt);
            }
        });
        getContentPane().add(juser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 680, 160, 60));

        jlaporan.setBackground(new java.awt.Color(204, 204, 204));
        jlaporan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlaporan.setText("LAPORAN");
        jlaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlaporanActionPerformed(evt);
            }
        });
        getContentPane().add(jlaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 680, 170, 60));

        jtransaksi.setBackground(new java.awt.Color(204, 204, 204));
        jtransaksi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jtransaksi.setText("TRANSAKSI");
        jtransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtransaksiActionPerformed(evt);
            }
        });
        getContentPane().add(jtransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 680, 180, 60));

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

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("USERNAME");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, -1, 40));

        jusername.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jusername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jusernameActionPerformed(evt);
            }
        });
        getContentPane().add(jusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 130, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("DIBAYAR");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 340, 90, 40));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("KEMBALIAN");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 340, 110, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TOTAL HARGA");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 340, 130, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("NAMA PRODUK");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 110, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ID PRODUK");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 100, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("STOK");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, 70, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("TANGGAL JUAL");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 150, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("HARGA");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 70, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("JUMLAH PCS");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, 90, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("JUMLAH HARGA");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 220, -1, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("JENIS PRODUK");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, -1, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("VARIAN LEVEL");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, -1, 40));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("TRANSAKSI PENJUALAN");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, -20, 280, 70));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("NO TRANSAKSI");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 150, 40));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, -1));

        jpengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/popup/profile.png"))); // NOI18N
        jpengguna.setText("    ");
        jpengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpenggunaMouseClicked(evt);
            }
        });
        getContentPane().add(jpengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/Halaman Produk Kosong.png"))); // NOI18N
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(1281, 800));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkeluarActionPerformed
        new Masuk().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jkeluarActionPerformed

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
                harga.setText(rs.getString("harga_jual"));
                stok.setText(rs.getString("stok"));
                jumlah_pcs.requestFocus(); // Moves focus to the specified component
            } else {
                // Display a message if the product code is not found
                popuppenjualan pwSalah = new popuppenjualan();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);

            }
        } catch (Exception e) {
            // Display the exception message for debugging purposes
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }


    }//GEN-LAST:event_nama_produkActionPerformed

    private void jumlah_pcsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlah_pcsActionPerformed
        // TODO add your handling code here:
        int a1, a2, tot, stok_brg;

        try {
            stok_brg = Integer.parseInt(stok.getText().trim());  // Mengambil stok barang yang tersedia
            a1 = Integer.parseInt(harga.getText().trim());  // Mengambil harga
            a2 = Integer.parseInt(jumlah_pcs.getText().trim());  // Mengambil jumlah pcs yang dipilih

            // Mengecek apakah jumlah pcs lebih besar dari stok yang tersedia
            if (a2 > stok_brg) {
                // Jika jumlah pcs lebih besar dari stok, tampilkan peringatan
                popuppenjualan1 pwSalah = new popuppenjualan1();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
            } else if (stok_brg > 0) {
                // Jika jumlah pcs valid dan stok tersedia
                tot = a1 * a2;  // Menghitung total harga
                jumlah_harga.setText(String.valueOf(tot));  // Menampilkan total harga
                tambah.requestFocus();  // Memfokuskan ke tombol/tindakan selanjutnya
            } else {
                // Jika stok barang sudah habis
                popuppenjualan2 pwSalah = new popuppenjualan2();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
            }
        } catch (NumberFormatException e) {
            // Menangani kesalahan input jika angka tidak valid
            popuppenjualan3 pwSalah = new popuppenjualan3();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);
        }


    }//GEN-LAST:event_jumlah_pcsActionPerformed

    private void jumlah_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlah_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlah_hargaActionPerformed

    private void tabelviewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelviewMouseClicked
        // TODO add your handling code here:
        int row = tabelview.getSelectedRow(); // Ambil baris yang diklik
        if (row != -1) {
            nama_produk.setSelectedItem(model.getValueAt(row, 0).toString());
            id_produk.setText(model.getValueAt(row, 1).toString());
            harga.setText(model.getValueAt(row, 2).toString());
            jumlah_pcs.setText(model.getValueAt(row, 3).toString());

        }
    }//GEN-LAST:event_tabelviewMouseClicked

    private void dibayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dibayarActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        int a1, a2, kemb;

        try {
            a1 = Integer.parseInt(total_harga.getText());  // Total harga yang harus dibayar
            a2 = Integer.parseInt(dibayar.getText());  // Uang yang dibayar oleh pelanggan

            // Mengecek apakah uang yang dibayar kurang dari total harga
            if (a2 < a1) {
                // Jika uang yang dibayar kurang dari harga, tampilkan peringatan
                popuppenjualan4 pwSalah = new popuppenjualan4();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
            } else {
                // Jika uang yang dibayar cukup, hitung kembalian
                kemb = a2 - a1;
                kembalian.setText(String.valueOf(kemb));  // Menampilkan kembalian
                simpan.requestFocus();  // Fokus ke tombol simpan (atau aksi selanjutnya)
            }
        } catch (NumberFormatException e) {
            // Menangani kesalahan input jika angka tidak valid
            popuppenjualan3 pwSalah = new popuppenjualan3();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);
        }

    }//GEN-LAST:event_dibayarActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here: 
//        try {
//            int n = JOptionPane.showConfirmDialog(this, "Tambah item lagi?", "SIAPKAN SNIPER", JOptionPane.YES_NO_OPTION,
//                    JOptionPane.QUESTION_MESSAGE, null);
//            if (n == JOptionPane.YES_OPTION) {
//                long qty, hrg, totalsementara;
//                model.addRow(new Object[]{nama_produk.getSelectedItem(), id_produk.getText(), harga.getText(), jumlah_pcs.getText()});
//                tabelview.setModel(model);
//
//                // Hitung ulang total harga berdasarkan data tabel
//                long totalBaru = 0;
//                for (int i = 0; i < tabelview.getRowCount(); i++) {
//                    long hargaRow = Long.parseLong(tabelview.getValueAt(i, 2).toString());
//                    long qtyRow = Long.parseLong(tabelview.getValueAt(i, 3).toString());
//                    totalBaru += (hargaRow * qtyRow);
//                }
//                sumTotall = totalBaru; // Perbarui sumTotall dengan total baru
//
//                total_harga.setText(String.valueOf(sumTotall));
//
//                // Reset form
//                nama_produk.requestFocus();
//                nama_produk.setSelectedItem("");
//                id_produk.setText("");
//                harga.setText("");
//                stok.setText("");
//                jumlah_pcs.setText("");
//                jumlah_harga.setText("");
//            } else if (n == JOptionPane.NO_OPTION) {
//                long qty, hrg, totalsementara;
//                model.addRow(new Object[]{nama_produk.getSelectedItem(), id_produk.getText(), harga.getText(), jumlah_pcs.getText()});
//                tabelview.setModel(model);
//
//                // Hitung ulang total harga berdasarkan data tabel
//                long totalBaru = 0;
//                for (int i = 0; i < tabelview.getRowCount(); i++) {
//                    long hargaRow = Long.parseLong(tabelview.getValueAt(i, 2).toString());
//                    long qtyRow = Long.parseLong(tabelview.getValueAt(i, 3).toString());
//                    totalBaru += (hargaRow * qtyRow);
//                }
//                sumTotall = totalBaru; // Perbarui sumTotall dengan total baru
//
//                total_harga.setText(String.valueOf(sumTotall));
//
//                // Reset form
//                nama_produk.requestFocus();
//                nama_produk.setSelectedItem("");
//                id_produk.setText("");
//                harga.setText("");
//                stok.setText("");
//                harga.setText("");
//                jumlah_pcs.setText("");
//                jumlah_harga.setText("");
//                dibayar.requestFocus();
//            } else {
//                dibayar.requestFocus();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        MainFrame main = new MainFrame();
        main.setVisible(true);
    }//GEN-LAST:event_tambahActionPerformed

    private MainFrame1 main1 = new MainFrame1();
    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
//        try {
//            // Konfirmasi pengguna
//            int n = JOptionPane.showConfirmDialog(this,
//                    "Anda yakin ingin mengedit produk ini?",
//                    "Konfirmasi Edit",
//                    JOptionPane.YES_NO_OPTION,
//                    JOptionPane.QUESTION_MESSAGE);
//
//            if (n == JOptionPane.YES_OPTION) {
//                // Pastikan ada baris yang dipilih sebelum melakukan edit
//                int selectedRow = tabelview.getSelectedRow();
//                if (selectedRow == -1) {
//                    popuppembelian6 pwSalah = new popuppembelian6();
//                    pwSalah.setAlwaysOnTop(true);
//                    pwSalah.setVisible(true);
//                    return;
//                }
//
//                // Ambil nilai input baru dari form
//                String newNamaProduk = nama_produk.getSelectedItem().toString();
//                String newIdProduk = id_produk.getText();
//                int newHarga = Integer.parseInt(harga.getText());
//                int newJumlah = Integer.parseInt(jumlah_pcs.getText());
//
//                // Validasi input
//                if (newNamaProduk.isEmpty() || newIdProduk.isEmpty()) {
//                    popuppenjualan6 pwSalah = new popuppenjualan6();
//                    pwSalah.setAlwaysOnTop(true);
//                    pwSalah.setVisible(true);
//                    return;
//                }
//
//                // Perbarui model tabel
//                model.setValueAt(newNamaProduk, selectedRow, 0);
//                model.setValueAt(newIdProduk, selectedRow, 1);
//                model.setValueAt(newHarga, selectedRow, 2);
//                model.setValueAt(newJumlah, selectedRow, 3);
//
//                // Hitung ulang total harga
//                int row = tabelview.getRowCount();
//                int totalHarga = 0;
//                for (int i = 0; i < row; i++) {
//                    try {
//                        int hargaJual = Integer.parseInt(tabelview.getValueAt(i, 2).toString());
//                        int jumlah = Integer.parseInt(tabelview.getValueAt(i, 3).toString());
//                        totalHarga += (hargaJual * jumlah);
//                    } catch (NumberFormatException e) {
//                        popuppenjualan17 pwSalah = new popuppenjualan17();
//                        pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
//                        pwSalah.setVisible(true);
//                        return;
//                    }
//                }
//
//                // Update tampilan total harga
//                total_harga.setText(String.valueOf(totalHarga));
//
//                // Notifikasi keberhasilan
//                popuppembelian8 pwSalah = new popuppembelian8();
//                pwSalah.setAlwaysOnTop(true);
//                pwSalah.setVisible(true);
//
//                // Reset hanya form input, tidak total_harga
//                nama_produk.setSelectedItem("");
//                id_produk.setText("");
//                harga.setText("");
//                jumlah_pcs.setText("");
//                stok.setText("");
//                jumlah_harga.setText("");
//                nama_produk.requestFocus();
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(),
//                    "Error", JOptionPane.ERROR_MESSAGE);
//        }
        //MainFrame1 main = new MainFrame1();
        main1.setVisible(true);
    }//GEN-LAST:event_editActionPerformed

    private void jno_transActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jno_transActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jno_transActionPerformed
    private MainFrame2 main2 = new MainFrame2();

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
//        // TODO add your handling code here:
//        try {
//            int n = JOptionPane.showConfirmDialog(this, "Anda Yakin menghapus produk ini?", "SIAPKAN SNIPER",
//                    JOptionPane.YES_NO_OPTION,
//                    JOptionPane.QUESTION_MESSAGE, null);
//
//            if (n == JOptionPane.YES_OPTION) {
//                // Ambil ID produk yang akan dihapus
//                int selectedRow = tabelview.getSelectedRow();
//                if (selectedRow != -1) {
//                    // Dapatkan harga dan jumlah produk yang akan dihapus
//                    int harga = Integer.parseInt(tabelview.getValueAt(selectedRow, 2).toString());
//                    int jumlah = Integer.parseInt(tabelview.getValueAt(selectedRow, 3).toString());
//                    // Hitung harga subtotal untuk produk yang akan dihapus
//                    int subtotal = harga * jumlah;
//
//                    // Hapus baris yang dipilih dari tabel
//                    model.removeRow(selectedRow);
//
//                    // Hitung total harga setelah penghapusan
//                    int row = tabelview.getRowCount();
//                    int total = 0;
//                    for (int i = 0; i < row; i++) {
//                        int hargaBaru = Integer.parseInt(tabelview.getValueAt(i, 2).toString());
//                        int jumlahBaru = Integer.parseInt(tabelview.getValueAt(i, 3).toString());
//                        total += (hargaBaru * jumlahBaru); // Menghitung total harga
//                    }
//
//                    // Update total harga pada label
//                    total_harga.setText(String.valueOf(total));
//                }
//
//                // Reset form input setelah menghapus
//                nama_produk.setSelectedItem("");
//                id_produk.setText("");
//                harga.setText("");
//                jumlah_pcs.setText("");
//                stok.setText("");
//                jumlah_harga.setText("");
//                nama_produk.requestFocus();
//            } else if (n == JOptionPane.NO_OPTION) {
//                // Jika tidak dihapus, tidak ada aksi tambahan
//            } else {
//                return; // Jika user menutup dialog
//            }
//        } catch (Exception e) {
//            popuppenjualan9 pwSalah = new popuppenjualan9();
//            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
//            pwSalah.setVisible(true);
//        }

        main2.setVisible(true);
    }//GEN-LAST:event_hapusActionPerformed

    private void total_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_hargaActionPerformed
        // TODO add your handling code here

    }//GEN-LAST:event_total_hargaActionPerformed

    private void jenis_produkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenis_produkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jenis_produkActionPerformed
    private MainFrame4 main4 = new MainFrame4();
    private void varian_levelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_varian_levelActionPerformed
//        try {
        //            String level = (String) varian_level.getSelectedItem();
        //            int hargaTambahan = 0;
        //
        //            int n = JOptionPane.showConfirmDialog(this, "Apakah anda yakin dengan level " + level + "?",
        //                    "Konfirmasi Level", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
        //
        //            if (n == JOptionPane.YES_OPTION) {
        //                switch (level) {
        //                    case "level 1":
        //                        hargaTambahan = 0;
        //                        break;
        //                    case "level 2":
        //                        hargaTambahan = 1000;
        //                        break;
        //                    case "level 3":
        //                        hargaTambahan = 2000;
        //                        break;
        //                    case "level 4":
        //                        hargaTambahan = 3000;
        //                        break;
        //                    case "level 5":
        //                        hargaTambahan = 4000;
        //                        break;
        //                    default:
        //                        return;
        //                }
        //
        //                // Ambil nilai total_harga yang sudah ada
        //                long currentTotal = Long.parseLong(total_harga.getText());
        //
        //                // Tambahkan harga level ke total yang sudah ada
        //                long newTotal = currentTotal + hargaTambahan;
        //
        //                // Update total_harga dengan nilai baru
        //                total_harga.setText(String.valueOf(newTotal));
        //
        //                // Tampilkan informasi perubahan harga
        //                popuppenjualan16 pwSalah = new popuppenjualan16();
        //                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
        //                pwSalah.setVisible(true);
        //
        //            } else if (n == JOptionPane.NO_OPTION) {
        //                // Kembali ke pemilihan level
        //                varian_level.requestFocus();
        //            }
        //
        //        } catch (Exception e) {
        //            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
        //        }
        main4.setVisible(true);
    }//GEN-LAST:event_varian_levelActionPerformed

    private void Cetak_strukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cetak_strukActionPerformed
        try {
            // Validasi apakah ada transaksi
            if (jno_trans.getText().isEmpty() || tabelview.getRowCount() == 0) {
                return;
            }

            // Validasi input pembayaran
            String dibayarValue = dibayar.getText();
            if (dibayarValue.isEmpty() || !dibayarValue.matches("\\d+")) {
                popuppenjualan13 pwSalah = new popuppenjualan13();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
                return;
            }
            int bayar = Integer.parseInt(dibayarValue); // Nilai pembayaran

            // Ambil posisi pengguna yang login
            String posisiPengguna = getPosisiPengguna(); // Metode untuk mendapatkan posisi pengguna yang login

            // Inisialisasi StringBuilder untuk mencetak struk
            StringBuilder struk = new StringBuilder();

            // Header Struk
            struk.append("====================================\n");
            struk.append("       Seblak Nurin's Prasmanan       \n");
            struk.append("====================================\n");
            struk.append("Tanggal Jual : ").append(jtanggal.getText()).append("\n");
            struk.append("No Transaksi : ").append(jno_trans.getText()).append("\n");
            struk.append("Posisi       : ").append(posisiPengguna).append("\n");
            struk.append("------------------------------------\n");
            struk.append(String.format("%-4s | %-13s | %-8s | %-4s | %-10s\n", "ID", "Nama Produk", "Harga", "Jml", "Subtotal"));
            struk.append("------------------------------------\n");

            // Rincian Produk
            int totalHarga = 0;
            for (int i = 0; i < tabelview.getRowCount(); i++) {
                String idProduk = tabelview.getValueAt(i, 1).toString(); // ID produk
                String namaProduk = tabelview.getValueAt(i, 0).toString(); // Nama produk
                int harga = Integer.parseInt(tabelview.getValueAt(i, 2).toString()); // Harga
                int jumlah = Integer.parseInt(tabelview.getValueAt(i, 3).toString()); // Jumlah pcs
                int subtotal = harga * jumlah; // Subtotal
                totalHarga += subtotal;

                struk.append(String.format("%-4s | %-13s | Rp %,6d | %-4d | Rp %,9d\n",
                        idProduk, namaProduk, harga, jumlah, subtotal));
            }

            // Varian Level dan Harga Tambahan
            String level = (String) varian_level.getSelectedItem();
            int hargaTambahan = 0;

            switch (level) {
                case "level 1":
                    hargaTambahan = 0;
                    break;
                case "level 2":
                    hargaTambahan = 1000;
                    break;
                case "level 3":
                    hargaTambahan = 2000;
                    break;
                case "level 4":
                    hargaTambahan = 3000;
                    break;
                case "level 5":
                    hargaTambahan = 4000;
                    break;
                default:
                    popuppenjualan18 pwSalah = new popuppenjualan18();
                    pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                    pwSalah.setVisible(true);
                    return;
            }

            if (hargaTambahan > 0) {
                struk.append("------------------------------------\n");
                struk.append("Level        : ").append(level).append("\n");
                struk.append("Harga Level  : ").append(String.format("Rp %,d\n", hargaTambahan));
                totalHarga += hargaTambahan; // Tambahkan harga tambahan level ke total
            }

            // Validasi Pembayaran
            if (bayar < totalHarga) {
                popuppenjualan14 pwSalah = new popuppenjualan14();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
                return;
            }

            int kembalian = bayar - totalHarga;

            // Total Harga, Bayar, dan Kembali
            struk.append("------------------------------------\n");
            struk.append("Total Harga   : ").append(String.format("Rp %,d\n", totalHarga));
            struk.append("Bayar         : ").append(String.format("Rp %,d\n", bayar));
            struk.append("Kembali       : ").append(String.format("Rp %,d\n", kembalian));
            struk.append("====================================\n");
            struk.append("    Terima kasih atas pesanan Anda!    \n");
            struk.append("====================================\n");

            // Tampilkan struk pada dialog
            JTextArea textArea = new JTextArea(struk.toString());
            textArea.setEditable(false);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Struk Transaksi", JOptionPane.INFORMATION_MESSAGE);

            // Konfirmasi cetak
            int printConfirm = JOptionPane.showConfirmDialog(null, "Cetak struk ini?", "Konfirmasi Cetak", JOptionPane.YES_NO_OPTION);
            if (printConfirm == JOptionPane.YES_OPTION) {
                textArea.print(); // Cetak Struk
                popuppenjualan15 pwSalah = new popuppenjualan15();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mencetak struk: " + e.getMessage());

        }
    }

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

// Metode untuk mendapatkan posisi pengguna
    private String getPosisiPengguna() {
        // Ganti implementasi ini sesuai dengan sistem login Anda
        // Misalnya, mengambil dari session pengguna atau variabel global
        return "Owner"; // Contoh posisi pengguna default
    }//GEN-LAST:event_Cetak_strukActionPerformed

    private void tampilstok() {
        try {
            ResultSet rs = db.ambildata("SELECT * FROM produk");
            // Update tampilan stok di form (misalnya di JTextField atau JTable)
            if (rs.next()) {
                stok.setText(rs.getString("stok")); // Jika menggunakan TextField
                // Atau update tabel jika menggunakan JTable
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error menampilkan stok: " + e.getMessage());
        }
    }
    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        int row = tabelview.getRowCount();

        if (row == 0) {
            popuppembelian11 pwSalah = new popuppembelian11();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);
            return;
        }

        try {
            db.aksi("START TRANSACTION"); // Mulai transaksi database

            // Ambil id_user berdasarkan Masuk.currentUsername
            String idUser = "";
            String userQuery = "SELECT id_user FROM users WHERE username = '" + Masuk.currentUsername + "'";
            ResultSet rsUser = db.ambildata(userQuery);
            System.out.println(Masuk.currentUsername);
            if (rsUser.next()) {
                idUser = rsUser.getString("id_user"); // Ambil id_user (VARCHAR)
                System.out.println("ID User yang ditemukan: " + idUser); // Debugging log
            } else {
                throw new Exception("User tidak ditemukan.");
            }

            //Query untuk insert ke tabel penjualan
//             int buatid = 0;
//            String queryPenjualan = "INSERT INTO penjualan (id_penjualan, id_user, tanggal_jual, grand_total) VALUES ('"+ buatid + idUser + "', '" + jtanggal.getText() + "', 0)";
//            //String queryPenjualan = "INSERT INTO penjualan (id_penjualan, id_user, tanggal_jual, grand_total) VALUES (?,'"+jtanggal.getText()+"', 0)";
//            System.out.println("Query Penjualan: " + queryPenjualan);
//            db.aksi(queryPenjualan);// Debugging log
            //db.aksi(queryPenjualan);
            String sqlTransaksi = "INSERT INTO penjualan (id_penjualan, id_user, tanggal_jual, grand_total) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stSimpan = Config.ConfigDB().prepareStatement(sqlTransaksi)) {
                stSimpan.setInt(1, 0);
                stSimpan.setString(2, idUser);
                stSimpan.setString(3, jtanggal.getText());
                stSimpan.setInt(4, 0);
                stSimpan.executeUpdate();
            }
            int idPenjualan;
            // Ambil id_penjualan yang baru saja dimasukkan
            String queryCheckPenjualan = "SELECT id_penjualan from penjualan order by id_penjualan DESC";
            ResultSet rsPenjualan = db.ambildata(queryCheckPenjualan);
            if (rsPenjualan.next()) {
                idPenjualan = rsPenjualan.getInt(1);

                if (idPenjualan >= 0) {
                    idPenjualan++;
                }
            } else {
                idPenjualan = 1;
            }

            // Ambil ID penjualan terakhir
            System.out.println("ID Penjualan berhasil disimpan: " + idPenjualan); // Debugging log

            // Proses detail_penjualan
            double sumTotal = 0;
            for (int i = 0; i < row; i++) {
                // Ambil data dari tabel
                String idProduk = tabelview.getValueAt(i, 1).toString();
                String namaProduk = tabelview.getValueAt(i, 0).toString();
                int jumlahPcs = Integer.parseInt(tabelview.getValueAt(i, 3).toString());
                double hargaJual = Double.parseDouble(tabelview.getValueAt(i, 2).toString());

                // Hitung subtotal
                double subtotal = jumlahPcs * hargaJual;

                // Susun query untuk detail_penjualan
                String queryDetail = "INSERT INTO detail_penjualan (id_penjualan, id_produk, nama_produk, varian_level, jumlah_pcs, subtotal) VALUES ("
                        + idPenjualan + ", '"
                        + idProduk + "', '"
                        + namaProduk + "', '"
                        + varian_level.getSelectedItem().toString() + "', "
                        + jumlahPcs + ", "
                        + subtotal + ")";
                db.aksi(queryDetail);

                sumTotal += subtotal; // Tambahkan subtotal ke total keseluruhan
            }

            // Tambahkan harga tambahan berdasarkan varian_level
            String level = (String) varian_level.getSelectedItem();
            int hargaTambahan = switch (level) {
                case "level 1" ->
                    0;
                case "level 2" ->
                    1000;
                case "level 3" ->
                    2000;
                case "level 4" ->
                    3000;
                case "level 5" ->
                    4000;
                default ->
                    0;
            };

            sumTotal += hargaTambahan;

            // Update grand total
            String queryGrandTotalUpdate = "UPDATE penjualan SET grand_total = " + sumTotal + " WHERE id_penjualan = " + idPenjualan;
            db.aksi(queryGrandTotalUpdate);

            db.aksi("COMMIT"); // Commit transaksi
            popuppembelian12 pwSalah = new popuppembelian12();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);

            // Reset form dan tabel
            DefaultTableModel model = (DefaultTableModel) tabelview.getModel();
            model.setRowCount(0);
            int i = 0;
            sumTotall = 0;
            total_harga.setText("0");
            dibayar.setText("");
            kembalian.setText("");
            buatnomor();
            db.transferStokDanHarga();

        } catch (Exception e) {
            try {
                db.aksi("ROLLBACK"); // Rollback jika terjadi kesalahan
            } catch (Exception rollbackEx) {
                System.err.println("Gagal melakukan rollback: " + rollbackEx.getMessage());
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_simpanActionPerformed

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
            JLabel label = new JLabel("Apakah anda ingin menambah item?", SwingConstants.CENTER);
            label.setForeground(Color.WHITE);
            label.setAlignmentX(Component.CENTER_ALIGNMENT); // Pusatkan label secara horizontal
            panel.add(Box.createVerticalStrut(35)); // Tambahkan jarak atas
            panel.add(label);

            // Panel untuk tombol
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(new Color(20, 20, 50)); // Warna biru tua
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Atur tombol berdampingan dengan jarak

            // Tombol Yes
            JButton yesButton = new JButton("Yes");
            yesButton.setFont(new Font("Arial", Font.BOLD, 14)); // Atur font untuk teks
            yesButton.setPreferredSize(new Dimension(80, 30)); // Ukuran tombol yang proporsional
            yesButton.setBackground(new Color(153, 153, 153)); // Warna tombol biru
            yesButton.setForeground(Color.BLACK); // Warna teks putih
            buttonPanel.add(yesButton);
            yesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Yes di  klik");
                    long qty, hrg, totalsementara;
                    model.addRow(new Object[]{nama_produk.getSelectedItem(), id_produk.getText(), harga.getText(), jumlah_pcs.getText()});
                    tabelview.setModel(model);

                    // Hitung ulang total harga berdasarkan data tabel
                    long totalBaru = 0;
                    for (int i = 0; i < tabelview.getRowCount(); i++) {
                        long hargaRow = Long.parseLong(tabelview.getValueAt(i, 2).toString());
                        long qtyRow = Long.parseLong(tabelview.getValueAt(i, 3).toString());
                        totalBaru += (hargaRow * qtyRow);
                    }
                    sumTotall = totalBaru; // Perbarui sumTotall dengan total baru

                    total_harga.setText(String.valueOf(sumTotall));

                    // Reset form
                    nama_produk.requestFocus();
                    nama_produk.setSelectedItem("");
                    id_produk.setText("");
                    harga.setText("");
                    stok.setText("");
                    jumlah_pcs.setText("");
                    jumlah_harga.setText("");
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
                    System.out.println("No di klik");
                    long qty, hrg, totalsementara;
                    model.addRow(new Object[]{nama_produk.getSelectedItem(), id_produk.getText(), harga.getText(), jumlah_pcs.getText()});
                    tabelview.setModel(model);

                    // Hitung ulang total harga berdasarkan data tabel
                    long totalBaru = 0;
                    for (int i = 0; i < tabelview.getRowCount(); i++) {
                        long hargaRow = Long.parseLong(tabelview.getValueAt(i, 2).toString());
                        long qtyRow = Long.parseLong(tabelview.getValueAt(i, 3).toString());
                        totalBaru += (hargaRow * qtyRow);
                    }
                    sumTotall = totalBaru; // Perbarui sumTotall dengan total baru

                    total_harga.setText(String.valueOf(sumTotall));

                    // Reset form
                    nama_produk.requestFocus();
                    nama_produk.setSelectedItem("");
                    id_produk.setText("");
                    harga.setText("");
                    stok.setText("");
                    harga.setText("");
                    jumlah_pcs.setText("");
                    jumlah_harga.setText("");
                    dibayar.requestFocus();
                }
            });

            // Tambahkan panel tombol ke panel utama
            panel.add(Box.createVerticalStrut(20)); // Tambahkan jarak antara label dan tombol
            panel.add(buttonPanel);

            // Tambahkan panel utama ke frame
            add(panel);
        }
    }

    public class MainFrame1 extends JFrame {

        public MainFrame1() {
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
            JLabel label = new JLabel("Apakah anda yakin ingin mengedit produk ini?", SwingConstants.CENTER);
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
                        popuppembelian6 pwSalah = new popuppembelian6();
                        pwSalah.setAlwaysOnTop(true);
                        pwSalah.setVisible(true);
                        return;
                    }

                    // Ambil nilai input baru dari form
                    String newNamaProduk = nama_produk.getSelectedItem().toString();
                    String newIdProduk = id_produk.getText();
                    int newHarga = Integer.parseInt(harga.getText());
                    int newJumlah = Integer.parseInt(jumlah_pcs.getText());

                    // Validasi input
                    if (newNamaProduk.isEmpty() || newIdProduk.isEmpty()) {
                        popuppenjualan6 pwSalah = new popuppenjualan6();
                        pwSalah.setAlwaysOnTop(true);
                        pwSalah.setVisible(true);
                        return;
                    }

                    // Perbarui model tabel
                    model.setValueAt(newNamaProduk, selectedRow, 0);
                    model.setValueAt(newIdProduk, selectedRow, 1);
                    model.setValueAt(newHarga, selectedRow, 2);
                    model.setValueAt(newJumlah, selectedRow, 3);

                    // Hitung ulang total harga
                    int row = tabelview.getRowCount();
                    int totalHarga = 0;
                    for (int i = 0; i < row; i++) {
                        int hargaJual = Integer.parseInt(tabelview.getValueAt(i, 2).toString());
                        int jumlah = Integer.parseInt(tabelview.getValueAt(i, 3).toString());
                        totalHarga += (hargaJual * jumlah);
//                        try {
//
//                        } catch (NumberFormatException e) {
//                            popuppenjualan17 pwSalah = new popuppenjualan17();
//                            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
//                            pwSalah.setVisible(true);
//                            return;
//                        }
                    }

                    // Update tampilan total harga
                    total_harga.setText(String.valueOf(totalHarga));

                    // Notifikasi keberhasilan
                    popuppembelian8 pwSalah = new popuppembelian8();
                    pwSalah.setAlwaysOnTop(true);
                    pwSalah.setVisible(true);

                    // Reset hanya form input, tidak total_harga
                    nama_produk.setSelectedItem("");
                    id_produk.setText("");
                    harga.setText("");
                    jumlah_pcs.setText("");
                    stok.setText("");
                    jumlah_harga.setText("");
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
            noButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    main1.setVisible(false);
                }
            });
            // Tambahkan panel tombol ke panel utama
            panel.add(Box.createVerticalStrut(20)); // Tambahkan jarak antara label dan tombol
            panel.add(buttonPanel);

            // Tambahkan panel utama ke frame
            add(panel);
        }
    }

    public class MainFrame2 extends JFrame {

        public MainFrame2() {
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
            JLabel label = new JLabel("Anda yakin menghapus produk ini?", SwingConstants.CENTER);
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
                    if (selectedRow != -1) {
                        // Dapatkan harga dan jumlah produk yang akan dihapus
                        int harga = Integer.parseInt(tabelview.getValueAt(selectedRow, 2).toString());
                        int jumlah = Integer.parseInt(tabelview.getValueAt(selectedRow, 3).toString());
                        // Hitung harga subtotal untuk produk yang akan dihapus
                        int subtotal = harga * jumlah;

                        // Hapus baris yang dipilih dari tabel
                        model.removeRow(selectedRow);

                        // Hitung total harga setelah penghapusan
                        int row = tabelview.getRowCount();
                        int total = 0;
                        for (int i = 0; i < row; i++) {
                            int hargaBaru = Integer.parseInt(tabelview.getValueAt(i, 2).toString());
                            int jumlahBaru = Integer.parseInt(tabelview.getValueAt(i, 3).toString());
                            total += (hargaBaru * jumlahBaru); // Menghitung total harga
                        }

                        // Update total harga pada label
                        total_harga.setText(String.valueOf(total));
                    }
//
//                // Reset form input setelah menghapus
                    nama_produk.setSelectedItem("");
                    id_produk.setText("");
                    harga.setText("");
                    jumlah_pcs.setText("");
                    stok.setText("");
                    jumlah_harga.setText("");
                    nama_produk.requestFocus();
//            } else if (n == JOptionPane.NO_OPTION) {
//                // Jika tidak dihapus, tidak ada aksi tambahan
//            } else {
//                return; // Jika user menutup dialog
//            }
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
                    main2.setVisible(false);
                }
            });
            // Tambahkan panel tombol ke panel utama
            panel.add(Box.createVerticalStrut(20)); // Tambahkan jarak antara label dan tombol
            panel.add(buttonPanel);

            // Tambahkan panel utama ke frame
            add(panel);
        }
    }

    public class MainFrame4 extends JFrame {

        public MainFrame4() {
            setTitle("SIAPKAN SNIPER");
            setSize(401, 183); // Tinggi frame sesuai dengan kebutuhan
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Panel utama
            JPanel panel = new JPanel();
            panel.setBackground(new Color(20, 20, 50)); // Warna biru tua
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Layout vertikal untuk mengatur label dan tombol ke bawah

            // Label dengan teks
            JLabel label = new JLabel("Anda yakin ingin menambah level?", SwingConstants.CENTER);
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
                    String level = (String) varian_level.getSelectedItem();
                    int hargaTambahan = 0;
                    System.out.println(level);
                    switch (level) {
                        case "level 1":
                            hargaTambahan = 0;
                            break;
                        case "level 2":
                            hargaTambahan = 1000;
                            break;
                        case "level 3":
                            hargaTambahan = 2000;
                            break;
                        case "level 4":
                            hargaTambahan = 3000;
                            break;
                        case "level 5":
                            hargaTambahan = 4000;
                            break;
                        default:

                            return;

                    }

                    // Ambil nilai total_harga yang sudah ada
                    long currentTotal = Long.parseLong(total_harga.getText());

                    // Tambahkan harga level ke total yang sudah ada
                    long newTotal = currentTotal + hargaTambahan;

                    // Update total_harga dengan nilai baru
                    total_harga.setText(String.valueOf(newTotal));

                    // Tampilkan informasi perubahan harga
                    popuppenjualan16 pwSalah = new popuppenjualan16();
                    pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                    pwSalah.setVisible(true);
                    main4.setVisible(false);
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


    private void varian_levelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_varian_levelKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_varian_levelKeyPressed

    private void juserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_juserActionPerformed
        popupposisi posisi = new popupposisi();
        posisi.setAlwaysOnTop(true);  // Agar popup berada di depan
        posisi.setVisible(true);
    }//GEN-LAST:event_juserActionPerformed

    private void jlaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlaporanActionPerformed
        popupposisi posisi = new popupposisi();
        posisi.setAlwaysOnTop(true);  // Agar popup berada di depan
        posisi.setVisible(true);
    }//GEN-LAST:event_jlaporanActionPerformed

    private void jtransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtransaksiActionPerformed
        new Transaksi_kasir().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jtransaksiActionPerformed

    private void jprodukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jprodukActionPerformed
        new Data_produkkasir().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jprodukActionPerformed

    private void jdashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdashboardActionPerformed
        new Dashboard_Kasir().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jdashboardActionPerformed

    private void jusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jusernameActionPerformed
        // Inisialisasi komponen GUI terlebih dahulu
        initComponents();
        Transaksi_penjualan_kasir tp = new Transaksi_penjualan_kasir();
        tp.jusername.setText("" + Masuk.currentUsername);
        tp.setVisible(true);
        jusername.setEditable(false);
        this.dispose(); // Menutup form login
        // Setel text jusername ke username yang login (terdapat di Masuk.currentUsername)
        jusername.setText(Masuk.currentUsername);
    }//GEN-LAST:event_jusernameActionPerformed

    private void stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stokActionPerformed

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
            java.util.logging.Logger.getLogger(Transaksi_penjualan_kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi_penjualan_kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi_penjualan_kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi_penjualan_kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Transaksi_penjualan_kasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cetak_struk;
    private javax.swing.JTextField dibayar;
    private javax.swing.JButton edit;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField harga;
    private javax.swing.JTextField id_produk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JButton jdashboard;
    private javax.swing.JComboBox<String> jenis_produk;
    private javax.swing.JButton jkeluar;
    private javax.swing.JButton jlaporan;
    private javax.swing.JTextField jno_trans;
    private javax.swing.JLabel jpengguna;
    private javax.swing.JButton jproduk;
    private javax.swing.JTextField jtanggal;
    private javax.swing.JButton jtransaksi;
    private javax.swing.JTextField jumlah_harga;
    private javax.swing.JTextField jumlah_pcs;
    private javax.swing.JButton juser;
    private javax.swing.JTextField jusername;
    private javax.swing.JTextField kembalian;
    private javax.swing.JComboBox<String> nama_produk;
    private javax.swing.JButton simpan;
    private javax.swing.JTextField stok;
    private javax.swing.JTable tabelview;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField total_harga;
    private javax.swing.JComboBox<String> varian_level;
    // End of variables declaration//GEN-END:variables
}
