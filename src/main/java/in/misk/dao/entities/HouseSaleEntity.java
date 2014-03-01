package in.misk.dao.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "HouseSale")
public class HouseSaleEntity {

	// "2009-01-09 00:00"
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	@Column
	@Id
	private String id;
	@Column
	private int price;
	@Column
	private Date saleDate;
	@Transient
	private String dateStr;
	@Column
	private String postCode;

	public int getPrice() {
		return price;
	}

	public void setPrice(final int price) {
		this.price = price;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(final String postCode) {
		this.postCode = postCode;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(final Date date) {
		this.saleDate = date;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(final String dateStr) {
		this.dateStr = dateStr;
		parseDate(dateStr);
	}

	private void parseDate(final String dateStr2) {
		try {
			saleDate = simpleDateFormat.parse(dateStr2);
		} catch (final ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}