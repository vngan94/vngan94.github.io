﻿CREATE DATABASE TSLTWEB -- test2
DROP DATABASE TSLTWEB

CREATE DATABASE TS
DROP DATABASE TS

CREATE TABLE NHANVIEN(
	MANV INT IDENTITY(1,1)  PRIMARY KEY,
	HO NVARCHAR(10) NOT NULL,
	TEN NVARCHAR(50) NOT NULL,
	DIACHI NVARCHAR(50),
	SDT VARCHAR(11),
	MATK INT  -- moi tao nhan vien thi chua co tai khoan
)
INSERT INTO NHANVIEN VALUES (N'Võ', N'Ngân', 'PY', '103812973',  1)
INSERT INTO NHANVIEN VALUES (N'Quản lí', N'X', 'PY', '826216', 2)


CREATE TABLE NGUYENLIEU(
	MANL INT IDENTITY(1,1)  PRIMARY KEY,
	TENNL NVARCHAR(50) NOT NULL UNIQUE,
	SOLUONG FLOAT NOT NULL,
	DONVI NVARCHAR(20) NOT NULL,
)

CREATE TABLE TOPPING(
	MATOPPING INT IDENTITY(1,1)  PRIMARY KEY,
	TOPPING NVARCHAR(50) NOT NULL UNIQUE,
	GIA MONEY NOT NULL
)

INSERT INTO TOPPING VALUES(N'Trân châu',5000)
INSERT INTO TOPPING VALUES(N'Flan',5000)
INSERT INTO TOPPING VALUES(N'Sương sáo',7000)



CREATE TABLE NHACUNGCAP(
	MANCC INT IDENTITY(1,1)  PRIMARY KEY,
	TENNCC NVARCHAR(50) NOT NULL UNIQUE,
	SDT VARCHAR(11) NOT NULL,
	DIACHI NVARCHAR(20)
)

INSERT INTO NHACUNGCAP VALUES(N'Tân Phú','0123123123','TPHCM')
INSERT INTO NHACUNGCAP VALUES(N'Nhơn Trạch','08736428736',N'Đồng Nai')
INSERT INTO NHACUNGCAP VALUES(N'Long Hải','0128463466',N'Vũng Tàu')

CREATE TABLE DONHANG(
	MADH INT IDENTITY(1,1) PRIMARY KEY,
	MAHD INT NOT NULL unique,
	MATTDH INT NOT NULL
)

INSERT INTO DONHANG VALUES(1, 1)
INSERT INTO DONHANG VALUES(2, 3)
INSERT INTO DONHANG VALUES(3, 4)
INSERT INTO DONHANG VALUES(4, 1)


CREATE TABLE DONDATHANG(
	MADDH INT IDENTITY(1,1) PRIMARY KEY,
	THOIGIANLAP DATE NOT NULL,
	MANV INT NOT NULL,
	MATTDDH INT NOT NULL,
	MANCC INT NOT NULL
)

-- bo


CREATE TABLE TRASUA (
	MATS INT IDENTITY(1,1) PRIMARY KEY,
	TENTS NVARCHAR(50) NOT NULL UNIQUE,
	MOTA NTEXT NOT NULL,
	HINHANH VARCHAR(50) NOT NULL,
	TRANGTHAI INT NOT NULL,
	GIA MONEY NOT NULL
)

INSERT INTO TRASUA VALUES (N'Trân châu đường đen', N'ngọt nhưng ngon', 'Tra1.png', 1,20000)
INSERT INTO TRASUA VALUES (N'Đá xay trà xanh', N'mát nhưng không cay', 'Tra2.png', 1,25000)
INSERT INTO TRASUA VALUES (N'Socola lạnh', N'không đắng', 'ST1.png', 1,30000)

CREATE TABLE TRANGTHAIHOADON(
	MATTHD INT IDENTITY(1,1) PRIMARY KEY,
	TENTT NVARCHAR(40) NOT NULL UNIQUE
)
 
