package com.example.newyeardinner;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Sprite {
    private Bitmap bitmap;
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;
    private int padding;
    public Sprite (double x, double y, double velocityX, double velocityY, Bitmap bitmap){
        this.x=x;
        this.y=y;
        this.velocityX=velocityX;
        this.velocityY=velocityY;
        this.bitmap = bitmap;
        this.padding = 20;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getX(){
        return x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setB(Bitmap b) {
        this.bitmap = b;
    }
    public double getVx() {
        return velocityX;
    }
    public void setVx(double velocityX) {
        this.velocityX = velocityX;
    }
    public double getVy() {
        return velocityY;
    }
    public void setVy(double velocityY) {
        this.velocityY = velocityY;
    }
    public int getBx() {
        return bitmap.getWidth();
    }
    public int getBy() {
        return bitmap.getHeight();
    }

    public void update (int ms) {
        x = x + velocityX * ms/1000.0;
        y = y + velocityY * ms/1000.0;
    }
    public void draw (Canvas canvas) {
        Paint p = new Paint();
        Rect destination = new Rect((int)x, (int)y, (int)(x + bitmap.getWidth()), (int)(y + bitmap.getHeight()));
        canvas.drawBitmap(bitmap,null, destination,  p);
    }
    public Rect getBoundingBoxRect () {
        return new Rect((int)x+padding, (int)y+padding, (int)(x + bitmap.getWidth() - 2 * padding), (int)(y + bitmap.getHeight() - 2 * padding));
    }
    public boolean intersect (Sprite s) {
        return getBoundingBoxRect().intersect(s.getBoundingBoxRect());
    }
}
