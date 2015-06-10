package com.dview.coreServer.module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.text.MessageFormat;

import com.dview.coreServer.util.Constant;
import com.dview.coreServer.util.CoreSvrUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

/**
 * 
 * @author lihailong
 * @date 2014-09-12
 * @email hailong_li@cn.dlink.com
 * @description http server for probe
 * 
 */
public class HttpModule extends BaseModule {

	// 启动服务，监听来自客户端的请求
	public void httpserverService() {
		HttpServerProvider provider = HttpServerProvider.provider();
		HttpServer httpserver;
		try {
			httpserver = provider.createHttpServer(new InetSocketAddress(5722),
					Constant.MAX_PROBE);
			// 监听端口5722,能同时接受100个请求
			httpserver.createContext("/dlink",new MyHttpHandler());
			httpserver.setExecutor(null);
			httpserver.start();
			
			CoreSvrUtil.getLogger().info("server started");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Http请求处理类
	class MyHttpHandler implements HttpHandler {
		public void handle(HttpExchange httpExchange) {
			try { // 响应信息
				String responseMsg = "ok";
				// 获得输入流
				InputStream in = httpExchange.getRequestBody();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in));

				String temp = null;

				while ((temp = reader.readLine()) != null) {
					CoreSvrUtil.getLogger().info(
							MessageFormat.format("client request:", temp));
				}

				// 设置响应头属性及响应信息的长度
				httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,
						responseMsg.getBytes().length);
				// 获得输出流
				OutputStream out = httpExchange.getResponseBody();
				out.write(responseMsg.getBytes());
				out.flush();
				httpExchange.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		this.httpserverService();
	}

	@Override
	public void stop() {

	}
}
