package com.example.tetris.Game;

import android.app.Activity;
import android.widget.GridView;

import com.example.tetris.Color.ColorAdapter;

public class Renderer {
    void consoleRender(Field field) {
        System.out.println("======================");
        for (int row = 0; row < field.size; row++) {
            for (int column = 0; column < field.size; column++) {
                if (field.simulationField[row][column] != null) {
                    System.out.print("@");
                } else {
                    System.out.print("_");
                }
            }
            System.out.println();
        }
    }

    void gridViewRender(Field field, GridView gridView) {
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
        int[][] newArray = new int[field.simulationField.length][field.simulationField[0].length];
        for (int row = 0; row < field.simulationField.length; row++) {
            for (int column = 0; column < field.simulationField[0].length; column++) {
                if (field.simulationField[row][column] == null) {
                    newArray[row][column] = 0;
                } else {
                    newArray[row][column] = field.simulationField[row][column].getColor();
                }
            }
        }
        return newArray;
    }

}
