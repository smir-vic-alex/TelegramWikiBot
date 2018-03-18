package com.business;

import com.common.wiki.tgm.PropertiesService;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Конфиг настроек для бизнес модулля
 * Created by Виктор on 11.03.2018.
 */
public class BusinessConfig {

    private static final String INPUT_COMMAND_LIST_KEY = "command.list.input";
    private static final String COMMA = ",";
    private static final String COMMAND_KEY_PREFIX = "command.";

    private static final Map<String, String> COMMAND_MAP = createMap();

    private static Map<String, String> createMap() {
        PropertiesService service = PropertiesService.getInstance();
        String[] commands = service.get(INPUT_COMMAND_LIST_KEY).split(COMMA);

        return Arrays.stream(commands)
                .collect(toMap(
                        cmd -> cmd,
                        cmd -> service.get(COMMAND_KEY_PREFIX + cmd.substring(1))
                ));
    }

    public Map<String, String> getCommandsMap() {
        return COMMAND_MAP;
    }

    public static Integer getSeeAlsoListSize() {
        return Integer.parseInt(PropertiesService.getInstance().get("see.also.list.size"));
    }
}
