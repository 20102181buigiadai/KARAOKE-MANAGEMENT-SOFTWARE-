package entity;

import java.util.Objects;

public class LoaiNhanVien {
	private String maLoai, tenLoai;
	private Float heSoLuong;

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public Float getHeSoLuong() {
		return heSoLuong;
	}

	public void setHeSoLuong(Float heSoLuong) {
		this.heSoLuong = heSoLuong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		LoaiNhanVien other = (LoaiNhanVien) obj;
		return Objects.equals(maLoai, other.maLoai);
	}

	public LoaiNhanVien(String maLoai, String tenLoai, Float heSoLuong) {
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
		this.heSoLuong = heSoLuong;
	}

	@Override
	public String toString() {
		return "LoaiNhanVien [maLoai=" + maLoai + ", tenLoai=" + tenLoai + ", heSoLuong=" + heSoLuong + "]";
	}

}
