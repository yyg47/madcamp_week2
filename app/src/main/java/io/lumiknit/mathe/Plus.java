package io.lumiknit.mathe;

public class Plus extends UnaryOperator {
    @Override
    public String toTex() {
        if(existsNullOperand()) return null;
        return "{+ {" + getOperand(1).toTex() + "}}";
    }

    public Plus() {
    }

    public Plus(Expr expr) {
        super(expr);
    }
}
