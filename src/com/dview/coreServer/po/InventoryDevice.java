package com.dview.coreServer.po;

import java.util.Date;
import java.util.List;

import com.googlecode.mjorm.annotations.Entity;
import com.googlecode.mjorm.annotations.Id;
import com.googlecode.mjorm.annotations.Property;
import com.googlecode.mjorm.mql.MqlParser.bool_return;

@Entity
public class InventoryDevice {
	private String id;
	private String devName;
	private String devIP;
	private String devMAC;
	private String devType;
	private String devModel;
	private String HW;
	private String FW;
	private String devSN;
	private Boolean managed;
	private Date DiscoverTime;
	private String devGrpId;
	private List<DeviceGroupBase> devGroups;

	@Id
	@Property
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Property
	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	@Property
	public String getDevIP() {
		return devIP;
	}

	public void setDevIP(String devIP) {
		this.devIP = devIP;
	}

	@Property
	public String getDevMAC() {
		return devMAC;
	}

	public void setDevMAC(String devMAC) {
		this.devMAC = devMAC;
	}

	@Property
	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	@Property
	public String getDevModel() {
		return devModel;
	}

	public void setDevModel(String devModel) {
		this.devModel = devModel;
	}

	@Property
	public String getHW() {
		return HW;
	}

	public void setHW(String hW) {
		HW = hW;
	}

	@Property
	public String getFW() {
		return FW;
	}

	public void setFW(String fW) {
		FW = fW;
	}

	@Property
	public String getDevSN() {
		return devSN;
	}

	public void setDevSN(String devSN) {
		this.devSN = devSN;
	}

	@Property
	public Date getDiscoverTime() {
		return DiscoverTime;
	}

	public void setDiscoverTime(Date discoverTime) {
		DiscoverTime = discoverTime;
	}

	@Property
	public String getDevGrpId() {
		return devGrpId;
	}

	public void setDevGrpId(String devGrpId) {
		this.devGrpId = devGrpId;
	}

	public Boolean getManaged() {
		return managed;
	}

	public void setManaged(Boolean managed) {
		this.managed = managed;
	}

	@Property
	public List<DeviceGroupBase> getDevGroups() {
		return devGroups;
	}

	public void setDevGroups(List<DeviceGroupBase> devGroups) {
		this.devGroups = devGroups;
	}

}
