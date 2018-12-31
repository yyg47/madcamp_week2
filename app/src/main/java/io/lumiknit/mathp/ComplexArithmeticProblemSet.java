package io.lumiknit.mathp;

import android.util.Log;

import io.lumiknit.mathe.*;
import io.lumiknit.mathe.Number;

public class ComplexArithmeticProblemSet extends ProblemSet {
    private int level;
    private Problem[] problems;

    public ComplexArithmeticProblemSet(int level) {
        this.level = level;
        switch(level) {
            case 0: {
                Range[] ranges1 = new Range[]{new Range(-99999, 99999)};
                Range[] ranges2 = new Range[]{new Range(2, 99)};
                Range[] ranges3 = new Range[]{new Range(1, 99), new Range(3, 20)};
                Range[] ranges4 = new Range[]{new Range(2, 15), new Range(2, 8)};
                Range[] ranges5 = new Range[]{new Range(-20, 20)};
                Range[] ranges6 = new Range[]{new Range(10, 9999)};
                problems = new Problem[]{
                        new AddProblem2(ranges1),
                        new SubProblem2(ranges1),
                        new MultProblem(ranges2),
                        new FracAddProblem(ranges3),
                        new ExpProblem(ranges4),
                        new VecLenProblem(ranges5),
                        new SqrtProblem(ranges6),
                };
            } break;
        }
    }

    @Override
    public Set generate() {
        int n = (int)Range.pickFrom(0, problems.length);
        Log.d("Test@Problem", "" + n);
        return problems[n].generate();
    }
}
