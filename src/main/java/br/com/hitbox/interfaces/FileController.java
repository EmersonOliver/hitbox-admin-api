package br.com.hitbox.interfaces;

import br.com.hitbox.core.gateway.StorageGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final StorageGateway storageGateway;

    @GetMapping
    public ResponseEntity<byte[]> getFile(
            @RequestParam String key
    ) {

        byte[] file =
                storageGateway.recuperarImagem(
                        key
                );

        MediaType mediaType =
                resolveContentType(key);

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(file);
    }

    private MediaType resolveContentType(
            String key
    ) {

        String lower =
                key.toLowerCase();

        if (lower.endsWith(".png")) {
            return MediaType.IMAGE_PNG;
        }

        if (
                lower.endsWith(".jpg")
                        || lower.endsWith(".jpeg")
        ) {
            return MediaType.IMAGE_JPEG;
        }

        if (lower.endsWith(".webp")) {
            return MediaType.valueOf(
                    "image/webp"
            );
        }

        return MediaType.APPLICATION_OCTET_STREAM;
    }
}