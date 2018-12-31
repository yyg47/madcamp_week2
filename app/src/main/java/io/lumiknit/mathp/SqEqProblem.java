package io.lumiknit.mathp;

import io.lumiknit.mathe.Add;
import io.lumiknit.mathe.Comma;
import io.lumiknit.mathe.Equal;
import io.lumiknit.mathe.Expr;
import io.lumiknit.mathe.Minus;
import io.lumiknit.mathe.Mul;
import io.lumiknit.mathe.Number;
import io.lumiknit.mathe.Question;
import io.lumiknit.mathe.Roman;
import io.lumiknit.mathe.Statement;
import io.lumiknit.mathe.Statements;
import io.lumiknit.mathe.Sub;
import io.lumiknit.mathe.Superscript;
import io.lumiknit.mathe.Texable;

public class SqEqProblem extends Problem {
    public SqEqProblem(Range[] ranges) {
        super(ranges);
    }

    @Override
    public Set generate() {
        long x1 = ranges[0].pick();
        long x2 = ranges[0].pick();
        if(x1 > x2) {
            long t = x1;
            x1 = x2;
            x2 = t;
        }
        long a = ranges[1].pick();
        while(a == 0) a = ranges[1].pick();
        long b = ranges[2].pick();

        Expr x = new Roman('x');
        Expr lhs = new Superscript(x, new Number(2));
        if(a == -1) lhs = new Minus(lhs);
        else if(a != 1) lhs = new Mul(new Number(a), lhs);
        long w = -a * (x1 + x2);
        if(w == 1) lhs = new Add(lhs, x);
        else if(w == -1) lhs = new Sub(lhs, x);
        else if(w > 0) lhs = new Add(lhs, new Mul(new Number(w), x));
        else if(w < 0) lhs = new Sub(lhs, new Mul(new Number(-w), x));

        long q = a * x1 * x2 + b;
        if(q > 0) lhs = new Add(lhs, new Number(q));
        else if(q < 0) lhs = new Sub(lhs, new Number(-q));

        Statement eq = new Equal(lhs, new Number(b));
        Texable p = new Statements(new Statement[]{eq, new Equal(new Roman('x'), new Question())});

        long[] d = new long[]{x1, x2, 0, 0, 0, 0, 0, 0};
        for(int i = 1; i < d.length / 2; i++) {
            d[2 * i] = x1 + Range.pickFrom(-5, 6);
            d[2 * i + 1] = x2 + Range.pickFrom(-5, 6);
            for(int j = 0; j < i; j++) {
                if(d[2 * i] == d[2 * j] && d[2 * i + 1] == d[2 * j + 1]) {
                    i--;
                    break;
                }
            }
        }

        Texable[] ans = new Texable[d.length / 2];
        for(int i = 0; i < ans.length; i++) {
            ans[i] = new Comma(new Expr[]{new Number(d[2 * i]), new Number(d[2 * i + 1])});
        }

        return new Set(p, ans);
    }
}
