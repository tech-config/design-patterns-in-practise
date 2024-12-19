package com.design.patterns.commander.service.messaging;

import java.util.HashMap;
import java.util.Map;

public class MessagingDatabase {
    private final Map<String, MessagingService.MessageRequest> data = new HashMap<>();
}
