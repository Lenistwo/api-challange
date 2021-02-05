package pl.lenistwo.apichallange.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("PRODUCT_NOT_FOUND");
    }
}
