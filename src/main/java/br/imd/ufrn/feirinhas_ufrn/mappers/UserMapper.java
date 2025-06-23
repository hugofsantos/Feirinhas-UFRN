package br.imd.ufrn.feirinhas_ufrn.mappers;

import org.springframework.stereotype.Component;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.dto.UserInfoResponseDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.UserResponseDTO;

@Component
public class UserMapper {
  public UserResponseDTO userResponseDtoFromUser(User user) {
    final UserResponseDTO dto = new UserResponseDTO(
      user.getId(),
      user.getFullname(),
      user.getEmail(),
      user.getWhatsapp(),
      user.getRole().getRole()
    );

    return dto;
  }

  public UserInfoResponseDTO userInfoResponseDtoFromUser(User user) {
    final UserInfoResponseDTO dto = new UserInfoResponseDTO(
        user.getId(),
        user.getFullname(),
        user.getEmail(),
        user.getWhatsapp(),
        user.getRole().getRole(),
        user.getProducts()
      );

    return dto;
  }
}
