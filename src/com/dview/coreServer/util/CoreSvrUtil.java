package com.dview.coreServer.util;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class CoreSvrUtil {

	/**
	 * 
	 * @param logName
	 *            null鎴栬�杈撳叆1涓瓧绗︿覆
	 * @return
	 */
	public static Logger getLogger(String... logName) {
		if (logName == null || logName.length == 0) {
			return LoggerFactory.getLogger(Constant.DEFAULT_LOG);
		}
		;
		return LoggerFactory.getLogger(logName[0]);
	}

	public static Properties getPorperties(String fileName) {
		try {
			Resource resource = new ClassPathResource(MessageFormat.format(
					"properties/{0}.properties", fileName));
			Properties props = PropertiesLoaderUtils.loadProperties(resource);

			return props;

		} catch (Exception e) {

			return null;
			// TODO: handle exception
		}
	}

	public static long getPageCount(long total, int pageSize) {
		if (pageSize > 0) {
			long count = total / pageSize;

			return count > 0 ? (total % pageSize > 0 ? count + 1 : count) : 1;
		}

		return 0;
	}
	
	public static boolean IsNullOrEmpty(String arg) {
		return Objects.isNull(arg) || arg.trim().isEmpty();
	}
}
