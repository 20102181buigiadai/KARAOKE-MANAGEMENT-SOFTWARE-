package entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class NhanVien {
	private String maNhanVien;
	private String tenNhanVien;
	private boolean gioiTinh;
	private LocalDate ngaySinh;
	private String cmnd;
	private String diaChi;
	private String soDienThoai;
	private LoaiNhanVien loaiNhanVien;
	private byte[] hinhAnh;
	private String email;

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public boolean getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public LoaiNhanVien getLoaiNhanVien() {
		return loaiNhanVien;
	}

	public void setLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
		this.loaiNhanVien = loaiNhanVien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	//tạo nhân viên mới không có email
	public NhanVien(String maNhanVien, String tenNhanVien, boolean gioiTinh, LocalDate ngaySinh, String cmnd,
			String diaChi, String soDienThoai, LoaiNhanVien loaiNhanVien, byte[] hinhAnh) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.cmnd = cmnd;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.loaiNhanVien = loaiNhanVien;
		this.hinhAnh = hinhAnh;
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", gioiTinh=" + gioiTinh
				+ ", ngaySinh=" + ngaySinh + ", cmnd=" + cmnd + ", diaChi=" + diaChi + ", soDienThoai=" + soDienThoai
				+ ", loaiNhanVien=" + loaiNhanVien + ", hinhAnh=" + Arrays.toString(hinhAnh) + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public NhanVien(String maNhanVien, String tenNhanVien, boolean gioiTinh, LocalDate ngaySinh, String cmnd,
			String diaChi, String soDienThoai, LoaiNhanVien loaiNhanVien, byte[] hinhAnh, String email) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.cmnd = cmnd;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.loaiNhanVien = loaiNhanVien;
		this.hinhAnh = hinhAnh;
		this.email = email;
	}

}
