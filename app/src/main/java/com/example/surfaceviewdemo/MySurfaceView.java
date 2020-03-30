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
        // в конструкторе нужно передать holder для дальнейшего доступа к канве
        public DrawThread(SurfaceHolder holder) {
            this.holder = holder;
        }

        SurfaceHolder holder;
        @Override
        public void run() {
            super.run();
            p.setColor(Color.YELLOW);

            // выполняем цикл (рисуем кадры) пока флаг включен
            while (runFlag) {
                Canvas c = holder.lockCanvas();
                // если успешно захватили канву
                if (c != null) {
                    c.drawColor(Color.RED);

                    // случайные блуждания - сдвигаем координаты шарика в случайную сторону
                    x += r.nextFloat() * 10 - 5;
                    y += r.nextFloat() * 10 - 5;
                    c.drawCircle(x,y,30,p);
                    holder.unlockCanvasAndPost(c);

                    // нужна пауза на каждом кадре
                    try {
                    Thread.sleep(100); }
                    catch (InterruptedException e) {}
                }
            }

        }
    }
    DrawThread thread;

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new DrawThread(holder);
        thread.start();
        // убеждаемся, что поток запускается
        Log.d("mytag", "DrawThread is running");

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // при изменении конфигурации поверхности поток нужно перезапустить
        thread.runFlag = false;
        thread = new DrawThread(holder);
        thread.start();
    }

    // поверхность уничтожается - поток останавливаем
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.runFlag = false;
    }
}
