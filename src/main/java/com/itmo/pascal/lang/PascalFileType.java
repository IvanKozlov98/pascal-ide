package com.itmo.pascal.lang;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.nio.charset.StandardCharsets;

public class PascalFileType extends LanguageFileType {
    protected PascalFileType() {
        super(PascalLanguage.getInstance());
    }
    private static final PascalFileType instance = new PascalFileType();

    public static PascalFileType getInstance() {
        return instance;
    }

    @Override
    public @NotNull String getName() {
        return "Pascal";
    }

    @Override
    public @NotNull String getDisplayName() {
        return "Pascal";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return "Pascal";
    }

    @Override
    public @NlsSafe @NotNull String getDefaultExtension() {
        return "pas";
    }

    @Override
    public String getCharset(@NotNull VirtualFile file, byte @NotNull [] content) {
        return StandardCharsets.UTF_8.name();
    }

    @Override
    public @Nullable Icon getIcon() {
        return null;
//        return PascalFileType.getInstance().getIcon();
    }
}
