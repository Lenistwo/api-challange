package pl.lenistwo.apichallange.exception;

public class CurrencyAlreadyExistsException extends RuntimeException {
    public CurrencyAlreadyExistsException() {
        super("CURRENCY_ALREADY_EXISTS");
    }
}
