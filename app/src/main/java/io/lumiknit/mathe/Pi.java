package io.lumiknit.mathe;

public class Pi extends Constant {
    @Override
    public String toTex() {
        return "{\\pi}";
    }

    @Override
    public String toString() {
        return "`pi`";
    }
}
