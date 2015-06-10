package com.dview.coreServer.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.dview.coreServer.bo.UserAccessHistoryData;
import com.dview.coreServer.po.User;

@WebService
public interface IUserService {

	@WebMethod
	void LoginHistory(UserAccessHistoryData history);

	@WebMethod
	void Login(@WebParam(name = "userName") String userName,
			@WebParam(name = "pwd") String pwd);

	@WebMethod
	User getUserById(@WebParam(name = "userId") String userId);
}
