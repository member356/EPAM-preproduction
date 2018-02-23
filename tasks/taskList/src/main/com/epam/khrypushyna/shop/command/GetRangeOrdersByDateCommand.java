package com.epam.khrypushyna.shop.command;

import com.epam.khrypushyna.shop.service.OrderService;
import com.epam.khrypushyna.shop.view.Reader;
import com.epam.khrypushyna.shop.view.Writer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetRangeOrdersByDateCommand implements Command {

    private Writer writer;
    private Reader reader;
    private OrderService orderService;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");

    public GetRangeOrdersByDateCommand(Writer writer, Reader reader, OrderService orderService) {
        this.writer = writer;
        this.reader = reader;
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        try {
            writer.write("Enter date from in format yyyy.MM.dd HH:mm");
            String from = reader.readString();
            writer.write("Enter date to in format yyyy.MM.dd HH:mm");
            String to = reader.readString();

            Date dateFrom = format.parse(from);
            Date dateTo = format.parse(to);

            writer.write(orderService.getOrdersBetweenDates(dateFrom, dateTo));

        } catch (ParseException e) {
            writer.write("Exception during date parsing");
        }
    }

    @Override
    public String commandName() {
        return "Get range orders by date";
    }
}
