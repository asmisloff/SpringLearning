package ru.asmisloff.spring.ht05.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.asmisloff.spring.ht05.entities.Order;

public class OrderSpecification {

    public static Specification<Order> priceGreaterThanOrEqualTo(int minPrice) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("currentPrice"), minPrice);
    }

    public static Specification<Order> priceLesserThanOrEqualTo(int maxPrice) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("currentPrice"), maxPrice);
    }

    public static Specification<Order> codeLike(String codePart) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("code"), String.format("%%%s%%", codePart));
    }

    public static Specification<Order> customerLike(String namePart) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("customer").get("name")),
                    String.format("%%%s%%", namePart).toLowerCase());
    }

}
