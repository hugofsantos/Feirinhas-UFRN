package br.imd.ufrn.feirinhas_ufrn.configuration; // ou um pacote de sua preferência

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.imd.ufrn.feirinhas_ufrn.domain.usuario.User;
import br.imd.ufrn.feirinhas_ufrn.domain.usuario.UserRole;
import br.imd.ufrn.feirinhas_ufrn.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class AdminUserInitializer implements CommandLineRunner {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  // Injeta os valores do application.properties
  @Value("${admin.default.fullname}")
  private String adminFullname;

  @Value("${admin.default.email}")
  private String adminEmail;

  @Value("${admin.default.password}")
  private String adminPassword;

  @Override
  public void run(String... args) throws Exception {
    // Verifica se o usuário admin já existe no banco de dados
    if (userRepository.findByEmail(adminEmail).isPresent()) {
      System.out.println("Usuário administrador padrão já existe. Nenhuma ação necessária.");
      return;
    }

    System.out.println("Usuário administrador padrão não encontrado. Criando novo usuário admin...");

    // Cria a nova entidade de usuário
    final User adminUser = new User();
    adminUser.setFullname(adminFullname);
    adminUser.setEmail(adminEmail); // Supondo que seu User tenha um campo email
    adminUser.setWhatsapp("(00) 00000-0000");

    // **IMPORTANTE**: Codifica a senha antes de salvar
    adminUser.setPassword(passwordEncoder.encode(adminPassword));

    adminUser.setRole(UserRole.ADMIN); // Define a role como ADMIN

    // Salva o novo usuário no banco de dados
    userRepository.save(adminUser);

    System.out.println("Usuário administrador padrão criado com sucesso!");
  }
}