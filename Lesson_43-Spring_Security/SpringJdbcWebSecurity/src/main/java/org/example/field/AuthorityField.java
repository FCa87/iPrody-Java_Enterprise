package org.example.field;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum AuthorityField {
    ID("au_id", "au.id"),
    USERID("au_user_id", "au.user_id"),
    AUTHORITY("au_authority", "au.authority");

    private final String alias;
    private final String dbName;

    AuthorityField(String alias, String dbName) {
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
