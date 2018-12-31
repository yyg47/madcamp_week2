package io.lumiknit.mathe;

public abstract class Constant extends Expr {
    public boolean equals(Object other) {
        return getClass().equals(other.getClass());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
