package com.itmo.pascal.lang;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public abstract class PascalPsiElement extends ASTWrapperPsiElement {
    protected PascalPsiElement(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PascalFile getContainingFile() {
        return (PascalFile)super.getContainingFile();
    }
}

class PascalSimpleNode extends PascalPsiElement {
    public PascalSimpleNode(@NotNull ASTNode node) {
        super(node);
    }
}

class PascalNamedNode extends PascalPsiElement implements PsiElement {

    public PascalNamedNode(ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        return getNode().getText();
    }
}


