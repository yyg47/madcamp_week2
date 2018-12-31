package io.lumiknit.mathe;

import java.util.HashMap;
import java.util.Map;

public class Context {
    Map<Expr, Expr> map;

    public Context() {
        map = new HashMap<>();
    }

    public void add(Expr k, Expr v) {
        map.put(k, v);
    }

    public Expr tryReplace(Expr k) {
        return map.get(k);
    }
}
