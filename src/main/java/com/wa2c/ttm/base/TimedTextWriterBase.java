package com.wa2c.ttm.base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class TimedTextWriterBase {

    protected String charsetName = "UTF-8";

    public String getCharsetName() {
        return charsetName;
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }


    public void save(String path, TimedTextModel model) throws IOException {
        save(new File(path), model);
    }

    public void save(File file, TimedTextModel model) throws IOException {
        try (FileOutputStream stream = new FileOutputStream(file)) {
            save(stream, model);
        }
    }

    public abstract void save(OutputStream stream, TimedTextModel model) throws IOException;

}
