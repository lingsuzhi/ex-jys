package com.lsz.jys.apply.base.controller;


import com.lsz.jys.apply.base.service.AdminInfoService;
import com.lsz.jys.common.BasePage;
import com.lsz.jys.common.PagesParam;
import com.lsz.jys.common.ResponseInfo;
import com.lsz.jys.pojo.AdminInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 用户资料
 */
@RestController("baseAdminInfoController")
@RequestMapping("/adminInfoController")
public class AdminInfoController {

    @Autowired
    private AdminInfoService adminInfoService;

    /**
     * 添加单个
     */
    @RequestMapping(value = "/addAdminInfo", method = RequestMethod.POST)
    public ResponseInfo<AdminInfo> addAdminInfo(@RequestBody AdminInfo adminInfo) {
        return ResponseInfo.success(adminInfoService.addAdminInfo(adminInfo));
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/deleteAdminInfo", method = RequestMethod.POST)
    public ResponseInfo<Map<String, Object>> delAdminInfo(@RequestBody Map<String, Object> param) {

        Map<String, Object> resultMap = adminInfoService.delAdminInfo(param);
        return ResponseInfo.success(resultMap);
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/updateAdminInfo", method = RequestMethod.POST)
    public ResponseInfo<AdminInfo> updAdminInfo(@RequestBody AdminInfo adminInfo) {
        return ResponseInfo.success(adminInfoService.updAdminInfo(adminInfo));
    }

    /**
     * 查询单个
     */
    @RequestMapping(value = "/getAdminInfo", method = RequestMethod.POST)
    public ResponseInfo<AdminInfo> getAdminInfo(@RequestBody Map<String, Object> param) {

        AdminInfo resultMap = adminInfoService.getAdminInfo(param);
        return ResponseInfo.success(resultMap);
    }

    /**
     * 查询列表
     */
    @RequestMapping(value = "/getAdminInfoList", method = RequestMethod.POST)
    public ResponseInfo<BasePage<AdminInfo>> getAdminInfoList(@RequestBody PagesParam param) {

        BasePage<AdminInfo> resultMap = adminInfoService.getAdminInfoList(param);
        return ResponseInfo.success(resultMap);
    }
}
