package io.lumiknit.mathe;

public class Paren extends Expr {
    public static final int TYPE_ROUND = 0;
    public static final int TYPE_CURLY = 1;
    public static final int TYPE_SQUARE = 2;
    public static final int TYPE_ANGLE = 3;
    private static final char[] LEFT = new char[]{'(', '{', '[', '<'};
    private static final char[] RIGHT = new char[]{')', '}', ']', '>'};

    public int type;
    public Expr expr;

    public Paren(int type, Expr expr) {
        this.type = type;
        this.expr = expr;
    }

    @Override
    public String toTex() {
        return "{\\left" + LEFT[type] + "{" + expr.toTex() + "}\\right" + RIGHT[type] + "}";
    }
}
