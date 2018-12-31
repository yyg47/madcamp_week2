package io.lumiknit.mathe;

public class Add extends BinOperator {
    public Add() {
    }

    public Add(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public String toTex() {
        if(existsNullOperand()) return null;
        return "{" + getOperand(0).toTex() + "} + {" + getOperand(1).toTex() + "}";
    }
}
