package com.lsz.jys.apply.base.service;

import com.lsz.jys.common.BasePage;
import com.lsz.jys.common.PagesParam;
import com.lsz.jys.pojo.AdminInfo;


import java.util.Map;

/**
 * 用户资料
 */
public interface AdminInfoService {

    AdminInfo addAdminInfo(AdminInfo adminInfo);

    Map<String, Object> delAdminInfo(Map<String, Object> parameterMap);

    AdminInfo updAdminInfo(AdminInfo adminInfo);

    BasePage<AdminInfo> getAdminInfoList(PagesParam parameterMap);

    AdminInfo getAdminInfo(Map<String, Object> parameterMap);
}


