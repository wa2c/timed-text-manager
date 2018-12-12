package com.wa2c.ttm.format.text.text;

import com.wa2c.ttm.base.TimedTextLine;
import com.wa2c.ttm.base.TimedTextModel;
import com.wa2c.ttm.value.FormatType;

import java.util.List;

public class TextModel extends TimedTextModel {

    private static final FormatType formatType = FormatType.TEXT;

    public TextModel(List<TimedTextLine> timedTextLines) {
        super(timedTextLines);
    }

    @Override
    public FormatType getFormatType() {
        return formatType;
    }
}
