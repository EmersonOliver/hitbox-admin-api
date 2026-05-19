package br.com.hitbox.core.gateway;

import org.springframework.web.multipart.MultipartFile;

public interface StorageGateway {

    String salvarImagem(
            MultipartFile file,
            String folder
    );

    void deleteImagem(String imageUrl);
}
