package io.lumiknit.mathp;

public class EqProblemSet extends ProblemSet {
    private Problem[] problems;

    public EqProblemSet(int level) {
        switch(level) {
            case 0:
                problems = new Problem[]{
                        new LinEqProblem(new Range[]{new Range(-30, 30), new Range(-10, 10), new Range(-50, 50)})
                };
                break;
            case 1:
                problems = new Problem[]{
                        new LinEqProblem(new Range[]{new Range(-10, 10), new Range(-5, 5), new Range(-10, 10)}),
                        new Lin2EqProblem(new Range[]{new Range(-10, 10), new Range(-5, 5), new Range(-10, 10)}),
                        new SqEqProblem(new Range[]{new Range(-10, 10), new Range(-3, 3), new Range(-10, 10)}),
                };
                break;
        }
    }

    @Override
    public Set generate() {
        int n = (int)Range.pickFrom(0, problems.length);
        return problems[n].generate();
    }
}
