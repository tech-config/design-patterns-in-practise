package com.design.patterns.commander.service.messaging;

import com.design.patterns.commander.Database;
import com.design.patterns.commander.service.Service;
import com.design.patterns.commander.exceptions.DatabaseUnavailableException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessagingService extends Service {
    enum MessageToSend {
        PAYMENT_FAIL,
        PAYMENT_TRYING,
        PAYMENT_SUCCESSFUL
    }

    record MessageRequest(String reqId, MessageToSend msg) {}

    public MessagingService(Database database, Exception... exceptionList) {
        super(database, exceptionList);
    }

    @Override
    public String receiveRequest(Object... parameters) throws DatabaseUnavailableException {
        var messageToSend = (int) parameters[0];
        var id = generateId();
        MessageToSend msg;
        if (messageToSend == 0) {
            msg = MessageToSend.PAYMENT_FAIL;
        } else if (messageToSend == 1) {
            msg = MessageToSend.PAYMENT_TRYING;
        } else {
            msg = MessageToSend.PAYMENT_SUCCESSFUL;
        }
        var req = new MessageRequest(id, msg);
        return updateObject(req);
    }

    @Override
    public String updateObject(Object... parameters) throws DatabaseUnavailableException {
        var req = (MessageRequest) parameters[0];
        if (this.database.get(req.reqId) == null) { //idempotence, in case db fails here
            database.add(req); //if successful:
            log.info(sendMessage(req.msg));
            return req.reqId;
        }
        return null;

    }

    String sendMessage(MessageToSend msg) {
        if (msg.equals(MessageToSend.PAYMENT_SUCCESSFUL)) {
            return "Msg: Your order has been placed and paid for successfully!"
                    + " Thank you for shopping with us!";
        } else if (msg.equals(MessageToSend.PAYMENT_TRYING)) {
            return "Msg: There was an error in your payment process,"
                    + " we are working on it and will return back to you shortly."
                    + " Meanwhile, your order has been placed and will be shipped.";
        } else {
            return "Msg: There was an error in your payment process."
                    + " Your order is placed and has been converted to COD."
                    + " Please reach us on CUSTOMER-CARE-NUMBER in case of any queries."
                    + " Thank you for shopping with us!";
        }
    }
}
