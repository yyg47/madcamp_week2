package io.lumiknit.mathe;

public class Equivalent extends BinStatement {
    public Equivalent(Expr lhs, Expr rhs) {
        super(lhs, rhs);
    }

    @Override
    public String toTex() {
        return lhs.toTex() + " \\equiv " + rhs.toTex();
    }
}
