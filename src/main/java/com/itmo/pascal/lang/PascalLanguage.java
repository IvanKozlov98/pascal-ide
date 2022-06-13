package com.itmo.pascal.lang;

import com.intellij.lang.Language;

public class PascalLanguage extends Language {
    protected PascalLanguage() {
        super("Pascal");
    }

    private static final PascalLanguage instance = new PascalLanguage();

    public static PascalLanguage getInstance() {
        return instance;
    }
}
