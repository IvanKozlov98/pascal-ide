package com.itmo.pascal.lexer;

import com.intellij.psi.tree.IElementType;import com.itmo.pascal.lang.PascalTokenType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import com.intellij.lexer.FlexLexer;
import static com.intellij.psi.TokenType.WHITE_SPACE;

%%
%public
%class _PascalLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode


DEC_DIGIT = [0-9]
DEC_INTEGER = "-"? {DEC_DIGIT}+
HEX_DIGIT = [a-fA-F0-9]
HEX_INTEGER = "0x" ({HEX_DIGIT})*

LINE_WS = [\ \t\f]+
EOL = "\r"|"\n"|"\r\n"
WHITE_SPACE=({LINE_WS}|{EOL})+

STRING = (\'([^\'\r\\]|\\.)*\')

COMMENT = (\(\*(.|\n)*\*\)|\/\/.*\n|\{(.|\n)*\})

END = "END"
// TODO ID may be start with '_'
ALPHA_NUM = [a-zA-Z0-9_]
ID = [a-zA-Z] {ALPHA_NUM}*
//SPID = "_" {ALPHA_NUM}+
//CID =   [A-Z] {ALPHA_NUM}*
//TID =  "'"{ALPHA_NUM}*

%%

{WHITE_SPACE}    { return WHITE_SPACE; }
{STRING}         { return PascalTokenType.STRING; }
{HEX_INTEGER}    { return PascalTokenType.HEX; }
{DEC_INTEGER}    { return PascalTokenType.INT; }

{END}            { return PascalTokenType.END; }

{COMMENT}        { return PascalTokenType.COMMENT; }

// Pascal tokens: http://cc.etsii.ull.es/ftp/antiguo/MTDPRG1/freepascal/manual/ref/node4.html

"ABSOLUTE"             { return PascalTokenType.ABSOLUTE; }
"AND"                  { return PascalTokenType.AND; }
"ASM"                  { return PascalTokenType.ASM; }
"BEGIN"                { return PascalTokenType.BEGIN; }
"BREAK"                { return PascalTokenType.BREAK; }
"CASE"                 { return PascalTokenType.CASE; }
"CONST"                { return PascalTokenType.CONST; }
"CONSTRUCTOR"          { return PascalTokenType.CONSTRUCTOR; }
"CONTINUE"             { return PascalTokenType.CONTINUE; }
"DESTRUCTOR"           { return PascalTokenType.DESTRUCTOR; }
"DIV"                  { return PascalTokenType.DIV; }
"DO"                   { return PascalTokenType.DO; }
"DOWNTO"               { return PascalTokenType.DOWNTO; }
"ELSE"                 { return PascalTokenType.ELSE; }
"FILE"                 { return PascalTokenType.FILE; }
"FOR"                  { return PascalTokenType.FOR; }
"FUNCTION"             { return PascalTokenType.FUNCTION; }
"GOTO"                 { return PascalTokenType.GOTO; }
"IF"                   { return PascalTokenType.IF; }
"IMPLEMENTATION"       { return PascalTokenType.IMPLEMENTATION; }
"IN"                   { return PascalTokenType.IN; }
"INHERITED"            { return PascalTokenType.INHERITED; }
"INLINE"               { return PascalTokenType.INLINE; }
"INTERFACE"            { return PascalTokenType.INTERFACE; }
"LABEL"                { return PascalTokenType.LABEL; }
"MOD"                  { return PascalTokenType.MOD; }
"NIL"                  { return PascalTokenType.NIL; }
"NOT"                  { return PascalTokenType.NOT; }
"OBJECT"               { return PascalTokenType.OBJECT; }
"OF"                   { return PascalTokenType.OF; }
"ON"                   { return PascalTokenType.ON; }
"OPERATOR"             { return PascalTokenType.OPERATOR; }
"OR"                   { return PascalTokenType.OR; }
"PACKED"               { return PascalTokenType.PACKED; }
"PROCEDURE"            { return PascalTokenType.PROCEDURE; }
"PROGRAM"              { return PascalTokenType.PROGRAM; }
"RECORD"               { return PascalTokenType.RECORD; }
"REPEAT"               { return PascalTokenType.REPEAT; }
"SELF"                 { return PascalTokenType.SELF; }
"SET"                  { return PascalTokenType.SET; }
"SHL"                  { return PascalTokenType.SHL; }
"SHR"                  { return PascalTokenType.SHR; }
"THEN"                 { return PascalTokenType.THEN; }
"TO"                   { return PascalTokenType.TO; }
"TYPE"                 { return PascalTokenType.TYPE; }
"UNIT"                 { return PascalTokenType.UNIT; }
"UNTIL"                { return PascalTokenType.UNTIL; }
"USES"                 { return PascalTokenType.USES; }
"VAR"                  { return PascalTokenType.VAR; }
"WHILE"                { return PascalTokenType.WHILE; }
"WITH"                 { return PascalTokenType.WITH; }
"XOR"                  { return PascalTokenType.XOR; }


"INTEGER"              { return PascalTokenType.INTEGER; }
"REAL"                 { return PascalTokenType.REAL; }
"BOOLEAN"              { return PascalTokenType.BOOLEAN; }
"CHAR"                 { return PascalTokenType.CHAR; }
"ARRAY"                { return PascalTokenType.ARRAY; }

{ID}             { return PascalTokenType.ID; }
//{CID}            { return PascalTokenType.CID; }
//{TID}            { return PascalTokenType.TID; }
//{SPID}           { return PascalTokenType.SPID; }

"'"           { return PascalTokenType.APOSTROPHE; }
"+"           { return PascalTokenType.PLUS; }
"-"           { return PascalTokenType.MINUS; }
"*"           { return PascalTokenType.ASTERISK; }
"/"           { return PascalTokenType.SLASH; }
"="           { return PascalTokenType.EQ; }
"<"           { return PascalTokenType.LT; }
">"           { return PascalTokenType.GT; }
"["           { return PascalTokenType.LBRACKET; }
"]"           { return PascalTokenType.RBRACKET; }
"."           { return PascalTokenType.DOT; }
","           { return PascalTokenType.COMMA; }
"("           { return PascalTokenType.LPAREN; }
")"           { return PascalTokenType.RPAREN; }
":"           { return PascalTokenType.COLON; }
"^"           { return PascalTokenType.CARET; }
"@"           { return PascalTokenType.AT; }
"$"           { return PascalTokenType.DOLLAR; }
"#"           { return PascalTokenType.HASH; }
"&"           { return PascalTokenType.AMP; }
"%"           { return PascalTokenType.PERCENT; }
";"           { return PascalTokenType.SEMICOLON; }


"<<"           { return PascalTokenType.DOUBLE_LT; }
">>"           { return PascalTokenType.DOUBLE_GT; }
"**"           { return PascalTokenType.DOUBLE_ASTERISK; }
"<>"           { return PascalTokenType.LT_GT; }
"><"           { return PascalTokenType.GT_LT; }
"<="           { return PascalTokenType.LQ; }
">="           { return PascalTokenType.GQ; }
":="           { return PascalTokenType.ASSIGN; }
"+="           { return PascalTokenType.PLUS_ASSIGN; }
"-="           { return PascalTokenType.MINUS_ASSIGN; }
"*="           { return PascalTokenType.ASTERISK_ASSIGN; }
"/="           { return PascalTokenType.DIVIDE_ASSIGN; }
"(."           { return PascalTokenType.LPAREN_DOT; }
".)"           { return PascalTokenType.RPAREN_DOT; }


[^] { return BAD_CHARACTER; }
