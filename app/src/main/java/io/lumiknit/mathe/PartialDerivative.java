package io.lumiknit.mathe;

public class PartialDerivative extends Derivative {
    public PartialDerivative(int n, Expr base, Expr expr) {
        super(n, base, expr);
    }

    @Override
    public String toTex() {
        if(existsNullOperand()) return null;
        if(n == 1) {
            return "{\\frac{\\partial}{\\partial" + getOperand(0).toTex() + "} + {" + getOperand(1).toTex() + "}";
        } else {
            return "{\\frac{\\partial^{" + n + "}}{\\partial" + getOperand(0).toTex() + "^{" + n + "}} + {" + getOperand(1).toTex() + "}";
        }
    }
}
