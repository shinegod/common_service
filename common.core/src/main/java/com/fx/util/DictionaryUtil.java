package com.fx.util;

import java.util.HashMap;
import java.util.Map;

public class DictionaryUtil {

	//字典表常量,引号中对应的是数据库中的CODE
	//所有节点的根节点
	public final static String DICTIONARY_ROOT = "0";
	//汇率设置
	public class RATE_SET {
		public final static String RATE_SET_ROOT ="RATE_SET";
		public final static String RATE_USD_CNY ="USDCNY";
		public final static String RATE_AUD_CNY ="AUDCNY";
		public final static String RATE_GBP_CNY ="GBPCNY";
		public final static String RATE_EUR_CNY ="EURCNY";
		public final static String RATE_SGD_CNY ="SGDCNY";
		public final static String RATE_JPY_CNY ="JPYCNY";
		public final static String RATE_NZD_CNY ="NZDCNY";
	}
	//杠杆设置
	public final static String LEVERAGE_SET = "LEVERAGE_SET";
	//全部自动入金标识
	public class AUTO_DEPOSIT{
		public final static String ALL_AUTO_DEPOSIT = "ALL_AUTO_DEPOSIT";
		public final static String AUTO = "1";
		public final static String MANUAL = "0";
	}

	//证件类型
	@SuppressWarnings("serial")
	public final static Map<String, String> IDENTITY_TYPE_MAP = new HashMap<String, String>(){{
		put("1", "身份证");
		put("2", "护照");
		put("3", "驾驶证");
		put("9", "其他");
	}};
	
	@SuppressWarnings("serial")
	public final static Map<String, String> TW_IDENTITY_TYPE_MAP = new HashMap<String, String>(){{
		put("1", "身份證");
		put("2", "護照");
		put("3", "駕駛證");
		put("9", "其他");
	}};
	
	@SuppressWarnings("serial")
	public final static Map<String, String> EN_IDENTITY_TYPE_MAP = new HashMap<String, String>(){{
		put("1", "Identity card");
		put("2", "Passport");
		put("3", "Driving license");
		put("9", "Other");
	}};
	
