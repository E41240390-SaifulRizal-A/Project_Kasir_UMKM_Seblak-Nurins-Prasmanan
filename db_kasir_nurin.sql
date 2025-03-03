-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 22, 2024 at 12:21 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kasir_nurin`
--

-- --------------------------------------------------------

--
-- Table structure for table `detail_pembelian`
--

CREATE TABLE `detail_pembelian` (
  `id_pembelian` int(10) NOT NULL,
  `id_produk` int(10) NOT NULL,
  `jumlah_beli` int(11) NOT NULL,
  `harga_beli` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_pembelian`
--

INSERT INTO `detail_pembelian` (`id_pembelian`, `id_produk`, `jumlah_beli`, `harga_beli`) VALUES
(1, 4, 25, 3000),
(1, 5, 45, 1800),
(1, 10, 100, 1500),
(1, 6, 80, 1000),
(1, 3, 45, 2000),
(2, 7, 20, 2000),
(3, 22, 16, 2812),
(4, 27, 30, 1000),
(5, 22, 25, 1800),
(6, 12, 25, 1800),
(6, 8, 18, 1388);

--
-- Triggers `detail_pembelian`
--
DELIMITER $$
CREATE TRIGGER `after_insert_detail_pembelian` AFTER INSERT ON `detail_pembelian` FOR EACH ROW BEGIN
    -- Masukkan stok dan harga beli ke tabel detail_produk
    INSERT INTO detail_produk (id_produk, stok, harga_beli, tanggal_masuk, is_active)
    VALUES (NEW.id_produk, NEW.jumlah_beli, NEW.harga_beli, CURRENT_DATE, 1);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `detail_penjualan`
--

