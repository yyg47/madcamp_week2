package io.lumiknit.mathe;

public class Sqrt extends UnaryOperator {
    public Sqrt() {
    }

    public Sqrt(Expr expr) {
        super(expr);
    }

    @Override
    public String toTex() {
        return "{\\sqrt{" + getOperand().toTex() + "}}";
    }
}
