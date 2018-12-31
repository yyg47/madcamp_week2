package io.lumiknit.mathe;

public class PlusMinus extends UnaryOperator {
    public PlusMinus() {
    }

    public PlusMinus(Expr expr) {
        super(expr);
    }

    @Override
    public String toTex() {
        if (existsNullOperand()) return null;
        return "{\\pm {" + getOperand(1).toTex() + "}}";
    }
}
