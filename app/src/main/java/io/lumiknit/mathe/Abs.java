package io.lumiknit.mathe;

public class Abs extends UnaryOperator {
    public Abs(Expr expr) {
        super(expr);
    }

    public Abs() {
    }

    @Override
    public String toTex() {
        return "{\\lvert{" + getOperand().toTex() + "}\\rvert}";
    }
}
