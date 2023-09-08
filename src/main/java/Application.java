import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.jwt.server", "com.service"})
@EnableJpaRepositories(basePackages = "com.service")
@EntityScan("com.service")
public class Application {
    public static void main(String[] args) throws NoSuchAlgorithmException {
//        SecretKeyFactory;
//        KeyPairGenerator
//        KeyGenerator.getInstance("AES").init(256, new SecureRandom());
      KeyGenerator k =   KeyGenerator.getInstance("AES");
      k.init(256, new SecureRandom());
        SpringApplication.run(Application.class, args);
    }
}