	//国家 英文
	@SuppressWarnings("serial")
	public final static Map<Integer, String> EN_COUNTRY_MAP = new HashMap<Integer, String>(){{
		put(1,"Afghanistan");
		put(2,"Albania");
		put(3,"Algeria");
		put(4,"Andorra");
		put(5,"Angola");
		put(6,"Anguilla");
		put(7,"AntiguaandBarbuda");
		put(8,"Argentina");
		put(9,"Armenia");
		put(10,"Australia");
		put(11,"Austria");
		put(12,"Azerbaijan");
		put(13,"Bahamas");
		put(14,"Bahrain");
		put(15,"Bangladesh");
		put(16,"Barbados");
		put(17,"Belarus");
		put(18,"Belgium");
		put(19,"Belize");
		put(20,"Benin");
		put(21,"BermudaIs.");
		put(22,"Bolivia");
		put(23,"Botswana");
		put(24,"Brazil");
		put(25,"Brunei");
		put(26,"Bulgaria");
		put(27,"Burkina-faso");
		put(28,"Burma");
		put(29,"Burundi");
		put(30,"Cameroon");
		put(31,"Canada");
		put(32,"CentralAfricanRepublic");
		put(33,"Chad");
		put(34,"Chile");
		put(35,"China");
		put(36,"Colombia");
		put(37,"Congo");
		put(38,"CookIs.");
		put(39,"CostaRica");
		put(40,"Cuba");
		put(41,"Cyprus");
		put(42,"Czech");
		put(43,"CzechRepublic");
		put(44,"Denmark");
		put(45,"Djibouti");
		put(46,"DominicaRep.");
		put(47,"Ecuador");
		put(48,"Egypt");
		put(49,"EISalvador");
		put(50,"Estonia");
		put(51,"Ethiopia");
		put(52,"Fiji");
		put(53,"Finland");
		put(54,"France");
		put(55,"FrenchGuiana");
		put(56,"FrenchPolynesia");
		put(57,"Gabon");
		put(58,"Gambia");
		put(59,"Georgia");
		put(60,"Germany");
		put(61,"Ghana");
		put(62,"Gibraltar");
		put(63,"Greece");
		put(64,"Grenada");
		put(65,"Guam");
		put(66,"Guatemala");
		put(67,"Guinea");
		put(68,"Guyana");
		put(69,"Haiti");
		put(70,"Honduras");
		put(71,"Hongkong");
		put(72,"Hungary");
		put(73,"Iceland");
		put(74,"India");
		put(75,"Indonesia");
		put(76,"Iran");
		put(77,"Iraq");
		put(78,"Ireland");
		put(79,"Israel");
		put(80,"Italy");
		put(81,"Jamaica");
		put(82,"Japan");
		put(83,"Jordan");
		put(84,"Kampuchea(Cambodia)");
		put(85,"Kazakstan");
		put(86,"Kenya");
		put(87,"Korea");
		put(88,"Kuwait");
		put(89,"Kyrgyzstan");
		put(90,"Laos");
		put(91,"Latvia");
		put(92,"Lebanon");
		put(93,"Lesotho");
		put(94,"Liberia");
		put(95,"Libya");
		put(96,"Liechtenstein");
		put(97,"Lithuania");
		put(98,"Luxembourg");
		put(99,"Macao");
		put(100, "Madagascar");
		put(101, "Malawi");
		put(102, "Malaysia");
		put(103, "Maldives");
		put(104, "Mali");
		put(105, "Malta");
		put(106, "Mauritius");
		put(107, "Mexico");
		put(108, "Moldova,Republicof");
		put(109, "Monaco");
		put(110, "Mongolia");
		put(111, "MontserratIs");
		put(112, "Morocco");
		put(113, "Mozambique");
		put(114, "Namibia");
		put(115, "Nauru");
		put(116, "Nepal");
		put(117, "Netherlands");
		put(118, "NewZealand");
		put(119, "Nicaragua");
		put(120, "Niger");
		put(121, "Nigeria");
		put(122, "NorthKorea");
		put(123, "Norway");
		put(124, "Oman");
		put(125, "Pakistan");
		put(126, "Palestine");
		put(127, "Panama");
		put(128, "PapuaNewCuinea");
		put(129, "Paraguay");
		put(130, "Peru");
		put(131, "Philippines");
		put(132, "Poland");
		put(133, "Portugal");
		put(134, "PuertoRico");
		put(135, "Qatar");
		put(136, "RepublicofIvoryCoast");
		put(137, "Romania");
		put(138, "Russia");
		put(139, "SaintVincent");
		put(140, "SanMarino");
		put(141, "SaoTomeandPrincipe");
		put(142, "SaudiArabia");
		put(143, "Senegal");
		put(144, "Seychelles");
		put(145, "SierraLeone");
		put(146, "Singapore");
		put(147, "Slovakia");
		put(148, "Slovenia");
		put(149, "SolomonIs");
		put(150, "Somali");
		put(151, "SouthAfrica");
		put(152, "Spain");
		put(153, "SriLanka");
		put(154, "St.Lucia");
		put(155, "Sudan");
		put(156, "Suriname");
		put(157, "Swaziland");
		put(158, "Sweden");
		put(159, "Switzerland");
		put(160, "Syria");
		put(161, "Taiwan");
		put(162, "Tajikstan");
		put(163, "Tanzania");
		put(164, "Thailand");
		put(165, "Togo");
		put(166, "Tonga");
		put(167, "TrinidadandTobago");
		put(168, "Tunisia");
		put(169, "Turkey");
		put(170, "Turkmenistan");
		put(171, "Uganda");
		put(172, "Ukraine");
		put(173, "UnitedArabEmirates");
		put(174, "UnitedKiongdom");
		put(175, "UnitedStatesofAmerica");
		put(176, "Uruguay");
		put(177, "Uzbekistan");
		put(178, "Venezuela");
		put(179, "Vietnam");
		put(180, "Yemen");
		put(181, "Yugoslavia");
		put(182, "Zaire");
		put(183, "Zambia");
		put(184, "Zimbabwe");
	}};
	
