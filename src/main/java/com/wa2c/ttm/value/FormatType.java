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
    SCC("scc", new String[]{"scc"}),
    SSA("ssa", new String[]{"ssa", "ass"}),
    TTML("ttml", new String[]{"ttml", "xml"}),
    QTTXT("qttxt", new String[]{"qt.txt"}),
    VTT("vtt", new String[]{"vtt"}),
    DFXP("dfxp", new String[]{"dfxp"}),
    SMI("smi", new String[]{"smi"}),
    SUB("sub", new String[]{"sub"}),
    RT("rt", new String[]{"rt"}),
    YOUTUBE("sbv", new String[]{"sbv"}),
    TX3G("tx3g", new String[]{""}),
    MPSUB("mpsub", new String[]{""}),

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
