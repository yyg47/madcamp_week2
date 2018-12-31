package io.lumiknit.mathp;

public abstract class Problem {
    public Range[] ranges;

    public Problem(Range[] ranges) {
        this.ranges = ranges.clone();
    }

    public abstract Set generate();
}
