package io.lumiknit.mathp;

import io.lumiknit.mathp.*;

public class AddSubProblemSet extends ProblemSet {
    private int level;
    private Problem[] problems;

    public AddSubProblemSet(int level) {
        this.level = level;
        switch(level) {
            case 0: {
                Range[] ranges = new Range[]{new Range(-99, 99)};
                problems = new Problem[]{
                        new AddProblem(ranges),
                        new SubProblem(ranges)
                };
            } break;
            case 1: {
                Range[] ranges = new Range[]{new Range(-999, 999)};
                problems = new Problem[]{
                        new AddProblem2(ranges),
                        new SubProblem2(ranges)
                };
            } break;
            case 2: {
                Range[] ranges = new Range[]{new Range(-99999999, 99999999)};
                problems = new Problem[]{
                        new AddProblem2(ranges),
                        new SubProblem2(ranges)
                };
            } break;
        }
    }

    @Override
    public Set generate() {
        int n = (int)Range.pickFrom(0, problems.length);
        return problems[n].generate();
    }
}
