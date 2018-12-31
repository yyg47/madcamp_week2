package io.lumiknit.mathe;

public class Products extends RangeOperator {
    public Products(Expr expr) {
        super(expr);
    }

    public Products(Expr from, Expr to, Expr expr) {
        super(from, to, expr);
    }

    @Override
    public String toTex() {
        return toTexWith("prod");
    }
}
