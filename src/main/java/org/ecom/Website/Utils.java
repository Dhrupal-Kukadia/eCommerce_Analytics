package org.ecom.Website;

import org.ecom.Website.DTO.ProductAdditionDTO;
import org.ecom.Website.DTO.UserRegistrationDTO;

public class Utils {

    public static void validateUserRegistrationDTO(UserRegistrationDTO userDTO) {
        if(userDTO == null || userDTO.getName().isEmpty() || userDTO.getEmail().isEmpty() || userDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("User registration DTO cannot be null.");
        }
    }

    public static void validateProductAdditionDTO(ProductAdditionDTO productDTO) {
        if(productDTO == null || productDTO.getName().isEmpty() || productDTO.getDesc().isEmpty() || productDTO.getCategory().isEmpty()) {
            throw new IllegalArgumentException("Product addition DTO cannot be null.");
        }
    }
}
