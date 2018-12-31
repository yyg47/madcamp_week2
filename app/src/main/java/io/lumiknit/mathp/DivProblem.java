package io.lumiknit.mathp;

import io.lumiknit.mathe.*;
import io.lumiknit.mathe.Number;

public class DivProblem extends Problem {
    public DivProblem(Range[] ranges) {
        super(ranges);
    }

    @Override
    public Set generate() {
        long l = ranges[0].pick();
        long r = ranges[0].pick();
        Expr rt = new Number(r);
        if (r < 0) rt = new Paren(Paren.TYPE_ROUND, rt);
        Expr lhs = new Div(new Number(l * r), rt);
        Texable p = new Equal(lhs, new Question());

        long[] d = new long[4];
        d[0] = l;
        long ll = ((long) Math.floor(Math.log10(l)));
        long lm = Range.pickFrom(0, ll);
        long lx = 1;
        for (int i = 0; i < lm; i++) lx *= 10;
        for (int i = 1; i < d.length; i++) {
            long a = Range.pickFrom(-4, 4);
            d[i] = l + lx * a;
            if (d[i] <= 0) {
                i--;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (d[i] == d[j]) {
                    i--;
                    break;
                }
            }
        }

        Texable[] ans = new Texable[d.length + 1];
        for (int i = 0; i < d.length; i++) ans[i] = new Number(d[i]);

        return new Set(p, ans);
    }
}
