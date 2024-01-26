package com.example.tetris.Game;

import android.graphics.drawable.ColorDrawable;
import android.widget.TableLayout;

import java.util.Objects;

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

    void tableLayoutRender(Field field, TableLayout tableLayout) {

    }

    private ColorDrawable[][] getColorArray() {
        ColorDrawable[][] newArray;
        return null;
    }
}
