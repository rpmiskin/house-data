package in.misk.rest;

public class SaleDataPage {

	int pageIndex;
	int pageSize;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(final int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(final int totalPages) {
		this.totalPages = totalPages;
	}

	public SaleData[] getSales() {
		return sales;
	}

	public void setSales(final SaleData[] sales) {
		this.sales = sales;
	}

	int totalPages;
	SaleData[] sales;

}
