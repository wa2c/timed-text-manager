package com.wa2c.ttm.base;

public class TimedTextPhrase {
    private int beginIndex;
    private int endIndex;
    private long beginMillis;
    private long endMillis;

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public long getBeginMillis() {
        return beginMillis;
    }

    public void setBeginMillis(long beginMillis) {
        this.beginMillis = beginMillis;
    }

    public long getEndMillis() {
        return endMillis;
    }

    public void setEndMillis(long endMillis) {
        this.endMillis = endMillis;
    }
}
