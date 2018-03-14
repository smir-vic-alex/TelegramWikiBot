package com.admin.wiki.tlg.bot.rest;

import ru.json.schema2pojo.dto.DefaultAnswer;

/**
 * Created by Виктор on 15.03.2018.
 */
public class MessageUtils {

    private static final String SUCCESS = "success";
    private static final String DONE = "done";
    private static final String FAIL_MESSAGE = "fail";
    private static final String FAIL = "fail";

    public static DefaultAnswer success() {
        return getMessage(SUCCESS, DONE);
    }

    public static DefaultAnswer fail() {
        return getMessage(FAIL, FAIL_MESSAGE);
    }

    private static DefaultAnswer getMessage(String success, String message) {
        DefaultAnswer answer = new DefaultAnswer();
        answer.setSuccess(success);
        answer.setMessage(message);
        return answer;
    }
}
