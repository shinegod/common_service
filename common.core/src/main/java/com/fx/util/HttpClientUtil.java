package com.fx.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.io.InputStream;

public class HttpClientUtil {
	
	/**
	 * 通过post获取Json
	 * 
	 * @param url
	 * @param data
	 * @return
	 */
	public static String getJsonByPost(String url,NameValuePair[] data){
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		try {
			method.setRequestBody(data);
			client.executeMethod(method);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String returnJson = "";
		try {
			returnJson = method.getResponseBodyAsString();//返回json字符串值
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			//使用完成后要释放链接
			method.releaseConnection();
		}
		return returnJson;

	}
	
	
	   public   static  String getDoGet(String url, String charset)   
	             throws  Exception {   
	         /*  
	         * 使用 GetMethod 来访问一个 URL 对应的网页,实现步骤:   
	         * 1:生成一个 HttpClinet 对象并设置相应的参数。  
	         * 2:生成一个 GetMethod 对象并设置响应的参数。   
	         * 3:用 HttpClinet 生成的对象来执行 GetMethod 生成的Get 方法。   
	         * 4:处理响应状态码。   
	         * 5:若响应正常，处理 HTTP 响应内容。   
	         * 6:释放连接。  
	         */   
	           
	         /* 1 生成 HttpClinet 对象并设置参数 */   
	        HttpClient httpClient =  new  HttpClient();   
	         // 设置 Http 连接超时为5秒   
	        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout( 50000 );   
	  
	         /* 2 生成 GetMethod 对象并设置参数 */   
	        GetMethod getMethod =  new  GetMethod(url);   
	         // 设置 get 请求超时为 5 秒   
	        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,  50000 );   
	         // 设置请求重试处理，用的是默认的重试处理:请求三次
	        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new  DefaultHttpMethodRetryHandler());   
	           
	        String response = "" ;   
	         /* 3 执行 HTTP GET 请求 */   
	         try  {   
	             int  statusCode = httpClient.executeMethod(getMethod);   
	             /* 4 判断访问的状态码 */   
	             if  (statusCode != HttpStatus.SC_OK) {   
	                System.err.println( "Method failed: " + getMethod.getStatusLine());   
	            }   
	  
	             /* 5 处理 HTTP 响应内容 */   
	             // HTTP响应头部信息，这里简单打印   
	            Header[] headers = getMethod.getResponseHeaders();   
	             for  (Header h : headers)   
	                System.out.println(h.getName() +  "------------ "  + h.getValue());   
	               
	             // 读取 HTTP 响应内容，这里简单打印网页内容   
	             byte [] responseBody = getMethod.getResponseBody(); // 读取为字节数组   
	            response =  new  String(responseBody, charset);   
	            System.out.println( "----------response:" +response);   
	               
	             // 读取为 InputStream，在网页内容数据量大时候推荐使用   
	             //InputStream response = getMethod.getResponseBodyAsStream();   
	               
	        }  catch  (HttpException e) {   
	             // 发生致命的异常，可能是协议不对或者返回的内容有问题   
	            System.out.println( "Please check your provided http address!" );   
	            e.printStackTrace();   
	        }  catch  (IOException e) {   
	             // 发生网络异常   
	            e.printStackTrace();   
	        }  finally  {   
	             /* 6 .释放连接 */   
	            getMethod.releaseConnection();   
	        }   
	         return  response;   
	    }   



	
	
	
	public static void main(String[] args) {
		//String url = "http://27.111.203.182:8020/quote?group=ALL&callback=quotation&_=1410416747022";
		String url = "http://www.mynzgft.com/isLogin";
		try {
			System.out.println("123123123-->"+getDoGet(url, "utf-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static InputStream downlaod(String url) throws Exception {
		HttpClient httpClient =  new  HttpClient();
		// 设置 Http 连接超时为5秒
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout( 50000 );
		GetMethod getMethod =  new  GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,  50000 );
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new  DefaultHttpMethodRetryHandler());
		int status ;
		try {
			status = httpClient.executeMethod(getMethod);
			if (status == HttpStatus.SC_OK) {
				return getMethod.getResponseBodyAsStream();
			}else{
				throw new Exception("request url : "+url+" fail");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

//	public static formatJson(){
//		if(returnJson != null && returnJson != ""){
//			String strJson = returnJson.replace("?(", "").replace(")", "").replace(";", "");
//			if(strJson.startsWith("{")){
//			JSONObject jsonObject = JSONObject.fromObject(strJson);
//			String code = jsonObject.getString("code").toString();
//			if("200".equals(code)){
//			JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("result"));
//			JSONObject jsonObj = jsonArray.getJSONObject(0);
//			JSONArray dataArray = JSONArray.fromObject(jsonObj.get("data"));
//			for (int i = 0, len = dataArray.size(); i < len; i++) {
//			PhotoAlbumVO vo = new PhotoAlbumVO();
//			JSONObject jsonVideo = dataArray.getJSONObject(i);
//			vo.setId(jsonVideo.get("id").toString());
//			vo.setUserid(jsonVideo.get("userid").toString());
//			vo.setTitle(jsonVideo.get("title").toString());
//			vo.setIntro(jsonVideo.get("intro").toString());
//			vo.setCoverPath(jsonVideo.get("cover_path").toString());
//			vo.setType(Integer.parseInt(jsonVideo.get("type").toString()));
//			vo.setNumberPhoto(Integer.parseInt(jsonVideo.get("number_photo").toString()));
//			vo.setCreatedTime(DateTimeUtils.parseFullDateTime(DateUtil.getDateTimeByMillisecond(jsonVideo.get("created_time").toString())));
//			vo.setUpdatedTime(DateTimeUtils.parseFullDateTime(DateUtil.getDateTimeByMillisecond(jsonVideo.get("updated_time").toString())));
//			photoAlbumList.add(vo);
//			}
//			JSONArray pageArray = JSONArray.fromObject(jsonObj.get("page"));
//			JSONObject pagerObj = pageArray.getJSONObject(0);
//			JSONArray pagerArray = JSONArray.fromObject(pagerObj.get("pager"));
//			JSONObject pagerObj1 = pagerArray.getJSONObject(0);
//			paginationDto.setTotalRowNum(Long.valueOf(pagerObj1.get("pageSum").toString()));
//			}
//			}
//			}
//	}
}



