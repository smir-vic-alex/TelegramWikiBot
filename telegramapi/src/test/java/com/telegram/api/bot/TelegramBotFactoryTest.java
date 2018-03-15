package com.telegram.api.bot;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNotNull;


public class TelegramBotFactoryTest {

    @Mock
    private ApplicationContext applicationContext;
    @InjectMocks
    private TelegramBotFactory botFactory;

    @BeforeTest
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        when(applicationContext.getBean(TelegramBot.class)).thenReturn(new TelegramBot());
        assertNotNull(botFactory.getNewBot());
    }
}
