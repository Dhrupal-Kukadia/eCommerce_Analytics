package org.ecom.Controller;

import org.ecom.Model.Product;
import org.ecom.DTOs.ProductAdditionDTO;
import org.ecom.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody ProductAdditionDTO productDTO) {
        productService.addProduct(productDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}
