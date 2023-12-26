package entity;

import java.util.Objects;

public class Phong {
	private String maPhong, tenPhong, viTri, trangThaiPhong;
	private LoaiPhong loaiPhong;
	private int sucChua;

	public Phong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public String getViTri() {
		return viTri;
	}

	public void setViTri(String viTri) {
		this.viTri = viTri;
	}

	public String getTrangThaiPhong() {
		return trangThaiPhong;
	}

	public void setTrangThaiPhong(String trangThaiPhong) {
		this.trangThaiPhong = trangThaiPhong;
	}

	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}

	public int getSucChua() {
		return sucChua;
	}

	public void setSucChua(int sucChua) {
		this.sucChua = sucChua;
	}

	public Phong(String maPhong, String tenPhong, String viTri, String trangThaiPhong, LoaiPhong loaiPhong,
			int sucChua) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.viTri = viTri;
		this.trangThaiPhong = trangThaiPhong;
		this.loaiPhong = loaiPhong;
		this.sucChua = sucChua;
	}

}
