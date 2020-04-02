-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 12 Feb 2020 pada 14.22
-- Versi server: 10.1.37-MariaDB
-- Versi PHP: 7.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `skripsi`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengaduan`
--

CREATE TABLE `pengaduan` (
  `id_pengaduan` int(11) NOT NULL,
  `tgl_pengaduan` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_pelanggan` int(11) NOT NULL,
  `id_petugas` varchar(100) NOT NULL,
  `latitude` varchar(100) NOT NULL,
  `longitude` varchar(128) NOT NULL,
  `ket_pengaduan` text NOT NULL,
  `gambar` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pengaduan`
--

INSERT INTO `pengaduan` (`id_pengaduan`, `tgl_pengaduan`, `id_pelanggan`, `id_petugas`, `latitude`, `longitude`, `ket_pengaduan`, `gambar`) VALUES
(1, '2020-02-10 04:28:19', 1, '', '', '', 'Masalahnya sampah tidak bersih', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_admin`
--

CREATE TABLE `tb_admin` (
  `id_admin` int(11) NOT NULL,
  `email` varchar(128) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nm_lengkap` varchar(128) NOT NULL,
  `foto` varchar(100) NOT NULL,
  `level` enum('admin','pegawai','','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_admin`
--

INSERT INTO `tb_admin` (`id_admin`, `email`, `password`, `nm_lengkap`, `foto`, `level`) VALUES
(1, 'admin@gmail.com', '0707', 'Admin', '', 'admin'),
(2, 'pegawai@gmail.com', '0404', 'Pegawai', '', 'pegawai');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_bayar`
--

CREATE TABLE `tb_bayar` (
  `id_bayar` int(11) NOT NULL,
  `tgl` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_pelanggan` int(11) NOT NULL,
  `id_jenisPelanggan` varchar(100) NOT NULL,
  `status` enum('Lunas','Belum Bayar') NOT NULL,
  `total` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_bayar`
--

INSERT INTO `tb_bayar` (`id_bayar`, `tgl`, `id_pelanggan`, `id_jenisPelanggan`, `status`, `total`) VALUES
(1, '2020-02-12 13:20:53', 1, '1', 'Lunas', '10000'),
(2, '2020-02-12 13:20:53', 2, '2', 'Belum Bayar', '15000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_jadwal`
--

CREATE TABLE `tb_jadwal` (
  `id_jadwal` int(11) NOT NULL,
  `tgl_awal` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tgl_akhir` date NOT NULL,
  `id_kendaraan` varchar(100) NOT NULL,
  `id_jalur` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_jalur`
--

CREATE TABLE `tb_jalur` (
  `id_jalur` int(11) NOT NULL,
  `jalur` text NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_jalur`
--

INSERT INTO `tb_jalur` (`id_jalur`, `jalur`, `keterangan`) VALUES
(1, 'Jl. Yos Sudarso', 'Jl. Yos Sudarso - Jl. Pagar Alam');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_jenispelanggan`
--

CREATE TABLE `tb_jenispelanggan` (
  `id_jenisPelanggan` int(11) NOT NULL,
  `jenis_pelanggan` varchar(100) NOT NULL,
  `tarif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_jenispelanggan`
--

INSERT INTO `tb_jenispelanggan` (`id_jenisPelanggan`, `jenis_pelanggan`, `tarif`) VALUES
(1, 'Rumah Tangga', 10000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_kendaraan`
--

CREATE TABLE `tb_kendaraan` (
  `id_kendaraan` int(11) NOT NULL,
  `kendaraan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_petugas`
--

CREATE TABLE `tb_petugas` (
  `id_petugas` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `no_telepon` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_petugas`
--

INSERT INTO `tb_petugas` (`id_petugas`, `email`, `nama`, `alamat`, `no_telepon`) VALUES
(1, 'hanisammalia@gmail.com', 'hanisas', 'Jl. Mggr', '089999999'),
(2, 'coba@gmail.com', 'abc', 'jl. IIII', '098345678');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_rpengajuan`
--

CREATE TABLE `tb_rpengajuan` (
  `id_pengajuan` int(100) NOT NULL,
  `tgl_pengajuan` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `latitude` varchar(100) NOT NULL,
  `longitude` varchar(100) NOT NULL,
  `id_pelanggan` int(100) NOT NULL,
  `id_jenisPelanggan` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `id_petugas` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_rpengajuan`
--

INSERT INTO `tb_rpengajuan` (`id_pengajuan`, `tgl_pengajuan`, `latitude`, `longitude`, `id_pelanggan`, `id_jenisPelanggan`, `alamat`, `id_petugas`) VALUES
(1, '2020-02-06 08:20:00', '5,3456789', '6,4567890', 1, 'Rumah Tangga', 'Jl. apa ya', 'Pak Kim'),
(2, '2020-02-10 00:15:01', '7,234567890', '4,234567890', 1, '1', 'jl.GG aja', '1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_user`
--

CREATE TABLE `tb_user` (
  `id_pelanggan` int(11) NOT NULL,
  `nama_pelanggan` varchar(50) NOT NULL,
  `id_jenisPelanggan` varchar(15) NOT NULL,
  `NIK` varchar(18) NOT NULL,
  `latitude` varchar(100) NOT NULL,
  `longitude` varchar(100) NOT NULL,
  `alamat` text NOT NULL,
  `no_telp` varchar(14) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `id_jalur` varchar(50) NOT NULL,
  `tgl_awal` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tgl_berhenti` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_user`
--

INSERT INTO `tb_user` (`id_pelanggan`, `nama_pelanggan`, `id_jenisPelanggan`, `NIK`, `latitude`, `longitude`, `alamat`, `no_telp`, `password`, `email`, `id_jalur`, `tgl_awal`, `tgl_berhenti`) VALUES
(1, 'hanis', '1', '354544', '5,4560', '5,3456789', 'jl.dfghj', '0897777777', 'gatau', 'hanisamalia37@gmail.com', '2', '2020-02-05 16:10:12', NULL),
(2, 'hanis 2', '2', '8765433', '5,87564534232', '6,75645323', 'Jl. llllll', '876543', 'xxxx', 'dis@gmail.com', '5', '2020-02-09 22:55:08', NULL),
(3, 'sss', '1', '6543', '5,4321', '7,543', 'Jl. Adaaja', '04567', 'hhhh', 'take@gmail.com', 'A', '2020-02-09 23:01:59', NULL),
(4, 'ghjk', '1', '74239047', '3,7890', '3,45678', 'jl. anime', '0888888', 'coba', 'cobasi@gmail.com', '1', '2020-02-09 23:04:21', NULL);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `pengaduan`
--
ALTER TABLE `pengaduan`
  ADD PRIMARY KEY (`id_pengaduan`);

--
-- Indeks untuk tabel `tb_admin`
--
ALTER TABLE `tb_admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indeks untuk tabel `tb_bayar`
--
ALTER TABLE `tb_bayar`
  ADD PRIMARY KEY (`id_bayar`);

--
-- Indeks untuk tabel `tb_jalur`
--
ALTER TABLE `tb_jalur`
  ADD PRIMARY KEY (`id_jalur`);

--
-- Indeks untuk tabel `tb_jenispelanggan`
--
ALTER TABLE `tb_jenispelanggan`
  ADD PRIMARY KEY (`id_jenisPelanggan`);

--
-- Indeks untuk tabel `tb_kendaraan`
--
ALTER TABLE `tb_kendaraan`
  ADD PRIMARY KEY (`id_kendaraan`);

--
-- Indeks untuk tabel `tb_petugas`
--
ALTER TABLE `tb_petugas`
  ADD PRIMARY KEY (`id_petugas`);

--
-- Indeks untuk tabel `tb_rpengajuan`
--
ALTER TABLE `tb_rpengajuan`
  ADD PRIMARY KEY (`id_pengajuan`);

--
-- Indeks untuk tabel `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `pengaduan`
--
ALTER TABLE `pengaduan`
  MODIFY `id_pengaduan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `tb_admin`
--
ALTER TABLE `tb_admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `tb_bayar`
--
ALTER TABLE `tb_bayar`
  MODIFY `id_bayar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `tb_jalur`
--
ALTER TABLE `tb_jalur`
  MODIFY `id_jalur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `tb_jenispelanggan`
--
ALTER TABLE `tb_jenispelanggan`
  MODIFY `id_jenisPelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `tb_kendaraan`
--
ALTER TABLE `tb_kendaraan`
  MODIFY `id_kendaraan` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `tb_petugas`
--
ALTER TABLE `tb_petugas`
  MODIFY `id_petugas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `tb_rpengajuan`
--
ALTER TABLE `tb_rpengajuan`
  MODIFY `id_pengajuan` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
