package com.design.patterns.commander.employeehandle;

import com.design.patterns.commander.Database;
import com.design.patterns.commander.Order;
import com.design.patterns.commander.exceptions.DatabaseUnavailableException;

import java.util.HashMap;
import java.util.Map;

public class EmployeeDatabase extends Database<Order> {
    private final Map<String, Order> data = new HashMap<>();
    @Override
    public Order add(Order obj) throws DatabaseUnavailableException {
        return data.put(obj.orderId, obj);
    }

    @Override
    public Order get(String orderId) throws DatabaseUnavailableException {
        return data.get(orderId);
    }
}
