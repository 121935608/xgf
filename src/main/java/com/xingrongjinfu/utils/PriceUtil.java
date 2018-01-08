package com.xingrongjinfu.utils;

import java.text.NumberFormat;

/**
 * @创建人 Lelouch
 * @创建时间 $date$
 * @描述
 */
public class PriceUtil {

    public static String priceFormat(double price){
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        nf.setGroupingUsed(false);
        return nf.format((double)price/100);
    }
}
