package com.design.patterns.commander.service.queue;

import com.design.patterns.commander.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class QueueTask {

    public enum TaskType {
        MESSAGING,
        PAYMENT,
        EMPLOYEE_DB
    }

    public final Order order;
    public final TaskType taskType;
    public final int messageType;

    @Getter
    @Setter
    private long firstAttemptTime = -1L;

    public String getType() {
        if (!taskType.equals(TaskType.MESSAGING)) {
            return taskType.toString();
        } else {
            if (messageType == 0) {
                return "Payment Failure Message";
            } else if (messageType == 1) {
                return "Payment Error Message";
            } else {
                return "Payment Success Message";
            }
        }
    }

    public boolean isFirstAttempt() {
        return firstAttemptTime == -1L;
    }
}


