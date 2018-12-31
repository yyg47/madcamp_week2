package io.lumiknit.mathp;

public class ComplexArithmeticEqSet extends ProblemSet {
    private int level;
    private Problem[] problems;

    public ComplexArithmeticEqSet(int level) {
        this.level = level;
        switch(level) {
            case 0: {
                Range[] ranges1 = new Range[]{new Range(-99999, 99999)};
                Range[] ranges2 = new Range[]{new Range(2, 199)};
                Range[] ranges3 = new Range[]{new Range(1, 40), new Range(3, 12)};
                Range[] ranges4 = new Range[]{new Range(2, 10), new Range(2, 7)};
                Range[] ranges5 = new Range[]{new Range(10, 150)};
                problems = new Problem[]{
                        new AddProblem2(ranges1),
                        new SubProblem2(ranges1),
                        new MultProblem(ranges2),
                        new DivProblem(ranges2),
                        new FracAddProblem(ranges3),
                        new ExpProblem(ranges4),
                        new SqrtProblem(ranges5),
                        new LinEqProblem(new Range[]{new Range(-10, 10), new Range(-5, 5), new Range(-10, 10)}),
                        new Lin2EqProblem(new Range[]{new Range(-10, 10), new Range(-5, 5), new Range(-10, 10)}),
                        new SqEqProblem(new Range[]{new Range(-10, 10), new Range(-3, 3), new Range(-10, 10)}),
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
