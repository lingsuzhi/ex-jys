package com.lsz.jys.apply.base.mapper;

import com.lsz.jys.pojo.AdminInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component("baseAdminInfoMapper")
public interface AdminInfoMapper {

    Integer addAdminInfo(Map<String, Object> paramMap);

    Integer delAdminInfo(Map<String, Object> paramMap);

    Integer killAdminInfo(Map<String, Object> paramMap);

    AdminInfo getAdminInfo(Map<String, Object> paramMap);

    Integer updAdminInfo(Map<String, Object> paramMap);

    List<AdminInfo> getAdminInfoList(Map<String, Object> paramMap);

    Integer getAdminInfoCount(Map<String, Object> paramMap);
}



