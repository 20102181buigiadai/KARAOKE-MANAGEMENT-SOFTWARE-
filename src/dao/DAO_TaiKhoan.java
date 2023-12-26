package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.LoaiKhachHang;
import entity.NhanVien;
import entity.TaiKhoan;

public class DAO_TaiKhoan {
	DAO_NhanVien dao_NhanVien = new DAO_NhanVien();

	public DAO_TaiKhoan() {
		ConnectDB.getInstance().connect();
	}
	
	public ArrayList<TaiKhoan> getAllTaiKhoan() {
		ArrayList<TaiKhoan> ds = new ArrayList<TaiKhoan>();
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from TaiKhoan");
			while (rs.next()) {
				NhanVien nhanVien = dao_NhanVien.getNhanVienTheoMa(rs.getString("maChuTK"));
				TaiKhoan tKhoan = new TaiKhoan(rs.getString("tenTK").trim(), rs.getString("matKhau").trim(), nhanVien);
				ds.add(tKhoan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public boolean addTaiKhoan(TaiKhoan taiKhoan) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("insert into TaiKhoan values(?,?,?)");
			pstm.setString(1, taiKhoan.getTenTaiKhoan());
			pstm.setString(2, taiKhoan.getMatKhau());
			pstm.setString(3, taiKhoan.getNhanVien().getMaNhanVien());
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

	// lấy TT theo mã
	public TaiKhoan getTaiKhoanTheoMa(String maTim) {
		TaiKhoan taiKhoan = new TaiKhoan();
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement("select * from TaiKhoan where tenTK = ?");
			pstm.setString(1, maTim);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				NhanVien nhanVien = dao_NhanVien.getNhanVienTheoMa(rs.getString("maChuTK"));
				taiKhoan = new TaiKhoan(rs.getString("tenTK"), rs.getString("matKhau"), nhanVien);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taiKhoan;
	}

	public boolean capNhapTaiKhoan(TaiKhoan taiKhoan) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("update TaiKhoan set matKhau = ?, maChuTK = ? where tenTK = ?");
			pstm.setString(3, taiKhoan.getTenTaiKhoan());
			pstm.setString(1, taiKhoan.getMatKhau());
			pstm.setString(2, taiKhoan.getNhanVien().getMaNhanVien());
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
	public boolean capNhapMKTaiKhoan(TaiKhoan taiKhoan) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("update TaiKhoan set matKhau = ? where tenTK = ?");
			pstm.setString(2, taiKhoan.getTenTaiKhoan());
			pstm.setString(1, taiKhoan.getMatKhau());
			//pstm.setString(2, taiKhoan.getNhanVien().getMaNhanVien());
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

	public boolean xoaTKTheoMa(String maXoa) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("delete from TaiKhoan where tenTK = ?");
			pstm.setString(1, maXoa);
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
	public ArrayList<TaiKhoan> getDSTaiKhoanTheoTen(String ten) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<TaiKhoan> ds = new ArrayList<TaiKhoan>();
		try {
			String query = "select * from TaiKhoan where tenTK like N'%" + ten + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				NhanVien ma = dao_NhanVien.getNhanVienTheoMa(rs.getString(3));
				ds.add(new TaiKhoan(rs.getString(1), rs.getString(2), ma));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<TaiKhoan> getDSTaiKhoanTheoMa(String maChuTK) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<TaiKhoan> ds = new ArrayList<TaiKhoan>();
		try {
			String query = "select * from TaiKhoan where maChuTK like N'%" + maChuTK + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				NhanVien ma = dao_NhanVien.getNhanVienTheoMa(rs.getString(3));
				ds.add(new TaiKhoan(rs.getString(1), rs.getString(2), ma));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<TaiKhoan> getTimKiemDSTK(String tenTK, String maChuTK) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<TaiKhoan> ds = new ArrayList<TaiKhoan>();
		try {
			String query = "select * from TaiKhoan where maChuTK like N'%" + maChuTK + "%' AND tenTK like N'%"+tenTK+"%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				NhanVien ma = dao_NhanVien.getNhanVienTheoMa(rs.getString(3));
				ds.add(new TaiKhoan(rs.getString(1), rs.getString(2), ma));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

}
