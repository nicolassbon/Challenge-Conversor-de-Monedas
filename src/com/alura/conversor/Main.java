package com.alura.conversor;

import com.alura.conversor.exception.ApiConfigurationException;

public class Main {

    public static void main(String[] args) {
        try {
            MenuController menuController = new MenuController();
            menuController.start();
        } catch (ApiConfigurationException e) {
            System.err.println("Error de configuración: " + e.getMessage());
            System.exit(1);
        }
    }
}
