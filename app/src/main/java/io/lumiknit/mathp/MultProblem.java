package io.lumiknit.mathp;

import io.lumiknit.mathe.*;
import io.lumiknit.mathe.Number;

public class MultProblem extends Problem {
    public MultProblem(Range[] ranges) {
        super(ranges);
    }

    @Override
    public Set generate() {
        long l = ranges[0].pick();
        long r = ranges[0].pick();
        android.util.Log.d("Test@MultP", "l=" + l + ",r=" + r);
        Expr rt = new Number(r);
        if(r < 0) rt = new Paren(Paren.TYPE_ROUND, rt);
        Expr lhs = new Times(new Number(l), rt);
        Texable p = new Equal(lhs, new Question());

        long[] d = new long[4];
        d[0] = l * r;
        long ll = ((long)Math.floor(Math.log10(l))) + 1;
        long lm = Range.pickFrom(ll > 2 ? 1 : 0, ll);
        long lx = 1;
        for(int i = 0; i < lm; i++) lx *= 10;
        long rr = ((long)Math.floor(Math.log10(r))) + 1;
        long rm = Range.pickFrom(rr > 2 ? 1 : 0, rr);
        long rx = 1;
        for(int i = 0; i < rm; i++) rx *= 10;
        for (int i = 1; i < d.length; i++) {
            long a = Range.pickFrom(-2, 2);
            long b = Range.pickFrom(-2, 2);
            d[i] = (l + lx * a) * (r + rx * b);
            if(d[i] <= 0) {
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
