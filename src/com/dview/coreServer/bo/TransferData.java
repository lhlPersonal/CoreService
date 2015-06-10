package com.dview.coreServer.bo;

import java.util.List;

import com.dview.coreServer.po.InventoryDevice;

public class TransferData {

	private List<InventoryDevice> listData;
	private long pageCount;

	public void setListData(List<InventoryDevice> listData) {
		this.listData = listData;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public List<InventoryDevice> getListData() {
		return listData;
	}

	public long getPageCount() {
		return pageCount;
	}
}
