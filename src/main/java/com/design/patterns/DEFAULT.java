package com.design.patterns;

import java.util.Locale;

public interface DEFAULT {

    String getItems();

    default String messageItems() {
        return getItems().toLowerCase(Locale.ROOT);
    }
}
