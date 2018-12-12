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

public class TimedTextUtil {


    public static TimedTextModel readModel(File file) {
        return null;
    }
    public static TimedTextModel readModel(InputStream stream) {
        FormatType type = analyzeFormatType(stream);

        return null;
    }




    public static FormatType analyzeFormatType(byte[] input) {
        if (input == null || input.length == 0)
            return null;

        return FormatType.TEXT;
    }

    public static FormatType analyzeFormatType(InputStream stream) {
        if (stream == null)
            return null;

        String encoding = analyzeEncoding(stream);


        return FormatType.TEXT;
    }


    /**
     * Analyze character encoding.
     * @param input Character bytes.
     * @return Encoding. Null if failed.
     */
    public static String analyzeEncoding(byte[] input) {
        if (input == null || input.length == 0)
            return null;

        try (ByteArrayInputStream stream = new ByteArrayInputStream(input)) {
            return analyzeEncoding(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Analyze character encoding.
     * @param stream Data stream.
     * @return Encoding. Null if failed.
     */
    public static String analyzeEncoding(InputStream stream) {
        UniversalDetector detector = new UniversalDetector(null);
        try {
            byte[] buf = new byte[4096];
            int nread;
            while ((nread = stream.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }
            detector.dataEnd();
            return detector.getDetectedCharset();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            detector.reset();
        }
    }

}
