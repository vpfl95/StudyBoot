package com.iu.home.util;

import lombok.Data;

@Data
public class Pager {
	private Long startRow;
	private Long lastRow;
	private Long page;
	private Long perPage;
	private Long totalPage;
	private Long perBlock;
	private Long block;
	
	public Pager() {
		this.page= 1L;
		this.perPage = 10L;
	}
	
	public void setRowNum() {
		this.startRow = (this.getPage()-1) * this.getPerPage();
		this.lastRow = this.getPage() * this.getPerPage();
	}
	
	public void setNum(Long totalCount) {
		this.totalPage = totalCount % this.getPerPage() == 0 ? totalCount/this.getPerPage() : totalCount/this.getPerPage() + 1;
		
	}
	
}
