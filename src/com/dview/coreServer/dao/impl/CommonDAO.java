package com.dview.coreServer.dao.impl;

import org.slf4j.Logger;

import com.dview.coreServer.module.DBModule;
import com.dview.coreServer.util.CoreSvrUtil;
import com.googlecode.mjorm.MongoDao;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class CommonDAO {
	private DBModule dbModule;

	public void setDbModule(DBModule dbModule) {
		this.dbModule = dbModule;
	}

	public DBModule getDbModule() {
		return dbModule;
	}

	public DB getDB() {
		return dbModule.getDB();

	}

	public DBCollection getCollection(String tableName) {
		return getDB().getCollection(tableName);
	}

	public MongoDao getMongoDao() {
		return dbModule.getDao();
	}

	public Logger getLogger() {
		return CoreSvrUtil.getLogger();
	}
}
