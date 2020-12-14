package ru.asmisloff.spring.ht08.controllers;

import ru.asmisloff.spring.ht08.services.CategoryService;
import ru.asmisloff.spring.ht08.services.ProductService;
import ru.asmisloff.spring.ht08.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomepageController {

    private CategoryService categoryService;
    private ProductService productService;
    private UserService userService;

    public HomepageController(CategoryService categoryService, ProductService productService, UserService userService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping
    public String main(
            @RequestParam(name = "p", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "s", required = false, defaultValue = "6") Integer size,
            Model model,
            Principal principal
    ){
        page = page > 0 ? page - 1 : page;
        model.addAttribute("products", productService.findAll(null, page, size));
        model.addAttribute("categories", categoryService.findAll());
        return "index";
    }
}
