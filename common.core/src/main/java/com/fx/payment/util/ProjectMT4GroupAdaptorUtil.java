package com.fx.payment.util;

import java.util.ArrayList;
import java.util.List;

import com.fx.MT4.enums.MT4GroupIdEnum;
import com.fx.payment.enums.ProjectTypeEnum;

public class ProjectMT4GroupAdaptorUtil {
	
	public List<MT4GroupIdEnum> getGroupIdByProjectType(ProjectTypeEnum typeEnum){
		List<MT4GroupIdEnum> groupIdEnumList = new ArrayList<MT4GroupIdEnum>();
		if (ProjectTypeEnum.COMMON.compareTo(typeEnum) == 0)  {
			groupIdEnumList.add(MT4GroupIdEnum.M_VFX_PACF_USD);
			groupIdEnumList.add(MT4GroupIdEnum.M_VFX_PACFV_USD);
		}
		if(ProjectTypeEnum.CASH_DIVIDEND.compareTo(typeEnum) == 0){
			groupIdEnumList.add(MT4GroupIdEnum.M_VFX_PAC_P_USD);
		}
		if(ProjectTypeEnum.GUARANTEED.compareTo(typeEnum) == 0){
			groupIdEnumList.add(MT4GroupIdEnum.M_VFX_PAC_USD);
		}
		if(ProjectTypeEnum.VIP.compareTo(typeEnum) == 0){
			groupIdEnumList.add(MT4GroupIdEnum.M_VFX_PAC_AUD);
			groupIdEnumList.add(MT4GroupIdEnum.M_VFX_PAC_P_AUD);
		}
		return groupIdEnumList;
	}

}
