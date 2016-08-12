package com.fx.MT4.vo;

import java.util.ArrayList;

public class MT4GroupConfig {
	
	private int p=-1;
	//--- common settings
	   String            group;                       // group name-char[16]
	   int               enable;                      // enable group
	   int               timeout;                     // trade confirmation timeout (seconds)
	   int               adv_security;                // enable advanced security
	   //--- statements
	   String              company;                   // company name -char[128]
	   String              signature;                 // statements signature-char[128]
	   String              support_page;              // company support page-char[128]
	   String              smtp_server;               // statements SMTP server-char[64]
	   String              smtp_login;                // statements SMTP login-char[32]
	   String              smtp_password;             // statements SMTP password-char[32]
	   String              support_email;             // support email-char[64]
	   String              templates;                 // path to directory with custom templates -char[32]
	   int               copies;                      // copy statements on support email
	   int               reports;                     // enable statements
	   //--- default settings
	   int               default_leverage;            // default leverage (user don't specify leverage himself)
	   double            default_deposit;             // default deposit  (user don't specify balance  himself)
	   //--- securities
	   int               maxsecurities;               // maximum simultaneous securities
	   ArrayList<ConGroupSec>       secgroups=new ArrayList<ConGroupSec> ();   // security group settings
	   ArrayList<ConGroupMargin>    secmargins=new ArrayList<ConGroupMargin>(); // special securities settings
	   int               secmargins_total;            // count of special securities settings
	   //--- margin & interest
	   String            currency;                    // deposit currency - char[12]
	   double            credit;                      // virtual credit
	   int               margin_call;                 // margin call level (percents)
	   int               margin_mode;                 // margin mode-MARGIN_DONT_USE,MARGIN_USE_ALL,MARGIN_USE_PROFIT,MARGIN_USE_LOSS
	   int               margin_stopout;              // stop out level
	   double            interestrate;                // annual interest rate (percents)
	   int               use_swap;                    // use rollovers & interestrate
	   //--- rights
	   int               news;                        // news mode
	   int               rights;                      // rights bit mask-ALLOW_FLAG_EMAIL
	   int               check_ie_prices;             // check IE prices on requests
	   int               maxpositions;                // maximum orders and open positions
	   int               close_reopen;                // partial close mode (if !=0 original position will be fully closed and remain position will be fully reopened)
	   int               hedge_prohibited;            // hedge prohibition flag
	   int               close_fifo;                  // fifo rule 
	   int               hedge_largeleg;              // use large leg margin for hedged positions
//	   int               unused_rights[2];            // reserved

//	   char              securities_hash[16];         // internal data
	   //---
	   int               margin_type;                 // margin controlling type { MARGIN_TYPE_PERCENT,  MARGIN_TYPE_CURRENCY }
	   //--- archives
	   int               archive_period;              // inactivity period after which account moves to archive base (in days)
	   int               archive_max_balance;         // maxumum balance of accounts to move in archive base
	   //---
	   int               stopout_skip_hedged;         // skip fully hedged accounts when checking for stopout
	   int               archive_pending_period;      // pendings clean period
	   //--- allowed news languages
//	   UINT              news_languages[8];           // LANGID array
//	   UINT              news_languages_total;        // news languages total
	   
	   
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public int getAdv_security() {
		return adv_security;
	}
	public void setAdv_security(int adv_security) {
		this.adv_security = adv_security;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getSupport_page() {
		return support_page;
	}
	public void setSupport_page(String support_page) {
		this.support_page = support_page;
	}
	public String getSmtp_server() {
		return smtp_server;
	}
	public void setSmtp_server(String smtp_server) {
		this.smtp_server = smtp_server;
	}
	public String getSmtp_login() {
		return smtp_login;
	}
	public void setSmtp_login(String smtp_login) {
		this.smtp_login = smtp_login;
	}
	public String getSmtp_password() {
		return smtp_password;
	}
	public void setSmtp_password(String smtp_password) {
		this.smtp_password = smtp_password;
	}
	public String getSupport_email() {
		return support_email;
	}
	public void setSupport_email(String support_email) {
		this.support_email = support_email;
	}
	public String getTemplates() {
		return templates;
	}
	public void setTemplates(String templates) {
		this.templates = templates;
	}
	public int getCopies() {
		return copies;
	}
	public void setCopies(int copies) {
		this.copies = copies;
	}
	public int getReports() {
		return reports;
	}
	public void setReports(int reports) {
		this.reports = reports;
	}
	public int getDefault_leverage() {
		return default_leverage;
	}
	public void setDefault_leverage(int default_leverage) {
		this.default_leverage = default_leverage;
	}
	public double getDefault_deposit() {
		return default_deposit;
	}
	public void setDefault_deposit(double default_deposit) {
		this.default_deposit = default_deposit;
	}
	public int getMaxsecurities() {
		return maxsecurities;
	}
	public void setMaxsecurities(int maxsecurities) {
		this.maxsecurities = maxsecurities;
	}
	public ArrayList<ConGroupSec> getSecgroups() {
		return secgroups;
	}
	public void setSecgroups(ArrayList<ConGroupSec> secgroups) {
		this.secgroups = secgroups;
	}
	public ArrayList<ConGroupMargin> getSecmargins() {
		return secmargins;
	}
	public void setSecmargins(ArrayList<ConGroupMargin> secmargins) {
		this.secmargins = secmargins;
	}
	public int getSecmargins_total() {
		return secmargins_total;
	}
	public void setSecmargins_total(int secmargins_total) {
		this.secmargins_total = secmargins_total;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public int getMargin_call() {
		return margin_call;
	}
	public void setMargin_call(int margin_call) {
		this.margin_call = margin_call;
	}
	public int getMargin_mode() {
		return margin_mode;
	}
	public void setMargin_mode(int margin_mode) {
		this.margin_mode = margin_mode;
	}
	public int getMargin_stopout() {
		return margin_stopout;
	}
	public void setMargin_stopout(int margin_stopout) {
		this.margin_stopout = margin_stopout;
	}
	public double getInterestrate() {
		return interestrate;
	}
	public void setInterestrate(double interestrate) {
		this.interestrate = interestrate;
	}
	public int getUse_swap() {
		return use_swap;
	}
	public void setUse_swap(int use_swap) {
		this.use_swap = use_swap;
	}
	public int getNews() {
		return news;
	}
	public void setNews(int news) {
		this.news = news;
	}
	public int getRights() {
		return rights;
	}
	public void setRights(int rights) {
		this.rights = rights;
	}
	public int getCheck_ie_prices() {
		return check_ie_prices;
	}
	public void setCheck_ie_prices(int check_ie_prices) {
		this.check_ie_prices = check_ie_prices;
	}
	public int getMaxpositions() {
		return maxpositions;
	}
	public void setMaxpositions(int maxpositions) {
		this.maxpositions = maxpositions;
	}
	public int getClose_reopen() {
		return close_reopen;
	}
	public void setClose_reopen(int close_reopen) {
		this.close_reopen = close_reopen;
	}
	public int getHedge_prohibited() {
		return hedge_prohibited;
	}
	public void setHedge_prohibited(int hedge_prohibited) {
		this.hedge_prohibited = hedge_prohibited;
	}
	public int getClose_fifo() {
		return close_fifo;
	}
	public void setClose_fifo(int close_fifo) {
		this.close_fifo = close_fifo;
	}
	public int getHedge_largeleg() {
		return hedge_largeleg;
	}
	public void setHedge_largeleg(int hedge_largeleg) {
		this.hedge_largeleg = hedge_largeleg;
	}
	public int getMargin_type() {
		return margin_type;
	}
	public void setMargin_type(int margin_type) {
		this.margin_type = margin_type;
	}
	public int getArchive_period() {
		return archive_period;
	}
	public void setArchive_period(int archive_period) {
		this.archive_period = archive_period;
	}
	public int getArchive_max_balance() {
		return archive_max_balance;
	}
	public void setArchive_max_balance(int archive_max_balance) {
		this.archive_max_balance = archive_max_balance;
	}
	public int getStopout_skip_hedged() {
		return stopout_skip_hedged;
	}
	public void setStopout_skip_hedged(int stopout_skip_hedged) {
		this.stopout_skip_hedged = stopout_skip_hedged;
	}
	public int getArchive_pending_period() {
		return archive_pending_period;
	}
	public void setArchive_pending_period(int archive_pending_period) {
		this.archive_pending_period = archive_pending_period;
	}
	
	public ConGroupSec getFirstSec(){
		if (secgroups.size()<=0) return null;
		p=1;
		return secgroups.get(0);
		
	}
	public ConGroupSec getNextSec(){
		if (p==-1) return getFirstSec();
		if (p>=secgroups.size()) {p=-1;return null;}
		p++;
		return secgroups.get(p-1);
	}

}
