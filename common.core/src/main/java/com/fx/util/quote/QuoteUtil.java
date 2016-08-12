package com.fx.util.quote;

import java.lang.reflect.Type;
import java.util.List;

import com.fx.util.HttpClientUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class QuoteUtil {
	
	
	public static List<quoteVo> getQuote(){
		List<quoteVo> quoteList = null;
		String url = "http://27.111.203.182:8020/quote?group=ALL&callback=quotation&_=1410416747022";
		try {
			String json = HttpClientUtil.getDoGet(url, "utf-8");
			Gson gson = new Gson();
			Type type = new TypeToken<List<quoteVo>>(){}.getType();
			quoteList =gson.fromJson(json, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quoteList;
		
	}
	
	public static void main(String[] args) {
		List<quoteVo> quoteList = getQuote();
		if (quoteList != null) {
			for (quoteVo vo : quoteList) {
				System.out.print("-->"+vo.getU());
			}
		}
		
	}
	
	
	
}
