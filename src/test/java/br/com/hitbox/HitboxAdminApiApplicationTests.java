package br.com.hitbox;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HitboxAdminApiApplicationTests {


    @Test
    void teste_getEnv() {
        System.out.println(System.getenv("URL"));
        assert System.getenv("URL") != null;
    }

}
