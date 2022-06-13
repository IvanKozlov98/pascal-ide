package com.itmo.pascal.lang;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.stubs.PsiFileStub;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IStubFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import static com.itmo.pascal.lang.PascalTokenType.COMMENT;
import static com.itmo.pascal.lang.PascalTokenType.STRING;


class PascalFile extends PsiFileBase implements NavigatablePsiElement {
    protected PascalFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, PascalLanguage.getInstance());
    }

    @Override
    public @NotNull FileType getFileType() {
        return PascalFileType.getInstance();
    }
}

public class PascalParserDefinition implements ParserDefinition {
    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new PascalLexer();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new PascalParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return new IStubFileElementType<PsiFileStub<PascalFile>>(PascalLanguage.getInstance());
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return TokenSet.create(COMMENT);
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return TokenSet.create(STRING);
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        if (node.getElementType().equals(PascalElementType.IDENTIFIER))
            return new PascalRefIdentifier(node);
        else
            return new PascalSimpleNode(node);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new PascalFile(viewProvider);
    }
}
