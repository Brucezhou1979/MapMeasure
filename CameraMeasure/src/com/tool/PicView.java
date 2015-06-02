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

/**
 * 相片显示类
 * @author Administrator
 *
 */
public class PicView extends ImageView {
	private Bitmap mCharBitmap = null;
	private Bitmap bitmap = null;
	private Bitmap bitmap1 = null;
	private Paint mFillBlackPaint = null;
	private Paint mPaint = new Paint(1);
	private Canvas mCanvas, mCanvas1;
	// 浜屾璐濆灏旓紝瀹炵幇骞虫粦鏇茬嚎锛沺reviousX, previousY涓烘搷浣滅偣锛宑X, cY涓虹粓鐐�
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
		// 鍒ゆ柗涓嶅悓鐙�鎱�
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			// 鎸変笅鏅傝閷勫骇妯�
			downX = event.getX();
			downY = event.getY();
			break;

		case MotionEvent.ACTION_MOVE: // 绉诲嫊閬庣▼涓紝涓嶆柗绻＝Line
			/*
			 * upX = event.getX(); upY = event.getY(); mCanvas.drawLine(downX,
			 * 0, upX, mCanvas.getHeight(), this.mPaint); invalidate(); downX =
			 * upX; downY = upY;
			 */

			switch (linestate) {
			case 0:// 宸﹁竟绾�
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
			case 1:// 鍙宠竟绾�
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
			case 2:// 涓婅竟绾�
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
			case 3:// 涓嬭竟绾�
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
			// 鍋滄鏅傦紝瑷橀寗搴ф
			switch (linestate) {
			case 0:// 宸﹁竟绾�
				mCanvas = null;
				bitmap = null;
				bitmap = Bitmap.createBitmap(mCharBitmap.getWidth(),
						mCharBitmap.getHeight(), mCharBitmap.getConfig());
				mCanvas = new Canvas(bitmap);
				mCanvas.drawLine(upX, 0, upX, mCanvas.getHeight(), this.mPaint);
				break;
			case 1:// 鍙宠竟绾�
				mCanvas1 = null;
				bitmap1 = null;
				bitmap1 = Bitmap.createBitmap(mCharBitmap.getWidth(),
						mCharBitmap.getHeight(), mCharBitmap.getConfig());
				mCanvas1 = new Canvas(bitmap1);
				mCanvas1.drawLine(lateX, 0, lateX, mCanvas1.getHeight(),
						this.mPaint);
				break;
			case 2:// 涓婅竟绾�
				upX = event.getX();
				upY = event.getY();
				mCanvas = null;
				bitmap = null;
				bitmap = Bitmap.createBitmap(mCharBitmap.getWidth(),
						mCharBitmap.getHeight(), mCharBitmap.getConfig());
				mCanvas = new Canvas(bitmap);
				mCanvas.drawLine(0, upY, mCanvas.getWidth(), upY, this.mPaint);
				break;
			case 3:// 涓嬭竟绾�
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
		// 杩斿洖true琛ㄧず锛屼竴鏃︿簨浠跺紑濮嬪氨瑕佺户缁帴鍙楄Е鎽镐簨浠�
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
		Rect src = new Rect();// 鍥剧墖 >>鍘熺煩褰�
		Rect dst = new Rect();// 灞忓箷 >>鐩爣鐭╁舰

		src.left = bx;
		src.top = by;
		src.right = bx + w;
		src.bottom = by + h;

		dst.left = x;
		dst.top = y;
		dst.right = x + w;
		dst.bottom = y + h;
		// 鐢诲嚭鎸囧畾鐨勪綅鍥撅紝浣嶅浘灏嗚嚜鍔�--銆嬬缉鏀�/鑷姩杞崲锛屼互濉ˉ鐩爣鐭╁舰
		// 杩欎釜鏂规硶鐨勬剰鎬濆氨鍍� 灏嗕竴涓綅鍥炬寜鐓ч渶姹傞噸鐢讳竴閬嶏紝鐢诲悗鐨勪綅鍥惧氨鏄垜浠渶瑕佺殑浜�
		canvas.drawBitmap(blt, null, dst, null);
		src = null;
		dst = null;
	}
}