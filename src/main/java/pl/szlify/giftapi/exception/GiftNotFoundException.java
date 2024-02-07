package pl.szlify.giftapi.exception;

import java.text.MessageFormat;

public class GiftNotFoundException extends RuntimeException {
    public GiftNotFoundException(int giftId) {
        super(MessageFormat.format("Gift with id={0} not found", giftId));
    }
}
