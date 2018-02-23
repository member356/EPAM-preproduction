package com.epam.khrypushyna.shop.command;

public class CommandManager {

    private CommandContainer container;

    public CommandManager(CommandContainer commandContainer) {
        this.container = commandContainer;
    }

    public void setCommand(int i) {
        container.getCommand(i).execute();
    }
}
