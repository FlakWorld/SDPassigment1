package currencyConverter;

import java.util.ArrayList;
import java.util.HashMap;

public class Currency {
	private String name;
	private String shortName;

	public Currency(String nameValue, String shortNameValue) {
		this.name = nameValue;
		this.shortName = shortNameValue;
	}

	public String getName() {
		return this.name;
	}

	public String getShortName() {
		return this.shortName;
	}

	public static ArrayList<Currency> init() {
		ArrayList<Currency> currencies = new ArrayList<Currency>();

		currencies.add(new Currency("US Dollar", "USD"));
		currencies.add(new Currency("Euro", "EUR"));
		currencies.add(new Currency("British Pound", "GBP"));
		currencies.add(new Currency("Swiss Franc", "CHF"));
		currencies.add(new Currency("Chinese Yuan Renminbi", "CNY"));
		currencies.add(new Currency("Japanese Yen", "JPY"));

		RateManager rateManager = RateManager.getInstance();

		// Set exchange rates using RateManager
		rateManager.setExchangeRate("US Dollar", "USD", 1.00);
		rateManager.setExchangeRate("US Dollar", "EUR", 0.93);
		rateManager.setExchangeRate("US Dollar", "GBP", 0.66);
		// Add exchange rates for other currencies here...

		return currencies;
	}

	public static Double convert(Double amount, String fromCurrency, String toCurrency) {
		RateManager rateManager = RateManager.getInstance();
		Double exchangeRate = rateManager.getExchangeRate(fromCurrency, toCurrency);

		if (exchangeRate != null) {
			Double price = amount * exchangeRate;
			return Math.round(price * 100d) / 100d;
		} else {
			// Handle case where exchange rate is not found
			return null;
		}
	}
}
