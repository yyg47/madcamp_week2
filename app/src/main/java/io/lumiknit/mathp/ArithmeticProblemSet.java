package io.lumiknit.mathp;

public class ArithmeticProblemSet extends ProblemSet {
    private int level;
    private Problem[] problems;

    public ArithmeticProblemSet(int level) {
        this.level = level;
        switch(level) {
            case 0: {
                Range[] ranges1 = new Range[]{new Range(-99, 99)};
                Range[] ranges2 = new Range[]{new Range(4, 19)};
                problems = new Problem[]{
                        new AddProblem(ranges1),
                        new SubProblem(ranges1),
                        new MultProblem(ranges2),
                        new DivProblem(ranges2),
                };
            } break;
            case 1: {
                Range[] ranges1 = new Range[]{new Range(-999, 999)};
                Range[] ranges2 = new Range[]{new Range(5, 99)};
                problems = new Problem[]{
                        new AddProblem2(ranges1),
                        new SubProblem2(ranges1),
                        new MultProblem(ranges2),
                        new DivProblem(ranges2),
                };
            } break;
            case 2: {
                Range[] ranges1 = new Range[]{new Range(-99999999999L, 99999999999L)};
                Range[] ranges2 = new Range[]{new Range(999L, 99999999L)};
                problems = new Problem[]{
                        new AddProblem2(ranges1),
                        new SubProblem2(ranges1),
                        new MultProblem(ranges2),
                        new DivProblem(ranges2),
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
