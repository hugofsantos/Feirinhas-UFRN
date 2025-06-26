package br.imd.ufrn.feirinhas_ufrn.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.dto.product.ProductResponseDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.user.UserInfoResponseDTO;
import br.imd.ufrn.feirinhas_ufrn.dto.user.UserResponseDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {
  private final ProductMapper productMapper;

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
    if(user == null) return null;

    final List<ProductResponseDTO> productList = user
      .getProducts()
      .stream()
      .map(productMapper::responseDtoFromProduct)
      .collect(Collectors.toList());

    final UserInfoResponseDTO dto = new UserInfoResponseDTO(
        user.getId(),
        user.getFullname(),
        user.getEmail(),
        user.getWhatsapp(),
        user.getRole().getRole(),
        productList
    );

    return dto;
  }
}
