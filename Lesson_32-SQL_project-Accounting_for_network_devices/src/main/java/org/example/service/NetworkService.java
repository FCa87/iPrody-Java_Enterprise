package org.example.service;

import org.example.dao.NetworkDao;
import org.example.model.Network;
import org.example.ui.UiController;

import java.sql.SQLException;
import java.util.List;

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
            default -> uiController.printError("Unexpected code; contact administrator");
        }
    }
}
