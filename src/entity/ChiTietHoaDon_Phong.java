package entity;

import java.time.LocalDateTime;

import javax.swing.JOptionPane;

public class ChiTietHoaDon_Phong {
	private HoaDon hoaDon;
	private Phong phong;
	private LocalDateTime gioVao, gioRa;

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public LocalDateTime getGioVao() {
		return gioVao;
	}

	public void setGioVao(LocalDateTime gioVao) {
		this.gioVao = gioVao;
	}

	public LocalDateTime getGioRa() {
		return gioRa;
	}

	public void setGioRa(LocalDateTime gioRa) {
		this.gioRa = gioRa;
	}

	public ChiTietHoaDon_Phong(HoaDon hoaDon, Phong phong, LocalDateTime gioVao, LocalDateTime gioRa) {
		super();
		this.hoaDon = hoaDon;
		this.phong = phong;
		this.gioVao = gioVao;
		this.gioRa = gioRa;
	}

	public ChiTietHoaDon_Phong() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hoaDon == null) ? 0 : hoaDon.hashCode());
		result = prime * result + ((phong == null) ? 0 : phong.hashCode());
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
		ChiTietHoaDon_Phong other = (ChiTietHoaDon_Phong) obj;
		if (hoaDon == null) {
			if (other.hoaDon != null)
				return false;
		} else if (!hoaDon.equals(other.hoaDon))
			return false;
		if (phong == null) {
			if (other.phong != null)
				return false;
		} else if (!phong.equals(other.phong))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon_Phong [hoaDon=" + hoaDon + ", phong=" + phong + ", gioVao=" + gioVao + ", gioRa=" + gioRa
				+ "]";
	}

	public int tinhSoPhut() {
		return ((gioRa.getHour() * 60 + gioRa.getMinute()) - (gioVao.getHour() * 60 + gioVao.getMinute()));
	}

	public int lamTronSoPhut() {
		int soPhutLamTron = tinhSoPhut() % 60;
		if (soPhutLamTron > 0 && soPhutLamTron <= 15) {
			soPhutLamTron = 15;
		} else {
			if (soPhutLamTron > 15 && soPhutLamTron <= 30) {
				soPhutLamTron = 30;
			} else {
				if (soPhutLamTron > 30 && soPhutLamTron < 45) {
					soPhutLamTron = 45;
				} else {
					if (soPhutLamTron > 45 && soPhutLamTron < 60) {
						soPhutLamTron = 60;
					}
				}
			}
		}
		return soPhutLamTron;
	}

	public long tinhTienPhong() {
		int tongThoiGian = tinhSoPhut(); // Tổng thời gian đổi ra phút
		int soGio = (tongThoiGian - tongThoiGian % 60) / 60; // Số giờ nguyên

		return (long) ((soGio +(double)  lamTronSoPhut() / 60) * phong.getLoaiPhong().getGia());
	}
}
