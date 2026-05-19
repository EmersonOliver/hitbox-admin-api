package br.com.hitbox.infra.service;

import br.com.hitbox.core.gateway.StorageGateway;
import br.com.hitbox.infra.props.UploadProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements StorageGateway {

    private final UploadProperties properties;

    @Override
    public String salvarImagem(MultipartFile file, String folder) {
        try {
            String extension =
                    Objects.requireNonNull(
                            file.getOriginalFilename()
                    ).substring(
                            file.getOriginalFilename()
                                    .lastIndexOf(".")
                    );
            String filename =
                    UUID.randomUUID() + extension;
            Path uploadPath =
                    Paths.get(
                            properties.getDir(),
                            folder
                    );
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath =
                    uploadPath.resolve(filename);
            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );
            return "/uploads/"
                    + folder
                    + "/"
                    + filename;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao salvar imagem",
                    e
            );
        }
    }

    @Override
    public void deleteImagem(String imageUrl) {
        try {

            Files.deleteIfExists(Path.of(imageUrl));

        } catch (Exception ex) {
            log.warn("Erro ao remover imagem",
                    ex
            );
        }
    }

}
