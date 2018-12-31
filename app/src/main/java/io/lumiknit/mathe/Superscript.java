package io.lumiknit.mathe;

public class Superscript extends BinOperator {
    public Superscript() {
    }

    public Superscript(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public String toTex() {
        if(existsNullOperand()) return null;
        return "{{" + getOperand(0).toTex() + "} ^ {" + getOperand(1).toTex() + "}}";
    }
}
