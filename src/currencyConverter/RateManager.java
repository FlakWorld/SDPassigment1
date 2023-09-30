package currencyConverter;

import java.util.HashMap;

public class RateManager {
    private static RateManager instance;
    private HashMap<String, HashMap<String, Double>> exchangeRates;

    private RateManager() {
        exchangeRates = new HashMap<>();
    }

    public static RateManager getInstance() {
        if (instance == null) {
            instance = new RateManager();
        }
        return instance;
    }

    public void setExchangeRate(String fromCurrency, String toCurrency, Double rate) {
        // Implement code to set exchange rate between fromCurrency and toCurrency.
        exchangeRates.computeIfAbsent(fromCurrency, k -> new HashMap<>())
                .put(toCurrency, rate);
        // Since exchange rates are typically bidirectional, you may also want to set the reverse rate.
        exchangeRates.computeIfAbsent(toCurrency, k -> new HashMap<>())
                .put(fromCurrency, 1 / rate);
    }

    public Double getExchangeRate(String fromCurrency, String toCurrency) {
        // Implement code to get the exchange rate between fromCurrency and toCurrency.
        // Return null if the rate is not found.
        if (exchangeRates.containsKey(fromCurrency)) {
            HashMap<String, Double> rates = exchangeRates.get(fromCurrency);
            if (rates.containsKey(toCurrency)) {
                return rates.get(toCurrency);
            }
        }
        return null;
    }
}
