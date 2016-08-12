package com.fx.user.model;

import com.fx.admin.model.Admin;
import com.fx.admin.model.Role;
import mybatis.framework.core.model.BaseValueObject;

public class User extends BaseValueObject {
    private Integer id;

    private Integer userId;

    private String firstName;

    private String lastName = "";

    private String cnName;

    private String birthday;

    private String idType;

    private String idNum;

    private Integer nationalityCode;

    private String nationality;

    private String residencyCountryCode;

    private String cityCode;

    private String address = "";

    private String postcode = "";

    private String occupationCode;

    private Integer industriesCode;

    public Integer getIndustriesCode() {
		return industriesCode;
	}

	public void setIndustriesCode(Integer industriesCode) {
		this.industriesCode = industriesCode;
	}

	private Integer annualIncomeCode;

    private Integer netAssetsValueCode;

    private int status;

    private String createUser;

    private String createTime;

    private String createIp;

    private String updateUser;

    private String updateTime;

    private String updateIp;

    private int isDel;

    private int is_ib;//0表示不是IB 1表示是IB

    private int ib_Id;//推荐人的id

    private String sex;//性别

    private int livingConditions;//居住情况

    private String interest;

    private String information;

    private String investObjecty;

    private String companyName;

    private String companyAdddress;

    private String companyNature;

    private int curentAssets;

    private int is_employed;

    private String employed_reason;

    private int is_employer;

    private int is_dispute;

    private String dispute_reason;

    private int is_bankruptcy;

    private String bankruptcy_reason;

    private String bank;

    private String bankCode;

    private String companyNameCn;

    private String personNationality;

    private String fax;

    private String companyPhone;

    private String email;

    private int is_nzgft;

    private String mt4Account;

    private int isComplete;//资料是否补全 0为未补全，1为补全

    private String maritalStatus; // 是表示已婚 ， 否表示未婚

    private int sell_Id;

	private String user_type;

	private String user_source;

	private String agent;

	private String customer_Id;

	private String province_code;

	private String comment;

    private int roleId;

    private int parent_Id;

    private String parent_name;

    private Integer ibUserType;

    private String property_name;
    private String unit_number;
    private String street_number;
    private String street_name;
    private String street_type;
    private String suburb;
    
    private int commissionType;//为0表示未设置返佣，为1标识已经设置过返佣

    private String dataSourceName;

    // 显示上级的MT4返佣账号
    private String agentMT4Account;

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public Integer getIbUserType() {
        return ibUserType;
    }

