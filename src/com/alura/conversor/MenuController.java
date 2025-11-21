package com.alura.conversor;

import com.alura.conversor.exception.HttpErrorException;
import com.alura.conversor.exception.InvalidAmountException;
import com.alura.conversor.model.ConversionPair;
import com.alura.conversor.model.Currency;
import com.alura.conversor.service.ApiClientService;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuController {
    private static final Map<Integer, ConversionPair> CONVERSION_OPTIONS;

    static {
        CONVERSION_OPTIONS = new LinkedHashMap<>();
        CONVERSION_OPTIONS.put(1, new ConversionPair(Currency.USD, Currency.ARS));
        CONVERSION_OPTIONS.put(2, new ConversionPair(Currency.ARS, Currency.USD));
        CONVERSION_OPTIONS.put(3, new ConversionPair(Currency.USD, Currency.BRL));
        CONVERSION_OPTIONS.put(4, new ConversionPair(Currency.BRL, Currency.USD));
        CONVERSION_OPTIONS.put(5, new ConversionPair(Currency.USD, Currency.COP));
        CONVERSION_OPTIONS.put(6, new ConversionPair(Currency.COP, Currency.USD));
    }

    private static final int EXIT_OPTION = 7;

    private final ApiClientService apiClientService;
    private final Scanner scanner;

    public MenuController() {
        this.apiClientService = new ApiClientService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int option = 0;

        while (option != EXIT_OPTION) {
            showOptions();

            try {
                option = Integer.parseInt(scanner.nextLine().trim());

                if (CONVERSION_OPTIONS.containsKey(option)) {
                    processConversion(option);
                } else if (option == EXIT_OPTION) {
                    System.out.println("Gracias por utilizar el conversor de moneda. ¡Hasta luego!");
                } else {
                    System.out.println("Error: Opción inválida. Por favor, elige una opción del 1 al 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido");
            }
        }

        scanner.close();
    }

    public void showOptions() {
        System.out.println("\n********************************************************");
        System.out.println("Sea bienvenido/a al Conversor de Moneda :)");

        CONVERSION_OPTIONS.forEach((key, pair) -> System.out.printf("%d) %s ==> %s%n",
                key,
                pair.getFrom().getDisplayName(),
                pair.getTo().getDisplayName()
        ));

        System.out.println("7) Salir");
        System.out.println("Elija una opción válida:");
        System.out.println("********************************************************");
        System.out.print("-- ");
    }

    public void processConversion(int option) {
        ConversionPair pair = CONVERSION_OPTIONS.get(option);

        try {
            System.out.println("Ingrese el valor que desea convertir:");
            System.out.print("-- ");

            String input = scanner.nextLine().trim();
            BigDecimal amount = new BigDecimal(input);

            var conversion = apiClientService.getConversion(
                    pair.getFromCode(),
                    pair.getToCode(),
                    amount
            );

            System.out.println(conversion);
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido (por ejemplo: 100.50)");
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (HttpErrorException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}

