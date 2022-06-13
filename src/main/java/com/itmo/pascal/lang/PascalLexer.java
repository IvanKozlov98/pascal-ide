package com.itmo.pascal.lang;

import com.intellij.lexer.FlexAdapter;
import com.itmo.pascal.lexer._PascalLexer;

public class PascalLexer extends FlexAdapter {
    public PascalLexer() {
        super(new _PascalLexer(null));
    }
}
