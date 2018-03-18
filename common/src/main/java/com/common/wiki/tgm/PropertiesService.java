package com.common.wiki.tgm;

import com.common.wiki.tgm.interfaces.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import static java.util.stream.Collectors.toMap;

/**
 * Сервис настроек приложения
 * Created by Виктор on 11.03.2018.
 */
public class PropertiesService implements Service {

    private static Logger LOGGER = LoggerFactory.getLogger(BeanFactory.class);
    private static PropertiesService instance = new PropertiesService();
    private Map<String, String> map;

    private PropertiesService() {
        map = loadPropertiesFromFile();
    }

    private Map<String, String> loadPropertiesFromFile() {
        try (InputStream inputStream = PropertiesService.class.getResourceAsStream("/application.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.entrySet().stream()
                    .collect(toMap(
                            entry -> (String) entry.getKey(),
                            entry -> (String) entry.getValue()

                    ));
        } catch (Exception e) {
            LOGGER.error("Could not load properties ", e);
            return Collections.emptyMap();
        }
    }

    public static PropertiesService getInstance() {
        return instance;
    }

    public String get(String key) {
        return map.get(key);
    }
}
