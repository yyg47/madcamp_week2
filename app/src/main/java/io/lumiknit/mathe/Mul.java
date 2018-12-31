package io.lumiknit.mathe;

public class Mul extends BinOperator {
    public Mul() {
    }

    public Mul(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public String toTex() {
        if(existsNullOperand()) return null;
        return "{" + getOperand(0).toTex() + "} {" + getOperand(1).toTex() + "}";
    }
}
