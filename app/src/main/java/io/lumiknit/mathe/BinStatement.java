package io.lumiknit.mathe;

public abstract class BinStatement extends Statement {
    public Expr lhs, rhs;

    public BinStatement(Expr lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