CREATE TABLE HOADON (
	MAHD INT  IDENTITY(1,1) PRIMARY KEY,
	THOIGIANLAP DATETIME NOT NULL DEFAULT GETDATE(),
	MANV INT NOT NULL,
	
	MADH INT, -- moi tao hoa don chua co madh
	MATTHD INT NOT NULL,
	THANHTIEN INT NOT NULL
	
)



INSERT INTO TRANGTHAIHOADON VALUES(N'Đã thanh toán')
INSERT INTO TRANGTHAIHOADON VALUES(N'Đã hủy')

CREATE TABLE TAIKHOAN(
	MATK INT IDENTITY(1,1)  PRIMARY KEY,
	TENTAIKHOAN VARCHAR(50) NOT NULL UNIQUE,
	PASSWORD VARCHAR(50) NOT NULL,
	TRANGTHAI INT NOT NULL,
	NGAYTHEM DATE NOT NULL,
	MAVT INT NOT NULL,
	MANV INT  NOT NULL unique 
)
CREATE TABLE VAITRO (
	MAVT INT IDENTITY(1,1) PRIMARY KEY,
	TENVAITRO NVARCHAR(50) NOT NULL
)

	
CREATE TABLE SIZE( --test
	MASIZE INT IDENTITY(1,1) PRIMARY KEY,
	TENSIZE CHAR(1) NOT NULL UNIQUE,
	TILE FLOAT NOT NULL
)

INSERT INTO SIZE VALUES ('S','0.8')
INSERT INTO SIZE VALUES ('M','1')
INSERT INTO SIZE VALUES ('L','1.3')


CREATE TABLE TRANGTHAIDONHANG(
	MATTDH INT IDENTITY(1,1)  PRIMARY KEY,
	TENTT NVARCHAR(20) NOT NULL UNIQUE
)
INSERT INTO TRANGTHAIDONHANG VALUES(N'Chờ xác nhận')
INSERT INTO TRANGTHAIDONHANG VALUES(N'Đang pha chế')
INSERT INTO TRANGTHAIDONHANG VALUES(N'Đang giao')
INSERT INTO TRANGTHAIDONHANG VALUES(N'Đã giao')
INSERT INTO TRANGTHAIDONHANG VALUES(N'Đã hủy')

CREATE TABLE TRANGTHAIDONDATHANG(
	MATTDDH INT IDENTITY(1,1)  PRIMARY KEY,
	TENTT NVARCHAR(20) NOT NULL UNIQUE
)

INSERT INTO TRANGTHAIDONDATHANG VALUES(N'Chờ xác nhận')
INSERT INTO TRANGTHAIDONDATHANG VALUES(N'Đang giao')
INSERT INTO TRANGTHAIDONDATHANG VALUES(N'Nhận hàng')
INSERT INTO TRANGTHAIDONDATHANG VALUES(N'Đã giao')
INSERT INTO TRANGTHAIDONDATHANG VALUES(N'Đã hủy')

CREATE TABLE CHITIETSIZE(
	MACTS INT IDENTITY(1,1) PRIMARY KEY,
	MASIZE INT NOT NULL ,
	MATS INT NOT NULL,
	UNIQUE (MASIZE, MATS)
)

INSERT INTO CHITIETSIZE VALUES (2,1)
INSERT INTO CHITIETSIZE VALUES (1,1)
INSERT INTO CHITIETSIZE VALUES (3,1)
INSERT INTO CHITIETSIZE VALUES (2,2)
INSERT INTO CHITIETSIZE VALUES (2,3)


CREATE TABLE CONGTHUC (
	MACT INT IDENTITY(1,1)  PRIMARY KEY,
	MATS INT NOT NULL,
	MANL INT NOT NULL,
	LIEULUONG FLOAT NOT NULL,
	DONVI NVARCHAR(30) NOT NULL,
	GHICHU TEXT,
	UNIQUE (MATS, MANL)
)
INSERT INTO NGUYENLIEU VALUES (N'Bột Matcha', 3, 'kg')
INSERT INTO NGUYENLIEU VALUES (N'Đường', 50, 'kg')
INSERT INTO NGUYENLIEU VALUES (N'Sữa tươi', 6, 'lit')



