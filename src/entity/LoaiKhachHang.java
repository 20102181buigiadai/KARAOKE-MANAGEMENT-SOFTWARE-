package entity;

import java.util.Objects;

public class LoaiKhachHang {
	private String maLoaiKhachHang;
	private String tenLoaiKhachHang;

	public String getMaLoaiKhachHang() {
		return maLoaiKhachHang;
	}

	public void setMaLoaiKhachHang(String maLoaiKhachHang) {
		this.maLoaiKhachHang = maLoaiKhachHang;
	}

	public String getTenLoaiKhachHang() {
		return tenLoaiKhachHang;
	}

	public void setTenLoaiKhachHang(String tenLoaiKhachHang) {
		this.tenLoaiKhachHang = tenLoaiKhachHang;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoaiKhachHang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		LoaiKhachHang other = (LoaiKhachHang) obj;
		return Objects.equals(maLoaiKhachHang, other.maLoaiKhachHang);
	}

	public LoaiKhachHang(String maLoaiKhachHang, String tenLoaiKhachHang) {
		this.maLoaiKhachHang = maLoaiKhachHang;
		this.tenLoaiKhachHang = tenLoaiKhachHang;
	}

}
