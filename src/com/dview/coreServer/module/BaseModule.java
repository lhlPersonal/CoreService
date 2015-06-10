package com.dview.coreServer.module;

import com.dview.coreServer.util.DVEnum;

public abstract class BaseModule {
	public abstract void run();

	public abstract void stop();

	public DVEnum.ModuleState state;
}
