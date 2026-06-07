package br.com.hitbox.infra.service;

import br.com.hitbox.core.gateway.StorageGateway;
import br.com.hitbox.infra.config.filter.TenantContext;
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
            UUID companyId =
                    TenantContext.getCompanyId();

            String extension =
                    Objects.requireNonNull(
                            file.getOriginalFilename()
                    ).substring(
                            file.getOriginalFilename()
                                    .lastIndexOf(".")
                    );

            String filename =
                    UUID.randomUUID() + extension;

            String fileKey =
                    "companies/"
                            + companyId
                            + "/"
                            + folder
                            + "/"
                            + filename;

            Path uploadPath =
                    Paths.get(
                            properties.getDir(),
                            fileKey
                    );

            Files.createDirectories(
                    uploadPath.getParent()
            );

            Files.copy(
                    file.getInputStream(),
                    uploadPath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return fileKey;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao salvar imagem",
                    e
            );
        }
    }

    @Override
    public void deleteImagem(String fileKey) {
        try {

            Path path =
                    Paths.get(
                            properties.getDir(),
                            fileKey
                    );

            Files.deleteIfExists(path);

        } catch (Exception ex) {

            log.warn(
                    "Erro ao remover imagem",
                    ex
            );
        }
    }

    @Override
    public byte[] recuperarImagem(String fileKey) {
        try {

            Path path =
                    Paths.get(
                            properties.getDir(),
                            fileKey
                    );

            return Files.readAllBytes(path);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao recuperar imagem",
                    e
            );
        }
    }

}
