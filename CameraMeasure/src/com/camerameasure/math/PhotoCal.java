package com.camerameasure.math;

import com.constant.Constants;
import com.camerameasure.math.PhotoCal;

//////

/**
 * ������Ƭ����ļ�����
 * @author Administrator
 *
 */
public class PhotoCal {
	//�����ƽ���뾶����λΪ��
	static double R = 6371393;
	static double PI = 3.1415926;
	
	/**
	 * ͨ����ǰ��ľ�γ���Լ�������۲��ľ���ͷ�λ�Ǽ����µ�ľ�γ��ֵ
	 * @param dAj ��֪��ľ���
	 * @param dAw ��֪���γ��
	 * @param dLong �����Ѿ���ľ��룬��λΪ��
	 * @param dAzimuth ����֪��ķ�λ�ǣ���λΪ���ȣ�����������ļн�
	 * @return
	 */
	public static LongLatPoint CalPosbyCurrentGPSPoint(double dAj, double dAw, double dLong, double dAzimuth)
	{
		double dc = dLong / R;
		dc = dc*180 / PI;
		///���a���õ�γ�ȵĽǶ�
		double a = Math.acos(Math.cos(90-dAj)*Math.cos(dc)+Math.sin(90-dAw)*Math.sin(dc)*Math.cos(dAzimuth));
		double dC = Math.asin(Math.sin(dc)*Math.sin(dAzimuth)/Math.sin(a));		
		double dBw =90 - a;
		double dBj = dAj + dC;
		LongLatPoint pt = new LongLatPoint();
		pt.dLongitude = dBj;
		pt.dLatitude = dBw;
		return pt;
	}
	
	
	/**
	 * ͨ������ĸ߶��Լ����Ķ�������ĵ�ķ���ǣ�����������ĵ��������ĵ�ĽǶ�
	 * @param dHeight ����ĳ���߶ȣ��߶�Ϊ��
	 * @param dAzimuth �����ĵ�ķ�λ�ǣ��Ƕ�
	 * @return
	 */
	public static double CalDistanceByAzimuth(double dHeight, double dAzimuth)
	{
		double dDis = 0.0;
		dDis = Math.tan(dAzimuth)* dHeight;
		return dDis;
	}
	
	
	/**
	 * ����Ƭ��Ӱ����׸߶ȼ��㹫ʽ
	 * @param dMoveStep ����Ƭ�ƶ�����
	 * @param dL1 �ڵ�һ����Ƭ�еĸ߶Ȼ���룬ͨ���������ߵ�������CCD��С����
	 * @param dL2 �ڵڶ�����Ƭ�еĸ߶Ȼ���룬ͨ���������ߵ�������CCD��С����
	 * @param dfoucs1 ��һ����Ƭ����ʱ�Ľ���
	 * @param dfoucs2 �ڶ�����Ƭ����ʱ�ļ���
	 * @return
	 */
	public static double CalHeightByTwoPicPixels(double dMoveStep, double dL1, double dL2, double dfoucs1, double dfoucs2)
	{
		double dDis =0.0;
		dDis = dMoveStep*dL1*dL2 / (dfoucs1*dL2 - dfoucs2*dL1);
		return dDis;
	}

	/**
	 * ����Ƭ��Ӱ����׾�����㹫ʽ
	 * @param dMoveStep ����Ƭ�ƶ�����
	 * @param dL1 �ڵ�һ����Ƭ�еĸ߶Ȼ���룬ͨ���������ߵ�������CCD��С����
	 * @param dL2 �ڵڶ�����Ƭ�еĸ߶Ȼ���룬ͨ���������ߵ�������CCD��С����
	 * @param dfoucs1 ��һ����Ƭ����ʱ�Ľ���
	 * @param dfoucs2 �ڶ�����Ƭ����ʱ�ļ���
	 * @return
	 */
	public static double CalDisByTwoPicPixels(double dMoveStep, double dL1, double dL2, double dfoucs1, double dfoucs2)
	{
		double dHeight =0.0;
		dHeight = (dMoveStep*dfoucs1*dL2)/(dfoucs1*dL2 - dfoucs2*dL1);
		return dHeight;
	}
}