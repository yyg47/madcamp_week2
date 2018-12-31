package io.lumiknit.mathe;

public class Statements extends Statement {
    public Statement[] statements;

    public Statements(int n) {
        statements = new Statement[n];
    }

    public Statements(Statement[] s) {
        statements = new Statement[s.length];
        for(int i = 0; i < s.length; i++) {
            statements[i] = s[i];
        }
    }

    @Override
    public String toTex() {
        StringBuilder sb = new StringBuilder();
        for(Statement i : statements) {
            if(sb.length() > 0) sb.append("\\]\\[");
            sb.append(i.toTex());
        }
        return sb.toString();
    }
}
