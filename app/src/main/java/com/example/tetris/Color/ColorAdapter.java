package com.example.tetris.Color;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ColorAdapter extends BaseAdapter {
    private Context context;
    private int[][] colorArray;
    private int cellSize;
    private int yOffset;

    public ColorAdapter(Context context, int[][] colorArray, int cellSize) {
        this.context = context;
        this.colorArray = colorArray;
        this.cellSize = cellSize;
        this.yOffset = 21; // Number of rows to hide at the top
    }

    @Override
    public int getCount() {
        return (colorArray.length - yOffset) * colorArray[0].length;
    }

    @Override
    public Object getItem(int position) {
        int visibleRow = (position / colorArray[0].length) + yOffset;
        int col = position % colorArray[0].length;
        return colorArray[visibleRow][col];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(cellSize, cellSize));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        int visibleRow = position / colorArray[0].length;
        int row = visibleRow + yOffset;
        int col = position % colorArray[0].length;

        int color = colorArray[row][col];
        imageView.setBackgroundColor(color);

        return imageView;
    }
}