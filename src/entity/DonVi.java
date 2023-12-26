package entity;

public class DonVi {
	private String maDonVi, tenDonVi;

	public String getMaDonVi() {
		return maDonVi;
	}

	public void setMaDonVi(String maDoVi) {
		this.maDonVi = maDoVi;
	}

	public String getTenDonVi() {
		return tenDonVi;
	}

	public void setTenDonVi(String tenDonVi) {
		this.tenDonVi = tenDonVi;
	}

	public DonVi(String maDoVi, String tenDonVi) {
		this.setMaDonVi(maDoVi);
		this.setTenDonVi(tenDonVi);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maDonVi == null) ? 0 : maDonVi.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		DonVi other = (DonVi) obj;
		if (maDonVi == null) {
			if (other.maDonVi != null)
				return false;
		} else if (!maDonVi.equals(other.maDonVi))
			return false;
		return true;
	}

}
