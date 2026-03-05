package org.example.service;

import java.lang.reflect.Field;
import org.example.dao.NetworkDao;
import org.example.model.Network;
import org.example.ui.UiController;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.example.model.Device;
import static org.example.service.UserChoice.FIND_DEVICES_BY_FIELD;

public class NetworkService {
    private final NetworkDao networkDao;
    private final UiController uiController;

    public NetworkService(NetworkDao networkDao, UiController uiController) {
        this.networkDao = networkDao;
        this.uiController = uiController;
    }


    public void process() {
        while (true) {
            int code = uiController.getUserAction();
            var userChoice = UserChoice.valueOf(code);
            if (userChoice.isEmpty()) {
                System.out.println("code was incorrect; try again");
                continue;
            }

            var choice = userChoice.get();
            if (choice == UserChoice.EXIT)
                break;

            try {
                processChoice(choice);
            } catch (SQLException | UserErrorException e) {
                uiController.printError(e.getMessage());
            }
        }
    }

    private void processChoice(UserChoice userChoice) throws SQLException, UserErrorException {
        switch (userChoice) {
            case ADD_NETWORK -> {
                var network = uiController.readNetwork();
                network = networkDao.save(network);
                uiController.print(network);
            }
            case ADD_DEVICE -> {
                List<Network> networks = networkDao.getAllNetworks();
                if (networks.isEmpty())
                    throw new UserErrorException("Networks not found; add them first");


                var networkToConnect = uiController.selectOf(networks, "network to connect");
                var device = uiController.readDevice();
                device.setNetworkId(networkToConnect.getId());
                device = networkDao.save(device);
                uiController.print(device);
            }
            case CONNECT_DEVICES -> {
                var allDevices = networkDao.getAllDevices();
                if (allDevices.size() < 2)
                    throw new UserErrorException("2 devices not found; add them first");

                var deviceFrom = uiController.selectOf(allDevices, "device from");
                var devicesWithoutSelected = allDevices.stream()
                        .filter(device -> !device.equals(deviceFrom))
                        .toList();
                var deviceTo = uiController.selectOf(devicesWithoutSelected, "device to");
                var deviceConnection = uiController.readConnection();
                deviceConnection.setDeviceFromId(deviceFrom.getId());
                deviceConnection.setDeviceToId(deviceTo.getId());
                deviceConnection = networkDao.save(deviceConnection);
                uiController.print(deviceConnection);
            }
            case EDIT_NETWORK -> {
                List<Network> networks = networkDao.getAllNetworks();
                if (networks.isEmpty())
                    throw new UserErrorException("Networks not found; add them first");

                var networkToEdit = uiController.selectOf(networks, "network to connect");
                networkToEdit = uiController.edit(networkToEdit);
                networkToEdit = networkDao.update(networkToEdit);
                uiController.print(networkToEdit);
            }
            case REMOVE_CONNECTION -> {
                var allDeviceConnections = networkDao.getAllConnections();
                var connectionToRemove = uiController.selectOf(allDeviceConnections, "connection to remove");
                networkDao.remove(connectionToRemove);
                uiController.printInfo("Connection " + connectionToRemove + " successfully removed");
            }
            case REMOVE_NETWORK -> {
                var allNetworks = networkDao.getEmptyNetworks();
                var networkToRemove = uiController.selectOf(allNetworks, "network to remove");
                networkDao.remove(networkToRemove);
                uiController.printInfo("Network " + networkToRemove + " successfully removed");
            }
            case FIND_DEVICES_BY_FIELD -> {
                var deviceFields = Stream.of(Device.class.getDeclaredFields()).map(field -> field.getName()).toList();
                uiController.printInfo("Device has fields: " + Arrays.toString(deviceFields.toArray()));
                var fieldForFind = uiController.selectField(deviceFields, "field for filtering");
                try {
                    var fieldType = Device.class.getDeclaredField(fieldForFind).getType().getSimpleName();
                    var valueForFind = uiController.getValueForFilter(fieldType, "value for filtering");
                    var filteredDevices = networkDao.filterDevice(fieldForFind, fieldType,valueForFind);
                    if (filteredDevices.isEmpty()){
                        uiController.printInfo("There is no device with such a field");
                    }
                        filteredDevices.forEach(uiController::print);
                } catch (NoSuchFieldException ex) {
                    throw new RuntimeException("No such field in FIND_DEVICES_BY_FIELD");
                }
            }
            case FIND_NETWORKS_BY_FIELD -> {
                var networkFields = Stream.of(Network.class.getDeclaredFields()).map(field -> field.getName()).toList();
                uiController.printInfo("Network has fields: " + Arrays.toString(networkFields.toArray()));
                var fieldForFind = uiController.selectField(networkFields, "field for filtering");
                try {
                    var fieldType = Device.class.getDeclaredField(fieldForFind).getType().getSimpleName();
                    var valueForFind = uiController.getValueForFilter(fieldType, "value for filtering");
                    var filteredDevices = networkDao.filterNetwork(fieldForFind, fieldType,valueForFind);
                    if (filteredDevices.isEmpty()){
                        uiController.printInfo("There is no network with such a field");
                    }
                        filteredDevices.forEach(uiController::print);
                } catch (NoSuchFieldException ex) {
                    throw new RuntimeException("No such field in FIND_NETWORKS_BY_FIELD");
                }
            }
            case SHOW_ALL_DEVICES_AND_CONNECTIONS -> {
                networkDao.showDevicesAndConnections().forEach(uiController::printInfo);
            }
            case SHOW_ALL_NETWORKS_AND_DEVICES -> {
                networkDao.showNetworksAndDevices().forEach(uiController::printInfo);
            }
            case STATISTICS -> {
                try {
                    var classForTable = uiController.selectClassForTable();
                    var classFields = Stream.of(Class.forName("org.example.model." + classForTable).getDeclaredFields()).map(field -> field.getName()).toList();
                    uiController.printInfo(classForTable + " has fields: " + Arrays.toString(classFields.toArray()));
                    var fieldForFind = uiController.selectField(classFields, "field for filtering");
                    try {
                        var fieldType = Class.forName("org.example.model." + classForTable).getDeclaredField(fieldForFind).getType().getSimpleName();
                        var valueForFind = uiController.getValueForFilter(fieldType, "value for filtering");
                        var filteredStrings = networkDao.filter(classForTable, fieldForFind, fieldType, valueForFind);
                        if (!filteredStrings.isEmpty()){
                            filteredStrings.forEach(uiController::printInfo);
                            uiController.printInfo("Found " + filteredStrings.size() + " suitable " + classForTable.toLowerCase() + "s.");
                        } else {
                            uiController.printInfo("Found no suitable " + classForTable.toLowerCase() + ".");
                        }
                    } catch (NoSuchFieldException ex) {
                        throw new RuntimeException("No such field in STATISTICS");
                    }
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException("No such class for proccess in STATISTICS");
                }
            }
            default -> uiController.printError("Unexpected code; contact administrator");
        }
    }
}
