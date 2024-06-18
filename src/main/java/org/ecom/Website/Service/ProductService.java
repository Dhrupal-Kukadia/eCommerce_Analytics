package org.ecom.Website.Service;

import org.ecom.Website.Model.Product;
import org.ecom.Website.DTO.ProductAdditionDTO;
import org.ecom.Website.Repository.ProductRepository;
import org.ecom.Website.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    public void addProduct(ProductAdditionDTO productDTO) {
        Utils.validateProductAdditionDTO(productDTO);
        Product product = productDTO.createProductFromProductDTO();
        productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
