package com.cmykui.framework.cmykui.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


import android.view.View;

public class LineChart extends View {
    private Paint paint;
    private int width, height, padding;
    private boolean isInit = false;

    public float[] Data = new float[7];

    public LineChart(Context context) {
        super(context);
    }

    public LineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineChart(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init() {
        paint = new Paint();

        width = getWidth();
        height = getHeight();
        padding = 20;

        isInit = true;

        Data[0] = 15.5f;
        Data[1] = 20.5f;
        Data[2] = 3.5f;
        Data[3] = 8.5f;
        Data[4] = 25.5f;
        Data[5] = 8.5f;
        Data[6] = 25.5f;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(!isInit){
            init();
        }

        canvas.drawColor(Color.BLACK);

        drawOsi(canvas);
        drawLine(canvas);
    }

    private void drawLine(Canvas canvas) {
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        float startX = padding;
        float startY = height - padding;
        int maxWidth = (width-padding*2) / Data.length - (padding);
        int currentX = padding;
        float broj = ((height - padding) / maxVisina());

        for(int i = 0; i < Data.length; i++) {

            float currentY = 0;

            if(Data[i] == maxVisina()) {
                currentY = padding;
            }
            else {
                currentY = (maxVisina() - Data[i]) * broj;
            }

            canvas.drawLine(startX, startY, currentX , currentY, paint);
            startX = currentX;
            startY = currentY;
            currentX += padding + maxWidth;
        }
    }

    private void drawOsi(Canvas canvas) {

        //Postavljam sve za boju u te drekove
        paint.reset();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        // Racunam gdje kako sta
        float startX = padding; // neka krene malo u lijevo
        float startY = height - padding; //neka krene malo manje od kraja
        float endX = width - padding;
        float endY = padding;

        canvas.drawLine(startX, startY, endX, startY, paint);
        canvas.drawLine(startX, startY, startX, endY, paint);

    }

    private float maxVisina(){
        // Izracun
        float max = -999;

        for(int i = 0; i < Data.length; i++) {
            if(max < Data[i]){
                max = Data[i];
            }
        }
        return max;
    }
    
    
}