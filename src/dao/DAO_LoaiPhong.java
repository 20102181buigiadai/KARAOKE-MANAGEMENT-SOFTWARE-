package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.DonVi;
import entity.LoaiPhong;

public class DAO_LoaiPhong {
	public ArrayList<LoaiPhong> getALLLoaiPhong() {
		ArrayList<LoaiPhong> ds = new ArrayList<LoaiPhong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from LoaiPhong");
			while (rs.next()) {
				ds.add(new LoaiPhong(rs.getString(1), rs.getString(2), rs.getString(4), rs.getDouble(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public LoaiPhong getLoaiTheoMa(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		LoaiPhong loai = null;
		try {
			PreparedStatement pstm = con.prepareStatement("select * from LoaiPhong where maLoaiPhong = ?");
			pstm.setString(1, ma);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				loai = new LoaiPhong(rs.getString(1), rs.getString(2), rs.getString(4), rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loai;
	}

	public LoaiPhong getLoaiTheoTen(String ten) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		LoaiPhong loai = null;
		try {
			pstm = con.prepareStatement("select * from LoaiPhong where tenLoaiPhong = ?");
			pstm.setString(1, ten);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				loai = new LoaiPhong(rs.getString(1), rs.getString(2), rs.getString(4), rs.getDouble(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loai;
	}

	public boolean addLoaiPhong(LoaiPhong loaiPhong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("insert into LoaiPhong values(?,?,?,?)");
			pstm.setString(1, loaiPhong.getMaLoaiPhong());
			pstm.setString(2, loaiPhong.getTenLoaiPhong());
			pstm.setDouble(3, loaiPhong.getGia());
			pstm.setString(4, loaiPhong.getMieuTa());
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

	public boolean updateLoaiPhong(LoaiPhong loaiPhong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement(
					"update LoaiPhong set tenLoaiPhong = ?, gia = ?, mieuTa = ? where maLoaiPhong = ?");
			pstm.setString(1, loaiPhong.getTenLoaiPhong());
			pstm.setDouble(2, loaiPhong.getGia());
			pstm.setString(3, loaiPhong.getMieuTa());
			pstm.setString(4, loaiPhong.getMaLoaiPhong());
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

	public boolean deleteLoaiPhong(String maLoaiPhong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("delete from LoaiPhong where maLoaiPhong = ?");
			pstm.setString(1, maLoaiPhong);
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
