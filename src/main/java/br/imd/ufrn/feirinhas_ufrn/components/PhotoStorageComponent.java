package br.imd.ufrn.feirinhas_ufrn.components;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.imd.ufrn.feirinhas_ufrn.exception.BusinessException;

@Component
public class PhotoStorageComponent {
  private String baseUploadDir;

  public PhotoStorageComponent() {
    this.baseUploadDir = System.getProperty("user.home") + "/feirinhas-ufrn-uploads";
    Path uploadPath = Paths.get(baseUploadDir);

    try {
      if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
        System.out.println("Upload directory created at: " + uploadPath.toAbsolutePath());
      }
    } catch (IOException e) {
      throw new RuntimeException("Error creating upload directory: " + uploadPath.toAbsolutePath(), e);
    }
  }
  
  public String storePhoto(MultipartFile file) throws BusinessException{
    if (file.isEmpty()) 
      throw new BusinessException("A foto a ser armazenada está vazia");

    try {
      String fileExtension = getFileExtension(file.getOriginalFilename());
      String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

      // Caminho completo do arquivo
      Path filePath = Paths.get(this.baseUploadDir, uniqueFileName);

      // Salva o arquivo no diretório
      file.transferTo(filePath.toFile());

      return uniqueFileName; // Retorna o nome do arquivo salvo
    } catch (IOException e) {
      throw new RuntimeException("Erro ao salvar o arquivo", e);
    }
  }

  public void deletePhoto(String fileName) {
    if (fileName == null || fileName.isBlank()) {
      throw new RuntimeException("O nome do arquivo a ser removido não pode estar vazio");
    }

    Path filePath = Paths.get(this.baseUploadDir, fileName);

    try {
      Files.deleteIfExists(filePath);
    } catch (IOException e) {
      throw new RuntimeException("Error deleting file: " + fileName, e);
    }
  }
  
  public byte[] downloadPhoto(String fileName) {
    if (fileName == null || fileName.isBlank()) {
      throw new IllegalArgumentException("O nome do arquivo não pode estar vazio");
    }

    Path filePath = Paths.get(this.baseUploadDir, fileName);

    try {
      if (!Files.exists(filePath)) {
        throw new IllegalArgumentException("Arquivo não encontrado: " + fileName);
      }

      // Lê o conteúdo do arquivo como bytes
      return Files.readAllBytes(filePath);
    } catch (IOException e) {
      throw new RuntimeException("Erro ao ler arquivo: " + fileName, e);
    }
  }  
  
  public String getFileExtension(String fileName) {
    if (fileName == null || !fileName.contains(".")) {
      return ""; // Sem extensão
    }
    return fileName.substring(fileName.lastIndexOf("."));
  }
  
  public String getFileFullPath(String fileName) {
    // Combina o diretório base com o nome do arquivo
    Path filePath = Paths.get(this.baseUploadDir, fileName);
    return filePath.toString();
  }  
}
