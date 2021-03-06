package com.example.camerameasure;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.camerameasure.math.PhotoCal;
import com.constant.Constants;
import com.tool.PicView;

public class WorkActivity extends Activity {
	private PicView mPicView;
	private Button mPre, mNext, mPre_Line, mNext_Line;
	private String mFile;
	
	/*
	 * 计算所用的相片文件列表
	 */
	private ArrayList<String> mImageList;
	
	

	
	
	/*
	 * 线的位置，记录高度值
	 */
	private int position = 0;
	
	/**
	 * 类型，1计算高度，2计算距离
	 */
	private int type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_work);
		Intent intent = getIntent();
		mFile = intent.getStringExtra(Constants.PATH);
		type = intent.getIntExtra(Constants.TYPe, -1);
		getFiles();
		intiUI();
	}

	private void getFiles() {
		mImageList = new ArrayList<String>();
		File dirFile = new File(mFile);
		if (dirFile.exists()) {
			for (File file : dirFile.listFiles()) {
				mImageList.add(file.getPath());
			}
		}
	}

	private void intiUI() {
		mPicView = (PicView) findViewById(R.id.picview);
		mPre = (Button) findViewById(R.id.btn_pre);
		mNext = (Button) findViewById(R.id.btn_next);
		mPre_Line = (Button) findViewById(R.id.pre_line);
		mNext_Line = (Button) findViewById(R.id.next_line);
		mPre.setOnClickListener(click);
		mNext.setOnClickListener(click);
		mPre_Line.setOnClickListener(click);
		mNext_Line.setOnClickListener(click);
		if (type == 1) {
			mPre_Line.setText("上边线");
			mNext_Line.setText("下边线");
		}
		mPicView.setFile(mImageList.get(position));
	}

	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_pre:
				mPicView.SetType(-1);
				if (position == 0) {
					Toast.makeText(getBaseContext(), "亲，第一张图片啦！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				position--;
				mPicView.setFile(mImageList.get(position));
				break;
			case R.id.btn_next:
				Constants.L1.add(mPicView.getL1L2());
				mPicView.SetType(-1);
				if (position == mImageList.size() - 1) {
					Back();
					return;
				}
				position++;
				mPicView.setFile(mImageList.get(position));
				break;
			case R.id.pre_line:
				if (type == 1)
					mPicView.SetType(2);
				if (type == 2)
					mPicView.SetType(0);
				break;
			case R.id.next_line:
				if (type == 1)
					mPicView.SetType(3);
				if (type == 2)
					mPicView.SetType(1);
				break;
			default:
				break;
			}
		}
	};

	private void Back() {
		AlertDialog.Builder builder = new Builder(WorkActivity.this);
		builder.setMessage("是否计算结果？");
		builder.setTitle("提示");
		builder.setPositiveButton("确定",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						double hh = 0;
						if (type == 1)						
							hh = PhotoCal.CalDisByTwoPicPixels(Constants.M,Constants.L1.get(0),Constants.L1.get(1),	Constants.f1,Constants.f2);							
						else
							hh = PhotoCal.CalHeightByTwoPicPixels(Constants.M, Constants.L1.get(0), Constants.L1.get(1), Constants.f1, Constants.f2);
						Toast.makeText(getBaseContext(), "当前测量值为：" +Math.abs( hh )+ "米",
								Toast.LENGTH_LONG).show();
						MainActivity.mTime = Constants.getTime();
						dialog.dismiss();
						finish();
					}
				});
		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						MainActivity.mTime = Constants.getTime();
						dialog.dismiss();
						finish();
					}
				});
		builder.create().show();
	}
}
