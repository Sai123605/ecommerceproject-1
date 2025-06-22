package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.entity.Category;
import com.project.entity.Product;
import com.project.serice.CategoryService;
import com.project.serice.ProductService;

@RestController
@RequestMapping("/api")
public class PageController {

    @Autowired
    private CategoryService cservice;

    @Autowired
    private ProductService pservice;

    // categories and products (like /shop)
    @GetMapping("/shop")
    public ShopResponse getShopData() {
        List<Category> categories = cservice.getAll();
        List<Product> products = pservice.getAll();
        return new ShopResponse(categories, products);
    }

    

  
    // Dummy endpoint for user form (you can expand this based on user input requirement)
    @GetMapping("/userform")
    public String userFormPlaceholder() {
        return "User form endpoint placeholder. Define user input handling here.";
    }

    // Customized response class for shop data
    public static class ShopResponse {
        private List<Category> categories;
        private List<Product> products;

        public ShopResponse(List<Category> categories, List<Product> products) {
            this.categories = categories;
            this.products = products;
        }

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }
    }
}
