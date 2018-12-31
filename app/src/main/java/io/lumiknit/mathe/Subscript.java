package io.lumiknit.mathe;

public class Subscript extends BinOperator {
    public Subscript() {
    }

    public Subscript(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public String toTex() {
        if(existsNullOperand()) return null;
        return "{{" + getOperand(0).toTex() + "} _ {" + getOperand(1).toTex() + "}}";
    }
}
