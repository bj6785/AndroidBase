package com.verifone.androidbase.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceViewTestActivity extends AppCompatActivity {
    MySurfaceView mMySurfaceView;
    SurfaceHolder mSurfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMySurfaceView = new MySurfaceView(this);
        setContentView(mMySurfaceView);
    }

    class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
        public MySurfaceView(Context context) {
            super(context);
            SurfaceHolder surfaceHolder = this.getHolder();
            surfaceHolder.addCallback(this);
        }

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            Log.i("TAG", "surfaceCreated: " + Thread.currentThread().getName());

        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
            // 调用画图线程
            Log.i("TAG", "surfaceChanged: " + Thread.currentThread().getName());
            Log.i("TAG", "width:" + width + " height:" + height);
            Thread myThread = new Thread(new myRunable(surfaceHolder));
            myThread.start();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            Log.i("TAG", "surfaceDestroyed: " + Thread.currentThread().getName());
        }
    }

    class myRunable implements Runnable {

        private SurfaceHolder mHolder = null;
        private boolean isRun;

        public myRunable(SurfaceHolder holder) {
            mHolder = holder;
            isRun = true;
        }

        @Override
        public void run() {
            Log.d("TAG", "myRunable: " + Thread.currentThread().getName());
            int count = 0;
            Canvas canvas;
            while (isRun) {
                if (count > 10) {
                    Log.d("TAG", "退出绘制线程");
                    break;
                }
                synchronized (mHolder) {
                    canvas = mHolder.lockCanvas();
                    canvas.drawColor(Color.WHITE);
                    Paint p = new Paint(); //创建画笔
                    p.setColor(Color.BLACK);
                    p.setTextSize(100);
                    Log.d("TAG", "这是第" + count + "秒");
                    canvas.drawText("这是第" + (count++) + "秒", 0, 400, p);

                    Rect rect = new Rect(100, 50, 520, 250);
                    canvas.drawRect(rect, p);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (canvas != null) {
                            mHolder.unlockCanvasAndPost(canvas);//结束锁定画图，并提交改变。
                        }
                    }
                }
            }
        }
    }
}
