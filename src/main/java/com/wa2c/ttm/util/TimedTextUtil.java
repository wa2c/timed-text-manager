package com.wa2c.ttm.util;

import com.wa2c.ttm.base.TimedTextModel;
import com.wa2c.ttm.value.FormatType;
import org.apache.commons.lang3.StringUtils;
import org.mozilla.universalchardet.UniversalDetector;
import org.omg.IOP.Encoding;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class TimedTextUtil {

    /**
     * Unicode empty characters.
     */
    public static final String UNICODE_SPACES = "[" +
            "\\u0009-\\u000d" +     //  # White_Space # Cc   [5] <control-0009>..<control-000D>
            "\\u0020" +             // White_Space # Zs       SPACE
            "\\u0085" +             // White_Space # Cc       <control-0085>
            "\\u00a0" +             // White_Space # Zs       NO-BREAK SPACE
            "\\u1680" +             // White_Space # Zs       OGHAM SPACE MARK
            "\\u180E" +             // White_Space # Zs       MONGOLIAN VOWEL SEPARATOR
            "\\u2000-\\u200a" +    // # White_Space # Zs  [11] EN QUAD..HAIR SPACE
            "\\u2028" +             // White_Space # Zl       LINE SEPARATOR
            "\\u2029" +             // White_Space # Zp       PARAGRAPH SEPARATOR
            "\\u202F" +             // White_Space # Zs       NARROW NO-BREAK SPACE
            "\\u205F" +             // White_Space # Zs       MEDIUM MATHEMATICAL SPACE
            "\\u3000" +             // White_Space # Zs       IDEOGRAPHIC SPACE
            "]";


    public static String trimSpaces(String text) {
        if (text == null)
            return null;
        else if (text.isEmpty())
            return "";
        else
            return text.replaceAll("^" + UNICODE_SPACES + "*", "").replaceAll(UNICODE_SPACES + "*$", "");
    }

    public static String getColorHexFromInt(int intColor) {
        return String.format("#%06X", 0xFFFFFF & intColor);
    }

    public static int getColorIntFromHex(String hexColor) {
        if (hexColor == null || hexColor.isEmpty())
            return 0;
        hexColor = hexColor.trim();
        if (hexColor.charAt(0) == '#')
            hexColor = hexColor.substring(1);
        return (Integer.parseInt(hexColor.substring( 0,2 ), 16) << 24) + Integer.parseInt(hexColor.substring( 2 ), 16);
    }

}
