package com.business;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class BusinessConfigTest {

    private BusinessConfig businessConfig;

    @BeforeTest
    public void init() {
        businessConfig = new BusinessConfig();
    }

    @Test
    public void test(){
        assertNotNull(businessConfig.getCommandsMap());
        businessConfig.getCommandsMap().put("test", "test");
        assertEquals(businessConfig.getCommandsMap().get("test"), "test");
    }
}
