package com.lsz.jys.util;


import org.springframework.util.CollectionUtils;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtils {

    public static final String COMMON_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String COMMON_FORMAT_DAY = "yyyy-MM-dd";

    public static String findKeyByVal(Map<String, String> map, String val) {
        String resultStr = "";
        if (CollectionUtils.isEmpty(map)) {
            return resultStr;
        }
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value != null) {
                if (value.equals(val)) {
                    return key;
                }
            }

        }
        return resultStr;
    }


    public static Map<String, Object> mapClone(Map<String, Object> map) {
        Set<String> keys = map.keySet();
        if (CollectionUtils.isEmpty(keys)) {
            return map;
        }
        Map<String, Object> resultMap = new HashMap<>();
        for (String key : keys) {
            Object o = map.get(key);
            resultMap.put(key, o);
        }
        return resultMap;
    }

    public static Map<String, Object> mapByMsg(String msg) {
        Map<String, Object> resultContentMap = new HashMap<>();
        resultContentMap.put("msg", msg);
        return resultContentMap;
    }




    //拼接查询条件
    public static String spliceMap(Map<String, Object> map, String... inArr) {
        if (inArr.length > 0) {
            for (String inStr : inArr) {
                String val = ValidateUtil.paramIsEmpty(inStr, map);
                if (!StringUtils.isEmpty(val)) {
                    String[] split = val.split(",");
                    if (split != null && split.length > 0) {
                        String newStr = "";
                        for (String s : split) {
                            if (newStr.length() > 0) {
                                newStr = newStr + ",";
                            }
                            newStr += "'" + s + "'";
                        }
                        map.put(inStr, newStr);
                    }

                }
            }
        }
        if (CollectionUtils.isEmpty(map)) {
            return "";
        }
        for (String key : map.keySet()) {
            Object obj = map.get(key);
            if ("pageSize".equals(key)) {
                Integer pageSize = objToInt(obj);
                map.put("pageSize", pageSize);
                continue;
            }
            if ("pageNum".equals(key)) {
                Integer pageSize = objToInt(map.get("pageSize"));
                Integer pageNum = objToInt(obj);
                map.put("pageNum", pageNum);
                continue;
            }
            if (obj instanceof String) {
                map.put(key, obj.toString());
            }
        }
//        Object query = map.get("query");
//        if (query != null) {
//            Map<String, Object> mapQuery = (Map) query;
//            for (String key : mapQuery.keySet()) {
//                Object obj = mapQuery.get(key);
//                if (obj != null && !StringUtils.isEmpty(obj.toString())){
//                    map.put(key, obj);
//                }
//            }
//            map.remove("query");
//        }
        String sortStr = map.get("sort").toString();
        if (StringUtils.isEmpty(sortStr)) {
            return sortStr;
        }
        sortStr += " " + map.get("order");
        return sortStr;
    }

    public static int objToInt(Object obj) {
        try {

            return NumberUtils.parseNumber(obj.toString(), Double.class).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //拼接查询条件
    public static String spliceString(String stirng) {
        String returnString = "";
        if (!StringUtils.isEmpty(stirng)) {
            String newStrings[] = stirng.split(",");
            for (int i = 0; i < newStrings.length; i++) {
                if (i == newStrings.length - 1) {
                    returnString = returnString + "'" + newStrings[i] + "'";
                } else {
                    returnString = returnString + "'" + newStrings[i] + "',";
                }
            }
        }
        return returnString;
    }

    //把 下划线转换成 首字母大写形式
    public static String xhxToJavaStr(String key) {
        if (StringUtils.isEmpty(key)) {
            return key;
        }
        if (!key.contains("_")) {
            return key;
        }
        String returnStr = "";
        boolean b = false;
        for (char c : key.toCharArray()) {
            if (c == '_') {
                b = true;
                continue;
            }
            String tmpStr = c + "";
            if (b) {
                b = false;
                tmpStr = tmpStr.toUpperCase();
            }
            returnStr += tmpStr;

        }
        return returnStr;
    }

    public static void timestampToStr(Map<String, Object> map, String key, boolean isDay) {
        if (map == null) return;
        Object timestamp = map.get(key);
        if (timestamp instanceof Timestamp) {
            SimpleDateFormat format = new SimpleDateFormat(isDay ? COMMON_FORMAT_DAY : COMMON_FORMAT_DATETIME);
            Date dateTime = new Date(((Timestamp) timestamp).getTime());
            map.put(key, format.format(dateTime));
        }
    }

    public static void listMapSort(List<Map<String, Object>> maps, String key) {
        if (CollectionUtils.isEmpty(maps)) {
            return;
        }
        maps.sort((a, b) -> {
            String s1 = ValidateUtil.paramIsEmpty(key, a);
            String s2 = ValidateUtil.paramIsEmpty(key, b);
            return s1.compareTo(s2);
        });
    }

    public static Map<String, Object> toMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    public static Map<String,Object> listToMap(List<Map<String,Object>> maps,String key){
        if (CollectionUtils.isEmpty(maps)){
            return null;
        }
        Map<String,Object> resultMap = new HashMap<>();
        for (Map<String,Object> map : maps){
            Object o = map.get(key);
            if ( o != null){
                resultMap.put(o.toString(),map);
            }
        }
        return resultMap;
    }

    public static Map<String, Object> toMap(Object value) {
        return toMap("id", value);
    }
}
