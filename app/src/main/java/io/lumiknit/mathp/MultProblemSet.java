package io.lumiknit.mathp;

import io.lumiknit.mathp.*;

public class MultProblemSet extends ProblemSet {
    private int level;
    private Problem[] problems;

    public MultProblemSet(int level) {
        this.level = level;
        switch(level) {
            case 0: {
                Range[] ranges = new Range[]{new Range(-19, 19)};
                problems = new Problem[]{
                        new MultProblem(ranges)
                };
            } break;
            case 1: {
                Range[] ranges = new Range[]{new Range(-999, 999)};
                problems = new Problem[]{
                        new MultProblem(ranges)
                };
            } break;
            case 2: {
                Range[] ranges = new Range[]{new Range(-999999, 999999)};
                problems = new Problem[]{
                        new MultProblem(ranges)
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
