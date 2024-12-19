package com.design.patterns.commander.service.shipping;

import com.design.patterns.commander.Database;
import com.design.patterns.commander.exceptions.DatabaseUnavailableException;
import com.design.patterns.commander.service.Service;
import lombok.AllArgsConstructor;

public class ShippingService extends Service {

    @AllArgsConstructor
    static class ShippingRequest {
        String transactionId;
        String item;
        String address;
    }

    public ShippingService(Database database, Exception... exceptions) {
        super(database, exceptions);
    }

    @Override
    public String receiveRequest(Object... parameters) throws DatabaseUnavailableException {
        var id = generateId();
        var item = (String) parameters[0];
        var address = (String) parameters[1];
        var req = new ShippingRequest(id, item, address);
        return updateObject(req);
    }

    @Override
    public String updateObject(Object... parameters) throws DatabaseUnavailableException {
        var req = (ShippingRequest) parameters[0];
        if (this.database.get(req.transactionId) == null) {
            database.add(req);
            return req.transactionId;
        }
        return null;
    }
}
