package pl.szlify.giftapi.gift.exception;

public class TooManyGiftsException extends RuntimeException {
    public TooManyGiftsException(String message) {
        super(message);
    }
}
