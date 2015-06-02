package com.database;

import java.io.File;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class CharDBHelper {
	private final static String DATABASE_NAME = "geoSeal.db";
	private final static int DATABASE_VERSION = 1;
	private SQLiteDatabase db;
	private Context context;
	private String mDataBaseName;
	private String TABLE_FILEDATA_NAME = "FileData";

	public CharDBHelper(Context context) {
		this.context = context;
		mDataBaseName = getMyDatabaseName(context);
		db = openDatabase();
	}

	private static String getMyDatabaseName(Context context) {
		boolean isSdcardEnable = false;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			isSdcardEnable = true;
		}
		String DB_PATH = null;
		if (isSdcardEnable) {
			DB_PATH =Environment.getExternalStorageDirectory()
					+ File.separator + "CameraMeasure" + File.separator+"database"+File.separator;
		} else {// 未插入SDCard，建在内存中
			DB_PATH = context.getFilesDir().getPath() + File.separator + "CameraMeasure" + File.separator+"database"+File.separator;
		}
		File file = new File(DB_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}
		return DB_PATH + DATABASE_NAME;
	}

	private SQLiteDatabase openDatabase() {
		try {
			SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(
					mDataBaseName, null);
			CreateTable(database);
			return database;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void CreateTable(SQLiteDatabase db) {
		db.beginTransaction();
		db.execSQL("create table if not exists  "
				+ TABLE_FILEDATA_NAME
				+ " (FilePath varchar(30),Lng varchar(30),Lati varchar(30),X varchar(30),Y varchar(30),Z varchar(30),HeadingAngel varchar(30),PitchAngle varchar(30),RollAngle varchar(30)");
		db.setTransactionSuccessful();
		db.endTransaction(); //
	}

	public int getVersion() {
		return db.getVersion();
	}

	public void closeDB() {
		if (db != null)
			db.close();
	}

	public boolean isExistFavorite(FileData rec) {
		if (rec == null)
			return false;
		boolean isExist = false;
		Cursor c = db
				.query(TABLE_FILEDATA_NAME, new String[] { "FilePath" },
						"FilePath = ?  ", new String[] { rec.getFilepath() }, null, null, null);
		if (c.moveToNext())
			isExist = true;
		c.close();
		return isExist;
	}

	public boolean insertFavoriteRec(FileData rec) {
		if (rec == null)
			return false;
		String strTable = TABLE_FILEDATA_NAME;

		if (isExistFavorite(rec))
			db.update(strTable, rec.getValues(), "FilePath=?",
					new String[] { rec.getFilepath()});
		else

			db.insert(strTable, null, rec.getValues());
		return true;
	}
	


	public String getFavorContent(String User) {
		Cursor c = db.query(TABLE_FILEDATA_NAME, new String[] { "Name" },
				"User = ?", new String[] { User }, null, null, null);
		String favorContent = "";
		while (c.moveToNext()) {
			favorContent += c.getString(0);
		}
		c.close();
		return favorContent;
	}
}
