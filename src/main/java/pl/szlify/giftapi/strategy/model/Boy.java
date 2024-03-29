package pl.szlify.giftapi.strategy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pl.szlify.giftapi.model.Kid;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
//@Table(name = "boy")
public class Boy extends Kid {


//    @Column(name = "pipe_length")
    private double pipeLength;
}
