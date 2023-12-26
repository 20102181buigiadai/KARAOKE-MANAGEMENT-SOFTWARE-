package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiKhachHang;

public class DAO_LoaiKhachHang {
	public DAO_LoaiKhachHang() {
		ConnectDB.getInstance().connect();
	}
	public ArrayList<LoaiKhachHang> getALLLoaiKhachHang() {
		ArrayList<LoaiKhachHang> ds = new ArrayList<LoaiKhachHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from LoaiKhachHang");
			while (rs.next()) {
				ds.add(new LoaiKhachHang(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public LoaiKhachHang getLoaiKhachHangTheoMa(String ma) {
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		LoaiKhachHang loaiKhachHang = null;
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from LoaiKhachHang where maLoaiKH = ?");
			pstm.setString(1, ma);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				loaiKhachHang = new LoaiKhachHang(rs.getString(1), rs.getString(2));
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
		return loaiKhachHang;
	}

	public LoaiKhachHang getLoaiKhachHangTheoTen(String ten) {
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		LoaiKhachHang loaiKhachHang = null;
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from LoaiKhachHang where tenLoaiKH = ?");
			pstm.setString(1, ten);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				loaiKhachHang = new LoaiKhachHang(rs.getString(1), rs.getString(2));
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
		return loaiKhachHang;
	}
	public boolean deleteLoaiKHTheoMa(String maLKH) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("delete from LoaiKhachHang where maLoaiKH = ?");
			pstm.setString(1, maLKH);
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
	public boolean updateLoaiKH(LoaiKhachHang loaiKhachHang) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement(
					"update LoaiKhachHang set tenLoaiKH = ? where maLoaiKH = ?");
			pstm.setString(1, loaiKhachHang.getTenLoaiKhachHang());
			pstm.setString(2, loaiKhachHang.getMaLoaiKhachHang());
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
	public boolean addLoaiKhachHang(LoaiKhachHang loaiKhachHang) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("insert into LoaiKhachHang values(?,?)");
			pstm.setString(1, loaiKhachHang.getMaLoaiKhachHang());
			pstm.setString(2, loaiKhachHang.getTenLoaiKhachHang());
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
	public ArrayList<LoaiKhachHang> getDSLoaiKHTheoMa(String maLoaiKH) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<LoaiKhachHang> ds = new ArrayList<LoaiKhachHang>();
		try {
			String query = "select * from LoaiKhachHang where maLoaiKH like N'%" + maLoaiKH + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				ds.add(new LoaiKhachHang(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<LoaiKhachHang> getTimKiemDSLKH(String maLKH ,String tenLoaiKH) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<LoaiKhachHang> ds = new ArrayList<LoaiKhachHang>();
		try {
			String query = "select * from LoaiKhachHang where tenLoaiKH like N'%" + tenLoaiKH + "%' AND maLoaiKH like N'%" + maLKH + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				ds.add(new LoaiKhachHang(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
