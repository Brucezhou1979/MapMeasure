package com.example.camerameasure;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

public class DrawThread extends Thread {

	private SurfaceHolder mHolder = null;
	private boolean isRun = false;

	public DrawThread(SurfaceHolder holder) {
		mHolder = holder;

	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	@Override
	public void run() {
		int count = 0;

		while (isRun) {
			Canvas canvas = null;
			synchronized (mHolder) {
				try {
					canvas = mHolder.lockCanvas();
					canvas.drawColor(Color.WHITE);
					Paint p = new Paint();
					p.setColor(Color.BLACK);

					Rect r = new Rect(100, 50, 300, 250);
					canvas.drawRect(r, p);
					canvas.drawText("这是第" + (count++) + "秒", 100, 310, p);

					Thread.sleep(1000);// 睡眠时间为1秒

				} catch (Exception e) {
					e.printStackTrace();

				} finally {
					if (null != canvas) {
						mHolder.unlockCanvasAndPost(canvas);
					}
				}

			}

		}
	}

}
