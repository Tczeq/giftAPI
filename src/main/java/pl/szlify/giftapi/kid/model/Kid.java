package pl.szlify.giftapi.kid.model;


import jakarta.persistence.*;
import lombok.*;
import pl.szlify.giftapi.gift.Gift;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Kid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private LocalDateTime birthday;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "kid_gift", joinColumns = @JoinColumn(name = "kid_id"))
    private List<Gift> gifts;

    private boolean deleted = false;

    @Version
    private Integer version;

}
