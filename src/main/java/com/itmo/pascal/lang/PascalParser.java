package com.itmo.pascal.lang;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
* Grammar for Pascal's sublanguage:
* program = program-heading ';' program-block
* program-block = block
* program-heading = 'program' identifier
*
* */
public class PascalParser implements PsiParser {
    @Override
    public @NotNull ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder) {
        new InnerParser(builder).parseContractModule();
        return builder.getTreeBuilt();
    }

    class InnerParser {
        private PsiBuilder builder;
        public InnerParser(PsiBuilder builder) {
            this.builder = builder;
        }


        private IElementType advance() {
            IElementType result = builder.getTokenType();
            builder.advanceLexer();
            while (builder.getTokenType() == TokenType.BAD_CHARACTER) {
                PsiBuilder.Marker badMark = builder.mark();
                builder.advanceLexer();
                badMark.error("Unexpected character");
            }
            return result;
        }

        private void errorAdvance(String expectedName) {
            PsiBuilder.Marker mark = builder.mark();
            advance();
            mark.error("Expected " + expectedName);
        }

        private boolean expectAdvance(PascalTokenType expectedTokenType, String expectedName) {
            if (builder.getTokenType().equals(expectedTokenType)) {
                advance();
                return true;
            } else {
                System.out.println(builder.getTokenText());
                builder.error(expectedName);
                return false;
            }
        }

        private void parseIdentifier() {
            PsiBuilder.Marker mark = builder.mark();
            expectAdvance(PascalTokenType.ID, "identifier");
            mark.done(PascalElementType.IDENTIFIER);
        }

        // program-heading = 'program' identifier
        private void parseProgramHeading() {
            PsiBuilder.Marker curMark = builder.mark();
            expectAdvance(PascalTokenType.PROGRAM, "program");
            parseIdentifier();
            if (isEqualCurToken(PascalTokenType.LPAREN)) {
                advance();
                if (!isEqualCurToken(PascalTokenType.RPAREN))
                    parseIdentifierList();
                expectAdvance(PascalTokenType.RPAREN, ")");
            }
            curMark.done(PascalElementType.PROGRAM_HEADING);
        }

//        actual-parameter-list = '(' actual-parameter { ',' actual-parameter } ')'
        private void parseParameterList() {
            PsiBuilder.Marker mark = builder.mark();
            expectAdvance(PascalTokenType.LPAREN, "(");
            while (!isEqualCurToken(PascalTokenType.RPAREN)) {
                parseExpression();
                if (isEqualCurToken(PascalTokenType.COMMA))
                    advance();
                else
                    break;
            }
            expectAdvance(PascalTokenType.RPAREN, ")");
            mark.done(PascalElementType.PARAMETER_LIST);
        }

        // identifier-list = identifier { ',' identifier }
        private void parseIdentifierList() {
            PsiBuilder.Marker mark = builder.mark();
            parseIdentifier();
            while (isEqualCurToken(PascalTokenType.COMMA)) {
                advance();
                parseIdentifier();
            }
            mark.done(PascalElementType.PARAMETERS);
        }

        private void parseType() {
            PsiBuilder.Marker mark = builder.mark();
            if (isEqualCurToken(PascalTokenType.INTEGER) ||
                isEqualCurToken(PascalTokenType.CHAR) ||
                isEqualCurToken(PascalTokenType.BOOLEAN))
                advance();

            mark.done(PascalElementType.TYPE);
        }

        // value-parameter-specification = identifier-list ':' type-identifier
        private void parseValueParameterSpec() {
            PsiBuilder.Marker mark = builder.mark(); 
            parseIdentifierList();
            expectAdvance(PascalTokenType.COLON, ":");
            parseType();
            mark.done(PascalElementType.VALUE_PARAMETER_SPECIFICATION);
        }

        // variable-parameter-specification = 'var' identifier-list ':' type-identifier
        private void parseVariableParameterSpec() {
            PsiBuilder.Marker mark = builder.mark();
            expectAdvance(PascalTokenType.VAR, "var");
            parseIdentifierList();
            expectAdvance(PascalTokenType.COLON, ":");
            parseType();
            mark.done(PascalElementType.VARIABLE_PARAMETER_SPECIFICATION);
        }

        // formal-parameter-section = value-parameter-specification |
        //                  variable-parameter-specification |
        //                  procedure-parameter-specification |
        //                  function-parameter-specification
        private void parseFormalParameterSection() {
            PsiBuilder.Marker mark = builder.mark();
            if (isEqualCurToken(PascalTokenType.VAR))
                parseVariableParameterSpec();
            else if (isEqualCurToken(PascalTokenType.FUNCTION)) 
                parseFunctionHeading();
            else if (isEqualCurToken(PascalTokenType.PROCEDURE)) 
                parseProcedureHeading();
            else if (isEqualCurToken(PascalTokenType.ID))
                parseValueParameterSpec();
            else
                errorAdvance("Expected VAR, FUNCTION, PROCEDURE OR IDENTIFIER");
            mark.done(PascalElementType.FORMAL_PARAMETER_SECTION);
        }

        // '(' formal-parameter-section { ';' formal-parameter-section } ')'
        private void parseFormalParameterList() {
            expectAdvance(PascalTokenType.LPAREN, "(");
            parseFormalParameterSection();
            while (isEqualCurToken(PascalTokenType.SEMICOLON)) {
                advance();
                parseFormalParameterSection();
            }
            expectAdvance(PascalTokenType.RPAREN, ")");
        }


        private void parseFunctionHeading() {
            PsiBuilder.Marker mark = builder.mark();
            expectAdvance(PascalTokenType.FUNCTION, "procedure");
            parseIdentifier();
            if (isEqualCurToken(PascalTokenType.LPAREN)) {
                parseFormalParameterList();
            }
            mark.done(PascalElementType.FUNCTION_HEADING);
        }

        // procedure-heading = 'procedure' identifier [ formal-parameter-list ]
        private void parseProcedureHeading() {
            PsiBuilder.Marker mark = builder.mark();
            expectAdvance(PascalTokenType.PROCEDURE, "procedure");
            parseIdentifier();
            if (isEqualCurToken(PascalTokenType.LPAREN)) {
                parseFormalParameterList();
            }
            mark.done(PascalElementType.PROCEDURE_HEADING);
        }

        // procedure-declaration =
        //       procedure-identification ';' procedure-block |
        //       procedure-heading ';' procedure-block
        private void parseProcedure() {
            PsiBuilder.Marker mark = builder.mark();
            parseProcedureHeading();
            expectAdvance(PascalTokenType.SEMICOLON, ";");
            parseBlock();
            mark.done(PascalElementType.PROCEDURE);
        }

        private void parseNum() {
            PsiBuilder.Marker numMark = builder.mark();
            advance();
            numMark.done(PascalElementType.NUM);
        }

        private void parseNil() {
            PsiBuilder.Marker numMark = builder.mark();
            advance();
            numMark.done(PascalElementType.NIL);
        }

        private void parseString() {
            PsiBuilder.Marker numMark = builder.mark();
            advance();
            numMark.done(PascalElementType.STRING);
        }



        //   constant = [ sign ] integer-number |
        //              [ sign ] constant-identifier |
        //                       character-liter |
        //                       string-literal
        private void parseConstant() { // OK
            PsiBuilder.Marker mark = builder.mark();
            boolean isConstNum = false;
            if (isEqualCurToken(PascalTokenType.MINUS)) {
                advance();
                isConstNum = true;
            }
            if (isEqualCurToken(PascalTokenType.INT))
                parseNum();
            else if (isEqualCurToken(PascalTokenType.ID))
                parseIdentifier();
            else if (isEqualCurToken(PascalTokenType.STRING)) {
                if (isConstNum) {
                    errorAdvance("expected num after minus");
                    return;
                } else
                    parseString();
            }
            mark.done(PascalElementType.CONSTANT);
        }

        // identifier '=' constant
        private void parseConstantDef() { // OK
            PsiBuilder.Marker mark = builder.mark();
            parseIdentifier();
            expectAdvance(PascalTokenType.EQ, "=");
            parseConstant();
            mark.done(PascalElementType.CONSTANT_DEF);
        }

        // variable-declaration = identifier-list ':' type-denoter
        private void parseVariableDeclaration() {
            PsiBuilder.Marker mark = builder.mark();
            parseIdentifierList();
            expectAdvance(PascalTokenType.COLON, ":");
            parseType();
            mark.done(PascalElementType.VARIABLE_DECLARATION);
        }

        private void parseFunction() {
            PsiBuilder.Marker mark = builder.mark();
            parseFunctionHeading();
            expectAdvance(PascalTokenType.SEMICOLON, ";");
            parseBlock();
            mark.done(PascalElementType.FUNCTION);
        }

        // declaration-group =
        //             constant-definition-group |
        //             variable-declaration-group |
        //             function-declaration  |
        //             procedure-declaration
        private void parseDeclarativePart() {
            PsiBuilder.Marker mark = builder.mark();
            boolean isWasDeclarative = false;
            while (true) {
                if (isEqualCurToken(PascalTokenType.CONST)) {
                    advance();
                    parseConstantDef();
                    expectAdvance(PascalTokenType.SEMICOLON, ";");
                    while (isEqualCurToken(PascalTokenType.ID)) {
                        parseConstantDef();
                        expectAdvance(PascalTokenType.SEMICOLON, ";");
                    }
                } else if (isEqualCurToken(PascalTokenType.PROCEDURE))
                    parseProcedure();
                else if (isEqualCurToken(PascalTokenType.FUNCTION))
                    parseFunction();
                else if (isEqualCurToken(PascalTokenType.VAR)) { // parse variable declaration
                    advance();
                    parseVariableDeclaration();
                    expectAdvance(PascalTokenType.SEMICOLON, ";");
                    while (isEqualCurToken(PascalTokenType.ID)) {
                        parseVariableDeclaration();
                        expectAdvance(PascalTokenType.SEMICOLON, ";");
                    }
                } else
                    break;
                isWasDeclarative = true;
            }
            if (isWasDeclarative)
                mark.done(PascalElementType.DECLARATION);
            else
                mark.drop();
        }

        private boolean isEqualCurToken(PascalTokenType tokenType) {
            return builder.getTokenType().equals(tokenType);
        }

        // unsigned-constant = integer-number |
        //         character-literal | string-literal | constant-identifier |
        //         'nil'
        boolean tryParseUnsignedConstant() { // OK
            if (isEqualCurToken(PascalTokenType.INT))
                parseNum();
            else if (isEqualCurToken(PascalTokenType.STRING))
                parseString();
            else if (isEqualCurToken(PascalTokenType.NIL))
                parseNil();
            else if (isEqualCurToken(PascalTokenType.ID))
                parseIdentifier();
            else
                return false;
            return true;
        }

        // factor = [ sign ] unsigned-constant |
        //          [ sign ] variable-access |
        //          [ sign ] '(' expression ')' |
        //          [ sign ] function-designator |
        //          [ sign ] 'not' factor
        private void parseFactor() {
            PsiBuilder.Marker mark = builder.mark();
            if (isEqualCurToken(PascalTokenType.MINUS))
                advance();
            if (isEqualCurToken(PascalTokenType.NOT)) {
                advance();
                parseFactor();
            } else if (isEqualCurToken(PascalTokenType.LPAREN)) {
                advance();
                parseExpression();
                expectAdvance(PascalTokenType.RPAREN, ")");
            } else if (isEqualCurToken(PascalTokenType.ID) &&
                    builder.lookAhead(1).equals(PascalTokenType.LPAREN))
                parseProcedureCalling();
            else if (!tryParseUnsignedConstant())
                errorAdvance("Expected unsigned-constant, variable, (expr), function calling or not factor");
            mark.done(PascalElementType.FACTOR);
        }


        // term = factor { multiplying-operator factor }
        private void parseTerm() {
            PsiBuilder.Marker mark = builder.mark();
            parseFactor();
            while (multiplyingTokens.contains(builder.getTokenType())) {
                advance();
                parseFactor();
            }
            mark.done(PascalElementType.TERM);
        }

        private final Set<PascalTokenType> addingTokens = Stream.of(
                PascalTokenType.PLUS,
                PascalTokenType.MINUS,
                PascalTokenType.OR,
                PascalTokenType.XOR
        ).collect(Collectors.toSet());

        private final Set<PascalTokenType> multiplyingTokens = Stream.of(
                PascalTokenType.DIV,
                PascalTokenType.MOD,
                PascalTokenType.AND,
                PascalTokenType.ASTERISK,
                PascalTokenType.SLASH
        ).collect(Collectors.toSet());

        // simple-expression = term { adding-operator term }
        private void parseSimpleExpression() {
            PsiBuilder.Marker mark = builder.mark();
            parseTerm();
            while (addingTokens.contains(builder.getTokenType())) {
                advance();
                parseTerm();
            }
            mark.done(PascalElementType.SIMPLE_EXPRESSION);
        }

        // shift-expression = simple-expression [ shift-operator simple-expression ]
        private void parseShiftExpression() {
            PsiBuilder.Marker mark = builder.mark();
            parseSimpleExpression();
            if (isEqualCurToken(PascalTokenType.SHL) || isEqualCurToken(PascalTokenType.SHR)) {
                advance();
                parseSimpleExpression();
            }
            mark.done(PascalElementType.SHIFT_EXPRESSION);
        }

        private final Set<PascalTokenType> relationTokens = Stream.of(
                PascalTokenType.IN,
                PascalTokenType.EQ,
                PascalTokenType.LT,
                PascalTokenType.GT,
                PascalTokenType.LQ,
                PascalTokenType.LT_GT).collect(Collectors.toSet());

        // expression = shift-expression [ relational-operator shift-expression ]
        private void parseExpression() { // OK
            PsiBuilder.Marker mark = builder.mark();
            parseShiftExpression();
            if (relationTokens.contains(builder.getTokenType())) {
                advance();
                parseShiftExpression();
            }
            mark.done(PascalElementType.EXPRESSION);
        }

        // assignment-statement = assignment-statement-lhs ':=' expression
        private void parseAssignmentStatement() { // OK
            PsiBuilder.Marker mark = builder.mark();
            // TODO (IvanKozlov98) while support only simple vars (without list, arrays, pointers...)
            parseIdentifier();
            expectAdvance(PascalTokenType.ASSIGN, "assignment");
            parseExpression();
            mark.done(PascalElementType.ASSIGNMENT);
        }

        //procedure-method-statement = procedure-method-specifier [ actual-parameter-list ]
        private void parseProcedureCalling() {
            PsiBuilder.Marker mark = builder.mark();
            parseIdentifier();
            if (isEqualCurToken(PascalTokenType.LPAREN))
               parseParameterList();
            mark.done(PascalElementType.PROCEDURE_CALLING);
        }

        private void parseSimpleStatement() { // OK
            // simple-statement = assignment-statement | procedure-statement | procedure-method-statement
            if (isEqualCurToken(PascalTokenType.PROCEDURE))
                parseProcedure();
            else {
                if (isEqualCurToken(PascalTokenType.ID) &&
                    builder.lookAhead(1).equals(PascalTokenType.ASSIGN))
                    parseAssignmentStatement();
                else
                    parseProcedureCalling();
            }
        }

        // if-statement = 'if' boolean-expression 'then' statement [ else-part ]
        private void parseIfStatement() {
            PsiBuilder.Marker mark = builder.mark();
            expectAdvance(PascalTokenType.IF, "if");
            parseExpression();
            expectAdvance(PascalTokenType.THEN, "then");
            parseStatement();
            if (isEqualCurToken(PascalTokenType.ELSE)) {
                advance();
                parseStatement();
            }
            mark.done(PascalElementType.IF_STATEMENT);
        }

        private void parseRepetitiveStatement() {
            if (isEqualCurToken(PascalTokenType.FOR)) {
                // for-statement = 'for' control-variable ':=' initial-value ( 'to' | 'downto' ) final-value
                //                  'do' statement
                PsiBuilder.Marker mark = builder.mark();
                advance();
                parseIdentifier();
                expectAdvance(PascalTokenType.ASSIGN, "expected :=");
                parseExpression();
                if (isEqualCurToken(PascalTokenType.TO) || isEqualCurToken(PascalTokenType.DOWNTO))
                    advance();
                else
                    errorAdvance("Expected TO or DOWNTO");
                parseExpression();
                mark.done(PascalElementType.FOR_STATEMENT);
            } else if (isEqualCurToken(PascalTokenType.WHILE)) {
                // while-statement = 'while' boolean-expression 'do' statement
                PsiBuilder.Marker mark = builder.mark();
                advance();
                parseExpression();
                expectAdvance(PascalTokenType.DO, "do in while");
                parseStatement();
                mark.done(PascalElementType.WHILE_STATEMENT);
            } else if (isEqualCurToken(PascalTokenType.REPEAT)) {
                // repeat-statement = 'repeat' statement-sequence 'until' boolean-expression
                PsiBuilder.Marker mark = builder.mark();
                advance();
                parseStatementSequence();
                expectAdvance(PascalTokenType.UNTIL, "until");
                parseExpression();
                mark.done(PascalElementType.REPEAT_STATEMENT);
            } else
                errorAdvance("Expected FOR, WHILE OR REPEAT");
        }



        // structured-statement = compound-statement |
        //                        conditional-statement |
        //                        repetitive-statement
        private boolean tryParseStructuredStatement() { // OK
            if (isEqualCurToken(PascalTokenType.BEGIN))
                parseStatementPart();
            else if (isEqualCurToken(PascalTokenType.IF))
                parseIfStatement();
            else if (isEqualCurToken(PascalTokenType.FOR) ||
                    isEqualCurToken(PascalTokenType.WHILE) ||
                    isEqualCurToken(PascalTokenType.REPEAT))
                parseRepetitiveStatement();
            else
                return false;
            return true;
        }

        // statement = ( simple-statement | structured-statement )
        private void parseStatement() { // OK
            PsiBuilder.Marker mark = builder.mark();
            if (!tryParseStructuredStatement())
                parseSimpleStatement();
            mark.done(PascalElementType.STATEMENT);
        }

        //  statement-sequence = statement { ';' statement }
        private void parseStatementSequence() { // OK
            PsiBuilder.Marker mark = builder.mark();
            parseStatement();
            while (isEqualCurToken(PascalTokenType.SEMICOLON)) {
                advance();
                parseStatement();
            }
            mark.done(PascalElementType.STATEMENT_SEQ);
        }

        // 'begin' statement-sequence 'end'
        private void parseStatementPart() { // OK
            PsiBuilder.Marker mark = builder.mark();
            expectAdvance(PascalTokenType.BEGIN, "begin");
            parseStatementSequence();
            expectAdvance(PascalTokenType.END, "end");
            mark.done(PascalElementType.STATEMENT_PART);
        }

        // block = declarative-part statement-part
        private void parseBlock() {
            PsiBuilder.Marker mark = builder.mark();
            parseDeclarativePart();
            parseStatementPart();
            mark.done(PascalElementType.BLOCK);
        }

        // program = program-heading ';' program-block
        public void parseContractModule() {
            PsiBuilder.Marker marker = builder.mark();
            parseProgramHeading();
            expectAdvance(PascalTokenType.SEMICOLON, ";");
            parseBlock();
            marker.done(PascalElementType.PASCAL_CONTRACT_FILE);
        }
    }
}
