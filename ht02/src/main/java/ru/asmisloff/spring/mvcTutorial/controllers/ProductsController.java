package ru.asmisloff.spring.mvcTutorial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.asmisloff.spring.mvcTutorial.model.Product;
import ru.asmisloff.spring.mvcTutorial.repositories.ProductRepository;

import javax.jws.WebParam;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private ProductRepository rep;

    @Autowired
    public ProductsController(ProductRepository rep) {
        this.rep = rep;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("products", rep.getProducts());
        return "products";
    }

    @GetMapping(value = "/product/{id}")
    public String showProduct(
            @PathVariable int id,
            Model model
    ) {
        model.addAttribute("product", rep.get(id));
        return "product";
    }

    @GetMapping("/add_product")
    public String showAddForm() {
        return "add_product";
    }

    @PostMapping("/add_product")
    public String addProduct(@ModelAttribute Product p) {
        p.setId(rep.generateNewId());
        rep.add(p);
        return "redirect:/products/";
    }

    @GetMapping("/edit_product/{id}")
    public String showEditForm(@PathVariable int id, Model m) {
        m.addAttribute(rep.get(id));
        return "edit_product";
    }

    @PostMapping("/edit_product")
    public String editProduct(@ModelAttribute Product p) {
        rep.updateProduct(p);
        return "redirect:/products/";
    }

}
