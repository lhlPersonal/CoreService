package com.dview.coreServer.po;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import com.googlecode.mjorm.annotations.Entity;
import com.googlecode.mjorm.annotations.Id;
import com.googlecode.mjorm.annotations.Property;

@Entity
public class User {
	private String id;
	private String username;
	private Date registerTime;
	private String password;

	@Id
	@Property
	public String getId() {
		return id;
	}

	@Property
	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public void setId(String _id) {
		this.id = _id;
	}

	@Property
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Property
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
