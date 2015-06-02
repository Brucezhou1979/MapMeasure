/*package com.example.camerameasure;

import java.io.File;
import java.util.ArrayList;

import com.constant.Constants;
import com.database.PicView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class OtherWorkActivity extends Activity {

	private PicView mPicView;
	private Button mPre, mNext;
	private String mFile;
	private ArrayList<String> mImageList;
	private int position = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_other_work);
		Intent intent = getIntent();
		mFile = intent.getStringExtra(Constants.PATH);
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
		mPre.setOnClickListener(click);
		mNext.setOnClickListener(click);
		mPicView.setFile(mImageList.get(position));
	}

	OnClickListener click = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_pre:
				if (position == 0) {
					Toast.makeText(getBaseContext(), "亲，第一张图片啦！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				position--;
				mPicView.setFile(mImageList.get(position));
				break;
			case R.id.btn_next:
				if (position == mImageList.size() - 1) {
					Back();
					return;
				}
				position++;
				mPicView.setFile(mImageList.get(position));
				break;
			default:
				break;
			}
		}
	};

	private void Back() {
		AlertDialog.Builder builder = new Builder(OtherWorkActivity.this);
		builder.setMessage("是否计算结果？");
		builder.setTitle("提示");
		builder.setPositiveButton("确定",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(getBaseContext(), "当前计算面积为："+(int) (5+Math.random() * 90)+"平方米",
								Toast.LENGTH_LONG).show();
						dialog.dismiss();
					}
				});
		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
		builder.create().show();
	}

}
*/