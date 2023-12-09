-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 18, 2022 lúc 06:03 PM
-- Phiên bản máy phục vụ: 10.4.21-MariaDB
-- Phiên bản PHP: 7.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlyoto`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cthd`
--

CREATE TABLE `cthd` (
  `mahd` int(11) NOT NULL,
  `masp` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `dongia` bigint(11) NOT NULL,
  `thanhtien` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `cthd`
--

INSERT INTO `cthd` (`mahd`, `masp`, `soluong`, `dongia`, `thanhtien`) VALUES
(19, 15, 1, 1900000000, 1900000000),
(19, 16, 1, 1000000000, 1000000000),
(20, 19, 1, 1200000000, 1200000000),
(20, 20, 2, 1400000000, 2800000000),
(21, 46, 1, 1600000000, 1600000000),
(21, 47, 1, 459000000, 459000000),
(24, 50, 1, 60000000000, 60000000000),
(24, 51, 1, 16000000000, 16000000000),
(25, 15, 1, 1900000000, 1900000000),
(25, 49, 1, 10829000000, 10829000000),
(26, 46, 1, 1600000000, 1600000000),
(26, 47, 1, 459000000, 459000000),
(27, 15, 1, 1900000000, 1900000000),
(27, 16, 1, 1000000000, 1000000000),
(27, 18, 1, 1100000000, 1100000000),
(28, 19, 1, 1200000000, 1200000000),
(28, 20, 1, 1400000000, 1400000000),
(29, 33, 6, 900000, 5400000),
(29, 49, 1, 10829000000, 10829000000),
(30, 15, 1, 1900000000, 1900000000),
(30, 16, 1, 1000000000, 1000000000),
(31, 31, 2, 2000000, 4000000),
(31, 33, 1, 900000, 900000),
(32, 18, 1, 1100000000, 1100000000),
(32, 19, 1, 1200000000, 1200000000),
(33, 18, 1, 1100000000, 1100000000),
(33, 19, 1, 1200000000, 1200000000),
(34, 18, 3, 1100000000, 3300000000),
(34, 31, 5, 2000000, 10000000),
(35, 33, 5, 900000, 4500000),
(35, 37, 1, 1000000000, 1000000000),
(36, 18, 2, 1100000000, 2200000000),
(36, 31, 1, 2000000, 2000000),
(37, 18, 2, 1100000000, 2200000000),
(37, 31, 2, 2000000, 4000000),
(38, 31, 3, 2000000, 6000000),
(38, 33, 3, 900000, 2700000),
(39, 20, 1, 1400000000, 1400000000),
(39, 31, 2, 2000000, 4000000),
(39, 33, 5, 900000, 4500000),
(40, 31, 5, 2000000, 10000000),
(40, 33, 3, 900000, 2700000),
(41, 18, 3, 1100000000, 3300000000),
(41, 33, 3, 900000, 2700000),
(41, 51, 1, 16000000000, 16000000000),
(42, 41, 1, 500000000, 500000000),
(42, 42, 1, 460000000, 460000000),
(42, 49, 1, 10829000000, 10829000000),
(43, 18, 4, 1100000000, 4400000000),
(43, 19, 3, 1200000000, 3600000000),
(44, 18, 5, 1100000000, 5500000000),
(44, 31, 1, 2000000, 2000000),
(44, 48, 3, 780000000, 2340000000),
(45, 15, 2, 1900000000, 3800000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctpn`
--

CREATE TABLE `ctpn` (
  `mapn` int(11) NOT NULL,
  `masp` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `dongia` bigint(11) NOT NULL,
  `thanhtien` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `ctpn`
--

INSERT INTO `ctpn` (`mapn`, `masp`, `soluong`, `dongia`, `thanhtien`) VALUES
(13, 40, 4, 500000000, 2000000000),
(13, 41, 3, 300000000, 900000000),
(14, 39, 4, 500000000, 2000000000),
(15, 42, 5, 600000000, 3000000000),
(16, 43, 4, 400000000, 1600000000),
(17, 50, 2, 55000000000, 110000000000),
(17, 51, 2, 14000000000, 28000000000),
(18, 31, 5, 1000000, 5000000),
(18, 49, 3, 9000000000, 27000000000),
(19, 33, 13, 500000, 6500000),
(19, 44, 3, 900000000, 2700000000),
(20, 31, 2, 1000000, 2000000),
(20, 33, 3, 500000, 1500000),
(20, 45, 3, 900000000, 2700000000),
(21, 31, 5, 1000000, 5000000),
(21, 42, 2, 350000000, 700000000),
(22, 31, 2, 1000000, 2000000),
(22, 46, 3, 1500000000, 4500000000),
(22, 47, 4, 400000000, 1600000000),
(23, 31, 4, 1000000, 4000000),
(23, 42, 3, 400000000, 1200000000),
(23, 48, 6, 650000000, 3900000000),
(24, 33, 5, 500000, 2500000),
(24, 50, 1, 55000000000, 55000000000),
(24, 51, 2, 14000000000, 28000000000),
(25, 40, 2, 600000000, 1200000000),
(25, 41, 3, 300000000, 900000000),
(26, 15, 2, 1800000000, 3600000000),
(26, 16, 3, 900000000, 2700000000),
(27, 31, 5, 1000000, 5000000),
(27, 39, 3, 500000000, 1500000000),
(28, 33, 4, 500000000, 2000000000),
(28, 49, 2, 9000000000, 18000000000),
(29, 46, 2, 400000000, 800000000),
(29, 47, 3, 1500000000, 4500000000),
(30, 20, 3, 1300000000, 3900000000),
(30, 36, 3, 1400000000, 4200000000),
(31, 42, 3, 400000000, 1200000000),
(31, 48, 2, 600000000, 1200000000),
(32, 40, 3, 550000000, 1650000000),
(32, 41, 2, 400000000, 800000000),
(33, 31, 2, 1000000, 2000000),
(33, 33, 3, 500000, 1500000),
(34, 52, 1, 100000000, 100000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `mahd` int(11) NOT NULL,
  `makh` int(5) NOT NULL,
  `manv` int(11) DEFAULT NULL,
  `ngaylap` datetime NOT NULL DEFAULT current_timestamp(),
  `tongtien` bigint(11) NOT NULL,
  `ghichu` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`mahd`, `makh`, `manv`, `ngaylap`, `tongtien`, `ghichu`) VALUES
(19, 12, 8, '2020-06-15 01:52:36', 2900000000, ''),
(20, 13, 8, '2020-06-15 01:55:02', 4000000000, ''),
(21, 14, 8, '2020-06-15 01:56:08', 2059000000, ''),
(24, 15, 8, '2020-06-15 02:02:25', 76000000000, ''),
(25, 16, 8, '2020-06-15 02:02:53', 12729000000, ''),
(26, 17, 8, '2020-06-15 02:03:08', 2059000000, ''),
(27, 12, 8, '2020-06-15 02:03:21', 4000000000, ''),
(28, 24, 8, '2021-06-15 02:03:31', 2600000000, ''),
(29, 12, 8, '2021-06-15 02:03:57', 10834400000, ''),
(30, 25, 8, '2021-06-15 02:04:21', 2900000000, ''),
(31, 26, 8, '2021-06-15 02:04:48', 4900000, ''),
(32, 27, 8, '2021-06-15 02:05:08', 2300000000, ''),
(33, 28, 8, '2021-06-15 02:05:25', 2300000000, ''),
(34, 30, 8, '2021-06-15 02:05:48', 3310000000, ''),
(35, 29, 8, '2021-06-15 02:06:26', 1004500000, ''),
(36, 31, 8, '2022-01-15 02:06:44', 2202000000, ''),
(37, 32, 8, '2022-02-15 02:07:00', 2204000000, ''),
(38, 33, 8, '2022-03-15 02:07:16', 8700000, ''),
(39, 36, 8, '2022-04-15 02:07:38', 1408500000, ''),
(40, 37, 8, '2022-05-15 02:07:54', 12700000, ''),
(41, 35, 8, '2022-04-15 02:08:21', 19302700000, ''),
(42, 34, 8, '2022-06-15 02:08:42', 11789000000, ''),
(43, 34, 8, '2022-03-15 02:26:23', 8000000000, ''),
(44, 36, 8, '2022-05-15 02:26:54', 7842000000, ''),
(45, 38, 1, '2022-06-15 11:07:18', 3800000000, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `makh` int(5) NOT NULL,
  `hoten` varchar(200) NOT NULL,
  `dienthoai` varchar(10) NOT NULL,
  `diachi` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`makh`, `hoten`, `dienthoai`, `diachi`) VALUES
(12, 'Nguyễn Văn A', '0321234567', 'Thủ Đức, TPHCM'),
(13, 'Trần Văn B', '0331234567', 'Quận 1, TPHCM'),
(14, 'Nguyễn Văn C', '0341234567', 'Quận 3, TPHCM'),
(15, 'Đinh Văn D', '0351234567', 'Quận 4, TPHCM'),
(16, 'Lê Văn E', '0361234567', 'Quận 5, TPHCM'),
(17, 'Lý Văn F', '0371234567', 'Quận 6, TPHCM'),
(24, 'Thanh', '0331234567', 'TPHCM'),
(25, 'Thanh Hoang', '0376318536', 'TPHCM'),
(26, 'Thanh Hoang', '0901234567', 'TPHCM'),
(27, 'Hoang Kim Thanh', '0341234567', 'TPHCM'),
(28, 'Test', '0331234567', 'TPHCM'),
(29, 'Test Test', '0334567899', 'Test test'),
(30, 'Test Test Test', '0371234567', 'Test test test'),
(31, 'Trần Công Đức', '0341234567', 'HN'),
(32, 'Trần Thiện Nhân', '0908763461', 'Đà Nẵng'),
(33, 'Lê Thiện Hiếu', '0341239834', 'Tân Bình,TPHCM'),
(34, 'Nguyễn Thanh Tùng', '0909999999', 'Thái Bình'),
(35, 'Kim Te Hun', '0341234326', 'Korean'),
(36, 'Trần Đức Bo', '0901238769', 'Quận 9,TPHCM'),
(37, 'Nguyễn Chí Thiện', '0349443217', 'Quận 10,TPHCM'),
(38, 'Nguyễn Văn D', '0334567891', 'TP HCM');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `mancc` int(11) NOT NULL,
  `tenncc` varchar(200) NOT NULL,
  `diachi` varchar(200) NOT NULL,
  `dienthoai` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`mancc`, `tenncc`, `diachi`, `dienthoai`) VALUES
(2, 'Mercedes', 'UK', '1234567'),
(14, 'BMW', 'UA', '801234567'),
(22, 'Toyota', 'JP', '0371234567'),
(23, 'Lamborghini', 'UK', '0901234567'),
(24, 'Huyndai', 'HQ', '0907654321'),
(25, 'Mitsubishi', 'JP', '0341234987'),
(26, 'KIA', 'HQ', '0351234569'),
(27, 'VINFAST', 'VN', '0904561231'),
(28, 'Mazda', 'GermanyJP', '0381234567'),
(29, 'Honda', 'JP', '0903567811'),
(30, 'ABC', 'TP HCM', '0336456789');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manv` int(11) NOT NULL,
  `hoten` varchar(100) NOT NULL,
  `taikhoan` varchar(100) NOT NULL,
  `matkhau` varchar(100) NOT NULL,
  `chucvu` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`manv`, `hoten`, `taikhoan`, `matkhau`, `chucvu`) VALUES
(1, 'Admin', 'admin', 'admin', 'Quản trị'),
(3, 'Thanh', 'NhanVien1', 'NhanVien1', 'Nhân viên'),
(4, 'Hoàng Kim Thành', 'QuanLy1', 'QuanLy1', 'Quản lý'),
(7, 'Nguyễn Văn A', 'quanly2', 'Quanly2', 'Quản lý'),
(8, 'Trần Khánh Quang Bảo', 'bao123', 'Bao123', 'Nhân viên'),
(9, 'Ngô Hữu Nhân', 'nhan123', 'Nhan123', 'Nhân viên'),
(10, 'Nguyễn Công Phú', 'phu123', 'Phu123', 'Nhân viên'),
(11, 'Nguyễn Tấn C', 'tanc123', 'Tanc123', 'Nhân viên'),
(12, 'Trần Đức Bo', 'bo123456', 'Bo123456', 'Nhân viên'),
(13, 'Sơn Tùng', 'sontung123', 'Sontung123', 'Nhân viên');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `mapn` int(11) NOT NULL,
  `mancc` int(11) NOT NULL,
  `manv` int(11) NOT NULL,
  `ngaylap` datetime NOT NULL DEFAULT current_timestamp(),
  `tongtien` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`mapn`, `mancc`, `manv`, `ngaylap`, `tongtien`) VALUES
