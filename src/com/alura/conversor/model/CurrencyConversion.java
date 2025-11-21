package com.alura.conversor.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyConversion {
    private final String baseCode;
    private final String targetCode;
    private final BigDecimal conversionRate;
    private BigDecimal amount;
    private final BigDecimal conversionResult;

    public CurrencyConversion() {
        this.baseCode = "";
        this.targetCode = "";
        this.conversionRate = BigDecimal.ZERO;
        this.conversionResult = BigDecimal.ZERO;
    }

    public CurrencyConversion(String baseCode, String targetCode, double conversionRate, double conversionResult) {
        this.baseCode = baseCode;
        this.targetCode = targetCode;
        this.conversionRate = BigDecimal.valueOf(conversionRate).setScale(6, RoundingMode.HALF_UP);
        this.conversionResult = BigDecimal.valueOf(conversionResult).setScale(2, RoundingMode.HALF_UP);
    }

    public String getBaseCode() {
        return baseCode;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getConversionResult() {
        return conversionResult;
    }

    @Override
    public String toString() {
        return String.format("El valor de %.2f [%s] corresponde al valor final de ==> %.2f [%s]",
                amount, baseCode, conversionResult, targetCode);
    }
}
