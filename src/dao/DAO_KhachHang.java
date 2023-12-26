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

public class DAO_KhachHang {
	private DAO_LoaiKhachHang dao_LoaiKhachHang = new DAO_LoaiKhachHang();

	public DAO_KhachHang() {
		ConnectDB.getInstance().connect();
	}
	
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

	public boolean addKhachHang(KhachHang khachHang) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("insert into KhachHang values(?,?,?,?,?,?)");
			pstm.setString(1, khachHang.getMaKhachHang());
			pstm.setString(2, khachHang.getTenKhachHang());
			pstm.setString(3, khachHang.getSoDienThoai());
			pstm.setInt(4, khachHang.getDiem());
			pstm.setString(5, khachHang.getGhiChu());
			pstm.setString(6, khachHang.getLoaiKhachHang().getMaLoaiKhachHang());
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

	public boolean deleteKhachHangTheoMa(String maKH) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("delete from KhachHang where maKH = ?");
			pstm.setString(1, maKH);
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

	public boolean updateKhachHang(KhachHang khachHang) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement(
					"update KhachHang set tenKH = ?, soDT = ?, diem = ?, ghiChu = ?, maLoaiKH = ? where maKH = ?");
			pstm.setString(1, khachHang.getTenKhachHang());
			pstm.setString(2, khachHang.getSoDienThoai());
			pstm.setInt(3, khachHang.getDiem());
			pstm.setString(4, khachHang.getGhiChu());
			pstm.setString(5, khachHang.getLoaiKhachHang().getMaLoaiKhachHang());
			pstm.setString(6, khachHang.getMaKhachHang());
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

	public ArrayList<KhachHang> getDSKhachHangTheoTenTuongDoi(String ten) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
		try {
			String query = "select * from KhachHang where tenKH like N'%" + ten + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				LoaiKhachHang loai = dao_LoaiKhachHang.getLoaiKhachHangTheoMa(rs.getString(6));
				ds.add(new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						loai));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public ArrayList<KhachHang> getDSKhachHangTheoLoai(String ten) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
		try {
			String query = "SELECT  * FROM KhachHang INNER JOIN LoaiKhachHang ON KhachHang.maLoaiKH = LoaiKhachHang.maLoaiKH where tenLoaiKH like N'%"
					+ ten + "%'";
			// pstm.setString(1, ten.getMaLoaiKhachHang());
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				LoaiKhachHang loaiKH = dao_LoaiKhachHang.getLoaiKhachHangTheoMa(rs.getString(6));
				ds.add(new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						loaiKH));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public ArrayList<KhachHang> getDSKhachHangTheoSDT(String SDT) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
		try {
			String query = "select * from KhachHang where soDT like '%" + SDT + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				LoaiKhachHang loai = dao_LoaiKhachHang.getLoaiKhachHangTheoMa(rs.getString(6));
				ds.add(new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),loai));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	public KhachHang getKhachHangTheoSoDT(String soDT) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		KhachHang khachHang = null;
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from KhachHang where soDT = ?");
			pstm.setString(1, soDT);
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
	public ArrayList<KhachHang> getTimKiemDSKH(String maKH, String tenLoaiKH, String tenKH, String sdt, String Diem, String ghiChu) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
		try {
			String query = "SELECT  * FROM KhachHang INNER JOIN LoaiKhachHang ON KhachHang.maLoaiKH = LoaiKhachHang.maLoaiKH where tenLoaiKH like N'%"
					+ tenLoaiKH + "%' AND maKH like '%"+maKH+"%' AND tenKH like N'%"+tenKH+"%' AND soDT like '%"+sdt+"%' AND diem like '%"+Diem+"%' AND ghiChu like N'%"+ghiChu+"%'";
			// pstm.setString(1, ten.getMaLoaiKhachHang());
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				LoaiKhachHang loaiKH = dao_LoaiKhachHang.getLoaiKhachHangTheoMa(rs.getString(6));
				ds.add(new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						loaiKH));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
