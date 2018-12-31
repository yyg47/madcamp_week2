package io.lumiknit.mathp;

import io.lumiknit.mathe.*;
import io.lumiknit.mathe.Number;

public class IntElemProblem extends Problem {
    public IntElemProblem(Range[] ranges) {
        super(ranges);
    }

    public Expr plusC(Expr e) {
        return new Add(e, new Roman('C'));
    }

    public Expr integral(Expr from, Expr to, Expr expr, Expr about) {
        return new Int(from, to, new Mul(expr, new Mul(new Roman('d'), about)));
    }

    public Expr round(Expr e) {
        return new Paren(Paren.TYPE_ROUND, e);
    }

    @Override
    public Set generate() {
        Expr pExpr = null;
        Texable ans[] = new Texable[4];

        Expr x = new Roman('x');
        Expr t = new Roman('t');
        Expr zero = new Number(0);
        Expr one = new Number(1);
        Expr two = new Number(2);
        Expr three = new Number(3);
        Expr inf = new Inf();
        Expr pi = new Pi();
        Expr e = new E();

        int n = (int)Range.pickFrom(0, 13);
        switch(n) {
            case 0: {
                Expr f = new Frac(new Mul(new Text("sin"), x), x);
                pExpr = integral(new Minus(inf), inf, f, x);
                ans[0] = pi;
                ans[1] = one;
                ans[2] = new Frac(pi, new Number(2));
                ans[3] = new Mul(new Number(2), pi);
            } break;
            case 1: {
                Expr f = new Frac(new Mul(new Text("sin"), x), x);
                pExpr = integral(new Number(0), new Inf(), f, x);
                ans[0] = new Frac(pi, new Number(2));
                ans[1] = pi;
                ans[2] = one;
                ans[3] = new Mul(new Number(2), pi);
            } break;
            case 2: {
                pExpr = integral(one, x, new Frac(one, t), t);
                ans[0] = new Log(null, x);
                ans[1] = one;
                ans[2] = new Minus(new Frac(one, new Superscript(x, new Number(2))));
                ans[3] = new Superscript(e, new Minus(x));
            } break;
            case 3: {
                pExpr = integral(one, x, new Frac(one, new Superscript(t, new Number(2))), t);
                ans[0] = new Sub(one, new Frac(one, x));
                ans[1] = new Frac(one, x);
                ans[2] = new Minus(new Frac(one, x));
                ans[3] = new Minus(new Frac(new Number(2), new Superscript(x, new Number(3))));
            } break;
            case 4: {
                pExpr = integral(null, null, new Frac(one, new Add(one, x)), x);
                ans[0] = plusC(new Log(null, round(new Add(one, x))));
                ans[1] = plusC(new Log(null, x));
                ans[2] = plusC(new Frac(one, new Superscript(x, new Number(2))));
                ans[3] = plusC(new Mul(new Superscript(new Text("tan"), new Number(-1)), x));
            } break;
            case 5: {
                pExpr = integral(null, null, new Frac(one, new Add(one, new Superscript(x, two))), x);
                ans[0] = plusC(new Mul(new Superscript(new Text("tan"), new Number(-1)), x));
                ans[1] = plusC(new Log(null, round(new Add(one, x))));
                ans[2] = plusC(new Frac(one, new Superscript(x, new Number(2))));
                ans[3] = plusC(new Log(null, round(new Sub(one, x))));
            } break;
            case 6: {
                pExpr = integral(null, null, new Frac(new Superscript(x, two), new Add(one, new Superscript(x, two))), x);
                ans[0] = plusC(new Sub(x, new Mul(new Superscript(new Text("tan"), new Number(-1)), x)));
                ans[1] = plusC(new Mul(new Superscript(new Text("tan"), new Number(-1)), x));
                ans[2] = plusC(new Frac(one, new Superscript(x, new Number(2))));
                ans[3] = plusC(new Mul(new Frac(one, two), new Log(null, round(new Frac(new Add(one, x), new Sub(one, x))))));
            } break;
            case 7: {
                pExpr = integral(null, null, new Frac(one, new Sub(one, new Superscript(x, two))), x);
                ans[0] = plusC(new Mul(new Frac(one, two), new Log(null, new Frac(new Add(one, x), new Sub(one, x)))));
                ans[1] = plusC(new Mul(new Superscript(new Text("tan"), new Number(-1)), x));
                ans[2] = plusC(new Minus(new Log(null, round(new Sub(one, x)))));
                ans[3] = plusC(new Log(null, round(new Sub(one, x))));
            } break;
            case 8: {
                pExpr = integral(null, null, new Frac(x, new Sub(one, new Superscript(x, two))), x);
                ans[0] = plusC(new Mul(new Minus(new Frac(one, two)), new Log(null, round(new Sub(one, new Superscript(x, two))))));
                ans[1] = plusC(new Mul(new Frac(one, two), new Log(null, new Frac(new Add(one, x), new Sub(one, x)))));
                ans[2] = plusC(new Minus(new Log(null, round(new Sub(one, x)))));
                ans[3] = plusC(new Mul(new Superscript(new Text("tan"), new Number(-1)), x));
            } break;
            case 9: {
                pExpr = integral(null, null, new Frac(x, new Add(one, new Add(new Superscript(x, two), new Superscript(x, new Number(4))))), x);
                ans[0] = plusC(new Minus(new Frac(one, new Mul(two, round(new Add(one, new Superscript(x, two)))))));
                ans[1] = plusC(new Mul(new Frac(one, two), new Log(null, new Frac(new Add(one, x), new Sub(one, x)))));
                ans[2] = plusC(new Mul(two, new Log(null, round(new Add(one, new Superscript(x, two))))));
                ans[3] = plusC(new Mul(new Superscript(new Text("tan"), new Number(-1)), new Add(one, new Superscript(x, two))));
            } break;
            case 10: {
                pExpr = integral(null, null, new Frac(one, new Sqrt(new Add(one, new Superscript(x, two)))), x);
                ans[0] = plusC(new Mul(new Superscript(new Text("sinh"), new Number(-1)), x));
                ans[1] = plusC(new Mul(new Superscript(new Text("tanh"), new Number(-1)), x));
                ans[2] = plusC(new Sqrt(new Add(one, new Superscript(x, two))));
                ans[3] = plusC(new Mul(new Superscript(new Text("tan"), new Number(-1)), new Add(one, new Superscript(x, two))));
            } break;
            case 11: {
                pExpr = integral(null, null, new Frac(one, new Sqrt(new Sub(one, new Superscript(x, two)))), x);
                ans[0] = plusC(new Mul(new Superscript(new Text("sin"), new Number(-1)), x));
                ans[1] = plusC(new Mul(new Superscript(new Text("sinh"), new Number(-1)), x));
                ans[2] = plusC(new Mul(new Superscript(new Text("tanh"), new Number(-1)), x));
                ans[3] = plusC(new Mul(new Superscript(new Text("tan"), new Number(-1)), new Add(one, new Superscript(x, two))));
            } break;
            case 12: {
                pExpr = integral(null, null, new Frac(x, new Sqrt(new Add(one, new Superscript(x, two)))), x);
                ans[0] = plusC(new Sqrt(new Add(one, new Superscript(x, two))));
                ans[1] = plusC(new Mul(new Superscript(new Text("sinh"), new Number(-1)), x));
                ans[2] = plusC(new Mul(new Frac(one, two), new Log(null, round(new Add(one, new Superscript(x, two))))));
                ans[3] = plusC(new Mul(new Superscript(new Text("tan"), new Number(-1)), new Add(one, new Superscript(x, two))));
            } break;
        }
        Texable problem = new Equal(pExpr, new Question());
        return new Set(problem, ans);
    }
}
