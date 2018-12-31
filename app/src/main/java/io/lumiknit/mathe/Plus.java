package io.lumiknit.mathe;

public class Plus extends UnaryOperator {
    @Override
    public String toTex() {
        if(existsNullOperand()) return null;
        return "{+ {" + getOperand(0).toTex() + "}}";
    }

    public Plus() {
    }

    public Plus(Expr expr) {
        super(expr);
    }
}
