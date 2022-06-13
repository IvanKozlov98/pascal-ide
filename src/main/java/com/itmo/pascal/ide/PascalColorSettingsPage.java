package com.itmo.pascal.ide;

import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.openapi.util.NlsContexts;
import com.itmo.pascal.lang.PascalFileType;
import com.itmo.pascal.lang.PascalLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PascalColorSettingsPage implements ColorSettingsPage {

    @Override
    public @Nullable Icon getIcon() {
        return PascalFileType.getInstance().getIcon();
    }

    @Override
    public @NotNull SyntaxHighlighter getHighlighter() {
        return new PascalSyntaxHighlighter();
    }

    private final String DEMO_TEXT = CodeStyleAbstractPanel.readFromFile(PascalLanguage.class, "Sample.pas");

    @Override
    public @NotNull String getDemoText() {
        return DEMO_TEXT;
    }

    @Override
    public @Nullable Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        HashMap<String, TextAttributesKey> res = new HashMap<>();
        for (PascalTextAttributeKeys v : PascalTextAttributeKeys.values()) {
            res.put(v.getName(), v.getKey());
        }
        return res;
    }

    @Override
    public @NotNull AttributesDescriptor[] getAttributeDescriptors() {
        List<AttributesDescriptor> res = new ArrayList<>();
        for (PascalTextAttributeKeys v : PascalTextAttributeKeys.values()) {
            res.add(v.getDescriptor());
        }
        return res.toArray(new AttributesDescriptor[0]);
    }

    @Override
    public @NotNull ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @Override
    public @NotNull @NlsContexts.ConfigurableName String getDisplayName() {
        return PascalLanguage.getInstance().getDisplayName();
    }
}