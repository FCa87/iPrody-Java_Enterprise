package org.example.field;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum UserField {
    ID("u_id", "u.id"),
    NAME("u_name", "u.name"),
    EMAIL("u_email", "u.email");

    private final String alias;
    private final String dbName;

    UserField(String alias, String dbName) {
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
