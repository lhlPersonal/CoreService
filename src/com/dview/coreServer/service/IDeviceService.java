package com.dview.coreServer.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.dview.coreServer.bo.EventData;
import com.dview.coreServer.bo.TransferData;
import com.dview.coreServer.po.DeviceGroupBase;
import com.dview.coreServer.po.InventoryDevice;

@WebService
public interface IDeviceService {

	// region Inventory

	@WebMethod
	TransferData getDevices(@WebParam(name = "pageSize") int pageSize,
			@WebParam(name = "pageIndex") int pageIndex,
			@WebParam(name = "isManaged") boolean isManaged);

	@WebMethod
	TransferData getDevicesbyGroupId(@WebParam(name = "pageSize") int pageSize,
			@WebParam(name = "pageIndex") int pageIndex,
			@WebParam(name = "devGrpId") String devGrpId);

	void GeneratorDevsData();

	// region Device group 增删改查

	@WebMethod
	DeviceGroupBase getDevGroupById(@WebParam(name = "devGrpId") String devGrpId);

	@WebMethod
	void delDevGroup(@WebParam(name = "devGroupId") String devGroupId);

	@WebMethod
	void saveOrUpdateDevGroup(DeviceGroupBase devGroup);

	@WebMethod
	List<DeviceGroupBase> getDevGroups();

	// endregion

	// endregion

	// region Event

	EventData getEventData();

	// endregion

}
