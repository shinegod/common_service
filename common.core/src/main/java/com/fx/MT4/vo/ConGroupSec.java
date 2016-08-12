package com.fx.MT4.vo;

public class ConGroupSec {
	int               show,trade;            // enable show and trade for this group of securites
	int               execution;             // dealing mode-EXECUTION_MANUAL,EXECUTION_AUTO,EXECUTION_ACTIVITY
	//--- comission settings
	double            comm_base;             // standart commission
	int               comm_type;             // commission type-COMM_TYPE_MONEY,COMM_TYPE_PIPS,COMM_TYPE_PERCENT
	int               comm_lots;             // commission lots mode-COMMISSION_PER_LOT,COMMISSION_PER_DEAL
	double            comm_agent;            // agent commission
	int               comm_agent_type;       // agent commission mode-COMM_TYPE_MONEY, COMM_TYPE_PIPS
	//---
	int               spread_diff;           // spread difference in compare with default security spread
	//---
	int               lot_min,lot_max;       // allowed minimal and maximal lot values
	int               lot_step;              // allowed step value (10 lot-1000, 1 lot-100, 0.1 lot-10)
	int               ie_deviation;          // maximum price deviation in Instant Execution mode
	int               confirmation;          // use confirmation in Request mode
	int               trade_rights;          // clients trade rights-bit mask see TRADE_DENY_NONE,TRADE_DENY_CLOSEBY,TRADE_DENY_MUCLOSEBY
	int               ie_quick_mode;         // do not resend request to the dealer when client uses deviation
	int               autocloseout_mode;     // auto close-out method { CLOSE_OUT_NONE, CLOSE_OUT_HIHI, CLOSE_OUT_LOLO, CLOSE_OUT_HILO, CLOSE_OUT_LOHI, CLOSE_OUT_LOHI, CLOSE_OUT_FIFO, CLOSE_OUT_LIFO, CLOSE_OUT_INTRDAY_FIFO }
	double            comm_tax;              // commission taxes
	int               comm_agent_lots;       // agent commission per lot/per deal { COMMISSION_PER_LOT,COMMISSION_PER_DEAL }
	int               freemargin_mode;       // "soft" margin check
//	int               reserved[3];           // reserved
	int 			  index;				 // index for all securities available
	int 			  pos;					 // index for security list in MT4
	String			  name;					 // Name
	String	          description;			 // description
	
	public int getShow() {
		return show;
	}
	public void setShow(int show) {
		this.show = show;
	}
	public int getTrade() {
		return trade;
	}
	public void setTrade(int trade) {
		this.trade = trade;
	}
	public int getExecution() {
		return execution;
	}
	public void setExecution(int execution) {
		this.execution = execution;
	}
	public double getComm_base() {
		return comm_base;
	}
	public void setComm_base(double comm_base) {
		this.comm_base = comm_base;
	}
	public int getComm_type() {
		return comm_type;
	}
	public void setComm_type(int comm_type) {
		this.comm_type = comm_type;
	}
	public int getComm_lots() {
		return comm_lots;
	}
	public void setComm_lots(int comm_lots) {
		this.comm_lots = comm_lots;
	}
	public double getComm_agent() {
		return comm_agent;
	}
	public void setComm_agent(double comm_agent) {
		this.comm_agent = comm_agent;
	}
	public int getComm_agent_type() {
		return comm_agent_type;
	}
	public void setComm_agent_type(int comm_agent_type) {
		this.comm_agent_type = comm_agent_type;
	}
	public int getSpread_diff() {
		return spread_diff;
	}
	public void setSpread_diff(int spread_diff) {
		this.spread_diff = spread_diff;
	}
	public double getLot_min() {
		return lot_min;
	}
	public void setLot_min(int lot_min) {
		this.lot_min = lot_min;
	}
	public double getLot_max() {
		return lot_max;
	}
	public void setLot_max(int lot_max) {
		this.lot_max = lot_max;
	}
	public double getLot_step() {
		return lot_step;
	}
	public void setLot_step(int lot_step) {
		this.lot_step = lot_step;
	}
	public int getIe_deviation() {
		return ie_deviation;
	}
	public void setIe_deviation(int ie_deviation) {
		this.ie_deviation = ie_deviation;
	}
	public int getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(int confirmation) {
		this.confirmation = confirmation;
	}
	public int getTrade_rights() {
		return trade_rights;
	}
	public void setTrade_rights(int trade_rights) {
		this.trade_rights = trade_rights;
	}
	public int getIe_quick_mode() {
		return ie_quick_mode;
	}
	public void setIe_quick_mode(int ie_quick_mode) {
		this.ie_quick_mode = ie_quick_mode;
	}
	public int getAutocloseout_mode() {
		return autocloseout_mode;
	}
	public void setAutocloseout_mode(int autocloseout_mode) {
		this.autocloseout_mode = autocloseout_mode;
	}
	public double getComm_tax() {
		return comm_tax;
	}
	public void setComm_tax(double comm_tax) {
		this.comm_tax = comm_tax;
	}
	public int getComm_agent_lots() {
		return comm_agent_lots;
	}
	public void setComm_agent_lots(int comm_agent_lots) {
		this.comm_agent_lots = comm_agent_lots;
	}
	public int getFreemargin_mode() {
		return freemargin_mode;
	}
	public void setFreemargin_mode(int freemargin_mode) {
		this.freemargin_mode = freemargin_mode;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
