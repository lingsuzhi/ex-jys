package com.lsz.jys.apply.base.service.impl;

import com.github.pagehelper.PageInfo;
import com.lsz.jys.apply.base.mapper.AdminInfoMapper;
import com.lsz.jys.apply.base.service.AdminInfoService;
import com.lsz.jys.common.BasePage;
import com.lsz.jys.common.PagesParam;
import com.lsz.jys.util.CommonUtils;
import com.lsz.jys.util.ValidateUtil;
import com.lsz.jys.pojo.AdminInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 *用户资料
 */
@Service("adminInfoServiceImpl")
public class AdminInfoServiceImpl implements AdminInfoService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @Override
    @Transactional
    public Map<String, Object> addAdminInfo(Map<String, Object> parameterMap) {
        parameterMap.put("createTime", new Date());
        parameterMap.put("updateTime", new Date());
        parameterMap.put("isDelete", 0);


        parameterMap.put("id", null);
        Integer count = adminInfoMapper.addAdminInfo(parameterMap);
        logger.info("addAdminInfo 完成:{}", count);
        return parameterMap;
    }

    @Override
    @Transactional
    public Map<String, Object> delAdminInfo(Map<String, Object> parameterMap) {
        String id = ValidateUtil.paramIsEmpty("id", parameterMap);
        Map<String, Object> paramMap = new HashMap<>();
        String[] produceIdList = id.split(",");
        List<String> produceIds = new ArrayList<>();
        for (String produceId : produceIdList) {
            String produce_id = "'" + produceId + "'";
            produceIds.add(produce_id);
        }

        paramMap.put("idList", produceIds);
        Integer count = adminInfoMapper.delAdminInfo(paramMap);
        logger.info("delAdminInfo 完成:{} {}", count, id);
        return CommonUtils.mapByMsg("删除完成！");
    }

    @Override
    @Transactional
    public Map<String, Object> updAdminInfo(Map<String, Object> parameterMap) {

        String id = ValidateUtil.validateParamContainKey("id", parameterMap);

        parameterMap.put("updateTime", new Date());
        Integer count = adminInfoMapper.updAdminInfo(parameterMap);
        logger.info("updAdminInfo 完成:{} {}", count, id);
        return parameterMap;
    }

    @Override
    public BasePage<AdminInfo> getAdminInfoList(PagesParam pageParam) {
        PagesParam.startPage(pageParam);
        List<AdminInfo> list = adminInfoMapper.getAdminInfoList(pageParam.getQuery());
        PageInfo<AdminInfo> pageInfo = new PageInfo<>(list);
        BasePage<AdminInfo> returnMap = new BasePage<>();

        if (list.size() > 0) {
            //处理
            for (AdminInfo objectMap : list) {
                manageAdminInfo(objectMap);
            }

            returnMap.setTotal(pageInfo.getTotal());
        } else {
            returnMap.setTotal(0L);
            list = new ArrayList<>();
        }

        returnMap.setContent(list);
        return returnMap;
    }

    //处理对象
    private static AdminInfo manageAdminInfo(AdminInfo adminInfo) {
        if (adminInfo == null) return adminInfo;

        return adminInfo;
    }

    @Override
    public AdminInfo getAdminInfo(Map<String, Object> parameterMap) {
        return manageAdminInfo(adminInfoMapper.getAdminInfo(parameterMap));
    }
}





