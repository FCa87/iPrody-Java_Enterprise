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
    FIND_DEVICES_BY_FIELD(7, "to find devices by field"),
    FIND_NETWORKS_BY_FIELD(8, "to find networks by field"),
    SHOW_ALL_DEVICES_AND_CONNECTIONS(9, "to show all devices and connections between them"),
    SHOW_ALL_NETWORKS_AND_DEVICES(10, "to show all networks and devices"),
    STATISTICS(11, "to get statistics"),
    EXIT(12, "to exit");


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
