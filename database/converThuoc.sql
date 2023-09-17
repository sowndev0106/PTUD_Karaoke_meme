/****** Script for SelectTopNRows command from SSMS  ******/
use KaraokeMeMe
go
/****** Script for SelectTopNRows command from SSMS  ******/
insert into  [KaraokeMeMe].[dbo].[DiaChi]([maDC]
      ,[phuongXa]
      ,[quanHuyen]
      ,[tinhTP])
select replace( [maDC],'DC-00','DC')
      ,[phuongXa]
      ,[quanHuyen]
      ,[tinhTP] from [QLThuoc].[dbo].[DiaChi]

go 
insert into [KaraokeMeMe].[dbo].[KhachHang]( [maKH]
      ,[gioiTinh]
      ,[hoTen]
      ,[ngaySinh]
      ,[soDienThoai]
      ,[maDC])
   SELECT replace( [maKhachHang],'000','')
      ,[gioiTinh]
	  ,[tenKhachHang],
     '2001-01-01 00:00:00.0000000'
      ,[soDienThoai]
      ,replace( [maDC],'DC-00','DC')
  FROM [Thuoc].[dbo].[KhachHang]

  go
  insert into LoaiNhanVien values('LNV001','Quản lý')

  go
   insert into LoaiNhanVien values('LNV002','Nhân viên thu ngân')
go

/****** Script for SelectTopNRows command from SSMS  ******/
insert into [KaraokeMeMe].[dbo].[NhanVien]( 
		[maNV]
      ,[gioiTinh]
      ,[hoTen]
      ,[ngaySinh]
      ,[password]
      ,[soCMND]
      ,[soDienThoai]
      ,[trangThaiLamViec]
      ,[maDC]
      ,[maLNV])
SELECT 
		
	replace( [maNhanVien],'NVAA00','NVAA')
      ,[gioiTinh]
	    ,[tenNhanVien]

		   , '2001-01-01 00:00:00.0000000'
      ,'123456'
	    ,[cmnd]
      ,[soDienThoaiNV]

      ,[trangThaiLamViec]
      ,replace( [maDC],'DC-00','DC'),'LNV002'
  FROM [Thuoc].[dbo].[NhanVien]
  