package com.itmo.pascal.lang;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class PascalElementType extends IElementType {
    public PascalElementType(@NonNls @NotNull String debugName) {
        super(debugName, PascalLanguage.getInstance());
    }

    public static IFileElementType PASCAL_CONTRACT_FILE = new IFileElementType(PascalLanguage.getInstance());
    public static PascalElementType PROGRAM_HEADING = new PascalElementType("PROGRAM_HEADING");
    public static PascalElementType BLOCK = new PascalElementType("BLOCK");
    public static PascalElementType DECLARATION = new PascalElementType("DECLARATION");
    public static PascalElementType STATEMENT = new PascalElementType("STATEMENT");
    public static PascalElementType STATEMENT_SEQ = new PascalElementType("STATEMENT_SEQ");
    public static PascalElementType STATEMENT_PART = new PascalElementType("STATEMENT_PART");
    public static PascalElementType PROCEDURE = new PascalElementType("PROCEDURE");
    public static PascalElementType PROCEDURE_HEADING = new PascalElementType("PROCEDURE_HEADING");
    public static PascalElementType FUNCTION_HEADING = new PascalElementType("FUNCTION_HEADING");
    public static PascalElementType ASSIGNMENT = new PascalElementType("ASSIGNMENT");
    public static PascalElementType EXPRESSION = new PascalElementType("EXPRESSION");
    public static PascalElementType NUM = new PascalElementType("NUM");
    public static PascalElementType STRING = new PascalElementType("STRING");
    public static PascalElementType NIL = new PascalElementType("NIL");
    public static PascalElementType CONSTANT = new PascalElementType("CONSTANT");
    public static PascalElementType CONSTANT_DEF = new PascalElementType("CONSTANT_DEF");
    public static PascalElementType ADDING_OPERATOR_WITH_TERM = new PascalElementType("ADDING_OPERATOR_WITH_TERM");
    public static PascalElementType FACTOR = new PascalElementType("FACTOR");
    public static PascalElementType IDENTIFIER = new PascalElementType("IDENTIFIER");
    public static PascalElementType PARAMETERS = new PascalElementType("PARAMETERS");
    public static PascalElementType VALUE_PARAMETER_SPECIFICATION = new PascalElementType("VALUE_PARAMETER_SPECIFICATION");
    public static PascalElementType VARIABLE_PARAMETER_SPECIFICATION = new PascalElementType("VARIABLE_PARAMETER_SPECIFICATION");
    public static PascalElementType FORMAL_PARAMETER_SECTION = new PascalElementType("FORMAL_PARAMETER_SECTION");
    public static PascalElementType PROCEDURE_CALLING = new PascalElementType("PROCEDURE_CALLING");
    public static PascalElementType SHIFT_EXPRESSION = new PascalElementType("SHIFT_EXPRESSION");
    public static PascalElementType TERM = new PascalElementType("TERM");
    public static PascalElementType SIMPLE_EXPRESSION = new PascalElementType("SIMPLE_EXPRESSION");
    public static PascalElementType PARAMETER_LIST = new PascalElementType("PARAMETER_LIST");
    public static PascalElementType FOR_STATEMENT = new PascalElementType("FOR_STATEMENT");
    public static PascalElementType REPEAT_STATEMENT = new PascalElementType("REPEAT_STATEMENT");
    public static PascalElementType WHILE_STATEMENT = new PascalElementType("WHILE_STATEMENT");
    public static PascalElementType IF_STATEMENT = new PascalElementType("IF_STATEMENT");
    public static PascalElementType FUNCTION = new PascalElementType("FUNCTION");
    public static PascalElementType VARIABLE_DECLARATION = new PascalElementType("VARIABLE_DECLARATION");
    public static PascalElementType TYPE = new PascalElementType("TYPE");
    public static PascalElementType GARBAGE_AT_THE_END_OF_FILE = new PascalElementType("GARBAGE_AT_THE_END_OF_FILE");
}
