package io.lumiknit.mathp;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Range {
    public static Random random = new Random();

    long from, to;

    public Range(long from, long to) {
        this.from = from;
        this.to = to;
    }

    private static long randomLong(long bound) {
        double n = random.nextDouble();
        return (long)Math.floor(n * bound);
    }

    public long pick() {
        return from + randomLong(to - from);
    }

    public long length() {
        return to - from;
    }

    public long pickFromLength() {
        return randomLong(to - from);
    }

    public static long pickFrom(long from, long to) {
        return from + randomLong(to - from);
    }

    public static long pickLengthFrom(long from, long to) {
        return randomLong(to - from);
    }
}
