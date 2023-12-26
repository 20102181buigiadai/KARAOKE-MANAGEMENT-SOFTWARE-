package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.DichVu;
import entity.DonVi;
import entity.LoaiDichVu;
import entity.LoaiPhong;
import entity.Phong;

public class DAO_Phong {

	private DAO_LoaiPhong daoLoaiPhong = new DAO_LoaiPhong();

	public DAO_Phong() {
		ConnectDB.getInstance().connect();
	}

	// lấy danh sách từ sql
	public ArrayList<Phong> getDanhSachPhong() {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from Phong");
			while (rs.next()) {
				LoaiPhong loai = daoLoaiPhong.getLoaiTheoMa(rs.getString("maLoaiPhong"));
				dsPhong.add(new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), rs.getString("viTri"),
						rs.getString("trangThaiPhong"), loai, rs.getInt("sucChua")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}

	// lấy TT theo mã
	public Phong getPhongTheoMa(String maPhongTim) {
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement("select * from Phong where maPhong = ?");
			pstm.setString(1, maPhongTim);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				LoaiPhong loai = daoLoaiPhong.getLoaiTheoMa(rs.getString("maLoaiPhong"));
				return new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), rs.getString("viTri"),
						rs.getString("trangThaiPhong"), loai, rs.getInt("sucChua"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Tìm tương đối theo tên phòng
	 * 
	 * @param ten: tên phòng truyền vào có thể là tên đầy đủ hoặc không.
	 * @return Danh sách phòng tìm được
	 */
	public ArrayList<Phong> getDSPhongTheoTenTuongDoi(String ten) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<Phong> ds = new ArrayList<Phong>();
		try {
			String query = "select * from Phong where tenPhong like N'%" + ten + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				LoaiPhong loai = daoLoaiPhong.getLoaiTheoMa(rs.getString("maLoaiPhong"));
				ds.add(new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), rs.getString("viTri"),
						rs.getString("trangThaiPhong"), loai, rs.getInt("sucChua")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	// lấy danh sách theo trạng thái Phòng
	public ArrayList<Phong> getDSTheoTTphong(String trangThaiTim) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from Phong where trangThaiPhong = ?");
			pstm.setString(1, trangThaiTim);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				LoaiPhong loai = daoLoaiPhong.getLoaiTheoMa(rs.getString("maLoaiPhong"));
				dsPhong.add(new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), rs.getString("viTri"),
						rs.getString("trangThaiPhong"), loai, rs.getInt("sucChua")));
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
		return dsPhong;
	}

