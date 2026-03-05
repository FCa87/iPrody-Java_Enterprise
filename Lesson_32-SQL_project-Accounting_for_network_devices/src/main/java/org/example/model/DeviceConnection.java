package org.example.model;

import java.sql.Timestamp;

public class DeviceConnection implements Model {
    private long id;
    private long device_from_id;
    private long device_to_id;
    private String connection_type;
    private String status;
    private Timestamp created_at;


    public DeviceConnection() {}

    public DeviceConnection(String type, String status) {
        this.connection_type = type;
        this.status = status;
    }

    public DeviceConnection(long id, long deviceFromId, long deviceToId, String type, String status, Timestamp createdAt) {
        this.id = id;
        this.device_from_id = deviceFromId;
        this.device_to_id = deviceToId;
        this.connection_type = type;
        this.status = status;
        this.created_at = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDeviceFromId() {
        return device_from_id;
    }

    public void setDeviceFromId(long deviceFromId) {
        this.device_from_id = deviceFromId;
    }

    public long getDeviceToId() {
        return device_to_id;
    }

    public void setDeviceToId(long deviceToId) {
        this.device_to_id = deviceToId;
    }

    public String getType() {
        return connection_type;
    }

    public void setType(String type) {
        this.connection_type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.created_at = createdAt;
    }

    @Override
    public String toString() {
        return "DeviceConnection{" +
                "id=" + id +
                ", deviceFromId=" + device_from_id +
                ", deviceToId=" + device_to_id +
                ", type='" + connection_type + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + created_at +
                '}';
    }
}
