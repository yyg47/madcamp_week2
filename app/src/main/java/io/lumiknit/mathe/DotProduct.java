package io.lumiknit.mathe;

public class DotProduct extends BinOperator {
    public DotProduct() {
    }

    public DotProduct(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public String toTex() {
        if(existsNullOperand()) return null;
        return "{{" + getOperand(0).toTex() + "} \\cdot {" + getOperand(1).toTex() + "}}";
    }
}
