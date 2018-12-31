package io.lumiknit.mathe;

public class Number extends Constant {
    public long value;

    public Number() {
        this(0);
    }

    public Number(long value) {
        this.value = value;
    }

    public Expr add(Number other) {
        return new Number(value + other.value);
    }

    public Expr sub(Number other) {
        return new Number(value - other.value);
    }

    public Expr mul(Number other) {
        return new Number(value * other.value);
    }

    public Expr div(Number other) {
        if(other.value == 0) {
            return new Undefined();
        }
        return new Number(value / other.value);
    }

    public Expr mod(Number other) {
        if(other.value == 0) {
            return new Number(0);
        }
        return new Number(value % other.value);
    }

    @Override
    public String toTex() {
        return "{" + Long.toString(value) + "}";
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Number) && ((Number)other).value == value;
    }
}
