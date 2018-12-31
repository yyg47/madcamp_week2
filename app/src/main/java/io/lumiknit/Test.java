package io.lumiknit;

import io.lumiknit.mathe.*;
import io.lumiknit.mathe.Number;
import io.lumiknit.mathp.*;

public class Test {
    public static void D(String tag, Object msg) {
        System.out.println(tag + ": " + msg.toString());
    }

    public static void S(Set s) {
        D("Problem", s.problem.toTex());
        D(" Answer", s.answers[0].toTex());
        D("   Ans1", s.answers[1].toTex());
        D("   Ans2", s.answers[2].toTex());
        D("   Ans3", s.answers[3].toTex());
    }


    public static void main(String[] args) {
        Range[] rng = new Range[]{new Range(10, 1000), new Range(3, 15)};
        Problem p = new SqrtProblem(rng);
        Set s = p.generate();
        S(s);
    }
}
