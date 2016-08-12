package com.fx.MT4.vo;

import mybatis.framework.core.model.BaseValueObject;




public class MT4TradeRecord extends BaseValueObject{
	public int               order;            // order ticket     			//交易编号
	public int               login;            // owner's login    			//用户编号
	public String           symbol;       // security                     	//交易产品    （eg AUDUSD,Gold)
	public int               digits;           // security precision        //报价位数       (IB不用显示)
	public int               cmd;              // trade command             //订单类型       (0-6:现价买,现价卖,OP_BUY_LIMIT,OP_SELL_LIMIT,OP_BUY_STOP,OP_SELL_STOP,入金/出金,credit
	public int               volume;           // volume					//交易量
	//---
	public String        	open_time;        // open time					//开仓时间
	public int               state;            // reserved					//(IB不用显示)       
	public double            open_price;       // open price				//开仓价格
	public double            sl,tp;            // stop loss & take profit	//止损位，止盈位
	public String        	close_time;       // close time					//平仓时间
	public String        	value_date;       // value date					//结算时间 (IB不用显示) 
	public String        	expiration;       // pending order's expiration time       //挂单有效期
	public char              reason;           // trade reason				//客户用备注 (IB不用显示)
	//char              conv_reserv[3];   // reserved fields
	public double            conv_rates_o;    // convertation rates from profit currency to group deposit currency		//开仓结算汇率 (IB不用显示)
	public double            conv_rates_c;		// (first element-for open time, second element-for close time)			//平仓结算汇率 (IB不用显示)
	public double            commission;       // commission				//客户支付到平台的佣金
	public double            commission_agent; // agent commission			//二级代理收取的佣金
	public double            storage;          // order swaps				//(IB不用显示)
	public double            close_price;      // close price				//平仓价格
	public double            profit;           // profit					//利润
	public double            taxes;            // taxes						//代收收入税 (无，IB不用显示)
	public int               magic;            // special value used by client experts       //客户方指定的识别码
	public String            comment;      // comment						//客户用备注
	public int               internal_id;      // trade order ticket on master server in STP //(IB不用显示)
	public int               activation;       // used by MT Manager        //内部交易编号(IB不用显示)
	public int               spread;           // spread					//点差(IB不用显示)
	public double            margin_rate;      // margin convertation rate (rate of convertation from margin currency to deposit one)  //已付款计算 (IB不用显示)
	public String        	timestamp;        // timestamp					//时间戳(IB不用显示)
	//int               reserved[4];      // reserved
	//TradeRecord *__ptr32 next;          // internal data
	public int getOrder() {
		return order;
	}
	public int getLogin() {
		return login;
	}
	public String getSymbol() {
		return symbol;
	}
	public String getCmd() {
		switch(cmd){
			case 0: return "BUY";
			case 1: return "SELL";
			case 2: return "BUY_LIMIT";
			case 3: return "SELL_LIMIT";
			case 4: return "BUY_STOP";
			case 5: return "SELL_STOP";
			case 6: return "Deposit/Withdrawal";
			case 7: return "Credit";
			default: return "";
		}
	}
	public String getVolume() {
		return String.format("%.2f",volume/100.0);
	}
	public String getOpen_time() {
		return open_time;
	}
	public String getOpen_price() {
		if (symbol.contains("GOLD")||symbol.contains("JPY"))
			return String.format("%.3f",open_price);
		else 
			return String.format("%.5f",open_price);
	}
	public String getSl() {
		if (sl==0)
			return null;
		if (symbol.contains("GOLD")||symbol.contains("JPY"))
			return String.format("%.3f",sl);
		else 
			return String.format("%.5f",sl);
	}
	public String getTp() {
		if (tp==0)
			return null;
		if (symbol.contains("GOLD")||symbol.contains("JPY"))
			return String.format("%.3f",tp);
		else 
			return String.format("%.5f",tp);
	}
	public String getClose_time() {
		return close_time;
	}
	public double getCommission() {
		return commission;
	}
	public double getCommission_agent() {
		return commission_agent;
	}
	public String getClose_price() {
		if (symbol.contains("GOLD")||symbol.contains("JPY"))
			return String.format("%.3f",close_price);
		else 
			return String.format("%.5f",close_price);
	}
	public String getProfit() {
		return String.format("%.2f",profit);
	}
	public int getMagic() {	
		return magic;
	}
	public String getComment() {
		return comment;
	}
	
}
