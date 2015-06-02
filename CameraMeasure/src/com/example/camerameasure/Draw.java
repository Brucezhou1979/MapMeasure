package com.example.camerameasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.ImageView;

/*定义一个画矩形框的类*/
public class Draw extends ImageView {

	public Draw(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	Paint paint = new Paint();
	{
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(3.5f);// 设置线宽
		paint.setAlpha(250);
	};

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, 10,
				paint);
		// canvas.drawRect(new Rect(100, 200, 400, 500), paint);// 绘制矩形

	}
}