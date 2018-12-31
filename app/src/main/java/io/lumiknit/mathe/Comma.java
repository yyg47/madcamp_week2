package io.lumiknit.mathe;

public class Comma extends Operator {
    public Comma(Expr[] exprs) {
        initializeOperands(exprs);
    }

    @Override
    public String toTex() {
        String c = "";
        if(operands.length > 0) {
            c = "{" + operands[0].toTex() + "}";
            for(int i = 1; i < operands.length; i++) {
                c += ",{";
                c += operands[i].toTex();
                c += "}";
            }
        }
        return "{" + c + "}";
    }
}
