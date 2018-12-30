package com.wa2c.ttm.base;

import com.wa2c.ttm.value.FormatType;

import java.io.*;
import java.nio.charset.Charset;

public abstract class TimedTextReaderBase {

    protected Charset charset = Charset.forName("UTF-8");

    public TimedTextReaderBase() {
    }

    public TimedTextReaderBase(String charsetName) {
        this(Charset.forName(charsetName));
    }

    public TimedTextReaderBase(Charset charset) {
        this.charset = charset;
    }



    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    // Data IO

    public TimedTextModel load(String path) throws IOException {
        return load(new File(path));
    }

    public TimedTextModel load(File file) throws IOException {
        try (FileInputStream stream = new FileInputStream(file)) {
            return load(stream);
        }
    }

    public TimedTextModel load(InputStream stream) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, charset))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            return parse(builder.toString());
        }
    }

    public abstract TimedTextModel parse(String text);

    public abstract long getTimeFromText(String time);

}
