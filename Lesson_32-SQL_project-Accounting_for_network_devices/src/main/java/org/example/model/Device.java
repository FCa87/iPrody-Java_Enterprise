package org.example.model;

import java.util.Date;
import java.util.Objects;

public class Device implements Model {
    private long id;
    private String name;
    private String ipAddress;
    private String macAddress;
    private String type;
    private String status;
    private long networkId;
    private Date createdAt;

    public Device() {}

    public Device(String name, String ipAddress, String macAddress, String type, String status) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.type = type;
        this.status = status;
    }

    public Device(long id, String name, String ipAddress, String macAddress,
                  String type, String status, long networkId, Date createdAt) {
        this.id = id;
        this.name = name;
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.type = type;
        this.status = status;
        this.networkId = networkId;
        this.createdAt = createdAt;
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
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
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
        return networkId;
    }

    public void setNetworkId(long networkId) {
        this.networkId = networkId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", networkId=" + networkId +
                ", createdAt=" + createdAt +
                '}';
    }


    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Device device = (Device) object;
        return id == device.id && networkId == device.networkId && Objects.equals(name, device.name) && Objects.equals(ipAddress, device.ipAddress) && Objects.equals(macAddress, device.macAddress) && Objects.equals(type, device.type) && Objects.equals(status, device.status) && Objects.equals(createdAt, device.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ipAddress, macAddress, type, status, networkId, createdAt);
    }
}
