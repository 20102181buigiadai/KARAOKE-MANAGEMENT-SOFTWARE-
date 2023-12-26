package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiDichVu;

public class DAO_LoaiDichVu {
	public ArrayList<LoaiDichVu> getALLLoaiDivhVu() {
		ArrayList<LoaiDichVu> ds = new ArrayList<LoaiDichVu>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from LoaiDichVu");
			while (rs.next()) {
				ds.add(new LoaiDichVu(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	// lấy TT theo mã
	public LoaiDichVu getLoaiTheoMa(String maLoaiDV) {
		LoaiDichVu loai = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement("select * from LoaiDichVu where maLoaiDV = ?");
			pstm.setString(1, maLoaiDV);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				loai = new LoaiDichVu(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loai;
	}

	// lấy TT theo tên
	public LoaiDichVu getLoaiTheoTen(String tenLoaiDV) {
		LoaiDichVu loai = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement("select * from LoaiDichVu where tenLoaiDV = ?");
			pstm.setString(1, tenLoaiDV);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				loai = new LoaiDichVu(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loai;
	}

	public boolean addLoaiDichVu(LoaiDichVu loaiDV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("insert into LoaiDichVu values(?,?)");
			pstm.setString(1, loaiDV.getMaLoaiDV());
			pstm.setString(2, loaiDV.getTenLoaiDV());
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

	public boolean updateLoaiDichVu(LoaiDichVu loaiDV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("update LoaiDichVu set tenLoaiDV = ? where maLoaiDV = ?");
			pstm.setString(1, loaiDV.getTenLoaiDV());
			pstm.setString(2, loaiDV.getMaLoaiDV());
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

	public boolean deleteLoaiDichVu(String maLoaiDV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("delete from LoaiDichVu where maLoaiDV = ?");
			pstm.setString(1, maLoaiDV);
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

}
