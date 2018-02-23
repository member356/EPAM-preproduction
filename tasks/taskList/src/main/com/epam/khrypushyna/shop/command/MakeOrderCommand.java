package com.epam.khrypushyna.shop.command;

import com.epam.khrypushyna.shop.service.CartService;
import com.epam.khrypushyna.shop.service.OrderService;
import com.epam.khrypushyna.shop.view.Reader;
import com.epam.khrypushyna.shop.view.Writer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeOrderCommand implements Command {

    private OrderService orderService;
    private CartService cartService;
    private Writer writer;
    private Reader reader;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");

    public MakeOrderCommand(OrderService orderService, CartService cartService, Writer writer, Reader reader) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public void execute() {
        try {
            writer.write("Enter the order time in format yyyy.MM.dd HH:mm");
            String dateString = reader.readString();

            Date date = format.parse(dateString);

            orderService.add(date, cartService.getAll());
            cartService.removeAll();
            writer.write("The order is done");

        } catch (ParseException e) {
            writer.write("Exception during date parsing");
        }
    }

    @Override
    public String commandName() {
        return "Make an order";
    }
}
