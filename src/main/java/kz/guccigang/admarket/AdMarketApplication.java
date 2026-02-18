package kz.guccigang.admarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("kz.guccigang.admarket.config.properties")
public class AdMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdMarketApplication.class, args);
    }

}
