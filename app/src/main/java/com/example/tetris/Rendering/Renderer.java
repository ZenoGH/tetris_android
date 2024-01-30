package com.example.tetris.Rendering;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import com.example.tetris.TetrisShape.TetrisShapePiece;


public class Renderer {
    private View view;
    private Context context;
    private ColorArrayView colorArrayView;
    public Renderer(View view) {
        this.view = view;
        context = view.getContext();
    }

    private void prepareColorArrayView() {
        colorArrayView = new ColorArrayView(context);
        ((Activity) context).runOnUiThread(() -> {
            ((ViewGroup) view).addView(colorArrayView);
        });
    }
    public void renderTetrisPieceArray(TetrisShapePiece[][] pieceArray, float cellSize) {
        if (colorArrayView == null) {
            prepareColorArrayView();
        }
        int[][] colorArray = convertToColorArray(pieceArray);
        ((Activity) context).runOnUiThread(() -> {
            colorArrayView.setColorArray(colorArray, cellSize);
        });
    }


    private int[][] convertToColorArray(TetrisShapePiece[][] pieceArray) {
        int[][] newArray =
                new int[pieceArray.length][pieceArray[0].length];
        for (int row = 0; row < pieceArray.length; row++) {
            for (int column = 0; column < pieceArray[0].length; column++) {
                if (pieceArray[row][column] == null) {
                    newArray[row][column] = Color.DKGRAY;
                } else {
                    newArray[row][column] = pieceArray[row][column].getColor();
                }
            }
        }
        return newArray;
    }

    public View getView() {
        return view;
    }
}
