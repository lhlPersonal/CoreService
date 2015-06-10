package com.dview.coreServer.test;

import java.util.Properties;

import javax.xml.ws.Endpoint;

import com.dview.coreServer.service.impl.DeviceService;
import com.dview.coreServer.service.impl.UserService;
import com.dview.coreServer.util.CoreSvrUtil;

public class CoreSvrTest {

	public static void main(String[] args) {

		// 閲嶆柊鍒濆鍖�
		// ServletContext sc = ServletActionContext.getServletContext();
		// WebApplicationContext wac =
		// WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		// AbstractRefreshableApplicationContext arac =
		// (AbstractRefreshableApplicationContext)wac;
		// arac.refresh();

		Properties p = com.dview.coreServer.util.CoreSvrUtil
				.getPorperties("conn");

		if (p != null) {

			Endpoint.publish(p.getProperty("userService_url"),
					new UserService());
			Endpoint.publish(p.getProperty("devService_url"),
					new DeviceService());

			CoreSvrUtil.getLogger().info("WS鍙戝竷鎴愬姛!");
		} else {
			CoreSvrUtil.getLogger().info("WS鍙戝竷澶辫触!");
		}
	}
}
