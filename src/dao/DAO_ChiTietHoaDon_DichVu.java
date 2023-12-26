package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon_DichVu;
import entity.DichVu;
import entity.HoaDon;
import entity.Phong;

public class DAO_ChiTietHoaDon_DichVu {
	private DAO_DichVu dao_DichVu = new DAO_DichVu();
	private DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
	private DAO_Phong dao_Phong = new DAO_Phong();

	public DAO_ChiTietHoaDon_DichVu() {
		ConnectDB.getInstance().connect();
	}

	// lấy danh sách từ sql
	public ArrayList<ChiTietHoaDon_DichVu> getAllDanhSach() {
		ArrayList<ChiTietHoaDon_DichVu> danhSach = new ArrayList<ChiTietHoaDon_DichVu>();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from ChiTietHoaDon_DichVu");
			while (rs.next()) {
				HoaDon hDon = dao_HoaDon.getHDTheoMaHD(rs.getString("maHD"));
				Phong phong = dao_Phong.getPhongTheoMa(rs.getString("maPhong"));
				DichVu dv = dao_DichVu.getDichVuTheoMa(rs.getString("maDichVu"));
				danhSach.add(new ChiTietHoaDon_DichVu(hDon, dv, phong, rs.getInt("soLuong")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return danhSach;
	}

	// lấy danh sách theo phòng
	public ArrayList<ChiTietHoaDon_DichVu> getDSTheoMaPhong(String maPhongTim) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<ChiTietHoaDon_DichVu> ds = new ArrayList<ChiTietHoaDon_DichVu>();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from ChiTietHoaDon_DichVu where maPhong = ?");
			pstm.setString(1, maPhongTim);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				HoaDon hDon = dao_HoaDon.getHDTheoMaHD(rs.getString("maHD"));
				Phong phong = dao_Phong.getPhongTheoMa(rs.getString("maPhong"));
				DichVu dv = dao_DichVu.getDichVuTheoMa(rs.getString("maDichVu"));
				ds.add(new ChiTietHoaDon_DichVu(hDon, dv, phong, rs.getInt("soLuong")));
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
		return ds;
	}

	// lấy danh sách theo hoá đơn
	public ArrayList<ChiTietHoaDon_DichVu> getDSTheoMaDV(String maHD) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<ChiTietHoaDon_DichVu> ds = new ArrayList<ChiTietHoaDon_DichVu>();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * ChiTietHoaDon_DichVu Phong where maHD = ?");
			pstm.setString(1, maHD);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				HoaDon hDon = dao_HoaDon.getHDTheoMaHD(rs.getString("maHD"));
				Phong phong = dao_Phong.getPhongTheoMa(rs.getString("maPhong"));
				DichVu dv = dao_DichVu.getDichVuTheoMa(rs.getString("maDichVu"));
				ds.add(new ChiTietHoaDon_DichVu(hDon, dv, phong, rs.getInt("soLuong")));
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
		return ds;
	}

	// lấy danh sách theo dịch vụ
	public ArrayList<ChiTietHoaDon_DichVu> getDSTheoMaHD(String maDV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<ChiTietHoaDon_DichVu> ds = new ArrayList<ChiTietHoaDon_DichVu>();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from ChiTietHoaDon_DichVu where maHD = ?");
			pstm.setString(1, maDV);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				HoaDon hDon = dao_HoaDon.getHDTheoMaHD(rs.getString("maHD"));
				Phong phong = dao_Phong.getPhongTheoMa(rs.getString("maPhong"));
				DichVu dv = dao_DichVu.getDichVuTheoMa(rs.getString("maDichVu"));
				ds.add(new ChiTietHoaDon_DichVu(hDon, dv, phong, rs.getInt("soLuong")));
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
		return ds;
	}

	// lấy một ci tiết cụ thể
	public ChiTietHoaDon_DichVu getCTHD_DV(String maDV, String maHD, String maPhong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"select * from ChiTietHoaDon_DichVu where maDichVu = ? and maHD =? and maPhong =?");
			pstm.setString(1, maDV);
			pstm.setString(2, maHD);
			pstm.setString(3, maPhong);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				HoaDon hDon = dao_HoaDon.getHDTheoMaHD(rs.getString("maHD"));
				Phong phong = dao_Phong.getPhongTheoMa(rs.getString("maPhong"));
				DichVu dv = dao_DichVu.getDichVuTheoMa(rs.getString("maDichVu"));
				return new ChiTietHoaDon_DichVu(hDon, dv, phong, rs.getInt("soLuong"));
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
		return null;
	}

	// thêm chi tiet hoá đơn dịch vụ mới
	public boolean themCTHD_DVMoi(ChiTietHoaDon_DichVu cthd_dv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("insert into ChiTietHoaDon_DichVu values(?,?,?,?)");
			pstm.setString(1, cthd_dv.getHoaDon().getMaHoaDon());
			pstm.setString(2, cthd_dv.getDichVu().getMaDV());
			pstm.setString(3, cthd_dv.getPhong().getMaPhong());
			pstm.setInt(4, cthd_dv.getSoLuong());
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

	// cập nhập cthd
	public boolean capNhap(ChiTietHoaDon_DichVu cTHD_DV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement(
					"update ChiTietHoaDon_DichVu set soLuong=? where maPhong = ? and maDichVu =? and maHD=?");
			pstm.setString(4, cTHD_DV.getHoaDon().getMaHoaDon());
			pstm.setString(2, cTHD_DV.getPhong().getMaPhong());
			pstm.setString(3, cTHD_DV.getDichVu().getMaDV());
			pstm.setInt(1, cTHD_DV.getSoLuong());
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

	// xoá cthd_dv
	public boolean xoaCTHD_DV(ChiTietHoaDon_DichVu cTHD_DV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con
					.prepareStatement("delete from ChiTietHoaDon_DichVu where maPhong = ? and maDichVu =? and maHD=?");
			pstm.setString(3, cTHD_DV.getHoaDon().getMaHoaDon());
			pstm.setString(1, cTHD_DV.getPhong().getMaPhong());
			pstm.setString(2, cTHD_DV.getDichVu().getMaDV());
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

	// thay đổi hoá đơn cho chi tiết dịch vụ
	public boolean thayDoiHoaDon(String maHDMoi, ArrayList<String> chuoiMaHDCu) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		String queryString = "UPDATE [ChiTietHoaDon_DichVu] SET maHD= '" + maHDMoi + "' WHERE  maHD = '"
				+ chuoiMaHDCu.get(0) + "'";
		for (int i = 1; i < chuoiMaHDCu.size(); i++) {
			queryString = queryString + " or maHD = '" + chuoiMaHDCu.get(i) + "'";
		}
		System.out.println(queryString);
		try {
			pstm = con.prepareStatement(queryString);
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
