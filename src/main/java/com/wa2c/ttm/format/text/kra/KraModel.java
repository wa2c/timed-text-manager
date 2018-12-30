package com.wa2c.ttm.format.text.kra;

import com.wa2c.ttm.base.TimedTextLine;
import com.wa2c.ttm.base.TimedTextModel;
import com.wa2c.ttm.value.FormatType;

import java.util.List;

public class KraModel extends TimedTextModel {

    private static final FormatType formatType = FormatType.KRA;

    public KraModel(List<TimedTextLine> timedTextLines) {
        super(timedTextLines);
    }

    @Override
    public FormatType getFormatType() {
        return formatType;
    }
}
