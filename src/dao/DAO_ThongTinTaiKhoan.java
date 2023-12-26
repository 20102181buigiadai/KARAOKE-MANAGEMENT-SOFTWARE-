package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;

public class DAO_ThongTinTaiKhoan {
	private DAO_LoaiKhachHang dao_LoaiKhachHang = new DAO_LoaiKhachHang();

	/*
	 * public ArrayList<NhanVien> getALLNhanVien() { ArrayList<NhanVien> ds = new
	 * ArrayList<NhanVien>(); ConnectDB.getInstance(); Connection con =
	 * ConnectDB.getConnection(); try { Statement stm = con.createStatement();
	 * ResultSet rs = stm.executeQuery("select *from NhanVien"); while (rs.next()) {
	 * ds.add(new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3),
	 * rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7),
	 * dao_LoaiNhanVien.getLoaiNhanVienTheoMa(rs.getString(8)))); } } catch
	 * (SQLException e) { e.printStackTrace(); } return ds; }
	 */

	/*
	 * public NhanVien getNhanVienTheoMa(String ma) { ConnectDB.getInstance();
	 * Connection con = ConnectDB.getConnection(); NhanVien nhanVien = null;
	 * PreparedStatement pstm = null; try { pstm =
	 * con.prepareStatement("select * from NhanVien where maNV = ?");
	 * pstm.setString(1, ma); ResultSet rs = pstm.executeQuery(); while (rs.next())
	 * { nhanVien = new NhanVien(rs.getString(1), rs.getString(2), rs.getString(3),
	 * rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7),
	 * dao_LoaiNhanVien.getLoaiNhanVienTheoMa(rs.getString(8))); } } catch
	 * (SQLException e) { e.printStackTrace(); } finally { try { pstm.close(); }
	 * catch (SQLException e2) { e2.printStackTrace(); } } return nhanVien; }
	 */
	public ArrayList<KhachHang> getALLKhachHang() {
		ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from KhachHang");
			while (rs.next()) {
				ds.add(new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						dao_LoaiKhachHang.getLoaiKhachHangTheoMa(rs.getString(6))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public KhachHang getKhachHangTheoMa(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		KhachHang khachHang = null;
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from KhachHang where maKH = ?");
			pstm.setString(1, ma);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				khachHang = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getString(5), dao_LoaiKhachHang.getLoaiKhachHangTheoMa(rs.getString(6)));
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
		return khachHang;
	}

}
