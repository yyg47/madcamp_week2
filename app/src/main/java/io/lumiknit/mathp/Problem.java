package io.lumiknit.mathp;

public abstract class Problem {
    public Range[] ranges;

    public Problem(Range[] ranges) {
        if(ranges != null)
            this.ranges = ranges.clone();
        else this.ranges = null;
    }

    public abstract Set generate();
}