CREATE TABLE `detail_penjualan` (
  `id_penjualan` int(10) NOT NULL,
  `id_produk` int(10) NOT NULL,
  `nama_produk` varchar(35) NOT NULL,
  `varian_level` text NOT NULL,
  `jumlah_pcs` int(11) NOT NULL,
  `subtotal` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_penjualan`
--

INSERT INTO `detail_penjualan` (`id_penjualan`, `id_produk`, `nama_produk`, `varian_level`, `jumlah_pcs`, `subtotal`) VALUES
(1, 1, 'Sosis panjang', 'level 3', 4, 8000),
(1, 5, 'Udang gulung', 'level 1', 25, 50000),
(1, 10, 'Jamur enoki', 'level 1', 25, 50000),
(1, 6, 'Ekor udang', 'level 1', 25, 50000),
(1, 3, 'Soki kotak', 'level 1', 25, 60000),
(2, 7, 'Fish roll', 'level 3', 25, 50000),
(3, 3, 'Soki kotak', 'level 1', 5, 15000),
(3, 6, 'Ekor udang', 'level 1', 5, 15000),
(4, 22, 'Telur', 'level 1', 25, 75000),
(5, 5, 'Udang gulung', 'level 3', 5, 10000),
(6, 3, 'Soki kotak', 'level 2', 5, 15000),
(6, 6, 'Ekor udang', 'level 2', 15, 45000),
(7, 27, 'Cikur', 'level 1', 25, 25000),
(8, 22, 'Telur', 'level 1', 16, 48000),
(9, 8, 'Chikuwa', 'level 2', 25, 50000),
(9, 5, 'Udang gulung', 'level 2', 5, 10000);

--
-- Triggers `detail_penjualan`
--
DELIMITER $$
CREATE TRIGGER `after_insert_detail_penjualan` AFTER INSERT ON `detail_penjualan` FOR EACH ROW BEGIN
    DECLARE stok_produk INT;
    DECLARE stok_tersisa INT;

    -- Ambil stok yang ada di tabel produk berdasarkan id_produk
    SELECT stok INTO stok_produk
    FROM produk
    WHERE id_produk = NEW.id_produk;

    -- Hitung stok yang akan dikurangi berdasarkan jumlah yang dijual
    SET stok_tersisa = NEW.jumlah_pcs;

    -- Jika stok produk mencukupi
    IF stok_produk >= stok_tersisa THEN
        -- Kurangi stok produk
        UPDATE produk
        SET stok = stok - stok_tersisa
        WHERE id_produk = NEW.id_produk;
    ELSE
        -- Jika stok produk tidak mencukupi, set stok menjadi 0
        UPDATE produk
        SET stok = 0
        WHERE id_produk = NEW.id_produk;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `detail_produk`
--

CREATE TABLE `detail_produk` (
  `id_detail` int(11) NOT NULL,
  `id_produk` int(11) NOT NULL,
  `harga_beli` int(11) NOT NULL,
  `stok` int(11) NOT NULL,
  `tanggal_masuk` date NOT NULL,
  `is_active` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_produk`
--

INSERT INTO `detail_produk` (`id_detail`, `id_produk`, `harga_beli`, `stok`, `tanggal_masuk`, `is_active`) VALUES
(1, 1, 3000, 0, '2024-12-14', 1),
(2, 5, 1800, 45, '2024-12-15', 1),
(3, 10, 1500, 100, '2024-12-15', 0),
(4, 6, 1000, 80, '2024-12-15', 0),
(5, 3, 2000, 45, '2024-12-15', 0),
(6, 7, 2000, 20, '2024-12-15', 0),
(7, 22, 2812, 16, '2024-12-15', 0),
(8, 27, 1000, 30, '2024-12-17', 0),
(9, 22, 1800, 25, '2024-12-18', 0),
(10, 12, 1800, 25, '2024-12-19', 1),
(11, 8, 1388, 18, '2024-12-19', 0);

-- --------------------------------------------------------

--
-- Table structure for table `log_delete`
--

CREATE TABLE `log_delete` (
  `id_log` int(11) NOT NULL,
  `id_produk` int(11) DEFAULT NULL,
  `nama_produk` varchar(35) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL,
  `harga` decimal(10,2) DEFAULT NULL,
  `waktu_hapus` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `log_delete`
--

INSERT INTO `log_delete` (`id_log`, `id_produk`, `nama_produk`, `stok`, `harga`, `waktu_hapus`) VALUES
(8, 75, 'bebek keju', 16, 3500.00, '2024-12-19 13:11:51'),
(9, 25, 'Mie sedap selection', 25, 4000.00, '2024-12-19 13:11:56'),
(10, 74, 'ayam keju', 14, 3000.00, '2024-12-19 13:23:58'),
(11, 73, 'udang keju', 12, 2000.00, '2024-12-19 13:25:21'),
(12, 76, 'ceker keju', 20, 2500.00, '2024-12-19 13:35:30'),
(13, 21, 'Bintang', 25, 1000.00, '2024-12-19 13:35:36');

-- --------------------------------------------------------

--
-- Table structure for table `log_update_stok`
--

CREATE TABLE `log_update_stok` (
  `id_log` int(11) NOT NULL,
  `id_produk` int(11) DEFAULT NULL,
  `nama_produk` varchar(255) DEFAULT NULL,
  `stok_awal` int(11) DEFAULT NULL,
  `stok_akhir` int(11) DEFAULT NULL,
  `harga` decimal(10,2) DEFAULT NULL,
  `jumlah_terjual` int(11) DEFAULT NULL,
  `waktu_update` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `log_update_stok`
--

INSERT INTO `log_update_stok` (`id_log`, `id_produk`, `nama_produk`, `stok_awal`, `stok_akhir`, `harga`, `jumlah_terjual`, `waktu_update`) VALUES
(3, 70, 'petis', 20, 15, 1500.00, 5, '2024-12-15 08:18:45'),
(4, 22, 'Telur', 25, 0, 3000.00, 25, '2024-12-15 08:39:50'),
(5, 22, 'Telur', 0, 16, 3000.00, -16, '2024-12-15 08:39:50'),
(6, 5, 'Udang gulung', 45, 40, 2000.00, 5, '2024-12-15 11:56:13'),
(7, 3, 'Soki kotak', 40, 35, 3000.00, 5, '2024-12-15 11:58:41'),
(8, 6, 'Ekor udang', 75, 60, 3000.00, 15, '2024-12-15 11:58:41'),
(9, 27, 'Cikur', 25, 0, 1000.00, 25, '2024-12-17 05:13:40'),
(10, 27, 'Cikur', 0, 30, 1000.00, -30, '2024-12-17 05:13:41'),
(11, 22, 'Telur', 16, 0, 3000.00, 16, '2024-12-18 12:58:50'),
(12, 22, 'Telur', 0, 25, 3000.00, -25, '2024-12-18 12:58:51'),
(13, 8, 'Chikuwa', 25, 0, 2000.00, 25, '2024-12-19 13:37:39'),
(14, 5, 'Udang gulung', 40, 35, 2000.00, 5, '2024-12-19 13:37:39'),
(15, 8, 'Chikuwa', 0, 18, 2000.00, -18, '2024-12-19 13:37:40'),
(16, 26, 'Cuanki', 25, 80, 2500.00, -55, '2024-12-22 10:20:37');

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE `pembelian` (
  `id_pembelian` int(10) NOT NULL,
  `id_user` varchar(4) NOT NULL,
  `tanggal_beli` date NOT NULL,
  `total_harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pembelian`
--

INSERT INTO `pembelian` (`id_pembelian`, `id_user`, `tanggal_beli`, `total_harga`) VALUES
(1, '001', '2024-12-14', 75000),
(2, '001', '2024-12-15', 40000),
(3, '001', '2024-12-15', 44992),
(4, '001', '2024-12-17', 30000),
(5, '001', '2024-12-18', 45000),
(6, '001', '2024-12-19', 69984);

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE `penjualan` (
  `id_penjualan` int(10) NOT NULL,
  `id_user` varchar(4) NOT NULL,
  `tanggal_jual` date NOT NULL,
  `grand_total` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`id_penjualan`, `id_user`, `tanggal_jual`, `grand_total`) VALUES
(1, '001', '2024-12-14', 10000),
(2, '001', '2024-12-15', 52000),
(3, '001', '2024-12-15', 30000),
(4, '001', '2024-12-15', 75000),
(5, '001', '2024-12-15', 12000),
(6, '001', '2024-12-15', 61000),
(7, '001', '2024-12-17', 25000),
(8, '001', '2024-12-18', 48000),
(9, '001', '2024-12-19', 61000);

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id_produk` int(4) NOT NULL,
  `nama_produk` varchar(25) NOT NULL,
  `harga_jual` int(6) NOT NULL,
  `harga_beli` int(6) NOT NULL,
  `stok` int(3) NOT NULL,
  `waktu_pemasukan` date NOT NULL DEFAULT current_timestamp(),
  `stok_diperbarui` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id_produk`, `nama_produk`, `harga_jual`, `harga_beli`, `stok`, `waktu_pemasukan`, `stok_diperbarui`) VALUES
(1, 'Sosis Panjang', 2000, 0, 25, '2024-12-18', 0),
(2, 'Keju pedas', 2000, 0, 0, '2024-12-18', 0),
(3, 'Soki kotak', 3000, 2000, 35, '2024-12-18', 0),
(4, 'Odeng', 2000, 0, 25, '2024-12-18', 0),
(5, 'Udang gulung', 2000, 1800, 35, '2024-12-18', 0),
(6, 'Ekor udang', 3000, 1000, 60, '2024-12-18', 0),
(7, 'Fish roll', 2000, 2000, 20, '2024-12-18', 0),
(8, 'Chikuwa', 2000, 1388, 18, '2024-12-18', 0),
(9, 'Soki stick1', 1000, 0, 25, '2024-12-18', 0),
(10, 'Jamur enoki', 3000, 1500, 100, '2024-12-18', 0),
(11, 'Crab stick', 2000, 0, 25, '2024-12-18', 0),
(12, 'Kembang cumi', 1500, 0, 25, '2024-12-18', 0),
(13, 'Dumpling ayam', 2000, 0, 25, '2024-12-18', 0),
(14, 'Dumpling keju', 2000, 0, 25, '2024-12-18', 0),
(15, 'Baso ikan', 1000, 0, 25, '2024-12-18', 0),
(16, 'Scallop', 1000, 0, 25, '2024-12-18', 0),
(17, 'Sosis jumbo', 4000, 0, 25, '2024-12-18', 0),
(18, 'Otak-otak', 2000, 0, 25, '2024-12-18', 0),
(19, 'Siomay ikan', 2000, 0, 25, '2024-12-18', 0),
(20, 'Scallop pedas', 2000, 0, 25, '2024-12-18', 0),
(22, 'Telur', 3000, 1800, 25, '2024-12-18', 0),
(24, 'Intermie', 3000, 0, 25, '2024-12-18', 0),
(26, 'Cuanki', 2500, 2000, 80, '2024-12-18', 0),
(27, 'Cikur', 1000, 1000, 30, '2024-12-18', 0),
(28, 'Siomay kering', 2000, 0, 25, '2024-12-18', 0),
(29, 'Bakso daging', 4000, 2600, 25, '2024-12-18', 0),
(72, 'ayam betutu', 3500, 2500, 20, '2024-12-18', 0),
(77, 'siomay udang', 2000, 1500, 20, '2024-12-19', 0);

--
-- Triggers `produk`
--
DELIMITER $$
CREATE TRIGGER `after_delete_produk` AFTER DELETE ON `produk` FOR EACH ROW BEGIN
    INSERT INTO log_delete (id_produk, nama_produk, stok, harga)
    VALUES (OLD.id_produk, OLD.nama_produk, OLD.stok, OLD.harga_jual);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `after_update_stok_produk` AFTER UPDATE ON `produk` FOR EACH ROW BEGIN
    -- Cek jika stok berubah
    IF OLD.stok <> NEW.stok THEN
        INSERT INTO log_update_stok (id_produk, nama_produk, stok_awal, stok_akhir, harga, jumlah_terjual)
        VALUES (OLD.id_produk, OLD.nama_produk, OLD.stok, NEW.stok, NEW.harga_jual, OLD.stok - NEW.stok);
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` varchar(4) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(10) NOT NULL,
  `posisi` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `username`, `password`, `posisi`) VALUES
('001', 'admin', 'admin123', 'Owner'),
('002', 'kasir', 'kasir123', 'Kasir');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_pembelian`
--
ALTER TABLE `detail_pembelian`
  ADD KEY `id_pembelian` (`id_pembelian`),
  ADD KEY `id_produk` (`id_produk`);

--
-- Indexes for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD KEY `id_penjualan` (`id_penjualan`),
  ADD KEY `id_produk` (`id_produk`);

--
-- Indexes for table `detail_produk`
--
ALTER TABLE `detail_produk`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `id_produk` (`id_produk`);

--
-- Indexes for table `log_delete`
--
ALTER TABLE `log_delete`
  ADD PRIMARY KEY (`id_log`),
  ADD UNIQUE KEY `id_produk` (`id_produk`);

--
-- Indexes for table `log_update_stok`
--
ALTER TABLE `log_update_stok`
  ADD PRIMARY KEY (`id_log`);

--
-- Indexes for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD PRIMARY KEY (`id_pembelian`),
  ADD KEY `id_user` (`id_user`) USING BTREE;

--
-- Indexes for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`id_penjualan`),
  ADD KEY `id_user` (`id_user`) USING BTREE;

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id_produk`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `idx_users_username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_produk`
--
ALTER TABLE `detail_produk`
  MODIFY `id_detail` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `log_delete`
--
ALTER TABLE `log_delete`
  MODIFY `id_log` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `log_update_stok`
--
ALTER TABLE `log_update_stok`
  MODIFY `id_log` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `pembelian`
--
ALTER TABLE `pembelian`
  MODIFY `id_pembelian` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `penjualan`
--
ALTER TABLE `penjualan`
  MODIFY `id_penjualan` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `produk`
--
ALTER TABLE `produk`
  MODIFY `id_produk` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_pembelian`
--
ALTER TABLE `detail_pembelian`
  ADD CONSTRAINT `detail_pembelian_ibfk_1` FOREIGN KEY (`id_pembelian`) REFERENCES `pembelian` (`id_pembelian`) ON UPDATE CASCADE;

--
-- Constraints for table `detail_penjualan`
--
ALTER TABLE `detail_penjualan`
  ADD CONSTRAINT `detail_penjualan_ibfk_1` FOREIGN KEY (`id_penjualan`) REFERENCES `penjualan` (`id_penjualan`) ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_penjualan_ibfk_2` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `detail_produk`
--
ALTER TABLE `detail_produk`
  ADD CONSTRAINT `detail_produk_ibfk_1` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD CONSTRAINT `pembelian_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON UPDATE CASCADE;

--
-- Constraints for table `penjualan`
--
ALTER TABLE `penjualan`
  ADD CONSTRAINT `penjualan_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON UPDATE CASCADE;

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `process_produk_update` ON SCHEDULE EVERY 1 SECOND STARTS '2024-12-14 21:20:41' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
 -- Update tabel produk dengan stok dan harga beli dari detail_produk
    UPDATE produk p
    JOIN (
        SELECT 
            dp.id_produk,
            dp.id_detail,
            dp.stok AS stok_terpakai,
            dp.harga_beli AS harga_beli_terpakai
        FROM detail_produk dp
        WHERE dp.is_active = 1 -- Hanya gunakan detail_produk yang aktif
        ORDER BY dp.id_produk, dp.id_detail ASC -- Prioritaskan id_detail terkecil untuk setiap id_produk
    ) sub ON p.id_produk = sub.id_produk
    SET 
        p.stok = sub.stok_terpakai,           -- Update stok dari detail_produk paling lama
        p.harga_beli = sub.harga_beli_terpakai, -- Update harga_beli dari detail_produk paling lama
        p.stok_diperbarui = 1                -- Tandai stok telah diperbarui
    WHERE p.stok = 0;  -- Hanya lakukan update jika stok di produk = 0

    -- Tandai hanya detail_produk yang digunakan untuk update stok dan harga_beli
    UPDATE detail_produk dp
    JOIN (
        SELECT 
            dp.id_detail,
            dp.id_produk
        FROM detail_produk dp
        JOIN produk p ON dp.id_produk = p.id_produk
        WHERE dp.is_active = 1 -- Hanya detail_produk yang aktif
          AND p.stok_diperbarui = 1 -- Hanya produk yang stoknya baru saja diperbarui
        ORDER BY dp.id_produk, dp.id_detail ASC -- Urutkan berdasarkan id_produk dan id_detail terkecil
    ) sub ON dp.id_detail = sub.id_detail
    SET dp.is_active = 0 -- Tandai hanya baris yang digunakan sebagai tidak aktif
    WHERE dp.is_active = 1 
      AND dp.id_detail = sub.id_detail; -- Pastikan hanya yang digunakan yang ditandai tidak aktif

    -- Pastikan stok_diperbarui direset agar hanya berlaku untuk update berikutnya
    UPDATE produk
    SET stok_diperbarui = 0
    WHERE stok_diperbarui = 1;

    -- Pastikan bahwa proses berhasil
    SELECT CONCAT('Proses Update Berhasil pada: ', NOW()) AS status_message;
END$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
