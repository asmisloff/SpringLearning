package ru.asmisloff.spring.ht06;


import ru.asmisloff.spring.ht06.utils.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("ru.asmisloff.spring.ht06.repositories")
@EnableTransactionManagement
@ComponentScan("ru.asmisloff.spring.ht06")
public class AppConfig {

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }
}
