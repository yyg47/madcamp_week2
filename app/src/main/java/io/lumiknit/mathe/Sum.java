package io.lumiknit.mathe;

public class Sum extends RangeOperator {
    public Sum(Expr expr) {
        super(expr);
    }

    public Sum(Expr from, Expr to, Expr expr) {
        super(from, to, expr);
    }

    @Override
    public String toTex() {
        return toTexWith("sum");
    }
}
