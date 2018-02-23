package com.epam.khrypushyna.shop.command;

import com.epam.khrypushyna.shop.view.Writer;

import java.util.Map;

public class WelcomeCommand implements Command {

    private Writer writer;
    private Map<Integer, Command> container;

    public WelcomeCommand(Writer writer, Map<Integer, Command> commandMap) {
        this.writer = writer;
        this.container = commandMap;
    }

    @Override
    public void execute() {

        StringBuilder sb = new StringBuilder("You have an opportunity to choose one of the listed options:" + System.lineSeparator());
        for (Map.Entry pair : container.entrySet()) {

            Command command = (Command) pair.getValue();
            int key = (int) pair.getKey();
            if(key!=-1){
                sb.append("press '").append(key).append("' to call command ")
                        .append(command.commandName()).append(System.lineSeparator());
            }
        }
        writer.write(sb);
    }

    @Override
    public String commandName() {
        return "See this menu of options again";
    }
}
