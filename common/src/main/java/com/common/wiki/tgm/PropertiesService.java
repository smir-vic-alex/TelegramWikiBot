package com.common.wiki.tgm;

import com.common.wiki.tgm.interfaces.Service;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Сервис настроек приложения
 * Created by Виктор on 11.03.2018.
 */
public class PropertiesService implements Service {
    private Map<String, String> map;
    private static PropertiesService instance = new PropertiesService();

    private PropertiesService() {
        map = loadPropertiesFromFile();
    }

    private Map<String, String> loadPropertiesFromFile() {
        try (InputStream inputStream = PropertiesService.class.getResourceAsStream("/application.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            Map<String, String> map = new HashMap<>();
            for (Map.Entry entry : properties.entrySet()) {
                map.put((String) entry.getKey(), (String) entry.getValue());
            }
            return map;
        } catch (Exception e) {
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
