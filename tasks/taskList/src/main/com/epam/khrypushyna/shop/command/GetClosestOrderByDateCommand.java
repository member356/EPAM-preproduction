package com.epam.khrypushyna.shop.command;

import com.epam.khrypushyna.shop.service.OrderService;
import com.epam.khrypushyna.shop.view.Reader;
import com.epam.khrypushyna.shop.view.Writer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class GetClosestOrderByDateCommand implements Command {

    private Writer writer;
    private Reader reader;
    private OrderService orderService;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");

    public GetClosestOrderByDateCommand(Writer writer, Reader reader, OrderService orderService) {
        this.writer = writer;
        this.reader = reader;
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        try {
            writer.write("Enter date for closest order searching in format yyyy.MM.dd HH:mm");
            Map.Entry pair = orderService.getClosestOrder(format.parse(reader.readString()));
            writer.write(pair);

        } catch (ParseException e) {
            writer.write("Exception during date parsing");
        }
    }

    @Override
    public String commandName() {
        return "Get closest order by date";
    }
}
