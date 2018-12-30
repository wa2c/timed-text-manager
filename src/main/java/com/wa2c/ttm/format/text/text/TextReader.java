package com.wa2c.ttm.format.text.text;

import com.wa2c.ttm.base.TimedTextReaderBase;

import java.io.IOException;
import java.io.InputStream;

public class TextReader extends TimedTextReaderBase {

    @Override
    public TextModel parse(String text) {
        TextModel model = new TextModel(null);
        return model;
    }
}
