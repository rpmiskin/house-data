package in.misk.rest;

import java.util.Date;

public class SaleData {
	private String id;
	private int price;
	private Date saleDate;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(final int price) {
		this.price = price;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(final Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(final String postCode) {
		this.postCode = postCode;
	}

	private String postCode;
}