	//中文
	@SuppressWarnings("serial")
	public final static Map<Integer, String> COUNTRY_MAP = new HashMap<Integer, String>(){{
		put(1,"中国");
		put(3276,"阿尔巴尼亚");
		put(3290,"阿尔及利亚");
		put(3340,"阿富汗");
		put(3346,"阿根廷");
		put(3382,"阿拉伯联合酋长国");
		put(3388,"阿鲁巴");
		put(3389,"阿曼");
		put(3399,"阿塞拜疆");
		put(3415,"阿森松岛");
		put(3416,"埃及");
		put(3423,"埃塞俄比亚");
		put(3436,"爱尔兰");
		put(3464,"爱沙尼亚");
		put(3481,"安道尔");
		put(3490,"安哥拉");
		put(3510,"安圭拉");
		put(3511,"安提瓜岛和巴布达");
		put(3512,"澳大利亚");
		put(3554,"奥地利");
		put(3565,"奥兰群岛");
		put(3566,"巴巴多斯岛");
		put(3567,"巴布亚新几内亚");
		put(3588,"巴哈马");
		put(3589,"巴基斯坦");
		put(3600,"巴拉圭");
		put(3620,"巴勒斯坦");
		put(3624,"巴林");
		put(3635,"巴拿马");
		put(3636,"巴西");
		put(3665,"白俄罗斯");
		put(3673,"百慕大");
		put(3674,"保加利亚");
		put(3685,"北马里亚纳群岛");
		put(3686,"贝宁");
		put(3701,"比利时");
		put(3714,"冰岛");
		put(3715,"波多黎各");
		put(3716,"波兰");
		put(3781,"玻利维亚");
		put(3795,"波斯尼亚和黑塞哥维那");
		put(3808,"博茨瓦纳");
		put(3809,"伯利兹");
		put(3817,"不丹");
		put(3818,"布基纳法索");
		put(3865,"布隆迪");
		put(3884,"布韦岛");
		put(3885,"朝鲜");
		put(3899,"丹麦");
		put(3915,"德国");
		put(3959,"东帝汶");
		put(3974,"多哥");
		put(3981,"多米尼加");
		put(3982,"多米尼加共和国");
		put(3983,"俄罗斯");
		put(4070,"厄瓜多尔");
		put(4093,"厄立特里亚");
		put(4101,"法国");
		put(4126,"法罗群岛");
		put(4127,"法属波利尼西亚");
		put(4128,"法属圭亚那");
		put(4129,"法属南部领地");
		put(4130,"梵蒂冈");
		put(4131,"菲律宾");
		put(4137,"斐济");
		put(4138,"芬兰");
		put(4160,"佛得角");
		put(4182,"弗兰克群岛");
		put(4183,"冈比亚");
		put(4184,"刚果");
		put(4185,"刚果民主共和国");
		put(4186,"哥伦比亚");
		put(4221,"哥斯达黎加");
		put(4230,"格恩西岛");
		put(4231,"格林纳达");
		put(4232,"格陵兰");
		put(4233,"古巴");
		put(4252,"瓜德罗普");
		put(4253,"关岛");
		put(4254,"圭亚那");
		put(4266,"哈萨克斯坦");
		put(4305,"海地");
		put(4306,"韩国");
		put(4482,"荷兰");
		put(4512,"荷属安地列斯");
		put(4513,"赫德和麦克唐纳群岛");
		put(4514,"洪都拉斯");
		put(4535,"基里巴斯");
		put(4540,"吉布提");
		put(4546,"吉尔吉斯斯坦");
		put(4564,"几内亚");
		put(4574,"几内亚比绍");
		put(4575,"加拿大");
		put(4611,"加纳");
		put(4624,"加蓬");
		put(4635,"柬埔寨");
		put(4661,"捷克共和国");
		put(4676,"津巴布韦");
		put(4688,"喀麦隆");
		put(4700,"卡塔尔");
		put(4711,"开曼群岛");
		put(4712,"科科斯群岛");
		put(4713,"科摩罗");
		put(4714,"科特迪瓦");
		put(4735,"科威特");
		put(4736,"克罗地亚");
		put(4759,"肯尼亚");
		put(4809,"库克群岛");
		put(4810,"拉脱维亚");
		put(4838,"莱索托");
		put(4850,"老挝");
		put(4869,"黎巴嫩");
		put(4877,"利比里亚");
		put(4896,"利比亚");
		put(4897,"立陶宛");
		put(4910,"列支敦士登");
		put(4911,"留尼旺岛");
		put(4912,"卢森堡");
		put(4917,"卢旺达");
		put(4935,"罗马尼亚");
		put(4978,"马达加斯加");
		put(4986,"马尔代夫");
		put(5009,"马耳他");
		put(5010,"马拉维");
		put(5015,"马来西亚");
		put(5144,"马里");
		put(5155,"马其顿");
		put(5156,"马绍尔群岛");
		put(5157,"马提尼克");
		put(5158,"马约特岛");
		put(5159,"曼岛");
		put(5160,"毛里求斯");
		put(5161,"毛里塔尼亚");
		put(5176,"美国");
		put(5511,"美属萨摩亚");
		put(5526,"美属外岛");
		put(5527,"蒙古");
		put(5551,"蒙特塞拉特");
		put(5552,"孟加拉");
		put(5557,"密克罗尼西亚");
		put(5558,"秘鲁");
		put(5589,"缅甸");
		put(5605,"摩尔多瓦");
		put(5606,"摩洛哥");
		put(5617,"摩纳哥");
		put(5618,"莫桑比克");
		put(5619,"墨西哥");
		put(5681,"纳米比亚");
		put(5696,"南非");
		put(5748,"南极洲");
		put(5749,"南乔治亚和南桑德威奇群岛");
		put(5750,"瑙鲁");
		put(5751,"尼泊尔");
		put(5767,"尼加拉瓜");
		put(5786,"尼日尔");
		put(5796,"尼日利亚");
		put(5803,"纽埃");
		put(5804,"挪威");
		put(5825,"诺福克");
		put(5826,"帕劳群岛");
		put(5827,"皮特凯恩");
		put(5828,"葡萄牙");
		put(5859,"乔治亚");
		put(5860,"日本");
		put(5909,"瑞典");
		put(5932,"瑞士");
		put(5961,"萨尔瓦多");
		put(5986,"萨摩亚");
		put(5987,"塞尔维亚,黑山");
		put(5997,"塞拉利昂");
		put(6003,"塞内加尔");
		put(6016,"塞浦路斯");
		put(6024,"塞舌尔");
		put(6025,"沙特阿拉伯");
		put(6049,"圣诞岛");
		put(6050,"圣多美和普林西比");
		put(6051,"圣赫勒拿");
		put(6052,"圣基茨和尼维斯");
		put(6053,"圣卢西亚");
		put(6054,"圣马力诺");
		put(6055,"圣皮埃尔和米克隆群岛");
		put(6056,"圣文森特和格林纳丁斯");
		put(6057,"斯里兰卡");
		put(6084,"斯洛伐克");
		put(6094,"斯洛文尼亚");
		put(6108,"斯瓦尔巴和扬马廷");
		put(6109,"斯威士兰");
		put(6110,"苏丹");
		put(6121,"苏里南");
		put(6133,"所罗门群岛");
		put(6145,"索马里");
		put(6146,"塔吉克斯坦");
		put(6163,"泰国");
		put(6239,"坦桑尼亚");
		put(6268,"汤加");
		put(6275,"特克斯和凯克特斯群岛");
		put(6276,"特里斯坦达昆哈");
		put(6277,"特立尼达和多巴哥");
		put(6278,"突尼斯");
		put(6304,"图瓦卢");
		put(6305,"土耳其");
		put(6385,"土库曼斯坦");
		put(6394,"托克劳");
		put(6395,"瓦利斯和福图纳");
		put(6396,"瓦努阿图");
		put(6404,"危地马拉");
		put(6430,"维尔京群岛，美属");
		put(6431,"维尔京群岛，英属");
		put(6432,"委内瑞拉");
		put(6458,"文莱");
		put(6459,"乌干达");
		put(6517,"乌克兰");
		put(6544,"乌拉圭");
		put(6565,"乌兹别克斯坦");
		put(6581,"西班牙");
		put(6633,"希腊");
		put(6645,"新加坡");
		put(6646,"新喀里多尼亚");
		put(6647,"新西兰");
		put(6673,"匈牙利");
		put(6695,"叙利亚");
		put(6713,"牙买加");
		put(6729,"亚美尼亚");
		put(6742,"也门");
		put(6765,"伊拉克");
		put(6766,"伊朗");
		put(6767,"以色列");
		put(6777,"意大利");
		put(6845,"印度");
		put(6877,"印度尼西亚");
		put(6907,"英国");
		put(6975,"英属印度洋领地");
		put(6976,"约旦");
		put(6991,"越南");
		put(6996,"赞比亚");
		put(7007,"泽西岛");
		put(7008,"乍得");
		put(7009,"直布罗陀");
		put(7010,"智利");
		put(7025,"中非共和国");

	}};
	
