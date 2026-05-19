package br.com.hitbox.infra.config;

import br.com.hitbox.infra.props.UploadProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(
        UploadProperties.class
)
public class UploadConfig {
}
