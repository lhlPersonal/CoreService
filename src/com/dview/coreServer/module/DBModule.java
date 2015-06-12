package com.dview.coreServer.module;

import java.net.UnknownHostException;
import java.text.MessageFormat;

import com.dview.coreServer.po.DeviceGroupBase;
import com.dview.coreServer.po.InventoryDevice;
import com.dview.coreServer.po.User;
import com.dview.coreServer.util.CoreSvrUtil;
import com.dview.coreServer.util.DVEnum;
import com.googlecode.mjorm.MongoDao;
import com.googlecode.mjorm.MongoDaoImpl;
import com.googlecode.mjorm.annotations.AnnotationsDescriptorObjectMapper;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class DBModule extends BaseModule {

	private DB db;
	private AnnotationsDescriptorObjectMapper mapper;
	private MongoDao dao;

	public DB getDB() {
		return db;
	}

	public MongoDao getDao() {
		return dao;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			state = DVEnum.ModuleState.Ready;
			MongoClient mongo = new MongoClient(
					com.dview.coreServer.util.CoreSvrUtil.getPorperties("conn")
							.getProperty("mongo_url"));
			db = mongo.getDB("JDView7");

			mapper = new AnnotationsDescriptorObjectMapper();

			mapper.addClass(User.class);
			mapper.addClass(DeviceGroupBase.class);
			mapper.addClass(InventoryDevice.class);
			dao = new MongoDaoImpl(db, mapper);

			CoreSvrUtil.getLogger().info("Database is ready");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			state = DVEnum.ModuleState.Error;
			CoreSvrUtil.getLogger().error(
					MessageFormat.format("Database is error,message:{0}",
							e.getMessage()));
		}
	}

	@Override
	public void stop() {
		try {
			db.getMongo().close();
			state = DVEnum.ModuleState.Stop;
		} catch (Exception e) {
			state = DVEnum.ModuleState.Error;
		}
	}
}
