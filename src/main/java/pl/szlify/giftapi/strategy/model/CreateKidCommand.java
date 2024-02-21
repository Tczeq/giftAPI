package pl.szlify.giftapi.strategy.model;

import lombok.Data;

import java.util.Map;

@Data
public class CreateKidCommand {

    private String type;
    private Map<String, String> params;
}
