package br.imd.ufrn.feirinhas_ufrn.domain.usuario;

import br.imd.ufrn.feirinhas_ufrn.domain.feira.Feira;
import br.imd.ufrn.feirinhas_ufrn.domain.produto.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User{
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String fullname;

    @Column(unique = true)
    private String email;

    private String password;

    private String whatsapp;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "seller")
    private List<Product> products;

    @ManyToMany(mappedBy = "sellers")
    private List<Feira> feiras;

    public User(String fullname, String email, String password, String whatsapp, UserRole role){
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.whatsapp = whatsapp;
        this.role = role;
    }
}
