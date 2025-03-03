package kasir_umkm;

import popupuser.popupuser8;
import popupuser.popupuser9;
import popupuser.popupuser1;
import popupuser.popupuser7;
import popupuser.popupuser6;
import popupuser.popupuser5;
import popupuser.popupuser4;
import popupuser.popupuser3;
import popupuser.popupuser2;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class Data_user extends javax.swing.JFrame {

    DefaultTableModel model = new DefaultTableModel();
    database db = new database();

    public Data_user() {
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
        for (int i = 0; i < jdatauser.getColumnModel().getColumnCount(); i++) {
            TableColumn column = jdatauser.getColumnModel().getColumn(i);
            column.setCellRenderer(cellRenderer);
        }

        // Pusatkan teks dalam header tabel
        JTableHeader header = jdatauser.getTableHeader();
        header.setFont(new Font("Tahome", Font.BOLD, 20));
        jdatauser.setFont(new Font("Tahome", Font.PLAIN, 18));
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Pusatkan horizontal
        headerRenderer.setVerticalAlignment(SwingConstants.CENTER);   // Pusatkan vertikal
        header.setDefaultRenderer(headerRenderer);
    }

    public void viewdata() {
        model.setRowCount(0);

        // Tambah kolom tabel hanya jika belum ada
        if (model.getColumnCount() == 0) {
            model.addColumn("id_user");
            model.addColumn("username");
            model.addColumn("password");
            model.addColumn("posisi");
        }
        try {
            ResultSet rs = db.ambildata("select * from users");
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("id_user"), rs.getString("username"), rs.getString("password"),
                    rs.getString("posisi")});
                jdatauser.setModel(model);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Debug", JOptionPane.ERROR_MESSAGE);
        }
    }

