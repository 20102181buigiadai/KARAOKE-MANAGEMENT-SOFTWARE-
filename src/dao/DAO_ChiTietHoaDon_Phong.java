package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon_Phong;
import entity.HoaDon;
import entity.Phong;

public class DAO_ChiTietHoaDon_Phong {
	private DAO_Phong dao_Phong = new DAO_Phong();
	private DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	DateFormat dfJava = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public DAO_ChiTietHoaDon_Phong() {
		ConnectDB.getInstance().connect();
	}

	// hàm chuyển đổi từ sql vào java ngày giờ
	public static LocalDateTime chuyenDateTimeSangLocal(String chuoiSQL) {
		if (chuoiSQL == null)
			return null;
		String dateSQL = chuoiSQL.substring(0, 10);
		String timeSQL = chuoiSQL.substring(11, 19);
		LocalDate date = LocalDate.parse(dateSQL);
		LocalTime time = LocalTime.parse(timeSQL);
		return LocalDateTime.of(date, time);
	}

	// hàm chuyển đổi từ java vào sql ngày giờ
	public static String chuyenLocalSangDateTime(LocalDateTime chuoiJava) {
		if (chuoiJava == null)
			return null;
		String str = chuoiJava.toString();
		return str.substring(0, 10) + " " + str.substring(11, 19);
	}

	// lấy danh sách từ sql
	public ArrayList<ChiTietHoaDon_Phong> getALLDanhSach() {
		ArrayList<ChiTietHoaDon_Phong> dsPhong = new ArrayList<ChiTietHoaDon_Phong>();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from ChiTietHoaDon_Phong");
			while (rs.next()) {
				HoaDon hd = dao_HoaDon.getHDTheoMaHD(rs.getString("maHD"));
				Phong phong = dao_Phong.getPhongTheoMa(rs.getString("maPhong"));
				LocalDateTime gioVao = chuyenDateTimeSangLocal(rs.getString("gioVao"));
				LocalDateTime gioRa = chuyenDateTimeSangLocal(rs.getString("gioRa"));
				ChiTietHoaDon_Phong cthd_p = new ChiTietHoaDon_Phong(hd, phong, gioVao, gioRa);
				dsPhong.add(cthd_p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}

	// lấy danh sách theo mã hoá đơn
	public ArrayList<ChiTietHoaDon_Phong> getDSTheoMaHD(String maHD) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<ChiTietHoaDon_Phong> ds = new ArrayList<ChiTietHoaDon_Phong>();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from ChiTietHoaDon_Phong where maHD = ?");
			pstm.setString(1, maHD);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				HoaDon hd = dao_HoaDon.getHDTheoMaHD(rs.getString("maHD"));
				Phong phong = dao_Phong.getPhongTheoMa(rs.getString("maPhong"));
				LocalDateTime gioVao = chuyenDateTimeSangLocal(rs.getString("gioVao"));
				LocalDateTime gioRa = chuyenDateTimeSangLocal(rs.getString("gioRa"));
				ChiTietHoaDon_Phong cthd_p = new ChiTietHoaDon_Phong(hd, phong, gioVao, gioRa);
				ds.add(cthd_p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return ds;
	}

	// lấy danh sách theo phòng
	public ArrayList<ChiTietHoaDon_Phong> getDSTheoMaPhong(String maPhong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<ChiTietHoaDon_Phong> ds = new ArrayList<ChiTietHoaDon_Phong>();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from ChiTietHoaDon_Phong where maPhong = ? ORDER BY gioVao");
			pstm.setString(1, maPhong);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				HoaDon hd = dao_HoaDon.getHDTheoMaHD(rs.getString("maHD"));
				Phong phong = dao_Phong.getPhongTheoMa(rs.getString("maPhong"));
				LocalDateTime gioVao = chuyenDateTimeSangLocal(rs.getString("gioVao"));
				LocalDateTime gioRa = chuyenDateTimeSangLocal(rs.getString("gioRa"));
				ChiTietHoaDon_Phong cthd_p = new ChiTietHoaDon_Phong(hd, phong, gioVao, gioRa);
				ds.add(cthd_p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return ds;
	}

	// lấy cthd_Phòng cụ thể theo mã phòng và mã hoá đơn
	public ChiTietHoaDon_Phong getCTHD_Phong(String maPhong, String maHD) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from ChiTietHoaDon_Phong where maPhong = ? and maHD=?");
			pstm.setString(1, maPhong);
			pstm.setString(2, maHD);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				HoaDon hd = dao_HoaDon.getHDTheoMaHD(rs.getString("maHD"));
				Phong phong = dao_Phong.getPhongTheoMa(rs.getString("maPhong"));
				LocalDateTime gioVao = chuyenDateTimeSangLocal(rs.getString("gioVao"));
				LocalDateTime gioRa = chuyenDateTimeSangLocal(rs.getString("gioRa"));
				return new ChiTietHoaDon_Phong(hd, phong, gioVao, gioRa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	// thêm phòng mới
	public boolean themCTHD_PMoi(ChiTietHoaDon_Phong cTHD_P) {
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("insert into ChiTietHoaDon_Phong values(?,?,?,null)");
			pstm.setString(1, cTHD_P.getHoaDon().getMaHoaDon());
			pstm.setString(2, cTHD_P.getPhong().getMaPhong());
			pstm.setString(3, chuyenLocalSangDateTime(cTHD_P.getGioVao()));
//			pstm.setString(4, chuyenLocalSangDateTime(cTHD_P.getGioRa()));
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

	// cập nhập phòng
	public boolean capNhap(ChiTietHoaDon_Phong cTHD_P) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement(
					"update ChiTietHoaDon_Phong set gioVao = ?, gioRa=? where maPhong = ? and maHD =?");
			pstm.setString(4, cTHD_P.getHoaDon().getMaHoaDon());
			pstm.setString(3, cTHD_P.getPhong().getMaPhong());
			pstm.setString(1, chuyenLocalSangDateTime(cTHD_P.getGioVao()));
			pstm.setString(2, chuyenLocalSangDateTime(cTHD_P.getGioRa()));
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

	// xoá phòng
	public boolean xoaPhongTheoMa(String maPhongXoa, String maHD) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("delete from Phong where maPhong = ? and maHD=?");
			pstm.setString(4, maPhongXoa);
			pstm.setString(3, maHD);
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

	// thay đổi hoá đơn cho chi tiết phòng
	public boolean thayDoiHoaDon(LocalDateTime gioRa, String maHDMoi, ArrayList<String> chuoiMaHDCu) {
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement pstm = null;
		int n = 0;
//		String gioRaSQL = chuyenLocalSangDateTime(gioRa);
		String query = "UPDATE [ChiTietHoaDon_Phong] SET  gioRa= ? , maHD ='" + maHDMoi + "' WHERE maHD = '"
				+ chuoiMaHDCu.get(0) + "' ";
		for (int i = 1; i < chuoiMaHDCu.size(); i++) {
			query = query + " or maHD = '" + chuoiMaHDCu.get(i) + "' ";
		}
		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, chuyenLocalSangDateTime(gioRa));
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