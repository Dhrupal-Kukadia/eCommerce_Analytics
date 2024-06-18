package org.ecom.Website.Controller;

import org.ecom.Website.Model.Product;
import org.ecom.Website.DTO.ProductAdditionDTO;
import org.ecom.Website.Service.ProductService;
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

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @GetMapping("/name/{name}")
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
