package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.DonVi;

public class DAO_DonVi {
	public ArrayList<DonVi> getALLDonVi() {
		ArrayList<DonVi> ds = new ArrayList<DonVi>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from DonVi");
			while (rs.next()) {
				ds.add(new DonVi(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public DonVi getDonViTheoMa(String maDonVi) {
		DonVi donVi = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement("select * from DonVi where maDonVi = ?");
			pstm.setString(1, maDonVi);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				donVi = new DonVi(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return donVi;
	}

	public DonVi getDonViTheoTen(String tenDonVi) {
		DonVi donVi = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement("select * from DonVi where tenDonVi = ?");
			pstm.setString(1, tenDonVi);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				donVi = new DonVi(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return donVi;
	}

	public boolean addDonVi(DonVi donVi) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("insert into DonVi values(?,?)");
			pstm.setString(1, donVi.getMaDonVi());
			pstm.setString(2, donVi.getTenDonVi());
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

	public boolean updateDonVi(DonVi donVi) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("update DonVi set tenDonVi = ? where maDonVi = ?");
			pstm.setString(1, donVi.getTenDonVi());
			pstm.setString(2, donVi.getMaDonVi());
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

	public boolean deleteDonVi(String maDonVi) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("delete from DonVi where maDonVi = ?");
			pstm.setString(1, maDonVi);
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