	//就业状况
	public final static Map<Integer, String> OCCUPATION_MAP = new HashMap<Integer, String>(){{
		put(1,"受雇");
        put(2,"自雇");
        put(3,"退休");
        put(4,"无业");
	}};
	
	public final static Map<Integer, String> EN_OCCUPATION_MAP = new HashMap<Integer, String>(){{
		put(1,"Employed");
        put(2,"Self-employed");
        put(3,"Retired");
        put(4,"Unemployed");
	}};
	
	
	//行业类型
	@SuppressWarnings("serial")
	public final static Map<Integer, String> INDUSTRIES_MAP = new HashMap<Integer, String>(){{
		put(1,"金融服务/银行业");
        put(2,"教育");
        put(3,"服务业");
        put(4,"司法");
        put(5,"销售");
        put(6,"会计");
        put(7,"艺术");
        put(8,"政府机关");
        put(9,"零售");
        put(10,"计算机");
        put(11,"医疗");
        put(12,"电信");
        put(13,"其他");
	}};
	
	@SuppressWarnings("serial")
	public final static Map<Integer, String> EN_INDUSTRIES_MAP = new HashMap<Integer, String>(){{
		put(1,"Financial Services/Banking");
        put(2,"Education");
        put(3,"Hospitality");
        put(4,"Legal/Compliance");
        put(5,"Sales");
        put(6,"Accountancy");
        put(7,"Arts");
        put(8,"Government");
        put(9,"Wholesale");
        put(10,"IT");
        put(11,"Healthcare");
        put(12,"Telecom");
        put(13,"Others");
	}};
	
