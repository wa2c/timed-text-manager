package com.wa2c.ttm.value;

import java.util.ResourceBundle;

public enum FormatType {
    // Text
    TEXT("text", new String[]{"txt"}),
    LRC("lrc", new String[]{"lrc"}),
    KRA("kra", new String[]{"kra", "lrc", "txt"}),

    // Subtitle
    SRT("srt", new String[]{"srt"}),
    STL("stl", new String[]{"stl"}),
    TTML("ttml", new String[]{"ttml"}),

    // Media
    UNSYNC("unsync", new String[]{}),
    SYLT("sylt", new String[]{"mp3"}),
    LYRICS3("lyrics3", new String[]{"mp3"})
    ;

    FormatType(String key, String[] extensions) {
        this.key = key;
        this.extensions = extensions;
    }

    private final String key;
    private final String[] extensions;

    public String getKey() {
        return this.key;
    }

    public String getName() {
        return ResourceBundle.getBundle("resource").getString("format.type." + getKey() + ".name");
    }


    public String[] getExtensions() {
        return this.extensions;
    }

    public String getDefaultExtension() {
        if (this.extensions.length == 0)
            return "";
        else
            return this.extensions[0];
    }
}
