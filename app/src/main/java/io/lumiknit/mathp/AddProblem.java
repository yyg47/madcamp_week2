package io.lumiknit.mathp;

import io.lumiknit.mathe.*;
import io.lumiknit.mathe.Number;

public class AddProblem extends Problem {
    public AddProblem(Range[] ranges) {
        super(ranges);
    }

    @Override
    public Set generate() {
        long l = ranges[0].pick();
        long r = ranges[0].pick();
        Expr rt = new Number(r);
        if(r < 0) rt = new Paren(Paren.TYPE_ROUND, rt);
        Expr lhs = new Add(new Number(l), rt);
        Statement p = new Equal(lhs, new Question());

        long[] d = new long[3];
        for(int i = 0; i < d.length; i++) {
            d[i] = Range.pickFrom(-4, 4);
            if(d[i] == 0) {
                i--; continue;
            }
            for(int j = 0; j < i; j++) {
                if(d[i] == d[j]) {
                    i--; break;
                }
            }
        }

        long x = Range.pickFrom(0, (long)Math.floor(Math.min(Math.log10(l), Math.log10(r))));
        long xx = 1;
        for(int i = 0; i < x; i++) xx *= 10;
        for(int i = 0; i < d.length; i++) d[i] = d[i] * xx + l + r;
        Texable[] ans = new Texable[d.length + 1];
        ans[0] = new Number(l + r);
        for(int i = 1; i <= d.length; i++) ans[i] = new Number(d[i - 1]);

        return new Set(p, ans);
    }
}