	// lấy danh sách theo trạng thái Phòng
	public ArrayList<Phong> getDSTheoTTphong(String trangThai1, String trangThai2) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from Phong where trangThaiPhong = ? or trangThaiPhong = ? ");
			pstm.setString(1, trangThai1);
			pstm.setString(2, trangThai2);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				LoaiPhong loai = daoLoaiPhong.getLoaiTheoMa(rs.getString("maLoaiPhong"));
				dsPhong.add(new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), rs.getString("viTri"),
						rs.getString("trangThaiPhong"), loai, rs.getInt("sucChua")));
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
		return dsPhong;
	}

	// lấy danh sách theo loại Phòng
	public ArrayList<Phong> getDSTheoLoaiPhong(String maLoaiPhong) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement("select * from Phong where maLoaiPhong = ?");
			pstm.setString(1, maLoaiPhong);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				LoaiPhong loai = daoLoaiPhong.getLoaiTheoMa(rs.getString("maLoaiPhong"));
				dsPhong.add(new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), rs.getString("viTri"),
						rs.getString("trangThaiPhong"), loai, rs.getInt("sucChua")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}

	// lấy danh sách theo loại Phòng và trạng thái phòng
	public ArrayList<Phong> getDSTheoLP_TTP(String maLoaiPhong, String trangThaiPhong) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			PreparedStatement pstm = con
					.prepareStatement("select * from Phong where maLoaiPhong = ? and trangThaiPhong = ?");
			pstm.setString(1, maLoaiPhong);
			pstm.setString(2, trangThaiPhong);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				LoaiPhong loai = daoLoaiPhong.getLoaiTheoMa(rs.getString("maLoaiPhong"));
				dsPhong.add(new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), rs.getString("viTri"),
						rs.getString("trangThaiPhong"), loai, rs.getInt("sucChua")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}

	// lấy danh sách phòng theo mã hoá đơn
	public ArrayList<Phong> getDSTheoMaHD(String maHD) {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement("select * from ChiTietHoaDon_Phong where maHD = ? ");
			pstm.setString(1, maHD);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Phong phong = this.getPhongTheoMa(rs.getString("maPhong"));
				dsPhong.add(phong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}

	// lấy danh sách phòng có phiếu đặt phòng
	public ArrayList<Phong> getDSCoPhieuDat() {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		Connection con = ConnectDB.getInstance().getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement(
					"select * from Phong where maPhong in ( select maPhong  FROM PhieuDatPhong WHERE trangThai= ? )");
			pstm.setString(1, "Đang chờ");
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Phong phong = this.getPhongTheoMa(rs.getString("maPhong"));
				dsPhong.add(phong);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhong;
	}

	// thêm phòng mới
	public boolean addPhong(Phong phongMoi) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("insert into Phong values(?,?,?,?,?,?)");
			pstm.setString(1, phongMoi.getMaPhong());
			pstm.setString(2, phongMoi.getTenPhong());
			pstm.setString(3, phongMoi.getViTri());
			pstm.setString(4, phongMoi.getTrangThaiPhong());
			pstm.setString(5, phongMoi.getLoaiPhong().getMaLoaiPhong());
			pstm.setInt(6, phongMoi.getSucChua());
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
	public boolean capNhapPhong(Phong phongMoi) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement(
					"update Phong set tenPhong = ?, viTri = ?, trangThaiPhong = ?, maLoaiPhong = ?, sucChua= ? where maPhong = ?");
			pstm.setString(1, phongMoi.getTenPhong());
			pstm.setString(2, phongMoi.getViTri());
			pstm.setString(3, phongMoi.getTrangThaiPhong());
			pstm.setString(4, phongMoi.getLoaiPhong().getMaLoaiPhong());
			pstm.setInt(5, phongMoi.getSucChua());
			pstm.setString(6, phongMoi.getMaPhong());
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
	public boolean xoaPhongTheoMa(String maPhongXoa) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("delete from Phong where maPhong = ?");
			pstm.setString(1, maPhongXoa);
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

	public ArrayList<Phong> search(String giaTriTimKiem) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<Phong> ds = new ArrayList<Phong>();
		try {
			int so = -1;
			if (giaTriTimKiem.isEmpty() == false && giaTriTimKiem.chars().allMatch(Character::isDigit)) {
				so = Integer.parseInt(giaTriTimKiem);
			}
			String query = "select a.maPhong, a.tenPhong, a.viTri, a.maLoaiPhong, a.sucChua, a.trangThaiPhong from Phong a inner join LoaiPhong b on a.maLoaiPhong = b.maLoaiPhong where maPhong like N'%"
					+ giaTriTimKiem + "%' or tenPhong like N'%" + giaTriTimKiem + "%' or viTri like N'%" + giaTriTimKiem
					+ "%' or trangThaiPhong like N'%" + giaTriTimKiem + "%' or tenLoaiPhong like N'%" + giaTriTimKiem
					+ "%' or sucChua = " + so;
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				LoaiPhong loai = daoLoaiPhong.getLoaiTheoMa(rs.getString("maLoaiPhong"));
				ds.add(new Phong(rs.getString("maPhong"), rs.getString("tenPhong"), rs.getString("viTri"),
						rs.getString("trangThaiPhong"), loai, rs.getInt("sucChua")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
