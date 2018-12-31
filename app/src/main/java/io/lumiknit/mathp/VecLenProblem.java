package io.lumiknit.mathp;

import io.lumiknit.mathe.*;
import io.lumiknit.mathe.Number;

public class VecLenProblem extends Problem {
    public VecLenProblem(Range[] ranges) {
        super(ranges);
    }

    long temp_s, temp_rt;

    void len(long a, long b, long c) {
        temp_rt = a * a + b * b + c * c;
        temp_s = 1;
        for(long i = 2; i * i <= temp_rt; i++) {
            while(temp_rt % (i * i) == 0) {
                temp_s *= i;
                temp_rt /= i * i;
            }
        }
    }

    @Override
    public Set generate() {
        long a = ranges[0].pick();
        long b = ranges[0].pick();
        long c = ranges[0].pick();
        len(a, b, c);

        Texable p = new Equal(new Norm(new Vec(new Expr[]{new Number(a), new Number(b), new Number(c)})), new Question());
        Texable[] ans = new Texable[4];
        long[] d = new long[ans.length * 2];
        d[0] = temp_s; d[1] = temp_rt;

        for(int i = 1; i < ans.length; i++) {
            long ta = a + Range.pickFrom(-3, 3);
            long tb = b + Range.pickFrom(-3, 3);
            long tc = c + Range.pickFrom(-3, 3);
            len(ta, tb, tc);
            d[i * 2] = temp_s; d[i * 2 + 1] = temp_rt;
            for(int j = 0; j < i; j++) {
                if(d[i * 2] == d[j * 2] && d[i * 2 + 1] == d[j * 2 + 1]) {
                    i--;
                    break;
                }
            }
        }

        for(int i = 0; i < ans.length; i++) {
            if(d[i * 2 + 1] == 1) ans[i] = new Number(d[i * 2]);
            else if(d[i * 2] == 1) ans[i] = new Sqrt(new Number(d[i * 2 + 1]));
            else ans[i] = new Mul(new Number(d[i * 2]), new Sqrt(new Number(d[i * 2 + 1])));
        }
        return new Set(p, ans);
    }
}
