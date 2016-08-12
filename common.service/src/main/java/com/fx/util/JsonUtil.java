package com.fx.util;

import com.fx.util.vos.JsonVo;

public class JsonUtil {
	public static final String JSON_RESULT_SECCESS = "0";
	public static final String JSON_RESULT_FAILED = "1";

	public static JsonVo getJosnResult(String code,String msg){
		JsonVo vo = new JsonVo();
		vo.setCode(code);
		vo.setMsg(msg);
		return vo;
	}
}

