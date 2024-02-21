package pl.szlify.giftapi.exception;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(LocalDate date) {
        super(MessageFormat.format("Kid have to be under 18 y/o. date={0}. Wrong date is enterd: {0}",
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

    }
}
