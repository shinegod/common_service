package com.fx.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.support.RequestContextUtils;

import com.fx.enums.LanguageEnum;

public class LanguageUtil {

	public static LanguageEnum getLanguage(HttpServletRequest request){
		Locale locale=RequestContextUtils.getLocale(request);
		if(locale.getLanguage().equals("en")||locale.getLanguage().equals("en_US")){
			return LanguageEnum.EN;
		}
		if(locale.getLanguage().equals("zh")){
			if ("CN".equals(locale.getCountry())) {
				return LanguageEnum.ZH_CN;
			}else {
				return LanguageEnum.ZH;
			}
		}
		return LanguageEnum.ZH_CN;
	}
}
