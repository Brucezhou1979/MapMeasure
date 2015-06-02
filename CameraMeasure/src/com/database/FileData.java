package com.database;

import android.content.ContentValues;

public class FileData {
private String filepath;
private String Lng;
private String Lati;
private String X;
private String Y;
private String Z ;
private String HeadingAngel;
public String getFilepath() {
	return filepath;
}
public void setFilepath(String filepath) {
	this.filepath = filepath;
}
public String getLng() {
	return Lng;
}
public void setLng(String lng) {
	Lng = lng;
}
public String getLati() {
	return Lati;
}
public void setLati(String lati) {
	Lati = lati;
}
public String getX() {
	return X;
}
public void setX(String x) {
	X = x;
}
public String getY() {
	return Y;
}
public void setY(String y) {
	Y = y;
}
public String getZ() {
	return Z;
}
public void setZ(String z) {
	Z = z;
}
public String getHeadingAngel() {
	return HeadingAngel;
}
public void setHeadingAngel(String headingAngel) {
	HeadingAngel = headingAngel;
}
public String getPitchAngle() {
	return PitchAngle;
}
public void setPitchAngle(String pitchAngle) {
	PitchAngle = pitchAngle;
}
public String getRollAngle() {
	return RollAngle;
}
public void setRollAngle(String rollAngle) {
	RollAngle = rollAngle;
}
private String PitchAngle;
private String RollAngle;
public FileData(String filepath,String Lng,String Lati,String X,String Y,String Z,String HeadingAngel,String PitchAngle,String RollAngle)
{
	this.filepath=filepath;
	this.Lng=Lng;
	this.Lati=Lati;
	this.X=X;
	this.Y=Y;
	this.Z=Z;
	this.HeadingAngel=HeadingAngel;
	this.PitchAngle=PitchAngle;
	this.RollAngle=RollAngle;
	}
public ContentValues getValues() {
	ContentValues values = new ContentValues();
	values.put("FilePath", filepath);
	values.put("Lng", Lng);
	values.put("Lati", Lati);
	values.put("X", X);
	values.put("Y", Y);
	values.put("Z", Z);
	values.put("HeadingAngel", HeadingAngel);
	values.put("PitchAngle", PitchAngle);
	values.put("RollAngle", RollAngle);
	return values;
}
}
