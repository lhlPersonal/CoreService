package com.dview.coreServer.bo;

import java.util.Date;

public class UserAccessHistoryData {
	private Date loginTime;
	private String latestBrowser;

	public UserAccessHistoryData() {
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLatestBrowser() {
		return latestBrowser;
	}

	public void setLatestBrowser(String latestBrowser) {
		this.latestBrowser = latestBrowser;
	}
}