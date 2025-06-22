package br.imd.ufrn.feirinhas_ufrn.domain.feira;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "feira")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Feira {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToMany
    @JoinTable(name = "sellers",
            joinColumns = @JoinColumn(name = "feira_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> sellers;
}
