package com.fx.util;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Formatter;

public final class NumberFormatUitl {

  private NumberFormatUitl() {
  }
  /**
   * 使用BigDecimal，截取小数点后两位
   */
  public static String cutOut2(BigDecimal a){
    String s = "0";
    if(a!=null){
      if(a.compareTo(new BigDecimal(0))!=0){
        DecimalFormat df = new DecimalFormat("0.0000");//格式化小数
        s= df.format(a.setScale(4, RoundingMode.DOWN).doubleValue());//返回的是String类型
      }
    }
    return  s;
  }
  public static String cutOut(BigDecimal a){
    String s = "0";
    if(a!=null){
      if(a.compareTo(new BigDecimal(0))!=0){
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        s= df.format(a.setScale(2, RoundingMode.DOWN).doubleValue());//返回的是String类型
      }
    }
    return  s;
  }
  /**
   * 使用BigDecimal，截取小数点后两位
   */
  public static BigDecimal cutOutBigDecimal(BigDecimal a){
    if(a!=null){
      if(a.compareTo(new BigDecimal(0))!=0){
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        String s = df.format(a.setScale(2, RoundingMode.DOWN).doubleValue());//返回的是String类型
        a = new BigDecimal(s);
      }else {
        a = new BigDecimal(0);
      }
    }else{
      a = new BigDecimal(0);
    }
    return  a;
  }
  public static BigDecimal cutOutBigDecimal2(BigDecimal a){
    if(a!=null){
      if(a.compareTo(new BigDecimal(0))!=0){
        DecimalFormat df = new DecimalFormat("0.0000");//格式化小数
        String s = df.format(a.setScale(4, RoundingMode.DOWN).doubleValue());//返回的是String类型
        a = new BigDecimal(s);
      }else {
        a = new BigDecimal(0);
      }
    }else{
      a = new BigDecimal(0);
    }
    return  a;
  }
  /**
   * 使用BigDecimal，截取小数点后两位
   */
  public static Double cutOutDouble(Double a){
    if(a!=null){
      if(a!=0){
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        String s = df.format(new BigDecimal(a+"").setScale(2, RoundingMode.DOWN).doubleValue());//返回的是String类型
        //a = Double.parseDouble(s);
        a = new Double(s);
      }
    }else{
      a = new Double(0);
    }
    return  a;
  }
  public static Double cutOutDouble2(Double a){
    if(a!=null){
      if(a!=0){
        DecimalFormat df = new DecimalFormat("0.0000");//格式化小数
        String s = df.format(new BigDecimal(a+"").setScale(4, RoundingMode.DOWN).doubleValue());//返回的是String类型
        //a = Double.parseDouble(s);
        a = new Double(s);
      }
    }else{
      a = new Double(0);
    }
    return  a;
  }
  /**
   * 使用BigDecimal，截取小数点后两位
   */
  public static String cutOutString(Double a){
    String s = "0";
    if(a!=null){
      if(a!=0){
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        s = df.format(new BigDecimal(a+"").setScale(2, RoundingMode.DOWN).doubleValue());//返回的是String类型
      }
    }
    return  s;
  }
  public static String cutOutString2(Double a){
    String s = "0";
    if(a!=null){
      if(a!=0){
        DecimalFormat df = new DecimalFormat("0.0000");//格式化小数
        s = df.format(new BigDecimal(a+"").setScale(4, RoundingMode.DOWN).doubleValue());//返回的是String类型
      }
    }
    return  s;
  }
  /**
   * 使用BigDecimal，保留小数点后两位
   */
  public static String format1(double value) {

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    return bd.toString();
  }
  public static String format1( BigDecimal bd) {
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    return bd.toString();
  }

  /**
   * 使用DecimalFormat,保留小数点后两位
   */
  public static String format2(double value) {

    DecimalFormat df = new DecimalFormat("0.00");
    df.setRoundingMode(RoundingMode.HALF_UP);
    return df.format(value);
  }

  /**
   * 使用NumberFormat,保留小数点后两位
   */
  public static String format3(double value) {

    NumberFormat nf = NumberFormat.getNumberInstance();
    nf.setMaximumFractionDigits(2);
    /*
     * setMinimumFractionDigits设置成2
     * 
     * 如果不这么做，那么当value的值是100.00的时候返回100
     * 
     * 而不是100.00
     */
    nf.setMinimumFractionDigits(2);
    nf.setRoundingMode(RoundingMode.HALF_UP);
    /*
     * 如果想输出的格式用逗号隔开，可以设置成true
     */
    nf.setGroupingUsed(false);
    return nf.format(value);
  }

  /**
   * 使用java.util.Formatter,保留小数点后两位
   */
  public static String format4(double value) {
    /*
     * %.2f % 表示 小数点前任意位数 2 表示两位小数 格式后的结果为 f 表示浮点型
     */
    return new Formatter().format("%.2f", value).toString();
  }

  /**
   * 使用String.format来实现。
   * 
   * 这个方法其实和format4是一样的
   */
  public static String format5(double value) {

    return String.format("%.2f", value).toString();
  }


  public static void main(String args[]) {
      //NumberFormatUitl.cutOutDouble(1.80d);
    System.out.println(NumberFormatUitl.cutOutBigDecimal(new BigDecimal(1)));
    System.out.println(NumberFormatUitl.cutOutBigDecimal(new BigDecimal(0.0)));
    System.out.println(NumberFormatUitl.cutOutBigDecimal(new BigDecimal(0.1)));
    System.out.println(NumberFormatUitl.cutOutBigDecimal(new BigDecimal(0.01)));
    System.out.println(NumberFormatUitl.cutOutBigDecimal(new BigDecimal(0.001)));
    System.out.println(NumberFormatUitl.cutOutBigDecimal(new BigDecimal(0.006)));
    //System.out.println(new BigDecimal(0).compareTo(new BigDecimal(0.0)));
   // System.out.println(0 ==0.0);
  }
}