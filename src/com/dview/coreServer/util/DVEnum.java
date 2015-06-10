package com.dview.coreServer.util;

/*
 * 将所有的枚举类型定义在一个类中，
 * 避免定义大量的public枚举类(一个java文件只能有一个public定义)
 * 
 * @author lihailong
 * @date 2014-09-15
 * @email hailong_li@gmail.com	
 * @description all emums for dview core server
 * 
 */
public class DVEnum {

	public enum ModuleState {
		Ready, Stop, Error
	}

	public enum ServerState {
		DBError, Normal, Reset, Error, Stop
	}
}
