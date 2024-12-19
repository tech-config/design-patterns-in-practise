package com.design.patterns.commander.service.payment;

import com.design.patterns.commander.Database;
import com.design.patterns.commander.exceptions.DatabaseUnavailableException;

import java.util.Hashtable;
import java.util.Map;

public class PaymentDatabase extends Database<PaymentService.PaymentRequest> {
    private final Map<String, PaymentService.PaymentRequest> data = new Hashtable<>();

    @Override
    public PaymentService.PaymentRequest add(PaymentService.PaymentRequest paymentRequest) throws DatabaseUnavailableException {
        return data.put(paymentRequest.transactionId, paymentRequest);
    }

    @Override
    public PaymentService.PaymentRequest get(String requestId) throws DatabaseUnavailableException {
        return data.get(requestId);
    }
}
