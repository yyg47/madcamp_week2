package io.lumiknit.mathe;

public class Minus extends UnaryOperator {
    public Minus(Expr expr) {
        super(expr);
    }

    public Minus() {
    }

    @Override
    public String toTex() {
        if(existsNullOperand()) return null;
        return "{- {" + getOperand(0).toTex() + "}}";
    }
}
