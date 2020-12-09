package ru.asmisloff.spring.ht07;


import ru.asmisloff.spring.ht07.utils.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("ru.asmisloff.spring.ht07.repositories")
@EnableTransactionManagement
@ComponentScan("ru.asmisloff.spring.ht07.lesson6")
public class AppConfig {

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }
}
