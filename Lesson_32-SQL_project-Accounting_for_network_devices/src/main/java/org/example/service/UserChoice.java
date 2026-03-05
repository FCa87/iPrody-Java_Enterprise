package org.example.service;

import java.util.Arrays;
import java.util.Optional;

public enum UserChoice {
    ADD_NETWORK(1, "to add network"),
    ADD_DEVICE(2, "to add device"),
    CONNECT_DEVICES(3, "to connect devices"),
    EDIT_NETWORK(4, "to edit networks"),
    REMOVE_CONNECTION(5, "to remove connection"),
    REMOVE_NETWORK(6, "to remove network"),
    EXIT(7, "to exit");


    private int code;
    private String description;

    UserChoice(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    public static Optional<UserChoice> valueOf(int code) {
        return Arrays.stream(values())
                .filter(userChoice -> userChoice.getCode() == code)
                .findAny();
    }
}
