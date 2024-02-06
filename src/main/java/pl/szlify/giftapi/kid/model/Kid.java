package pl.szlify.giftapi.kid.model;


import jakarta.persistence.*;
import lombok.*;
import pl.szlify.giftapi.gift.model.Gift;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
public class Kid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private LocalDateTime birthday;

    @ManyToMany
//    @CollectionTable(name = "kid_gift", joinColumns = @JoinColumn(name = "kid_id"))
    @JoinTable(
            name = "kid_gift",
            joinColumns = @JoinColumn(name = "kid_id"),
            inverseJoinColumns = @JoinColumn(name = "gift_id")
    )
    private List<Gift> gifts = new ArrayList<>();

    private boolean deleted = false;

    @Version
    private Integer version;

}
