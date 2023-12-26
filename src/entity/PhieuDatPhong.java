package entity;

import java.time.LocalDateTime;

public class PhieuDatPhong {
	private String maPhieuDatPhong;
	private LocalDateTime gioTaoPhieu;
	private LocalDateTime gioVaoPhong;
	private double tongGioDat;
	private KhachHang khachHang;
	private Phong phong;
	private NhanVien nhanVien;
	private String trangThai;

	public String getMaPhieuDatPhong() {
		return maPhieuDatPhong;
	}

	public void setMaPhieuDatPhong(String maPhieuDatPhong) {
		this.maPhieuDatPhong = maPhieuDatPhong;
	}

	public LocalDateTime getGioTaoPhieu() {
		return gioTaoPhieu;
	}

	public void setGioTaoPhieu(LocalDateTime gioTaoPhieu) {
		this.gioTaoPhieu = gioTaoPhieu;
	}

	public LocalDateTime getGioVaoPhong() {
		return gioVaoPhong;
	}

	public void setGioVaoPhong(LocalDateTime gioVaoPhong) {
		this.gioVaoPhong = gioVaoPhong;
	}

	public double getTongGioDat() {
		return tongGioDat;
	}

	public void setTongGioDat(double tongGioDat) {
		this.tongGioDat = tongGioDat;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public PhieuDatPhong(String maPhieuDatPhong, LocalDateTime gioTaoPhieu, LocalDateTime gioVaoPhong,
			double tongGioDat, KhachHang khachHang, Phong phong, NhanVien nhanVien, String trangThai) {
		super();
		this.maPhieuDatPhong = maPhieuDatPhong;
		this.gioTaoPhieu = gioTaoPhieu;
		this.gioVaoPhong = gioVaoPhong;
		this.tongGioDat = tongGioDat;
		this.khachHang = khachHang;
		this.phong = phong;
		this.nhanVien = nhanVien;
		this.trangThai = trangThai;
	}

	public PhieuDatPhong() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PhieuDatPhong [maPhieuDatPhong=" + maPhieuDatPhong + ", gioTaoPhieu=" + gioTaoPhieu + ", gioVaoPhong="
				+ gioVaoPhong + ", tongGioDat=" + tongGioDat + ", khachHang=" + khachHang + ", phong=" + phong
				+ ", nhanVien=" + nhanVien + ", trangThai=" + trangThai + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maPhieuDatPhong == null) ? 0 : maPhieuDatPhong.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuDatPhong other = (PhieuDatPhong) obj;
		if (maPhieuDatPhong == null) {
			if (other.maPhieuDatPhong != null)
				return false;
		} else if (!maPhieuDatPhong.equals(other.maPhieuDatPhong))
			return false;
		return true;
	}

}
