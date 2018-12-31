package io.lumiknit.mathp;

import io.lumiknit.mathe.*;
import io.lumiknit.mathe.Number;

public class LinEqProblem extends Problem {
    public LinEqProblem(Range[] ranges) {
        super(ranges);
    }

    @Override
    public Set generate() {
        long x = ranges[0].pick();
        long a = ranges[1].pick();
        while(a == 0) a = ranges[1].pick();
        long b = ranges[2].pick();
        while(b == 0) b = ranges[1].pick();

        Expr lhs;
        lhs = new Roman('x');
        if(a == -1) lhs = new Minus(lhs);
        else if(a != 1) lhs = new Mul(new Number(a), lhs);
        if(b > 0) lhs = new Add(lhs, new Number(b));
        else if(b < 0) lhs = new Sub(lhs, new Number(-b));
        Statement eq = new Equal(lhs, new Number(a * x + b));
        Texable p = new Statements(new Statement[]{eq, new Equal(new Roman('x'), new Question())});

        long[] d = new long[]{x, 0, 0, 0};
        for(int i = 1; i < d.length; i++) {
            d[i] = x + Range.pickFrom(-5, 6);
            for(int j = 0; j < i; j++) {
                if(d[i] == d[j]) {
                    i--;
                    break;
                }
            }
        }

        Texable[] ans = new Texable[d.length];
        for(int i = 0; i < d.length; i++) {
            ans[i] = new Number(d[i]);
        }

        return new Set(p, ans);
    }
}
