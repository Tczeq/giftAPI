package pl.szlify.giftapi.exception;

public class TooManyGiftsException extends RuntimeException {
    public TooManyGiftsException() {
        super("Kid can have maximum of 3 gifts");
    }
}