INSERT INTO CONGTHUC VALUES (1,1,30, 'gam','')
INSERT INTO CONGTHUC VALUES (1,2,50, 'gam','')
INSERT INTO CONGTHUC VALUES (1,3, 20, 'mil', '')
INSERT INTO CONGTHUC VALUES (2,1,60,'gam','')
INSERT INTO CONGTHUC VALUES (2,2,10, 'gam','')
INSERT INTO CONGTHUC VALUES (2,3, 20, 'mil', '')
INSERT INTO CONGTHUC VALUES (3,1,30, 'gam','')
INSERT INTO CONGTHUC VALUES (3,3, 80, 'mil', '')


CREATE TABLE CHITIETCUNGCAP(
	MACTCC INT IDENTITY(1,1) PRIMARY KEY,
	MANCC INT NOT NULL,
	MANL INT NOT NULL,
	DONGIA INT  NOT NULL,
	DONVI NVARCHAR(30)  NOT NULL,
	UNIQUE (MANCC , MANL)
)

INSERT INTO CHITIETCUNGCAP VALUES(1,1,10000, 'kg')
INSERT INTO CHITIETCUNGCAP VALUES(1,2,15000, 'kg')
INSERT INTO CHITIETCUNGCAP VALUES(1,3,16000, 'lit')
INSERT INTO CHITIETCUNGCAP VALUES(2,1,9000, 'kg')
INSERT INTO CHITIETCUNGCAP VALUES(2,2,15000, 'kg')
INSERT INTO CHITIETCUNGCAP VALUES(3,1,20000, 'kg')

CREATE TABLE CHITIETDONDATHANG(
	MACTDDH INT IDENTITY(1,1)  PRIMARY KEY,
	MADDH INT NOT NULL  ,
	MANL INT NOT NULL,
	DONVI NVARCHAR(30)  NOT NULL,
	SOLUONG INT  NOT NULL,
	GHICHU TEXT,
	UNIQUE(MADDH, MANL)
)

INSERT INTO CHITIETDONDATHANG VALUES('1','1', 'kg', 10, '')
INSERT INTO CHITIETDONDATHANG VALUES('1','2', 'kg', 5, '')
INSERT INTO CHITIETDONDATHANG VALUES('2','1', 'kg', 5, '')
INSERT INTO CHITIETDONDATHANG VALUES('2','2', 'kg', 7, '')
INSERT INTO CHITIETDONDATHANG VALUES('3','3', 'lit', 7, '')
INSERT INTO CHITIETDONDATHANG VALUES('4','1', 'kg', 3, '')

CREATE TABLE CHITIETHOADON (
	MACTHD INT  IDENTITY(1,1) PRIMARY KEY,
	MAHD INT NOT NULL,
	MATS INT,
	MATOPPING INT,
	MASIZE INT,
	SOLUONG INT NOT NULL,
	GHICHU TEXT,
	UNIQUE(MAHD, MASIZE, MATS, MATOPPING)
)


INSERT INTO CHITIETHOADON VALUES(1,1,NULL,2,1,'') -- DE TRONG THI GHI NULL, NO LA KHOA NGOAI NEN KHONG DE '' DUOC
INSERT INTO CHITIETHOADON VALUES(1,2,NULL,3,2,'')
INSERT INTO CHITIETHOADON VALUES(1,NULL,2,NULL,1,'')
INSERT INTO CHITIETHOADON VALUES(2,2,NULL,2,1,'')
INSERT INTO CHITIETHOADON VALUES(3,1,NULL,2,1,'')
INSERT INTO CHITIETHOADON VALUES(3,NULL,2,NULL,1,'')
INSERT INTO CHITIETHOADON VALUES(3,NULL,1,NULL,2,'')
INSERT INTO CHITIETHOADON VALUES(4,1,NULL,3,1,'')
INSERT INTO CHITIETHOADON VALUES(4,NULL,2,NULL,1,'')



INSERT INTO VAITRO VALUES(N'Nhân viên')
INSERT INTO VAITRO VALUES(N'Quản lí')



