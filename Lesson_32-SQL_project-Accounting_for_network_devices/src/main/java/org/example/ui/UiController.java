package org.example.ui;

import org.example.model.Device;
import org.example.model.DeviceConnection;
import org.example.model.Model;
import org.example.model.Network;
import org.example.service.UserChoice;

import java.io.PrintStream;
import java.sql.Timestamp;
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
    
    public String selectClassForTable() {
        String result;
        outStream.println("Enter the number of the table for getting statistics:");
        outStream.println("1 networks");
        outStream.println("2 devices");
        outStream.println("3 device connections");
        int code = Integer.MAX_VALUE;
        while (true){
            try{
                code = Integer.parseInt(scanner.nextLine());
                if (code >= 1 && code <= 3){
                    break;
                }
                outStream.println("Value was incorrect! Try again");
            } catch (Exception ex){
                outStream.println("Value was not a number! Try again");
            }
        }
        switch (code){
            case 1 -> result = "Network";
            case 2 -> result = "Device";
            case 3 -> result = "DeviceConnection";
            default -> throw new RuntimeException("Unexpected choice in selectTable");
        }
        return result;
    }
    
    public String selectField(List<String> fields, String nameOfChoice) {
        while (true) {
            String id = this.readString(nameOfChoice);
            for (var field : fields) {
                if (field.compareTo(id) == 0)
                    return field;
            }
            outStream.println("Name of field was incorrect! Try again");
        }
    }
    
    public String getValueForFilter(String requierdeTypeOfValue, String nameOfChoice) {
        String value;
        while (true) {
            value = this.readString(nameOfChoice);
            if (value.isEmpty()) continue;
            try{
                switch(requierdeTypeOfValue){
                    case "int" -> Integer.getInteger(value);
                    case "long" -> Long.getLong(value);
                    case "Timestamp" -> Timestamp.valueOf(value);
                }
                break;
            } catch (Exception ex){
                outStream.println("Value was incorrect; try again");
            } 
        }
        return value;
    }

    public Network edit(Network network) {
        network.setName(stringSizeValidation("name", 255));
        network.setDescription(readString("description"));
        return network;
    }

    public Network readNetwork() {
        return edit(new Network());
    }

    public DeviceConnection readConnection() {
        var type = stringSizeValidation("type", 50);
        var status = stringSizeValidation("status", 50);
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
    
    public void printSmth(String message) {
        outStream.println(message);
    }
    
    public Device readDevice() {
        var name = stringSizeValidation("name", 255);
        String ipAddress;
        do {
           ipAddress = stringSizeValidation("ip address", 15);
        } while (!ipFormatValidation(ipAddress));
        var macAddress = stringSizeValidation("mac address", 17);
        var type = stringSizeValidation("type", 50);
        var status = stringSizeValidation("status", 50);
        return new Device(name, ipAddress, macAddress, type, status);
    }
    
    private String stringSizeValidation(String nameOfField, int size){
        while (true){
            var result = readString(nameOfField);
            if (result.length() <= size){
                return result;
            } else {
                outStream.println("String must have no more than " + size + " symbols! Try again");
            }
        }
    }
    
    private boolean ipFormatValidation(String input){
       var inputMass = input.split("\\.");
       if (inputMass.length != 4){
           outStream.println("Incorrect format! Try again");
           return false;
       }
       for (int i = 0; i < 4; i++){
           try{
               var buf = Integer.parseInt(inputMass[i]);
               if ( buf < 0 || buf > 255){
                   outStream.println("IP address has incorrect range! Try again");
                   return false;
               }
           } catch (Exception ex){
               outStream.println("IP address must consist of numbers only! Try again");
               return false;
           }
       }
        return true;
    }
    
}
