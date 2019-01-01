package com.wa2c.ttm.base;

import com.wa2c.ttm.value.FormatType;

import java.util.ArrayList;
import java.util.List;

public abstract class TimedTextModel {

    protected String text = "";
    protected byte[] rawData = null;

    protected String rawText = "";
    protected String plainText = "";

    protected long offset = 0;

    public TimedTextModel() {
        this.timedTextLineList = new ArrayList<>();
    }

    public TimedTextModel(List<TimedTextLine> timedTextLines) {
        this.timedTextLineList = timedTextLines;
    }


    protected List<TimedTextLine> timedTextLineList = new ArrayList<>();

    public abstract FormatType getFormatType();

    public String getRawText() {
        return this.rawText;
    }

    public String getPlainText() {
        return this.plainText;
    }


    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

}
