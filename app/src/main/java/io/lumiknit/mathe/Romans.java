package io.lumiknit.mathe;

public class Romans extends Constant {
    public String s;

    public Romans(String s) {
        this.s = s;
    }

    @Override
    public String toTex() {
        return s;
    }

    @Override
    public String toString() {
        return s;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Roman) && ((Romans)other).s.equals(s);
    }
}
