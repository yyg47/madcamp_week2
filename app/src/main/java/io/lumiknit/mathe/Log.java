package io.lumiknit.mathe;

public class Log extends BinOperator {
    public Log() {
    }

    public Log(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public String toTex() {
        String sub = "";
        if(getOperand(0) != null) sub = "_{" + getOperand(0).toTex() + "}";
        return "{\\log" + sub + "{" + getOperand(1).toTex() + "}}";
    }
}
