package com.design.patterns.commander.service.queue;

import com.design.patterns.commander.Database;
import com.design.patterns.commander.exceptions.DatabaseUnavailableException;

import java.util.ArrayList;
import java.util.List;

public class QueueDatabase extends Database<QueueTask> {

    private final Queue<QueueTask> data;
    public List<Exception> exceptionList;

    public QueueDatabase(Exception... exceptions) {
        this.data = new Queue<>();
        this.exceptionList = new ArrayList<>(List.of(exceptions));
    }

    @Override
    public QueueTask add(QueueTask obj) throws DatabaseUnavailableException {
        return null;
    }

    @Override
    public QueueTask get(String id) throws DatabaseUnavailableException {
        return null;
    }
}
