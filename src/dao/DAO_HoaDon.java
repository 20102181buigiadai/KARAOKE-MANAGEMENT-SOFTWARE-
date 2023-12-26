package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class DAO_HoaDon {
	private DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
	private DAO_KhachHang dao_KhachHang = new DAO_KhachHang();

	public ArrayList<HoaDon> getALLHoaDon() {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from HoaDon");
			while (rs.next()) {
				NhanVien nVien = dao_NhanVien.getNhanVienTheoMa(rs.getString("maNV"));
				KhachHang khachHang = dao_KhachHang.getKhachHangTheoMa(rs.getString("maKH"));
				HoaDon hDon = new HoaDon(rs.getString("maHD"), rs.getInt("chietKhau"), nVien, khachHang,
						rs.getLong("tienNhan"));
				dsHoaDon.add(hDon);
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return dsHoaDon;
	}

	// lấy TT theo mã Hoá Đơn
	public HoaDon getHDTheoMaHD(String maHDTim) {
		HoaDon hd = null;
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement("select * from HoaDon where maHD =?");
			pstm.setString(1, maHDTim);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				NhanVien nVien = dao_NhanVien.getNhanVienTheoMa(rs.getString("maNV"));
				KhachHang khachHang = dao_KhachHang.getKhachHangTheoMa(rs.getString("maKH"));
				hd = new HoaDon(rs.getString("maHD"), rs.getInt("chietKhau"), nVien, khachHang, rs.getLong("tienNhan"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hd;
	}

	// lấy TT theo mã PHÒNG
	public ArrayList<HoaDon> getDSTheoMaPhong(String maPhongTim) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement("select * from HoaDon where maPhong =?");
			pstm.setString(1, maPhongTim);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				NhanVien nVien = dao_NhanVien.getNhanVienTheoMa(rs.getString("maNV"));
				KhachHang khachHang = dao_KhachHang.getKhachHangTheoMa(rs.getString("maKH"));
				HoaDon hDon = new HoaDon(rs.getString("maHD"), rs.getInt("chietKhau"), nVien, khachHang,
						rs.getLong("tienNhan"));
				dsHoaDon.add(hDon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsHoaDon;
	}

	// lấy danh sách theo mã nhân viên
	public ArrayList<HoaDon> getDSTheoMaNV(String maNVTim) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement("select * from HoaDon where maNV =?");
			pstm.setString(1, maNVTim);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				NhanVien nVien = dao_NhanVien.getNhanVienTheoMa(rs.getString("maNV"));
				KhachHang khachHang = dao_KhachHang.getKhachHangTheoMa(rs.getString("maKH"));
				HoaDon hDon = new HoaDon(rs.getString("maHD"), rs.getInt("chietKhau"), nVien, khachHang,
						rs.getLong("tienNhan"));
				dsHoaDon.add(hDon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsHoaDon;
	}

	// lấy danh sách theo mã khách hàng
	public ArrayList<HoaDon> getDSTheoMaKhach(String maKhachTim) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement("select * from HoaDon where maKH =?");
			pstm.setString(1, maKhachTim);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				NhanVien nVien = dao_NhanVien.getNhanVienTheoMa(rs.getString("maNV"));
				KhachHang khachHang = dao_KhachHang.getKhachHangTheoMa(rs.getString("maKH"));
				HoaDon hDon = new HoaDon(rs.getString("maHD"), rs.getInt("chietKhau"), nVien, khachHang,
						rs.getLong("tienNhan"));
				dsHoaDon.add(hDon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsHoaDon;
	}

	// thêm hoá đơn mới
	public boolean themHoaDon(HoaDon hoaDonMoi) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("insert into HoaDon values(?,?,?,?,?)");
			pstm.setString(1, hoaDonMoi.getMaHoaDon());
			pstm.setString(2, hoaDonMoi.getChietKhau() + "");
			pstm.setString(3, hoaDonMoi.getNhanVien().getMaNhanVien());
			pstm.setString(4, hoaDonMoi.getKhachHang().getMaKhachHang());
			pstm.setLong(5, hoaDonMoi.getTienNhan());
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

	// cập nhập hoá đơn
	public boolean capNhapHonDon(HoaDon hoaDonSua) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con
					.prepareStatement("update HoaDon set chietKhau = ?, maNV=? ,  maKH =? ,tienNhan =? where maHD = ?");
			pstm.setString(1, hoaDonSua.getChietKhau() + "");
			pstm.setString(2, hoaDonSua.getNhanVien().getMaNhanVien());
			pstm.setString(3, hoaDonSua.getKhachHang().getMaKhachHang());
			pstm.setLong(4, hoaDonSua.getTienNhan());
			pstm.setString(5, hoaDonSua.getMaHoaDon());
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

	// xoá hoá đơn
	public boolean xoaHoaDonTheoMa(String maHDXoa) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("delete from HoaDon where maHD = ?");
			pstm.setString(1, maHDXoa);
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

	/**
	 * Tìm tương đối theo tên
	 * 
	 * @param tenKH
	 * @return danh sách hóa đơn tìm được
	 */
	public ArrayList<HoaDon> getDSTheoTenKhachHangTuongDoi(String tenKH) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		try {
			String query = "select maHD, chietKhau, maNV, h.maKH from HoaDon h inner join KhachHang k on h.maKH = k.maKH where tenKH like N'%"
					+ tenKH + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				ds.add(new HoaDon(rs.getString("maHD"), rs.getInt("chietKhau"),
						dao_NhanVien.getNhanVienTheoMa(rs.getString("maNV")),
						dao_KhachHang.getKhachHangTheoMa(rs.getString("maKH"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * Tìm tương đối theo số điện thoại khách hàng
	 * 
	 * @param sdt
	 * @return danh sách hóa đơn tìm được
	 */
	public ArrayList<HoaDon> getDSTheoSDTKhachHangTuongDoi(String sdt) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		try {
			String query = "select maHD, chietKhau, maNV, h.maKH from HoaDon h inner join KhachHang k on h.maKH = k.maKH where soDT like '%"
					+ sdt + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				ds.add(new HoaDon(rs.getString(1), rs.getInt(2), dao_NhanVien.getNhanVienTheoMa(rs.getString(3)),
						dao_KhachHang.getKhachHangTheoMa(rs.getString(4))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * Tìm tương đối theo tên nhân viên lập hóa đơn
	 * 
	 * @param tenNV
	 * @return danh sách hóa đơn tìm được
	 */
	public ArrayList<HoaDon> getDSTheoTenNhanVienTuongDoi(String tenNV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		try {
			String query = "select * from HoaDon inner join NhanVien on HoaDon.maNV = NhanVien.maNV where tenNV like N'%"
					+ tenNV + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				ds.add(new HoaDon(rs.getString(1), rs.getInt(2), dao_NhanVien.getNhanVienTheoMa(rs.getString(3)),
						dao_KhachHang.getKhachHangTheoMa(rs.getString(4))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public ArrayList<HoaDon> getHoaDonDaThanhToan() {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from HoaDon where tienNhan > 0");
			while (rs.next()) {
				NhanVien nVien = dao_NhanVien.getNhanVienTheoMa(rs.getString("maNV"));
				KhachHang khachHang = dao_KhachHang.getKhachHangTheoMa(rs.getString("maKH"));
				HoaDon hDon = new HoaDon(rs.getString("maHD"), rs.getInt("chietKhau"), nVien, khachHang,
						rs.getLong("tienNhan"));
				dsHoaDon.add(hDon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsHoaDon;
	}

}
