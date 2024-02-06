package pl.szlify.giftapi.gift.exception;

public class TooManyGiftsException extends RuntimeException {
    public TooManyGiftsException() {
        super("3 gifts is enough for him");
    }
}
