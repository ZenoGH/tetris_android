package com.example.tetris.TetrisShape;

import java.util.Random;

public class TetrisShapeFactory {
    private static final Random RNG = new Random();

    public enum Type {
        lShape,
        oShape,
        iShape,
        tShape,
        zShape,
        sShape,
        jShape,
    }

    public AbstractTetrisShape createShape(Type type) {
        switch (type) {
            case lShape:
                return new TetrisLShape();
            case oShape:
                return new TetrisOShape();
            case iShape:
                return new TetrisIShape();
            case tShape:
                return new TetrisTShape();
            case zShape:
                return new TetrisZShape();
            case jShape:
                return new TetrisJShape();
            case sShape:
                return new TetrisSShape();
        }
        return null;
    }

    public AbstractTetrisShape createRandomShape() {
        Type[] types = Type.values();
        return createShape(types[RNG.nextInt(types.length)]);
    }
}
