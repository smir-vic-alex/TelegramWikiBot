package com.business;

import com.common.wiki.tgm.PropertiesService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Конфиг настроек для бизнес модулля
 * Created by Виктор on 11.03.2018.
 */
public class BusinessConfig {

    private static final String COMMAND_LIST_KEY = "command.list";
    private static final String INPUT_COMMAND_LIST_KEY = "command.list.input";
    private static final String COMMA = ",";
    private static final String SLASH = "/";
    private static final String COMMAND_KEY_PREFIX = "command.";

    private static final List<String> INPUT_COMMANDS = Arrays.asList(PropertiesService.getInstance().get(INPUT_COMMAND_LIST_KEY).split(COMMA));

    private static final Map<String, String> COMMAND_MAP = createMap();

    private static Map<String, String> createMap() {
        String[] commands = PropertiesService.getInstance().get(COMMAND_LIST_KEY).split(COMMA);
        Map<String, String> map = new HashMap<>(commands.length);
        for (String cmd : commands) {
            map.put(SLASH + cmd, PropertiesService.getInstance().get(COMMAND_KEY_PREFIX + cmd));
        }
        return map;
    }

    public static Map<String, String> getCommandsMap() {
        return COMMAND_MAP;
    }

    public static List<String> getInputCommands() {
        return INPUT_COMMANDS;
    }

}
