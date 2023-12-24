package com.example.newyeardinner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameView extends View {
    private int viewWidth;
    private int viewHeight;
    private int points = 0;
    private List<Bitmap> bitmaps;
    private Sprite food;
    private final int timerInterval = 30;
    private Sprite cat;
    private Bitmap elka;
    private Bitmap kota;
    public GameView(Context context) {
        super(context);
        kota = BitmapFactory.decodeResource(getResources(), R.drawable.kotik);
        this.bitmaps = new ArrayList<Bitmap>();
        this.bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.mini_ikra));
        this.bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.mini_kola));
        this.bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.mini_mandarin));
        this.bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.mini_olivie));
        this.bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.mini_pryanik));
        this.bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.mini_tortik));
        Collections.shuffle(this.bitmaps, new Random());
        elka = BitmapFactory.decodeResource(getResources(), R.drawable.elka);
        food = new Sprite(10, -280, 0, 600, this.bitmaps.get(0));
        cat = new Sprite(30, 900, 0, 0, kota);
        Timer t = new Timer();
        t.start();
    }
//    public void сreateDialog(Activity activity) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        builder.setTitle("Диалог")
//                .setMessage("Текст в диалоге")
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        Toast.makeText(activity,"Нажата кнопка 'OK'",Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        Toast.makeText(activity,"Нажата кнопка 'Отмена'",Toast.LENGTH_SHORT).show();
//                    }
//                });
//        builder.create().show();
//    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
    }
    class Timer extends CountDownTimer {
        public Timer() {
            super(Integer.MAX_VALUE, timerInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            update();
        }
        @Override
        public void onFinish() {
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setTextSize(55.0f);
        p.setColor(Color.BLACK);
        Rect gran = new Rect(0, 0, (int)getWidth(), (int)getHeight());
        canvas.drawBitmap(elka, null, gran, p);
        canvas.drawText(points+"", viewWidth - 100, 70, p);
        cat.draw(canvas);
        food.draw(canvas);
    }
    protected void update () {
        food.update(timerInterval);
        invalidate();
        if (food.getY() > viewHeight) {
            teleportEnemy ();
            points -=50;
        }
        if (food.intersect(cat)) {
            teleportEnemy ();
            points += 10;
        }
    }
    private void teleportEnemy () {
        food.setX(Math.random() * (viewWidth - food.getBx()));
        food.setY(-280);
        Collections.shuffle(this.bitmaps, new Random());
        food.setB(this.bitmaps.get(0));
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        cat.setX(event.getX() - 80);
        return true;
    }
}
