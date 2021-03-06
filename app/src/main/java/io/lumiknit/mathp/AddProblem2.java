package io.lumiknit.mathp;

import io.lumiknit.mathe.Number;
import io.lumiknit.mathe.*;

public class AddProblem2 extends Problem {
    public AddProblem2(Range[] ranges) {
        super(ranges);
    }

    @Override
    public Set generate() {
        long l = ranges[0].pick();
        long r = ranges[0].pick();
        Expr rt = new Number(r);
        if(r < 0) rt = new Paren(Paren.TYPE_ROUND, rt);
        Expr lhs = new Add(new Number(l), rt);
        Texable p = new Equal(lhs, new Question());

        long[] d = new long[]{0, 0, 0};

        long m = (long) Math.floor(Math.log10(Math.abs(l) + Math.abs(r)));
        long mx = m > 3 ? 2 : 0;

        long km = Math.max(1, 1 + m / 2);
        for (int i = 0; i < d.length; i++) {
            long z = 0;
            for(int k = 0; k < km; k++) {
                long x = Range.pickFrom(mx, m);
                long xx = 1;
                long y = Range.pickFrom(-4, 4);
                for (int j = 0; j < x; j++) xx *= 10;
                for (int j = 0; j < d.length; j++) z += y * xx;
            }
            d[i] = z;
            if (d[i] == 0) {
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
        ans[0] = new Number(l + r);
        for(int i = 1; i <= d.length; i++) ans[i] = new Number(l + r + d[i - 1]);

        return new Set(p, ans);
    }
}
