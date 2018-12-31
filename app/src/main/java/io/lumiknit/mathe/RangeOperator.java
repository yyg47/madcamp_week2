package io.lumiknit.mathe;

public abstract class RangeOperator extends Operator {
    public RangeOperator(Expr expr) {
        this(null, null, expr);
    }

    public RangeOperator(Expr from, Expr to, Expr expr) {
        initializeOperands(3);
        setOperand(0, from);
        setOperand(1, to);
        setOperand(2, expr);
    }

    public String toTexWith(String name) {
        String f = getOperand(0) == null ? "" : "_{" + getOperand(0).toTex() + "}";
        String t = getOperand(1) == null ? "" : "^{" + getOperand(1).toTex() + "}";
        String e = "{" + getOperand(2).toTex() + "}";
        return "{\\" + name + f + t + e + "}";
    }
}
