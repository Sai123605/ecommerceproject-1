package com.project.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.dto.ProductDt;
import com.project.entity.Category;
import com.project.entity.Product;
import com.project.serice.CategoryService;
import com.project.serice.ProductService;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private CategoryService cservice;

    @Autowired
    private ProductService pservice;

    // GET all categories
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return cservice.getAll();
    }

 // GET a category by ID
    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return cservice.fetchbyId(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
    }

    // ADD a new category
    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category) {
        return cservice.saveCategory(category);
    }

    // DELETE a category
    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable int id) {
        cservice.deletebyId(id);
    }

    // UPDATE a category
    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category category) {
        category.setId(id);
        return cservice.saveCategory(category);
    }

    // GET all products
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return pservice.getAll();
    }

    // DELETE a product
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable long id) {
        pservice.deletebyId(id);
    }
    
    @PostMapping("/products")
    public Product createProduct(@RequestBody ProductDt p) {
        Product pro = new Product();
        pro.setId(p.getId());
        pro.setName(p.getName());
        pro.setPrice(p.getPrice());
        pro.setDescription(p.getDescription());
        pro.setWeight(p.getWeight());
        pro.setCategory(cservice.fetchbyId(p.getCategoryId()).orElseThrow(
            () -> new RuntimeException("Category not found with ID: " + p.getCategoryId())
        ));
        return pservice.saveProduct(pro);
    }

    @GetMapping("/products/{id}")
    public ProductDt getProductById(@PathVariable long id) {
        Product pro = pservice.fetchbyId(id).orElseThrow(
            () -> new RuntimeException("Product not found with ID: " + id)
        );
        ProductDt pdt = new ProductDt();
        pdt.setId(pro.getId());
        pdt.setName(pro.getName());
        pdt.setPrice(pro.getPrice());
        pdt.setWeight(pro.getWeight());
        pdt.setDescription(pro.getDescription());
        pdt.setCategoryId(pro.getCategory().getId());
        
        return pdt;
    }

   
   }
