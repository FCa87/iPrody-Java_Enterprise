package org.example.field;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum AddressField {
    ID("a_id", "a.id"),
    USERID("a_user_id", "a.user_id"),
    STREET("a_street", "a.street"),
    CITY("a_city", "a.city"),
    POSTALCODE("a_postal_code", "a.postal_code");

    private final String alias;
    private final String dbName;

    AddressField(String alias, String dbName) {
        this.alias = alias;
        this.dbName = dbName;
    }

    public String getAlias() {
        return alias;
    }

    public String getDbName() {
        return dbName;
    }

    public static String buildFieldsQuery() {
        return Arrays.stream(values())
                .map(field -> field.getDbName() + " as " + field.getAlias())
                .collect(Collectors.joining(","));
    }
}
