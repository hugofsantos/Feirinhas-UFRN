package br.imd.ufrn.feirinhas_ufrn.dto;

import br.imd.ufrn.feirinhas_ufrn.domain.produto.Product;
import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.domain.usuario.UserRole;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserDetailDTO {
    private String id;

    private String fullname;

    private String email;

    private String whatsapp;

    private UserRole role;

    private List<Product> products;

    public UserDetailDTO(User user){
        this.id = user.getId();
        this.fullname = user.getFullname();
        this.email = user.getEmail();
        this.whatsapp = user.getWhatsapp();
        this.role = user.getRole();
        this.products = new ArrayList<>();
        this.products.addAll(user.getProducts().stream().collect(Collectors.toList()));
    }
}
