package com.design.patterns.commander.service.payment;

import com.design.patterns.commander.Database;
import com.design.patterns.commander.exceptions.DatabaseUnavailableException;
import com.design.patterns.commander.service.Service;
import lombok.RequiredArgsConstructor;

public class PaymentService extends Service {

    @RequiredArgsConstructor
    static class PaymentRequest {
        final String transactionId;
        final float payment;
        boolean paid;
    }

    public PaymentService(Database database, Exception... exceptionList) {
        super(database, exceptionList);
    }

    @Override
    public String receiveRequest(Object... parameters) throws DatabaseUnavailableException {
        var id = generateId();
        var req = new PaymentRequest(id, (float) parameters[0]);
        return updateObject(req);
    }

    @Override
    public String updateObject(Object... parameters) throws DatabaseUnavailableException {
        var req = (PaymentRequest) parameters[0];
        if (database.get(req.transactionId) == null || !req.paid) {
            database.add(req);
            req.paid = true;
            return req.transactionId;
        }
        return null;
    }
}
