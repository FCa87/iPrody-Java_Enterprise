package org.example.model;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Objects;

public class Device implements Model {
    private long id;
    private long network_id;
    private String name;
    private String ip_address;
    private String mac_address;
    private String type;
    private String status;
    private Timestamp created_at;

    public Device() {}

    public Device(String name, String ipAddress, String macAddress, String type, String status) {
        this.name = name;
        this.ip_address = ipAddress;
        this.mac_address = macAddress;
        this.type = type;
        this.status = status;
    }

    public Device(long id, String name, String ipAddress, String macAddress,
                  String type, String status, long networkId, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.ip_address = ipAddress;
        this.mac_address = macAddress;
        this.type = type;
        this.status = status;
        this.network_id = networkId;
        this.created_at = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ip_address;
    }

    public void setIpAddress(String ipAddress) {
        this.ip_address = ipAddress;
    }

    public String getMacAddress() {
        return mac_address;
    }

    public void setMacAddress(String macAddress) {
        this.mac_address = macAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getNetworkId() {
        return network_id;
    }

    public void setNetworkId(long networkId) {
        this.network_id = networkId;
    }

    public Timestamp getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.created_at = createdAt;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ipAddress='" + ip_address + '\'' +
                ", macAddress='" + mac_address + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", networkId=" + network_id +
                ", createdAt=" + created_at +
                '}';
    }


    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Device device = (Device) object;
        return id == device.id && network_id == device.network_id && Objects.equals(name, device.name) && Objects.equals(ip_address, device.ip_address) && Objects.equals(mac_address, device.mac_address) && Objects.equals(type, device.type) && Objects.equals(status, device.status) && Objects.equals(created_at, device.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ip_address, mac_address, type, status, network_id, created_at);
    }
    
    public static String getFields() {
        StringBuilder result = new StringBuilder();
        Field[] deviceFields = Device.class.getDeclaredFields();
        for (int i = 0; i < deviceFields.length - 1; i++) {
            result.append(deviceFields[i].getName() + ", ");
        }
        result.append(deviceFields[deviceFields.length - 1].getName());
        return result.toString();
    }
}
