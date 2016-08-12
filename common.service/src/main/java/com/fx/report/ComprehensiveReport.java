package com.fx.report;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.mt4TradeRecord.model.ComprehensiveReportOneday;
import com.fx.mt4TradeRecord.model.Mt4Users;
import com.fx.mt4TradeRecord.service.IComprehensiveReportOnedayService;
import com.fx.mt4TradeRecord.service.IMt4TradesService;
import com.fx.mt4TradeRecord.service.IMt4UsersService;
import com.fx.payment.model.UserMT4Account;
import com.fx.payment.service.IUserMT4AccountService;
import com.fx.user.model.UserRegister;
import com.fx.user.service.IUserRegisterService;

@Service
public class ComprehensiveReport{
	
	@Autowired
	private IComprehensiveReportOnedayService  comprehensiveReportOnedayService;
	
	@Autowired
	private IMt4UsersService mt4UsersService;
	
	@Autowired
	private IMt4TradesService mt4TradesService;
	
	@Autowired
	private IUserMT4AccountService userMT4AccountService;
	
	@Autowired
	private IUserRegisterService userRegisterService;
	
	public void comReport(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = null;
		/*if(comdate != null){
			today = comdate;
		}else{*/
		today = new Date();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(today);
		rightNow.add(Calendar.DAY_OF_YEAR,-1);
		today=rightNow.getTime();

		/*}*/
		try {
			today = sf2.parse(sf.format(today)+ " 00:00:00");
			//today = sf2.parse("2015-09-09 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Mt4Users> usersList = mt4UsersService.findAll();
		List<Integer> loginList = new ArrayList<Integer>();
		if(usersList != null){
			for (Mt4Users mt4User : usersList) {
				int login = mt4User.getLogin();
				loginList.add(login);
			}
			
			List<ComprehensiveReportOneday> amountinList = mt4TradesService.getAmountInByAccountTime(loginList,today);
			List<ComprehensiveReportOneday> amountoutList = mt4TradesService.getAmountOutByAccountTime(loginList,today);
			List<ComprehensiveReportOneday> commissionAndTaxesList = mt4TradesService.getCommissionAndTaxesByAccountTime(loginList,today);
			List<ComprehensiveReportOneday> profitList = mt4TradesService.getProfitByAccountTime(loginList,today);
			HashMap<Integer,BigDecimal> amountInMap = new HashMap<Integer,BigDecimal>();
			HashMap<Integer,BigDecimal> amountOutMap = new HashMap<Integer,BigDecimal>();
			HashMap<Integer,BigDecimal> commissionMap = new HashMap<Integer,BigDecimal>();
			HashMap<Integer,BigDecimal> taxesMap = new HashMap<Integer,BigDecimal>();
			HashMap<Integer,BigDecimal> profitMap = new HashMap<Integer,BigDecimal>();
			for (ComprehensiveReportOneday comprehensiveReportOneday : amountinList) {
				amountInMap.put(comprehensiveReportOneday.getAccount(), comprehensiveReportOneday.getAmountIn());
			}
			for (ComprehensiveReportOneday comprehensiveReportOneday : amountoutList) {
				amountOutMap.put(comprehensiveReportOneday.getAccount(), comprehensiveReportOneday.getAmountOut());
			}
			for (ComprehensiveReportOneday comprehensiveReportOneday : commissionAndTaxesList) {
				commissionMap.put(comprehensiveReportOneday.getAccount(), comprehensiveReportOneday.getCommission());
			}
			for (ComprehensiveReportOneday comprehensiveReportOneday : commissionAndTaxesList) {
				taxesMap.put(comprehensiveReportOneday.getAccount(), comprehensiveReportOneday.getTaxes());
			}
			for (ComprehensiveReportOneday comprehensiveReportOneday : profitList) {
				profitMap.put(comprehensiveReportOneday.getAccount(), comprehensiveReportOneday.getProfit());
			}
			
			//
			Date date1 = null;
			Date date2 = null;
			try {
				SimpleDateFormat sfToday = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sfToday2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				date1 = sfToday2.parse(sfToday.format(today) + " 00:00:00");
				date2 = sfToday2.parse(sfToday.format(today) + " 23:59:59");
			}catch (Exception e){
				e.printStackTrace();
			}
			List<ComprehensiveReportOneday> amountinListToday = mt4TradesService.getAmountInByAccountTimeToday(loginList,date1,date2);
			List<ComprehensiveReportOneday> amountoutListToday = mt4TradesService.getAmountOutByAccountTimeToday(loginList,date1,date2);
			List<ComprehensiveReportOneday> commissionAndTaxesListToday = mt4TradesService.getCommissionAndTaxesByAccountTimeToday(loginList,date1,date2);
			List<ComprehensiveReportOneday> profitListToday = mt4TradesService.getProfitByAccountTimeToday(loginList,date1,date2);
			HashMap<Integer,BigDecimal> amountInMapToday = new HashMap<Integer,BigDecimal>();
			HashMap<Integer,BigDecimal> amountOutMapToday = new HashMap<Integer,BigDecimal>();
			HashMap<Integer,BigDecimal> commissionMapToday = new HashMap<Integer,BigDecimal>();
			HashMap<Integer,BigDecimal> taxesMapToday = new HashMap<Integer,BigDecimal>();
			HashMap<Integer,BigDecimal> profitMapToday = new HashMap<Integer,BigDecimal>();
			for (ComprehensiveReportOneday comprehensiveReportOneday : amountinListToday) {
				amountInMapToday.put(comprehensiveReportOneday.getAccount(), comprehensiveReportOneday.getAmountIn());
			}
			for (ComprehensiveReportOneday comprehensiveReportOneday : amountoutListToday) {
				amountOutMapToday.put(comprehensiveReportOneday.getAccount(), comprehensiveReportOneday.getAmountOut());
			}
			for (ComprehensiveReportOneday comprehensiveReportOneday : commissionAndTaxesListToday) {
				commissionMapToday.put(comprehensiveReportOneday.getAccount(), comprehensiveReportOneday.getCommission());
			}
			for (ComprehensiveReportOneday comprehensiveReportOneday : commissionAndTaxesListToday) {
				taxesMapToday.put(comprehensiveReportOneday.getAccount(), comprehensiveReportOneday.getTaxes());
			}
			for (ComprehensiveReportOneday comprehensiveReportOneday : profitListToday) {
				profitMapToday.put(comprehensiveReportOneday.getAccount(), comprehensiveReportOneday.getProfit());
			}
			ComprehensiveReportOneday comprehensiveReportOneday = new ComprehensiveReportOneday();
			for (Mt4Users mt4User : usersList) {
				String name = "";
				String ibName = "";
				int login = mt4User.getLogin();
				UserMT4Account userMt4Account = userMT4AccountService.getByMt4AccountComp(login);
				if(userMt4Account != null){
					UserRegister userRegister = userRegisterService.findById(userMt4Account.getUid());
					if(userRegister != null){
						name = userRegister.getEnName();
						UserRegister ibUserRegister = userRegisterService.findById(userRegister.getIs_ibId());
						if(ibUserRegister != null){
							ibName = ibUserRegister.getEnName();
						}
					}
				}
				BigDecimal amountIn = new BigDecimal(0);
				BigDecimal amountOut = new BigDecimal(0);
				BigDecimal commission = new BigDecimal(0);
				BigDecimal taxes = new BigDecimal(0);
				BigDecimal profit = new BigDecimal(0);
				BigDecimal earlyAmount = new BigDecimal(0);
				if(amountInMap.get(login) != null){
					amountIn = amountInMap.get(login);
				}
				if(amountOutMap.get(login) != null){
					amountOut = amountOutMap.get(login);
				}
				if(commissionMap.get(login) != null){
					commission = commissionMap.get(login);
				}
				if(taxesMap.get(login) != null){
					taxes = taxesMap.get(login);
				}
				if(profitMap.get(login) != null){
					profit = profitMap.get(login);
				}
				earlyAmount = amountIn.add(amountOut).add(commission).add(taxes).add(profit);
				BigDecimal amountInToday = new BigDecimal(0);
				BigDecimal amountOutToday = new BigDecimal(0);
				BigDecimal commissionToday = new BigDecimal(0);
				BigDecimal taxesToday = new BigDecimal(0);
				BigDecimal profitToday = new BigDecimal(0);
				BigDecimal lastAmount = new BigDecimal(0);
				if(amountInMapToday.get(login) != null){
					amountInToday = amountInMapToday.get(login);
				}
				if(amountOutMapToday.get(login) != null){
					amountOutToday = amountOutMapToday.get(login);
				}
				if(commissionMapToday.get(login) != null){
					commissionToday = commissionMapToday.get(login);
				}
				if(taxesMapToday.get(login) != null){
					taxesToday = taxesMapToday.get(login);
				}
				if(profitMapToday.get(login) != null){
					profitToday = profitMapToday.get(login);
				}
				lastAmount = earlyAmount.add(amountInToday).add(amountOutToday).add(commissionToday).add(taxesToday).add(profitToday);
				comprehensiveReportOneday.setEarlyAmount(earlyAmount);
				comprehensiveReportOneday.setAccount(login);
				comprehensiveReportOneday.setIbName(ibName);
				comprehensiveReportOneday.setName(name);
				comprehensiveReportOneday.setAmountIn(amountInToday);
				comprehensiveReportOneday.setAmountOut(amountOutToday);
				comprehensiveReportOneday.setCommission(commissionToday);
				comprehensiveReportOneday.setTaxes(taxesToday);
				comprehensiveReportOneday.setProfit(profitToday);
				comprehensiveReportOneday.setLastAmount(lastAmount);
				comprehensiveReportOneday.setCreateDate(sf.format(today));
				comprehensiveReportOneday.setUpdateDate(sf.format(today));
				comprehensiveReportOneday.setCalculateDate(sf.format(today));
				comprehensiveReportOneday.setStatus(0);
				comprehensiveReportOnedayService.doInsert(comprehensiveReportOneday);
			}
		}
		
	}

}