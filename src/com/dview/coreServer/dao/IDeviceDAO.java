package com.dview.coreServer.dao;

import java.util.List;

import com.dview.coreServer.bo.EventData;
import com.dview.coreServer.bo.TransferData;
import com.dview.coreServer.po.DeviceGroupBase;
import com.dview.coreServer.po.InventoryDevice;

public interface IDeviceDAO {

	// region Inventory

	TransferData getDevices(int pageSize, int pageIndex, boolean isManaged);

	TransferData getDevicesbyGroupId(int pageSize, int pageIndex,
			String devGrpId);

	void GeneratorDevsData();

	// region Device group 增删改查

	DeviceGroupBase getDevGroupById(String devGrpId);

	List<DeviceGroupBase> getDevGroups();

	void delDevGroup(String devGrpId);

	void saveOrUpdateDevGroup(DeviceGroupBase devGroup);

	// endregion

	// endregion

	// region Event

	EventData getEventData();

	// endregion
}