// Fungsi untuk mencari dan memperbarui tabel
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jsimpan = new javax.swing.JButton();
        jkeluar = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jdatauser = new javax.swing.JTable();
        jpassword = new javax.swing.JTextField();
        jid_user = new javax.swing.JTextField();
        jcari = new javax.swing.JButton();
        jEdit1 = new javax.swing.JButton();
        jHapus = new javax.swing.JButton();
        jposisi = new javax.swing.JComboBox<>();
        jusername = new javax.swing.JTextField();
        Jcariuser = new javax.swing.JTextField();
        jdashboard3 = new javax.swing.JButton();
        jproduk = new javax.swing.JButton();
        jtransaksi = new javax.swing.JButton();
        jlaporan = new javax.swing.JButton();
        juser = new javax.swing.JButton();
        j1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        j = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jpengguna = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/seblak.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jsimpan.setBackground(new java.awt.Color(153, 153, 153));
        jsimpan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jsimpan.setText("SIMPAN");
        jsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsimpanActionPerformed(evt);
            }
        });
        getContentPane().add(jsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, 100, 40));

        jkeluar.setBackground(new java.awt.Color(255, 0, 0));
        jkeluar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jkeluar.setText("KELUAR");
        jkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkeluarActionPerformed(evt);
            }
        });
        getContentPane().add(jkeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 10, 160, 60));

        jdatauser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jdatauser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id user", "username", "password", "posisi"
            }
        ));
        jdatauser.setRowHeight(30);
        jdatauser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jdatauserMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jdatauser);

        getContentPane().add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 1060, 340));
        getContentPane().add(jpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 150, 40));

        jid_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jid_userActionPerformed(evt);
            }
        });
        getContentPane().add(jid_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 150, 40));

        jcari.setBackground(new java.awt.Color(153, 153, 153));
        jcari.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jcari.setText("CARI");
        jcari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcariMouseClicked(evt);
            }
        });
        jcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcariActionPerformed(evt);
            }
        });
        getContentPane().add(jcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 220, 100, 40));

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
        getContentPane().add(jEdit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 170, 100, 40));

        jHapus.setBackground(new java.awt.Color(153, 153, 153));
        jHapus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jHapus.setText("HAPUS");
        jHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHapusActionPerformed(evt);
            }
        });
        getContentPane().add(jHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 170, 100, 40));

        jposisi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jposisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Posisi", "Owner", "Kasir" }));
        getContentPane().add(jposisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 130, 40));
        getContentPane().add(jusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 150, 40));

        Jcariuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcariuserActionPerformed(evt);
            }
        });
        getContentPane().add(Jcariuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, 190, 40));

        jdashboard3.setBackground(new java.awt.Color(204, 204, 204));
        jdashboard3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jdashboard3.setText("DASHBOARD");
        jdashboard3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdashboard3ActionPerformed(evt);
            }
        });
        getContentPane().add(jdashboard3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 680, 170, 60));

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

        j1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        j1.setForeground(new java.awt.Color(255, 255, 255));
        j1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        j1.setText("POSISI");
        getContentPane().add(j1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 80, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DATA USER");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PASSWORD");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 80, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("ID USER");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 80, 40));

        j.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        j.setForeground(new java.awt.Color(255, 255, 255));
        j.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        j.setText("CARI");
        getContentPane().add(j, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 70, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("USERNAME");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 80, 40));

        jpengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/popup/profile.png"))); // NOI18N
        jpengguna.setText("    ");
        jpengguna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpenggunaMouseClicked(evt);
            }
        });
        getContentPane().add(jpengguna, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GAMBAR/Halaman Produk Kosong.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 800));

        setSize(new java.awt.Dimension(1280, 800));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsimpanActionPerformed
        try {

            ResultSet rs = db.ambildata("select * from users where id_user='" + jid_user.getText() + "'");
            if (rs.next()) {
                popupuser8 pwSalah = new popupuser8();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
                jusername.setText(rs.getString("username"));
                jpassword.setText(rs.getString("password"));
                jposisi.setSelectedItem(rs.getString("posisi"));
            } else {

                db.aksi("insert into users(id_user,username,password,posisi)values"
                        + "('" + jid_user.getText() + "','" + jusername.getText() + "','" + jpassword.getText() + "','" + jposisi.getSelectedItem() + "')");
                popupuser9 pwSalah = new popupuser9();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
                jid_user.setText("");
                jusername.setText("");
                jpassword.setText("");
                jposisi.setSelectedItem("");

                viewdata();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_jsimpanActionPerformed

    private void jkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkeluarActionPerformed
        new Masuk().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jkeluarActionPerformed

    private void jid_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jid_userActionPerformed

        try {
            ResultSet rs = db.ambildata("select * from users where id_user='" + jid_user.getText() + "'");
            if (rs.next()) {
                jusername.setText(rs.getString("username"));
                jpassword.setText(rs.getString("password"));
                jposisi.setSelectedItem(rs.getString("posisi"));
                jsimpan.setEnabled(false);

            } else {
                popupuser1 pwSalah = new popupuser1();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
                jpassword.requestFocus();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jid_userActionPerformed

    private void jcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcariActionPerformed

        String keyword = Jcariuser.getText(); // Mengambil teks dari input pencarian
        model.setRowCount(0); // Menghapus semua baris pada tabel sebelum menambahkan data

        if (keyword.isEmpty()) {
            // Jika input pencarian kosong, tampilkan semua data
            viewdata();
            popupuser2 pwSalah = new popupuser2();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);
            return;
        }

        try {
            // Query untuk mencari data berdasarkan username
            ResultSet rs = db.ambildata("SELECT * FROM users WHERE username LIKE '%" + keyword + "%'");
            boolean dataFound = false;

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id_user"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("posisi")
                });
                dataFound = true;
            }

            // Jika data ditemukan, tampilkan di tabel
            if (dataFound) {
                jdatauser.setModel(model);
            } else {
                // Jika data tidak ditemukan, tampilkan semua data dan pesan
                viewdata();
                popupuser3 pwSalah = new popupuser3();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error dalam pencarian data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jcariActionPerformed

    private void jcariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcariMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jcariMouseClicked

    private void jHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHapusActionPerformed
        int row = jdatauser.getSelectedRow();
        if (row != -1) {
            String idUser = model.getValueAt(row, 0).toString(); // Ambil id_user
            try {
                db.aksi("DELETE FROM users WHERE id_user='" + idUser + "'");
                popupuser4 pwSalah = new popupuser4();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
                viewdata(); // Refresh tabel
                jid_user.setText("");
                jusername.setText("");
                jpassword.setText("");
                jposisi.setSelectedItem("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error dalam menghapus data: " + e.getMessage());
            }
        } else {
            popupuser5 pwSalah = new popupuser5();
            pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
            pwSalah.setVisible(true);
        }

    }//GEN-LAST:event_jHapusActionPerformed

    private void jdatauserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdatauserMouseClicked
        // TODO add your handling code here:
        int row = jdatauser.getSelectedRow(); // Ambil baris yang diklik
        if (row != -1) {
            jid_user.setText(model.getValueAt(row, 0).toString());
            jusername.setText(model.getValueAt(row, 1).toString());
            jpassword.setText(model.getValueAt(row, 2).toString());
            jposisi.setSelectedItem(model.getValueAt(row, 3).toString());

        }
    }//GEN-LAST:event_jdatauserMouseClicked

    private void jEdit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jEdit1MouseClicked
        // TODO add your handling code here:
        try {
            ResultSet rs = db.ambildata("select * from users where id_user='" + jid_user.getText() + "'");
            if (rs.next()) {
                db.aksi("update users set username='" + jusername.getText() + "',password='" + jpassword.getText() + "',"
                        + "posisi='" + jposisi.getSelectedItem() + "'");
            } else {
            }

            jid_user.requestFocus();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_jEdit1MouseClicked

    private void jEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEdit1ActionPerformed
        try {
            ResultSet rs = db.ambildata("select * from users where id_user='" + jid_user.getText() + "'");
            if (rs.next()) {
                db.aksi("update users set username='" + jusername.getText() + "',password='" + jpassword.getText() + "',"
                        + "posisi='" + jposisi.getSelectedItem() + "' where id_user='" + jid_user.getText() + "'");
                // Refresh tampilan tabel
                viewdata();
                popupuser6 pwSalah = new popupuser6();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
                // Reset form
                jid_user.setText("");
                jusername.setText("");
                jpassword.setText("");
                jposisi.setSelectedIndex(0);
                // Set focus ke id_user
                jid_user.requestFocus();

            } else {
                popupuser7 pwSalah = new popupuser7();
                pwSalah.setAlwaysOnTop(true);  // Agar popup berada di depan
                pwSalah.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_jEdit1ActionPerformed

    private void JcariuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcariuserActionPerformed
        // TODO add your handling code here:\
    }//GEN-LAST:event_JcariuserActionPerformed

    private void jdashboard3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdashboard3ActionPerformed
        new Dashboard_Owner().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jdashboard3ActionPerformed

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
            java.util.logging.Logger.getLogger(Data_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Data_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Data_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Data_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Data_user().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Jcariuser;
    private javax.swing.JLabel j;
    private javax.swing.JLabel j1;
    private javax.swing.JButton jEdit1;
    private javax.swing.JButton jHapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JButton jcari;
    private javax.swing.JButton jdashboard3;
    private javax.swing.JTable jdatauser;
    private javax.swing.JTextField jid_user;
    private javax.swing.JButton jkeluar;
    private javax.swing.JButton jlaporan;
    private javax.swing.JTextField jpassword;
    private javax.swing.JLabel jpengguna;
    private javax.swing.JComboBox<String> jposisi;
    private javax.swing.JButton jproduk;
    private javax.swing.JButton jsimpan;
    private javax.swing.JButton jtransaksi;
    private javax.swing.JButton juser;
    private javax.swing.JTextField jusername;
    // End of variables declaration//GEN-END:variables
}
