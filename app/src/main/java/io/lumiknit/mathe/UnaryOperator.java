package io.lumiknit.mathe;

public abstract class UnaryOperator extends Operator {
    public UnaryOperator() {
        initializeOperands();
    }

    public UnaryOperator(Expr expr) {
        initializeOperands(expr);
    }


    protected void initializeOperands() {
        this.initializeOperands((Expr)null);
    }

    protected void initializeOperands(Expr expr) {
        super.initializeOperands(1);
        setOperand(expr);
    }

    public Expr getOperand() {
        return getOperand(0);
    }

    public void setOperand(Expr expr) {
        setOperand(0, expr);
    }
}
