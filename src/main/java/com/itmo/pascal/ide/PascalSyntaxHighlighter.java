package com.itmo.pascal.ide;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.itmo.pascal.lang.PascalLexer;
import com.itmo.pascal.lang.PascalTokenType;
import io.netty.util.AttributeKey;
import org.jetbrains.annotations.NotNull;

import javax.print.attribute.Attribute;
import java.util.HashMap;
import java.util.Map;


public class PascalSyntaxHighlighter extends SyntaxHighlighterBase {
    private final HashMap<IElementType, TextAttributesKey> keys1 = new HashMap<>();

    private void fillKeys(IElementType[] keys, TextAttributesKey value) {
        for (IElementType key : keys) {
            keys1.put(key, value);
        }
    }

    public PascalSyntaxHighlighter() {
        fillMap(keys1, PascalTokenType.KEYWORDS, PascalTextAttributeKeys.KEYWORD.getKey());
        fillMap(keys1, PascalTokenType.PROCEDURES, PascalTextAttributeKeys.FUNCTION_PROCEDURE.getKey());
        fillMap(keys1, PascalTokenType.TYPES, PascalTextAttributeKeys.TYPES.getKey());
        fillMap(keys1, PascalTokenType.SIMPLE_EXPRESSION_OPERATORS, PascalTextAttributeKeys.OPERATORS.getKey());
        fillMap(keys1, PascalTokenType.TERM_OPERATORS, PascalTextAttributeKeys.OPERATORS.getKey());
        fillMap(keys1, PascalTokenType.FACTOR_OPERATORS, PascalTextAttributeKeys.OPERATORS.getKey());
        fillMap(keys1, PascalTokenType.ASSIGNMENTS, PascalTextAttributeKeys.OPERATORS.getKey());

        keys1.put(PascalTokenType.INT, PascalTextAttributeKeys.INT.getKey());
        keys1.put(PascalTokenType.HEX, PascalTextAttributeKeys.HEX.getKey());
        keys1.put(PascalTokenType.STRING, PascalTextAttributeKeys.STRING.getKey());
        keys1.put(PascalTokenType.COMMENT, PascalTextAttributeKeys.COMMENT.getKey());
        keys1.put(PascalTokenType.LPAREN, PascalTextAttributeKeys.PARENTHESES.getKey());
        keys1.put(PascalTokenType.RPAREN, PascalTextAttributeKeys.PARENTHESES.getKey());
        keys1.put(PascalTokenType.LBRACKET, PascalTextAttributeKeys.BRACKETS.getKey());
        keys1.put(PascalTokenType.RBRACKET, PascalTextAttributeKeys.BRACKETS.getKey());
        keys1.put(PascalTokenType.COMMA, PascalTextAttributeKeys.COMMA.getKey());
        keys1.put(PascalTokenType.DOT, PascalTextAttributeKeys.DOT.getKey());
        keys1.put(PascalTokenType.SEMICOLON, PascalTextAttributeKeys.SEMICOLON.getKey());
        keys1.put(PascalTokenType.COLON, PascalTextAttributeKeys.COLON.getKey());
    }

    @Override
    public @NotNull Lexer getHighlightingLexer() {
        return new PascalLexer();
    }

    @Override
    public com.intellij.openapi.editor.colors.@NotNull TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return new TextAttributesKey[]{keys1.get(tokenType)};
    }
}
