package io.lumiknit.mathe;

public class MinusPlus extends UnaryOperator {
    public MinusPlus() {
    }

    public MinusPlus(Expr expr) {
        super(expr);
    }

    @Override
    public String toTex() {
        if(existsNullOperand()) return null;
        return "{\\pm {" + getOperand(0).toTex() + "}}";
    }
}
