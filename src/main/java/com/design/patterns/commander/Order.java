package com.design.patterns.commander;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;


public class Order {
    enum PaymentStatus {
        NOT_DONE,
        TRYING,
        DONE
    }

    enum MessageSent {
        NONE_SENT,
        PAYMENT_FAIL,
        PAYMENT_TRYING,
        PAYMENT_SUCCESSFUL
    }

    final User user;
    final String item;
    public final String orderId;
    final float price;
    final long createdTime;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final Map<String, Boolean> USER_IDS = new HashMap<>();
    private static final String ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    PaymentStatus paymentStatus;
    MessageSent messageSent;
    boolean addToEmployeeHandle;

    public Order(User user, String item, float price) {
        this.createdTime = System.currentTimeMillis();
        this.user = user;
        this.item = item;
        this.price = price;
        String orderId = createUniqueId();
        if (USER_IDS.get(orderId) != null) {
            while (USER_IDS.get(orderId)) {
                orderId = createUniqueId();
            }
        }
        this.orderId = orderId;
        USER_IDS.put(this.orderId, true);
        this.paymentStatus = PaymentStatus.TRYING;
        this.messageSent = MessageSent.NONE_SENT;
        this.addToEmployeeHandle = false;
    }

    private String createUniqueId() {
        StringBuilder random = new StringBuilder();
        while (random.length() < 12) { // length of the random string.
            int index = (int) (RANDOM.nextFloat() * ALL_CHARS.length());
            random.append(ALL_CHARS.charAt(index));
        }
        return random.toString();
    }
}
