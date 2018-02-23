package com.epam.khrypushyna.shop.command;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private Map<Integer, Command> commandMap = new HashMap<>();

    public CommandContainer(Command... commands) {
        for (int i = 1; i <= commands.length; ) {
            commandMap.put(i, commands[--i]);
            i = i + 2;
        }
    }

    public Command getCommand(int i) {
        if (!commandMap.keySet().contains(i)) {
            return commandMap.get(-1);
        }
        return commandMap.get(i);
    }

    public void addCommand(int key, Command command) {
        commandMap.put(key, command);
    }

    public Map<Integer, Command> getCommandMap() {
        return Collections.unmodifiableMap(commandMap);
    }
}
