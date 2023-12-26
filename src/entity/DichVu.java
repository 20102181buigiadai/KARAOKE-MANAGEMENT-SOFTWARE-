package entity;

import java.util.Objects;

public class DichVu {
	private String maDV;
	private String tenDV;
	private long gia;
	private String ghiChu;
	private int soLuong;
	private DonVi donVi;
	private LoaiDichVu loai;
	private byte[] hinhAnh;

	public String getMaDV() {
		return maDV;
	}

	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}

	public String getTenDV() {
		return tenDV;
	}

	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}

	public long getGia() {
		return gia;
	}

	public void setGia(long gia) {
		this.gia = gia;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public DonVi getDonVi() {
		return donVi;
	}

	public void setDonVi(DonVi donVi) {
		this.donVi = donVi;
	}

	public LoaiDichVu getLoai() {
		return loai;
	}

	public void setLoai(LoaiDichVu loai) {
		this.loai = loai;
	}

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maDV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		DichVu other = (DichVu) obj;
		return Objects.equals(maDV, other.maDV);
	}

	public DichVu(String maDV, String tenDV, long gia, String ghiChu, int soLuong, DonVi donVi, LoaiDichVu loai,
			byte[] hinhAnh) {
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.gia = gia;
		this.ghiChu = ghiChu;
		this.soLuong = soLuong;
		this.donVi = donVi;
		this.loai = loai;
		this.hinhAnh = hinhAnh;
	}

}
