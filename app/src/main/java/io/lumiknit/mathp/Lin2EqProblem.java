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
import io.lumiknit.mathe.Texable;

public class Lin2EqProblem extends Problem {
    public Lin2EqProblem(Range[] ranges) {
        super(ranges);
    }

    Equal lin(long x, long y, long a, long b) {
        Expr xx = new Roman('x');
        if(a == -1) xx = new Minus(xx);
        else if(a != 1) xx = new Mul(new Number(a), xx);
        Expr yy = new Roman('y');
        if(b == -1) xx = new Sub(xx, yy);
        else if(b == 1) xx = new Add(xx, yy);
        else if(b < 0) xx = new Sub(xx, new Mul(new Number(-b), yy));
        else xx = new Add(xx, new Mul(new Number(b), yy));
        return new Equal(xx, new Number(a * x + b * y));
    }

    @Override
    public Set generate() {
        long x = ranges[0].pick();
        long y = ranges[0].pick();
        long a1 = ranges[1].pick();
        while(a1 == 0) a1 = ranges[1].pick();
        long a2 = ranges[1].pick();
        while(a2 == 0) a2 = ranges[1].pick();
        long b1 = ranges[1].pick();
        while(b1 == 0) b1 = ranges[1].pick();
        long b2 = ranges[1].pick();
        while(b2 == 0) b2 = ranges[1].pick();

        Statement eq1 = lin(x, y, a1, b1);
        Statement eq2 = lin(x, y, a2, b2);
        Texable p = new Statements(new Statement[]{eq1, eq2,
                new Equal(new Comma(new Expr[]{new Roman('x'), new Roman('y')}), new Question())});

        long[] d = new long[]{x, y, 0, 0, 0, 0, 0, 0};
        for(int i = 1; i < d.length / 2; i++) {
            d[2 * i] = x + Range.pickFrom(-5, 6);
            d[2 * i + 1] = y + Range.pickFrom(-5, 6);
            for(int j = 0; j < i; j++) {
                if(d[2 * i] == d[2 * j] && d[2 * i + 1] == d[2 * j + 1]) {
                    i--;
                    break;
                }
            }
        }

        Texable[] ans = new Texable[d.length / 2];
        for(int i = 0; i < ans.length; i++) {
            ans[i] = new Comma(new Expr[]{new Number(d[i * 2]), new Number(d[i * 2 + 1])});
        }
        return new Set(p, ans);
    }
}
