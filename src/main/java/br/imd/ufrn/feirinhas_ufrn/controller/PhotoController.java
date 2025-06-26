package br.imd.ufrn.feirinhas_ufrn.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.imd.ufrn.feirinhas_ufrn.components.PhotoStorageComponent;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/photo")
@RequiredArgsConstructor
public class PhotoController {
  private final PhotoStorageComponent photoStorageComponent; 

  @GetMapping("/download/{fileName}")
  public ResponseEntity<Resource> downloadPhoto(@PathVariable(required = true) String fileName) throws Exception{
    // Caminho completo do arquivo
    Path filePath = Paths.get(photoStorageComponent.getFileFullPath(fileName)).normalize();

    // Verificar se o arquivo existe e é legível
    if (!Files.exists(filePath) || !Files.isReadable(filePath)) {
        throw new EntityNotFoundException("Arquivo não encontrado ou não pode ser lido: " + fileName);
    }

    // Criar um recurso a partir do arquivo
    Resource resource = new UrlResource(filePath.toUri());

    if (!resource.exists()) {
        throw new EntityNotFoundException("Recurso não encontrado: " + fileName);
    }

    // Detectar o tipo MIME do arquivo
    String mimeType = Files.probeContentType(filePath);
    if (mimeType == null) {
        mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
    }

    // Construir a resposta
    return ResponseEntity.ok()
      //.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
      .header("attachment; filename=\"" + fileName + "\"")
      .contentType(MediaType.parseMediaType(mimeType))
      .body(resource);
  }
  
}
