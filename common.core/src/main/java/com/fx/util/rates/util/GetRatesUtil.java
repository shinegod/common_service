package com.fx.util.rates.util;

import java.lang.reflect.Type;

import org.apache.commons.httpclient.NameValuePair;

import com.fx.util.HttpClientUtil;
import com.fx.util.rates.vo.GsonC;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GetRatesUtil {
	public static double getMoney(String scur,String tcur){
		NameValuePair[] data = {new NameValuePair("from",scur),new NameValuePair("to",tcur),new NameValuePair("format","json")};
		String  json =HttpClientUtil.getJsonByPost("http://api.uihoo.com/currency/currency.http.php", data);
		Gson gson = new Gson();
		Type type = new TypeToken<GsonC>(){}.getType();
		GsonC c =gson.fromJson(json, type);
		return c.getNow();
		
	}
	
	public static void main(String[] args) {
		System.out.println(getMoney("AUD","USD"));
	}
}
