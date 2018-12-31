package io.lumiknit.mathe;

public class Sub extends BinOperator {
    public Sub() {
    }

    public Sub(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public String toTex() {
        if(existsNullOperand()) return null;
        return "{" + getOperand(0).toTex() + "} - {" + getOperand(1).toTex() + "}";
    }
}
