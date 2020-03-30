package com.example.surfaceviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /*
    Этапы:
    - создать класс на основе SurfaceView
    - реализовать интерфейс обратного вызова
    - добавить поток для отрисовки DrawThread
    - реализовать запуск и остановку потока
    - в потоке получать доступ к канве (lock) и после работы с ней
      освобождать (unlock)

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
