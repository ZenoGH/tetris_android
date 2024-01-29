package com.example.tetris.Rendering;

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


    public ColorAdapter(Context context, int[][] colorArray, int cellSize) {
        this.context = context;
        this.colorArray = colorArray;
        this.cellSize = cellSize;
    }

    @Override
    public int getCount() {
        return colorArray.length * colorArray[0].length;
    }

    @Override
    public Object getItem(int position) {
        int row = position % colorArray.length;
        int column = position % colorArray[0].length;
        return colorArray[row][column];
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
        imageView.setEnabled(false);
        int row = position / colorArray[0].length;
        int column = position % colorArray[0].length;

        int color = colorArray[row][column];
        imageView.setBackgroundColor(color);

        return imageView;
    }
}