package io.lumiknit.mathp;

import io.lumiknit.mathe.*;
import io.lumiknit.mathe.Number;

public class SqrtProblem extends Problem {
    public SqrtProblem(Range[] ranges) {
        super(ranges);
    }

    long temp_s, temp_rt;

    void len(long a) {
        temp_rt = a;
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
        Texable p;
        Texable[] ans = new Texable[4];
        long[] d = new long[ans.length * 2];
        long a;


        do {
            a = ranges[0].pick();
            len(a);
            d[0] = temp_s;
            d[1] = temp_rt;
        } while(d[0] == 1);
        p = new Equal(new Sqrt(new Number(a)), new Question());

        long x = (long) (Math.floor(Math.log10(a)) + 1) * 10;

        for(int i = 1; i < ans.length; i++) {
            long ta = Math.max(1, a + Range.pickFrom(-x, x));
            len(ta);
            d[i * 2] = temp_s; d[i * 2 + 1] = temp_rt;
            if(d[i * 2] == 1) {
                i--;
                continue;
            }
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
