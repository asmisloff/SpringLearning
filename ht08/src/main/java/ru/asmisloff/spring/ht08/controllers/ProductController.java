package ru.asmisloff.spring.ht08.controllers;

import ru.asmisloff.spring.ht08.entities.Product;
import ru.asmisloff.spring.ht08.exceptions.ResourceNotFoundException;
import ru.asmisloff.spring.ht08.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProductById(
            @PathVariable Long id,
            Model model
    ) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists"));
        model.addAttribute("product", product);
        return "product";
    }


}
