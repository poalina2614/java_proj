package com.example.newyeardinner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameView extends View {
    private int viewWidth;
    private int viewHeight;
    private int points = 0;
    private List<Bitmap> bitmaps;
    private Sprite playerBird;
    private final int timerInterval = 30;
    public GameView(Context context) {
        super(context);
        this.bitmaps = new ArrayList<Bitmap>();
        this.bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.ikra));
        this.bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.kola));
        this.bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.mandarin));
        this.bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.olivie));
        this.bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.pryanik));
        this.bitmaps.add(BitmapFactory.decodeResource(getResources(), R.drawable.tortik));
        Collections.shuffle(this.bitmaps, new Random());
        playerBird = new Sprite(10, -280, 0, 400, this.bitmaps.get(0));
        Timer t = new Timer();
        t.start();
    }
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
        canvas.drawText(points+"", viewWidth - 100, 70, p);
        playerBird.draw(canvas);
    }
    protected void update () {
        playerBird.update(timerInterval);
        invalidate();
        if (playerBird.getY() > viewHeight) {
            teleportEnemy ();
            points +=10;
        }
    }
    private void teleportEnemy () {
        playerBird.setX(Math.random() * (viewWidth - playerBird.getBx()));
        playerBird.setY(-280);
        Collections.shuffle(this.bitmaps, new Random());
        playerBird.setB(this.bitmaps.get(0));
    }
}
