package org.example.ui;

import org.example.model.Device;
import org.example.model.DeviceConnection;
import org.example.model.Model;
import org.example.model.Network;
import org.example.service.UserChoice;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UiController {
    private final Scanner scanner;
    private final PrintStream outStream;


    public UiController(Scanner scanner, PrintStream outStream) {
        this.scanner = scanner;
        this.outStream = outStream;
    }


    public int getUserAction() {
        outStream.println("Enter: ");
        for (var choice : UserChoice.values()) {
            outStream.println(choice.getCode() + " " + choice.getDescription());
        }

        var code = scanner.nextInt();
        scanner.nextLine();
        return code;
    }

    public void print(Network network) {
        outStream.println(network);
    }

    public void print(Device device) {
        outStream.println(device);
    }

    public void print(DeviceConnection connection) {
        outStream.println(connection);
    }

    public <T extends Model> T selectOf(List<T> models, String nameOfChoice) {
        while (true) {
            outStream.println("Enter " + nameOfChoice + " id: ");
            for (var model : models) {
                outStream.println(model);
            }

            long id = readLong();
            for (var model : models) {
                if (model.getId() == id)
                    return model;
            }

            outStream.println("Id was incorrect; try again");
        }
    }

    public Network edit(Network network) {
        network.setName(readString("name"));
        network.setDescription(readString("description"));
        return network;
    }

    public Network readNetwork() {
        return edit(new Network());
    }

    public DeviceConnection readConnection() {
        var type = readString("type");
        var status = readString("status");
        return new DeviceConnection(type, status);
    }

    private String readString(String name) {
        outStream.println("Enter " + name + ":");
        return scanner.nextLine();
    }

    private long readLong() {
        long value = scanner.nextLong();
        scanner.nextLine();
        return value;
    }

    public void printError(String error) {
        outStream.println(error);
    }

    public void printInfo(String message) {
        outStream.println(message);
    }

    public Device readDevice() {
        var name = readString("name");
        var ipAddress = readString("ip address");
        var macAddress = readString("mac address");
        var type = readString("type");
        var status = readString("status");
        return new Device(name, ipAddress, macAddress, type, status);
    }
}
