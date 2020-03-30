package com.example.surfaceviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    class DrawThread extends Thread {
        float x = 300, y = 300;
        Random r = new Random();
        Paint p = new Paint();
        boolean runFlag = true;
        public DrawThread(SurfaceHolder holder) {
            this.holder = holder;
        }

        SurfaceHolder holder;
        @Override
        public void run() {
            super.run();
            p.setColor(Color.YELLOW);

            while (runFlag) {
                Canvas c = holder.lockCanvas();
                if (c != null) {
                    c.drawColor(Color.RED);
                    x += r.nextFloat() * 10 - 5;
                    y += r.nextFloat() * 10 - 5;
                    c.drawCircle(x,y,30,p);
                    holder.unlockCanvasAndPost(c);
                    try {
                    Thread.sleep(100); }
                    catch (InterruptedException e) {}
                }
            }

        }
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        DrawThread thread = new DrawThread(holder);
        thread.start();
        Log.d("mytag", "DrawThread is running");

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
