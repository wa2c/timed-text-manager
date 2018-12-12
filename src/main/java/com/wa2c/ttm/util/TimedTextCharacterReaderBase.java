package com.wa2c.ttm.util;

import com.wa2c.ttm.base.TimedTextReaderBase;

public abstract class TimedTextCharacterReaderBase extends TimedTextReaderBase {
//
//    // Data IO
//
//    @Override
//    public void load(InputStream stream) throws IOException {
//        String charSetName = TimedTextUtil.analyzeEncoding(stream);
//        if (charSetName == null)
//            charSetName = "UTF-8";
//        load(stream, charSetName);
//    }
//
//    public void load(String path, String charsetName) throws IOException {
//        load(new File(path), charsetName);
//    }
//
//    public void load(File file, String charsetName) throws IOException {
//        try (FileInputStream stream = new FileInputStream(file)) {
//            load(stream, charsetName);
//        }
//    }
//
//    public void load(InputStream stream, String charsetName) throws IOException {
//        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, charsetName))) {
//            StringBuilder builder = new StringBuilder();
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                builder.append(line);
//            }
//            this.rawTimedText = builder.toString();
//            this.charsetName = charsetName;
//        }
//    }


}
