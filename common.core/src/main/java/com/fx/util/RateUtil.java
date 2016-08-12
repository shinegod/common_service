package com.fx.util;

import java.math.BigDecimal;

import org.apache.shiro.cache.ehcache.EhCacheManager;

import com.fx.mt4TradeRecord.model.Mt4Prices;
import com.fx.mt4TradeRecord.service.IMt4PricesService;
import com.fx.mt4TradeRecord.service.impl.Mt4PricesServiceImpl;

public class RateUtil {

	public RateUtil(){}
	
	private static String CURREN="-ECN";
	
	public static BigDecimal getCurrencyRate(String str1,String str2,Integer dataBaseId){
		String currency = str1+str2;
		String currency2 = str2+str1;
		if(str1.equals(str2)){
			return new BigDecimal(1.0D);
		}else{
			if(currency.length()<6){
				return new BigDecimal(0.0D);
			}else{
				IMt4PricesService mt4PricesService = SpringUtils.getBean(IMt4PricesService.class);
				Mt4Prices mt4Prices = mt4PricesService.getMt4PricesBySymbol(currency+CURREN,dataBaseId);
				if(null!=mt4Prices){
					return new BigDecimal(mt4Prices.getBid()) ;
				}else{
					Mt4Prices mt4Prices2 = mt4PricesService.findById(currency);
					if(null!=mt4Prices2){
						return new BigDecimal(mt4Prices2.getBid()) ;
					}else{
						if(currency2.length()<6){
							return new BigDecimal(0.0D);
						}else{
							Mt4Prices mt4Prices3 = mt4PricesService.getMt4PricesBySymbol(currency2+CURREN,dataBaseId);
							if(null!=mt4Prices3){
								return new BigDecimal(1/mt4Prices3.getBid()) ;
							}else{
								Mt4Prices mt4Prices4 = mt4PricesService.findById(currency2);
								if(null!=mt4Prices4){
									return new BigDecimal(1/mt4Prices4.getBid()) ;
								}else{
									return new BigDecimal(0.0D);
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
//		System.out.println(getCurrencyRate("AUD","CAD",6,new Mt4PricesServiceImpl()));
	}
}

