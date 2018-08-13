package com.verifone.androidbase.ui.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.blankj.ALog;


public class MyCustomView extends View {
    private int width, height;

    public MyCustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        ALog.d("onDraw...");
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        Rect rect = new Rect(100, 100, width - 100, height - 100);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(rect, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        ALog.d("onSizeChanged...");
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        ALog.i("(" + oldw + ", " + oldh + ")" + "=> (" + w + ", " + h + ")");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        ALog.d("onMeasure...");
        ALog.i("(" + MeasureSpec.getSize(widthMeasureSpec )+ ", " + MeasureSpec.getSize(heightMeasureSpec) + ")");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(300, 500); //修改view的大小
    }

    @Override
    protected void onFinishInflate() {
        ALog.d("onFinishInflate...");
        super.onFinishInflate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        ALog.d("onLayout...");
        super.onLayout(changed, left, top, right, bottom);
    }
}
