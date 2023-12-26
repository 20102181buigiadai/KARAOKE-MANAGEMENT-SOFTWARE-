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

public class DAO_DichVu {
	private DAO_DonVi daoDonVi = new DAO_DonVi();
	private DAO_LoaiDichVu daoLoaiDV = new DAO_LoaiDichVu();

	public ArrayList<DichVu> getAllDichVu() {
		ArrayList<DichVu> ds = new ArrayList<DichVu>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from DichVu");
			while (rs.next()) {
				DonVi donVi = daoDonVi.getDonViTheoMa(rs.getString(6));
				LoaiDichVu loai = daoLoaiDV.getLoaiTheoMa(rs.getString(7));
				byte[] hinhAnh = rs.getBytes(8);
				ds.add(new DichVu(rs.getString(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getInt(5), donVi,
						loai, hinhAnh));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public boolean addDichVu(DichVu dichVu) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("insert into DichVu values(?,?,?,?,?,?,?,?)");
			pstm.setString(1, dichVu.getMaDV());
			pstm.setString(2, dichVu.getTenDV());
			pstm.setDouble(3, dichVu.getGia());
			pstm.setString(4, dichVu.getGhiChu());
			pstm.setInt(5, dichVu.getSoLuong());
			pstm.setString(6, dichVu.getDonVi().getMaDonVi());
			pstm.setString(7, dichVu.getLoai().getMaLoaiDV());
			pstm.setBytes(8, dichVu.getHinhAnh());
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
	 * Cập nhật dịch vụ theo mã.
	 * 
	 * @param dichVu:     dịch vụ cần cập nhật
	 * @param capNhatAnh: có cập nhật ảnh không, true: có, false: không
	 * @return true: nếu cập nhật thành công, false: nếu cập nhật thất bại.
	 */
	public boolean updateDichVu(DichVu dichVu, boolean capNhatAnh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			if (capNhatAnh == true) {
				// Có cập nhật ảnh
				pstm = con.prepareStatement(
						"update DichVu set tenDV = ?, gia = ?, ghiChu = ?, soLuong = ?, maDonVi = ?, maLoaiDV = ?, hinhAnh = ? where maDichVu  = ?");
				pstm.setString(1, dichVu.getTenDV());
				pstm.setDouble(2, dichVu.getGia());
				pstm.setString(3, dichVu.getGhiChu());
				pstm.setInt(4, dichVu.getSoLuong());
				pstm.setString(5, dichVu.getDonVi().getMaDonVi());
				pstm.setString(6, dichVu.getLoai().getMaLoaiDV());
				pstm.setBytes(7, dichVu.getHinhAnh());
				pstm.setString(8, dichVu.getMaDV());
				n = pstm.executeUpdate();
			} else {
				// Không cập nhật ảnh
				pstm = con.prepareStatement(
						"update DichVu set tenDV = ?, gia = ?, ghiChu = ?, soLuong = ?, maDonVi = ?, maLoaiDV = ? where maDichVu  = ?");
				pstm.setString(1, dichVu.getTenDV());
				pstm.setDouble(2, dichVu.getGia());
				pstm.setString(3, dichVu.getGhiChu());
				pstm.setInt(4, dichVu.getSoLuong());
				pstm.setString(5, dichVu.getDonVi().getMaDonVi());
				pstm.setString(6, dichVu.getLoai().getMaLoaiDV());
				pstm.setString(7, dichVu.getMaDV());
				n = pstm.executeUpdate();
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
		return n > 0;
	}

	public boolean deleteDichVuTheoMa(String maDV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pstm = null;
		int n = 0;
		try {
			pstm = con.prepareStatement("delete from DichVu where maDichVu = ?");
			pstm.setString(1, maDV);
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

	public DichVu getDichVuTheoMa(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		DichVu dichVu = null;
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from DichVu where maDichVu = ?");
			pstm.setString(1, ma);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				DonVi donVi = daoDonVi.getDonViTheoMa(rs.getString(6));
				LoaiDichVu loai = daoLoaiDV.getLoaiTheoMa(rs.getString(7));
				byte[] hinhAnh = rs.getBytes(8);
				dichVu = new DichVu(rs.getString(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getInt(5),
						donVi, loai, hinhAnh);
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
		return dichVu;
	}

	public ArrayList<DichVu> getDSDichVuTheoTenTuongDoi(String ten) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<DichVu> ds = new ArrayList<DichVu>();
		try {
			String query = "select * from DichVu where tenDV like N'%" + ten + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				DonVi donVi = daoDonVi.getDonViTheoMa(rs.getString(6));
				LoaiDichVu loai = daoLoaiDV.getLoaiTheoMa(rs.getString(7));
				byte[] hinhAnh = rs.getBytes(8);
				ds.add(new DichVu(rs.getString(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getInt(5), donVi,
						loai, hinhAnh));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}

	public ArrayList<DichVu> getDSDichVuTheoLoai(LoaiDichVu loai) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<DichVu> ds = new ArrayList<DichVu>();
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement("select * from DichVu where maLoaiDV = ?");
			pstm.setString(1, loai.getMaLoaiDV());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				DonVi donVi = daoDonVi.getDonViTheoMa(rs.getString(6));
				byte[] hinhAnh = rs.getBytes(8);
				ds.add(new DichVu(rs.getString(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getInt(5), donVi,
						loai, hinhAnh));
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

	public byte[] getHinhAnhTheoMaDichVu(String maDV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		byte[] anh = null;
		try {
			String query = "select hinhAnh from DichVu where maDichVu = '" + maDV + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				anh = rs.getBytes(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return anh;
	}

	public ArrayList<DichVu> search(String giaTriTimKiem) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		ArrayList<DichVu> ds = new ArrayList<DichVu>();
		try {
			int so = -1;
			if (giaTriTimKiem.isEmpty() == false && giaTriTimKiem.chars().allMatch(Character::isDigit)) {
				so = Integer.parseInt(giaTriTimKiem);
			}
			String query = "select a.maDichVu, a.tenDV, a.gia, a.ghiChu, a.soLuong, a.hinhAnh, b.maDonVi, c.maLoaiDV from DichVu a inner join DonVi b on a.maDonVi = b.maDonVi inner join LoaiDichVu c on a.maLoaiDV = c.maLoaiDV where maDichVu like N'%"
					+ giaTriTimKiem + "%' or tenDV like N'%" + giaTriTimKiem + "%' or gia = " + so
					+ " or ghiChu like N'%" + giaTriTimKiem + "%' or soLuong = " + so + " or tenDonVi like N'%"
					+ giaTriTimKiem + "%' or tenLoaiDV like N'%" + giaTriTimKiem + "%'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				DonVi donVi = daoDonVi.getDonViTheoMa(rs.getString("maDonVi"));
				LoaiDichVu loai = daoLoaiDV.getLoaiTheoMa(rs.getString("maLoaiDV"));
				byte[] hinhAnh = rs.getBytes("hinhAnh");
				ds.add(new DichVu(rs.getString("maDichVu"), rs.getString("tenDV"), rs.getLong("gia"),
						rs.getString("ghiChu"), rs.getInt("soLuong"), donVi, loai, hinhAnh));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
}
