package pl.szlify.giftapi.gift.model;

import jakarta.persistence.*;
import lombok.*;
import pl.szlify.giftapi.kid.model.Kid;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "kid_id")
    private Kid kid;

    private boolean deleted = false;

    @Version
    private Integer version;
}
