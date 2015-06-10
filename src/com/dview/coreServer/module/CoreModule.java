package com.dview.coreServer.module;

import java.util.ArrayList;

import com.dview.coreServer.util.DVEnum.ModuleState;
import com.dview.coreServer.util.DVEnum.ServerState;

public class CoreModule extends BaseModule {
	private ArrayList<? extends BaseModule> modules;
	private ServerState svrState;

	@Override
	public void run() {
		if (modules != null && !modules.isEmpty()) {
			// 数据库连接成功再启动其它操作
			if (modules.get(0).state == ModuleState.Ready) {
				this.state = ModuleState.Ready;

				for (BaseModule baseModule : modules.subList(1,
						modules.size() - 1)) {
					baseModule.run();

					if (baseModule.state != ModuleState.Ready) {
						this.state = ModuleState.Error;
						break;
					}
				}
				if (this.state == ModuleState.Ready) {
					svrState = ServerState.Normal;
				} else {
					svrState = ServerState.Error;
				}
			} else {
				svrState = ServerState.DBError;
			}
		}
		// TODO Auto-generated method stub
	}

	@Override
	public void stop() {
		if (modules != null && !modules.isEmpty()) {
			this.state = ModuleState.Stop;

			for (BaseModule baseModule : modules) {
				baseModule.stop();
				if (baseModule.state != ModuleState.Stop) {
					this.state = ModuleState.Error;
					break;
				}
			}

			if (this.state == ModuleState.Stop) {
				svrState = ServerState.Stop;
			} else {
				svrState = ServerState.Error;
			}
		}
	}

	public ServerState getSvrState() {
		return svrState;
	}

	public void setSvrState(ServerState svrState) {
		this.svrState = svrState;
	}

	public ArrayList<? extends BaseModule> getModules() {
		return modules;
	}

	public void setModules(ArrayList<? extends BaseModule> modules) {
		this.modules = modules;
	}

}
