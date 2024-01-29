package com.example.tetris.Rendering;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

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

        int viewWidth = getWidth();
        int viewHeight = getHeight();
        float contentWidth = cellSize * colorArray[0].length;
        float contentHeight = cellSize * colorArray.length;
        //ViewGroup parent = (ViewGroup) getParent();
        int parentLeft = ((View) getParent()).getLeft();
        int parentTop = ((View) getParent()).getTop();
        float translationX = parentLeft;
        float translationY = parentTop;


        for (int i = 0; i < colorArray.length; i++) {
            for (int j = 0; j < colorArray[i].length; j++) {
                int color = colorArray[i][j];
                cellPaint.setColor(color);
                float left = j * cellSize + translationX;
                float top = i * cellSize + translationY;
                float right = left + cellSize;
                float bottom = top + cellSize;
                canvas.drawRect(left, top, right, bottom, cellPaint);
            }
        }
    }

}