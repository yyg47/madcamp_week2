package io.lumiknit.mathe;

public class Roman extends Constant {
    char ch;

    public Roman(char ch) {
        this.ch = ch;
    }

    @Override
    public String toTex() {
        return "" + ch;
    }

    @Override
    public String toString() {
        return "" + ch;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Roman) && ((Roman)other).ch == ch;
    }
}
