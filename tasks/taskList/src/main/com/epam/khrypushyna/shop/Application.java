package com.epam.khrypushyna.shop;

import com.epam.khrypushyna.shop.command.AddProductToCartCommand;
import com.epam.khrypushyna.shop.command.AddToCatalogCommand;
import com.epam.khrypushyna.shop.command.AddToCatalogReflectionCommand;
import com.epam.khrypushyna.shop.command.CartCacheCommand;
import com.epam.khrypushyna.shop.creator.mode.ModeManager;
import com.epam.khrypushyna.shop.command.Command;
import com.epam.khrypushyna.shop.command.CommandContainer;
import com.epam.khrypushyna.shop.command.CommandManager;
import com.epam.khrypushyna.shop.command.ExitCommand;
import com.epam.khrypushyna.shop.command.GetCartCommand;
import com.epam.khrypushyna.shop.command.GetCatalogCommand;
import com.epam.khrypushyna.shop.command.GetClosestOrderByDateCommand;
import com.epam.khrypushyna.shop.command.GetOrderCommand;
import com.epam.khrypushyna.shop.command.GetRangeOrdersByDateCommand;
import com.epam.khrypushyna.shop.command.MakeOrderCommand;
import com.epam.khrypushyna.shop.command.WelcomeCommand;
import com.epam.khrypushyna.shop.creator.mode.Mode;
import com.epam.khrypushyna.shop.repository.CartRepository;
import com.epam.khrypushyna.shop.repository.CartRepositoryImpl;
import com.epam.khrypushyna.shop.repository.CatalogRepository;
import com.epam.khrypushyna.shop.repository.OrderRepository;
import com.epam.khrypushyna.shop.repository.OrderRepositoryImpl;
import com.epam.khrypushyna.shop.server.ServerStart;
import com.epam.khrypushyna.shop.server.factory.HttpHandlerFactory;
import com.epam.khrypushyna.shop.server.factory.TCPHandlerFactory;
import com.epam.khrypushyna.shop.service.CartCacheService;
import com.epam.khrypushyna.shop.service.CartCacheServiceImpl;
import com.epam.khrypushyna.shop.service.CartService;
import com.epam.khrypushyna.shop.service.CartServiceImpl;
import com.epam.khrypushyna.shop.service.CatalogService;
import com.epam.khrypushyna.shop.service.CatalogServiceImpl;
import com.epam.khrypushyna.shop.service.OrderService;
import com.epam.khrypushyna.shop.service.OrderServiceImpl;
import com.epam.khrypushyna.shop.view.Reader;
import com.epam.khrypushyna.shop.view.ReaderImpl;
import com.epam.khrypushyna.shop.view.Writer;
import com.epam.khrypushyna.shop.view.WriterImpl;

import java.util.ResourceBundle;

public class Application {
    private static final int PORT_HTTP = 8080;
    private static final int PORT_TCP = 8081;

    private Reader reader;
    private Writer writer;
    private CommandManager commandManager;

    private Application() {

        reader = new ReaderImpl(System.in);
        writer = new WriterImpl(System.out);

        CatalogManager catalogManager = new CatalogManager();

        CatalogRepository catalogRepository = catalogManager.load();
        CatalogService catalogService = new CatalogServiceImpl(catalogRepository, catalogManager);

        ServerStart serverStart = new ServerStart(catalogService);
        serverStart.run(PORT_HTTP, new HttpHandlerFactory());
        serverStart.run(PORT_TCP, new TCPHandlerFactory());

        Mode mode = new ModeManager(reader, writer).execute();
        ResourceBundle bundle = new LanguageManager(reader, writer).execute();

        CartCacheService cacheService = new CartCacheServiceImpl();
        CartRepository cartRepository = new CartRepositoryImpl();
        CartService cartService = new CartServiceImpl(cartRepository, cacheService);

        OrderRepository orderRepository = new OrderRepositoryImpl();
        OrderService orderService = new OrderServiceImpl(orderRepository, catalogService);

        Command getCatalogCommand = new GetCatalogCommand(catalogService, writer);
        Command addProductToCartCommand = new AddProductToCartCommand(cartService, catalogService, reader, writer);
        Command getCartCommand = new GetCartCommand(cartService, writer);
        Command cartCacheCommand = new CartCacheCommand(cacheService, writer);
        Command makeOrderCommand = new MakeOrderCommand(orderService, cartService, writer, reader);
        Command getOrderCommand = new GetOrderCommand(orderService, writer);
        Command getRangeOrdersByDate = new GetRangeOrdersByDateCommand(writer, reader, orderService);
        Command getClosestOrderByDate = new GetClosestOrderByDateCommand(writer, reader, orderService);
        Command addToCatalogCommand = new AddToCatalogCommand(catalogService, mode, reader, writer);
        Command addToCatalogReflectionCommand = new AddToCatalogReflectionCommand(catalogService, mode, reader, writer, bundle);
        Command exitCommand = new ExitCommand(catalogRepository, catalogManager);

        CommandContainer commandContainer = new CommandContainer(getCatalogCommand, addProductToCartCommand,
                getCartCommand, cartCacheCommand, makeOrderCommand, getOrderCommand, getRangeOrdersByDate,
                getClosestOrderByDate, addToCatalogCommand, addToCatalogReflectionCommand, exitCommand);

        Command welcomeCommand = new WelcomeCommand(writer, commandContainer.getCommandMap());
        commandContainer.addCommand(0, welcomeCommand);
        commandContainer.addCommand(-1, welcomeCommand);

        commandManager = new CommandManager(commandContainer);
    }

    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        while (true) {
            try {
                commandManager.setCommand(-1);
                commandManager.setCommand(Integer.parseInt(reader.readString()));
                writer.write("");
            } catch (NumberFormatException e) {
                writer.write("Enter a key number");
            }
        }
    }
}
