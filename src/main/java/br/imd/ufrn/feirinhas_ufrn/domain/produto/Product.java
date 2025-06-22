package br.imd.ufrn.feirinhas_ufrn.domain.produto;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "product")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String description;

    private String price;

    @ManyToOne
    private User seller;

}
