package pl.szlify.giftapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;
import pl.szlify.giftapi.model.Kid;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Where(clause = "deleted = false")
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double price;

    @ManyToOne
    private Kid kid;

    private boolean deleted = false;

    @Version
    private Integer version;
}
