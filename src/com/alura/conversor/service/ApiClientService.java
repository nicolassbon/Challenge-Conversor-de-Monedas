package com.alura.conversor.service;

import com.alura.conversor.exception.ApiConfigurationException;
import com.alura.conversor.exception.HttpErrorException;
import com.alura.conversor.exception.InvalidAmountException;
import com.alura.conversor.model.CurrencyConversion;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;

public class ApiClientService {
    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/";
    private final String apiKey;
    private final HttpClientService httpClientService;
    private final Gson gson;

    public ApiClientService() {
        this.apiKey = System.getenv("API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new ApiConfigurationException("API_KEY no está configurada. Configure la variable de entorno API_KEY");
        }

        this.httpClientService = new HttpClientService();
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }

    public CurrencyConversion getConversion(String baseCode, String targetCode, BigDecimal amount) {
        validateAmount(amount);

        String url = URL_BASE + apiKey + "/pair/" + baseCode + "/" + targetCode + "/" + amount;

        try {
            String response = httpClientService.get(url);
            CurrencyConversion currencyConversion = gson.fromJson(response, CurrencyConversion.class);

            currencyConversion.setAmount(amount);

            return currencyConversion;
        } catch (HttpErrorException e) {
            throw new HttpErrorException("Error al obtener la conversión: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado: " + e.getMessage());
        }
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null) {
            throw new InvalidAmountException("El monto no puede ser nulo.");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("El monto debe ser mayor a cero.");
        }
    }
}
