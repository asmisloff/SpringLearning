package ru.asmisloff.spring.ht07.populators;

import ru.asmisloff.spring.ht07.data.ProductData;
import ru.asmisloff.spring.ht07.entities.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductPopulator implements Populator<Product, ProductData> {

    @Override
    public ProductData populate(Product product, ProductData productData) {
        if(product == null || productData == null)
            return null;
        productData.setId(product.getId());
        productData.setTitle(product.getTitle());
        productData.setBrandName(product.getBrandName());
        productData.setPrice(product.getPrice());
        productData.setCreateDate(product.getCreateDate());
        productData.setModifyDate(product.getModifyDate());
        return productData;
    }

    @Override
    public ProductData populate(Product product) {
        return populate(product, new ProductData());
    }
}
