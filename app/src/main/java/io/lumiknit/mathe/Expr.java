package io.lumiknit.mathe;

public abstract class Expr implements Texable, Cloneable {
    public Expr simplify(Context context) {
        return this;
    }
    public Expr replace(Context context) {
        Expr e = context.tryReplace(this);
        if(e != null) return e;
        return this;
    }
}
