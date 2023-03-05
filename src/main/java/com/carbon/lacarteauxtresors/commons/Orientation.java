package com.carbon.lacarteauxtresors.commons;

public enum Orientation {
    N,
    E,
    S,
    O;

    public Orientation turnLeft() {
        return switch (this) {
            case N -> O;
            case E -> N;
            case S -> E;
            case O -> S;
        };
    }

    public Orientation turnRight() {
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> O;
            case O -> N;
        };
    }
}
