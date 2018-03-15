package com.telegram.api.bot;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class BotMessageUtilsTest {

    @Test
    public void test() {
        assertNotNull(BotMessageUtils.fail(0L));
        assertNotNull(BotMessageUtils.fail(0L, 0));
        assertNotNull(BotMessageUtils.message(0L, "text"));
        assertNotNull(BotMessageUtils.message(0L, "text", new InlineKeyboardMarkup()));
        assertNotNull(BotMessageUtils.message(0L, 0, "text", new InlineKeyboardMarkup()));
    }
}
