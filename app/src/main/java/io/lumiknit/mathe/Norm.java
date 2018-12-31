package io.lumiknit.mathe;

public class Norm extends UnaryOperator {
    public Norm(Expr expr) {
        super(expr);
    }

    public Norm() {
    }

    @Override
    public String toTex() {
        return "{\\lVert{" + getOperand().toTex() + "}\\rVert}";
    }
}
