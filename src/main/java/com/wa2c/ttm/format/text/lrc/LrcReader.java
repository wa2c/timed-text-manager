package com.wa2c.ttm.format.text.lrc;

import com.wa2c.ttm.base.TimedTextLine;
import com.wa2c.ttm.base.TimedTextReaderBase;
import com.wa2c.ttm.format.text.text.TextModel;
import com.wa2c.ttm.util.TimedTextUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LrcReader extends TimedTextReaderBase {

    @Override
    public LrcModel parse(String lyricsRawText) {

        final HashMap<String, String> metaInfoMap = new HashMap<>();

        // 正規表現パターン
        final String metaRegExp = "\\[([^\\d]+?):(.*?)\\]"; // メタタグ
        final String timeRegExp = "\\[(\\d+:\\d+(\\.\\d+)?)\\]"; // タイムタグ
        final String extTimeRegExp = "<(\\d+:\\d+(\\.\\d+)?)>"; // 行内タイムタグ(拡張タイムタグ)
        final String lineRegExp = "(?m)^\\s*?(" + timeRegExp + "(.*?))?$"; // 歌詞行 (空行含む)

        // フォーマットが正しいかどうか判定
        Matcher lineMatcher = Pattern.compile("(?m)^\\s*?" + timeRegExp).matcher(lyricsRawText);
        if (!lineMatcher.find())
            return null;

        // メタデータ取得
        lineMatcher = Pattern.compile(metaRegExp).matcher(lyricsRawText);
        while (lineMatcher.find()) {
            if (lineMatcher.groupCount() > 0) metaInfoMap.put(lineMatcher.group(1), lineMatcher.group(2));
        }

        // 歌詞データ取得
        ArrayList<TimedTextLine> lyricsLineList = new ArrayList<>();
        lineMatcher = Pattern.compile(lineRegExp).matcher(lyricsRawText.trim());
        boolean isLineSync = true;
        int emptyLineCount = 0;
        long lineMaxMilliseconds = 0;
        while (lineMatcher.find()) {
            TimedTextLine lyricsLine = new TimedTextLine();
            String line = "";
            if (StringUtils.isEmpty(lineMatcher.group().trim())) {
                // 空白のみ (タイムタグも無し)
                if (lyricsLineList.size() == 0)
                    continue; // 先頭の空白は除外
                lyricsLine.setBeginTime(lineMaxMilliseconds);
                lyricsLine.setText("");
                emptyLineCount++;
            } else {
                line = lineMatcher.group(4).trim();
                lyricsLine.setBeginTime(getTimeFromText(lineMatcher.group(2))); // 秒
                lyricsLine.setText( line.replaceAll(timeRegExp, "").replaceAll(extTimeRegExp, "") ); // 行テキスト(タイムタグ除去)
                if (TimedTextUtil.trimSpaces(lyricsLine.getText()).length() == 0) {
                    // 空行の場合は空文字とする
                    lyricsLine.setText("");
                }
                // 前の行が空行の場合
                if (emptyLineCount > 0) {
                    for (int i = 0; i < emptyLineCount; i++) {
                        TimedTextLine prevLine = lyricsLineList.get(lyricsLineList.size() - 1 - i);
                        if (isLineSync)
                            prevLine.setBeginTime(lyricsLine.getBeginTime()); // 行単位同期
                        else if (prevLine.getBeginTime() > lyricsLine.getBeginTime())
                            prevLine.setBeginTime(lyricsLine.getBeginTime()); // 空行が歌詞行よりも遅い
                        else
                            prevLine.setBeginTime(lineMaxMilliseconds);          // 空行が歌詞行よりも早い (通常)
                    }
                    emptyLineCount = 0;
                }
                lineMaxMilliseconds = lyricsLine.getBeginTime();
            }

            // 行内表示タイムテーブルの取得
            Matcher charMatcher;
            lyricsLine.setTimeTable(new TreeMap<Long, Integer>());
            lyricsLine.getTimeTable().put(lyricsLine.getBeginTime(), 0); // 先頭1つ目は必ず入れる
            if (lyricsLine.getText().length() > 0) {
                isLineSync = true;
                charMatcher = Pattern.compile(extTimeRegExp).matcher(line.replaceAll(timeRegExp, ""));
                int timeTagOffset = 0;
                while (charMatcher.find()) {
                    try {
                        long t = getTimeFromText(charMatcher.group(1));
                        lyricsLine.getTimeTable().put(t, charMatcher.start() - timeTagOffset);
                        timeTagOffset += charMatcher.group().length(); // タイムタグ文字数インデックスをずらす
                        lineMaxMilliseconds = Math.max(lineMaxMilliseconds, t); // 行の最大時間を取得
                        isLineSync = false;
                    } catch (NumberFormatException e) {
                        // 解析に失敗した場合は何もしない
                    }
                }
            }

            lyricsLineList.add(lyricsLine);

            // 繰り返しタグの取得 (行内に[xx:xx.xx]が存在)
            charMatcher = Pattern.compile(timeRegExp).matcher(line.replaceAll(extTimeRegExp, ""));
            while (charMatcher.find()) {
                try {
                    TimedTextLine l = new TimedTextLine();
                    l.setBeginTime(getTimeFromText(charMatcher.group(1)));
                    l.setText(lyricsLine.getText());
                    l.setTimeTable( new TreeMap<>(lyricsLine.getTimeTable()) );
                    lyricsLineList.add(l);
                } catch (NumberFormatException e) {
                    // 解析に失敗した場合は何もしない
                }
            }
        }

        // 前の行が空行の場合は、現在の時間を設定する
        if (emptyLineCount > 0) {
            for (int i = 0; i < emptyLineCount; i++) {
                TimedTextLine prevLine = lyricsLineList.get(lyricsLineList.size() - 1 - i);
                prevLine.setBeginTime(Long.MAX_VALUE);
            }
        }

        return new LrcModel(lyricsLineList);
    }



    /**
     * Get time from text
     * @param timeText 時間を表すテキスト。
     * @return テキストから変換したミリ秒。
     */
    @Override
    public long getTimeFromText(String timeText) {
        if (timeText == null)
            return 0;

        try {
            String[] timeTexts = timeText.trim().split("[:\\.]");
            long time = 0;

            if (timeTexts.length == 1) {
                // [ms]
                time = Long.parseLong(timeText);
            } else if (timeTexts.length == 2) {
                // [mm:ss]
                time = (long)(Float.parseFloat(timeTexts[1]) * 1000);
                time += Long.parseLong(timeTexts[0]) * 60 * 1000; // minutes
            } else if (timeTexts.length == 3) {
                // [mm:ss.ms] or [mm:ss:ms]
                time = (long)(Float.parseFloat(timeTexts[1] + "." + timeTexts[2]) * 1000); // seconds
                time += Long.parseLong(timeTexts[0]) * 60 * 1000; // minutes
            } else if (timeTexts.length == 4) {
                // [hh:mm:ss.ms] or [hh:mm:ss:ms]
                time = (long)(Float.parseFloat(timeTexts[2] + "." + timeTexts[3]) * 1000); // seconds
                time += Long.parseLong(timeTexts[1]) * 60 * 1000; // minutes
                time += Long.parseLong(timeTexts[0]) * 60* 60 * 1000; // hours
            }
            return time;
        } catch (NumberFormatException e) {
            return 0;
        }
    }


}
