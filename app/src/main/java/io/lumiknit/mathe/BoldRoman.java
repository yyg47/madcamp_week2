package io.lumiknit.mathe;

public class BoldRoman extends Constant {
    char ch;

    public BoldRoman(char ch) {
        this.ch = ch;
    }

    @Override
    public String toTex() {
        return "\\mathbf{" + ch + "}";
    }

    @Override
    public String toString() {
        return "<<" + ch + ">>";
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof BoldRoman) && ((BoldRoman)other).ch == ch;
    }
}
