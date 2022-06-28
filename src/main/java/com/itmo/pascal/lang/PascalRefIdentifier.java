package com.itmo.pascal.lang;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PascalRefIdentifier extends PascalPsiElement {
    public PascalRefIdentifier(@NotNull ASTNode node) {
        super(node);
    }

    private ASTNode lookInDeclaredVars(ASTNode varsDeclaration) {
        ASTNode ids = varsDeclaration.findChildByType(PascalElementType.PARAMETERS);
        if (ids == null)
            return null;

        for (ASTNode child : ids.getChildren(null)) {
            if (child.getText().equals(getNode().getText()))
                return child;
        }

        return null;
    }

    private ASTNode lookInConstVars(ASTNode constantDef) {
        ASTNode identifier = constantDef.findChildByType(PascalElementType.IDENTIFIER);
        if (identifier != null && identifier.getText().equals(getNode().getText()))
            return identifier;
        else
            return null;
    }


    private ASTNode lookInDeclaredFunctionsOrProcedures(ASTNode functionDeclaration) {
        ASTNode heading = functionDeclaration.getElementType().equals(PascalElementType.PROCEDURE)
                ? functionDeclaration.findChildByType(PascalElementType.PROCEDURE_HEADING)
                : functionDeclaration.findChildByType(PascalElementType.FUNCTION_HEADING);
        assert heading != null;
        ASTNode identifier = heading.findChildByType(PascalElementType.IDENTIFIER);
        assert identifier != null;
        if (identifier.getText().equals(getNode().getText()))
            return identifier;
        return null;
    }

    private ASTNode lookInDeclaredEntities(ASTNode declarations) {
        ASTNode identifier = null;
        for (ASTNode decl : declarations.getChildren(null)) {
            if (decl.getElementType().equals(PascalElementType.VARIABLE_DECLARATION))
                identifier = lookInDeclaredVars(decl);
            else if (decl.getElementType().equals(PascalElementType.CONSTANT_DEF))
                identifier = lookInConstVars(decl);
            else if (decl.getElementType().equals(PascalElementType.PROCEDURE) ||
                    decl.getElementType().equals(PascalElementType.FUNCTION)) {
                identifier = lookInDeclaredFunctionsOrProcedures(decl);
            }
            if (identifier != null)
                return identifier;
        }
        return null;
    }

    @Override
    public PsiReferenceBase<PascalRefIdentifier> getReference() {
        ASTNode parent = getNode();
        ASTNode foundRef = null;
        do {
            if (parent.getElementType().equals(PascalElementType.BLOCK)) {
                ASTNode declarationNode = parent.findChildByType(PascalElementType.DECLARATION);
                if (declarationNode != null)
                    foundRef = lookInDeclaredEntities(declarationNode);
            }
            if (foundRef != null)
                break;
            parent = parent.getTreeParent();
        } while (parent != null);

        ASTNode returnedFoundRef = foundRef;
        PsiElement ref = getNode().getPsi();
        return new PsiReferenceBase<PascalRefIdentifier>(this,
                /*rangeInElement*/ ref.getTextRangeInParent().shiftRight(ref.getStartOffsetInParent())) {
            @Override
            public @Nullable PsiElement resolve() {
                return new PascalNamedNode(returnedFoundRef == null ? getNode() : returnedFoundRef);
            }
        };
    }
}
