package io.lumiknit.mathe;

public abstract class BinOperator extends Operator {
    public BinOperator() {
        initializeOperands();
    }

    public BinOperator(Expr l, Expr r) {
        initializeOperands(l, r);
    }

    protected void initializeOperands() {
        initializeOperands(null, null);
    }

    protected void initializeOperands(Expr a, Expr b) {
        super.initializeOperands(2);
        setOperand(0, a);
        setOperand(1, b);
    }
}
