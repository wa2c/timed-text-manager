package com.wa2c.ttm.base;

import java.util.List;
import java.util.TreeMap;

public class TimedTextLine {

    private String text;
    private long beginTime;
    private long endTime;
    private List<TimedTextPhrase> timedTextPhraseList;
    private TreeMap<Long, Integer> timeTable;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public List<TimedTextPhrase> getTimedTextPhraseList() {
        return timedTextPhraseList;
    }

    public void setTimedTextPhraseList(List<TimedTextPhrase> timedTextPhraseList) {
        this.timedTextPhraseList = timedTextPhraseList;
    }

    public TreeMap<Long, Integer> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TreeMap<Long, Integer> timeTable) {
        this.timeTable = timeTable;
    }


    public boolean isEmpty() {
        return (text == null || text.length() == 0);
    }

    public boolean isBlankLine() {
        return (isEmpty() || text.trim().length() == 0);
    }
}
