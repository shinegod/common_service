package com.fx.user.model;

import java.io.Serializable;
import java.util.List;

import com.fx.userbank.model.UserBank;

/**
 * 
 * 接收前段参数dto
 * @author Administrator
 *
 */
public class EdocDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String driverlicense;//驾驶证
	private String passport;   //护照
	private String address;   //地址证明
	private String bankCard; //银行卡
	private String idCardA; //身份证A
	private String idCardB; //身份证B
	
	
	
	
	
	public String getIdCardA() {
		return idCardA;
	}

	public void setIdCardA(String idCardA) {
		this.idCardA = idCardA;
	}

	public String getIdCardB() {
		return idCardB;
	}

	public void setIdCardB(String idCardB) {
		this.idCardB = idCardB;
	}

	//TODO 用户银行信息
	private UserBank userBank;


	public String getDriverlicense() {
		return driverlicense;
	}

	public void setDriverlicense(String driverlicense) {
		this.driverlicense = driverlicense;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public UserBank getUserBank() {
		return userBank;
	}

	public void setUserBank(UserBank userBank) {
		this.userBank = userBank;
	}

	
	
}
