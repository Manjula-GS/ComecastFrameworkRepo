package com.comcast.crm.contacttest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;

public class JavaBasics {
	@Test
	public void date()
	{
		Date dateObj=new Date();
		
		SimpleDateFormat sim= new SimpleDateFormat("yyyy/MM/dd");
		String actDate = sim.format(dateObj);
		System.out.println(actDate);
		
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String dateReq = sim.format(cal.getTime());
		System.out.println(dateReq);
	}

}
