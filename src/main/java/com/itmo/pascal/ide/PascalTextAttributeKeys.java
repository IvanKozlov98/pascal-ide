package com.itmo.pascal.ide;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.options.colors.AttributesDescriptor;

import java.util.HashSet;
import java.util.Set;

public class PascalTextAttributeKeys {
    public TextAttributesKey getKey() {
        return key;
    }

    private TextAttributesKey key;

    public AttributesDescriptor getDescriptor() {
        return descriptor;
    }

    private AttributesDescriptor descriptor;
    private String name;

    public String getName() {
        return name;
    }

    public PascalTextAttributeKeys(String humanName, TextAttributesKey fallback) {
        this.key = TextAttributesKey.createTextAttributesKey("Pascal." + humanName, fallback);
        this.name = humanName;
        this.descriptor = new AttributesDescriptor(humanName, key);
    }

    public static Set<PascalTextAttributeKeys> values() {
        Set<PascalTextAttributeKeys> res = new HashSet<>();
        res.add(COMMENT);
        res.add(STRING);
        res.add(INT);
        res.add(HEX);
        res.add(DOT);
        res.add(COMMA);
        res.add(SEMICOLON);
        res.add(COLON);
        res.add(PARENTHESES);
        res.add(BRACKETS);
        res.add(OPERATORS);
        res.add(KEYWORD);
        res.add(TYPES);
        res.add(ASSIGNMENTS);
        res.add(SPECIFIC_SYMBOLS);
        return res;
    }

    public static PascalTextAttributeKeys COMMENT = new PascalTextAttributeKeys("Comment", DefaultLanguageHighlighterColors.DOC_COMMENT);
    public static PascalTextAttributeKeys STRING = new PascalTextAttributeKeys("String text", DefaultLanguageHighlighterColors.STRING);
    public static PascalTextAttributeKeys INT = new PascalTextAttributeKeys("Decimal", DefaultLanguageHighlighterColors.NUMBER);
    public static PascalTextAttributeKeys HEX = new PascalTextAttributeKeys("Hexadecimal", DefaultLanguageHighlighterColors.NUMBER);

    public static PascalTextAttributeKeys DOT = new PascalTextAttributeKeys("Dot", DefaultLanguageHighlighterColors.DOT);
    public static PascalTextAttributeKeys COMMA = new PascalTextAttributeKeys("Comma", DefaultLanguageHighlighterColors.COMMA);
    public static PascalTextAttributeKeys SEMICOLON = new PascalTextAttributeKeys("Semicolon", DefaultLanguageHighlighterColors.SEMICOLON);
    public static PascalTextAttributeKeys COLON = new PascalTextAttributeKeys("Colon", DefaultLanguageHighlighterColors.DOT);

    public static PascalTextAttributeKeys PARENTHESES = new PascalTextAttributeKeys("Parentheses", DefaultLanguageHighlighterColors.PARENTHESES);
    public static PascalTextAttributeKeys BRACKETS = new PascalTextAttributeKeys("Brackets", DefaultLanguageHighlighterColors.BRACKETS);

    public static PascalTextAttributeKeys ASSIGNMENTS = new PascalTextAttributeKeys("Signs", DefaultLanguageHighlighterColors.DOT);
    public static PascalTextAttributeKeys SPECIFIC_SYMBOLS = new PascalTextAttributeKeys("Specific symbols", DefaultLanguageHighlighterColors.DOT);
    public static PascalTextAttributeKeys OPERATORS = new PascalTextAttributeKeys("Mathematical operations", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static PascalTextAttributeKeys KEYWORD = new PascalTextAttributeKeys("Keyword", DefaultLanguageHighlighterColors.KEYWORD);
    public static PascalTextAttributeKeys TYPES = new PascalTextAttributeKeys("Variables types", DefaultLanguageHighlighterColors.LABEL);
    public static PascalTextAttributeKeys FUNCTION_PROCEDURE = new PascalTextAttributeKeys("Procedure or function", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
}
