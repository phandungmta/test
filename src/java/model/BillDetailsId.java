package model;
// Generated May 4, 2019 8:01:01 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BillDetailsId generated by hbm2java
 */
@Embeddable
public class BillDetailsId implements java.io.Serializable {

	private int productId;
	private int billId;

	public BillDetailsId() {
	}

	public BillDetailsId(int productId, int billId) {
		this.productId = productId;
		this.billId = billId;
	}

	@Column(name = "ProductID", nullable = false)
	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Column(name = "BillID", nullable = false)
	public int getBillId() {
		return this.billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BillDetailsId))
			return false;
		BillDetailsId castOther = (BillDetailsId) other;

		return (this.getProductId() == castOther.getProductId()) && (this.getBillId() == castOther.getBillId());
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProductId();
		result = 37 * result + this.getBillId();
		return result;
	}

}
