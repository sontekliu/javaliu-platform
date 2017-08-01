package com.javaliu.platform.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字工具类
 */
public class NumberUtils {

    /**
     * 金额千分位显示规则
     */
    public static final String THOUSANDST_POINT_TWO = ",###.00";

    /**
     * 是否是数值
     * @param value	格式为：+23.900，-23.90,23.98，23456 均为合法
     * @return 是否合法
     * @author  javaliu
     */
    public static boolean isNumber(String value){
        String regex = "^[\\-\\+]?\\d*\\.?(\\d+)$";
        return Pattern.matches(regex, value);
    }

    /**
     * 将Double类型的数据转换为千分位显示，本方法支持四舍五入
     * 格式如下：0        --> 0.00
     *        0.123     --> 0.12
     *        0.456     --> 0.46
     *        123.456   --> 123.46
     *        56789.00  --> 56,789.00
     *        45,456.456--> 45,456.46
     * @param  d   参数Long类型
     * @param  pattern 格式化规则
     * @return
     * @author javaliu
     */
    public static String formatThousand(Double d, String pattern){
        if(null == pattern){
            pattern = THOUSANDST_POINT_TWO;
        }
        if(d == null){
            throw new RuntimeException("输入值 [" + d + "] 不能为空");
        }
        StringBuilder str = new StringBuilder();
        NumberFormat thousandsFormat = new DecimalFormat(pattern);
        String value = thousandsFormat.format(d);
        if(value.startsWith(".")){
            str.append("0");
        }
        if(value.startsWith("-.")){
            value = value.replace("-.", "-0.");
        }
        return str.append(value).toString();
    }

    /**
     * 将输入的数值转化为千分位格式(进行四舍五入)
     * @param value		被转化的数值
     * @param decimals	保留小数位,数值大于0
     * @return
     * @author javaliu
     */
    public static String formatThousand(String value, int decimals){
        if(null == value){
            return value;
        }
        if(decimals <= 0){
            throw new RuntimeException("小数位数必须大于0");
        }
        //判断传入的值是否为数值
        boolean b = isNumber(value);
        //转化千分位的正则表达式
        String regex = "(\\d{1,3})(?=(\\d{3})+(\\.\\d{"+decimals+"})$)";
        //匹配数据的正则表达式
        String findRegex = "[\\-|+]?\\d+(\\.\\d{"+decimals+"})";
        if(b){
            BigDecimal data = new BigDecimal(value);
            data = data.setScale(decimals, BigDecimal.ROUND_HALF_UP);
            value = data.toString();
            int index = value.indexOf(".");
            if(-1 == index){//是整数，不是小数
                String str = ".";
                for (int i = 0; i < decimals; i++) {
                    str += "0";
                }
                value += str;
                return value.replaceAll(regex, "$1,");
            }else{
                //数据的小数位数
                int decimalCount = value.length() - value.indexOf(".") - 1;
                if((decimalCount - decimals) < 0){//已有的小数位数不到要保留的小数位数时，补零
                    for (int i = 0; i < decimals - decimalCount; i++) {
                        value += "0";
                    }
                }
                Pattern p = Pattern.compile(findRegex);
                Matcher matcher = p.matcher(value);
                if(matcher.find()){//注意一定要先执行find(),才能执行 group()，并且matcher对象为一个， 否则报错。
                    /**
                     *  取出匹配到的值
                     *  例如：123456.342  保留2位小数，取得s的值为 123456.34
                     */
                    String s = matcher.group();
                    return s.replaceAll(regex, "$1,");
                }
                return value.replaceAll(regex, "$1,");
            }
        }else{
            throw new RuntimeException("输入的值["+value+"]不为数值，请检查");
        }
    }
}
