package io.lumiknit.mathe;

public class Int extends RangeOperator {
    public Int(Expr expr) {
        super(expr);
    }

    public Int(Expr from, Expr to, Expr expr) {
        super(from, to, expr);
    }

    @Override
    public String toTex() {
        return toTexWith("int");
    }
}
