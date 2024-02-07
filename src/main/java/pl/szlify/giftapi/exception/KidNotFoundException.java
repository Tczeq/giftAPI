package pl.szlify.giftapi.exception;

import java.text.MessageFormat;

public class KidNotFoundException extends RuntimeException {
    public KidNotFoundException(Integer kidId) {
        super(MessageFormat.format("Kid with id={0} not found", kidId));
    }
}
