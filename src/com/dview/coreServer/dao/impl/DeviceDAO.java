package com.dview.coreServer.dao.impl;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bson.types.ObjectId;

import com.dview.coreServer.bo.EventData;
import com.dview.coreServer.bo.TransferData;
import com.dview.coreServer.dao.IDeviceDAO;
import com.dview.coreServer.po.DeviceGroupBase;
import com.dview.coreServer.po.InventoryDevice;
import com.dview.coreServer.util.Constant;
import com.dview.coreServer.util.CoreSvrUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DeviceDAO extends CommonDAO implements IDeviceDAO {

	@Override
	public TransferData getDevices(int pageSize, int pageIndex,
			boolean isManaged) {
		// TODO Auto-generated method stub
		TransferData data = new TransferData();
		DBObject query = new BasicDBObject();

		query.put("managed", isManaged);

		List<InventoryDevice> devices = getMongoDao().findObjects(
				Constant.TABLE_InvDev, query, InventoryDevice.class).readAll();
		if (devices != null && !devices.isEmpty()) {
			int size = devices.size();
			int start = (pageIndex - 1) * pageSize;

			data.setPageCount(CoreSvrUtil.getPageCount(size, pageSize));
			data.setListData(devices.subList(start,
					start + pageSize < size ? start + pageSize : size));
		}
		return data;
	}

	@Override
	public TransferData getDevicesbyGroupId(int pageSize, int pageIndex,
			String devGrpId) {
		// TODO Auto-generated method stub
		TransferData data = new TransferData();

		if (!CoreSvrUtil.IsNullOrEmpty(devGrpId)) {
			// DBObject query = new BasicDBObject();
			// query.put("devGrpId", devGrpId);
			//
			// Statement st = getMongoDao()
			// .createStatement(
			// MessageFormat
			// .format("FROM {0} WHERE devGrpId={3} SELECT * LIMIT {1},{2}",
			// Constant.TABLE_InvDev, pageSize
			// * (pageIndex - 1),
			// pageSize, devGrpId));

			long total = getMongoDao().getCollection(Constant.TABLE_InvDev)
					.count();
			DBObject query = new BasicDBObject();

			data.setPageCount(CoreSvrUtil.getPageCount(total, pageSize));
			query.put("managed", true);
			query.put("devGrpId", devGrpId);

			List<InventoryDevice> devices = getMongoDao().findObjects(
					Constant.TABLE_InvDev, query, InventoryDevice.class)
					.readAll();
			if (devices != null && !devices.isEmpty()) {
				int start = (pageIndex - 1) * pageSize;

				data.setListData(devices.subList(start, start + pageSize));
			}
		}
		return data;
	}

	// region device group增删改查

	@Override
	public DeviceGroupBase getDevGroupById(String devGroupId) {
		// TODO Auto-generated method stub
		if (!CoreSvrUtil.IsNullOrEmpty(devGroupId)) {
			DBObject query = new BasicDBObject();
			query.put("_id", new ObjectId(devGroupId));
			return getMongoDao().findObject(Constant.TABLE_DevGroup, query,
					DeviceGroupBase.class);
		}
		return null;
	}

	@Override
	public void delDevGroup(String devGroupId) {
		// TODO Auto-generated method stub
		if (!CoreSvrUtil.IsNullOrEmpty(devGroupId)) {
			getMongoDao().deleteObject(Constant.TABLE_DevGroup,
					new ObjectId(devGroupId));
		}
	}

	@Override
	public void saveOrUpdateDevGroup(DeviceGroupBase devGroup) {
		// TODO Auto-generated method stub
		if (devGroup != null) {
			if (CoreSvrUtil.IsNullOrEmpty(devGroup.getId())) {
				getMongoDao().createObject(Constant.TABLE_DevGroup, devGroup);
			} else {
				getMongoDao().updateObject(Constant.TABLE_DevGroup,
						new ObjectId(devGroup.getId()), devGroup);
			}
		}
	}

	@Override
	public List<DeviceGroupBase> getDevGroups() {

		return getMongoDao().createQuery()
				.setCollection(Constant.TABLE_DevGroup)
				.findObjects(DeviceGroupBase.class).readAll();
	}

	@Override
	public void GeneratorDevsData() {
		InventoryDevice[] devs = new InventoryDevice[10000];
		Random random = new Random();
		String macString = null;
		String uuidString = null;
		// TODO Auto-generated method stub
		for (int i = 0; i < 10000; i++) {
			InventoryDevice device = new InventoryDevice();
			uuidString = UUID.randomUUID().toString();
			macString = uuidString.substring(24).toUpperCase();

			device.setDevName(MessageFormat.format("device{0}", i));
			device.setDevIP(MessageFormat.format("{0}.{1}.{2}.{3}",
					random.nextInt(254), random.nextInt(254),
					random.nextInt(254), random.nextInt(254)));
			device.setDevModel(MessageFormat.format("DWS-{0}", i));
			device.setDevMAC(MessageFormat.format("{0}:{1}:{2}:{3}:{4}:{5}",
					macString.substring(0, 2), macString.substring(2, 4),
					macString.substring(4, 6), macString.substring(6, 8),
					macString.substring(8, 10), macString.substring(10, 12)));

			device.setDevType(MessageFormat.format("Switch-{0}", i));
			device.setDevSN(uuidString.substring(0, 8));
			device.setFW(MessageFormat.format("FW{0}", i));
			device.setHW(MessageFormat.format("HW{0}", i));
			device.setDiscoverTime(Date.from(Instant.now().plusSeconds(i)));
			device.setDevGroups(new ArrayList<DeviceGroupBase>());

			if (i % 3 == 0) {
				device.setManaged(true);
			}

			devs[i] = device;
		}

		getMongoDao().createObjects(Constant.TABLE_InvDev, devs);
	}

	// endregion

	@Override
	public EventData getEventData() {
		// TODO Auto-generated method stub
		EventData eventData = new EventData();
		Random random = new Random();

		eventData.setCritical(random.nextInt(200));
		eventData.setWarn(random.nextInt(300));
		eventData.setInfo(random.nextInt(400));
		eventData.setSystem(random.nextInt(500));

		return eventData;
	}
}
