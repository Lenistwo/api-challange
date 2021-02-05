package pl.lenistwo.apichallange.exception;

public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException() {
        super("CURRENCY_NOT_FOUND_EXCEPTION");
    }
}
