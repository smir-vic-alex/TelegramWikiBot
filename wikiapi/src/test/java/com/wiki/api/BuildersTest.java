package com.wiki.api;

import com.wiki.api.builders.PageContentByIdRqBuilder;
import com.wiki.api.builders.PageIdByTitleRqBuilder;
import com.wiki.api.builders.PageLinkByIdRqBuilder;
import com.wiki.api.builders.PageSectionsByIdRqBuilder;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class BuildersTest {

    @Test
    public void test() {
        assertNotNull(new PageSectionsByIdRqBuilder(0).build());
        assertNotNull(new PageLinkByIdRqBuilder(0, 0).build());
        assertNotNull(new PageIdByTitleRqBuilder("param").build());
        assertNotNull(new PageContentByIdRqBuilder(0).build());
    }
}
