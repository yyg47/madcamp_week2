package io.lumiknit.mathp;

import io.lumiknit.mathe.Number;
import io.lumiknit.mathe.*;

public class FracAddProblem extends Problem {
    public FracAddProblem(Range[] ranges) {
        super(ranges);
    }

    long gcd(long a, long b) {
        if(b == 0) return a;
        else return gcd(b, a % b);
    }

    Expr frac(long u, long d) {
        Expr e = new Number(u);
        if(d != 1) e = new Frac(e, new Number(d));
        return e;
    }

    long temp_u, temp_d;
    void add(long au, long ad, long bu, long bd) {
        au *= bd;
        bu *= ad;
        ad *= bd;
        temp_u = au + bu;
        temp_d = ad;
        long g = gcd(temp_u, temp_d);
        temp_u /= g;
        temp_d /= g;
    }

    @Override
    public Set generate() {
        long lu = ranges[0].pick();
        long ld = ranges[1].pick();
        long lgcd = gcd(lu, ld);
        lu /= lgcd;
        ld /= lgcd;

        long ru = ranges[0].pick();
        long rd = ranges[1].pick();
        long rgcd = gcd(ru, rd);
        ru /= rgcd;
        rd /= rgcd;

        Expr lt = frac(lu, ld);
        Expr rt = frac(ru, rd);
        Expr lhs = new Add(lt, rt);
        Texable p = new Equal(lhs, new Question());
        Texable[] ans = new Texable[4];
        long[] d = new long[ans.length * 2];

        add(lu, ld, ru, rd);
        d[0] = temp_u; d[1] = temp_d;

        for(int i = 1; i < ans.length; i++) {
            long au = Math.max(1, lu + Range.pickFrom(-3, 3));
            long ad = ld;
            long bu = Math.max(1, ru + Range.pickFrom(-3, 3));
            long bd = rd;
            add(au, ad, bu, bd);
            d[i * 2] = temp_u;
            d[i * 2 + 1] = temp_d;
            for(int j = 0; j < i; j++) {
                if(d[j * 2] == d[i * 2] && d[j * 2 + 1] == d[i * 2 + 1]) {
                    i--;
                    break;
                }
            }
        }

        for(int i = 0; i < ans.length; i++) {
            ans[i] = frac(d[i * 2], d[i * 2 + 1]);
        }
        return new Set(p, ans);
    }
}
