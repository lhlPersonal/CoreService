package com.dview.coreServer.dao;

import com.dview.coreServer.po.User;

public interface IUserDAO {
	void saveUser(User user);

	User getUserById(String userId);

	void deleteUserById(String userId);
}
