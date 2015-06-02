package com.tool;

import com.example.camerameasure.MainActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class PicView extends ImageView {
	private Bitmap mCharBitmap = null;
	private Bitmap bitmap = null;
	private Bitmap bitmap1 = null;
	private Paint mFillBlackPaint = null;
	private Paint mPaint = new Paint(1);
	private Canvas mCanvas, mCanvas1;
	// 二次贝塞尔，实现平滑曲线；previousX, previousY为操作点，cX, cY为终点
	private float downX = 0, downY = 0, upX = 0, upY = 0, lateX = 0, lateY = 0;
	private Matrix matrix = new Matrix();
	private int linestate = -1;

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

	public void setFile(String filepath) {
		clear();
		invalidate();
		Bitmap bm = BitmapFactory.decodeFile(filepath);
		mCharBitmap = FitTheScreenSizeImage(bm, MainActivity.width,
				MainActivity.height);
		bitmap = Bitmap.createBitmap(mCharBitmap.getWidth(),
				mCharBitmap.getHeight(), mCharBitmap.getConfig());
		bitmap1 = Bitmap.createBitmap(mCharBitmap.getWidth(),
				mCharBitmap.getHeight(), mCharBitmap.getConfig());
		mCanvas = new Canvas(bitmap);
		mCanvas1 = new Canvas(bitmap);
	}

	public void SetType(int type) {
		linestate = type;
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

	public void clear() {
		if (mCharBitmap != null) {
			mCharBitmap.recycle();
			mCharBitmap = null;
		}
		if (bitmap != null) {
			bitmap.recycle();
			bitmap = null;
		}
		if (bitmap1 != null) {
			bitmap1.recycle();
			bitmap1 = null;
		}
		System.gc();
	}

	protected void onDraw(Canvas canvas) {
		if (mCharBitmap != null)
			canvas.drawBitmap(mCharBitmap, matrix, mPaint);
		if (bitmap != null)
			canvas.drawBitmap(bitmap, 0, 0, null);
		if (bitmap1 != null)
			canvas.drawBitmap(bitmap1, 0, 0, null);
	}

	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		// 判斷不同狀態
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			// 按下時記錄座標
			downX = event.getX();
			downY = event.getY();
			break;

		case MotionEvent.ACTION_MOVE: // 移動過程中，不斷繪製Line
			/*
			 * upX = event.getX(); upY = event.getY(); mCanvas.drawLine(downX,
			 * 0, upX, mCanvas.getHeight(), this.mPaint); invalidate(); downX =
			 * upX; downY = upY;
			 */

			switch (linestate) {
			case 0:// 左边线
				upX = event.getX();
				upY = event.getY();
				mCanvas = null;
				bitmap = null;
				bitmap = Bitmap.createBitmap(mCharBitmap.getWidth(),
						mCharBitmap.getHeight(), mCharBitmap.getConfig());
				mCanvas = new Canvas(bitmap);
				mCanvas.drawLine(upX, 0, upX, mCanvas.getHeight(), this.mPaint);
				invalidate();
				break;
			case 1:// 右边线
				lateX = event.getX();
				lateY = event.getY();
				mCanvas1 = null;
				bitmap1 = null;
				bitmap1 = Bitmap.createBitmap(mCharBitmap.getWidth(),
						mCharBitmap.getHeight(), mCharBitmap.getConfig());
				mCanvas1 = new Canvas(bitmap1);
				mCanvas1.drawLine(lateX, 0, lateX, mCanvas1.getHeight(),
						this.mPaint);
				invalidate();
				break;
			case 2:// 上边线
				upX = event.getX();
				upY = event.getY();
				mCanvas = null;
				bitmap = null;
				bitmap = Bitmap.createBitmap(mCharBitmap.getWidth(),
						mCharBitmap.getHeight(), mCharBitmap.getConfig());
				mCanvas = new Canvas(bitmap);
				mCanvas.drawLine(0, upY, mCanvas.getWidth(), upY, this.mPaint);
				invalidate();
				break;
			case 3:// 下边线
				lateX = event.getX();
				lateY = event.getY();
				mCanvas1 = null;
				bitmap1 = null;
				bitmap1 = Bitmap.createBitmap(mCharBitmap.getWidth(),
						mCharBitmap.getHeight(), mCharBitmap.getConfig());
				mCanvas1 = new Canvas(bitmap1);
				mCanvas1.drawLine(0, lateY, mCanvas1.getWidth(), lateY,
						this.mPaint);
				invalidate();
				break;

			default:
				break;
			}
			break;

		case MotionEvent.ACTION_UP:
			// 停止時，記錄座標
			switch (linestate) {
			case 0:// 左边线
				mCanvas = null;
				bitmap = null;
				bitmap = Bitmap.createBitmap(mCharBitmap.getWidth(),
						mCharBitmap.getHeight(), mCharBitmap.getConfig());
				mCanvas = new Canvas(bitmap);
				mCanvas.drawLine(upX, 0, upX, mCanvas.getHeight(), this.mPaint);
				break;
			case 1:// 右边线
				mCanvas1 = null;
				bitmap1 = null;
				bitmap1 = Bitmap.createBitmap(mCharBitmap.getWidth(),
						mCharBitmap.getHeight(), mCharBitmap.getConfig());
				mCanvas1 = new Canvas(bitmap1);
				mCanvas1.drawLine(lateX, 0, lateX, mCanvas1.getHeight(),
						this.mPaint);
				break;
			case 2:// 上边线
				upX = event.getX();
				upY = event.getY();
				mCanvas = null;
				bitmap = null;
				bitmap = Bitmap.createBitmap(mCharBitmap.getWidth(),
						mCharBitmap.getHeight(), mCharBitmap.getConfig());
				mCanvas = new Canvas(bitmap);
				mCanvas.drawLine(0, upY, mCanvas.getWidth(), upY, this.mPaint);
				break;
			case 3:// 下边线
				lateX = event.getX();
				lateY = event.getY();
				mCanvas1 = null;
				bitmap1 = null;
				bitmap1 = Bitmap.createBitmap(mCharBitmap.getWidth(),
						mCharBitmap.getHeight(), mCharBitmap.getConfig());
				mCanvas1 = new Canvas(bitmap1);
				mCanvas1.drawLine(0, lateY, mCanvas1.getWidth(), lateY,
						this.mPaint);
				break;

			default:
				break;
			}
			invalidate();
			break;
		case MotionEvent.ACTION_CANCEL:
			break;
		default:
			break;
		}
		// 返回true表示，一旦事件开始就要继续接受触摸事件
		return true;
	}

	public double getL1L2() {
		if (linestate < 2)
			return Math.abs(lateX - upX);
		else
			return Math.abs(lateY - upY);

	}

	public void drawImage(Canvas canvas, Bitmap blt, int x, int y, int w,
			int h, int bx, int by) {
		Rect src = new Rect();// 图片 >>原矩形
		Rect dst = new Rect();// 屏幕 >>目标矩形

		src.left = bx;
		src.top = by;
		src.right = bx + w;
		src.bottom = by + h;

		dst.left = x;
		dst.top = y;
		dst.right = x + w;
		dst.bottom = y + h;
		// 画出指定的位图，位图将自动--》缩放/自动转换，以填补目标矩形
		// 这个方法的意思就像 将一个位图按照需求重画一遍，画后的位图就是我们需要的了
		canvas.drawBitmap(blt, null, dst, null);
		src = null;
		dst = null;
	}
}