package org.example.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.example.dao.NetworkDao;
import org.example.model.Network;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Scanner;
import org.example.ui.UiController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NetworkServiceTest {

    @Test
    @Order(1)
    public void testAddDeviceNoNetwork() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException {
        var uiController = new UiController(new Scanner(System.in), System.out);
        var networkDao = new NetworkDao();
        var service = new NetworkService(networkDao, uiController);
        if (networkDao.getAllNetworks().isEmpty()) {
            Method testMethod = NetworkService.class.getDeclaredMethod("processChoice", UserChoice.class);
            testMethod.setAccessible(true);
            var throwedException = Assertions.assertThrowsExactly(UserErrorException.class, () -> testMethod.invoke(service, UserChoice.ADD_DEVICE), "UserErrorException expected");
            Assertions.assertEquals("Networks not found; add them first", throwedException.getMessage());
        } else {
            Assertions.fail("Networkz table is not empty");
        }
    }

    @Test
    @Order(2)
    public void testSave() throws ClassNotFoundException, SQLException {
        NetworkDao networkDao = new NetworkDao();
        var network = new Network("first", "first_description");
        network = networkDao.save(network);
        assert network != null;
    }

}
