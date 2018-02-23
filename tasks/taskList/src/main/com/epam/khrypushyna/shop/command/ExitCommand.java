package com.epam.khrypushyna.shop.command;

import com.epam.khrypushyna.shop.CatalogManager;
import com.epam.khrypushyna.shop.repository.CatalogRepository;

public class ExitCommand implements Command {

    private CatalogRepository catalogRepository;
    private CatalogManager catalogManager;

    public ExitCommand(CatalogRepository catalogRepository, CatalogManager catalogManager) {
        this.catalogRepository = catalogRepository;
        this.catalogManager = catalogManager;
    }

    @Override
    public void execute() {
        catalogManager.save(catalogRepository);
        System.exit(0);
    }

    @Override
    public String commandName() {
        return "Exit program";
    }
}
