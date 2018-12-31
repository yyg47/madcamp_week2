package io.lumiknit.mathe;

public class Times extends BinOperator {
    public Times() {
    }

    public Times(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public String toTex() {
        if(existsNullOperand()) return null;
        return "{" + getOperand(0).toTex() + "} \\times {" + getOperand(1).toTex() + "}";
    }
}
