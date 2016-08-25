package com.fx.util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class HttpUtil {


	/**
	 * post调用
	 * @param url
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String postInvoke(String url,String sign,StringEntity entityInfo,String charset)
			throws URISyntaxException,IOException {

		try{
			HttpClient client = buildHttpClient(false);

			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(120000).setConnectTimeout(120000).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			httpPost.setHeader("X-Authorization", sign);
			httpPost.setEntity(entityInfo);
			HttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				String returnStr = EntityUtils.toString(entity, charset);
				return returnStr;
			}
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException("[errorMethod]"+url);
			//return "0x111151";
		}
		return null;
	}

	/**
	 * 创建HttpClient
	 *
	 * @param isMultiThread
	 * @return
	 */
	public static HttpClient buildHttpClient(boolean isMultiThread) {

		CloseableHttpClient client;

		if (isMultiThread)
			client = HttpClientBuilder
					.create()
					.setConnectionManager(
							new PoolingHttpClientConnectionManager()).build();

		else
			client = HttpClientBuilder.create().build();
		// 设置代理服务器地址和端口
		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
		return client;
	}
}
