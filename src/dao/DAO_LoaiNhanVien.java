package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiNhanVien;

public class DAO_LoaiNhanVien {
	public DAO_LoaiNhanVien() {
		ConnectDB.getInstance().connect();
	}
	public ArrayList<LoaiNhanVien> getALLLoaiNhanVien() {
		ArrayList<LoaiNhanVien> ds = new ArrayList<LoaiNhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from LoaiNhanVien");
			while (rs.next()) {
				ds.add(new LoaiNhanVien(rs.getString(1), rs.getString(2), rs.getFloat(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public LoaiNhanVien getLoaiNhanVienTheoMa(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		LoaiNhanVien loaiNhanVien = null;
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from LoaiNhanVien where maLoaiNV = ?");
			pstm.setString(1, ma);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				loaiNhanVien = new LoaiNhanVien(rs.getString(1), rs.getString(2), rs.getFloat(3));
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
		return loaiNhanVien;
	}

	public LoaiNhanVien getLoaiNhanVienTheoTen(String ten) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		LoaiNhanVien loaiNhanVien = null;
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from LoaiNhanVien where tenLoaiNV = ?");
			pstm.setString(1, ten);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				loaiNhanVien = new LoaiNhanVien(rs.getString(1), rs.getString(2), rs.getFloat(3));
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
		return loaiNhanVien;
	}
	public boolean addLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("insert into LoaiNhanVien values(?,?,?)");
			pstm.setString(1, loaiNhanVien.getMaLoai());
			pstm.setString(2, loaiNhanVien.getTenLoai());
			pstm.setFloat(3, loaiNhanVien.getHeSoLuong());
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
	public boolean deleteLoaiNVTheoMa(String maLNV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("delete from LoaiNhanVien where maLoaiNV = ?");
			pstm.setString(1, maLNV);
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
	public boolean updateLoaiNV(LoaiNhanVien loaiNhanVien) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement(
					"update LoaiNhanVien set tenLoaiNV = ?, heSoLuong = ? where maLoaiNV = ?");
			pstm.setString(1, loaiNhanVien.getTenLoai());
			pstm.setFloat(2, loaiNhanVien.getHeSoLuong());
			pstm.setString(3, loaiNhanVien.getMaLoai());
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
	public ArrayList<LoaiNhanVien> getDSLoaiNVTheoTenLNV(String tenLoaiNV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<LoaiNhanVien> ds = new ArrayList<LoaiNhanVien>();
		try {
			String query = "select * from LoaiNhanVien where tenLoaiNV like N'%" + tenLoaiNV + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				ds.add(new LoaiNhanVien(rs.getString(1), rs.getString(2), rs.getFloat(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<LoaiNhanVien> getDSLoaiNVTheoHSL(String hsl) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<LoaiNhanVien> ds = new ArrayList<LoaiNhanVien>();
		try {
			String query = "select * from LoaiNhanVien where heSoLuong like '%" + hsl + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				ds.add(new LoaiNhanVien(rs.getString(1), rs.getString(2), rs.getFloat(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	public ArrayList<LoaiNhanVien> gettimKiemDSLNV(String maLoaiNV, String tenLNV, String hsl) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<LoaiNhanVien> ds = new ArrayList<LoaiNhanVien>();
		try {
			String query = "select * from LoaiNhanVien where maLoaiNV like N'%" + maLoaiNV + "%' AND tenLoaiNV like N'%"+tenLNV+"%' AND heSoLuong like '%"+hsl+"%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				ds.add(new LoaiNhanVien(rs.getString(1), rs.getString(2), rs.getFloat(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
