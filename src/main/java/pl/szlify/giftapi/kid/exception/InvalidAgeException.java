package pl.szlify.giftapi.kid.exception;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(LocalDateTime dateTime) {
        super(MessageFormat.format("Kid have to be under 18 y/o. dateTime={0}. Wrong date is enterd: {0}",
                dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

    }
}
