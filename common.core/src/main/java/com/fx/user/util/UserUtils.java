package com.fx.user.util;

import com.fx.user.model.InvestExperience;
import com.fx.user.model.User;
import com.fx.user.model.UserRegister;
import org.apache.commons.lang.StringUtils;

public class UserUtils {
	
	public static final String AT = "@";
	public static final String CODE_PRE = "vitas";
	public static final int TRADE_CODE_MAX_LENGTH = 10;
	
	/**
	 * checkInfoIsIntact	检查用户信息是否完整
	 * @param user	用户信息
	 * @return boolean	true:完整	false:不完整
	 * @exception 
	*/
	public static boolean checkInfoIsIntact(User user, UserRegister userRegister, InvestExperience investExperience){
		if(null == userRegister){
			return false;
		}
		if(null == user){
			return false;
		}
		if(null == investExperience){
			return false;
		}
		if(userRegister.getIsDel() == 1){
			return false;
		}
		if(user.getIsDel() == 1){
			return false;
		}
		if(investExperience.getIsDel() == 1){
			return false;
		}
//		if(userRegister.getStatus() != UserRegisterStatusEnum.ACTIVATED.getValue()){
//			return false;
//		}
		if(StringUtils.isBlank(userRegister.getEmail())){
			return false;
		}
		if(StringUtils.isBlank(userRegister.getPhoneNum())){
			return false;
		}
		if(StringUtils.isBlank(user.getFirstName())){
			return false;
		}
		if(StringUtils.isBlank(user.getLastName())){
			return false;
		}
		if(StringUtils.isBlank(user.getBirthday())){
			return false;
		}
		if(StringUtils.isBlank(user.getIdType())){
			return false;
		}
		if(StringUtils.isBlank(user.getIdNum())){
			return false;
		}
		if(null == user.getNationalityCode() || user.getNationalityCode() <= 0){
			return false;
		}
		if(null == user.getResidencyCountryCode() || user.getResidencyCountryCode().length() <= 0){
			return false;
		}
		if(StringUtils.isBlank( user.getOccupationCode())){
			return false;
		}
		if(null == user.getIndustriesCode() || user.getIndustriesCode() <= 0){
			return false;
		}
		if(null == user.getAnnualIncomeCode() || user.getAnnualIncomeCode() <= 0){
			return false;
		}
		if(null == user.getNetAssetsValueCode() || user.getNetAssetsValueCode() <= 0){
			return false;
		}
		if(null == investExperience.getInvestmentExperienceCode() || investExperience.getInvestmentExperienceCode() <= 0){
			return false;
		}
		if(null == investExperience.getOthersCode() || investExperience.getOthersCode() <= 0){
			return false;
		}
		if(null == investExperience.getTradingFrequencyCode() || investExperience.getTradingFrequencyCode() <= 0){
			return false;
		}
		
		return true;
	}
	
	public static String getEmailPrefix(String email){
		if (StringUtils.isBlank(email)) {
			return "";
		}
		if (email.indexOf(AT) < 0) {
			return "";
		}
		return email.substring(0, email.indexOf(AT));
	}

	/**
	 * 生成交易码
	 * 
	 * @param uid
	 * @return
	 */
	public static String createTradeCode(Integer uid) {
		String tradeCode = "";
		if (uid != null && uid.intValue() > 0) {
			String uidStr = ""+uid;
			StringBuffer sb = new StringBuffer();
			int maxCount = TRADE_CODE_MAX_LENGTH-uidStr.length();
			for (int i = 0;i < maxCount ; i++) {
				sb.append(0);
			}
			sb.append(uidStr);
			tradeCode = CODE_PRE + sb.toString();
		}
		return tradeCode;
	}
	
	public static String getUserName(String enName, String cnName) {
		String userName = "";
		if (!StringUtils.isBlank(enName) && !StringUtils.isBlank(cnName)) {
			userName = enName + "-" + cnName;
		} else if (StringUtils.isBlank(enName)) {
			userName = cnName==null?"":cnName;
		} else if (StringUtils.isBlank(cnName)) {
			userName = enName ==null?"":enName;
		}
		return userName;
	}
	public static void main(String[] args) {
		String aa = createTradeCode(123);
		System.out.println(aa);
	}
	
}
