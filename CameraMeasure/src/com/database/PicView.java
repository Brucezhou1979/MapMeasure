package com.database;

import com.example.camerameasure.MainActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

public class PicView extends ImageView {
	private Bitmap mCharBitmap = null;
	private Paint mFillBlackPaint = null;
	private Paint mPaint = new Paint(1);
	private Matrix matrix = new Matrix();

	public PicView(Context context, int fontSize) {
		super(context, null);
		init();
	}

	public PicView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public PicView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		mFillBlackPaint = new Paint();
		mFillBlackPaint.setColor(0xff000000);
		mFillBlackPaint.setStyle(Paint.Style.FILL);
		mFillBlackPaint.setAntiAlias(true);
		this.mPaint.setStyle(Paint.Style.STROKE);
		this.mPaint.setStrokeWidth(3.0F);
		this.mPaint.setStrokeCap(Paint.Cap.ROUND);
		this.mPaint.setColor(-16776961);

	}


 
	public void clear() {
		if (mCharBitmap != null) {
			mCharBitmap.recycle();
			mCharBitmap = null;
		}
		
		
		System.gc();
	}
	public void setFile(Bitmap bm) {
		clear();
		invalidate();
		Matrix matrix1 = new Matrix();  
        // 缩放原图  
        matrix1.postScale(1f, 1f);  
        // 向左旋转45度，参数为正则向右旋转  
        matrix1.postRotate(90);  
        //bmp.getWidth(), 500分别表示重绘后的位图宽高  
        Bitmap dstbmp = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),  bm.getHeight(),  
                matrix1, true); 
		mCharBitmap = FitTheScreenSizeImage(dstbmp, MainActivity.width,
				MainActivity.height);
	}
	public Bitmap FitTheScreenSizeImage(Bitmap m, int ScreenWidth,
			int ScreenHeight) {
		float width = (float) ScreenWidth / m.getWidth();
		float height = (float) ScreenHeight / m.getHeight();
		Matrix matrix = new Matrix();
		matrix.postScale(width, height);
		return Bitmap.createBitmap(m, 0, 0, m.getWidth(), m.getHeight(),
				matrix, true);
	}
	protected void onDraw(Canvas canvas) {
		if (mCharBitmap != null)
			canvas.drawBitmap(mCharBitmap, matrix, mPaint);
	}

	
}