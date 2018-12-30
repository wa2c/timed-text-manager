package com.wa2c.ttm.format.text.lrc;

import com.wa2c.ttm.base.TimedTextLine;
import com.wa2c.ttm.base.TimedTextModel;
import com.wa2c.ttm.value.FormatType;

import java.util.List;

public class LrcModel extends TimedTextModel {

    private static final FormatType formatType = FormatType.LRC;

    public LrcModel() {
        super();
    }

    public LrcModel(List<TimedTextLine> timedTextLines) {
        super(timedTextLines);
    }

    @Override
    public FormatType getFormatType() {
        return formatType;
    }
}
