package com.dview.coreServer.service.impl;

import java.text.MessageFormat;
import java.util.Date;
import javax.jws.WebService;
import com.dview.coreServer.bo.UserAccessHistoryData;
import com.dview.coreServer.dao.IUserDAO;
import com.dview.coreServer.po.User;
import com.dview.coreServer.service.IUserService;
import com.dview.coreServer.util.CoreSvrUtil;

@WebService(endpointInterface = "com.dview.coreServer.service.IUserService")
public class UserService implements IUserService {
	private IUserDAO userDAO;

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public void LoginHistory(UserAccessHistoryData history) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Login(String userName, String pwd) {
		// TODO Auto-generated method stub
		if (!CoreSvrUtil.IsNullOrEmpty(userName) && !CoreSvrUtil.IsNullOrEmpty(pwd)) {

			User user = new User();

			user.setUsername(userName);
			user.setRegisterTime(new Date());
			user.setPassword(pwd);

			userDAO.saveUser(user);
		}
	}

	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		com.dview.coreServer.util.CoreSvrUtil.getLogger().info(
				MessageFormat.format("get user {0}", userId));
		return userDAO.getUserById(userId);

	}
}