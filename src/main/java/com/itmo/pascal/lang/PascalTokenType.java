package com.itmo.pascal.lang;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class PascalTokenType extends IElementType {

    public PascalTokenType(@NotNull String debugName) {
        super(debugName, PascalLanguage.getInstance());
    }

    @Override
    public String toString() {
        return "PascalTokenType." + super.toString();
    }


    public static final PascalTokenType NEW_LINE = new PascalTokenType("NEW_LINE");
    public static final PascalTokenType COMMENT = new PascalTokenType("COMMENT");
    public static final PascalTokenType STRING = new PascalTokenType("STRING");
    public static final PascalTokenType INT = new PascalTokenType("INT");
    public static final PascalTokenType HEX = new PascalTokenType("HEX");

    public static final PascalTokenType ABSOLUTE = new PascalTokenType("ABSOLUTE");
    public static final PascalTokenType AND = new PascalTokenType("AND");
    public static final PascalTokenType ASM = new PascalTokenType("ASM");
    public static final PascalTokenType BEGIN = new PascalTokenType("BEGIN");
    public static final PascalTokenType BREAK = new PascalTokenType("BREAK");
    public static final PascalTokenType CASE = new PascalTokenType("CASE");
    public static final PascalTokenType CONST = new PascalTokenType("CONST");
    public static final PascalTokenType CONSTRUCTOR = new PascalTokenType("CONSTRUCTOR");
    public static final PascalTokenType CONTINUE = new PascalTokenType("CONTINUE");
    public static final PascalTokenType DESTRUCTOR = new PascalTokenType("DESTRUCTOR");
    public static final PascalTokenType DIV= new PascalTokenType("DIV");
    public static final PascalTokenType DO = new PascalTokenType("DO");
    public static final PascalTokenType DOWNTO = new PascalTokenType("DOWNTO");
    public static final PascalTokenType ELSE = new PascalTokenType("ELSE");
    public static final PascalTokenType END = new PascalTokenType("END");
    public static final PascalTokenType FILE = new PascalTokenType("FILE");
    public static final PascalTokenType FOR = new PascalTokenType("FOR");
    public static final PascalTokenType FUNCTION = new PascalTokenType("FUNCTION");
    public static final PascalTokenType GOTO = new PascalTokenType("GOTO");
    public static final PascalTokenType IF = new PascalTokenType("IF");
    public static final PascalTokenType IMPLEMENTATION = new PascalTokenType("IMPLEMENTATION");
    public static final PascalTokenType IN = new PascalTokenType("IN");
    public static final PascalTokenType INHERITED = new PascalTokenType("INHERITED");
    public static final PascalTokenType INLINE = new PascalTokenType("INLINE");
    public static final PascalTokenType INTERFACE = new PascalTokenType("INTERFACE");
    public static final PascalTokenType LABEL = new PascalTokenType("LABEL");
    public static final PascalTokenType MOD = new PascalTokenType("MOD");
    public static final PascalTokenType NIL = new PascalTokenType("NIL");
    public static final PascalTokenType NOT = new PascalTokenType("NOT");
    public static final PascalTokenType OBJECT = new PascalTokenType("OBJECT");
    public static final PascalTokenType OF = new PascalTokenType("OF");
    public static final PascalTokenType ON = new PascalTokenType("ON");
    public static final PascalTokenType OPERATOR = new PascalTokenType("OPERATOR");
    public static final PascalTokenType OR = new PascalTokenType("OR");
    public static final PascalTokenType PACKED = new PascalTokenType("PACKED");
    public static final PascalTokenType PROCEDURE = new PascalTokenType("PROCEDURE");
    public static final PascalTokenType PROGRAM = new PascalTokenType("PROGRAM");
    public static final PascalTokenType RECORD = new PascalTokenType("RECORD");
    public static final PascalTokenType REPEAT = new PascalTokenType("REPEAT");
    public static final PascalTokenType SELF = new PascalTokenType("SELF");
    public static final PascalTokenType SET = new PascalTokenType("SET");
    public static final PascalTokenType SHL = new PascalTokenType("SHL");
    public static final PascalTokenType SHR = new PascalTokenType("SHR");
    public static final PascalTokenType THEN = new PascalTokenType("THEN");
    public static final PascalTokenType TO = new PascalTokenType("TO");
    public static final PascalTokenType TYPE = new PascalTokenType("TYPE");
    public static final PascalTokenType UNIT = new PascalTokenType("UNIT");
    public static final PascalTokenType UNTIL = new PascalTokenType("UNTIL");
    public static final PascalTokenType USES = new PascalTokenType("USES");
    public static final PascalTokenType VAR = new PascalTokenType("VAR");
    public static final PascalTokenType WHILE = new PascalTokenType("WHILE");
    public static final PascalTokenType WITH = new PascalTokenType("WITH");
    public static final PascalTokenType XOR = new PascalTokenType("XOR");


    public static final PascalTokenType INTEGER = new PascalTokenType("INTEGER");
    public static final PascalTokenType REAL = new PascalTokenType("REAL");
    public static final PascalTokenType BOOLEAN = new PascalTokenType("BOOLEAN");
    public static final PascalTokenType CHAR = new PascalTokenType("CHAR");
    public static final PascalTokenType ARRAY = new PascalTokenType("ARRAY");


    public static final PascalTokenType APOSTROPHE = new PascalTokenType("APOSTROPHE");
    public static final PascalTokenType PLUS = new PascalTokenType("PLUS");
    public static final PascalTokenType MINUS = new PascalTokenType("MINUS");
    public static final PascalTokenType ASTERISK = new PascalTokenType("ASTERISK");
    public static final PascalTokenType SLASH = new PascalTokenType("SLASH");
    public static final PascalTokenType EQ = new PascalTokenType("EQ");
    public static final PascalTokenType LT = new PascalTokenType("LT");
    public static final PascalTokenType GT = new PascalTokenType("GT");
    public static final PascalTokenType LBRACKET = new PascalTokenType("LBRACKET");
    public static final PascalTokenType RBRACKET = new PascalTokenType("RBRACKET");
    public static final PascalTokenType DOT = new PascalTokenType("DOT");
    public static final PascalTokenType COMMA = new PascalTokenType("COMMA");
    public static final PascalTokenType LPAREN = new PascalTokenType("LPAREN");
    public static final PascalTokenType RPAREN = new PascalTokenType("RPAREN");
    public static final PascalTokenType COLON = new PascalTokenType("COLON");
    public static final PascalTokenType CARET = new PascalTokenType("CARET");
    public static final PascalTokenType AT = new PascalTokenType("AT");
    public static final PascalTokenType DOLLAR = new PascalTokenType("DOLLAR");
    public static final PascalTokenType HASH = new PascalTokenType("HASH");
    public static final PascalTokenType AMP = new PascalTokenType("AMP");
    public static final PascalTokenType PERCENT = new PascalTokenType("PERCENT");
    public static final PascalTokenType SEMICOLON = new PascalTokenType("SEMICOLON");


    public static final PascalTokenType DOUBLE_LT = new PascalTokenType("DOUBLE_LT");
    public static final PascalTokenType DOUBLE_GT = new PascalTokenType("DOUBLE_GT");
    public static final PascalTokenType DOUBLE_ASTERISK = new PascalTokenType("DOUBLE_ASTERISK");
    public static final PascalTokenType LT_GT = new PascalTokenType("LT_GT");
    public static final PascalTokenType GT_LT = new PascalTokenType("GT_LT");
    public static final PascalTokenType LQ = new PascalTokenType("LQ");
    public static final PascalTokenType GQ = new PascalTokenType("GQ");
    public static final PascalTokenType ASSIGN = new PascalTokenType("ASSIGN");
    public static final PascalTokenType PLUS_ASSIGN = new PascalTokenType("PLUS_ASSIGN");
    public static final PascalTokenType MINUS_ASSIGN = new PascalTokenType("MINUS_ASSIGN");
    public static final PascalTokenType ASTERISK_ASSIGN = new PascalTokenType("ASTERISK_ASSIGN");
    public static final PascalTokenType DIVIDE_ASSIGN = new PascalTokenType("DIVIDE_ASSIGN");
    public static final PascalTokenType LPAREN_DOT = new PascalTokenType("LPAREN_DOT");
    public static final PascalTokenType RPAREN_DOT = new PascalTokenType("RPAREN_DOT");
    public static final PascalTokenType CID = new PascalTokenType("CID");
    public static final PascalTokenType TID = new PascalTokenType("TID");
    public static final PascalTokenType ID = new PascalTokenType("ID");
    public static final PascalTokenType SPID = new PascalTokenType("SPID");

    public static final TokenSet KEYWORDS = TokenSet.create(ABSOLUTE, AND, ASM, BREAK, CASE, CONST, CONSTRUCTOR, CONTINUE,
            DESTRUCTOR, DIV, DO, DOWNTO, ELSE, FILE, FOR, FUNCTION, GOTO, IF, IMPLEMENTATION,
            IN, INHERITED, LABEL, MOD, NIL, NOT, OBJECT, OF, ON, OPERATOR, OR, PACKED,
            PROGRAM, RECORD, REPEAT, SELF, SET, SHL, SHL, THEN, TO, TYPE, UNIT,
            UNTIL, USES, VAR, WHILE, WITH, XOR);
    public static final TokenSet OPERATORS = TokenSet.create(PLUS, MINUS, ASTERISK, SLASH, CARET);
    public static final TokenSet SPECIFIC_SYMBOLS = TokenSet.create(APOSTROPHE, AT, DOLLAR, HASH, AMP, PERCENT, LPAREN_DOT, RPAREN_DOT);
    public static final TokenSet ASSIGNMENTS = TokenSet.create(EQ, ASSIGN, LQ, GQ, LT_GT, GT_LT, GT, LT, PLUS_ASSIGN,
            MINUS_ASSIGN, ASTERISK_ASSIGN, DIVIDE_ASSIGN);
    public static final TokenSet PROCEDURES = TokenSet.create(PROCEDURE, BEGIN, END);
    public static final TokenSet TYPES = TokenSet.create(INTEGER, REAL, BOOLEAN, CHAR, ARRAY);
    public static final TokenSet COMMENTS = TokenSet.create(COMMENT);
    public static final TokenSet STRINGS = TokenSet.create(STRING);

    public static final TokenSet SIMPLE_EXPRESSION_OPERATORS = TokenSet.create(EQ, LQ, GQ, LT_GT, GT, LT);
    public static final TokenSet TERM_OPERATORS = TokenSet.create(PLUS, MINUS, OR, XOR);
    public static final TokenSet FACTOR_OPERATORS = TokenSet.create(ASTERISK, SLASH, DIV, MOD, AND);
}