    public void setIbUserType(Integer ibUserType) {
        this.ibUserType = ibUserType;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getParent_Id() {
        return parent_Id;
    }

    public void setParent_Id(int parent_Id) {
        this.parent_Id = parent_Id;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    //List<UserContacts> contactses;

    //List<SalesInfo> salesInfos;

    UserContacts userContacts;

    SalesInfo salesInfo;

    Admin admin;

    User user;

    UserRegister userRegister;

    String userAccount;

    Role role;

    /*

    public List<UserContacts> getContactses() {
        return contactses;
    }

    public void setContactses(List<UserContacts> contactses) {
        this.contactses = contactses;
    }

    public List<SalesInfo> getSalesInfos() {
        return salesInfos;
    }

    public void setSalesInfos(List<SalesInfo> salesInfos) {
        this.salesInfos = salesInfos;
    }

    */

    public Role getRole() {
        return role;
    }

    public int getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(int commissionType) {
		this.commissionType = commissionType;
	}

	public void setRole(Role role) {
        this.role = role;
    }

//    public UserContacts getUserContacts() {
//        return userContacts;
//    }
//
//    public void setUserContacts(UserContacts userContacts) {
//        System.out.println("------------------->"+userContacts);
//        this.userContacts = userContacts;
//    }

    public SalesInfo getSalesInfo() {
        return salesInfo;
    }

    public void setSalesInfo(SalesInfo salesInfo) {
        this.salesInfo = salesInfo;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserRegister getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(UserRegister userRegister) {
        this.userRegister = userRegister;
    }

    public int getSell_Id() {
		return sell_Id;
	}

    public void setSell_Id(int sell_Id) {
		this.sell_Id = sell_Id;
	}

	public int getIs_ib() {
		return is_ib;
	}

	public void setIs_ib(int is_ib) {
		this.is_ib = is_ib;
	}

	public int getIb_Id() {
		return ib_Id;
	}

	public void setIb_Id(int ib_Id) {
		this.ib_Id = ib_Id;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName == null ? null : cnName.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType == null ? null : idType.trim();
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum == null ? null : idNum.trim();
    }

	public Integer getNationalityCode() {
		return nationalityCode;
	}

	public void setNationalityCode(Integer nationalityCode) {
		this.nationalityCode = nationalityCode;
	}

	public String getResidencyCountryCode() {
		return residencyCountryCode;
	}

	public void setResidencyCountryCode(String residencyCountryCode) {
		this.residencyCountryCode = residencyCountryCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }


    public String getOccupationCode() {
		return occupationCode;
	}

	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}



    public Integer getAnnualIncomeCode() {
        return annualIncomeCode;
    }

    public void setAnnualIncomeCode(Integer annualIncomeCode) {
        this.annualIncomeCode = annualIncomeCode;
    }

    public Integer getNetAssetsValueCode() {
        return netAssetsValueCode;
    }

    public void setNetAssetsValueCode(Integer netAssetsValueCode) {
        this.netAssetsValueCode = netAssetsValueCode;
    }

    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp == null ? null : updateIp.trim();
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


	public int getLivingConditions() {
		return livingConditions;
	}

	public void setLivingConditions(int livingConditions) {
		this.livingConditions = livingConditions;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getInvestObjecty() {
		return investObjecty;
	}

	public void setInvestObjecty(String investObjecty) {
		this.investObjecty = investObjecty;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAdddress() {
		return companyAdddress;
	}

	public void setCompanyAdddress(String companyAdddress) {
		this.companyAdddress = companyAdddress;
	}

	public String getCompanyNature() {
		return companyNature;
	}

	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}

	public int getCurentAssets() {
		return curentAssets;
	}

	public void setCurentAssets(int curentAssets) {
		this.curentAssets = curentAssets;
	}

	public int getIs_employed() {
		return is_employed;
	}

	public void setIs_employed(int is_employed) {
		this.is_employed = is_employed;
	}

	public String getEmployed_reason() {
		return employed_reason;
	}

	public void setEmployed_reason(String employed_reason) {
		this.employed_reason = employed_reason;
	}

	public int getIs_employer() {
		return is_employer;
	}

	public void setIs_employer(int is_employer) {
		this.is_employer = is_employer;
	}

	public int getIs_dispute() {
		return is_dispute;
	}

	public void setIs_dispute(int is_dispute) {
		this.is_dispute = is_dispute;
	}

	public String getDispute_reason() {
		return dispute_reason;
	}

	public void setDispute_reason(String dispute_reason) {
		this.dispute_reason = dispute_reason;
	}

	public int getIs_bankruptcy() {
		return is_bankruptcy;
	}

	public void setIs_bankruptcy(int is_bankruptcy) {
		this.is_bankruptcy = is_bankruptcy;
	}

	public String getBankruptcy_reason() {
		return bankruptcy_reason;
	}

	public void setBankruptcy_reason(String bankruptcy_reason) {
		this.bankruptcy_reason = bankruptcy_reason;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getCompanyNameCn() {
		return companyNameCn;
	}

	public void setCompanyNameCn(String companyNameCn) {
		this.companyNameCn = companyNameCn;
	}

	public String getPersonNationality() {
		return personNationality;
	}

	public void setPersonNationality(String personNationality) {
		this.personNationality = personNationality;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIs_nzgft() {
		return is_nzgft;
	}

	public void setIs_nzgft(int is_nzgft) {
		this.is_nzgft = is_nzgft;
	}

	public String getMt4Account() {
		return mt4Account;
	}

	public void setMt4Account(String mt4Account) {
		this.mt4Account = mt4Account;
	}

	public int getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(int isComplete) {
		this.isComplete = isComplete;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getUser_source() {
		return user_source;
	}

	public void setUser_source(String user_source) {
		this.user_source = user_source;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getCustomer_Id() {
		return customer_Id;
	}

	public void setCustomer_Id(String customer_Id) {
		this.customer_Id = customer_Id;
	}

	public String getProvince_code() {
		return province_code;
	}

	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getProperty_name() {
        return property_name;
    }

    public void setProperty_name(String property_name) {
        this.property_name = property_name;
    }

    public String getUnit_number() {
        return unit_number;
    }

    public void setUnit_number(String unit_number) {
        this.unit_number = unit_number;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getStreet_type() {
        return street_type;
    }

    public void setStreet_type(String street_type) {
        this.street_type = street_type;
    }

    public String getAgentMT4Account() {
        return agentMT4Account;
    }

    public void setAgentMT4Account(String agentMT4Account) {
        this.agentMT4Account = agentMT4Account;
    }
}