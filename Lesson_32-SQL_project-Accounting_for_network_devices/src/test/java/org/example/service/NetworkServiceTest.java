package org.example.service;

import org.example.dao.NetworkDao;
import org.example.model.Network;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class NetworkServiceTest {

    @Test
    public void testSave() throws ClassNotFoundException, SQLException {
        NetworkDao networkDao = new NetworkDao();
        var network = new Network("first", "first_description");
        network = networkDao.save(network);
        assert network != null;
    }
}
