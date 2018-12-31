package io.lumiknit.mathe;

public class Undefined extends Constant {
    public Undefined() {}

    @Override
    public String toTex() {
        return "\\text{Undefined}";
    }

    @Override
    public String toString() {
        return "Undefined";
    }
}
