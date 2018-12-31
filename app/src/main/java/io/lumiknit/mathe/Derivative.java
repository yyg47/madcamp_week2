package io.lumiknit.mathe;

public class Derivative extends Operator {
    int n;

    public Derivative(int n, Expr base, Expr expr) {
        initializeOperands(2);
        this.n = n;
        setOperand(0, base);
        setOperand(1, expr);
    }

    @Override
    public String toTex() {
        if(existsNullOperand()) return null;
        if(n == 1) {
            return "{\\frac{d}{d" + getOperand(0).toTex() + "} + {" + getOperand(1).toTex() + "}";
        } else {
            return "{\\frac{d^{" + n + "}}{d" + getOperand(0).toTex() + "^{" + n + "}} + {" + getOperand(1).toTex() + "}";
        }
    }
}
