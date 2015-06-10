package com.dview.coreServer.service.impl;

import java.util.List;

import javax.jws.WebService;

import com.dview.coreServer.bo.EventData;
import com.dview.coreServer.bo.TransferData;
import com.dview.coreServer.dao.IDeviceDAO;
import com.dview.coreServer.po.DeviceGroupBase;
import com.dview.coreServer.po.InventoryDevice;
import com.dview.coreServer.service.IDeviceService;

@WebService(endpointInterface = "com.dview.coreServer.service.IDeviceService")
public class DeviceService implements IDeviceService {
	private IDeviceDAO devDAO;

	public IDeviceDAO getDevDAO() {
		return devDAO;
	}

	public void setDevDAO(IDeviceDAO devDAO) {
		this.devDAO = devDAO;
	}

	@Override
	public void delDevGroup(String devGrpId) {
		// TODO Auto-generated method stub
		devDAO.delDevGroup(devGrpId);
	}

	@Override
	public void saveOrUpdateDevGroup(DeviceGroupBase devGroup) {
		// TODO Auto-generated method stub
		devDAO.saveOrUpdateDevGroup(devGroup);
	}

	@Override
	public List<DeviceGroupBase> getDevGroups() {
		// TODO Auto-generated method stub
		return devDAO.getDevGroups();
	}

	@Override
	public DeviceGroupBase getDevGroupById(String devGrpId) {
		// TODO Auto-generated method stub
		return devDAO.getDevGroupById(devGrpId);
	}

	@Override
	public TransferData getDevices(int pageSize, int pageIndex,
			boolean isManaged) {
		// TODO Auto-generated method stub
		return devDAO.getDevices(pageSize, pageIndex, isManaged);
	}

	@Override
	public TransferData getDevicesbyGroupId(int pageSize, int pageIndex,
			String devGrpId) {
		// TODO Auto-generated method stub
		return devDAO.getDevicesbyGroupId(pageSize, pageIndex, devGrpId);
	}

	@Override
	public void GeneratorDevsData() {
		// TODO Auto-generated method stub
		devDAO.GeneratorDevsData();
	}

	@Override
	public EventData getEventData() {
		// TODO Auto-generated method stub
		return devDAO.getEventData();
	}

}
