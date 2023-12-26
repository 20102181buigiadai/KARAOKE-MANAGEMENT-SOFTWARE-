package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.DichVu;
import entity.DonVi;
import entity.KhachHang;
import entity.LoaiDichVu;
import entity.LoaiNhanVien;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;

public class DAO_PhieuDatPhong {
	private DAO_KhachHang dao_KhachHang = new DAO_KhachHang();
	private DAO_Phong dao_Phong = new DAO_Phong();
	private DAO_NhanVien dao_NhanVien = new DAO_NhanVien();

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
		return str.substring(0, 10) + " " + str.substring(11, str.length());
	}

	public ArrayList<PhieuDatPhong> getAllPhieu() {
		ArrayList<PhieuDatPhong> ds = new ArrayList<PhieuDatPhong>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from PhieuDatPhong");
			while (rs.next()) {
				LocalDateTime gioTaoPhieu = chuyenDateTimeSangLocal(rs.getString("gioTaoPhieu"));
				LocalDateTime gioVaoPhong = chuyenDateTimeSangLocal(rs.getString("gioVaoPhong"));
				KhachHang khachHang = dao_KhachHang.getKhachHangTheoMa(rs.getString("maKH"));
				Phong phong = dao_Phong.getPhongTheoMa(rs.getString("maPhong"));
				NhanVien nv = dao_NhanVien.getNhanVienTheoMa(rs.getString("maNhanVien"));
				ds.add(new PhieuDatPhong(rs.getString("maPhieuDatPhong"), gioTaoPhieu, gioVaoPhong,
						Double.parseDouble(rs.getString("tongGioDat")), khachHang, phong, nv,
						rs.getString("trangThai")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public boolean themPhieuDat(PhieuDatPhong phieuMoi) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("insert into PhieuDatPhong values(?,?,?,?,?,?,?,?)");
			pstm.setString(1, phieuMoi.getMaPhieuDatPhong());
			pstm.setString(2, chuyenLocalSangDateTime(phieuMoi.getGioTaoPhieu()));
			pstm.setString(3, chuyenLocalSangDateTime(phieuMoi.getGioVaoPhong()));
			pstm.setString(4, phieuMoi.getTongGioDat() + "");
			pstm.setString(5, phieuMoi.getKhachHang().getMaKhachHang());
			pstm.setString(6, phieuMoi.getPhong().getMaPhong());
			pstm.setString(7, phieuMoi.getNhanVien().getMaNhanVien());
			pstm.setString(8, phieuMoi.getTrangThai());
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
	public PhieuDatPhong getPhieuTheoMa(String maTim) {
		PhieuDatPhong phieuDat = new PhieuDatPhong();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement("select * from PhieuDatPhong where maPhieuDatPhong = ?");
			pstm.setString(1, maTim);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				LocalDateTime gioTaoPhieu = chuyenDateTimeSangLocal(rs.getString("gioTaoPhieu"));
				LocalDateTime gioVaoPhong = chuyenDateTimeSangLocal(rs.getString("gioVaoPhong"));
				KhachHang khachHang = dao_KhachHang.getKhachHangTheoMa(rs.getString("maKH"));
				Phong phong = dao_Phong.getPhongTheoMa(rs.getString("maPhong"));
				NhanVien nv = dao_NhanVien.getNhanVienTheoMa(rs.getString("maNhanVien"));
				return new PhieuDatPhong(rs.getString("maPhieuDatPhong"), gioTaoPhieu, gioVaoPhong,
						Double.parseDouble(rs.getString("tongGioDat")), khachHang, phong, nv,
						rs.getString("trangThai"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return phieuDat;
	}

	// lấy TT theo mã
	public ArrayList<PhieuDatPhong> getTheoMaPhong_TrangThai(String maPhong, String TrangThai) {
		Connection con = ConnectDB.getInstance().getConnection();
		ArrayList<PhieuDatPhong> ds = new ArrayList<PhieuDatPhong>();
		try {
			PreparedStatement pstm = con.prepareStatement(
					"select * from PhieuDatPhong where maPhong = ? and trangThai=? ORDER BY gioVaoPhong");
			pstm.setString(1, maPhong);
			pstm.setString(2, TrangThai);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				LocalDateTime gioTaoPhieu = chuyenDateTimeSangLocal(rs.getString("gioTaoPhieu"));
				LocalDateTime gioVaoPhong = chuyenDateTimeSangLocal(rs.getString("gioVaoPhong"));
				KhachHang khachHang = dao_KhachHang.getKhachHangTheoMa(rs.getString("maKH"));
				Phong phong = dao_Phong.getPhongTheoMa(rs.getString("maPhong"));
				NhanVien nv = dao_NhanVien.getNhanVienTheoMa(rs.getString("maNhanVien"));
				ds.add(new PhieuDatPhong(rs.getString("maPhieuDatPhong"), gioTaoPhieu, gioVaoPhong,
						Double.parseDouble(rs.getString("tongGioDat")), khachHang, phong, nv,
						rs.getString("trangThai")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	// lấy TT theo mã
	public ArrayList<PhieuDatPhong> getDSTheoTrangThai(String TrangThai) {
		Connection con = ConnectDB.getInstance().getConnection();
		ArrayList<PhieuDatPhong> ds = new ArrayList<PhieuDatPhong>();
		try {
			PreparedStatement pstm = con
					.prepareStatement("select * from PhieuDatPhong where  trangThai=? ORDER BY gioVaoPhong");
			pstm.setString(1, TrangThai);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				LocalDateTime gioTaoPhieu = chuyenDateTimeSangLocal(rs.getString("gioTaoPhieu"));
				LocalDateTime gioVaoPhong = chuyenDateTimeSangLocal(rs.getString("gioVaoPhong"));
				KhachHang khachHang = dao_KhachHang.getKhachHangTheoMa(rs.getString("maKH"));
				Phong phong = dao_Phong.getPhongTheoMa(rs.getString("maPhong"));
				NhanVien nv = dao_NhanVien.getNhanVienTheoMa(rs.getString("maNhanVien"));
				ds.add(new PhieuDatPhong(rs.getString("maPhieuDatPhong"), gioTaoPhieu, gioVaoPhong,
						Double.parseDouble(rs.getString("tongGioDat")), khachHang, phong, nv,
						rs.getString("trangThai")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public boolean capNhapPhieu(PhieuDatPhong phieu) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement(
					"update PhieuDatPhong set gioTaoPhieu = ? , gioVaoPhong = ? , tongGioDat=? , maKH = ? , maPhong = ? , maNhanVien = ? , trangThai=? where maPhieuDatPhong = ?");
			pstm.setString(8, phieu.getMaPhieuDatPhong());
			pstm.setString(1, chuyenLocalSangDateTime(phieu.getGioTaoPhieu()));
			pstm.setString(2, chuyenLocalSangDateTime(phieu.getGioVaoPhong()));
			pstm.setString(3, phieu.getTongGioDat() + "");
			pstm.setString(4, phieu.getKhachHang().getMaKhachHang());
			pstm.setString(5, phieu.getPhong().getMaPhong());
			pstm.setString(6, phieu.getNhanVien().getMaNhanVien());
			pstm.setString(7, phieu.getTrangThai());
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

	public boolean xoaPhieuTheoMa(String maXoa) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("delete from PhieuDatPhong where maPhieuDatPhong = ?");
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

	public ArrayList<PhieuDatPhong> getDSPhieuTheoMa(String MaPhieu, String soDT) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<PhieuDatPhong> ds = new ArrayList<PhieuDatPhong>();
		try {
			String query = "SELECT * FROM PhieuDatPhong INNER JOIN KhachHang ON PhieuDatPhong.maKH = KhachHang.maKH where soDT like '%"
					+ soDT + "%' AND maPhieuDatPhong like '%" + MaPhieu + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				LocalDateTime gioTaoPhieu = chuyenDateTimeSangLocal(rs.getString("gioTaoPhieu"));
				LocalDateTime gioVaoPhong = chuyenDateTimeSangLocal(rs.getString("gioVaoPhong"));
				KhachHang khachHang = dao_KhachHang.getKhachHangTheoMa(rs.getString("maKH"));
				Phong phong = dao_Phong.getPhongTheoMa(rs.getString("maPhong"));
				NhanVien nv = dao_NhanVien.getNhanVienTheoMa(rs.getString("maNhanVien"));
				ds.add(new PhieuDatPhong(rs.getString("maPhieuDatPhong"), gioTaoPhieu, gioVaoPhong,
						Double.parseDouble(rs.getString("tongGioDat")), khachHang, phong, nv,
						rs.getString("trangThai")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public boolean nhanPhongCho(String maPhong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement(
					"update  Phong set trangThaiPhong = N'Đang sử dụng' where maPhong ='" + maPhong + "'");
			// pstm.setString(1, maPhong);
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

	public boolean capNhatTrangThai(String maPhong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("update  Phong set trangThaiPhong = N'Trống' where maPhong ='" + maPhong + "'");
			// pstm.setString(1, maPhong);
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

	public ArrayList<ArrayList<String>> getTKNhanVienTheoPhieuDatPhong(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<ArrayList<String>> ds = new ArrayList<ArrayList<String>>();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(
					"  select DP.maNhanVien, tenNV, soDT, count(DP.maNhanVien) as SLPhieu from PhieuDatPhong DP inner join NhanVien NV on DP.maNhanVien = NV.maNV where gioTaoPhieu between ? and ? group by DP.maNhanVien, tenNV, soDT order by DP.maNhanVien");
			pstm.setString(1, ngayBatDau.toString());

			// Cộng ngày kết thúc lên 1 để get ra luôn những phiếu đặt phòng trong ngày kết
			// thúc
			pstm.setString(2, ngayKetThuc.plusDays(1).toString());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				ArrayList<String> row = new ArrayList<String>();
				row.add(rs.getString("maNhanVien"));
				row.add(rs.getString("tenNV"));
				row.add(rs.getString("soDT"));
				row.add(rs.getString("SLPhieu"));
				ds.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
