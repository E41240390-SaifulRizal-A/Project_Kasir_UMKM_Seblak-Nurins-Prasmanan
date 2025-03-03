package kasir_umkm;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class database {

    Connection con;
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost/db_kasir_umkm1";
    private final String user = "root";
    private final String pwd = "";

    // Metode koneksi ke database
    public void koneksi() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println("Koneksi Berhasil");
        } catch (Exception e) {
            System.out.println("Error: Koneksi Gagal! " + e.getMessage());
        }
    }

    // Metode untuk memperbarui stok dan harga_beli di tabel produk
    public void transferStokDanHarga() {
        try {
            // Query untuk memindahkan data stok dan harga_beli
            String sql = "UPDATE produk p "
                    + "JOIN detail_produk dp ON p.id_produk = dp.id_produk "
                    + "SET p.stok = dp.stok, "
                    + "    p.harga_beli = dp.harga_beli, "
                    + "    p.stok_diperbarui = 1, "
                    + "    dp.is_active = 0 "
                    + "WHERE p.stok = 0 AND dp.is_active = 1";

            // Eksekusi query
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(sql);

            // Output hasil
            System.out.println(rowsAffected + " baris diperbarui.");
        } catch (SQLException e) {
            System.out.println("Error: Transfer stok dan harga gagal!");
            e.printStackTrace();
        }
    }

    public ResultSet ambildata(String SQL) {
        try {
            Statement st = con.createStatement();
            return st.executeQuery(SQL);
        } catch (Exception e) {
            System.out.println("Error:\nPengecekan data gagal diakses !");
            return null;
        }
    }

    public void aksi(String SQL) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate(SQL);
        } catch (Exception e) {
            System.out.println("Error:\nAksi gagal diakses !");
        }
    }

    public void aksi1(String SQL) {
        try {
            
            PreparedStatement stSimpan1 = con.prepareStatement(SQL);
            stSimpan1.setInt(1, 0);
            
            stSimpan1.addBatch();
            stSimpan1.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error:\nAksi gagal diakses !");
        }
    }

    // Metode main untuk menjalankan program
    public static void main(String[] args) {
        // Inisialisasi dan koneksi ke database
        database db = new database();
        db.koneksi();
        ResultSet ambildata;
        // Menjalankan transfer stok dan harga
        db.transferStokDanHarga();
    }
}
