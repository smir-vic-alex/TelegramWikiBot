package com.business.delegates;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class AnswerHelper {
    private static final String EMPTY = "";

    public static String clearHtmlTags(String text) {
        return Jsoup.clean(text, EMPTY, Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
    }

}
