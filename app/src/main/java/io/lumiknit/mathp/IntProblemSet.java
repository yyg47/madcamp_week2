package io.lumiknit.mathp;

public class IntProblemSet extends ProblemSet {
    private Problem[] problems;

    public IntProblemSet() {
        problems = new Problem[]{
                new IntElemProblem(null),
        };
    }
    @Override
    public Set generate() {
        int n = (int)Range.pickFrom(0, problems.length);
        return problems[n].generate();
    }
}
