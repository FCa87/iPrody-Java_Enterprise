package org.example.dao;

import org.example.model.Device;
import org.example.model.DeviceConnection;
import org.example.model.Model;
import org.example.model.Network;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NetworkDao {

    public NetworkDao() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }


    public List<Network> getAllNetworks() throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.createStatement()) {
                var result = statement.executeQuery("select * from networks.networks");
                List<Network> networks = new ArrayList<>();
                while (result.next()) {
                    networks.add(toModel(result));
                }
                return networks;
            }
        }
    }


    public List<Network> getEmptyNetworks() throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.createStatement()) {
                var result = statement.executeQuery("select ns.id, ns.name, ns.description, ns.created_at from networks.networks ns left join networks.devices nd on nd.network_id = ns.id where nd.id is null");
                List<Network> networks = new ArrayList<>();
                while (result.next()) {
                    networks.add(toModel(result));
                }
                return networks;
            }
        }
    }


    public List<Device> getAllDevices() throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.createStatement()) {
                var result = statement.executeQuery("select * from networks.devices");
                List<Device> devices = new ArrayList<>();
                while (result.next()) {
                    devices.add(toDeviceModel(result));
                }
                return devices;
            }
        }
    }

    public List<DeviceConnection> getAllConnections() throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.createStatement()) {
                var result = statement.executeQuery("select * from networks.connections");
                List<DeviceConnection> connections = new ArrayList<>();
                while (result.next()) {
                    connections.add(toConnectionModel(result));
                }
                return connections;
            }
        }
    }

    public Network toModel(ResultSet resultSet) throws SQLException {
        return toModel(resultSet, new Network());
    }

    public Network toModel(ResultSet resultSet, Network network) throws SQLException {
        network.setId(resultSet.getLong("id"));
        network.setName(resultSet.getString("name"));
        network.setDescription(resultSet.getString("description"));
        network.setCreatedAt(resultSet.getTimestamp("created_at"));
        return network;
    }

    public Device toDeviceModel(ResultSet resultSet) throws SQLException {
        Device device = new Device();
        device.setId(resultSet.getLong("id"));
        device.setName(resultSet.getString("name"));
        device.setNetworkId(resultSet.getLong("network_id"));
        device.setIpAddress(resultSet.getString("ip_address"));
        device.setMacAddress(resultSet.getString("mac_address"));
        device.setStatus(resultSet.getString("status"));
        device.setType(resultSet.getString("type"));
        device.setCreatedAt(resultSet.getTimestamp("created_at"));
        return device;
    }

    public DeviceConnection toConnectionModel(ResultSet resultSet) throws SQLException {
        DeviceConnection deviceConnection = new DeviceConnection();
        deviceConnection.setId(resultSet.getLong("id"));
        deviceConnection.setDeviceToId(resultSet.getLong("device_to_id"));
        deviceConnection.setDeviceFromId(resultSet.getLong("device_from_id"));
        deviceConnection.setStatus(resultSet.getString("status"));
        deviceConnection.setType(resultSet.getString("type"));
        deviceConnection.setCreatedAt(resultSet.getTimestamp("created_at"));
        return deviceConnection;
    }

    public void remove(DeviceConnection deviceConnection) throws SQLException {
        remove(deviceConnection, "connections");
    }

    public void remove(Network network) throws SQLException {
        remove(network, "networks");
    }

    public void remove(Model model, String tableName) throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("delete from networks." + tableName + " where id = ?")) {
                statement.setLong(1, model.getId());
                statement.execute();
            }
        }
    }

    public Network save(Network network) throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("insert into networks.networks (name, description) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, network.getName());
                statement.setString(2, network.getDescription());
                statement.execute();

                var result = statement.getGeneratedKeys();
                result.next();
                network.setId(result.getLong("id"));
                network.setCreatedAt(result.getTimestamp("created_at"));
                return network;
            }
        }
    }

    public Network update(Network network) throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("update networks.networks set name=?,description=? where id=? returning id, name, description, created_at")) {
                statement.setString(1, network.getName());
                statement.setString(2, network.getDescription());
                statement.setLong(3, network.getId());
                var result = statement.executeQuery();

                result.next();
                return toModel(result, network);
            }
        }
    }

    public Device save(Device device) throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("insert into networks.devices (name, ip_address, mac_address, type, status, network_id) values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, device.getName());
                statement.setString(2, device.getIpAddress());
                statement.setString(3, device.getMacAddress());
                statement.setString(4, device.getType());
                statement.setString(5, device.getStatus());
                statement.setLong(6, device.getNetworkId());
                statement.execute();

                var result = statement.getGeneratedKeys();
                result.next();
                device.setId(result.getLong("id"));
                device.setCreatedAt(result.getTimestamp("created_at"));
                return device;
            }
        }
    }

    public DeviceConnection save(DeviceConnection deviceConnection) throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("insert into networks.connections (device_from_id, device_to_id, type, status) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, deviceConnection.getDeviceFromId());
                statement.setLong(2, deviceConnection.getDeviceToId());
                statement.setString(3, deviceConnection.getType());
                statement.setString(4, deviceConnection.getStatus());
                statement.execute();

                var result = statement.getGeneratedKeys();
                result.next();
                deviceConnection.setId(result.getLong("id"));
                deviceConnection.setCreatedAt(result.getTimestamp("created_at"));
                return deviceConnection;
            }
        }
    }

    private static Connection openConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/last_networks", "admin", "admin");
    }
}
