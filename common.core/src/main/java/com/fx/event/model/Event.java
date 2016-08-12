package com.fx.event.model;

import java.util.Date;
import mybatis.framework.core.model.BaseValueObject;

public class Event extends BaseValueObject {
	private Integer id;

	private String name;

	private String email;

	private String phone;

	private Integer age;

	private String city;

	private Integer tradeTerm;

	private Integer amount;

	private String contactTime;

	private Date createTime;

	private int type;
	
	private int is_del;
	
	private String event_language;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public Integer getTradeTerm() {
		return tradeTerm;
	}

	public void setTradeTerm(Integer tradeTerm) {
		this.tradeTerm = tradeTerm;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getContactTime() {
		return contactTime;
	}

	public void setContactTime(String contactTime) {
		this.contactTime = contactTime == null ? null : contactTime.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIs_del() {
		return is_del;
	}

	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}

	public String getEvent_language() {
		return event_language;
	}

	public void setEvent_language(String event_language) {
		this.event_language = event_language;
	}

}