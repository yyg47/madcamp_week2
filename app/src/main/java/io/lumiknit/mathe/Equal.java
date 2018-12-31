package io.lumiknit.mathe;

public class Equal extends BinStatement {
    public Equal(Expr lhs, Expr rhs) {
        super(lhs, rhs);
    }

    @Override
    public String toTex() {
        return lhs.toTex() + " = " + rhs.toTex();
    }
}
