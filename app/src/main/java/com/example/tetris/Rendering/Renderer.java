package com.example.tetris.Rendering;

import android.app.Activity;
import android.widget.GridView;

import com.example.tetris.Game.Field;


public class Renderer {
    private GridView gridView;
    public Renderer(GridView gridView) {
        this.gridView = gridView;
    }

    public void gridViewRender(Field field) {
        int[][] colorArray = getColorArray(field);
        ((Activity) gridView.getContext()).runOnUiThread(() -> {
            // Calculate the size of each grid cell
            int cellSize = gridView.getWidth() / colorArray[0].length;

            // Set the column width of the GridView to match the cell size
            gridView.setColumnWidth(cellSize);
            // Create an adapter to populate the GridView
            ColorAdapter colorAdapter = new ColorAdapter(gridView.getContext(), colorArray, cellSize);

            // Set the adapter for the GridView
            gridView.setAdapter(colorAdapter);
        });
    }


    private int[][] getColorArray(Field field) {
        int[][] newArray = new int[field.renderedFieldArray.length][field.renderedFieldArray[0].length];
        for (int row = 0; row < field.renderedFieldArray.length; row++) {
            for (int column = 0; column < field.renderedFieldArray[0].length; column++) {
                if (field.renderedFieldArray[row][column] == null) {
                    newArray[row][column] = 0;
                } else {
                    newArray[row][column] = field.renderedFieldArray[row][column].getColor();
                }
            }
        }
        return newArray;
    }

}
