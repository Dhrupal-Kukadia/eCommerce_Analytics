package org.ecom.Service;

import org.ecom.Model.Product;
import org.ecom.DTOs.ProductAdditionDTO;
import org.ecom.Repository.ProductRepository;
import org.ecom.Utils;
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
