package com.wiki.api;

import com.fasterxml.jackson.core.JsonEncoding;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Executor запросов в Вики
 * Created by Виктор on 10.03.2018.
 */
public class WikiExecutor<T> {

    private static final String FAIL_MSG = "WikiExecutor fail";
    private static Logger LOGGER = LoggerFactory.getLogger(WikiExecutor.class);

    public T execute(String params, ResponseMapper<T> mapper) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost postMethod = new HttpPost(getUri(handleDataToValid(params)));
            HttpResponse rawResponse = httpClient.execute(postMethod);
            String rs = EntityUtils.toString(rawResponse.getEntity(), JsonEncoding.UTF8.getJavaName());
            LOGGER.debug(rs);

            return mapper.mapResponse(rs);
        } catch (Throwable e) {
            LOGGER.error(FAIL_MSG, e);
            throw new WkiApiException(FAIL_MSG, e);
        }
    }

    private String getUri(String params) {
        return WikiApiConfig.getBaseUrl() + params;
    }

    private String handleDataToValid(String params) {
        return params.replace("|", "%7C");
    }
}
