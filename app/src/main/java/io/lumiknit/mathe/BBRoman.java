package io.lumiknit.mathe;

public class BBRoman extends Constant {
    char ch;

    public BBRoman(char ch) {
        this.ch = ch;
    }

    @Override
    public String toTex() {
        return "\\mathbb{" + ch + "}";
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
