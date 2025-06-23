package br.imd.ufrn.feirinhas_ufrn.dto;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserDTO {
    private String id;
    private String fullname;
    private String email;
    private String whatsapp;

    public UserDTO (User user) {
        this.id = user.getId();
        this.fullname = user.getFullname();
        this.email = user.getEmail();
        this.whatsapp = user.getWhatsapp();
    }

    public static List<UserDTO> convertFromUserDTO(List<User> users){
        return users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
    }
}

