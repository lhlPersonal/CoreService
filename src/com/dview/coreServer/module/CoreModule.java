package com.dview.coreServer.module;

import java.util.ArrayList;
import java.util.List;

import com.dview.coreServer.util.CoreSvrUtil;
import com.dview.coreServer.util.DVEnum.ModuleState;
import com.dview.coreServer.util.DVEnum.ServerState;

public class CoreModule extends BaseModule {
	private ArrayList<? extends BaseModule> modules;
	private ServerState svrState;

	@Override
	public void run() {
		System.out.println("   aaaaaadddddd");
		if (modules != null && !modules.isEmpty()) {
			// 数据库连接成功再启动其它操作
			if (modules.get(0).state == ModuleState.Ready) {
				this.state = ModuleState.Ready;

				modules.subList(1, modules.size()).forEach(m -> {
					m.run();

					if (m.state != ModuleState.Ready) {
						this.state = ModuleState.Error;
						return;
					}
				});

				if (this.state == ModuleState.Ready) {
					svrState = ServerState.Normal;
				} else {
					svrState = ServerState.Error;
				}
			} else {
				svrState = ServerState.DBError;
			}
		}
	}

	@Override
	public void stop() {
		if (modules != null && !modules.isEmpty()) {
			this.state = ModuleState.Stop;

			modules.forEach(m -> {
				m.stop();
				if (m.state != ModuleState.Stop) {
					this.state = ModuleState.Error;
					return;
				}
			});

			if (this.state == ModuleState.Stop) {
				svrState = ServerState.Stop;
				CoreSvrUtil.getLogger().info("core service has been stoped");
			} else {
				svrState = ServerState.Error;
				CoreSvrUtil
						.getLogger()
						.info("core service has not been stoped because some error happens.");
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

	public void setModules(ArrayList<BaseModule> modules) {
		this.modules = modules;
	}

}
