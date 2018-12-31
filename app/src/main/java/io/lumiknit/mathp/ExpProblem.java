package io.lumiknit.mathp;

import io.lumiknit.mathe.*;
import io.lumiknit.mathe.Number;

public class ExpProblem extends Problem {
    public ExpProblem(Range[] ranges) {
        super(ranges);
    }

    @Override
    public Set generate() {
        long l = ranges[0].pick();
        long r = ranges[1].pick();

        android.util.Log.d("Test@ExpP", "l=" + l + ",r=" + r);

        Expr lhs = new Superscript(new Number(l), new Number(r));
        Texable p = new Equal(lhs, new Question());

        long[] d = new long[]{1, 0, 0, 0};
        for(int i = 0; i < r; i++) d[0] *= l;

        for(int i = 1; i < d.length; i++) {
            long a = Math.max(2, l + Range.pickFrom(-1, 1) * 2);
            long b = Math.max(0, r + Range.pickFrom(-3, 3));
            d[i] = 1;
            for(int j = 0; j < b; j++) d[i] *= a;
            for(int j = 0; j < i; j++) {
                if(d[i] == d[j]) {
                    i--;
                    break;
                }
            }
        }

        Texable[] ans = new Texable[d.length];
        for(int i = 0; i < d.length; i++) ans[i] = new Number(d[i]);

        return new Set(p, ans);
    }
}
