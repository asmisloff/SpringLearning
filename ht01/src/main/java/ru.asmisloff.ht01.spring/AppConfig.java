package ru.asmisloff.ht01.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean(name = "productRepository")
    public ProductRepository productRepository() {
        return new ProductRepository();
    }

    @Bean(name = "cardService")
    @Scope("prototype")
    public CardService cardService() {
        return new CardService();
    }

}
