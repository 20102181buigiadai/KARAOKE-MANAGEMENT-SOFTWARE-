package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiNhanVien;
import entity.NhanVien;

public class DAO_NhanVien {
	private DAO_LoaiNhanVien dao_LoaiNhanVien = new DAO_LoaiNhanVien();

	public DAO_NhanVien() {
		ConnectDB.getInstance().connect();
	}

	public ArrayList<NhanVien> getALLNhanVien() {
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from NhanVien");
			while (rs.next()) {
				byte[] hinhAnh = rs.getBytes("hinhAnh");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				LoaiNhanVien loaiNhanVien = dao_LoaiNhanVien.getLoaiNhanVienTheoMa(rs.getString("maLoaiNV"));
				ds.add(new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), gioiTinh,
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("cmnd"), rs.getString("diaChi"),
						rs.getString("soDT"), loaiNhanVien, hinhAnh));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public ArrayList<NhanVien> getALLNhanVienKhongCoADMIN() {
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from NhanVien where maNV NOT LIKE 'admin'");
			while (rs.next()) {
				byte[] hinhAnh = rs.getBytes("hinhAnh");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				LoaiNhanVien loaiNhanVien = dao_LoaiNhanVien.getLoaiNhanVienTheoMa(rs.getString("maLoaiNV"));
				ds.add(new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), gioiTinh,
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("cmnd"), rs.getString("diaChi"),
						rs.getString("soDT"), loaiNhanVien, hinhAnh));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public NhanVien getNhanVienTheoMa(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		NhanVien nhanVien = null;
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from NhanVien where maNV = ?");
			pstm.setString(1, ma);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				byte[] hinhAnh = rs.getBytes("hinhAnh");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				LoaiNhanVien loaiNhanVien = dao_LoaiNhanVien.getLoaiNhanVienTheoMa(rs.getString("maLoaiNV"));
				nhanVien = new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), gioiTinh,
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("cmnd"), rs.getString("diaChi"),
						rs.getString("soDT"), loaiNhanVien, hinhAnh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return nhanVien;
	}

	public boolean addNhanVien(NhanVien nhanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("insert into NhanVien values(?,?,?,?,?,?,?,?,?,?)");
			pstm.setString(1, nhanVien.getMaNhanVien());
			pstm.setString(2, nhanVien.getTenNhanVien());
			pstm.setDate(3, Date.valueOf(nhanVien.getNgaySinh()));
			pstm.setBoolean(4, nhanVien.getGioiTinh());
			pstm.setString(5, nhanVien.getCmnd());
			pstm.setString(6, nhanVien.getDiaChi());
			pstm.setString(7, nhanVien.getSoDienThoai());
			pstm.setString(8, nhanVien.getLoaiNhanVien().getMaLoai());
			pstm.setBytes(9, nhanVien.getHinhAnh());
			pstm.setString(10, nhanVien.getEmail());
			n = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean deleteNhanVienTheoMa(String maNV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("delete from NhanVien where maNV = ?");
			pstm.setString(1, maNV);
			n = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean updateNhanVien(NhanVien nhanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement(
					"update NhanVien set tenNV = ?, gioiTinh = ?, ngaySinh = ?, cmnd = ?, diaChi = ?, soDT = ?, maLoaiNV = ? , hinhAnh =? , gmail=? where maNV = ?");
			pstm.setString(1, nhanVien.getTenNhanVien());
			pstm.setBoolean(2, nhanVien.getGioiTinh());
			pstm.setDate(3, Date.valueOf(nhanVien.getNgaySinh()));
			pstm.setString(4, nhanVien.getCmnd());
			pstm.setString(5, nhanVien.getDiaChi());
			pstm.setString(6, nhanVien.getSoDienThoai());
			pstm.setString(7, nhanVien.getLoaiNhanVien().getMaLoai());
			pstm.setBytes(8, nhanVien.getHinhAnh());
			pstm.setString(9, nhanVien.getEmail());
			pstm.setString(10, nhanVien.getMaNhanVien());
			n = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean updateTTNhanVien(NhanVien nhanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement(
					"update NhanVien set tenNV = ?, gioiTinh = ?, ngaySinh = ?, cmnd = ?, diaChi = ?, soDT = ? , gmail = ? where maNV = ?");
			// pstm = con.prepareStatement("insert into NhanVien
			// values(?,?,?,?,?,?,?,?,?)");
			pstm.setString(1, nhanVien.getTenNhanVien());
			pstm.setBoolean(2, nhanVien.getGioiTinh());
			pstm.setDate(3, Date.valueOf(nhanVien.getNgaySinh()));
			pstm.setString(4, nhanVien.getCmnd());
			pstm.setString(5, nhanVien.getDiaChi());
			pstm.setString(6, nhanVien.getSoDienThoai());
			pstm.setString(7, nhanVien.getEmail());
			pstm.setString(8, nhanVien.getMaNhanVien());
			n = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
	}

	public ArrayList<NhanVien> getDSNhanVienTheoTenTuongDoi(String ten) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			String query = "select * from NhanVien where tenNV like N'%" + ten + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				byte[] hinhAnh = rs.getBytes("hinhAnh");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				LoaiNhanVien loaiNhanVien = dao_LoaiNhanVien.getLoaiNhanVienTheoMa(rs.getString("maLoaiNV"));
				ds.add(new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), gioiTinh,
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("cmnd"), rs.getString("diaChi"),
						rs.getString("soDT"), loaiNhanVien, hinhAnh));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public ArrayList<NhanVien> getDsNhanVienTheoLoai(String ten) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			String query = "SELECT  * FROM KhachHang INNER JOIN LoaiKhachHang ON KhachHang.maLoaiKH = LoaiKhachHang.maLoaiKH where tenLoaiKH like N'%"
					+ ten + "%'";
			// pstm.setString(1, ten.getMaLoaiKhachHang());
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				byte[] hinhAnh = rs.getBytes("hinhAnh");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				LoaiNhanVien loaiNhanVien = dao_LoaiNhanVien.getLoaiNhanVienTheoMa(rs.getString("maLoaiNV"));
				ds.add(new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), gioiTinh,
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("cmnd"), rs.getString("diaChi"),
						rs.getString("soDT"), loaiNhanVien, hinhAnh));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public ArrayList<NhanVien> getDSNhanVienTheoSDT(String SDT) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			String query = "select * from NhanVien where soDT like '%" + SDT + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				byte[] hinhAnh = rs.getBytes("hinhAnh");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				LoaiNhanVien loaiNhanVien = dao_LoaiNhanVien.getLoaiNhanVienTheoMa(rs.getString("maLoaiNV"));
				ds.add(new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), gioiTinh,
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("cmnd"), rs.getString("diaChi"),
						rs.getString("soDT"), loaiNhanVien, hinhAnh));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

