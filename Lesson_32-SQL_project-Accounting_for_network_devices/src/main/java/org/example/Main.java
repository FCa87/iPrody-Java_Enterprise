package org.example;

import org.example.dao.NetworkDao;
import org.example.service.NetworkService;
import org.example.ui.UiController;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        var uiController = new UiController(new Scanner(System.in), System.out);
        var networkDao = new NetworkDao();
        var service = new NetworkService(networkDao, uiController);
        service.process();
    }
}