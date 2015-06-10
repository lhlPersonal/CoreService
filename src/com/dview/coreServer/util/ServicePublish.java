package com.dview.coreServer.util;

import java.util.Map;
import java.util.Map.Entry;

import javax.xml.ws.Endpoint;

import com.dview.coreServer.module.CoreModule;

/**
 * 发布core service
 * 
 * @author bulusli
 * @date 2014-09-12
 * @email hailong_li@cn.dlink.com
 */
public class ServicePublish {

	public ServicePublish(Map<String, Object> services, CoreModule module) {
		CoreSvrUtil.getLogger().info("web service start...");
		if (services != null && module != null) {
			if (module.state == DVEnum.ModuleState.Ready) {
				
				for (Entry<String, Object> entry : services.entrySet()) {
					Endpoint.publish(entry.getKey(), entry.getValue());
				}
				CoreSvrUtil.getLogger().info("publish web service success!");
			} else {
				CoreSvrUtil.getLogger().info("publish web service failed!");
			}
		}
		CoreSvrUtil.getLogger().info("web service end.");
	}
}
