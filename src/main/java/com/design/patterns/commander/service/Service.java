package com.design.patterns.commander.service;

import com.design.patterns.commander.Database;
import com.design.patterns.commander.exceptions.DatabaseUnavailableException;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public abstract class Service {

    protected final Database database;
    private List<Exception> exceptionList;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final Hashtable<String, Boolean> USED_IDS = new Hashtable<>();

    public Service(Database database, Exception... exceptionList) {
        this.database = database;
        this.exceptionList = new ArrayList<>(List.of(exceptionList));
    }

    public abstract String receiveRequest(Object... parameters) throws DatabaseUnavailableException;

    public abstract String updateObject(Object... parameters) throws DatabaseUnavailableException;

    protected String generateId() {
        StringBuilder random = new StringBuilder();
        while (random.length() < 12) {
            int index = (int) (RANDOM.nextFloat() * ALL_CHARS.length());
            random.append(ALL_CHARS.charAt(index));
        }
        String id = random.toString();
        if (USED_IDS.get(id) != null) {
            while (USED_IDS.get(id)) {
                id = generateId();
            }
        }
        return id;
    }
}
