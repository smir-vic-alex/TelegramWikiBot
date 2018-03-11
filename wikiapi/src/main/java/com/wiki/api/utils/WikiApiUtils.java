package com.wiki.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.json.schema2pojo.dto.*;

import java.io.IOException;

/**
 * Сервис запросов в вики
 * Created by Виктор on 11.03.2018.
 */
public class WikiApiUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(WikiApiUtils.class);

    public static LinksRs mapJsonToLinksRs(String json) {
        try {
            return new ObjectMapper().readValue(json, LinksRs.class);
        } catch (IOException e) {
            LOGGER.error("Mapping Fail: mapJsonToLinksRs ", e);
            throw new RuntimeException(e);
        }
    }

    public static SearchByIdRs mapJsonToSearchByIdRs(String json) {
        try {
            return new ObjectMapper().readValue(json, SearchByIdRs.class);
        } catch (IOException e) {
            LOGGER.error("Mapping Fail: mapJsonToSearchByIdRs ", e);
            throw new RuntimeException(e);
        }
    }

    public static SearchByTitleRs mapJsonToSearchByTitleRs(String json) {
        try {
            return new ObjectMapper().readValue(json, SearchByTitleRs.class);
        } catch (IOException e) {
            LOGGER.error("Mapping Fail: mapJsonToSearchByTitleRs ", e);
            throw new RuntimeException(e);
        }
    }

    public static ContentsRs mapJsonToContentsRs(String json) {
        try {
            return new ObjectMapper().readValue(json, ContentsRs.class);
        } catch (IOException e) {
            LOGGER.error("Mapping Fail: mapJsonToContentsRs ", e);
            throw new RuntimeException(e);
        }
    }

    public static PageByPageIdRs mapToPageByPageIdRs(SearchByIdRs searchByIdRs, Integer id) {
        return new ObjectMapper().convertValue(searchByIdRs.getQuery().getPages().getAdditionalProperties().get(id.toString()), PageByPageIdRs.class);
    }
}
