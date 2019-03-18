package com.usedBooks.result;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 分页参数
 * @param <T>
 */
public class Pager<T> {

	private long total;
	private int pageCount;
	private int pageIndex;
	private List<T> rows;

	public Pager(PageInfo pageInfo) {
		this.total = pageInfo.getTotal();
		this.pageCount = pageInfo.getPageSize();
		this.pageIndex = pageInfo.getPages();
		this.rows = pageInfo.getList();
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
