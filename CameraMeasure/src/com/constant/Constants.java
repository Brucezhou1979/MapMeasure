package com.constant;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Constants {
	@SuppressLint("SimpleDateFormat")
	public static String getTime() {
		SimpleDateFormat date = new SimpleDateFormat("yyyyy-MM-dd  hh:mm:ss");
		String time = date.format(new Date());
		return time;
	}

	public static String FILEBYTEDATA = "filebytedata";
	public static String PATH = "path";
	public static String TYPe = "type";
	public static  ArrayList<Double> L1=new ArrayList<Double>();
	//public static double L2;
	public static double M;
	public static double f1;
	public static double f2;
	public static int pre;
	public static int next;
}
