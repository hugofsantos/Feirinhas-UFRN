package br.imd.ufrn.feirinhas_ufrn.domain.feirinha;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "feirinhas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Feirinha {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String description;

    private String location;

    private OffsetDateTime beginTime;

    private OffsetDateTime endTime;

    @ManyToMany
    @JoinTable(name = "user_feirinha",
            joinColumns = @JoinColumn(name = "feirinha_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> sellers;
}
