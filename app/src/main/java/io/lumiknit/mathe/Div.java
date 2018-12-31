package io.lumiknit.mathe;

public class Div extends BinOperator {
    public Div() {
    }

    public Div(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public String toTex() {
        if(existsNullOperand()) return null;
        return "{" + getOperand(0).toTex() + "} \\div {" + getOperand(1).toTex() + "}";
    }
}
