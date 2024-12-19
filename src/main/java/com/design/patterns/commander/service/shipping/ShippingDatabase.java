package com.design.patterns.commander.service.shipping;

import com.design.patterns.commander.Database;

import java.util.Hashtable;
import java.util.Map;

public class ShippingDatabase extends Database<ShippingService.ShippingRequest> {

    private final Map<String, ShippingService.ShippingRequest> data = new Hashtable<>();

    @Override
    public ShippingService.ShippingRequest add(ShippingService.ShippingRequest r) {
        return data.put(r.transactionId, r);
    }

    public ShippingService.ShippingRequest get(String trasnactionId) {
        return data.get(trasnactionId);
    }
}
