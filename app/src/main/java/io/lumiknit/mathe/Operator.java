package io.lumiknit.mathe;

public abstract class Operator extends Expr {
    protected Expr[] operands;

    protected void initializeOperands(int n) {
        operands = new Expr[n];
        for(int i = 0; i < n; i++) {
            operands[i] = null;
        }
    }

    protected void initializeOperands(Expr[] exprs) {
        operands = new Expr[exprs.length];
        for(int i = 0; i < exprs.length; i++) {
            operands[i] = exprs[i];
        }
    }

    public int getTermNumber() {
        return operands.length;
    }

    public Expr getOperand(int idx) {
        return operands[idx];
    }

    public void setOperand(int idx, Expr expr) {
        operands[idx] = expr;
    }

    public boolean existsNullOperand() {
        for(Expr e : operands) {
            if(e == null) return true;
        }
        return false;
    }

    public boolean hasEquivalentOperands(Operator other) {
        if(operands.length != other.operands.length) return false;
        for(int i = 0; i < operands.length; i++) {
            if(!operands[i].equals(other.operands[i])) return false;
        }
        return true;
    }

    public boolean equals(Object other) {
        return getClass().equals(other.getClass()) && hasEquivalentOperands((Operator)other);
    }

    @Override
    public Expr replace(Context context) {
        Expr e = context.tryReplace(this);
        if(e != null) return e;
        try {
            Operator op = (Operator)clone();
            for(int i = 0; i < op.operands.length; i++) {
                if(op.operands[i] != null) op.operands[i] = op.operands[i].replace(context);
            }
            return op;
        } catch(CloneNotSupportedException err) {
            return null;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Operator one = (Operator)super.clone();
        one.operands = one.operands.clone();
        return one;
    }
}