	//年收入
	@SuppressWarnings("serial")
	public final static Map<Integer, String> ANNUALINCOME_MAP = new HashMap<Integer, String>(){{
		put(1,"少于AUD$25,000");
	    put(2,"AUD$25,000 - AUD$49,999");
	    put(3,"AUD$50,000 - AUD$99,999");
	    put(4,"AUD$100,000 - AUD$249,999");
	    put(5,"AUD$250,000 - AUD$1,000,000");
	    put(6,"AUD$1,000,000 +");
	}};
	
	//年收入
		@SuppressWarnings("serial")
		public final static Map<Integer, String> EN_ANNUALINCOME_MAP = new HashMap<Integer, String>(){{
			put(1,"under AUD$25,000");
		    put(2,"AUD$25,000 - AUD$49,999");
		    put(3,"AUD$50,000 - AUD$99,999");
		    put(4,"AUD$100,000 - AUD$249,999");
		    put(5,"AUD$250,000 - AUD$1,000,000");
		    put(6,"AUD$1,000,000 +");
		}};
	
	//净资产
	@SuppressWarnings("serial")
	public final static Map<Integer, String> NETASSETSVALUE_MAP = new HashMap<Integer, String>(){{
		put(1,"少于AUD$25,000");
	    put(2,"AUD$25,000 - AUD$49,999");
	    put(3,"AUD$50,000 - AUD$99,999");
	    put(4,"AUD$100,000 - AUD$249,999");
	    put(5,"AUD$250,000 - AUD$1,000,000");
	    put(6,"AUD$1,000,000 +");
		
	}};
	
	@SuppressWarnings("serial")
	public final static Map<Integer, String> EN_NETASSETSVALUE_MAP = new HashMap<Integer, String>(){{
		put(1,"under AUD$25,000");
	    put(2,"AUD$25,000 - AUD$49,999");
	    put(3,"AUD$50,000 - AUD$99,999");
	    put(4,"AUD$100,000 - AUD$249,999");
	    put(5,"AUD$250,000 - AUD$1,000,000");
	    put(6,"AUD$1,000,000 +");
		
	}};
	
	//外汇/CFD投资经验
	@SuppressWarnings("serial")
	public final static Map<Integer, String> INVESTMENT_EXPERIENCE_MAP = new HashMap<Integer, String>(){{
		put(1,"无");
	    put(2,"1-3年");
	    put(3,"3年以上");
	}};
	
	//外汇/CFD投资经验
	@SuppressWarnings("serial")
	public final static Map<Integer, String> EN_INVESTMENT_EXPERIENCE_MAP = new HashMap<Integer, String>(){{
		put(1,"none");
	    put(2,"1-3 years");
	    put(3,"over 3 years");
	}};
		
		
	//其他投资经验
	@SuppressWarnings("serial")
	public final static Map<Integer, String> OTHER_EXPERINCE_MAP = new HashMap<Integer, String>(){{
		put(1,"无");
	    put(2,"1-3年");
	    put(3,"3年以上");
		
	}};
	
	//其他投资经验
	@SuppressWarnings("serial")
	public final static Map<Integer, String> EN_OTHER_EXPERINCE_MAP = new HashMap<Integer, String>(){{
		put(1,"none");
	    put(2,"1-3 years");
	    put(3,"over 3 years");
	}};
		
		
	//外汇/CFD投资频率
	@SuppressWarnings("serial")
	public final static Map<Integer, String> TRADING_FREQUENCY_MAP = new HashMap<Integer, String>(){{
		put(1,"每天");
	    put(2,"每周");
	    put(3,"每月");
	    put(4,"每年");
	    put(5,"更长");
	}};
	
	//外汇/CFD投资频率
	@SuppressWarnings("serial")
	public final static Map<Integer, String> EN_TRADING_FREQUENCY_MAP = new HashMap<Integer, String>(){{
		put(1,"Daily");
	    put(2,"Weekly");
	    put(3,"Monthly");
	    put(4,"Annually");
	    put(5,"Longer");
	}};
	
}
