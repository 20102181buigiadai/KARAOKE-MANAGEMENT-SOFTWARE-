package entity;

public class LoaiDichVu {
	private String maLoaiDV, tenLoaiDV;

	public String getMaLoaiDV() {
		return maLoaiDV;
	}

	public void setMaLoaiDV(String maLoaiDV) {
		this.maLoaiDV = maLoaiDV;
	}

	public String getTenLoaiDV() {
		return tenLoaiDV;
	}

	public void setTenLoaiDV(String tenLoaiDV) {
		this.tenLoaiDV = tenLoaiDV;
	}

	public LoaiDichVu(String maLoaiDV, String tenLoaiDV) {
		this.setMaLoaiDV(maLoaiDV);
		this.setTenLoaiDV(tenLoaiDV);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((maLoaiDV == null) ? 0 : maLoaiDV.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		LoaiDichVu other = (LoaiDichVu) obj;
		if (maLoaiDV == null) {
			if (other.maLoaiDV != null)
				return false;
		} else if (!maLoaiDV.equals(other.maLoaiDV))
			return false;
		return true;
	}

}
