package ru.asmisloff.spring.ht05.utils;

import org.springframework.data.jpa.domain.Specification;
import ru.asmisloff.spring.ht05.entities.Order;
import ru.asmisloff.spring.ht05.repositories.specifications.OrderSpecification;

import java.util.Map;

public class OrderFilter {
    private Specification<Order> spec;
    private String filterDefinition;

    public OrderFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);

        String codePart = params.get("code_part");
        if (codePart != null && !codePart.isBlank()) {
            filterDefinitionBuilder.append("&code_part=").append(codePart);
            spec = spec.and(OrderSpecification.codeLike(codePart));
        }

        int minPrice = tryParse(params.get("min_price"), 0);
        if (minPrice > 0) {
            filterDefinitionBuilder.append("&min_price=").append(minPrice);
            spec = spec.and(OrderSpecification.priceGreaterThanOrEqualTo(minPrice));
        }

        int maxPrice = tryParse(params.get("max_price"), Integer.MAX_VALUE);
        if (maxPrice > 0 && maxPrice < Integer.MAX_VALUE) {
            filterDefinitionBuilder.append("&max_price=").append(maxPrice);
            spec = spec.and(OrderSpecification.priceLesserThanOrEqualTo(maxPrice));
        }

        String namePart = params.get("name_part");
        if (namePart != null && !namePart.isBlank()) {
            filterDefinitionBuilder.append("&name_part=").append(namePart);
            spec = spec.and(OrderSpecification.customerLike(namePart));
        }

        filterDefinition = filterDefinitionBuilder.toString();
    }

    public Specification<Order> getSpec() {
        return spec;
    }

    public String getFilterDefinition() {
        return filterDefinition;
    }

    private int tryParse(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
