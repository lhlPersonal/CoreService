package com.dview.coreServer.dao.impl;

import java.text.MessageFormat;

import com.dview.coreServer.dao.IUserDAO;
import com.dview.coreServer.po.User;
import com.dview.coreServer.util.Constant;
import com.dview.coreServer.util.CoreSvrUtil;

public class UserDAO extends CommonDAO implements IUserDAO {

	public void saveUser(User user) {
		if (user != null) {
			User u1 = getMongoDao().createObject(Constant.TABLE_USER, user);
			getLogger().info(
					MessageFormat.format("User {0}({2}) is login at {1}",
							user.getUsername(), user.getRegisterTime(),
							u1.getId()));
		}
	}

	@Override
	public User getUserById(String userId) {
		if (CoreSvrUtil.IsNullOrEmpty(userId)) {
			return null;
		}
		// TODO Auto-generated method stub
		return getMongoDao()
				.readObject(Constant.TABLE_USER, userId, User.class);
	}

	@Override
	public void deleteUserById(String userId) {
		if (!CoreSvrUtil.IsNullOrEmpty(userId)) {
			// TODO Auto-generated method stub
			getMongoDao().deleteObject(Constant.TABLE_USER, userId);
		}
	}
}
