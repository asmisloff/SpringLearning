package ru.asmisloff.ht01.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductRepository prepo = ctx.getBean("productRepository", ProductRepository.class);
        System.out.printf("Products in repository: %s\n", prepo.getProducts().toString());

        CardService bin1 = ctx.getBean("cardService", CardService.class);
        bin1.add(1).add(2).add(3);
        System.out.printf("Bin #1: %s\n", bin1.toString());

        CardService bin2 = ctx.getBean("cardService", CardService.class);
        bin2.add(3).add(4).add(5).add(5).add(5);
        System.out.printf("Bin #1: %s\n", bin2.toString());
        System.out.println("Remove from bin2 product with id = 5");
        bin2.remove(5);
        System.out.printf("Bin #1: %s\n", bin2.toString());
    }

}
