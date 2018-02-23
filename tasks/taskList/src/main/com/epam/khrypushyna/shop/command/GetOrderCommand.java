package com.epam.khrypushyna.shop.command;

import com.epam.khrypushyna.shop.service.OrderService;
import com.epam.khrypushyna.shop.view.Writer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class GetOrderCommand implements Command {

    private OrderService orderService;
    private Writer writer;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");

    public GetOrderCommand(OrderService orderService, Writer writer) {
        this.orderService = orderService;
        this.writer = writer;
    }

    @Override
    public void execute() {
        Map<Date, Map<Integer, Integer>> orders = orderService.getAll();

        if (!orders.isEmpty()) {

            for (Map.Entry<Date, Map<Integer, Integer>> orderPair : orders.entrySet()) {

                Date date = orderPair.getKey();
                String dateString = format.format(date);

                Map<Integer, Integer> cartMap = orderPair.getValue();
                writer.write("Order " + dateString + " sum is " + orderService.countOrderSum(cartMap));
            }
        } else {
            writer.write("Order is empty");
        }
    }

    @Override
    public String commandName() {
        return "Show your order";
    }
}
