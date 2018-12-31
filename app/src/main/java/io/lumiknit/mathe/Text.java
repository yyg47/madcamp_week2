package io.lumiknit.mathe;

public class Text extends Expr {
    public String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public String toTex() {
        return "{\\text{" + text + "}}";
    }
}
