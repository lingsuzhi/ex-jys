package com.lsz.jys.common;


import com.github.pagehelper.PageHelper;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class PagesParam {

    private Integer pageSize = 1;
    private Integer pageNum = 20;
    private String sort;
    private String order;
    private Map<String, Object> query;

    public static void startPage(PagesParam pagesParam) {
        String sortStr = pagesParam.getSort();
        if (!StringUtils.isEmpty(sortStr)) {
            if (StringUtils.isEmpty(pagesParam.getOrder())) {
                pagesParam.setOrder("desc");
            }
            sortStr += " " + pagesParam.getOrder();
        }
        PageHelper.startPage(pagesParam.getPageNum(), pagesParam.getPageSize(), sortStr);

        Map<String, Object> mapQuery = pagesParam.getQuery();
        if (!CollectionUtils.isEmpty(mapQuery)) {
            Map<String, Object> map = new HashMap<>();
            for (String key : mapQuery.keySet()) {
                Object obj = mapQuery.get(key);
                if (obj != null && !StringUtils.isEmpty(obj.toString())) {
                    map.put(key, obj);
                }
            }
            pagesParam.setQuery(map);
        }

    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Map<String, Object> getQuery() {
        return query;
    }

    public void setQuery(Map<String, Object> query) {
        this.query = query;
    }
}
