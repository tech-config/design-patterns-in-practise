package com.design.patterns.commander.employeehandle;

import com.design.patterns.commander.Database;
import com.design.patterns.commander.service.Service;
import com.design.patterns.commander.Order;
import com.design.patterns.commander.exceptions.DatabaseUnavailableException;

public class EmployeeHandle extends Service {

    public EmployeeHandle(Database database, Exception... exceptionList) {
        super(database, exceptionList);
    }

    @Override
    public String receiveRequest(Object... parameters) throws DatabaseUnavailableException {
        return updateObject(parameters);
    }

    @Override
    public String updateObject(Object... parameters) throws DatabaseUnavailableException {
        var order = (Order) parameters[0];
        if (database.get(order.orderId) == null) {
            database.add(order);
            return order.orderId;
        }
        return null;
    }
}
