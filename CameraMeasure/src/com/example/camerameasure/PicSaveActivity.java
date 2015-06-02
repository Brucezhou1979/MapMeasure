package com.example.camerameasure;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.constant.Constants;
import com.database.PicView;

public class PicSaveActivity extends Activity {
	private Button mBtnsave, mBtncancel;
	private byte[] data;
	private String PATH = "";
	//private ImageView mImageView;
	private File file;
    private PicView mPicView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_pic_save);
		Intent intent = getIntent();
		data = intent.getByteArrayExtra(Constants.FILEBYTEDATA);
		PATH = intent.getStringExtra(Constants.PATH);
		initUI();
	}

	private void initUI() {
		mBtnsave = (Button) findViewById(R.id.save);
		mBtncancel = (Button) findViewById(R.id.cancel);
		mBtnsave.setOnClickListener(click);
		mBtncancel.setOnClickListener(click);
/*		mBtnsave.setRotation(270);
		mBtncancel.setRotation(270);*/
		mPicView=(PicView)findViewById(R.id.picview1);
		mPicView.setFile(Bytes2Bimap(data));
		//mImageView = (ImageView) findViewById(R.id.imageview);
		//mImageView.setImageBitmap(Bytes2Bimap(data));
		//mImageView.setImageBitmap(zoomBitmap());
	}

	public Bitmap Bytes2Bimap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}

	

	private void savePic() {
		file = new File(PATH, Constants.getTime() + ".jpg");
	    File f=new File(PATH);
	    if(!f.exists())
	    	f.mkdirs();
		try {
			// 获得文件输出流
			FileOutputStream fos = new FileOutputStream(file.getPath());
			Bitmap bitmap = zoomBitmap();
			 BufferedOutputStream bos = new BufferedOutputStream(fos);
			 bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);    
		     bos.flush();    
		     bos.close();    
			// 写到该文件
			//fos.write(data);
			// 关闭文件流
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Bitmap zoomBitmap(){
		Bitmap bitmap = Bytes2Bimap(data);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(90);
       // float scaleWidth = ((float) w / width);
       // float scaleHeight = ((float) h / height);
       // matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                        matrix, true);
        return newBmp;
}

	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.save:
				savePic();
				setResult(RESULT_OK);
				finish();
				break;
			case R.id.cancel:
				setResult(RESULT_CANCELED);
				finish();
				break;
			default:
				break;
			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			setResult(RESULT_CANCELED);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
