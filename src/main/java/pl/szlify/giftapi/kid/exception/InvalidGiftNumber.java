package pl.szlify.giftapi.kid.exception;

public class InvalidGiftNumber extends RuntimeException {

    public InvalidGiftNumber() {
        super("Number of gifts is too much for kid. Max is 3");
    }
}
