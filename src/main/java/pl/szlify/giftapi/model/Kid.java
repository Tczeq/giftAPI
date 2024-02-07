package pl.szlify.giftapi.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "UPDATE kid SET deleted = true, version = version + 1 WHERE id = ? AND version = ?")
@Where(clause = "deleted = false")
public class Kid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private LocalDateTime birthday;

    @ManyToMany
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
