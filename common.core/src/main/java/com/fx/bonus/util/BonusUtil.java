package com.fx.bonus.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class BonusUtil {

	//	public static List<String> getTargetDepositDate(Calendar today){
	//		List<String> r=new ArrayList<String>();
	//		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
	//		int year=today.get(Calendar.YEAR);
	//		if(today.get(Calendar.DAY_OF_MONTH)==31)
	//			return r;
	//		else if(today.get(Calendar.MONTH)==Calendar.JANUARY&&today.get(Calendar.DAY_OF_MONTH)==30){
	//			r.add((year-1)+"-12-30");
	//			r.add((year-1)+"-12-31");
	//			return r;
	//		}
	//		else if(today.get(Calendar.MONTH)==Calendar.FEBRUARY&&today.get(Calendar.DAY_OF_MONTH)>=28){
	//			r.add(year+"-01-29");
	//			r.add(year+"-01-30");
	//			r.add(year+"-01-31");
	//			return r;
	//		}
	//		else if(today.get(Calendar.MONTH)==Calendar.MARCH&&today.get(Calendar.DAY_OF_MONTH)>=29){
	//			return r;
	//		}
	//		else if(today.get(Calendar.MONTH)==Calendar.APRIL&&today.get(Calendar.DAY_OF_MONTH)==30){
	//			r.add(year+"-03-30");
	//			r.add(year+"-03-31");
	//			return r;
	//		}
	//		else if(today.get(Calendar.MONTH)==Calendar.JUNE&&today.get(Calendar.DAY_OF_MONTH)==30){
	//			r.add(year+"-05-30");
	//			r.add(year+"-05-31");
	//			return r;
	//		}
	//		else if(today.get(Calendar.MONTH)==Calendar.AUGUST&&today.get(Calendar.DAY_OF_MONTH)==30){
	//			r.add(year+"-07-30");
	//			r.add(year+"-07-31");
	//			return r;
	//		}
	//		else if(today.get(Calendar.MONTH)==Calendar.SEPTEMBER&&today.get(Calendar.DAY_OF_MONTH)==30){
	//			r.add(year+"-08-30");
	//			r.add(year+"-08-31");
	//			return r;
	//		}
	//		else if(today.get(Calendar.MONTH)==Calendar.NOVEMBER&&today.get(Calendar.DAY_OF_MONTH)==30){
	//			r.add(year+"-10-30");
	//			r.add(year+"-10-31");
	//			return r;
	//		}
	//		else {
	//			today.add(Calendar.MONTH, -1);
	//			r.add(sf.format(today.getTime()));
	//		}
	//		
	//		
	//		
	//		return r;

	//	}
	public static int getTargetDepositDate(Calendar today){
		int r=1;
		if(today.get(Calendar.DAY_OF_MONTH)==31){
			today.add(Calendar.MONTH, -1);
			return 0;
		}
		else if(today.get(Calendar.MONTH)==Calendar.JANUARY&&today.get(Calendar.DAY_OF_MONTH)==30){
			today.add(Calendar.MONTH, -1);
			return 2;    //12.30-12.31
		}
		else if(today.get(Calendar.MONTH)==Calendar.MARCH&&today.get(Calendar.DAY_OF_MONTH)==1){
			today.add(Calendar.MONTH, -2);
			today.set(Calendar.DAY_OF_MONTH, 28);
			return 5;    //1.29-1.31
		}
		else if(today.get(Calendar.MONTH)==Calendar.FEBRUARY&&today.get(Calendar.DAY_OF_MONTH)>28){
			today.add(Calendar.MONTH, -1);
			today.set(Calendar.DAY_OF_MONTH, 28);
			return 4;    //1.29-1.31
		}
		else if(today.get(Calendar.MONTH)==Calendar.MARCH&&today.get(Calendar.DAY_OF_MONTH)>=29){
			GregorianCalendar cal=new GregorianCalendar ();
			if (cal.isLeapYear(today.get(Calendar.YEAR))&&today.get(Calendar.DAY_OF_MONTH)==29){
				today.add(Calendar.MONTH, -1);
				return 1;                    //leap year 2.29
			}else{
				today.add(Calendar.MONTH, -1);
				return 0;
			}

			    //   normal year
		}
		else if(today.get(Calendar.MONTH)==Calendar.APRIL&&today.get(Calendar.DAY_OF_MONTH)==30){
			today.add(Calendar.MONTH, -1);
			return 2;
		}
		else if(today.get(Calendar.MONTH)==Calendar.JUNE&&today.get(Calendar.DAY_OF_MONTH)==30){
			today.add(Calendar.MONTH, -1);
			return 2;
		}
		else if(today.get(Calendar.MONTH)==Calendar.AUGUST&&today.get(Calendar.DAY_OF_MONTH)==30){
			today.add(Calendar.MONTH, -1);
			return 2;
		}
		else if(today.get(Calendar.MONTH)==Calendar.SEPTEMBER&&today.get(Calendar.DAY_OF_MONTH)==30){
			today.add(Calendar.MONTH, -1);
			return 2;
		}
		else if(today.get(Calendar.MONTH)==Calendar.NOVEMBER&&today.get(Calendar.DAY_OF_MONTH)==30){
			today.add(Calendar.MONTH, -1);
			return 2;
		}
		else {
			today.add(Calendar.MONTH, -1);
			return r;
		}
	}
	public static void main(String[] args) {
		Calendar date=Calendar.getInstance();
		date.set(2014, 3, 1);
		System.out.println(getTargetDepositDate(date));
	}
}
