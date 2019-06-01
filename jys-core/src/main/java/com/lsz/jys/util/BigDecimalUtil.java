package com.lsz.jys.util;



import com.lsz.jys.exception.BusinessException;

import java.math.BigDecimal;

public class BigDecimalUtil {
    public static boolean greater(BigDecimal decimal,BigDecimal decimal2){
        if (decimal == null){
            decimal = BigDecimal.ZERO;
        }
        if (decimal2 == null){
            decimal2 = BigDecimal.ZERO;
        }
        return decimal.compareTo(decimal2) > 0;
    }

    public static boolean less(BigDecimal decimal,BigDecimal decimal2){
        if (decimal == null){
            decimal = BigDecimal.ZERO;
        }
        if (decimal2 == null){
            decimal2 = BigDecimal.ZERO;
        }
        return decimal.compareTo(decimal2) < 0;
    }

    public static boolean equal(BigDecimal decimal,BigDecimal decimal2){
        if (decimal == null){
            decimal = BigDecimal.ZERO;
        }
        if (decimal2 == null){
            decimal2 = BigDecimal.ZERO;
        }
        return decimal.compareTo(decimal2) == 0;
    }

    public static BigDecimal sub(BigDecimal decimal,BigDecimal decimal2){
        if (decimal == null){
            decimal = BigDecimal.ZERO;
        }
        if (decimal2 == null){
            decimal2 = BigDecimal.ZERO;
        }
        return decimal.subtract(decimal2);
    }

    public static BigDecimal add(BigDecimal decimal,BigDecimal decimal2){
        if (decimal == null){
            decimal = BigDecimal.ZERO;
        }
        if (decimal2 == null){
            decimal2 = BigDecimal.ZERO;
        }
        return decimal.add(decimal2);
    }

    public static BigDecimal divide(BigDecimal decimal,BigDecimal decimal2){
        if (decimal == null){
            decimal = BigDecimal.ZERO;
        }
        if (decimal2 == null || equal(BigDecimal.ZERO,decimal2)){
            throw new BusinessException("除法操作异常,除数不能为0");
        }
        return decimal.divide(decimal2);
    }

    public static BigDecimal multiply(BigDecimal decimal,BigDecimal decimal2){
        if (decimal == null){
            decimal = BigDecimal.ZERO;
        }
        if (decimal2 == null){
            decimal2 = BigDecimal.ZERO;
        }
        return decimal.multiply(decimal2);
    }
}
