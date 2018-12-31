package io.lumiknit.mathe;

public class Lim extends Operator {
    public Lim(Expr which, Expr to, Expr expr) {
        initializeOperands(3);
        setOperand(0, which);
        setOperand(1, to);
        setOperand(2, expr);
    }
    @Override
    public String toTex() {
        return "{\\lim_{{" + getOperand(0).toTex() + "}\\to{" + getOperand(1).toTex() + "}}{" + getOperand(2).toTex() + "}}";
    }
}
