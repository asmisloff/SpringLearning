package ru.asmisloff.spring.ht06.controllers;

import ru.asmisloff.spring.ht06.entities.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.asmisloff.spring.ht06.exceptions.ResourceNotFoundException;
import ru.asmisloff.spring.ht06.services.CategoryService;
import ru.asmisloff.spring.ht06.services.ProductService;

@Controller()
@RequestMapping("/categories")
public class CategoryController {

    CategoryService categoryService;
    ProductService productService;

    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public String showAllCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories";
    }

    @GetMapping("/{category_name}")
    public String showProductsInCategory(Model model,
                                         @PathVariable("category_name") String categoryName,
                                         @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        Category category = categoryService.findByName(categoryName).orElse(null);
        if (category == null) {
            throw new ResourceNotFoundException(
                    String.format("Category with name = %s doesn't exist", categoryName));
        }
        model.addAttribute("category", category);
        model.addAttribute("products", productService.findAllByCategoryId(category.getId(), page - 1, 5));
        return "products_in_category";
    }

}
