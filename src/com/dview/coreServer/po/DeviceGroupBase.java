package com.dview.coreServer.po;

import com.googlecode.mjorm.annotations.Entity;
import com.googlecode.mjorm.annotations.Id;
import com.googlecode.mjorm.annotations.Property;

@Entity
public class DeviceGroupBase {
	private String id;
	private String groupName;
	private String color;
	private String desc;

	@Id
	@Property
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Property
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Property
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Property
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
