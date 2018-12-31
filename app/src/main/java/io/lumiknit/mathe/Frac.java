package io.lumiknit.mathe;

public class Frac extends BinOperator {
    @Override
    public String toTex() {
        if(existsNullOperand()) return null;
        return "{\\frac{" + getOperand(0).toTex() + "}{" + getOperand(1).toTex() + "}}";
    }

    public Frac() {
    }

    public Frac(Expr l, Expr r) {
        super(l, r);
    }
}
