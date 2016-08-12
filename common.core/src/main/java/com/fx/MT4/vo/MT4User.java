package com.fx.MT4.vo;

public class MT4User {
	//----------------------  parameter  -----------------------//
	public int login;							//login;                      // login
	public String g_name_MT4;				//group[16];                  // group
	public String password;				//password[16];               // password
	public int enable;													  // enable
	public int enable_change_password;     							  // allow to change password
	public int enable_read_only;           							  // allow to open/positions (TRUE-may not trade)
//	public int enable_reserved[3];         							  // for future use
	public String password_investor;			//password_investor[16];      // read-only mode password
	public String password_phone;			//password_phone[32];         // phone password
	public String name;					//name[128];                  // name
	public String country;					//country[32];                // country
	public String city;					//city[32];                   // city
	public String state;					//state[32];                  // state
	public String zipcode;					//zipcode[16];                // zipcode
	public String address;					//address[128];               // address
	public String phone;					//phone[32];                  // phone
	public String email;					//email[48];                  // email
	public String comment;					//comment[64];                // comment
	public String  id;             		//id[32];                     // SSN (IRD)
	public String status;  	            //status[16];                 // status
	public String regdate;					//regdate;                    // registration date
	public String lastdate;                //lastdate;                   // last coonection time
	   //--- trade settings
	public int leverage;                   //leverage;                   // leverage
	public int agent_account;              //agent_account;              // agent account
	public String timestamp;				//timestamp;                  // timestamp
	public int last_ip;              		//last_ip;                    // last visit ip
	   //---            ��
	public double balance;                    							  // balance
	public double prevmonthbalance;           							  // previous month balance
	public double prevbalance;                							  // previous day balance
	public double credit;                    							  // credit
	public double interestrate;              							  // accumulated interest rate
	public double taxes;                   							  // taxes
	public double prevmonthequity;         							  // previous month equity
	public double prevequity;              							  // previous day equity
//	public double reserved2[2];           								  // for future use
	   //---
	public String publickey;              //publickey[PUBLIC_KEY_SIZE];  // public key
	public int send_reports;              								  // enable send reports by email
	public int mqid;                       							  // MQ client identificator
//	public COLORREF user_color;           //user_color;                  // color got to client (used by MT Manager)
	   //---
//	public char              unused[40];                 // for future use
//	public char              api_data[16];               // for API usage
	  
	public String g_name;
	public String info;
	
	
	//-------------------------- func -----------------------//
	public MT4User(){}
//	public Client(String name,String city,String email,String g_name_MT4){
//		this.name=name;
//		this.city=city;
//		this.email=email;
//		this.g_name_MT4=g_name_MT4;
//	}
//	public Client(String name,String city,String email){
//		this.name=name;
//		this.city=city;
//		this.email=email;
//
//	}
			public MT4User(MT4User newClient) {
		name=newClient.name;
    	g_name_MT4=newClient.g_name_MT4;
    	g_name=newClient.g_name;
    	info =newClient.info;
    	
    	address=newClient.address;
    	city=newClient.city;
    	country =newClient.country;
    	enable =newClient.enable;
    	email=newClient.email;
    	id =newClient.id;
    	
    	leverage =newClient.leverage;
    	phone =newClient.phone;
    	state =newClient.state;
    	zipcode =newClient.zipcode;
	}
	public int getLogin() {
		return login;
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	public String getG_name_MT4() {
		return g_name_MT4;
	}
	public void setG_name_MT4(String g_name_MT4) {
		this.g_name_MT4 = g_name_MT4;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public int getEnable_change_password() {
		return enable_change_password;
	}
	public void setEnable_change_password(int enable_change_password) {
		this.enable_change_password = enable_change_password;
	}
	public int getEnable_read_only() {
		return enable_read_only;
	}
	public void setEnable_read_only(int enable_read_only) {
		this.enable_read_only = enable_read_only;
	}
	public String getPassword_investor() {
		return password_investor;
	}
	public void setPassword_investor(String password_investor) {
		this.password_investor = password_investor;
	}
	public String getPassword_phone() {
		return password_phone;
	}
	public void setPassword_phone(String password_phone) {
		this.password_phone = password_phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getLastdate() {
		return lastdate;
	}
	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}
	public int getLeverage() {
		return leverage;
	}
	public void setLeverage(int leverage) {
		this.leverage = leverage;
	}
	public int getAgent_account() {
		return agent_account;
	}
	public void setAgent_account(int agent_account) {
		this.agent_account = agent_account;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public int getLast_ip() {
		return last_ip;
	}
	public void setLast_ip(int last_ip) {
		this.last_ip = last_ip;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getPrevmonthbalance() {
		return prevmonthbalance;
	}
	public void setPrevmonthbalance(double prevmonthbalance) {
		this.prevmonthbalance = prevmonthbalance;
	}
	public double getPrevbalance() {
		return prevbalance;
	}
	public void setPrevbalance(double prevbalance) {
		this.prevbalance = prevbalance;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public double getInterestrate() {
		return interestrate;
	}
	public void setInterestrate(double interestrate) {
		this.interestrate = interestrate;
	}
	public double getTaxes() {
		return taxes;
	}
	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}
	public double getPrevmonthequity() {
		return prevmonthequity;
	}
	public void setPrevmonthequity(double prevmonthequity) {
		this.prevmonthequity = prevmonthequity;
	}
	public double getPrevequity() {
		return prevequity;
	}
	public void setPrevequity(double prevequity) {
		this.prevequity = prevequity;
	}
	public String getPublickey() {
		return publickey;
	}
	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}
	public int getSend_reports() {
		return send_reports;
	}
	public void setSend_reports(int send_reports) {
		this.send_reports = send_reports;
	}
	public int getMqid() {
		return mqid;
	}
	public void setMqid(int mqid) {
		this.mqid = mqid;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public void setLogin(int login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
