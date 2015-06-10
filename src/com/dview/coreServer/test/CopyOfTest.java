package com.dview.coreServer.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import com.dview.coreServer.dao.impl.CommonDAO;

public class CopyOfTest implements InvocationHandler, FactoryBean<CommonDAO> {
	private static Logger log = LoggerFactory.getLogger(CopyOfTest.class);
	/**
	 * 新的dao
	 */
	@Resource
	private CommonDAO bidProxyDAONew;
	/**
	 * 老的dao
	 */
	@Resource
	private CommonDAO proxyBidRepo;

	@Resource
	private boolean diamondSwitchConfig;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// 如果开关打开那么调用新的DAO
		if (diamondSwitchConfig) {
			log.warn("bid_proxy user new table..........................................");
			return method.invoke(bidProxyDAONew, args);
		}
		return method.invoke(proxyBidRepo, args);
	}

	@Override
	public CommonDAO getObject() throws Exception {
		Object proxyObj = Proxy.newProxyInstance(this.getClass()
				.getClassLoader(), new Class[] { CommonDAO.class }, this);
		return (CommonDAO) proxyObj;
	}

	@Override
	public Class<CommonDAO> getObjectType() {
		return CommonDAO.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}