//	public static void main(String[] args) {
//		DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
//
//		NhanVien nVien = new NhanVien("001", "SFGD", false, LocalDate.of(22, 8, 12), "54646", "54646", null,
//				new LoaiNhanVien("LNV001", "546", Float.valueOf(4)), null);
//		System.out.println(nVien);
//		dao_NhanVien.addNhanVien(nVien);
//	}
	public byte[] getHinh(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		byte[] hinhAnh = null;
		try {
			pstm = con.prepareStatement("select * from NhanVien where maNV = ?");
			pstm.setString(1, ma);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				hinhAnh = rs.getBytes("hinhAnh");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return hinhAnh;
	}

	public ArrayList<NhanVien> getTimKiemDSNV(String tenLoaiNV, String maNV, String tenNV, String ngaySinh, String gt,
			String cmnd, String diaChi, String soDT) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<NhanVien> ds = new ArrayList<NhanVien>();
		try {
			String query = "SELECT  * FROM NhanVien INNER JOIN LoaiNhanVien ON NhanVien.maLoaiNV = LoaiNhanVien.maLoaiNV where tenLoaiNV like N'%"
					+ tenLoaiNV + "%' AND maNV like '%" + maNV + "%' AND tenNV like N'%" + tenNV
					+ "%' AND ngaySinh like N'%" + ngaySinh + "%' AND gioiTinh like N'%" + gt + "%' AND cmnd like N'%"
					+ cmnd + "%' AND diaChi like N'%" + diaChi + "%' AND soDT like N'%" + soDT
					+ "%'  and maNV NOT LIKE 'admin'";
			// pstm.setString(1, ten.getMaLoaiKhachHang());
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				byte[] hinhAnh = rs.getBytes("hinhAnh");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				LoaiNhanVien loaiNhanVien = dao_LoaiNhanVien.getLoaiNhanVienTheoMa(rs.getString("maLoaiNV"));
				ds.add(new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), gioiTinh,
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("cmnd"), rs.getString("diaChi"),
						rs.getString("soDT"), loaiNhanVien, hinhAnh));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

}
