package ru.asmisloff.spring.ht08.facade;


import ru.asmisloff.spring.ht08.data.ProductData;
import ru.asmisloff.spring.ht08.entities.Product;
import ru.asmisloff.spring.ht08.populators.ProductPopulator;
import ru.asmisloff.spring.ht08.repositories.ProductDataRepository;
import ru.asmisloff.spring.ht08.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductFacade {

    private ProductPopulator productPopulator;
    private ProductService productService;
    private ProductDataRepository productDataRepository;

    public ProductFacade(ProductPopulator productPopulator, ProductService productService, ProductDataRepository productDataRepository) {
        this.productPopulator = productPopulator;
        this.productService = productService;
        this.productDataRepository = productDataRepository;
    }

    public ProductData getProductById(Long id){
        Product product = productService.findById(id).get();
        ProductData productData = new ProductData();
        productData.setId(product.getId());
        productData.setTitle(product.getTitle());
        productData.setBrandName(product.getBrandName());
        productData.setPrice(product.getPrice());
        return productData;
    }

    public List<ProductData> getAllProductData(){
        return productPopulator.convertAll(productService.findAll());
    }

    public List<ProductData> getAllProductDataFromRepository(){
        return productDataRepository.findAllProductData();
    }
}
