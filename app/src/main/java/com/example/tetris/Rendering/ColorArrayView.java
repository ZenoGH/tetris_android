package com.example.tetris.Rendering;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class ColorArrayView extends View {

    private int[][] colorArray;
    private float cellSize;
    private Paint cellPaint;

    public ColorArrayView(Context context) {
        super(context);
        cellPaint = new Paint();
        cellPaint.setStyle(Paint.Style.FILL);
    }

    public void setColorArray(int[][] array, float cellSize) {
        colorArray = array;
        this.cellSize = cellSize;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (colorArray == null) {
            return;
        }
        super.onDraw(canvas);


        for (int i = 0; i < colorArray.length; i++) {
            for (int j = 0; j < colorArray[i].length; j++) {
                int color = colorArray[i][j];
                cellPaint.setColor(color);
                float left = j * (cellSize + 1);
                float top = i * (cellSize + 1);
                float right = left + cellSize;
                float bottom = top + cellSize;
                canvas.drawRect(left, top, right, bottom, cellPaint);
            }
        }
    }

}