INSERT INTO TAIKHOAN VALUES('tnnhan97@gmail.com', '123', 1, '4/23/2012', 1, 1)
INSERT INTO TAIKHOAN VALUES('n19dccn119@student.ptithcm.edu.vn', '123', 1, '4/23/2012', 2, 2)



INSERT INTO DONDATHANG VALUES ('4/4/2021','1','1','1')
INSERT INTO DONDATHANG VALUES ('10/4/2021','1','2','1')
INSERT INTO DONDATHANG VALUES ('10/4/2021','1','3','1')
INSERT INTO DONDATHANG VALUES ('11/4/2022','2','4','2')

INSERT INTO HOADON VALUES(GETDATE(), 1, 1, 1, 90000)
INSERT INTO HOADON VALUES(GETDATE(), 1, 2, 1, 25000)
INSERT INTO HOADON VALUES(GETDATE(), 1, 3, 1, 35000)
INSERT INTO HOADON VALUES(GETDATE(), 1, 4, 2, 31000)


ALTER TABLE NHANVIEN
	ADD FOREIGN KEY (MATK) REFERENCES TAIKHOAN(MATK)  
ALTER TABLE DONHANG
	ADD FOREIGN KEY (MAHD) REFERENCES HOADON(MAHD) 
ALTER TABLE DONHANG
	ADD FOREIGN KEY (MATTDH) REFERENCES TRANGTHAIDONHANG(MATTDH)  
ALTER TABLE HOADON
	ADD FOREIGN KEY (MATTHD) REFERENCES TRANGTHAIHOADON(MATTHD)  
ALTER TABLE DONDATHANG
	ADD FOREIGN KEY (MATTDDH) REFERENCES TRANGTHAIDONDATHANG(MATTDDH) 
ALTER TABLE DONDATHANG
	ADD FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV) 
ALTER TABLE DONDATHANG
	ADD FOREIGN KEY (MANCC) REFERENCES NHACUNGCAP(MANCC) 
 
ALTER TABLE HOADON
	ADD FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV) 
ALTER TABLE HOADON
	ADD FOREIGN KEY (MADH) REFERENCES DONHANG(MADH)


 
ALTER TABLE CHITIETSIZE
	ADD FOREIGN KEY (MATS) REFERENCES TRASUA(MATS)
ALTER TABLE CHITIETSIZE
	ADD FOREIGN KEY (MASIZE) REFERENCES SIZE(MASIZE)
ALTER TABLE CONGTHUC
	ADD FOREIGN KEY (MATS) REFERENCES TRASUA(MATS) 
ALTER TABLE CONGTHUC
	ADD FOREIGN KEY (MANL) REFERENCES NGUYENLIEU(MANL) 

ALTER TABLE CHITIETCUNGCAP
	ADD FOREIGN KEY (MANL) REFERENCES NGUYENLIEU(MANL)
ALTER TABLE CHITIETCUNGCAP
	ADD FOREIGN KEY (MANCC) REFERENCES NHACUNGCAP(MANCC)
ALTER TABLE CHITIETDONDATHANG
	ADD FOREIGN KEY (MANL) REFERENCES NGUYENLIEU(MANL)
ALTER TABLE CHITIETDONDATHANG
	ADD FOREIGN KEY (MADDH) REFERENCES DONDATHANG(MADDH) 
ALTER TABLE CHITIETHOADON
	ADD FOREIGN KEY (MAHD) REFERENCES HOADON(MAHD)
ALTER TABLE CHITIETHOADON
	ADD FOREIGN KEY (MASIZE) REFERENCES SIZE(MASIZE) 
ALTER TABLE CHITIETHOADON 
	ADD FOREIGN KEY (MATS) REFERENCES TRASUA(MATS)
ALTER TABLE CHITIETHOADON 
	ADD FOREIGN KEY (MATOPPING) REFERENCES TOPPING(MATOPPING)
ALTER TABLE TAIKHOAN
	ADD FOREIGN KEY(MAVT) REFERENCES VAITRO(MAVT) 
ALTER TABLE TAIKHOAN
	ADD FOREIGN KEY(MANV) REFERENCES NHANVIEN(MANV)