(13, 22, 7, '2020-06-15 01:22:37', 2900000000),
(14, 27, 7, '2020-06-15 01:24:38', 2000000000),
(15, 24, 7, '2020-06-15 01:25:55', 3000000000),
(16, 29, 7, '2020-06-15 01:27:12', 1600000000),
(17, 23, 7, '2020-06-15 01:28:32', 138000000000),
(18, 2, 7, '2020-06-15 01:29:49', 27005000000),
(19, 2, 7, '2020-06-15 01:30:46', 2706500000),
(20, 2, 7, '2020-06-15 01:31:42', 2703500000),
(21, 2, 7, '2020-06-15 01:32:59', 705000000),
(22, 2, 7, '2020-06-15 01:34:04', 6102000000),
(23, 24, 7, '2020-06-15 01:35:50', 5104000000),
(24, 23, 7, '2020-06-15 01:37:29', 83002500000),
(25, 22, 7, '2020-06-15 01:38:42', 2100000000),
(26, 22, 7, '2020-06-15 01:39:54', 6300000000),
(27, 22, 7, '2020-06-15 01:40:59', 1505000000),
(28, 22, 7, '2020-06-15 01:41:33', 20000000000),
(29, 2, 7, '2020-06-15 01:42:44', 5300000000),
(30, 14, 7, '2020-06-15 01:44:21', 8100000000),
(31, 2, 7, '2020-06-15 01:45:28', 2400000000),
(32, 2, 7, '2020-06-15 01:47:06', 2450000000),
(33, 2, 7, '2020-06-15 01:47:30', 3500000),
(34, 30, 1, '2022-06-15 11:05:35', 100000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quyen`
--

CREATE TABLE `quyen` (
  `chucvu` varchar(100) NOT NULL,
  `tenloaiquanly` varchar(100) NOT NULL,
  `them` int(1) NOT NULL DEFAULT 1,
  `xem` int(1) NOT NULL DEFAULT 1,
  `sua` int(1) NOT NULL DEFAULT 1,
  `xoa` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `quyen`
--

INSERT INTO `quyen` (`chucvu`, `tenloaiquanly`, `them`, `xem`, `sua`, `xoa`) VALUES
('Nhân viên', 'Quản lý hóa đơn', 1, 1, 0, 0),
('Nhân viên', 'Quản lý khách hàng', 1, 1, 1, 1),
('Nhân viên', 'Quản lý nhà cung cấp', 0, 0, 0, 0),
('Nhân viên', 'Quản lý nhân viên', 0, 0, 0, 0),
('Nhân viên', 'Quản lý nhập hàng', 0, 0, 0, 0),
('Nhân viên', 'Quản lý sản phẩm', 0, 1, 1, 0),
('Nhân viên', 'Thống kê', 0, 0, 0, 0),
('Quản lý', 'Quản lý hóa đơn', 1, 1, 1, 1),
('Quản lý', 'Quản lý khách hàng', 1, 1, 1, 1),
('Quản lý', 'Quản lý nhà cung cấp', 0, 1, 0, 0),
('Quản lý', 'Quản lý nhân viên', 1, 1, 1, 1),
('Quản lý', 'Quản lý nhập hàng', 1, 1, 1, 1),
('Quản lý', 'Quản lý sản phẩm', 1, 1, 1, 1),
('Quản lý', 'Thống kê', 1, 1, 1, 1),
('Quản trị', 'Quản lý hóa đơn', 1, 1, 1, 1),
('Quản trị', 'Quản lý khách hàng', 1, 1, 1, 1),
('Quản trị', 'Quản lý nhà cung cấp', 1, 1, 1, 1),
('Quản trị', 'Quản lý nhân viên', 1, 1, 1, 1),
('Quản trị', 'Quản lý nhập hàng', 1, 1, 1, 1),
('Quản trị', 'Quản lý sản phẩm', 1, 1, 1, 1),
('Quản trị', 'Thống kê', 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `masp` int(11) NOT NULL,
  `loai` varchar(50) NOT NULL,
  `ten` varchar(200) NOT NULL,
  `soluong` int(11) DEFAULT NULL,
  `gia` bigint(11) NOT NULL,
  `hanbaohanh` int(11) NOT NULL,
  `anh` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`masp`, `loai`, `ten`, `soluong`, `gia`, `hanbaohanh`, `anh`) VALUES
(15, 'Ô tô', 'Lexus ES250', 0, 1900000000, 4, 'lexus-es250.png'),
(16, 'Ô tô', 'Lexus RX300', 6, 1000000000, 5, 'lexus-rx300.jpg'),
(18, 'Ô tô', 'Lexus RX350', 6, 1100000000, 3, 'lexus-rx350.png'),
(19, 'Ô tô', 'BMW X5', 9, 1200000000, 5, 'bmw-x5.jpg'),
(20, 'Ô tô', 'BMW X7', 6, 1400000000, 5, 'bmw-x7.jpg'),
(31, 'Phụ tùng', 'Mâm ép', 12, 2000000, 0, 'mam-ep.png'),
(33, 'Phụ tùng', 'Cao su chân máy trước', 10, 900000, 0, 'cao-su-chan-may-truoc.png'),
(36, 'Ô tô', 'BMW X6', 3, 2000000000, 3, 'bmw-x7.jpg'),
(37, 'Ô tô', 'BMW 685654', 9, 1000000000, 3, 'bmw-x7.jpg'),
(39, 'Ô tô', 'Vinfast Fadil', 7, 700000000, 3, 'vinfast-fadil.png'),
(40, 'Ô tô', 'Toyota Corolla Cross', 9, 800000000, 4, 'toyota-corolla-cross.png'),
(41, 'Ô tô', 'Toyota Vios', 7, 500000000, 3, 'toyota-vios.png'),
(42, 'Ô tô', 'Hyundai Accent', 7, 460000000, 3, 'hyundai-accent.png'),
(43, 'Ô tô', 'Honda City', 4, 545000000, 3, 'honda-city.jpg'),
(44, 'Ô tô', 'Mitsubishi Xpander', 3, 1100000000, 3, 'mitsubishi-xpander.jpg'),
(45, 'Ô tô', 'Mazda CX-5', 3, 1059000000, 3, 'mazda-cx-5.jpg'),
(46, 'Ô tô', 'Kia K3', 3, 1600000000, 3, 'kia-k3.png'),
(47, 'Ô tô', 'Kia Seltos', 5, 459000000, 3, 'kia-seltos.jpg'),
(48, 'Ô tô', 'Hyundai Santa Fe', 5, 780000000, 4, 'hyundai-santa-fe.jpg'),
(49, 'Ô tô', 'Mercedes-Benz G-Class', 1, 10829000000, 5, 'mercedes-benz-g-class.jpg'),
(50, 'Ô tô', 'Lamborghini Aventador SVJ', 1, 60000000000, 5, 'lamborghini-aventador-SVJ.jpg'),
(51, 'Ô tô', 'Lamborghini Huracan LP610-4', 1, 16000000000, 5, 'lamborghini-huracan-LP610-4.jpg'),
(52, 'Ô tô', 'BMW X6', 1, 2000000000, 3, 'bmw-x7.jpg');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `cthd`
--
ALTER TABLE `cthd`
  ADD PRIMARY KEY (`mahd`,`masp`),
  ADD KEY `masp` (`masp`);

--
-- Chỉ mục cho bảng `ctpn`
--
ALTER TABLE `ctpn`
  ADD PRIMARY KEY (`mapn`,`masp`),
  ADD KEY `masp` (`masp`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`mahd`),
  ADD KEY `makh` (`makh`),
  ADD KEY `manv` (`manv`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makh`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`mancc`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manv`),
  ADD KEY `chucvu` (`chucvu`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`mapn`),
  ADD KEY `mancc` (`mancc`),
  ADD KEY `manv` (`manv`);

--
-- Chỉ mục cho bảng `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`chucvu`,`tenloaiquanly`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`masp`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `mahd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `makh` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `mancc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `manv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `mapn` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `masp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `cthd`
--
ALTER TABLE `cthd`
  ADD CONSTRAINT `cthd_ibfk_1` FOREIGN KEY (`mahd`) REFERENCES `hoadon` (`mahd`),
  ADD CONSTRAINT `cthd_ibfk_2` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`);

--
-- Các ràng buộc cho bảng `ctpn`
--
ALTER TABLE `ctpn`
  ADD CONSTRAINT `ctpn_ibfk_1` FOREIGN KEY (`mapn`) REFERENCES `phieunhap` (`mapn`),
  ADD CONSTRAINT `ctpn_ibfk_2` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_3` FOREIGN KEY (`makh`) REFERENCES `khachhang` (`makh`),
  ADD CONSTRAINT `hoadon_ibfk_4` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`);

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `nhanvien_ibfk_1` FOREIGN KEY (`chucvu`) REFERENCES `quyen` (`chucvu`);

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`mancc`) REFERENCES `nhacungcap` (`mancc`),
  ADD CONSTRAINT `phieunhap_ibfk_3` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
